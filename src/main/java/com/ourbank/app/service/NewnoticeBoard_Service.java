package com.ourbank.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ourbank.app.mapper.NewnoticeBoard_Mapper;
import com.ourbank.app.bean.NewnoticeBoard_Bean;
import com.ourbank.app.bean.NewsBoard_Bean;
import com.ourbank.app.bean.ReviewBoard_Bean;

@Component
public class NewnoticeBoard_Service {
	@Autowired
	private NewnoticeBoard_Mapper boardMapper;
	
	//글입력 처리
	public void insertBoard(NewnoticeBoard_Bean boardBean) {
		boardMapper.insertBoard(boardBean);
	}
	
	//전체글 수
	public int getTotalCnt() {
		int nCnt=0;
		nCnt=this.boardMapper.getTotalCnt();
		return nCnt;
	}
	
	//List에 뿌릴 arraylist 얻어옴
		public ArrayList<NewnoticeBoard_Bean> getList(int nStartPage, int list_num){
			return this.boardMapper.getList(nStartPage, list_num);
		}
		//은행별공지 글수
		public int getBankNoticeCnt() {
			int nCnt=0;
			nCnt=this.boardMapper.getBankNoticeCnt();
			return nCnt;
		}
		//은행별공지 arraylist
		public ArrayList<NewnoticeBoard_Bean> getBankNoticeList(int nStartPage, int list_num){
			return this.boardMapper.getBankNoticeList(nStartPage, list_num);
		}
		//상품별공지
		public int getProductNoticeCnt() {
			int nCnt=0;
			nCnt=this.boardMapper.getProductNoticeCnt();
			return nCnt;
		}
		//상품별공지 arraylist
		public ArrayList<NewnoticeBoard_Bean> getProductNoticeList(int nStartPage, int list_num){
			return this.boardMapper.getProductNoticeList(nStartPage, list_num);
		}
		//기타
		public int getEtcCnt() {
			int nCnt=0;
			nCnt=this.boardMapper.getEtcCnt();
			return nCnt;
		}
		//기타 arraylist
		public ArrayList<NewnoticeBoard_Bean> getEtcList(int nStartPage, int list_num){
			return this.boardMapper.getEtcList(nStartPage, list_num);
		}
	

	
	//조회수 올리기
	public void updateHits(int hits, int idx) {
		System.out.println("조회수 올라감");
		this.boardMapper.updateHits(hits, idx);
	}
	
	//글보기에 뿌릴 bean
	public NewnoticeBoard_Bean getSpecificRow(int idx) {
		return this.boardMapper.getSpecificRow(idx);//-getSpecificRow(@Param("id") String id): return id, subject, name, created_date, mail, memo, hits
	}
	
	//글 수정
	public void updateBoard(NewnoticeBoard_Bean boardBean) {
		boardMapper.updateBoard(boardBean.getIdx(), boardBean.getSubject(), 
				boardBean.getContent(), boardBean.getFilename(), boardBean.getFilesize());
	}
	
	//글 삭제
	public void deleteRow(int idx) {
		this.boardMapper.deleteSpecificRow(idx);
	}
	
	//검색한 글 수
	public int getTotalCntBySubject(String search) {
		int nCnt=0;
		nCnt=this.boardMapper.getTotalCntBySubject(search);
		return nCnt;
	}
	
	//검색하기 -> ArrayList로 목록
	public ArrayList<NewnoticeBoard_Bean> getSearchedList(int nStartPage, int list_num, String strSearchThis){
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
	public NewnoticeBoard_Bean stairBoard(int idx) {
		return this.boardMapper.stairBoard(idx);
	}
	public void updateGroupStep(int ref, int step) {
		this.boardMapper.updateGroupStep(ref, step);
	}
	
	
}
