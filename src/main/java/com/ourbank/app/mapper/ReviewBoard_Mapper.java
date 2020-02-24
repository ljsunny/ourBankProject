package com.ourbank.app.mapper;


import java.util.ArrayList;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.ourbank.app.bean.ReviewBoard_Bean;

@Repository
public interface ReviewBoard_Mapper {
	//글입력처리
		final String INSERT=
				"insert into review_board(board_idx, best_idx, id_x, id, subject, created_date, review_case, "
				+"content, filename, filesize, step, ref, depth, satisfaction, banks, re_productname) "
				+"values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,#{id},#{subject},SYSDATE, #{review_case}, " 
				+"#{content},#{filename},#{filesize}, #{step}, #{ref}, #{depth}, #{satisfaction}, #{banks}, #{re_productname})";		
		@Insert(INSERT)
		void insertBoard(ReviewBoard_Bean boardBean);
		
		//전체 글개수
		final String SELECT_CNT_ALL="select count(1) from review_board";
		@Select(SELECT_CNT_ALL)
		int getTotalCnt();
		
		//조회수 증가
		final String UPDATE_HITS="update review_board set hits=#{hits}+1 where id_x=#{idx}";
		@Update(UPDATE_HITS)
		void updateHits(@Param("hits") int hits, @Param("idx") int idx);
		
		//리스트 뿌리기
		final String SELECT_PAGE=
				"SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE, CONTENT, HITS, "
				+"step, ref, depth, ceil(rownum/ #{rowsPerPage}) as page "
				+"FROM (select * from review_board ORDER BY ref desc, step asc)) "
				+"WHERE page=#{page}" ;
		
		@Select(SELECT_PAGE)
		@Results(value= {
				@Result(property="idx",column="id_x"),
				@Result(property = "subject",column = "subject"),
				@Result(property = "id", column = "id"),
				@Result(property = "created_date",column = "created_date"),
				@Result(property = "content",column = "content"),
				@Result(property = "hits",column = "hits"),
				@Result(property = "step", column = "step"),
				@Result(property = "ref", column = "ref"), 
				@Result(property = "depth", column = "depth")
		})
		ArrayList<ReviewBoard_Bean>getList(@Param("page") int page,
				@Param("rowsPerPage") int rowsPerPage);
		
	// 예금리뷰 글개수
	final String SELECT_SIGN_UP_CNT = 
			"select count(1) from review_board where review_case='예금리뷰'";
	@Select(SELECT_SIGN_UP_CNT)
	int getSignUpCnt();
	// 예금리뷰 뿌리기
	final String SELECT_SiGN_UP_PAGE = 
			"SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE, "
			+"CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page " 
			+"FROM review_BOARD WHERE review_case='예금리뷰' "
			+"ORDER BY CREATED_DATE DESC) WHERE page=#{page}";
	@Select(SELECT_SiGN_UP_PAGE)
	@Results(value = { 
			@Result(property = "idx", column = "id_x"), 
			@Result(property = "subject", column = "subject"),
			@Result(property = "id", column = "id"), 
			@Result(property = "created_date", column = "created_date"),
			@Result(property = "content", column = "content"),
			@Result(property = "hits", column = "hits") })
	ArrayList<ReviewBoard_Bean> getSignUpList(@Param("page") int page, @Param("rowsPerPage") int rowsPerPage);

	// 적금리뷰 글개수
	final String SELECT_SAVINGS_CNT = 
			"select count(1) from review_board where review_case='적금리뷰'";
	@Select(SELECT_SAVINGS_CNT)
	int getSavingsCnt();

	// 적금리뷰 리스트 뿌리기
	final String SELECT_SAVINGS_PAGE = 
			"SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
			+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page" 
			+ " FROM review_BOARD WHERE review_case='적금리뷰' "
			+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";
	@Select(SELECT_SAVINGS_PAGE)
	@Results(value = { 
			@Result(property = "idx", column = "id_x"), 
			@Result(property = "subject", column = "subject"),
			@Result(property = "id", column = "id"), 
			@Result(property = "created_date", column = "created_date"),
			@Result(property = "content", column = "content"), 
			@Result(property = "hits", column = "hits") })
	ArrayList<ReviewBoard_Bean> getSavingsList(@Param("page") int page, @Param("rowsPerPage") int rowsPerPage);

	// 기타리뷰 글개수
		final String SELECT_ETC_CNT = 
				"select count(1) from review_board where review_case='기타리뷰'";
		@Select(SELECT_ETC_CNT)
		int getEtcCnt();
	// 기타리뷰 리스트 뿌리기
		final String SELECT_ETC_PAGE = 
				"SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
				+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page" 
				+ " FROM review_BOARD WHERE review_case='기타리뷰' "
				+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";
		@Select(SELECT_ETC_PAGE)
		@Results(value = { 
				@Result(property = "idx", column = "id_x"), 
				@Result(property = "subject", column = "subject"),
				@Result(property = "id", column = "id"),
				@Result(property = "created_date", column = "created_date"),
				@Result(property = "content", column = "content"), 
				@Result(property = "hits", column = "hits") })
		ArrayList<ReviewBoard_Bean> getEtcList(@Param("page") int page, @Param("rowsPerPage") int rowsPerPage);

