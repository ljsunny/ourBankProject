package com.ourbank.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.LineListener;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.expr.NewArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ourbank.app.service.DebateBoard_Service;
import com.ourbank.app.bean.DebateBoard_Bean;
import com.ourbank.app.bean.FreeBoard_Bean;

@Controller
public class DebateBoard_Controller {

	@Autowired
	DebateBoard_Service boardService;
	
	private static final Logger logger = 
			LoggerFactory.getLogger(DebateBoard_Controller.class);
	
	//글쓰기 폼으로
	@RequestMapping(value = "/debate_show_write_fome.do", method = RequestMethod.GET)
	public String debate_show_write_form( HttpServletRequest request,DebateBoard_Bean boardBean, Model model) {
		logger.info("debate_write_form called");
		
		int ref=0;  //그룹(원글의 글번호 참조)
		int step=0;  //그룹내 순서
		int depth=0; //계층
		int re_idx=0;
		HttpSession session = request.getSession();
		String uid = (String)session.getAttribute("id");
		
		logger.info("ref:"+ref+" step: "+step+"depth: "+depth+" " + "id:"+uid);
		
		model.addAttribute("uid", uid);
		model.addAttribute("re_idx", re_idx);
		model.addAttribute("step", step);
		model.addAttribute("ref", ref);
		model.addAttribute("depth", depth);
		model.addAttribute("boardBean", new FreeBoard_Bean());
		return "board_community/debate/debateWriteForm";
	}
	
