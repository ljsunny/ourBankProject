package com.ourbank.app.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ourbank.app.bean.BestBoard_Bean;


@Repository
public interface BestBoard_Mapper {
	
	//글 목록
	final String BEST_SELECT =
			"select * from (select category, category_num, best_idx, id, subject, content, created_date, hits, depth, " 
					+"filename, ceil(rownum / 10) as page from "
					+"((select category, category_num, best_idx, id, subject, content, created_date, hits, filename, depth from review_board) union "
					+"(select category, category_num, best_idx, id, subject, content, created_date, hits, filename, depth from tlb_free_board) union "
					+"(select category, category_num, best_idx, id, subject, content, created_date, hits, filename, depth  from tlb_meeting_board) union " 
					+"(select category, category_num, best_idx, id, subject, content, created_date, hits, filename, depth from tlb_debate_board) union "
					+"(select category, category_num, best_idx, id, subject, content, created_date, hits, filename, depth from invest_board)) " 
				+"where rownum <=20 and hits is not null and depth=0 order by hits desc) "
				+"where page=1";
	@Select(BEST_SELECT)
	@Results(value = {
			@Result(property = "category", column = "category"),
			@Result(property = "category_num", column = "category_num"),
			@Result(property = "best_idx", column = "best_idx"),
			@Result(property = "id", column = "id"), 
			@Result(property = "subject", column = "subject"), 
			@Result(property = "content", column = "content"),
			@Result(property = "created_date", column = "created_date"),
			@Result(property = "hits", column = "hits"), 
			@Result(property = "depth", column = "depth"),
			@Result(property = "filename", column = "filename")
	})
	ArrayList<BestBoard_Bean>getList(@Param("page") int page,@Param("rowsPerPage") int rowsPerPage);
	
	//조회수 증가
	//자유게시판
	final String UPDATE_FREE_HITS=
			"update tlb_free_board set hits=#{hits}+1 where best_idx=#{best_idx}";
	@Update(UPDATE_FREE_HITS)
	void updateFreeHits(@Param("hits") int hits, @Param("best_idx") int best_idx);
	//모임방게시판
	final String UPDATE_MEETING_HITS=
			"update tlb_meeting_board set hits=#{hits}+1 where best_idx=#{best_idx}";
	@Update(UPDATE_MEETING_HITS)
	void updateMeetingHits(@Param("hits") int hits, @Param("best_idx") int best_idx);
	//토론방게시판
	final String UPDATE_DEBATE_HITS=
			"update tlb_debate_board set hits=#{hits}+1 where best_idx=#{best_idx}";
	@Update(UPDATE_DEBATE_HITS)
	void updateDebateHits(@Param("hits") int hits, @Param("best_idx") int best_idx);
	
	
	//글보기-글보기에 뿌릴 bean 가져오기: + category 가져오기
	final String SELECT_BY_ID=
			"select * from ("
					+"(select category, category_num, best_idx, id, subject, content, created_date, hits, filename from review_board) union "
					+"(select category, category_num, best_idx, id, subject, content, created_date, hits, filename from TLB_free_BOARD) union " 
					+"(select category, category_num, best_idx, id, subject, content, created_date, hits, filename from tlb_meeting_board) union "
					+"(select category, category_num, best_idx, id, subject, content, created_date, hits, filename from tlb_debate_board) union "
					+"(select category, category_num, best_idx, id, subject, content, created_date, hits, filename from invest_board)) "
					+"where best_idx=#{best_idx}";
	@Select(SELECT_BY_ID)
	//				output
	@Results(value= {
			@Result(property = "category",column="category"),
			@Result(property = "category_num", column = "category_num"),
			@Result(property = "best_idx",column="best_idx"),
			@Result(property="subject",column="subject"),
			@Result(property = "id",column="id"),
			@Result(property="created_date",column="created_date"),
			@Result(property="content",column="content"),
			@Result(property = "hits", column="hits"),
			@Result(property = "filename", column="filename")
	})
	//						input
	BestBoard_Bean getSpecificRow(@Param("best_idx") int best_idx);
	
	

}
