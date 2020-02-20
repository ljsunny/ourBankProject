package com.ourbank.app.mapper;

<<<<<<< HEAD
import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
=======

import java.util.ArrayList;

import org.apache.ibatis.annotations.*;
>>>>>>> f468c823fb38f81388f1f0cd521a2d8766679c8d
import org.springframework.stereotype.Repository;

import com.ourbank.app.bean.FreeBoard_Bean;

@Repository
public interface FreeBoard_Mapper {
<<<<<<< HEAD
	
	//글 입력 처리
	final String INSERT=
			"insert into tlb_free_board("
			+ "best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)"
			+ " values (best_seq_idx.nextval, free_seq_idnum.nextval, #{id}, #{subject}, #{content}, SYSDATE, #{filename}, #{filesize}, #{step}, #{ref}, #{depth})";
	@Insert(INSERT)
	void insertBoard(FreeBoard_Bean boardBean);
	
	//리스트 출력
	final String SELECT_PAGE =
			"select * from (select id, idx_num, subject, content, created_date, hits, filename, step, ref, depth, "
			+ "ceil(rownum / #{rowsPerPage}) as page "
			+ "from (select * from tlb_free_board order by ref desc, step asc))"
			+ "where page=#{page}";
	@Select(SELECT_PAGE)
	@Results(value= {
			@Result(property = "id", column = "id"), 
			@Result(property = "idx_num", column = "idx_num"), 
			@Result(property = "subject", column = "subject"), 
			@Result(property = "content", column = "content"),
			@Result(property = "created_date", column = "created_date"),
			@Result(property = "hits", column = "hits"), 
			@Result(property = "filename", column = "filename"), 
			@Result(property = "step", column = "step"),
			@Result(property = "ref", column = "ref"), 
			@Result(property = "depth", column = "depth")
	})
	ArrayList<FreeBoard_Bean>getList(@Param("page") int page,@Param("rowsPerPage") int rowsPerPage);
	
	//전체글 수
	final String SELECT_CNT_ALL =
			"select count(1) from tlb_free_board";
	@Select(SELECT_CNT_ALL)
	int getTotalCnt();
	
	//조회수 증가
	final String UPDATE_HITS="update tlb_free_board set hits=#{hits}+1 where idx_num=#{idx_num}";
	@Update(UPDATE_HITS)
	void updateHits(@Param("hits") int hits, @Param("idx_num") int idx_num);
	
	
	//글보기-글보기에 뿌릴 bean 가져오기: idx_num, subject, id, created_date, content, hits, filename
	final String SELECT_BY_ID="select idx_num, subject, id, created_date, content, hits, filename"
			+ " from tlb_free_board where idx_num=#{idx_num}";
	@Select(SELECT_BY_ID)
	//				output
	@Results(value= {
			@Result(property = "idx_num",column="idx_num"),
			@Result(property="subject",column="subject"),
			@Result(property = "id",column="id"),
			@Result(property="created_date",column="created_date"),
			@Result(property="content",column="content"),
			@Result(property = "hits", column="hits"),
			@Result(property = "filename", column="filename")
	})
	//						input
	FreeBoard_Bean getSpecificRow(@Param("idx_num") int idx_num);
	
	//글 수정
	final String UPDATE_BY_ID="update tlb_free_board set subject = #{subject}, "
			+ "content=#{content}, filename=#{filename}, filesize=#{filesize}"
			+ "where idx_num=#{idx_num}";
	@Update(UPDATE_BY_ID)
	void updateBoard(@Param("idx_num") int idx_num, @Param("subject") String subject,
			@Param("content") String content, @Param("filename") String filename, 
			@Param("filesize") long filesize);
	
	//글 삭제
	final String DELETE_BY_ID="delete from tlb_free_board where idx_num=#{idx_num}";
	@Delete(DELETE_BY_ID)
	void deleteSpecificRow(@Param("idx_num") int idx_num);
	
	//검색한 글 수
	final String SELECT_CNT_BY_SUBJECT="select count(1) from tlb_free_board where "
			+ " subject like '%'||'${searchThis}'||'%'";
	
	@Select(SELECT_CNT_BY_SUBJECT)
	int getTotalCntBySubject(@Param("searchThis") String includingThis);

