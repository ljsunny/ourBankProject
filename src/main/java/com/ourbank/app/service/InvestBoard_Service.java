package com.ourbank.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ourbank.app.bean.InvestBoard_Bean;
import com.ourbank.app.mapper.InvestBoard_Mapper;

@Component
public class InvestBoard_Service {
	@Autowired
	private InvestBoard_Mapper boardMapper;
	
	//글입력 처리
	public void insertBoard(InvestBoard_Bean boardBean) {
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

	//인덱스, 제목, 작성자, 날짜, 내용, 조회수  arraylist 얻어옴
	public ArrayList<InvestBoard_Bean> getList(int nStartPage, int list_num){
		return this.boardMapper.getList(nStartPage, list_num);
	}
	
	//항목별 글목록
	//성공사례 글 수
	public int getSuccessCnt() {
		int nCnt=0;
		nCnt=this.boardMapper.getSuccessCnt();
		return nCnt;
	}
	//성공사례arraylist
	public ArrayList<InvestBoard_Bean> getSuccessList(int nStartPage, int list_num){
		return this.boardMapper.getSuccessList(nStartPage, list_num);
	}
	
	//실폐사례
	public int getFailCnt() {
		int nCnt=0;
		nCnt=this.boardMapper.getFailCnt();
		return nCnt;
	}
	//실폐사례 arraylist
	public ArrayList<InvestBoard_Bean> getFailList(int nStartPage, int list_num){
		return this.boardMapper.getFailList(nStartPage, list_num);
	}
	//기타
	public int getEtcCnt() {
		int nCnt=0;
		nCnt=this.boardMapper.getEtcCnt();
		return nCnt;
	}
	//기타 arraylist
	public ArrayList<InvestBoard_Bean> getEtcList(int nStartPage, int list_num){
		return this.boardMapper.getEtcList(nStartPage, list_num);
	}
	
	//글보기에 뿌릴 bean
	public InvestBoard_Bean getSpecificRow(int idx) {
		return this.boardMapper.getSpecificRow(idx);//-getSpecificRow(@Param("id") String id): return id, subject, name, created_date, mail, memo, hits
	}
	
	//글 수정하기
	public void updateBoard(InvestBoard_Bean boardBean) {
		boardMapper.updateBoard(boardBean.getIdx(), boardBean.getSubject(), 
				boardBean.getInvest_case(), boardBean.getContent());
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
	public ArrayList<InvestBoard_Bean> getSearchedList(int nStartPage, int list_num, String strSearchThis){
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
	public InvestBoard_Bean stairBoard(int idx) {
		return this.boardMapper.stairBoard(idx);
	}
	
	public void updateGroupStep(int ref, int step) {
		this.boardMapper.updateGroupStep(ref, step);
	}

	//선택힝목 가져오기
	public String InvestCase(int ref) {
		return this.boardMapper.InvestCase(ref);
	}
	

}
