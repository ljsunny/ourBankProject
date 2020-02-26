package com.ourbank.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.ourbank.app.bean.Email_Bean;
import com.ourbank.app.service.Emeil_Service;



@Controller
public class Email_Controller {
@Autowired
private Emeil_Service boardService;

private static final Logger logger=
LoggerFactory.getLogger(Email_Controller.class);

@RequestMapping(value="/email.do", method = RequestMethod.GET) 
public String show_emailForm(Email_Bean emailBean, HttpServletRequest request, Model model) {
	logger.info("mail_form called!!");
	
	HttpSession session = request.getSession();
	String uid = (String)session.getAttribute("uid");

	model.addAttribute("uid", uid);
	model.addAttribute("emailBean", new Email_Bean());
	
	return "board_customer/email/emailView";
	
}

// 메일전송 시 구글에서 POP,보안수준 낮은 앱 ON해주기 
// 메일전송
@RequestMapping(value = "/email_send.do", method = RequestMethod.POST)
public String email_send(@ModelAttribute("emailBean") Email_Bean emailBean, 
		HttpServletRequest request, Model model) throws Exception {
	
	System.out.println("-----------------------------------------");
	logger.info("email_send called");
	
	HttpSession session = request.getSession();
	String uid = (String)session.getAttribute("uid");
	
	String content = "이 름 : " + emailBean.getName() + "\n" + "이메일 : " + emailBean.getSender() + "\n\n" + emailBean.getMessage();
	emailBean.setMessage(content);
	logger.info("content:"+content);
	
	emailBean.setReceiver("gmlwl2121@gmail.com");//실제 Admin 이메일 계정 넣어주기
	boardService.sendMail(emailBean);
	
	
	return "board_customer/email/complete";
}




































}
