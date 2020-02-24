package com.ourbank.app.service;

import java.util.ArrayList;

import org.omg.CORBA.FREE_MEM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ourbank.app.bean.DepositBoard_Bean;
import com.ourbank.app.bean.FreeBoard_Bean;
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
	this.boardMapper.updateMyInfo(userBean.getPasswd(), userBean.getUser_name(), userBean.getUser_birth(),
			userBean.getUser_phone(), userBean.getUser_address(), userBean.getUser_email(),userBean.getId());
}
//삭제
public void deleteId(String id) {
	this.boardMapper.deleteId(id);
}

//내가쓴글 - 리스트보기  / 전체글번호, 글제목, 날짜 , 조회수, 아이디
public ArrayList<FreeBoard_Bean> getUserBoardList(String id, int nStartPage, int list_num) {
	return boardMapper.getBoardList(id, nStartPage, list_num);
}

//내가쓴글 - 글보기
public FreeBoard_Bean getSpecificRow(int board_idx) {
	return this.boardMapper.getSpecificRow(board_idx);
}

//조회수 올리기
//리뷰게시판
public void updateReviewHits(int hits, int best_idx) {
	System.out.println("조회수 올라감");
	this.boardMapper.updateReviewHits(hits, best_idx);
}
//자유게시판 
public void updateFreeHits(int hits, int best_idx) {
	System.out.println("조회수 올라감");
	this.boardMapper.updateFreeHits(hits, best_idx);
}
//모임방게시판 
public void updateMeetingHits(int hits, int best_idx) {
	System.out.println("조회수 올라감");
	this.boardMapper.updateMeetingHits(hits, best_idx);
}
//토론방게시판 
public void updateDebateHits(int hits, int best_idx) {
	System.out.println("조회수 올라감");
	this.boardMapper.updateDebateHits(hits, best_idx);
}
//제테크노하우게시판 
public void updateInvestHits(int hits, int best_idx) {
	System.out.println("조회수 올라감");
	this.boardMapper.updateInvestHits(hits, best_idx);
}
//QnA게시판 
public void updateQnaHits(int hits, int best_idx) {
	System.out.println("조회수 올라감");
	this.boardMapper.updateQnaHits(hits, best_idx);
}


//전체글 수
public int getTotalCnt(String id) {
	int nCnt=0;
	nCnt=this.boardMapper.getTotalCnt(id);
	return nCnt;
	}




	





}