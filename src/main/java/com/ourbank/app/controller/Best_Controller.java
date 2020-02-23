package com.ourbank.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ourbank.app.bean.BestBoard_Bean;
import com.ourbank.app.service.BestBoard_Service;

@Controller
public class Best_Controller {
	
	@Autowired
	BestBoard_Service boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(Best_Controller.class);
	
	//Best리스트 이동
	@RequestMapping(value = "/bestList.do", method=RequestMethod.GET)
	public String free_listSpecificPageWork(
			@RequestParam("current_page") String pageForView, 
			Model model) {
		System.out.println("-------------------------------");
		logger.info("best_listSpecificPageWork called");
		logger.info("current_page=["+pageForView+"]");
		model.addAttribute("current_page", pageForView);
		model.addAttribute("boardList", boardService.getList(Integer.parseInt(pageForView),10));
		System.out.println("-------------------------------");
		
		return "board_community/best/bestListSpecificPage";
	}
	
	//글보기
	@RequestMapping(value="/bestView.do", method=RequestMethod.GET)
	public String best_viewWork(@RequestParam("best_idx") int best_idx,
							@RequestParam("category_num") int category_num,
						    @RequestParam("current_page") String current_page,
						    Model model) {
		logger.info("best_viewWork called");
		logger.info("best_idx=["+best_idx+"] current_page=["+current_page+"] ");
		logger.info("category_num=["+category_num+"]");
			
		BestBoard_Bean boardData=boardService.getSpecificRow(best_idx);
		logger.info(boardData.getContent());
		logger.info("category="+boardData.getCategory());
		logger.info("hits= "+boardData.getHits());

		//조회수 증가
		switch (boardData.getCategory_num()) {
		case 1: 
			boardService.updateReviewHits(boardData.getHits(),boardData.getBest_idx());
			boardData.setHits(boardService.getSpecificRow(best_idx).getHits());
			break;
		case 2:
			boardService.updateMeetingHits(boardData.getHits(),boardData.getBest_idx());
			boardData.setHits(boardService.getSpecificRow(best_idx).getHits());
			break;
		case 3:
			boardService.updateDebateHits(boardData.getHits(),boardData.getBest_idx());
			boardData.setHits(boardService.getSpecificRow(best_idx).getHits());
			break;
		case 4:
			boardService.updateDebateHits(boardData.getHits(),boardData.getBest_idx());
			boardData.setHits(boardService.getSpecificRow(best_idx).getHits());
			break;
		case 5:
			boardService.updateInvestHits(boardData.getHits(),boardData.getBest_idx());
			boardData.setHits(boardService.getSpecificRow(best_idx).getHits());
			break;
			
		default:
			break;
		}		
			
		model.addAttribute("best_idx", best_idx);
		model.addAttribute("current_page", current_page);
		model.addAttribute("boardData", boardData);
		model.addAttribute("filename", boardData.getFilename());
		logger.info(boardData.getFilename());
		
			
		return "board_community/best/bestViewMemo";
	}
	
	//파일 다운로드
	@RequestMapping(value = "/best_download.do", method=RequestMethod.GET)
	@ResponseBody
	public byte[] best_downProcess(HttpServletResponse response, @RequestParam String filename) 
			   throws IOException{
		System.out.println("다운로드");
		String fn2=new String(filename);
		System.out.println(fn2);
	    File file = new File("C:\\Users\\user\\Desktop\\OurBank\\src\\main\\webapp\\resources\\files\\" + filename);
	    byte[] bytes = FileCopyUtils.copyToByteArray(file);
	    String fn = new String(file.getName().getBytes(),"iso_8859_1");
	      
	    response.setHeader("Content-Disposition", "attachment;filename=\"" + fn + "\"");
	    response.setContentLength(bytes.length);
	    return bytes;
	    }

}
