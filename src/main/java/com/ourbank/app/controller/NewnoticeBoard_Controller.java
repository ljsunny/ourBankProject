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

import javax.servlet.http.HttpServletResponse;
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

import com.ourbank.app.bean.NewnoticeBoard_Bean;
import com.ourbank.app.service.NewnoticeBoard_Service;


@Controller
public class NewnoticeBoard_Controller {
	
	@Autowired
	NewnoticeBoard_Service boardService;
	
	private static final Logger logger = 
			LoggerFactory.getLogger(NewnoticeBoard_Controller.class); 
	
	
	//글쓰기 폼으로
	@RequestMapping(value = "/newnotice_show_write_form.do", method = RequestMethod.GET)
	public String newnotice_show_write_form(NewnoticeBoard_Bean boardBean, Model model) {
		logger.info("newnotice_write_form called");
		
		int ref=0;  //그룹(원글의 글번호 참조)
		int step=0;  //그룹내 순서
		int depth=0; //계층
		int re_idx=0;
		logger.info("ref:"+ref+" step: "+step+"depth: "+depth+" ");

		//임시로 넣어둠
		String id = "admin";
		
		model.addAttribute("id", id);
		model.addAttribute("re_idx", re_idx);
		model.addAttribute("step", step);
		model.addAttribute("ref", ref);
		model.addAttribute("depth", depth);
		model.addAttribute("boardBean", new NewnoticeBoard_Bean());
		return "board_notice/newnotice/newnoticeWriteForm";
	}
	
	//글쓰기처리
	@RequestMapping(value="/newnotice_write_form.do" , method = RequestMethod.POST)
	public String DonewnoticeWriteBoard(@ModelAttribute("boardBean") NewnoticeBoard_Bean boardBean, 
			Model model) {
		System.out.println("-----------------------------------------");
		logger.info("newnotice_DoWriteBoard.do called");
		
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
		
		String id = "exId";
		boardBean.setId(id);
		logger.info(boardBean.getNewnotice_case()+" "+
				boardBean.getId()+" "+
				boardBean.getContent()+" "+
				boardBean.getRe_idx()+" "+
				boardBean.getSubject());

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
		return "redirect:newnoticeList.do"; 
	}
	
