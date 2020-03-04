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
	public String depositList(@RequestParam("current_page") int currentPage, Model model) {
		int total_cnt = new Integer(boardService.nSavingProduct());

		int total_page = PageNumberingManager.getInstance().getTotalPage(total_cnt, 3);

		model.addAttribute("total_page", total_page);
		model.addAttribute("totalCnt", total_cnt);// ��ü �ۼ�
		model.addAttribute("current_page", currentPage);
		model.addAttribute("boardList", boardService.selectSavingList(currentPage, 3));
		model.addAttribute("all_bank", boardService.selectAllBank());

		return "/board_product/saving/savingList";
	}
	
	
	@RequestMapping(value = "/savingByBank.do", method = RequestMethod.GET)
	public String depositByBank(@RequestParam("current_page") String currentPage,
								@RequestParam("bank") String kor_co_nm ,
								Model model) {
		
		logger.info("depositByBank called");
		logger.info("bank: "+kor_co_nm);
		int total_cnt = new Integer(boardService.nSavingProduct(kor_co_nm));

		int total_page = PageNumberingManager.getInstance().getTotalPage(total_cnt, 3);
		int first_block = PageNumberingManager.getInstance().getFirstPageInBlock(Integer.parseInt(currentPage), 10);
		int last_block = PageNumberingManager.getInstance().getLastPageInBlock(Integer.parseInt(currentPage), 10);

		model.addAttribute("total_page", total_page);
		model.addAttribute("totalCnt", total_cnt);// ��ü �ۼ�
		model.addAttribute("current_page", currentPage);
		model.addAttribute("boardList", boardService.selectSavingByBank(Integer.parseInt(currentPage), 3 ,kor_co_nm));
		model.addAttribute("last_page", Integer.parseInt(currentPage) + 9);
		model.addAttribute("all_bank", boardService.selectAllBank());
		model.addAttribute("bank_text", kor_co_nm);
		return "/board_product/saving/savingList";
	}
	@RequestMapping(value="/savingSearch.do", method=RequestMethod.GET)
	public String savingSearch(
			@RequestParam("current_page") String currentPage,
			@RequestParam("searchStr") String searchStr,
			Model model) {
		logger.info("savingSearch called");
		logger.info("bank: "+searchStr);
		int total_cnt = new Integer(boardService.nSavingSearched(searchStr));

		int total_page = PageNumberingManager.getInstance().getTotalPage(total_cnt, 3);
		int first_block = PageNumberingManager.getInstance().getFirstPageInBlock(Integer.parseInt(currentPage), 10);
		int last_block = PageNumberingManager.getInstance().getLastPageInBlock(Integer.parseInt(currentPage), 10);

		model.addAttribute("total_page", total_page);
		model.addAttribute("totalCnt", total_cnt);// ��ü �ۼ�
		model.addAttribute("current_page", currentPage);
		model.addAttribute("boardList", boardService.selectSavingSearched(Integer.parseInt(currentPage), 3 ,searchStr));
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

		// ����ȸ�� ���� ������
		String year = savingBean.getFin_co_subm_day().substring(0, 4);
		String month = savingBean.getFin_co_subm_day().substring(4, 6);
		String day = savingBean.getFin_co_subm_day().substring(6, 8);
		String submitDay = (year + "-" + month + "-" + day).toString();
		logger.info(submitDay);
		
		//���Թ��
		String[] joinWay_part=savingBean.getJoin_way().split(",");
		
		StringBuilder joinWay = new StringBuilder( " " );
		for(int i = 0; i<joinWay_part.length;i++) {
			joinWay.append(joinWay_part[i]);
			if(i<joinWay_part.length-1) {
			joinWay.append("|");
			}
		}
		
		String join_deny="";
		//��������
		if(savingBean.getJoin_deny().equals("1")) {
			join_deny="���Ѿ���";
		}else if(savingBean.getJoin_deny().equals("2")) {
			join_deny="��������";
		}else {
			join_deny="�Ϻ�����";
		}
		
		//������ �ٹٲ� ó��
		savingBean.setMtrt_int(savingBean.getMtrt_int().replace("\n", "<br>"));
		
		//��Ÿ���� �ٹٲ� ó��
		savingBean.setEtc_note(savingBean.getEtc_note().replace("\n", "<br>"));
		
		//������� �ٹٲ� ó��
		savingBean.setSpcl_cnd(savingBean.getSpcl_cnd().replace("\n", "<br>"));
		//Ȩ������ �ּҰ�������
		String url=boardService.selectBankUrl(savingBean.getFin_co_no());
		model.addAttribute("savingBean", savingBean);
		model.addAttribute("submitDay", submitDay);
		model.addAttribute("joinWay",joinWay);
		model.addAttribute("join_deny", join_deny);
		model.addAttribute("url", url);

		return "/board_product/saving/savingContent";
	}

}
