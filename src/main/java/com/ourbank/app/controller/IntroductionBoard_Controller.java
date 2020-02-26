package com.ourbank.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IntroductionBoard_Controller {
	
	
	private static final Logger logger=LoggerFactory.getLogger(IntroductionBoard_Controller.class);
	
	//사이트 소개로 이동
	@RequestMapping(value="/introduction_show.do", method=RequestMethod.GET)
	public String introductionShow(Model model) {
		logger.info("show_introduction called!!");
	
		return "board_notice/introduction/introductionForm";
	}

}
