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

	public ArrayList<DepositBoard_Bean> selectDepositList(int page, int rowPerPage) {

		return this.boardMapper.selectDeposit(page, rowPerPage);
	}

	public ArrayList<DepositBoard_Bean> selectAllBank() {
		return this.boardMapper.all_bank();
	}

	public DepositBoard_Bean selectDepositContent(String fin_prdt_cd) {
		DepositBoard_Bean deposit_bean = boardMapper.selectDepositContent(fin_prdt_cd);
		System.out.println(deposit_bean.getFin_co_subm_day());
		return deposit_bean;
	}

	//은행 주소 가져오기
	public String selectBankUrl(String fin_co_no) {
		return boardMapper.selectBankUrl(fin_co_no);
	}
//리스트에서 은행별로 정렬할때(셀렉트 태그)
	public int nDepositProduct(String kor_co_nm) {
		return boardMapper.nDepositProductBank(kor_co_nm);
	}

	public ArrayList<DepositBoard_Bean> selectDepositByBank(int page, int rowPerPage, String kor_co_nm) {
		ArrayList<DepositBoard_Bean> banklist = boardMapper.selectDepositByBank(page, rowPerPage, kor_co_nm);
		System.out.println("서비스:" + banklist.size());

		return banklist;
	}

//검색
	public int nDepositSearched(String searchStr) {
		return boardMapper.nDepositSearched(searchStr);
	}

	public ArrayList<DepositBoard_Bean> selectDepositSearched(int page, int rowPerPage, String searchStr) {
		ArrayList<DepositBoard_Bean> searchedlist = boardMapper.selectSearched(page, rowPerPage, searchStr);
		System.out.println("서비스:" + searchedlist.size());

		return searchedlist;
	}
}
