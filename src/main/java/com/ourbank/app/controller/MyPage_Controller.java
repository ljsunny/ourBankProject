package com.ourbank.app.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
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

import com.ourbank.app.bean.DepositBoard_Bean;
import com.ourbank.app.bean.FreeBoard_Bean;
import com.ourbank.app.bean.SavingBoard_Bean;
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
////////////////수정//////////////////////////////////////////////////////////////////
@RequestMapping(value = "/myInfo.do", method = RequestMethod.GET)
public String myInfo(
		HttpServletRequest request,
		HttpServletResponse response,
		Model model) {
	HttpSession session=request.getSession();
	String uid=(String)session.getAttribute("uid");
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
		UserBoard_Bean userBean= boardService.getUserInfo(uid);
		
		model.addAttribute("uid", uid );
		model.addAttribute("userBean", userBean);
		
		return "board_Adminpage/myInfo";
	}
	
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
	
	//아이디 삭제
	boardService.deleteId(id);
	//내 상품 데이터베이스에서 삭제하기
	boardService.deleteProductData(id);
	
	HttpSession session=request.getSession();
	session.invalidate();
	return "redirect:index.do";
}
/////////////////////////////////////////////////////////////////////////////////

//내가 작성한글 -리스트 
	@RequestMapping(value = "/myBoardList.do", method = RequestMethod.GET)
	public String myBoardList(HttpServletRequest request,
							HttpServletResponse response,
							@RequestParam("current_page") String pageForView, 
							Model model) {
		
		HttpSession session=request.getSession();
		String uid=(String)session.getAttribute("uid");
		
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
			model.addAttribute("totalCnt", new Integer(boardService.getTotalCnt(uid)));
			model.addAttribute("current_page", pageForView);
			model.addAttribute("boardList", boardService.getUserBoardList(uid, Integer.parseInt(pageForView),10));	
			model.addAttribute("id",uid);
			
			return "board_Adminpage/myBoardList";
		}
		
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

