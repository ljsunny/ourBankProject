package com.ourbank.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ourbank.app.bean.DepositBoard_Bean;
import com.ourbank.app.bean.SavingBoard_Bean;
import com.ourbank.app.mapper.DepositProduct_Mapper;
import com.ourbank.app.mapper.SavingProduct_Mapper;

@Component
public class SavingService {
@Autowired
private SavingProduct_Mapper boardMapper;

public int nSavingProduct() {
	return boardMapper.nSavingProduct();
}
public ArrayList<SavingBoard_Bean> selectSavingList(int page, int rowPerPage){
	
	return this.boardMapper.selectSaving(page,rowPerPage);
}

public ArrayList<SavingBoard_Bean> selectAllBank(){
	return this.boardMapper.all_bank();
}
public SavingBoard_Bean selectSavingContent(String fin_prdt_cd) {
	SavingBoard_Bean saving_bean=boardMapper.selectSavingContent(fin_prdt_cd);
	System.out.println(saving_bean.getFin_co_subm_day());
	return saving_bean;
}
//���� �ּ� ��������
	public String selectBankUrl(String fin_co_no) {
		return boardMapper.selectBankUrl(fin_co_no);
	}
//����Ʈ���� ���ະ�� �����Ҷ�(����Ʈ �±�)
public int nSavingProduct(String kor_co_nm) {
	return boardMapper.nSavingProductBank(kor_co_nm);
}
public ArrayList<SavingBoard_Bean> selectSavingByBank(int page,
														int rowPerPage, 
														String kor_co_nm){
	ArrayList<SavingBoard_Bean> banklist=boardMapper.selectSavingByBank(page,rowPerPage,kor_co_nm);
	System.out.println("����:"+banklist.size());
	
	return banklist;

}//�˻�
public int nSavingSearched(String searchStr) {
	return boardMapper.nSavingSearched(searchStr);
}

public ArrayList<SavingBoard_Bean> selectSavingSearched(int page, int rowPerPage, String searchStr) {
	ArrayList<SavingBoard_Bean> searchedlist = boardMapper.selectSearched(page, rowPerPage, searchStr);
	System.out.println("����:" + searchedlist.size());

	return searchedlist;
}
}
