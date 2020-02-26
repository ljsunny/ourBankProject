package com.ourbank.app.service;

import java.util.ArrayList;

import org.omg.CORBA.FREE_MEM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ourbank.app.bean.DepositBoard_Bean;
import com.ourbank.app.bean.FreeBoard_Bean;
import com.ourbank.app.bean.SavingBoard_Bean;
import com.ourbank.app.bean.UserBoard_Bean;
import com.ourbank.app.mapper.MyPage_Mapper;

@Component
public class MyPage_Service {
@Autowired
private MyPage_Mapper boardMapper;

public UserBoard_Bean getUserInfo(String id) {
	return boardMapper.selectMyInfo(id);
}

//내정보 수정
public void updateUserInfo(UserBoard_Bean userBean) {
	this.boardMapper.updateMyInfo(userBean.getUser_name(), userBean.getUser_birth(),
			userBean.getUser_phone(), userBean.getUser_address(), userBean.getUser_email(),userBean.getId());
}
//삭제
public void deleteId(String id) {
	this.boardMapper.deleteId(id);
}
//상품 정보 삭제
public void deleteProductData(String id) {
	this.boardMapper.deleteProductData(id);
}

//내가쓴글 - 리스트보기  / 전체글번호, 글제목, 날짜 , 조회수, 아이디
public ArrayList<FreeBoard_Bean> getUserBoardList(String id, int nStartPage, int list_num) {
	return boardMapper.getBoardList(id, nStartPage, list_num);
}

//내가쓴글 - 글보기
public FreeBoard_Bean getSpecificRow(int board_idx) {
	return this.boardMapper.getSpecificRow(board_idx);
}

/////////////////////////////////////////////////////////////////////////////////////////////////
	
//가입한 예금 은행 선택
public ArrayList<DepositBoard_Bean> getDepositBank() {
	return this.boardMapper.selectDepositBank();
}
//가입한 은행에 맞는 상품 검색
public ArrayList<DepositBoard_Bean> getDepositProduct() {
	return this.boardMapper.selectDepositProduct();
}
//상품코드에 일치하는 데이터 가져오기
public DepositBoard_Bean getOneDeposit(String fin_prdt_cd) {
	return this.boardMapper.selectOneDeposit(fin_prdt_cd);
}
//이미 등록했는지 확인
public int selectCntExist(String id, String fin_prdt_cd) {
	return this.boardMapper.selectCntExist(id, fin_prdt_cd);
}

//가입한 상품  넣기
public void insertMyDeposit(DepositBoard_Bean depositBean) {
	System.out.println( depositBean.getId());
	boardMapper.insertMyDeposit( depositBean.getId(),
								depositBean.getFin_co_no(),
								depositBean.getKor_co_nm(),
								depositBean.getFin_prdt_cd(),
								depositBean.getFin_prdt_nm());
}
//적금-------------
//가입한 예금 은행 선택
public ArrayList<SavingBoard_Bean> getSavingBank() {
	return this.boardMapper.selectSavingBank();
}
//가입한 은행에 맞는 상품 검색
public ArrayList<SavingBoard_Bean> getSavingProduct() {
	return this.boardMapper.selectSavingProduct();
}
//상품코드에 일치하는 데이터 가져오기
public SavingBoard_Bean getOneSaving(String fin_prdt_cd) {
	return this.boardMapper.selectOneSaving(fin_prdt_cd);
}

//가입한 상품  넣기
public void insertMySaving(SavingBoard_Bean savingBean) {
	System.out.println(savingBean.getId());
	boardMapper.insertMySaving(savingBean.getId(),
			savingBean.getFin_co_no(),
			savingBean.getKor_co_nm(),
			savingBean.getFin_prdt_cd(),
			savingBean.getFin_prdt_nm());
}
public int selectCntMYProduct(String id) {
	return this.boardMapper.selectCntMyProduct(id);
}

//가입상품 리스트 출력
public ArrayList<DepositBoard_Bean> selectAllProduct(String id,int current_page, int pageforList){
	return this.boardMapper.selectAllProduct(id,current_page,pageforList);
}

//가입상품 삭제
public void deleteProduct(String id, String fin_prdt_cd) {
	 boardMapper.deleteProduct(id, fin_prdt_cd);
}
////장바구니///////////////////////////////////////////////
//원하는 상품 중복확인
public int selectWantExist(String id, String fin_prdt_cd) {
	return boardMapper.selectWantExist(id, fin_prdt_cd);
}
//원하는 상품 넣기
public void insertMyWant(String fin_prdt_cd,DepositBoard_Bean depositBean, String dep_or_sav) {
	boardMapper.insertMyWant(fin_prdt_cd,
			depositBean.getFin_co_no(),
			depositBean.getKor_co_nm(),
			depositBean.getFin_prdt_cd(),
			depositBean.getFin_prdt_nm(),
			dep_or_sav);
}

//모든 원하는 상품 수
public int selectCntWant(String id) {
	return boardMapper.selectWantCnt(id);
}
//원하는 상품 리스트
public ArrayList<DepositBoard_Bean> selectWantList(String id, int current_page, int pageforView){
	return boardMapper.selectWantList(id, current_page, pageforView);
}
public void deleteWant(String id, String fin_prdt_cd) {
	boardMapper.deleteMyWant(id, fin_prdt_cd);
}
/////////////////////////////////////////////////////////////////////////////////////////////////


}