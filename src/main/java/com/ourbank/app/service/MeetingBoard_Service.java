package com.ourbank.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ourbank.app.mapper.MeetingBaord_Mapper;
import com.ourbank.app.bean.MeetingBoard_Bean;;

@Component
public class MeetingBoard_Service {
	
	@Autowired
	private MeetingBaord_Mapper boardMapper;
	
	//글입력 처리
	public void insertBoard(MeetingBoard_Bean boardBean) {
		boardMapper.insertBoard(boardBean);
	}
	
	//전체글 수
	public int getTotalCnt() {
		int nCnt=0;
		nCnt=this.boardMapper.getTotalCnt();
		return nCnt;
	}

	//인덱스, 제목, 작성자, 날짜, 내용, 조회수  arraylist 얻어옴
	public ArrayList<MeetingBoard_Bean> getList(int nStartPage, int list_num) {
		return this.boardMapper.getList(nStartPage, list_num);
	}
	
	//조회수 올리기
	public void updateHits(int hits, int idx_num) {
		System.out.println("조회수 올라감");
		this.boardMapper.updateHits(hits, idx_num);
	}
	
	//글보기에 뿌릴 bean
	public MeetingBoard_Bean getSpecificRow(int idx_num) {
		return this.boardMapper.getSpecificRow(idx_num);//-getSpecificRow(@Param("id") String id): return id, subject, name, created_date, mail, memo, hits
	}
	
	//글 수정
	public void updateBoard(MeetingBoard_Bean boardBean) {
		boardMapper.updateBoard(boardBean.getIdx_num(), boardBean.getSubject(), 
				boardBean.getContent(), boardBean.getFilename(), boardBean.getFilesize());
	}
	
	//글 삭제
	public void deleteRow(int idx_num) {
		this.boardMapper.deleteSpecificRow(idx_num);
	}
	
	//검색한 글 수
	public int getTotalCntBySubject(String search) {
		int nCnt=0;
		nCnt=this.boardMapper.getTotalCntBySubject(search);
		return nCnt;
	}
	
	//검색하기 -> ArrayList로 목록
	public ArrayList<MeetingBoard_Bean> getSearchedList(int nStartPage, int list_num, String strSearchThis){
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
	public MeetingBoard_Bean stairBoard(int idx_num) {
		return this.boardMapper.stairBoard(idx_num);
	}
	public void updateGroupStep(int ref, int step) {
		this.boardMapper.updateGroupStep(ref, step);
	}
	
	//아이디 가져오기
	/*public meetingBoard_Bean getIdname(int idx_num) {
		return this.boardMapper.getIdname(idx_num);*/
}
