package com.ourbank.app.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ourbank.app.bean.InvestBoard_Bean;
import com.ourbank.app.service.InvestBoard_Service;

@Controller
public class InvestBoard_Controller {
	@Autowired
	private InvestBoard_Service boardService;
	
	private static final Logger logger=LoggerFactory.getLogger(InvestBoard_Controller.class);
	
	//글쓰기폼
	@RequestMapping(value="/invest_show_write_form.do", method=RequestMethod.GET)
	public String showWriteForm( HttpServletRequest request,Model model) {
		logger.info("show_write_form called!!");
		
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
		model.addAttribute("boardBean", new InvestBoard_Bean());
		return "board_community/invest/investWriteForm";
		
	}
	//글쓰기
	@RequestMapping(value="/invest_write_form.do", method=RequestMethod.POST)
	public String DoinvestWriteBoard(@ModelAttribute("boardBean") @Valid InvestBoard_Bean boardBean,
			BindingResult bindingResult, HttpServletRequest request,
			Model model) {
		System.out.println(boardBean.toString());
		MultipartFile file=boardBean.getFile();
		
		//유효성 검사
		if(bindingResult.hasErrors()) {
			List<ObjectError> list=bindingResult.getAllErrors();
			for(ObjectError e:list) {
				logger.error("ObjectError"+e+"\n");
			}
			model.addAttribute("boardBean",boardBean);
			return "board_community/invest/investWriteForm";
		}
		
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
				FileOutputStream output=new FileOutputStream("C:\\mystudy\\myspring\\OurBank3_1\\src\\main\\webapp\\resources\\files\\"+fileName);
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
			boardBean.setInvest_case(boardService.InvestCase(ref));//항목 
			logger.info("case: " + boardService.InvestCase(ref));
			boardService.insertBoard(boardBean);
			System.out.println("답글작성");
		} 
		
