package com.ourbank.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.ourbank.app.bean.ReviewBoard_Bean;
import com.ourbank.app.mapper.ReviewBoard_Mapper;

@Component
public class ReviewBoard_Service {
	@Autowired
	private ReviewBoard_Mapper boardMapper;
	
	//글입력 처리
	public void insertBoard(ReviewBoard_Bean boardBean) {
		boardMapper.insertBoard(boardBean);	
		};

	// 조회수 올리기
	public void updateHits(int hits, int idx) {
		System.out.println("조회수 올라감");
		this.boardMapper.updateHits(hits, idx);
	}

	//전체글수
	public int getTotalCnt() {
		int nCnt=0;
		nCnt=this.boardMapper.getTotalCnt();
		return nCnt;
	}

	//List에 뿌릴 arraylist 얻어옴
	public ArrayList<ReviewBoard_Bean> getList(int nStartPage, int list_num){
		return this.boardMapper.getList(nStartPage, list_num);
	}
	//회원가입 글수
	public int getSignUpCnt() {
		int nCnt=0;
		nCnt=this.boardMapper.getSignUpCnt();
		return nCnt;
	}
	//회원가입 arraylist
	public ArrayList<ReviewBoard_Bean> getSignUpList(int nStartPage, int list_num){
		return this.boardMapper.getSignUpList(nStartPage, list_num);
	}
	//예적금
	public int getSavingsCnt() {
		int nCnt=0;
		nCnt=this.boardMapper.getSavingsCnt();
		return nCnt;
	}
	//예적금 arraylist
	public ArrayList<ReviewBoard_Bean> getSavingsList(int nStartPage, int list_num){
		return this.boardMapper.getSavingsList(nStartPage, list_num);
	}
	//기타
	public int getEtcCnt() {
		int nCnt=0;
		nCnt=this.boardMapper.getEtcCnt();
		return nCnt;
	}
	//기타 arraylist
	public ArrayList<ReviewBoard_Bean> getEtcList(int nStartPage, int list_num){
		return this.boardMapper.getEtcList(nStartPage, list_num);
	}
	
	//글보기에 뿌릴 bean
	public ReviewBoard_Bean getSpecificRow(int idx) {
		return this.boardMapper.getSpecificRow(idx);//-getSpecificRow(@Param("id") String id): return id, subject, name, created_date, mail, memo, hits
	}
	
	//글 수정하기
	public void updateBoard(ReviewBoard_Bean boardBean) {
		boardMapper.updateBoard(boardBean.getIdx(), boardBean.getSubject(), 
				boardBean.getReview_case(), boardBean.getContent(),
				boardBean.getSatisfaction(), boardBean.getBanks(), boardBean.getRe_productname());
	}
	//글삭제하기
	public void deleteRow(int idx) {
		this.boardMapper.deleteSpecificRow(idx);
	}
	//검색한 글 수
	public int getTotalCntBySubject(String search) {
		int nCnt=0;
		nCnt=this.boardMapper.getTotalCntBySubject(search);
		return nCnt;
	}
	//검색하기 => ArrayList 로 목록 
	public ArrayList<ReviewBoard_Bean> getSearchedList(int nStartPage, int list_num, String strSearchThis){
		return this.boardMapper.getSearchedList(nStartPage, list_num, strSearchThis);
	}
	
	//답글
	public void updateRewrite(int idx) {
		boardMapper.updateRewrite(idx);
	}
	public int recentID() {
		int recent_id=0;
		recent_id=this.boardMapper.recentID();
		return recent_id;
	}
	public ReviewBoard_Bean stairBoard(int idx) {
		return this.boardMapper.stairBoard(idx);
	}
	public void updateGroupStep(int ref, int step) {
		this.boardMapper.updateGroupStep(ref, step);
	}
	
	// 선택힝목 가져오기
	public String ReviewCase(int ref) {
		return this.boardMapper.ReviewCase(ref);
	}
	
	//댓글-원글의 일부 값 넣기
	public String Satisfaction(int ref) {
		return this.boardMapper.Satisfaction(ref);
	}
	
	public String Banks(int ref) {
		return this.boardMapper.Banks(ref);
	}
	
	public String Re_productname(int ref) {
		return this.boardMapper.Re_productname(ref);
	}

	

}
