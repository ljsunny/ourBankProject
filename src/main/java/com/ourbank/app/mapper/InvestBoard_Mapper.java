package com.ourbank.app.mapper;


import java.util.ArrayList;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.ourbank.app.bean.InvestBoard_Bean;

@Repository
public interface InvestBoard_Mapper {
	//글입력처리
		final String INSERT=
				"insert into invest_board(board_idx, best_idx, id_x, id, subject, created_date, invest_case, "
				+"content, filename, filesize, step, ref, depth) "
				+"values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,#{id},#{subject},SYSDATE, #{invest_case}, " 
				+"#{content},#{filename},#{filesize}, #{step}, #{ref}, #{depth})";	
		@Insert(INSERT)
		void insertBoard(InvestBoard_Bean boardBean);
		
		//전체 글개수
		final String SELECT_CNT_ALL="select count(1) from invest_board";
		
		@Select(SELECT_CNT_ALL)
		int getTotalCnt();
		
		//조회수 증가
		final String UPDATE_HITS="update invest_board set hits=#{hits}+1 where id_x=#{idx}";
		
		@Update(UPDATE_HITS)
		void updateHits(@Param("hits") int hits, @Param("idx") int idx);
		
		//리스트 뿌리기
		final String SELECT_PAGE=
				"SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE, CONTENT, HITS, "
				+"step, ref, depth, ceil(rownum/ #{rowsPerPage}) as page "
				+"FROM (select * from invest_board ORDER BY ref desc, step asc)) "
				+"WHERE page=#{page}" ;
		
		@Select(SELECT_PAGE)
		@Results(value= {
				@Result(property="idx",column="id_x"),
				@Result(property = "id", column = "id"),
				@Result(property = "subject",column = "subject"),
				@Result(property = "content",column = "content"),
				@Result(property = "created_date",column = "created_date"),
				@Result(property = "hits",column = "hits"),
				@Result(property = "step", column = "step"),
				@Result(property = "ref", column = "ref"), 
				@Result(property = "depth", column = "depth")
		})
		ArrayList<InvestBoard_Bean>getList(@Param("page") int page,
				@Param("rowsPerPage") int rowsPerPage);
	
	//항목별 리스트 목록 뿌리기
	// 성공사례 글개수
	final String SELECT_SUCCESS_CNT = 
			"select count(1) from invest_board where invest_case='성공사례'";
	@Select(SELECT_SUCCESS_CNT)
	int getSuccessCnt();
	// 성공사례 리스트 뿌리기
	final String SELECT_SUCCESS_PAGE = 
			"SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
			+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page" 
			+ " FROM invest_BOARD WHERE invest_case='성공사례' "
			+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";
	@Select(SELECT_SUCCESS_PAGE)
	@Results(value = { 
			@Result(property = "idx", column = "id_x"), 
			@Result(property = "subject", column = "subject"),
			@Result(property = "id", column = "id"), 
			@Result(property = "created_date", column = "created_date"),
			@Result(property = "content", column = "content"), 
			@Result(property = "hits", column = "hits") })
	ArrayList<InvestBoard_Bean> getSuccessList(@Param("page") int page, @Param("rowsPerPage") int rowsPerPage);

	// 실폐사례 글 개수
	final String SELECT_FAIL_CNT = "select count(1) from invest_board where invest_case='실패사례'";
	@Select(SELECT_FAIL_CNT)
	int getFailCnt();
	// 실폐사례 리스트 뿌리기
	final String SELECT_FAIL_PAGE = 
			"SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
			+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page" 
			+ " FROM invest_BOARD WHERE invest_case='실패사례' "
			+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";
	@Select(SELECT_FAIL_PAGE)
	@Results(value = { 
			@Result(property = "idx", column = "id_x"), 
			@Result(property = "subject", column = "subject"),
			@Result(property = "id", column = "id"), 
			@Result(property = "created_date", column = "created_date"),
			@Result(property = "content", column = "content"), 
			@Result(property = "hits", column = "hits") })
	ArrayList<InvestBoard_Bean> getFailList(@Param("page") int page, @Param("rowsPerPage") int rowsPerPage);

