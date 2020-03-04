package com.ourbank.app.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ourbank.app.bean.BestBoard_Bean;
import com.ourbank.app.bean.DepositBoard_Bean;
import com.ourbank.app.bean.FreeBoard_Bean;
import com.ourbank.app.bean.NewnoticeBoard_Bean;
import com.ourbank.app.bean.NewsBoard_Bean;

@Repository
public interface MainPage_Mapper {
	final String SELECT_NOTICE_NEW="select idx, substr(subject,6,16) subject, to_char(created_date,'YYYY-MM-DD') created_date "
			+ " from (select * from newnotice_board order by created_date desc)"
			+ " where rownum<=5 ";
	
	@Select(SELECT_NOTICE_NEW)
	@Results(value= {
			@Result(property = "idx",column = "idx"),
			@Result(property = "subject",column = "subject"),
			@Result(property = "created_date" , column = "created_date")
	})
	public ArrayList<NewnoticeBoard_Bean> selectNotice();
	
	final String BEST_HITS_COMMUNITY=
			"select category, category_num, best_idx, id, subject, to_char(created_date,'YYYY-MM-DD') created_date, hits " 
			+"from (select * from commuboardview order by hits desc)"
			+"where rownum <=5 and hits is not null ";
	@Select(BEST_HITS_COMMUNITY)
	@Results(value = {
			@Result(property = "category",column = "category"),
			@Result(property = "category_num", column = "category_num"),
			@Result(property = "best_idx",column="best_idx"),
			@Result(property = "id", column="id"),
			@Result(property = "subject",column = "subject"),
			@Result(property = "created_date", column = "created_date"),
			@Result(property = "hits" , column = "hits")
	})
	public ArrayList<BestBoard_Bean> selectBest();
	
	 //검색한 글 수
	   final String SELECT_CNT_BY_SUBJECT="select count(1) from View_Full_Board where "
	   + " subject like '%'||'${searchThis}'||'%' "
	   + "or content like '%'||'${searchThis}'||'%'";
	   
	   @Select(SELECT_CNT_BY_SUBJECT)
	   int getTotalCntBySubject(@Param("searchThis") String includingThis);

	   //글검색 -> 리스트로
	   final String SELECT_ROWS_BY_SEARCH =
	         "select * from (select board_idx, category_num, id, subject, content, "
	         +"to_char(created_date,'YYYY-MM-DD')  created_date, hits, ceil(rownum / #{rowsPerPage}) as page "
	         +"from View_Full_Board where subject like '%'||'${searchThis}'||'%' "
	         +"or content like '%'||'${searchThis}'||'%' order by created_date desc) "
	         +"where page=#{page}";
	   @Select(SELECT_ROWS_BY_SEARCH)
	   @Results(value= {
	         @Result(property="board_idx", column="board_idx"),
	         @Result(property ="category_num", column="category_num"),
	         @Result(property="id", column="id"),
	         @Result(property = "subject",column = "subject"),
	         @Result(property = "created_date",column="created_date"),
	         @Result(property = "hits",column = "hits")
	   })
	   public ArrayList<FreeBoard_Bean> getSearchedList(
	         @Param("page") int page,
	         @Param("rowsPerPage") int rowsPerPage,
	         @Param("searchThis") String searchThis);
	   
	   //글검색 - 글보기
	   final String SELECT_SEARCH_VIEW =
	         "select * from View_Full_Board where board_idx = #{board_idx}";
	   @Select(SELECT_SEARCH_VIEW)
	   @Results(value = {
	         @Result(property = "board_idx", column = "board_idx"),
	         @Result(property = "category_num", column = "category_num"),
	         @Result(property = "id", column = "id"),
	         @Result(property = "subject", column = "subject"),
	         @Result(property = "content", column = "content"),
	         @Result(property = "filename", column = "filename"),
	         @Result(property = "filesize", column = "filesize"),
	         @Result(property = "hits", column = "hits")
	         
	   })
	   public FreeBoard_Bean getSpecificRow(@Param("board_idx") int board_idx);
	   
	   ////조회수 증가
	   //리뷰게시판
	   final String UPDATE_REVIEW_HITS=
	         "update review_board set hits=#{hits}+1 where board_idx=#{board_idx}";
	   @Update(UPDATE_REVIEW_HITS)
	   void updateReviewHits(@Param("hits") int hits, @Param("board_idx") int board_idx);
	   //자유게시판
	   final String UPDATE_FREE_HITS=
	         "update tlb_free_board set hits=#{hits}+1 where board_idx=#{board_idx}";
	   @Update(UPDATE_FREE_HITS)
	   void updateFreeHits(@Param("hits") int hits, @Param("board_idx") int board_idx);
	   //모임방게시판
	   final String UPDATE_MEETING_HITS=
	         "update tlb_meeting_board set hits=#{hits}+1 where board_idx=#{board_idx}";
	   @Update(UPDATE_MEETING_HITS)
	   void updateMeetingHits(@Param("hits") int hits, @Param("board_idx") int board_idx);
	   //토론방게시판
	   final String UPDATE_DEBATE_HITS=
	         "update tlb_debate_board set hits=#{hits}+1 where board_idx=#{board_idx}";
	   @Update(UPDATE_DEBATE_HITS)
	   void updateDebateHits(@Param("hits") int hits, @Param("board_idx") int board_idx);
	   //재테크노하우게시판
	   final String UPDATE_INVEST_HITS=
	         "update invest_board set hits=#{hits}+1 where board_idx=#{board_idx}";
	   @Update(UPDATE_INVEST_HITS)
	   void updateInvestHits(@Param("hits") int hits, @Param("board_idx") int board_idx);
	   //Qna게시판
	   final String UPDATE_QNA_HITS=
	         "update qna_board set hits=#{hits}+1 where board_idx=#{board_idx}";
	   @Update(UPDATE_QNA_HITS)
	   void updateQnaHits(@Param("hits") int hits, @Param("board_idx") int board_idx);
	   ////FAQ게시판
	   final String UPDATE_FAQ_HITS=
	         "update faq_board set hits=#{hits}+1 where board_idx=#{board_idx}";
	   @Update(UPDATE_FAQ_HITS)
	   void updateFaqHits(@Param("hits") int hits, @Param("board_idx") int board_idx);
	   //관련뉴스게시판
	   final String UPDATE_NEWS_HITS=
	         "update news_board set hits=#{hits}+1 where board_idx=#{board_idx}";
	   @Update(UPDATE_NEWS_HITS)
	   void updateNewstHits(@Param("hits") int hits, @Param("board_idx") int board_idx);
	   //공지사항게시판
	   final String UPDATE_NEWNOTICE_HITS=
	         "update newnotice_board set hits=#{hits}+1 where board_idx=#{board_idx}";
	   @Update(UPDATE_QNA_HITS)
	   void updateNewnoticeHits(@Param("hits") int hits, @Param("board_idx") int board_idx);
	
	
	final String BEST_DEPOSIT="select * "
								+ " from "
								+ " (select kor_co_nm, fin_prdt_cd, fin_prdt_nm, intr_rate2 from tlb_deposit_board"
								+ " order by intr_rate2 desc)"
								+ " where rownum=1";
	@Select(BEST_DEPOSIT)
	@Results(value= {
			@Result(property ="kor_co_nm", column = "kor_co_nm" ),
			@Result(property ="fin_prdt_cd",column = "fin_prdt_cd" ),
			@Result(property = "fin_prdt_nm", column = "fin_prdt_nm"),
			@Result(property = "intr_rate2",column = "intr_rate2")
	})
	public DepositBoard_Bean bestDeposit();
	

}

