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
			//�ڹٿ��� �˸�â ����
			out = response.getWriter();
			out.println("<script>alert('������ �����ϴ�.'); location.href='loginForm.do';</script>"); 
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
////////////////����//////////////////////////////////////////////////////////////////
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
			//�ڹٿ��� �˸�â ����
			out = response.getWriter();
			out.println("<script>alert('������ �����ϴ�.'); location.href='loginForm.do';</script>"); 
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
	
	//���̵� ����
	boardService.deleteId(id);
	//�� ��ǰ �����ͺ��̽����� �����ϱ�
	boardService.deleteProductData(id);
	
	HttpSession session=request.getSession();
	session.invalidate();
	return "redirect:index.do";
}
/////////////////////////////////////////////////////////////////////////////////

//���� �ۼ��ѱ� -����Ʈ 
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
				//�ڹٿ��� �˸�â ����
				out = response.getWriter();
				out.println("<script>alert('������ �����ϴ�.'); location.href='loginForm.do';</script>"); 
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
	//�����ۼ��� �� - �ۺ���
	@RequestMapping(value = "/myBoardView.do", method = RequestMethod.GET)
	public String myBoardView(@RequestParam("board_idx") int board_idx,
							  @RequestParam("current_page") String current_page,
						      Model model) {
		logger.info("myBoardView called");
		
		FreeBoard_Bean boardData = boardService.getSpecificRow(board_idx);
		logger.info(boardData.getContent());
		logger.info("hits: "+boardData.getHits());
		
		//��ȸ�� ����
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

	//���� �ٿ�ε�
	@RequestMapping(value = "/my_download.do", method=RequestMethod.GET)
	@ResponseBody
	public byte[] free_downProcess(HttpServletResponse response, @RequestParam String filename) 
			   throws IOException{
		System.out.println("�ٿ�ε�");
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
	//���Ի�ǰ
	@RequestMapping(value="/myProductDetail.do", method =RequestMethod.GET )
	public String myProductDetail(@RequestParam("ck") int ck, Model model) {
		//���ݻ�ǰ
		if(ck==0) {
		//�Ѹ���
		ArrayList<DepositBoard_Bean> depositBankBean=boardService.getDepositBank();
		//���ະ�� ���� bean
		ArrayList<DepositBoard_Bean> depositProductBean=boardService.getDepositProduct();
		model.addAttribute("bank_bean", depositBankBean);
		model.addAttribute("product_bean", depositProductBean);
		//���� empty ��
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
		
		//�ƹ��͵� �������� ������ ����Ʈ��
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
					//����/������ ck �ڵ�� ���� ����
						depositBean.setDep_or_sav("����");
						//�������̵� ��ǰ���� ��ǰ�����ڵ� ��ǰ�ڵ� ��ǰ�̸� ����/����insert �ϱ�
						boardService.insertMyDeposit(depositBean);
					}	
			}else {
				SavingBoard_Bean savingBean=boardService.getOneSaving(fpn_array[i]);
				savingBean.setId(uid);
				if(boardService.selectCntExist(uid,savingBean.getFin_prdt_cd())==0) {
					//����/������ ck �ڵ�� ���� ����
					savingBean.setDep_or_sav("����");
						//�������̵� ��ǰ���� ��ǰ�����ڵ� ��ǰ�ڵ� ��ǰ�̸� ����/����insert �ϱ�
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
				//�ڹٿ��� �˸�â ����
				out = response.getWriter();
				out.println("<script>alert('������ �����ϴ�.'); location.href='loginForm.do';</script>"); 
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
			//�ڹٿ��� �˸�â ����
			out = response.getWriter();
			out.println("<script>alert('�����Ǿ����ϴ�.');location.href='myProductList.do'; </script>"); 
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
				//�ڹٿ��� �˸�â ����
				out = response.getWriter();
				out.println("<script>alert('������ �����ϴ�.'); location.href='loginForm.do';</script>"); 
		        out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     
			return "writeboard";
		
		}
		
		//���ɻ�ǰ�� �̹� ��ǰ�� �����ϴ��� Ȯ��
		if(boardService.selectWantExist(id, fin_prdt_cd)==0) {
		
		if(dep_or_sav.equals("0")) {
			dep_or_sav="����";
			logger.info(fin_prdt_cd);
			DepositBoard_Bean depositBean=boardService.getOneDeposit(fin_prdt_cd);
			System.out.println(depositBean.getFin_prdt_cd());
			boardService.insertMyWant(id,depositBean,dep_or_sav);
		}else {
			dep_or_sav="����";
			SavingBoard_Bean savingBean=boardService.getOneSaving(fin_prdt_cd);
			System.out.println(savingBean.getFin_prdt_cd());
			boardService.insertMyWant(id,savingBean,dep_or_sav);
		}
		
	
		}else {
			PrintWriter out;
			try {
				//�ڹٿ��� �˸�â ����
				out = response.getWriter();
				out.println("<script>alert('�̹� ���ɻ�ǰ�� �߰��� ��ǰ�Դϴ�.');history.back(); </script>"); 
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
				//�ڹٿ��� �˸�â ����
				out = response.getWriter();
				out.println("<script>alert('������ �����ϴ�.'); location.href='loginForm.do';</script>"); 
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
		//����
		boardService.deleteWant(uid, fin_prdt_cd);
		model.addAttribute("current_page", 1);
		return "redirect:myWantList.do";
	}
/////////////////////////////////////////////////////////////////////////////////////////////////


}