	//글검색
	final String SELECT_ROWS_BY_SUBJECT=
		"SELECT * FROM (SELECT idx_num, SUBJECT, ID, CREATED_DATE,"
					+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page"
					+ " FROM tlb_free_board WHERE SUBJECT LIKE '%'||'${likeThis}'||'%' "
					+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";
			
	@Select(SELECT_ROWS_BY_SUBJECT)
	@Results(value= {
			@Result(property="idx_num",column="idx_num"),
			@Result(property ="subject", column="subject"),
			@Result(property="id", column="id"),
			@Result(property = "created_date",column = "created_date"),
			@Result(property = "content",column="content"),
			@Result(property = "hits",column = "hits")
	})
	public ArrayList<FreeBoard_Bean> getSearchedList(
			@Param("page") int page,
			@Param("rowsPerPage") int rowsPerPage,
			@Param("likeThis") String likeThis);
	
	////////////////////답변형 게시판/////////////////////////////
	//idx_num값을 업데이트 합니다.
	final String UPDATE_RE_WRITE="update tlb_free_board set ref=#{idx_num} where idx_num=#{idx_num}";
	@Select(UPDATE_RE_WRITE)
	void updateRewrite(@Param("idx_num") int idx_num);
	
	//가장 최근글을 가져옵니다.
	final String RECENT_ID="select max(idx_num) from tlb_free_board";
	@Select(RECENT_ID)
	int recentID();

	// 아이디로 id, ref,step,depth,subject를 출력합니다.
	final String SELECT_STAIR_BOARD="select idx_num, ref, step, depth, subject from tlb_free_board where idx_num=#{idx_num}";
	@Select(SELECT_STAIR_BOARD)
	FreeBoard_Bean stairBoard(@Param("idx_num") int idx_num);
	
	//그룹 step 을 증가시키고 현재 스탭보다 큰 애들 다 +1해줍니다.
	final String UPDATE_GROUP_STEP="update tlb_free_board set step=step+1 where ref=#{ref} and step>#{step}";
	@Update(UPDATE_GROUP_STEP)
	void updateGroupStep(@Param("ref") int ref, @Param("step") int step);
	
}
	
	


