package com.ourbank.app.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ourbank.app.bean.DepositBoard_Bean;
import com.ourbank.app.mapper.DepositProduct_Mapper;

@Component
public class DepositProduct_Service {
@Autowired
private DepositProduct_Mapper boardMapper;

public int nDepositProduct() {
	return boardMapper.nDepositProduct();
}
public ArrayList<DepositBoard_Bean> selectDepositList(int page, int rowPerPage){
	
	return this.boardMapper.selectDeposit(page,rowPerPage);
}

public ArrayList<DepositBoard_Bean> selectAllBank(){
	return this.boardMapper.all_bank();
}

public DepositBoard_Bean selectDepositContent(String fin_prdt_cd) {
	DepositBoard_Bean deposit_bean=boardMapper.selectDepositContent(fin_prdt_cd);
	System.out.println(deposit_bean.getFin_co_subm_day());
	return deposit_bean;
}
}