	// 기타 글 개수
		final String SELECT_ETC_CNT = 
				"select count(1) from invest_board where invest_case='기타'";
		@Select(SELECT_ETC_CNT)
		int getEtcCnt();
		// 기타 리스트 뿌리기
		final String SELECT_ETC_PAGE = 
				"SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
				+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page" 
				+ " FROM invest_BOARD WHERE invest_case='기타' "
				+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";
		@Select(SELECT_ETC_PAGE)
		@Results(value = { 
				@Result(property = "idx", column = "id_x"), 
				@Result(property = "subject", column = "subject"),
				@Result(property = "id", column = "id"), 
				@Result(property = "created_date", column = "created_date"),
				@Result(property = "content", column = "content"), 
				@Result(property = "hits", column = "hits") })
		ArrayList<InvestBoard_Bean> getEtcList(@Param("page") int page, @Param("rowsPerPage") int rowsPerPage);

		//글보기-글보기에 뿌릴 bean 가져오기: idx, subject, id, created_date, content, hits, filename
		final String SELECT_BY_ID="select id_x,subject, id, created_date, content, hits, filename, invest_case, depth"
				+ " from invest_board where id_x=#{idx}";
		
		@Select(SELECT_BY_ID)
		//				output
		@Results(value= {
				@Result(property = "idx",column="id_x"),
				@Result(property="subject",column="subject"),
				@Result(property = "id",column="id"),
				@Result(property="created_date",column="created_date"),
				@Result(property="content",column="content"),
				@Result(property = "hits", column="hits"),
				@Result(property = "filename", column="filename"),
				@Result(property = "invest_case", column="invest_case"),
				@Result(property = "depth", column="depth")
		})
		//						input
		InvestBoard_Bean getSpecificRow(@Param("idx") int idx);
		
		// 수정
		final String UPDATE_BY_ID="update invest_board set subject = #{subject}, invest_case=#{invest_case}, "
				+ " content=#{content} where id_x=#{idx}";
		@Update(UPDATE_BY_ID)
		void updateBoard(@Param("idx") int idx, @Param("subject") String subject,
				@Param("invest_case") String invest_case, @Param("content") String content);
		
		//글 삭제
		final String DELETE_BY_ID="delete from invest_board where id_x=#{idx}";
		
		@Delete(DELETE_BY_ID)
		void deleteSpecificRow(@Param("idx") int idx);
		
		// 검색한 글 수
		final String SELECT_CNT_BY_SUBJECT="select count(1) from invest_board where "
				+ " subject like '%'||'${searchThis}'||'%'";
		
		@Select(SELECT_CNT_BY_SUBJECT)
		int getTotalCntBySubject(@Param("searchThis") String includingThis);

		//글검색
		final String SELECT_ROWS_BY_SUBJECT=
			"SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
						+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page"
						+ " FROM invest_BOARD WHERE SUBJECT LIKE '%'||'${likeThis}'||'%' "
						+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";
				
		@Select(SELECT_ROWS_BY_SUBJECT)
		@Results(value= {
				@Result(property="idx",column="id_x"),
				@Result(property ="subject", column="subject"),
				@Result(property="id", column="id"),
				@Result(property = "created_date",column = "created_date"),
				@Result(property = "content",column="content"),
				@Result(property = "hits",column = "hits")
		})
		public ArrayList<InvestBoard_Bean> getSearchedList(
				@Param("page") int page,
				@Param("rowsPerPage") int rowsPerPage,
				@Param("likeThis") String likeThis);
		
		
		//////////////////////답변형 게시판/////////////////////////////
		//idx_num값을 업데이트 합니다.
		final String UPDATE_RE_WRITE=
				"update invest_board set ref=#{idx} where id_x=#{idx}";
		@Select(UPDATE_RE_WRITE)
		void updateRewrite(@Param("idx") int idx);
		
		//가장 최근글을 가져옵니다.
		final String RECENT_ID=
				"select max(id_x) from invest_board";
		@Select(RECENT_ID)
		int recentID();

		// 아이디로 id, ref,step,depth,subject를 출력합니다.
		final String SELECT_STAIR_BOARD=
				"select id_x, ref, step, depth, subject, invest_case from invest_board where id_x=#{idx}";
		@Select(SELECT_STAIR_BOARD)
		InvestBoard_Bean stairBoard(@Param("idx") int idx);
		
		//그룹 step 을 증가시키고 현재 스탭보다 큰 애들 다 +1해줍니다.
		final String UPDATE_GROUP_STEP=
				"update invest_board set step=step+1 where ref=#{ref} and step>#{step}";
		@Update(UPDATE_GROUP_STEP)
		void updateGroupStep(@Param("ref") int ref, @Param("step") int step);
		
		//
		final String SELECT_INVEST_CASE = 
				"select invest_case from invest_board where ref = #{ref}";
		@Select(SELECT_INVEST_CASE)
		String InvestCase(int ref);
		
		 
	
		
}