		//글보기-글보기에 뿌릴 bean 가져오기: idx, subject, id, created_date, content, hits, filename
		final String SELECT_BY_ID="select id_x,subject, id, created_date, content, hits, filename, "
				+"review_case, satisfaction, banks, re_productname, depth "
				+"from review_board where id_x=#{idx}";
		
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
				@Result(property = "review_case", column="review_case"),
				@Result(property = "satisfaction", column="satisfaction"),
				@Result(property = "banks", column="banks"),
				@Result(property = "re_productname", column="re_productname"),
				@Result(property = "depth", column="depth")
		})
		//						input
		ReviewBoard_Bean getSpecificRow(@Param("idx") int idx);
		
		// 수정
		final String UPDATE_BY_ID=
				"update review_board set subject = #{subject},review_case=#{review_case}, "
				+"content=#{content}, satisfaction=#{satisfaction}, banks=#{banks}, "
						+"re_productname=#{re_productname} where id_x=#{idx}";
		@Update(UPDATE_BY_ID)
		void updateBoard(@Param("idx") int idx, @Param("subject") String subject,
				@Param("review_case") String review_case, @Param("content") String content,
				@Param("satisfaction") String satisfaction, @Param("banks") String banks, 
				@Param("re_productname") String re_productname);
		
		//글 삭제
		final String DELETE_BY_ID="delete from review_board where id_x=#{idx}";
		
		@Delete(DELETE_BY_ID)
		void deleteSpecificRow(@Param("idx") int idx);
		
		// 검색한 글 수
		final String SELECT_CNT_BY_SUBJECT="select count(1) from review_board where "
				+ " subject like '%'||'${searchThis}'||'%'";
		
		@Select(SELECT_CNT_BY_SUBJECT)
		int getTotalCntBySubject(@Param("searchThis") String includingThis);

		//글검색
		final String SELECT_ROWS_BY_SUBJECT=
			"SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
						+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page"
						+ " FROM review_BOARD WHERE SUBJECT LIKE '%'||'${likeThis}'||'%' "
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
		public ArrayList<ReviewBoard_Bean> getSearchedList(
				@Param("page") int page,
				@Param("rowsPerPage") int rowsPerPage,
				@Param("likeThis") String likeThis);
		
		
		
		////////////////////////답변형 게시판/////////////////////////////
		//idx_num값을 업데이트 합니다.
		final String UPDATE_RE_WRITE=
				"update review_board set ref=#{idx} where id_x=#{idx}";
		@Select(UPDATE_RE_WRITE)
		void updateRewrite(@Param("idx") int idx);
		
		//가장 최근글을 가져옵니다.
		final String RECENT_ID=
				"select max(id_x) from review_board";
		@Select(RECENT_ID)
		int recentID();

		// 아이디로 id, ref,step,depth,subject를 출력합니다.
		final String SELECT_STAIR_BOARD=
				"select id_x, ref, step, depth, subject from review_board where id_x=#{idx}";
		@Select(SELECT_STAIR_BOARD)
		ReviewBoard_Bean stairBoard(@Param("idx") int idx);
		
		//그룹 step 을 증가시키고 현재 스탭보다 큰 애들 다 +1해줍니다.
		final String UPDATE_GROUP_STEP=
				"update review_board set step=step+1 where ref=#{ref} and step>#{step}";
		@Update(UPDATE_GROUP_STEP)
		void updateGroupStep(@Param("ref") int ref, @Param("step") int step);
		
		//답글-원글의 일부 값들 가져오기
		//선택항목가져오기
		final String SELECT_REVIEW_CASE =
				"select review_case from review_board where ref = #{ref} and depth=0";
		@Select(SELECT_REVIEW_CASE)
		String ReviewCase(int ref);
		
		final String SELECT_SATISFACTION =
				"select satisfaction from review_board where ref = #{ref} and depth=0";
		@Select(SELECT_SATISFACTION)
		String Satisfaction(int ref);
		
		final String SELECT_BANKS =
				"select banks from review_board where ref = #{ref} and depth=0";
		@Select(SELECT_BANKS)
		String Banks(int ref);
		
		final String SELECT_RE_PRODUCTNAME =
				"select re_productname from review_board where ref = #{ref} and depth=0";
		@Select(SELECT_RE_PRODUCTNAME)
		String Re_productname(int ref);
		



		
	
		
}