=======
	//글입력처리
		final String INSERT="insert into free_board(id_x, id, subject, created_date, category, content, filename, filesize)"
				+ " values(free_seq.nextval,#{id},#{subject},SYSDATE, #{category},#{content},#{filename},#{filesize})";
		
		@Insert(INSERT)
		void insertBoard(FreeBoard_Bean boardBean);
		
		//전체 글개수
		final String SELECT_CNT_ALL="select count(1) from free_board";
		
		@Select(SELECT_CNT_ALL)
		int getTotalCnt();
		
		//조회수 증가
		final String UPDATE_HITS="update free_board set hits=#{hits}+1 where id_x=#{idx}";
		
		@Update(UPDATE_HITS)
		void updateHits(@Param("hits") int hits, @Param("idx") int idx);
		
		//리스트 뿌리기
		final String SELECT_PAGE="SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
				+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page"
				+ " FROM free_BOARD ORDER BY CREATED_DATE DESC) WHERE page=#{page}" ;
		
		@Select(SELECT_PAGE)
		@Results(value= {
				@Result(property="idx",column="id_x"),
				@Result(property = "subject",column = "subject"),
				@Result(property = "id", column = "id"),
				@Result(property = "created_date",column = "created_date"),
				@Result(property = "content",column = "content"),
				@Result(property = "hits",column = "hits")
		})
		ArrayList<FreeBoard_Bean>getList(@Param("page") int page,
				@Param("rowsPerPage") int rowsPerPage);
		
	// 회원가입 글개수
	final String SELECT_SIGN_UP_CNT = "select count(1) from free_board where category='signup'";

	@Select(SELECT_SIGN_UP_CNT)
	int getSignUpCnt();

	// 회원가입 리스트 뿌리기
	final String SELECT_SiGN_UP_PAGE = "SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
			+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page" + " FROM free_BOARD WHERE CATEGORY='signup' "
			+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";

	@Select(SELECT_SiGN_UP_PAGE)
	@Results(value = { @Result(property = "idx", column = "id_x"), @Result(property = "subject", column = "subject"),
			@Result(property = "id", column = "id"), @Result(property = "created_date", column = "created_date"),
			@Result(property = "content", column = "content"), @Result(property = "hits", column = "hits") })
	ArrayList<FreeBoard_Bean> getSignUpList(@Param("page") int page, @Param("rowsPerPage") int rowsPerPage);

	// 예적금 글개수
	final String SELECT_SAVINGS_CNT = "select count(1) from free_board where category='savings'";

	@Select(SELECT_SAVINGS_CNT)
	int getSavingsCnt();

	// 예적금 리스트 뿌리기
	final String SELECT_SAVINGS_PAGE = "SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
			+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page" + " FROM free_BOARD WHERE CATEGORY='savings' "
			+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";

	@Select(SELECT_SAVINGS_PAGE)
	@Results(value = { @Result(property = "idx", column = "id_x"), @Result(property = "subject", column = "subject"),
			@Result(property = "id", column = "id"), @Result(property = "created_date", column = "created_date"),
			@Result(property = "content", column = "content"), @Result(property = "hits", column = "hits") })
	ArrayList<FreeBoard_Bean> getSavingsList(@Param("page") int page, @Param("rowsPerPage") int rowsPerPage);

	// 기타 글개수
		final String SELECT_ETC_CNT = "select count(1) from free_board where category='etc'";

		@Select(SELECT_ETC_CNT)
		int getEtcCnt();

		// 예적금 리스트 뿌리기
		final String SELECT_ETC_PAGE = "SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
				+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page" + " FROM free_BOARD WHERE CATEGORY='etc' "
				+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";

		@Select(SELECT_ETC_PAGE)
		@Results(value = { @Result(property = "idx", column = "id_x"), @Result(property = "subject", column = "subject"),
				@Result(property = "id", column = "id"), @Result(property = "created_date", column = "created_date"),
				@Result(property = "content", column = "content"), @Result(property = "hits", column = "hits") })
		ArrayList<FreeBoard_Bean> getEtcList(@Param("page") int page, @Param("rowsPerPage") int rowsPerPage);

		//글보기-글보기에 뿌릴 bean 가져오기: idx, subject, id, created_date, content, hits, filename
		final String SELECT_BY_ID="select id_x,subject, id, created_date, content, hits, filename"
				+ " from free_board where id_x=#{idx}";
		
		@Select(SELECT_BY_ID)
		//				output
		@Results(value= {
				@Result(property = "idx",column="id_x"),
				@Result(property="subject",column="subject"),
				@Result(property = "id",column="id"),
				@Result(property="created_date",column="created_date"),
				@Result(property="content",column="content"),
				@Result(property = "hits", column="hits"),
				@Result(property = "filename", column="filename")
		})
		//						input
		FreeBoard_Bean getSpecificRow(@Param("idx") int idx);
		
		// 수정
		final String UPDATE_BY_ID="update free_board set subject = #{subject},category=#{category}, "
				+ " content=#{content} where id_x=#{idx}";
		
		@Update(UPDATE_BY_ID)
		void updateBoard(@Param("idx") int idx, @Param("subject") String subject,
				@Param("category") String category, @Param("content") String content);
		
		//글 삭제
		final String DELETE_BY_ID="delete from free_board where id_x=#{idx}";
		
		@Delete(DELETE_BY_ID)
		void deleteSpecificRow(@Param("idx") int idx);
		
		// 검색한 글 수
		final String SELECT_CNT_BY_SUBJECT="select count(1) from free_board where "
				+ " subject like '%'||'${searchThis}'||'%'";
		
		@Select(SELECT_CNT_BY_SUBJECT)
		int getTotalCntBySubject(@Param("searchThis") String includingThis);

		//글검색
		final String SELECT_ROWS_BY_SUBJECT=
			"SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
						+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page"
						+ " FROM free_BOARD WHERE SUBJECT LIKE '%'||'${likeThis}'||'%' "
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
		public ArrayList<FreeBoard_Bean> getSearchedList(
				@Param("page") int page,
				@Param("rowsPerPage") int rowsPerPage,
				@Param("likeThis") String likeThis);
	
		
}
>>>>>>> f468c823fb38f81388f1f0cd521a2d8766679c8d
