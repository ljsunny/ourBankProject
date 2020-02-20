package com.ourbank.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ourbank.app.bean.BestBoard_Bean;
import com.ourbank.app.mapper.BestBoard_Mapper;

@Component
public class BestBoard_Service {
	
	@Autowired 
	BestBoard_Mapper boardMapper;

	//인덱스, 제목, 작성자, 날짜, 내용, 조회수  arraylist 얻어옴
	public ArrayList<BestBoard_Bean> getList(int nStartPage, int list_num) {
		return this.boardMapper.getList(nStartPage, list_num);
	}
	
	//조회수 올리기
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
	
	
	//글보기에 뿌릴 bean
	public BestBoard_Bean getSpecificRow(int best_idx) {
		return this.boardMapper.getSpecificRow(best_idx);
	}
	
	//글판단 카테고리 
	//public BestBoard_Bean getCategoryNum(int category) {
	//	return this.boardMapper.getCategoryNum(category);
	//}
	
	
	

}
