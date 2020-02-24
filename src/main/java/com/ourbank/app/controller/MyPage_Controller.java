package com.ourbank.app.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ourbank.app.bean.FreeBoard_Bean;
import com.ourbank.app.bean.UserBoard_Bean;
import com.ourbank.app.service.MyPage_Service;

@Controller
public class MyPage_Controller {
@Autowired
private MyPage_Service boardService;

private static final Logger logger=LoggerFactory.getLogger( MyPage_Controller.class);

@RequestMapping(value="myPage.do", method=RequestMethod.GET)
public String myPage(
		HttpServletRequest request,
		HttpServletResponse response,
		Model model)  {
	HttpSession session= request.getSession();
	String uid=(String)session.getAttribute("uid");
	logger.info(uid);
	if(uid==null) {
		PrintWriter out;
		try {
			//자바에서 알림창 띄우기
			out = response.getWriter();
			out.println("<script>alert('권한이 없습니다.'); location.href='loginForm.do';</script>"); 
	        out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
		return "writeboard";
	
	}else if(uid.equals("admin")) {
		return "board_Adminpage/mypage";
	}
	
	return "/board_Mypage/mypage";
}

@RequestMapping(value = "/myInfo.do", method = RequestMethod.GET)
public String myInfo(
		HttpServletRequest request,
		Model model) {
	HttpSession session=request.getSession();
	String uid=(String)session.getAttribute("uid");
	
	UserBoard_Bean userBean= boardService.getUserInfo(uid);
	
	model.addAttribute("uid", uid );
	model.addAttribute("userBean", userBean);
	
	return "/board_Mypage/myInfo";
}

@RequestMapping(value = "/myInfoUpdateForm.do", method = RequestMethod.GET)
public String myInfoUpdate(
		@RequestParam("id") String id,
		Model model) {
	logger.info("myInfoUpdateForm called");
	UserBoard_Bean userBean= boardService.getUserInfo(id);
	model.addAttribute("id", id);
	model.addAttribute("userBean", userBean);
	return "/board_Mypage/myInfoUpdate";
}

@RequestMapping(value = "/myInfoUpdate.do", method = RequestMethod.POST)
public String myInfoUpdate(
		@ModelAttribute("userBean") UserBoard_Bean userBean,
		Model model) {
	
	boardService.updateUserInfo(userBean);
	model.addAttribute("id", userBean.getId());
	return "redirect:myInfo.do";
}

@RequestMapping(value="/deleteId.do", method=RequestMethod.GET)
public String deleteId(
	HttpServletRequest request,	
	@RequestParam("id") String id,
	Model model) {
	
	boardService.deleteId(id);
	HttpSession session=request.getSession();
	session.invalidate();
	return "writeboard";
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//내가 작성한글 -리스트 
	@RequestMapping(value = "/myBoardList.do", method = RequestMethod.GET)
	public String myBoardList(HttpServletRequest request,
							@RequestParam("current_page") String pageForView, 
							Model model) {
		
		HttpSession session=request.getSession();
		String uid=(String)session.getAttribute("uid");
			
		logger.info("best_listSpecificPageWork called");
		logger.info("current_page=["+pageForView+"]");
		
		
		model.addAttribute("totalCnt", new Integer(boardService.getTotalCnt(uid)));
		logger.info("totalCnt: " + new Integer(boardService.getTotalCnt(uid)));
		model.addAttribute("current_page", pageForView);
		logger.info("pageForView: "+pageForView);
		model.addAttribute("boardList", boardService.getUserBoardList(uid, Integer.parseInt(pageForView),10));	
		model.addAttribute("id",uid);
		
		return "/board_Mypage/myBoardList";
	
	}
	//내가작성한 글 - 글보기
	@RequestMapping(value = "/myBoardView.do", method = RequestMethod.GET)
	public String myBoardView(@RequestParam("board_idx") int board_idx,
							  @RequestParam("current_page") String current_page,
						      Model model) {
		logger.info("myBoardView called");
		
		FreeBoard_Bean boardData = boardService.getSpecificRow(board_idx);
		logger.info(boardData.getContent());
		logger.info("hits: "+boardData.getHits());
		
		//조회수 증가
		switch (boardData.getCategory_num()) {
		case 1: 
			boardService.updateReviewHits(boardData.getHits(),boardData.getBoard_idx());
			boardData.setHits(boardService.getSpecificRow(board_idx).getHits());
			break;
		case 2:
			boardService.updateFreeHits(boardData.getHits(),boardData.getBoard_idx());
			boardData.setHits(boardService.getSpecificRow(board_idx).getHits());
			break;
		case 3:
			boardService.updateMeetingHits(boardData.getHits(),boardData.getBoard_idx());
			boardData.setHits(boardService.getSpecificRow(board_idx).getHits());
			break;
		case 4:
			boardService.updateDebateHits(boardData.getHits(),boardData.getBoard_idx());
			boardData.setHits(boardService.getSpecificRow(board_idx).getHits());
			break;
		case 5:
			boardService.updateInvestHits(boardData.getHits(),boardData.getBoard_idx());
			boardData.setHits(boardService.getSpecificRow(board_idx).getHits());
			break;
		case 6:
			boardService.updateQnaHits(boardData.getHits(),boardData.getBoard_idx());
			boardData.setHits(boardService.getSpecificRow(board_idx).getHits());
			break;
			
		default:
			break;
		}		
				
		
		model.addAttribute("board_idx", board_idx);
		model.addAttribute("current_page", current_page);
		model.addAttribute("boardData", boardData);
		model.addAttribute("filename", boardData.getFilename());
		
		return "/board_Mypage/myBoardView";
	}

	//파일 다운로드
	@RequestMapping(value = "/my_download.do", method=RequestMethod.GET)
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