/////////////////////////////////////////////////////////////////////////////////////////////////
	//가입상품
	@RequestMapping(value="/myProductDetail.do", method =RequestMethod.GET )
	public String myProductDetail(@RequestParam("ck") int ck, Model model) {
		//예금상품
		if(ck==0) {
		//뿌릴거
		ArrayList<DepositBoard_Bean> depositBankBean=boardService.getDepositBank();
		//은행별로 담을 bean
		ArrayList<DepositBoard_Bean> depositProductBean=boardService.getDepositProduct();
		model.addAttribute("bank_bean", depositBankBean);
		model.addAttribute("product_bean", depositProductBean);
		//담을 empty 빈
		model.addAttribute("productBean", new DepositBoard_Bean());
		}else {
		ArrayList<SavingBoard_Bean> savingBankBean=boardService.getSavingBank();
		ArrayList<SavingBoard_Bean> savingtProductBean=boardService.getSavingProduct();
		model.addAttribute("bank_bean", savingBankBean);
		model.addAttribute("product_bean", savingtProductBean);
		model.addAttribute("productBean", new SavingBoard_Bean());
		}
		model.addAttribute("ck", ck);
		
		return "/board_Mypage/myProduct";
	}
	
	@RequestMapping(value="/myProduct.do", method =RequestMethod.POST)
	public String myProduct(HttpServletRequest request,
							HttpServletResponse response,
							int ck,
							String fin_prdt_cd, 
							Model model) {
		
		//아무것도 선택하지 않을시 리스트로
		if(fin_prdt_cd==null) {
			return "redirect:myProductList.do";
		}
		HttpSession session = request.getSession();
		String uid = (String)session.getAttribute("uid");
		if(uid==null) {
			return "redirect:loginForm.do";
		}
		logger.info(fin_prdt_cd);
		
		String fpn_array[]=fin_prdt_cd.split(",");
		for(int i=0; i<fpn_array.length;i++) {
			System.out.println(fpn_array[i]);
			if(ck==0) {
				DepositBoard_Bean depositBean=boardService.getOneDeposit(fpn_array[i]);
				depositBean.setId(uid);
				if(boardService.selectCntExist(uid,depositBean.getFin_prdt_cd())==0) {
					//예금/적금을 ck 코드로 나눠 넣음
						depositBean.setDep_or_sav("예금");
						//유저아이디 상품은행 상품은행코드 상품코드 상품이름 예금/적금insert 하기
						boardService.insertMyDeposit(depositBean);
					}	
			}else {
				SavingBoard_Bean savingBean=boardService.getOneSaving(fpn_array[i]);
				savingBean.setId(uid);
				if(boardService.selectCntExist(uid,savingBean.getFin_prdt_cd())==0) {
					//예금/적금을 ck 코드로 나눠 넣음
					savingBean.setDep_or_sav("적금");
						//유저아이디 상품은행 상품은행코드 상품코드 상품이름 예금/적금insert 하기
					boardService.insertMySaving(savingBean);
					}
			}
		}
		
		return "redirect:myProductList.do";
	}
	@RequestMapping(value = "/myProductList.do",method = RequestMethod.GET)
	public String myProductList(HttpServletRequest request,
			HttpServletResponse response,
			Model model) {
		HttpSession session = request.getSession();
		String uid = (String)session.getAttribute("uid");
		
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
			model.addAttribute("totalCnt",boardService.selectCntMYProduct(uid));
			model.addAttribute("current_page", 1);
			model.addAttribute("boardList", boardService.selectAllProduct(uid, 1, 10));	
			model.addAttribute("uid",uid);
			return "board_Adminpage/myProductList";
		}
		
		
		model.addAttribute("totalCnt",boardService.selectCntMYProduct(uid));
		model.addAttribute("current_page", 1);
		model.addAttribute("boardList", boardService.selectAllProduct(uid, 1, 10));	
		model.addAttribute("uid",uid);
		return "/board_Mypage/myProductList";
	}
	
	@RequestMapping(value = "/myProductDelete.do", method=RequestMethod.GET)
	public String myProductDelete(HttpServletResponse response,
									@Param("id") String id, 
									@Param("fin_prdt_cd") String fin_prdt_cd,
									Model model) {
		boardService.deleteProduct(id, fin_prdt_cd);
		PrintWriter out;
		try {
			//자바에서 알림창 띄우기
			out = response.getWriter();
			out.println("<script>alert('삭제되었습니다.');location.href='myProductList.do'; </script>"); 
	        out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "history.back();";
		
	}
	
	@RequestMapping(value="/myWant.do",method=RequestMethod.GET )
	public String myWant(
						HttpServletRequest request,
						HttpServletResponse response,
						@Param("current_page") int current_page,
						@Param("dep_or_sav") String dep_or_sav,
						@Param("fin_prdt_cd") String fin_prdt_cd,
						Model model) {
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("uid");
	
		if(id==null) {
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
		
		}
		
		//관심상품에 이미 상품이 존재하는지 확인
		if(boardService.selectWantExist(id, fin_prdt_cd)==0) {
		
		if(dep_or_sav.equals("0")) {
			dep_or_sav="예금";
			logger.info(fin_prdt_cd);
			DepositBoard_Bean depositBean=boardService.getOneDeposit(fin_prdt_cd);
			System.out.println(depositBean.getFin_prdt_cd());
			boardService.insertMyWant(id,depositBean,dep_or_sav);
		}else {
			dep_or_sav="적금";
			SavingBoard_Bean savingBean=boardService.getOneSaving(fin_prdt_cd);
			System.out.println(savingBean.getFin_prdt_cd());
			boardService.insertMyWant(id,savingBean,dep_or_sav);
		}
		
	
		}else {
			PrintWriter out;
			try {
				//자바에서 알림창 띄우기
				out = response.getWriter();
				out.println("<script>alert('이미 관심상품에 추가된 상품입니다.');history.back(); </script>"); 
		        out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(id.equals("admin")) {
			return "board_Adminpage/myWant";
		}	
		return myWantList(request,response,1,model);
	
	}
	@RequestMapping(value = "/myWantList.do",method = RequestMethod.GET)
	public String myWantList(HttpServletRequest request,
							HttpServletResponse response,
							@Param("current_page") int current_page,
							Model model) {
		HttpSession session = request.getSession();
		String uid = (String)session.getAttribute("uid");
		
		
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
			model.addAttribute("uid",uid);
			model.addAttribute("totalCnt",boardService.selectCntWant(uid));
			model.addAttribute("current_page", current_page);
			model.addAttribute("boardList",boardService.selectWantList(uid, current_page, 10));
			
			return "board_Adminpage/myWant";
		}
		
		model.addAttribute("uid",uid);
		model.addAttribute("totalCnt",boardService.selectCntWant(uid));
		model.addAttribute("current_page", current_page);
		model.addAttribute("boardList",boardService.selectWantList(uid, current_page, 10));
		return "/board_Mypage/myWant";
	}
	
	@RequestMapping(value="deleteMyWant.do" ,method = RequestMethod.GET)
	public String deleteMyWant(
			HttpServletRequest request,
			String fin_prdt_cd,
			Model model) {
		HttpSession session = request.getSession();
		String uid = (String)session.getAttribute("uid");
		//삭제
		boardService.deleteWant(uid, fin_prdt_cd);
		model.addAttribute("current_page", 1);
		return "redirect:myWantList.do";
	}
/////////////////////////////////////////////////////////////////////////////////////////////////


}