	//글쓰기처리 + 유효성검사
	@RequestMapping(value="/debate_write_form.do" , method = RequestMethod.POST)
	public String DodebateWriteBoard(@ModelAttribute("boardBean") DebateBoard_Bean boardBean, HttpServletRequest request,
			 Model model) {
		System.out.println("-----------------------------------------");
		logger.info("free_DoWriteBoard.do called");
		
		MultipartFile file=boardBean.getFile();

		// 자료실에 file 올리기
		if(file!=null) {
			String fileName=file.getOriginalFilename();
			long fileSize= file.getSize();
			boardBean.setFilename(fileName);
			boardBean.setFilesize(fileSize);
			logger.info(boardBean.getFilename());
			logger.info(boardBean.getFilesize()+"");
			
			try {
				byte[] fileData=file.getBytes();
				FileOutputStream output=
						new FileOutputStream("C:\\Users\\user\\Desktop\\OurBank\\src\\main\\webapp\\resources\\files\\"+fileName);
			
				output.write(fileData);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("uid");
		boardBean.setId(id);
		logger.info(boardBean.getId());

		
		//본글인 경우
		if(boardBean.getRe_idx()==0) {
		boardService.insertBoard(boardBean);
		int recent_id=boardService.recentID(); //가장최근값가져옴
		logger.info("reid :"+recent_id);
		boardService.updateRewrite(recent_id);
		System.out.println("본글작성");
	}
	else {//답글인 경우
		int ref=boardBean.getRef();
		System.out.println("ref"+ref);
		boardBean.setRef(ref); //원글의 글번호로 묶음
		boardService.updateGroupStep(ref, boardBean.getStep());
		boardBean.setStep(boardBean.getStep()+1);
		boardBean.setDepth(boardBean.getDepth()+1);
		boardBean.setRe_idx(0);
		boardService.insertBoard(boardBean);
		System.out.println("답글작성");
	} 
		model.addAttribute("subject", boardBean.getSubject());
		model.addAttribute("totalCnt", new Integer(boardService.getTotalCnt()));
		model.addAttribute("current_page", "1");
		model.addAttribute("boardList",boardService.getList(1, 10));
	    System.out.println("ok");
		 
		return "redirect:debateList.do"; 
	}
	
	//답글
	@RequestMapping(value = "/debate_show_rewrite_form.do", method = RequestMethod.GET)
	public String debate_show_rewrite_from(
			@RequestParam("idx_num") String idx_num,
			@RequestParam("current_page") String current_page, 
			Model model) {
		logger.info("debate_how_rewrite_form called!!");
		
		DebateBoard_Bean boardBean=boardService.stairBoard(Integer.parseInt(idx_num));
		
		logger.info("idx_num :"+boardBean.getIdx_num());
		logger.info("ref :"+boardBean.getRef());
		logger.info("step :"+boardBean.getStep());
		logger.info("depth"+boardBean.getDepth());
		boardBean.setRe_idx(1); //답글
		logger.info("re_idx :"+boardBean.getRe_idx());
		logger.info("subject :"+boardBean.getSubject());
	
		//ref=0인경우 (답글그룹)
		if(boardBean.getDepth()==0) {
			model.addAttribute("ref", boardBean.getIdx_num());
			String re_subject = "Re:"+boardBean.getSubject()+"_답변";
			logger.info("subject :"+re_subject);
			model.addAttribute("subject", re_subject);
		}
		else {
			model.addAttribute("ref", boardBean.getRef());
			String re_subject = "Re:"+boardBean.getSubject();
			logger.info("subject :"+re_subject);
			model.addAttribute("subject", re_subject);
		}
		
		model.addAttribute("idx_num", idx_num);
		model.addAttribute("re_idx", boardBean.getRe_idx());
		model.addAttribute("step", boardBean.getStep());
		model.addAttribute("depth", boardBean.getDepth());
		model.addAttribute("boardBean", new FreeBoard_Bean());
		
		return "board_community/debate/debateWriteForm";
	} 

	//리스트로
	@RequestMapping(value = "/debateList.do", method=RequestMethod.GET)
	public String debate_listSpecificPageWork(HttpServletRequest request,
			@RequestParam("current_page") String pageForView, Model model) {
		System.out.println("-------------------------------");
		logger.info("debateList called");
		logger.info("current_page=["+pageForView+"]");
		HttpSession session=request.getSession();
		String uid=(String)session.getAttribute("uid");
		logger.info(uid);
		model.addAttribute("uid", uid);
		model.addAttribute("totalCnt", new Integer(boardService.getTotalCnt()));
		model.addAttribute("current_page", pageForView);
		model.addAttribute("boardList", boardService.getList(Integer.parseInt(pageForView),10));
		System.out.println("-------------------------------");
		
		return "board_community/debate/debateListSpecificPage";
	}
	
	//글보기
	@RequestMapping(value="/debateView.do", method=RequestMethod.GET)
	public String debate_viewWork(@RequestParam("idx_num") int idx_num,
						   @RequestParam("current_page") String current_page,
						   @RequestParam("searchStr") String searchStr,
						   Model model) {
		logger.info("debateView called");
		logger.info("idx_num=["+idx_num+"] current_page=["+current_page+"] "
		+ "searchStr=["+searchStr+"]");
			
		DebateBoard_Bean boardData=boardService.getSpecificRow(idx_num);
		logger.info(boardData.getContent());
		logger.info("hits: "+boardData.getHits());
		//조회수 증가
		boardService.updateHits(boardData.getHits(),boardData.getIdx_num());
		boardData.setHits(boardService.getSpecificRow(idx_num).getHits());;		
			
		model.addAttribute("idx_num", idx_num);
		model.addAttribute("current_page", current_page);
		model.addAttribute("searchStr", searchStr);
		model.addAttribute("boardData", boardData);
		model.addAttribute("filename", boardData.getFilename());
		System.out.println("zzzzzz");
		logger.info(boardData.getFilename());
		
			
		return "board_community/debate/debateViewMemo";
	}
	
	//파일 다운로드
	@RequestMapping(value = "/debate_download.do", method=RequestMethod.GET)
	@ResponseBody
	public byte[] debate_downProcess(HttpServletResponse response, @RequestParam String filename) 
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
	
	//글수정 페이지
	@RequestMapping(value="debate_show_update_form.do",method=RequestMethod.GET)
	public String debate_showUpdateForm(@RequestParam("idx_num") int idx_num,
								 @RequestParam("current_page") String current_page,
								 Model model) {
		logger.info("debate_show_update_form called");
		logger.info("idx_num="+idx_num);
		model.addAttribute("idx_num", idx_num);
		model.addAttribute("current_page", current_page);
		model.addAttribute("boardData", boardService.getSpecificRow(idx_num));
		
		return "board_community/debate/debateViewMemoForUpdate";
	}
	//글수정 처리
	@RequestMapping(value="/debate_update.do", method=RequestMethod.POST)
	public String debate_Update(
			@ModelAttribute("boardBean") DebateBoard_Bean boardBean,
			@RequestParam("idx_num") int idx_num,
			@RequestParam("current_page") String current_page,
			Model model) {
		System.out.println(boardBean.toString());
		MultipartFile file=boardBean.getFile();
		
		//파일 처리
		if(file!=null) {
			String fileName=file.getOriginalFilename();
			long fileSize=file.getSize();
			boardBean.setFilename(fileName);
			boardBean.setFilesize(fileSize);
			logger.info(boardBean.getFilename());
			logger.info(boardBean.getFilesize()+"");
			
			try {
				byte[] fileData=file.getBytes();
				FileOutputStream output=new FileOutputStream("C:\\Users\\user\\Desktop\\OurBank\\src\\main\\webapp\\resources\\files\\"+fileName);
				output.write(fileData);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		//세션에서 얻어와야함
		String id="exId";
		boardBean.setId(id);
		logger.info(boardBean.getId()+" "+
					boardBean.getContent()+" "+
					boardBean.getSubject());
		
		boardBean.setIdx_num(idx_num);
		
		boardService.updateBoard(boardBean);
		boardBean=boardService.getSpecificRow(idx_num);
		
		model.addAttribute("idx_num", idx_num);
		model.addAttribute("current_page", current_page);
		model.addAttribute("searchStr", "None");
		model.addAttribute("boardData", boardService.getSpecificRow(idx_num));
		model.addAttribute("filename", boardBean.getFilename());
		
		return "board_community/debate/debateViewMemo";
	}
	
	//글 삭제
	@RequestMapping(value="/debate_DeleteSpecificRow.do", method=RequestMethod.GET)
	public String deleteSpecificRow(@RequestParam("idx_num") int idx_num,
									@RequestParam("current_page") int current_page,
									Model model) {
		logger.info("debate_DeleteSpecificRow called!!");
		logger.info("idx_num=["+idx_num+"]  current_page=["+current_page+"]");
		boardService.deleteRow(idx_num);
		//다시 페이지를 조회한다.
		model.addAttribute("totalCnt", new Integer(boardService.getTotalCnt()));
		model.addAttribute("current_page", current_page);
		model.addAttribute("boardList",boardService.getList(current_page, 10));
		
		return "redirect:debateList.do";
	}
	
	//글검색
	@RequestMapping(value="/debateSearch.do", method=RequestMethod.POST)
	public String searchWithSubject (@RequestParam("searchStr") String searchStr, 
									Model model) {
		
		logger.info("글검색");
		return searchedList(1, searchStr,model);
	}

	//검색한 페이지로 이동
	@RequestMapping(value="/debateSearchedList.do",method = RequestMethod.GET)
	public String searchedList(
			@RequestParam("pageForView") int pageForView,
			@RequestParam("searchStr") String searchStr,
			Model model
			) {
		logger.info("debateSearchedList called");
		logger.info("pageForView=["+pageForView+"]");
		logger.info("searchStr=["+searchStr+"]");
		
		model.addAttribute("totalCnt", new Integer(boardService.getTotalCntBySubject(searchStr)));
		model.addAttribute("searchedList", boardService.getSearchedList(pageForView, 10, searchStr));
		model.addAttribute("pageForView", pageForView);
		model.addAttribute("searchStr", searchStr);

		return "board_community/debate/debateListSearchedPage";
	}
}
