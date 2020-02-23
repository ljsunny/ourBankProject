package com.ourbank.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ourbank.app.bean.BestBoard_Bean;
import com.ourbank.app.bean.NewnoticeBoard_Bean;
import com.ourbank.app.mapper.MainPage_Mapper;

@Component
public class MainPage_Service {

	@Autowired
	private MainPage_Mapper boardMapper;
	
	public ArrayList<NewnoticeBoard_Bean> selectNewNotice() {
		
		return this.boardMapper.selectNotice();
	}
	
	
	public ArrayList<BestBoard_Bean> selectBest(){
		return this.boardMapper.selectBest();
	}
}
