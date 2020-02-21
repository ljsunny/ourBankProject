package com.ourbank.app.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ourbank.app.bean.FreeBoard_Bean;

@Repository
public interface FreeBoard_Mapper {
	
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
	final String UPDATE_RE_WRITE=
			"update tlb_free_board set ref=#{idx_num} where idx_num=#{idx_num}";
	@Select(UPDATE_RE_WRITE)
	void updateRewrite(@Param("idx_num") int idx_num);
	
	//가장 최근글을 가져옵니다.
	final String RECENT_ID=
			"select max(idx_num) from tlb_free_board";
	@Select(RECENT_ID)
	int recentID();

	// 아이디로 id, ref,step,depth,subject를 출력합니다.
	final String SELECT_STAIR_BOARD=
			"select idx_num, ref, step, depth, subject from tlb_free_board where idx_num=#{idx_num}";
	@Select(SELECT_STAIR_BOARD)
	FreeBoard_Bean stairBoard(@Param("idx_num") int idx_num);
	
	//그룹 step 을 증가시키고 현재 스탭보다 큰 애들 다 +1해줍니다.
	final String UPDATE_GROUP_STEP=
			"update tlb_free_board set step=step+1 where ref=#{ref} and step>#{step}";
	@Update(UPDATE_GROUP_STEP)
	void updateGroupStep(@Param("ref") int ref, @Param("step") int step);
	
}
	
	


