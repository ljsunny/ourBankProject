package com.ourbank.app.mapper;


import java.util.ArrayList;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.ourbank.app.bean.NewsBoard_Bean;

@Repository
public interface NewsBoard_Mapper {
	//글입력처리
		final String INSERT=
				"insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, "
				+"content, filename, filesize, step, ref, depth) "
				+"values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval,#{id},#{subject},SYSDATE, #{news_case}, " 
				+"#{content},#{filename},#{filesize}, #{step}, #{ref}, #{depth})";		
		@Insert(INSERT)
		void insertBoard(NewsBoard_Bean boardBean);
		
		//전체 글개수
		final String SELECT_CNT_ALL="select count(1) from tlb_news_board";
		@Select(SELECT_CNT_ALL)
		int getTotalCnt();
		
		//조회수 증가
		final String UPDATE_HITS="update tlb_news_board set hits=#{hits}+1 where idx_num=#{idx}";
		@Update(UPDATE_HITS)
		void updateHits(@Param("hits") int hits, @Param("idx") int idx);
		
		//리스트 뿌리기
		final String SELECT_PAGE=
				"SELECT * FROM (SELECT idx_num, SUBJECT, ID, CREATED_DATE, CONTENT, HITS, "
				+"step, ref, depth, ceil(rownum/ #{rowsPerPage}) as page "
				+"FROM (select * from tlb_news_board ORDER BY ref desc, step asc)) "
				+"WHERE page=#{page}" ;
		
		@Select(SELECT_PAGE)
		@Results(value= {
				@Result(property="idx",column="idx_num"),
				@Result(property = "subject",column = "subject"),
				@Result(property = "id", column = "id"),
				@Result(property = "created_date",column = "created_date"),
				@Result(property = "content",column = "content"),
				@Result(property = "hits",column = "hits"),
				@Result(property = "step", column = "step"),
				@Result(property = "ref", column = "ref"), 
				@Result(property = "depth", column = "depth")
		})
		ArrayList<NewsBoard_Bean>getList(@Param("page") int page,
				@Param("rowsPerPage") int rowsPerPage);
		
	// 금융회사정보 글개수
	final String SELECT_FINANCE_CNT = 
			"select count(1) from tlb_news_board where news_case='금융회사정보'";
	@Select(SELECT_FINANCE_CNT)
	int getSignUpCnt();
	// 금융회사정보 뿌리기
	final String SELECT_FINANCE_PAGE = 
			"SELECT * FROM (SELECT idx_num, SUBJECT, ID, CREATED_DATE, "
			+"CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page " 
			+"FROM tlb_news_board WHERE news_case='금융회사정보' "
			+"ORDER BY CREATED_DATE DESC) WHERE page=#{page}";
	@Select(SELECT_FINANCE_PAGE)
	@Results(value = { 
			@Result(property = "idx", column = "idx_num"), 
			@Result(property = "subject", column = "subject"),
			@Result(property = "id", column = "id"), 
			@Result(property = "created_date", column = "created_date"),
			@Result(property = "content", column = "content"),
			@Result(property = "hits", column = "hits") })
	ArrayList<NewsBoard_Bean> getSignUpList(@Param("page") int page, @Param("rowsPerPage") int rowsPerPage);

	// 관련뉴스글개수
	final String SELECT_RELATED_CNT = 
			"select count(1) from tlb_news_board where news_case='관련뉴스'";
	@Select(SELECT_RELATED_CNT)
	int getSavingsCnt();

	// 관련뉴스 리스트 뿌리기
	final String SELECT_RELATED_PAGE = 
			"SELECT * FROM (SELECT idx_num, SUBJECT, ID, CREATED_DATE,"
			+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page" 
			+ " FROM tlb_news_board WHERE news_case='관련뉴스' "
			+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";
	@Select(SELECT_RELATED_PAGE)
	@Results(value = { 
			@Result(property = "idx", column = "idx_num"), 
			@Result(property = "subject", column = "subject"),
			@Result(property = "id", column = "id"), 
			@Result(property = "created_date", column = "created_date"),
			@Result(property = "content", column = "content"), 
			@Result(property = "hits", column = "hits") })
	ArrayList<NewsBoard_Bean> getSavingsList(@Param("page") int page, @Param("rowsPerPage") int rowsPerPage);

