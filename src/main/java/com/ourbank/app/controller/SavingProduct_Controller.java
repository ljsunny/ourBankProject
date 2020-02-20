package com.ourbank.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ourbank.app.PageNumberingManager;
import com.ourbank.app.bean.DepositBoard_Bean;
import com.ourbank.app.bean.SavingBoard_Bean;
import com.ourbank.app.service.SavingService;

@Controller
public class SavingProduct_Controller {
	@Autowired
	private SavingService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(SavingProduct_Controller.class);
	
	@RequestMapping(value = "/savingList.do", method = RequestMethod.GET)
	public String depositList(@RequestParam("current_page") String currentPage, Model model) {
		int total_cnt = new Integer(boardService.nSavingProduct());

		int total_page = PageNumberingManager.getInstance().getTotalPage(total_cnt, 3);
		int first_block = PageNumberingManager.getInstance().getFirstPageInBlock(Integer.parseInt(currentPage), 10);
		int last_block = PageNumberingManager.getInstance().getLastPageInBlock(Integer.parseInt(currentPage), 10);

		model.addAttribute("total_page", total_page);
		model.addAttribute("totalCnt", total_cnt);// 전체 글수
		model.addAttribute("current_page", currentPage);
		model.addAttribute("boardList", boardService.selectSavingList(Integer.parseInt(currentPage), 3));
		model.addAttribute("last_page", Integer.parseInt(currentPage) + 9);
		model.addAttribute("all_bank", boardService.selectAllBank());

		return "/board_product/saving/savingList";
	}
	

	@RequestMapping(value = "/savingContent.do", method = RequestMethod.GET)
	public String savingContent(@RequestParam("current_page") int currentPage,
			@RequestParam("fin_prdt_cd") String fin_prdt_cd,
			Model model) {

		logger.info("depositContent called");
		SavingBoard_Bean savingBean = boardService.selectSavingContent(fin_prdt_cd);

		// 금융회사 최종 제출일
		String year = savingBean.getFin_co_subm_day().substring(0, 4);
		String month = savingBean.getFin_co_subm_day().substring(4, 6);
		String day = savingBean.getFin_co_subm_day().substring(6, 8);
		String submitDay = (year + "-" + month + "-" + day).toString();
		logger.info(submitDay);
		
		//가입방법
		String[] joinWay_part=savingBean.getJoin_way().split(",");
		
		StringBuilder joinWay = new StringBuilder( " " );
		for(int i = 0; i<joinWay_part.length;i++) {
			joinWay.append(joinWay_part[i]);
			if(i<joinWay_part.length-1) {
			joinWay.append("|");
			}
		}
		
		String join_deny="";
		//가입제한
		if(savingBean.getJoin_deny().equals("1")) {
			join_deny="제한없음";
		}else if(savingBean.getJoin_deny().equals("2")) {
			join_deny="서민전용";
		}else {
			join_deny="일부제한";
		}
		
		//우대사항 줄바꿈 처리
		savingBean.setMtrt_int(savingBean.getMtrt_int().replace("\n", "<br>"));
		
		//기타사항 줄바꿈 처리
		savingBean.setEtc_note(savingBean.getEtc_note().replace("\n", "<br>"));
		
		//우대조건 줄바꿈 처리
		savingBean.setSpcl_cnd(savingBean.getSpcl_cnd().replace("\n", "<br>"));
		
		model.addAttribute("savingBean", savingBean);
		model.addAttribute("submitDay", submitDay);
		model.addAttribute("joinWay",joinWay);
		model.addAttribute("join_deny", join_deny);
		

		return "/board_product/saving/savingContent";
	}

}
