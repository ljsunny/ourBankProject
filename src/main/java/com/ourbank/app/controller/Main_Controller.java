package com.ourbank.app.controller;

import java.io.File;
import java.io.IOException;

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

import com.ourbank.app.bean.FreeBoard_Bean;
import com.ourbank.app.bean.NewnoticeBoard_Bean;
import com.ourbank.app.mapper.MainPage_Mapper;
import com.ourbank.app.service.DepositProduct_Service;
import com.ourbank.app.service.MainPage_Service;

@Controller
public class Main_Controller {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(FreeBoard_Controller.class); 

	@Autowired
	private MainPage_Service boardService;
	
	@RequestMapping(value="/index.do", method = RequestMethod.GET)
	public String showIndex(Model model) {
		
		model.addAttribute("newNoticeBean", boardService.selectNewNotice());
		model.addAttribute("bestBean", boardService.selectBest());
		return "main";
	}
	
	
	/////////////////////////////////////
	//검색
	//글검색
	@RequestMapping(value="/indexSearch.do", method=RequestMethod.POST)
	public String freesearch (@RequestParam("searchStr") String searchStr, 
			Model model) {
		logger.info("indexSearch called");
		
		
		return searchedList(1, searchStr,model);
	}
		
	//검색한 페이지로 이동
	@RequestMapping(value="/fullboardSearchList.do",method = RequestMethod.GET)
	public String searchedList(
			@RequestParam("pageForView") int pageForView,
			@RequestParam("searchStr") String searchStr,
			Model model
			) {
		logger.info("freeSearchedList called");
		logger.info("pageForView=["+pageForView+"]");
		logger.info("searchStr=["+searchStr+"]");
		
		model.addAttribute("totalCnt", new Integer(boardService.getTotalCntBySubject(searchStr)));
		logger.info("totlaCnt: "+new Integer(boardService.getTotalCntBySubject(searchStr)));
		model.addAttribute("searchedList", boardService.getSearchedList(pageForView, 10, searchStr));
		model.addAttribute("pageForView", pageForView);
		model.addAttribute("searchStr", searchStr);

		return "main/fullboardSearchList";
	}	
	
	//내가작성한 글 - 글보기
	@RequestMapping(value = "/fullboardSearchView.do", method = RequestMethod.GET)
	public String myBoardView(@RequestParam("board_idx") int board_idx,
							  @RequestParam("current_page") String current_page,
							  @RequestParam("searchStr") String searchStr,
						      Model model) {
		logger.info("fullboardSearchView called");
		
		FreeBoard_Bean boardData = boardService.getSpecificRow(board_idx);
		logger.info(boardData.getContent());
		logger.info("hits: "+boardData.getHits());
		
		//조회수 증가
		switch (boardData.getCategory_num()) {
		case 1: 
			boardService.updateReviewHits(boardData.getHits(),boardData.getBoard_idx());
			boardData.setHits(boardService.getSpecificRow(board_idx).getHits());
			model.addAttribute("categoryStr", "[리뷰게시판]");
			break;
		case 2:
			boardService.updateFreeHits(boardData.getHits(),boardData.getBoard_idx());
			boardData.setHits(boardService.getSpecificRow(board_idx).getHits());
			model.addAttribute("categoryStr", "[자유게시판]");
			break;
		case 3:
			boardService.updateMeetingHits(boardData.getHits(),boardData.getBoard_idx());
			boardData.setHits(boardService.getSpecificRow(board_idx).getHits());
			model.addAttribute("categoryStr", "[모임게시판]");
			break;
		case 4:
			boardService.updateDebateHits(boardData.getHits(),boardData.getBoard_idx());
			boardData.setHits(boardService.getSpecificRow(board_idx).getHits());
			model.addAttribute("categoryStr", "[토론게시판]");
			break;
		case 5:
			boardService.updateInvestHits(boardData.getHits(),boardData.getBoard_idx());
			boardData.setHits(boardService.getSpecificRow(board_idx).getHits());
			model.addAttribute("categoryStr", "[재테크노하우 게시판]");
			break;
		case 6:
			boardService.updateQnaHits(boardData.getHits(),boardData.getBoard_idx());
			boardData.setHits(boardService.getSpecificRow(board_idx).getHits());
			model.addAttribute("categoryStr", "[QnA게시판]");
			break;
		case 7:
			boardService.updateFaqHits(boardData.getHits(),boardData.getBoard_idx());
			boardData.setHits(boardService.getSpecificRow(board_idx).getHits());
			model.addAttribute("categoryStr", "[FAQ게시판]");
			break;
		case 8:
			boardService.updateNewsHits(boardData.getHits(),boardData.getBoard_idx());
			boardData.setHits(boardService.getSpecificRow(board_idx).getHits());
			model.addAttribute("categoryStr", "[관련뉴스]");
			break;
		case 9:
			boardService.updateNewnoticeHits(boardData.getHits(),boardData.getBoard_idx());
			boardData.setHits(boardService.getSpecificRow(board_idx).getHits());
			model.addAttribute("categoryStr", "[공지사항]");
			break;
			
		default:
			break;
		}					
		
		model.addAttribute("board_idx", board_idx);
		model.addAttribute("current_page", current_page);
		model.addAttribute("searchStr", searchStr);
		model.addAttribute("boardData", boardData);
		model.addAttribute("filename", boardData.getFilename());
		
		return "main/fullboardSearchView";
	}
	
	//파일 다운로드
	@RequestMapping(value = "/fullboard_download.do", method=RequestMethod.GET)
	@ResponseBody
	public byte[] free_downProcess(HttpServletResponse response, @RequestParam String filename) 
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
