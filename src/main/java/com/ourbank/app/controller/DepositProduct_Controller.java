package com.ourbank.app.controller;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ourbank.app.PageNumberingManager;
import com.ourbank.app.bean.DepositBoard_Bean;
import com.ourbank.app.service.DepositProduct_Service;

@Controller
public class DepositProduct_Controller {
	@Autowired
	private DepositProduct_Service boardService;

	private static final Logger logger = LoggerFactory.getLogger(DepositProduct_Controller.class);

	@RequestMapping(value = "/depositList.do", method = RequestMethod.GET)
	public String depositList(@RequestParam("current_page") String currentPage, Model model) {
		int total_cnt = new Integer(boardService.nDepositProduct());

		int total_page = PageNumberingManager.getInstance().getTotalPage(total_cnt, 3);
		int first_block = PageNumberingManager.getInstance().getFirstPageInBlock(Integer.parseInt(currentPage), 10);
		int last_block = PageNumberingManager.getInstance().getLastPageInBlock(Integer.parseInt(currentPage), 10);

		model.addAttribute("total_page", total_page);
		model.addAttribute("totalCnt", total_cnt);// 전체 글수
		model.addAttribute("current_page", currentPage);
		model.addAttribute("boardList", boardService.selectDepositList(Integer.parseInt(currentPage), 3));
		model.addAttribute("last_page", Integer.parseInt(currentPage) + 9);
		model.addAttribute("all_bank", boardService.selectAllBank());
		return "/board_product/deposit/depositList";
	}

	@RequestMapping(value = "/depositContent.do", method = RequestMethod.GET)
	public String depositContent(@RequestParam("current_page") int currentPage,
			@RequestParam("fin_prdt_cd") String fin_prdt_cd, Model model) {

		logger.info("depositContent called");
		DepositBoard_Bean depositBean = boardService.selectDepositContent(fin_prdt_cd);

		// 금융회사 최종 제출일
		String year = depositBean.getFin_co_subm_day().substring(0, 4);
		String month = depositBean.getFin_co_subm_day().substring(4, 6);
		String day = depositBean.getFin_co_subm_day().substring(6, 8);
		String submitDay = (year + "-" + month + "-" + day).toString();
		logger.info(submitDay);
		
		//가입방법
		String[] joinWay_part=depositBean.getJoin_way().split(",");
		
		StringBuilder joinWay = new StringBuilder( " " );
		for(int i = 0; i<joinWay_part.length;i++) {
			joinWay.append(joinWay_part[i]);
			if(i<joinWay_part.length-1) {
			joinWay.append("|");
			}
		}
		
		String join_deny="";
		//가입제한
		if(depositBean.getJoin_deny().equals("1")) {
			join_deny="제한없음";
		}else if(depositBean.getJoin_deny().equals("2")) {
			join_deny="서민전용";
		}else {
			join_deny="일부제한";
		}
		
		//우대사항 줄바꿈 처리
		depositBean.setMtrt_int(depositBean.getMtrt_int().replace("\n", "<br>"));
		
		//기타사항 줄바꿈 처리
		depositBean.setEtc_note(depositBean.getEtc_note().replace("\n", "<br>"));
		
		//우대조건 줄바꿈 처리
		depositBean.setSpcl_cnd(depositBean.getSpcl_cnd().replace("\n", "<br>"));
		
		model.addAttribute("depositBean", depositBean);
		model.addAttribute("submitDay", submitDay);
		model.addAttribute("joinWay",joinWay);
		model.addAttribute("join_deny", join_deny);
		

		return "/board_product/deposit/depositContent";
	}
	
	

}
