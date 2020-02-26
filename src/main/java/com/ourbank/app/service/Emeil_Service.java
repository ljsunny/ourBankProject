package com.ourbank.app.service;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ourbank.app.bean.Email_Bean;
import com.ourbank.app.controller.Email_Controller;

@Component
public class Emeil_Service {
	@Autowired
	private JavaMailSender mailSender;
	
	private static final Logger logger=
			LoggerFactory.getLogger(Email_Controller.class);

	public void sendMail(Email_Bean emailBean) throws Exception{ 
		
		MimeMessage msg = mailSender.createMimeMessage();
		
		
		
		//메일 보낼 구성
		msg.setSubject(emailBean.getSubject());
		msg.setText(emailBean.getMessage());
		msg.setSentDate(new Date());
		msg.setRecipient(RecipientType.TO, new InternetAddress(emailBean.getReceiver()));
				
				
		mailSender.send(msg);
		
	}
	
	
	
}