	/* 기타리뷰 글개수
		final String SELECT_ETC_CNT = 
				"select count(1) from tlb_news_board where news_case='기타리뷰'";
		@Select(SELECT_ETC_CNT)
		int getEtcCnt();
	// 기타리뷰 리스트 뿌리기
		final String SELECT_ETC_PAGE = 
				"SELECT * FROM (SELECT idx_num, SUBJECT, ID, CREATED_DATE,"
				+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page" 
				+ " FROM tlb_news_board WHERE news_case='기타리뷰' "
				+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";
		@Select(SELECT_ETC_PAGE)
		@Results(value = { 
				@Result(property = "idx", column = "idx_num"), 
				@Result(property = "subject", column = "subject"),
				@Result(property = "id", column = "id"),
				@Result(property = "created_date", column = "created_date"),
				@Result(property = "content", column = "content"), 
				@Result(property = "hits", column = "hits") })
		ArrayList<NewsBoard_Bean> getEtcList(@Param("page") int page, @Param("rowsPerPage") int rowsPerPage);
		*/

		//글보기-글보기에 뿌릴 bean 가져오기: idx, subject, id, created_date, content, hits, filename
		final String SELECT_BY_ID="select idx_num,subject, id, created_date, content, hits, filename, "
				+"news_case, depth "
				+"from tlb_news_board where idx_num=#{idx}";
		
		@Select(SELECT_BY_ID)
		//				output
		@Results(value= {
				@Result(property = "idx",column="idx_num"),
				@Result(property="subject",column="subject"),
				@Result(property = "id",column="id"),
				@Result(property="created_date",column="created_date"),
				@Result(property="content",column="content"),
				@Result(property = "hits", column="hits"),
				@Result(property = "filename", column="filename"),
				@Result(property = "news_case", column="news_case"),
				@Result(property = "depth", column="depth")
		})
		//						input
		NewsBoard_Bean getSpecificRow(@Param("idx") int idx);
		
		// 수정
		final String UPDATE_BY_ID=
				"update tlb_news_board set subject = #{subject},news_case=#{news_case}, "
				+"content=#{content} where idx_num=#{idx}";
		@Update(UPDATE_BY_ID)
		void updateBoard(@Param("idx") int idx, @Param("subject") String subject,
				@Param("news_case") String news_case, @Param("content") String content);
		
		//글 삭제
		final String DELETE_BY_ID="delete from tlb_news_board where idx_num=#{idx}";
		
		@Delete(DELETE_BY_ID)
		void deleteSpecificRow(@Param("idx") int idx);
		
		// 검색한 글 수
		final String SELECT_CNT_BY_SUBJECT="select count(1) from tlb_news_board where "
				+ " subject like '%'||'${searchThis}'||'%'";
		
		@Select(SELECT_CNT_BY_SUBJECT)
		int getTotalCntBySubject(@Param("searchThis") String includingThis);

		//글검색
		final String SELECT_ROWS_BY_SUBJECT=
			"SELECT * FROM (SELECT idx_num, SUBJECT, ID, CREATED_DATE,"
						+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page"
						+ " FROM tlb_news_board WHERE SUBJECT LIKE '%'||'${likeThis}'||'%' "
						+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";
				
		@Select(SELECT_ROWS_BY_SUBJECT)
		@Results(value= {
				@Result(property="idx",column="idx_num"),
				@Result(property ="subject", column="subject"),
				@Result(property="id", column="id"),
				@Result(property = "created_date",column = "created_date"),
				@Result(property = "content",column="content"),
				@Result(property = "hits",column = "hits")
		})
		public ArrayList<NewsBoard_Bean> getSearchedList(
				@Param("page") int page,
				@Param("rowsPerPage") int rowsPerPage,
				@Param("likeThis") String likeThis);
		
		
		
		////////////////////////답변형 게시판/////////////////////////////
		//idx_num값을 업데이트 합니다.
		final String UPDATE_RE_WRITE=
				"update tlb_news_board set ref=#{idx} where idx_num=#{idx}";
		@Select(UPDATE_RE_WRITE)
		void updateRewrite(@Param("idx") int idx);
		
		//가장 최근글을 가져옵니다.
		final String RECENT_ID=
				"select max(idx_num) from tlb_news_board";
		@Select(RECENT_ID)
		int recentID();

		// 아이디로 id, ref,step,depth,subject를 출력합니다.
		final String SELECT_STAIR_BOARD=
				"select idx_num, ref, step, depth, subject from tlb_news_board where idx_num=#{idx}";
		@Select(SELECT_STAIR_BOARD)
		NewsBoard_Bean stairBoard(@Param("idx") int idx);
		
		//그룹 step 을 증가시키고 현재 스탭보다 큰 애들 다 +1해줍니다.
		final String UPDATE_GROUP_STEP=
				"update tlb_news_board set step=step+1 where ref=#{ref} and step>#{step}";
		@Update(UPDATE_GROUP_STEP)
		void updateGroupStep(@Param("ref") int ref, @Param("step") int step);
		
		//답글-원글의 일부 값들 가져오기
		//선택항목가져오기
		final String SELECT_news_case =
				"select news_case from tlb_news_board where ref = #{ref} and depth=0";
		@Select(SELECT_news_case)
		String ReviewCase(int ref);
		
		
		



		
	
		
}
