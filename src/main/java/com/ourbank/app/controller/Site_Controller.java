package com.ourbank.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Site_Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Best_Controller.class);
	
	//국내사이트 이동
	@RequestMapping(value = "/domestic_site_show.do", method = RequestMethod.GET)
	public String domesticSiteShow(Model model) {
		logger.info("show_domestic_site called!!");
		
		return "site/domestic_site";
	}
	
	//해외사이트 이동
	@RequestMapping(value = "/overseas_site_show.do", method = RequestMethod.GET)
	public String overseasSiteShow(Model model) {
		logger.info("show_overseas_site called!!");
		
		return "site/overseas_site";
	}
	

}