		model.addAttribute("totalCnt", new Integer(boardService.getTotalCnt()));
		model.addAttribute("current_page", 1);
		model.addAttribute("boardList", boardService.getList(1, 10));
		return "redirect:investList.do";
	}
	
	//답글
	@RequestMapping(value = "/invest_show_rewrite_form.do", method = RequestMethod.GET)
	public String free_show_rewrite_from(
			@RequestParam("idx") String idx,
			@RequestParam("current_page") String current_page, 
			Model model) {
		logger.info("invest_how_rewrite_form called!!");
		
		//해당 idx_num에 대한 정보 
		InvestBoard_Bean boardBean= boardService.stairBoard(Integer.parseInt(idx));
		
		logger.info("idx :"+boardBean.getIdx());
		logger.info("ref :"+boardBean.getRef()); //그룹(원글의 글번호 참조)
		logger.info("step :"+boardBean.getStep()); //그룹내부순서
		logger.info("depth"+boardBean.getDepth()); //계층
		boardBean.setRe_idx(1); //답글
		logger.info("re_idx :"+boardBean.getRe_idx());
		logger.info("subject :"+boardBean.getSubject());
		
		
		//ref=0인경우 (답글그룹)
		if(boardBean.getRef()==0) {
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
		logger.info("invset :"+boardBean.getInvest_case());
		model.addAttribute("idx", idx);
		model.addAttribute("re_idx", boardBean.getRe_idx());
		model.addAttribute("step", boardBean.getStep());
		model.addAttribute("depth", boardBean.getDepth());
		model.addAttribute("invest_case", boardBean.getInvest_case());
		model.addAttribute("boardBean", new InvestBoard_Bean());
		
		return "board_community/invest/investWriteForm";
	} 
	
	//리스트 뿌리기
	@RequestMapping(value="/investList.do", method=RequestMethod.GET)
	public String investList(HttpServletRequest request,
			@RequestParam("current_page") String pageForView, Model model
			) {
		logger.info("investList called !!");
		HttpSession session=request.getSession();
		String uid=(String)session.getAttribute("uid");
		logger.info(uid);
		model.addAttribute("uid", uid);
		model.addAttribute("totalCnt", new Integer(boardService.getTotalCnt()));//전체 글수
		model.addAttribute("current_page",pageForView);
		model.addAttribute("boardList", boardService.getList(Integer.parseInt(pageForView), 10)); //리스트뿌릴 ArrayList 받아와서 저장
		return "board_community/invest/investListSpecificPage";
	}
	//성공사례 리스트 뿌리기
	@RequestMapping(value="/investSuccessList.do", method=RequestMethod.GET)
	public String investSignUpList(
			@RequestParam("current_page") String pageForView, Model model
			) {
		logger.info("invest Success List called !!");
		model.addAttribute("totalCnt", new Integer(boardService.getSuccessCnt()));
		model.addAttribute("current_page",pageForView);
		model.addAttribute("boardList", boardService.getSuccessList(Integer.parseInt(pageForView), 10)); 
		return "board_community/invest/investListSpecificPage";
	}
	//실패사례 리스트 뿌리기
	@RequestMapping(value="/investFailList.do", method=RequestMethod.GET)
	public String investSavingList(
			@RequestParam("current_page") String pageForView, Model model
			) {
		logger.info("invest Fail List called !!");
		model.addAttribute("totalCnt", new Integer(boardService.getFailCnt()));
		model.addAttribute("current_page",pageForView);
		model.addAttribute("boardList", boardService.getFailList(Integer.parseInt(pageForView), 10)); 
		return "board_community/invest/investListSpecificPage";
	}
	//기타 리스트 뿌리기
	@RequestMapping(value="/investEtcList.do", method=RequestMethod.GET)
	public String investEtcList(
			@RequestParam("current_page") String pageForView, Model model
			) {
		logger.info("invest etc List called !!");
		model.addAttribute("totalCnt", new Integer(boardService.getEtcCnt()));//회원가입 글수
		model.addAttribute("current_page",pageForView);
		model.addAttribute("boardList", boardService.getEtcList(Integer.parseInt(pageForView), 10)); //리스트뿌릴 ArrayList 받아와서 저장
		return "board_community/invest/investListSpecificPage";
	}

	//글보기
	@RequestMapping(value="/investView.do", method=RequestMethod.GET)
	public String viewWork(@RequestParam("idx") int idx,
						   @RequestParam("current_page") String current_page,
						   @RequestParam("searchStr") String searchStr,
						   Model model) {
		logger.info("viewWork called");
		logger.info("idx=["+idx+"] current_page=["+current_page+"] "
				+ "searchStr=["+searchStr+"]");
		InvestBoard_Bean boardData=boardService.getSpecificRow(idx);
		logger.info(boardData.getContent());
		boardService.updateHits(boardData.getHits(), boardData.getIdx());
		model.addAttribute("hits", boardData.getHits());
		logger.info(boardData.getCategory());
		
		logger.info("dept: "+boardData.getDepth());
		logger.info("invest: "+boardData.getInvest_case());
		model.addAttribute("idx", idx);
		model.addAttribute("current_page", current_page);
		model.addAttribute("searchStr", searchStr);
		model.addAttribute("boardData", boardService.getSpecificRow(idx));
		model.addAttribute("filename", boardData.getFilename());
		return "board_community/invest/investViewMemo";
	}
	
	//다운로드
	@RequestMapping(value = "/invest_download.do", 
	         method=RequestMethod.GET)
	   @ResponseBody
	   public byte[] downProcess(HttpServletResponse response, @RequestParam String filename) throws IOException{
	      File file = new File("C:\\eclipse_ourBank\\OurBank\\src\\main\\resources\\files\\" + filename);
	      byte[] bytes = FileCopyUtils.copyToByteArray(file);
	      String fn = new String(file.getName().getBytes(),"iso_8859_1");
	      
	      response.setHeader("Content-Disposition",
	                "attachment;filename=\"" + fn + "\"");
	        response.setContentLength(bytes.length);
	        return bytes;
	   }
	//글수정폼
	@RequestMapping(value="invest_show_update_form.do",method=RequestMethod.GET)
	public String showUpdateForm(
			@RequestParam("idx") int idx,
			@RequestParam("current_page") String current_page,
			Model model
			) {
		
		logger.info("update form!!");
		logger.info(idx+"");
		model.addAttribute("idx", idx);
		model.addAttribute("current_page", current_page);
		model.addAttribute("boardData", boardService.getSpecificRow(idx));
		
		return "board_community/invest/investViewMemoForUpdate";
	}
	//글수정
	@RequestMapping(value="/invest_update.do", method=RequestMethod.POST)
	public String investUpdate(
			@ModelAttribute("boardBean") @Valid InvestBoard_Bean boardBean,
			BindingResult bindingResult,
			@RequestParam("idx") int idx,
			@RequestParam("current_page") String current_page,
			Model model) {
		System.out.println(boardBean.toString());
		MultipartFile file=boardBean.getFile();
		
		//유효성 검사
		if(bindingResult.hasErrors()) {
			List<ObjectError> list=bindingResult.getAllErrors();
			for(ObjectError e:list) {
				logger.error("ObjectError"+e+"\n");
			}
			model.addAttribute("boardBean",boardBean);
			return "board_community/invest/investWriteForm";
		}
		
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
				FileOutputStream output=new FileOutputStream("C:\\eclipse_ourBank\\OurBank\\src\\main\\resources\\files\\"+fileName);
				output.write(fileData);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		//세션에서 얻어와야함
		String id="admin";
		boardBean.setId(id);
		logger.info(boardBean.getCategory()+" "+
					boardBean.getId()+" "+
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
		model.addAttribute("invest_case", boardBean.getInvest_case());
		
		return "board_community/invest/investViewMemo";
	}
	
	//글 삭제
	@RequestMapping(value="/investDeleteSpecificRow.do", method=RequestMethod.GET)
	public String deleteSpecificRow(@RequestParam("idx") int idx,
									@RequestParam("current_page") int current_page,
									Model model) {
		logger.info("DeleteSpecificRow called!!");
		logger.info("memo_id=["+idx+"]  current_page=["+current_page+"]");
		boardService.deleteRow(idx);
		//다시 페이지를 조회한다.
		model.addAttribute("totalCnt", new Integer(boardService.getTotalCnt()));
		model.addAttribute("current_page", current_page);
		model.addAttribute("boardList",boardService.getList(current_page, 10));
		
		return "redirect:investList.do";
	}
	
	//글검색
	@RequestMapping(value="/investSearch.do", method=RequestMethod.POST)
	public String searchWithSubject (@RequestParam("searchStr") String searchStr, 
									Model model) {
		
		
		return searchedList(1, searchStr,model);
	}

	//검색한 페이지로 이동
	@RequestMapping(value="/investSearchedList.do",method = RequestMethod.GET)
	public String searchedList(
			@RequestParam("pageForView") int pageForView,
			@RequestParam("searchStr") String searchStr,
			Model model
			) {
		logger.info("listSearchedSpecificPageWork called");
		logger.info("pageForView=["+pageForView+"]");
		logger.info("searchStr=["+searchStr+"]");
		
		model.addAttribute("totalCnt", new Integer(boardService.getTotalCntBySubject(searchStr)));
		model.addAttribute("searchedList", boardService.getSearchedList(pageForView, 10, searchStr));
		model.addAttribute("pageForView", pageForView);
		model.addAttribute("searchStr", searchStr);

		return "board_community/invest/investListSearchedPage";
	}

}
