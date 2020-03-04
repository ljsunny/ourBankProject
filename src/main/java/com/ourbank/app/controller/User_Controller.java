package com.ourbank.app.controller;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ourbank.app.bean.DepositBoard_Bean;
import com.ourbank.app.bean.UserBoard_Bean;

import com.ourbank.app.service.User_Service;
import com.ourbank.app.SHA256Util;

@Controller
public class User_Controller {
	@Autowired
	private User_Service boardService;
	private static final Logger logger = LoggerFactory.getLogger(User_Controller.class);
	
	// ȸ������â ������
	@RequestMapping(value = "/signUp.do", method = RequestMethod.GET)
	public String showSignUpForm(Model model) {

		model.addAttribute("boardBean", new UserBoard_Bean());
		return "/user/signUpForm";
	}

	// ���̵� üũ
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.GET)
	public String idCheck(@RequestParam("id") String id, Model model) {
		// ������ ���̵� �ִ��� Ȯ��
		Boolean check = boardService.selectCheckID(id);
		// true : ��� ���� , false: ���Ұ�
		model.addAttribute("check", check);
		model.addAttribute("id", id);
		return "/user/IdCheck";
	}

	// �ּ�ã�� - zipcode ��� x api�����
	@RequestMapping(value = "/zipCode.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String zipCode(Model model) {

		return "/user/zipCode";
	}

	// ȸ������ ing
	@RequestMapping(value = "/doSignUp.do", method = RequestMethod.POST)
	public String doSignUp(UserBoard_Bean boardBean, Model model) {
		//��й�ȣ�� �ؽ� ��ȣȭ�Ͽ� ����
		SHA256Util sha256=new SHA256Util();
		String salt = sha256.generateSalt();
		System.out.println(salt);
		String pwd=boardBean.getPasswd();
		String new_pwd=sha256.getEncrypt(pwd, salt);
		boardBean.setPasswd(new_pwd);
		boardBean.setSalt(salt);
		
		boardService.insert(boardBean);

		return "redirect:welcome.do";
	}
	//ȯ���մϴ� + ������ ��ǰ�ޱ�
	@RequestMapping(value="/welcome.do",method = RequestMethod.GET )
	public String welcome(Model model) {
		
		return "/user/welcome";
	}

	// �α���â ������
	@RequestMapping(value = "/loginForm.do", method = RequestMethod.GET)
	public String loginForm(Model model) {
		logger.info("login called");
		model.addAttribute("boardBean", new UserBoard_Bean());
		model.addAttribute("noinfo", false);
		return "/user/loginForm";
	}

	//�α��� ó��
	@RequestMapping(value="/loginPro.do", method = RequestMethod.POST)
	public String loginPro(
			HttpServletRequest request,
			@RequestParam("id") String id,
			@RequestParam("passwd") String passwd,
			Model model) {
		logger.info("loginPro called");
		logger.info(id+" "+passwd);
		//ù��° if id�� �ִ��� ã�� ������ true ������ false â ��ȯ
		logger.info("loginPro1 called");
		boolean checkid= boardService.selectCheckID(id);
		logger.info(checkid+"");
		if(checkid==true) {//������ ���� ���
			model.addAttribute("check", checkid);
			model.addAttribute("noinfo", true);
			return "/user/loginForm";
		}
		
		//�ι�° if ã�� id�� password �´��� Ȯ�� ������ homeâ , ������ false â ��ȯ
		logger.info("loginPro2 called");
		//��Ʈ�� ��й�ȣ�� ���� sort, hash_passwd ��������
		String salt=boardService.selectSort(id);
		String re_pass=SHA256Util.getEncrypt(passwd, salt);
		boolean checkpasswd=boardService.CheckIDandPassword(id, re_pass);
		if(checkpasswd==false) {//��й�ȣ�� Ʋ�����
			model.addAttribute("check", checkpasswd);
			model.addAttribute("noinfo", true);
			return "/user/loginForm";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("uid", id);
		return "redirect:index.do";
	}
	
	//�α׾ƿ�
	@RequestMapping(value="/logOut.do", method=RequestMethod.GET)
	public String logOut(HttpServletRequest request,
			Model model) {
		HttpSession session=request.getSession();
		session.invalidate();
		
		return "redirect:index.do";
	}
	
	//���̵� ã�� â����
	@RequestMapping(value="/findId.do" , method=RequestMethod.GET)
	public String findId(Model model) {
		
		model.addAttribute("user_id", null);
		model.addAttribute("ck", false);
		return "user/findId";
	}
	
	@RequestMapping(value="/findIdCheck.do",method = RequestMethod.POST)
	public String findIdCheck(@RequestParam("user_name") String user_name,
			@RequestParam("user_birth") String user_birth,
			@RequestParam("user_phone") String user_phone,
			Model model) {
		
		String user_id = boardService.checkFindId(user_name, user_birth, user_phone);
		logger.info(user_id);
		model.addAttribute("ck", true);
		model.addAttribute("user_id", user_id);
		return "user/findId";
	}
	//��й�ȣ ã�� â����
	@RequestMapping(value="/findPasswd.do", method=RequestMethod.GET)
	public String findPassword(Model model) {
		model.addAttribute("ck", 0);
		return "user/findPasswd";
	}
	
	@RequestMapping(value="/findPasswdCheck.do", method=RequestMethod.POST)
	public String findPasswordCheck(
			@RequestParam("id") String id,
			@RequestParam("user_name") String user_name,
			@RequestParam("user_birth") String user_birth,
			@RequestParam("user_phone") String user_phone,
			Model model) {
		int ck=boardService.checkFindPasswd(id, user_name, user_birth, user_phone);
		if(ck==0) {//������ Ʋ����
			model.addAttribute("ck", 1);
			return "user/findPasswd";
		}
		//������ ������
		model.addAttribute("ck", 2);
		model.addAttribute("id", id);
		return "user/findPasswd";
	}
	
	//��й�ȣ ����
	@RequestMapping(value="/changePasswdCheck.do" , method = RequestMethod.POST)
	public String changePasswdCheck(
			@Param("passwd") String passwd,
			@Param("id") String id,
			Model model) {
		System.out.println(id);
		String salt=boardService.selectSort(id);
		String re_pass=SHA256Util.getEncrypt(passwd, salt);
		boardService.changePasswd(re_pass, id);
		return "user/passwdChanged";
		
	}
	
	
	
}
