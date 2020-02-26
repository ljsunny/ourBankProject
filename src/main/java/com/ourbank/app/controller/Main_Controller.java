package com.ourbank.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ourbank.app.bean.NewnoticeBoard_Bean;
import com.ourbank.app.mapper.MainPage_Mapper;
import com.ourbank.app.service.DepositProduct_Service;
import com.ourbank.app.service.MainPage_Service;

@Controller
public class Main_Controller {

	@Autowired
	private MainPage_Service boardService;
	
	@RequestMapping(value="/index.do", method = RequestMethod.GET)
	public String showIndex(Model model) {
		
		model.addAttribute("bestDeposit", boardService.bestDeposit());
		model.addAttribute("newNoticeBean", boardService.selectNewNotice());
		model.addAttribute("bestBean", boardService.selectBest());
		return "main";
	}
	

}
