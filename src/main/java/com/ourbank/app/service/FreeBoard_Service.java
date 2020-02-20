package com.ourbank.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
import com.ourbank.app.mapper.FreeBoard_Mapper;
import com.ourbank.app.bean.FreeBoard_Bean;
=======
import com.ourbank.app.bean.FAQBoard_Bean;
import com.ourbank.app.bean.FreeBoard_Bean;
import com.ourbank.app.mapper.FreeBoard_Mapper;
>>>>>>> f468c823fb38f81388f1f0cd521a2d8766679c8d

@Component
public class FreeBoard_Service {
	@Autowired
	private FreeBoard_Mapper boardMapper;
	
	//글입력 처리
	public void insertBoard(FreeBoard_Bean boardBean) {
<<<<<<< HEAD
		boardMapper.insertBoard(boardBean);
	}
	
	//전체글 수
=======
		boardMapper.insertBoard(boardBean);	
		};

	// 조회수 올리기
	public void updateHits(int hits, int idx) {
		System.out.println("조회수 올라감");
		this.boardMapper.updateHits(hits, idx);
	}

	//전체글수
>>>>>>> f468c823fb38f81388f1f0cd521a2d8766679c8d
	public int getTotalCnt() {
		int nCnt=0;
		nCnt=this.boardMapper.getTotalCnt();
		return nCnt;
	}

	//인덱스, 제목, 작성자, 날짜, 내용, 조회수  arraylist 얻어옴
<<<<<<< HEAD
	public ArrayList<FreeBoard_Bean> getList(int nStartPage, int list_num) {
		return this.boardMapper.getList(nStartPage, list_num);
	}
	
	//조회수 올리기
	public void updateHits(int hits, int idx_num) {
		System.out.println("조회수 올라감");
		this.boardMapper.updateHits(hits, idx_num);
	}
	
	//글보기에 뿌릴 bean
	public FreeBoard_Bean getSpecificRow(int idx_num) {
		return this.boardMapper.getSpecificRow(idx_num);//-getSpecificRow(@Param("id") String id): return id, subject, name, created_date, mail, memo, hits
	}
	
	//글 수정
	public void updateBoard(FreeBoard_Bean boardBean) {
		boardMapper.updateBoard(boardBean.getIdx_num(), boardBean.getSubject(), 
				boardBean.getContent(), boardBean.getFilename(), boardBean.getFilesize());
	}
	
	//글 삭제
	public void deleteRow(int idx_num) {
		this.boardMapper.deleteSpecificRow(idx_num);
	}
	
=======
	public ArrayList<FreeBoard_Bean> getList(int nStartPage, int list_num){
		return this.boardMapper.getList(nStartPage, list_num);
	}
	//회원가입 글수
	public int getSignUpCnt() {
		int nCnt=0;
		nCnt=this.boardMapper.getSignUpCnt();
		return nCnt;
	}
	//회원가입 arraylist
	public ArrayList<FreeBoard_Bean> getSignUpList(int nStartPage, int list_num){
		return this.boardMapper.getSignUpList(nStartPage, list_num);
	}
	//예적금
	public int getSavingsCnt() {
		int nCnt=0;
		nCnt=this.boardMapper.getSavingsCnt();
		return nCnt;
	}
	//예적금 arraylist
	public ArrayList<FreeBoard_Bean> getSavingsList(int nStartPage, int list_num){
		return this.boardMapper.getSavingsList(nStartPage, list_num);
	}
	//기타
	public int getEtcCnt() {
		int nCnt=0;
		nCnt=this.boardMapper.getEtcCnt();
		return nCnt;
	}
	//기타 arraylist
	public ArrayList<FreeBoard_Bean> getEtcList(int nStartPage, int list_num){
		return this.boardMapper.getEtcList(nStartPage, list_num);
	}
	
	//글보기에 뿌릴 bean
	public FreeBoard_Bean getSpecificRow(int idx) {
		return this.boardMapper.getSpecificRow(idx);//-getSpecificRow(@Param("id") String id): return id, subject, name, created_date, mail, memo, hits
	}
	
	//글 수정하기
	public void updateBoard(FreeBoard_Bean boardBean) {
		boardMapper.updateBoard(boardBean.getIdx(), boardBean.getSubject(), 
				boardBean.getCategory(), boardBean.getContent());
	}
	//글삭제하기
	public void deleteRow(int idx) {
		this.boardMapper.deleteSpecificRow(idx);
	}
>>>>>>> f468c823fb38f81388f1f0cd521a2d8766679c8d
	//검색한 글 수
	public int getTotalCntBySubject(String search) {
		int nCnt=0;
		nCnt=this.boardMapper.getTotalCntBySubject(search);
		return nCnt;
	}
<<<<<<< HEAD
	
	//검색하기 -> ArrayList로 목록
	public ArrayList<FreeBoard_Bean> getSearchedList(int nStartPage, int list_num, String strSearchThis){
		return this.boardMapper.getSearchedList(nStartPage, list_num, strSearchThis);
	}
	
	//답글
	public void updateRewrite(int idx_num) {
		boardMapper.updateRewrite(idx_num);
	}
	public int recentID() {
		int recent_id=0;
		recent_id=this.boardMapper.recentID();
		return recent_id;
	}
	public FreeBoard_Bean stairBoard(int idx_num) {
		return this.boardMapper.stairBoard(idx_num);
	}
	public void updateGroupStep(int ref, int step) {
		this.boardMapper.updateGroupStep(ref, step);
	}
	
	//아이디 가져오기
	/*public FreeBoard_Bean getIdname(int idx_num) {
		return this.boardMapper.getIdname(idx_num);*/
	
=======
	//검색하기 => ArrayList 로 목록 
	public ArrayList<FreeBoard_Bean> getSearchedList(int nStartPage, int list_num, String strSearchThis){
		return this.boardMapper.getSearchedList(nStartPage, list_num, strSearchThis);
	}

>>>>>>> f468c823fb38f81388f1f0cd521a2d8766679c8d
}