	//답글
	@RequestMapping(value = "/newnotice_show_rewrite_form.do", method = RequestMethod.GET)
	public String newnotice_show_rewrite_from(
			@RequestParam("idx") String idx,
			@RequestParam("current_page") String current_page, 
			Model model) {
		logger.info("newnotice_how_rewrite_form called!!");
		
		//해당 idx에 대한 정보 
		NewnoticeBoard_Bean boardBean=boardService.stairBoard(Integer.parseInt(idx));
		
		logger.info("idx :"+boardBean.getIdx());
		logger.info("ref :"+boardBean.getRef()); //그룹(원글의 글번호 참조)
		logger.info("step :"+boardBean.getStep()); //그룹내부순서
		logger.info("depth"+boardBean.getDepth()); //계층
		boardBean.setRe_idx(1); //답글
		logger.info("re_idx :"+boardBean.getRe_idx());
		logger.info("subject :"+boardBean.getSubject());
		
		
		//ref=0인경우 (답글그룹)
		if(boardBean.getDepth()==0) {
			model.addAttribute("ref", boardBean.getIdx());
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
		model.addAttribute("idx", idx);
		model.addAttribute("re_idx", boardBean.getRe_idx());
		model.addAttribute("step", boardBean.getStep());
		model.addAttribute("depth", boardBean.getDepth());
		model.addAttribute("boardBean", new NewnoticeBoard_Bean());
		
		return "board_notice/newnotice/newnoticeWriteForm";
	} 
	
	//전체리스트로
	@RequestMapping(value = "/newnoticeList.do", method=RequestMethod.GET)
	public String newnotice_listSpecificPageWork(
			@RequestParam("current_page") String pageForView, Model model) {
		System.out.println("-------------------------------");
		logger.info("newnoticeList called");
		logger.info("current_page=["+pageForView+"]");
		model.addAttribute("totalCnt", new Integer(boardService.getTotalCnt()));
		model.addAttribute("current_page", pageForView);
		model.addAttribute("boardList", boardService.getList(Integer.parseInt(pageForView),10));
		System.out.println("-------------------------------");
		
		return "board_notice/newnotice/newnoticeListSpecificPage";
	}
	//예금공지 리스트 뿌리기
		@RequestMapping(value="/newnoticeBankList.do", method=RequestMethod.GET)
		public String BankNoticeList(
				@RequestParam("current_page") String pageForView, Model model
				) {
			logger.info("newnotice signup List called !!");
			model.addAttribute("totalCnt", new Integer(boardService.getBankNoticeCnt()));
			model.addAttribute("current_page",pageForView);
			model.addAttribute("boardList", boardService.getBankNoticeList(Integer.parseInt(pageForView), 10)); 
			return "board_notice/newnotice/newnoticeListSpecificPage";
		}
		//적금공지 리스트 뿌리기
		@RequestMapping(value="/newnoticeProductList.do", method=RequestMethod.GET)
		public String ProductNoticeList(
				@RequestParam("current_page") String pageForView, Model model
				) {
			logger.info("newnotice saving List called !!");
			model.addAttribute("totalCnt", new Integer(boardService.getProductNoticeCnt()));
			model.addAttribute("current_page",pageForView);
			model.addAttribute("boardList", boardService.getProductNoticeList(Integer.parseInt(pageForView), 10)); //리스트뿌릴 ArrayList 받아와서 저장
			return "board_notice/newnotice/newnoticeListSpecificPage";
		}
		//기타 리스트 뿌리기
		@RequestMapping(value="/newnoticeEtcList.do", method=RequestMethod.GET)
		public String newnoticeEtcList(
				@RequestParam("current_page") String pageForView, Model model
				) {
			logger.info("newnotice etc List called !!");
			model.addAttribute("totalCnt", new Integer(boardService.getEtcCnt()));//회원가입 글수
			model.addAttribute("current_page",pageForView);
			model.addAttribute("boardList", boardService.getEtcList(Integer.parseInt(pageForView), 10)); //리스트뿌릴 ArrayList 받아와서 저장
			return "board_notice/newnotice/newnoticeListSpecificPage";
		}
	
	//글보기
	@RequestMapping(value="/newnoticeView.do", method=RequestMethod.GET)
	public String newnotice_viewWork(@RequestParam("idx") int idx,
						   @RequestParam("current_page") String current_page,
						   @RequestParam("searchStr") String searchStr,
						   Model model) {
		logger.info("newnoticeView called");
		logger.info("idx=["+idx+"] current_page=["+current_page+"] "
		+ "searchStr=["+searchStr+"]");
			
		NewnoticeBoard_Bean boardData=boardService.getSpecificRow(idx);
		logger.info(boardData.getContent());
		logger.info("hits: "+boardData.getHits());
		//조회수 증가
		boardService.updateHits(boardData.getHits(),boardData.getIdx());
		boardData.setHits(boardService.getSpecificRow(idx).getHits());		
			
		model.addAttribute("idx", idx);
		model.addAttribute("current_page", current_page);
		model.addAttribute("searchStr", searchStr);
		model.addAttribute("boardData", boardData);
		model.addAttribute("filename", boardData.getFilename());
		System.out.println("zzzzzz");
		logger.info(boardData.getFilename());
		
			
		return "board_notice/newnotice/newnoticeViewMemo";
	}
	
	//파일 다운로드
	@RequestMapping(value = "/newnotice_download.do", method=RequestMethod.GET)
	@ResponseBody
	public byte[] newnotice_downProcess(HttpServletResponse response, @RequestParam String filename) 
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
	@RequestMapping(value="newnotice_show_update_form.do",method=RequestMethod.GET)
	public String newnotice_showUpdateForm(@RequestParam("idx") int idx,
								 @RequestParam("current_page") String current_page,
								 Model model) {
		logger.info("newnotice_show_update_form called");
		logger.info("idx="+idx);
		model.addAttribute("idx", idx);
		model.addAttribute("current_page", current_page);
		model.addAttribute("boardData", boardService.getSpecificRow(idx));
		
		return "board_notice/newnotice/newnoticeViewMemoForUpdate";
	}
	//글수정 처리
	@RequestMapping(value="/newnotice_update.do", method=RequestMethod.POST)
	public String newnotice_Update(
			@ModelAttribute("boardBean")  NewnoticeBoard_Bean boardBean,
			@RequestParam("idx") int idx,
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
		
		boardBean.setIdx(idx);
		
		boardService.updateBoard(boardBean);
		boardBean=boardService.getSpecificRow(idx);
		
		model.addAttribute("idx", idx);
		model.addAttribute("current_page", current_page);
		model.addAttribute("searchStr", "None");
		model.addAttribute("boardData", boardService.getSpecificRow(idx));
		model.addAttribute("filename", boardBean.getFilename());
		
		return "board_notice/newnotice/newnoticeViewMemo";
	}
	
	//글 삭제
	@RequestMapping(value="/newnotice_DeleteSpecificRow.do", method=RequestMethod.GET)
	public String deleteSpecificRow(@RequestParam("idx") int idx,
									@RequestParam("current_page") int current_page,
									Model model) {
		logger.info("newnotice_DeleteSpecificRow called!!");
		logger.info("idx=["+idx+"]  current_page=["+current_page+"]");
		boardService.deleteRow(idx);
		//다시 페이지를 조회한다.
		model.addAttribute("totalCnt", new Integer(boardService.getTotalCnt()));
		model.addAttribute("current_page", current_page);
		model.addAttribute("boardList",boardService.getList(current_page, 10));
		
		return "redirect:newnoticeList.do";
	}
	
	//글검색
	@RequestMapping(value="/newnoticeSearch.do", method=RequestMethod.POST)
	public String newnoticesearch (@RequestParam("searchStr") String searchStr, 
									Model model) {
		logger.info("newnoticeSearch.do called");
		
		
		return searchedList(1, searchStr,model);
	}

	//검색한 페이지로 이동
	@RequestMapping(value="/newnoticeSearchedList.do",method = RequestMethod.GET)
	public String searchedList(
			@RequestParam("pageForView") int pageForView,
			@RequestParam("searchStr") String searchStr,
			Model model
			) {
		logger.info("newnoticeSearchedList called");
		logger.info("pageForView=["+pageForView+"]");
		logger.info("searchStr=["+searchStr+"]");
		
		model.addAttribute("totalCnt", new Integer(boardService.getTotalCntBySubject(searchStr)));
		model.addAttribute("searchedList", boardService.getSearchedList(pageForView, 10, searchStr));
		model.addAttribute("pageForView", pageForView);
		model.addAttribute("searchStr", searchStr);

		return "board_notice/newnotice/newnoticeListSearchedPage";
	}
	
	
	
		
}
