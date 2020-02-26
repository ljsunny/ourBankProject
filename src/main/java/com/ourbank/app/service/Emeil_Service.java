package com.ourbank.app.service;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ourbank.app.bean.Email_Bean;

@Component
public class Emeil_Service {
	@Autowired
	private JavaMailSender mailSender;

	public void sendMail(Email_Bean emailBean) throws Exception{ 
		
		MimeMessage msg = mailSender.createMimeMessage();
		
		//파일첨부
		MultipartFile file=emailBean.getFile();
		if(file!=null) {
			String fileName=file.getOriginalFilename();
			emailBean.setFilename(fileName);
			
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(emailBean.getMessage());
			mbp1.setHeader("Content-Type", emailBean.getMailtype());
			
			MimeBodyPart mbp2 = new MimeBodyPart();
			String FILE_PATH = 
					"C:\\Users\\user\\Desktop\\OurBank\\src\\main\\webapp\\resources\\files\\";
			FileDataSource fds = new FileDataSource(FILE_PATH+emailBean.getFilename());
			mbp2.setDataHandler(new DataHandler(fds));
			try {
				mbp2.setFileName(MimeUtility.encodeWord(fds.getName())); //파일이름 엔코딩
			}catch (UnsupportedEncodingException e) {
				System.out.println(e.toString());
			}
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);
			msg.setContent(mp);
			
		}else { //파일 없을 경우
			msg.setText(emailBean.getMessage());
			msg.setHeader("Content-Type", emailBean.getMailtype());
		}
		
		msg.setSubject(emailBean.getSubject());
		msg.setText(emailBean.getMessage());
		msg.setSentDate(new Date());
		msg.setRecipient(RecipientType.TO, new InternetAddress(emailBean.getReceiver()));
		mailSender.send(msg);
		
	}
	
	
	
}
