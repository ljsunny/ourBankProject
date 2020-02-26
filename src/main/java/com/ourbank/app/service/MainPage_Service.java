package com.ourbank.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ourbank.app.bean.BestBoard_Bean;
import com.ourbank.app.bean.DepositBoard_Bean;
import com.ourbank.app.bean.FreeBoard_Bean;
import com.ourbank.app.bean.NewnoticeBoard_Bean;
import com.ourbank.app.mapper.MainPage_Mapper;

@Component
public class MainPage_Service {

	@Autowired
	private MainPage_Mapper boardMapper;
	
	public ArrayList<NewnoticeBoard_Bean> selectNewNotice() {
		
		return this.boardMapper.selectNotice();
	}
	
	
	public ArrayList<BestBoard_Bean> selectBest(){
		return this.boardMapper.selectBest();
	}
	//검색한 글 수
		public int getTotalCntBySubject(String search) {
			int nCnt = 0;
			nCnt = this.boardMapper.getTotalCntBySubject(search);
			return nCnt;
		}

		//검색하기 -> 글목록 ArrayList
		public ArrayList<FreeBoard_Bean> getSearchedList(int nStartPage, int list_num, String strSearchThis) {
			return this.boardMapper.getSearchedList(nStartPage, list_num, strSearchThis);
		}

		//검색하기 - 글보기
		public FreeBoard_Bean getSpecificRow(int board_idx) {
			return this.boardMapper.getSpecificRow(board_idx);
		}
		
		////조회수 올리기
		//리뷰게시판
		public void updateReviewHits(int hits, int best_idx) {
			System.out.println("조회수 올라감");
			this.boardMapper.updateReviewHits(hits, best_idx);
		}
		//자유게시판 
		public void updateFreeHits(int hits, int best_idx) {
			System.out.println("자유게시판 조회수 올라감");
			this.boardMapper.updateFreeHits(hits, best_idx);
		}
		//모임방게시판 
		public void updateMeetingHits(int hits, int best_idx) {
			System.out.println(" 모임방게시판  조회수 올라감");
			this.boardMapper.updateMeetingHits(hits, best_idx);
		}
		//토론방게시판 
		public void updateDebateHits(int hits, int best_idx) {
			System.out.println(" 토론방게시판  조회수 올라감");
			this.boardMapper.updateDebateHits(hits, best_idx);
		}
		//제테크노하우게시판 
		public void updateInvestHits(int hits, int best_idx) {
			System.out.println("제테크노하우게시판  조회수 올라감");
			this.boardMapper.updateInvestHits(hits, best_idx);
		}
		//QnA게시판 
		public void updateQnaHits(int hits, int best_idx) {
			System.out.println("QnA게시판 조회수 올라감");
			this.boardMapper.updateQnaHits(hits, best_idx);
		}
		//FAQ게시판 
		public void updateFaqHits(int hits, int best_idx) {
			System.out.println("FAQ게시판  조회수 올라감");
			this.boardMapper.updateFaqHits(hits, best_idx);
		}
		//관련뉴스게시판 
		public void updateNewsHits(int hits, int best_idx) {
			System.out.println("관련뉴스게시판 조회수 올라감");
			this.boardMapper.updateNewstHits(hits, best_idx);
		}
		//공지사항게시판 
		public void updateNewnoticeHits(int hits, int best_idx) {
			System.out.println("공지사항게시판  조회수 올라감");
			this.boardMapper.updateNewnoticeHits(hits, best_idx);
		}
		
	public DepositBoard_Bean bestDeposit() {
		return this.boardMapper.bestDeposit();
	}
}
