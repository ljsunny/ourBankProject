package com.ourbank.app.mapper;


import java.util.ArrayList;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.ourbank.app.bean.QNABoard_Bean;

@Repository
public interface QNABoard_Mapper {
	//���Է�ó��
		final String INSERT="insert into qna_board(board_idx,id_x, id, subject, created_date, reply,category, content, filename, filesize)"
				+ " values(board_seq_idx.nextval, qna_seq.nextval,#{id},#{subject},SYSDATE,#{reply}, #{category},#{content},#{filename},#{filesize})";
		
		@Insert(INSERT)
		void insertBoard(QNABoard_Bean boardBean);
		
		//���� �ֱٱ� ������
		final String SELECT_RECENT=" select id_x from ("
									+ "select * from qna_board order by created_date desc) "
									+ "where rownum=1";
		@Select(SELECT_RECENT)
		int getRecent();
		
		//����-�亯 �׷� ����
		final String UPDATE_REPLY="update qna_board set reply=#{idx} where id_x=#{idx}";
		
		@Update(UPDATE_REPLY)
		void updateReply(
				@Param("idx") int idx);
		
		//��ü �۰���
		final String SELECT_CNT_ALL="select count(1) from qna_board";
		
		@Select(SELECT_CNT_ALL)
		int getTotalCnt();
		
		//��ȸ�� ����
		final String UPDATE_HITS="update qna_board set hits=#{hits}+1 where id_x=#{idx}";
		
		@Update(UPDATE_HITS)
		void updateHits(@Param("hits") int hits, @Param("idx") int idx);
		
		//����Ʈ �Ѹ���
		final String SELECT_PAGE="SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE," + 
				" CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page" + 
				" FROM (SELECT * FROM QNA_BOARD ORDER BY REPLY DESC, ID_X ASC))" + 
				" WHERE page=#{page}" ;
		
		@Select(SELECT_PAGE)
		@Results(value= {
				@Result(property="idx",column="id_x"),
				@Result(property = "subject",column = "subject"),
				@Result(property = "id", column = "id"),
				@Result(property = "created_date",column = "created_date"),
				@Result(property = "content",column = "content"),
				@Result(property = "hits",column = "hits")
		})
		ArrayList<QNABoard_Bean>getList(@Param("page") int page,
				@Param("rowsPerPage") int rowsPerPage);
	
	
	// ȸ������ �۰���
	final String SELECT_SIGN_UP_CNT = "select count(1) from qna_board where category='signup'";

	@Select(SELECT_SIGN_UP_CNT)
	int getSignUpCnt();

	// ȸ������ ����Ʈ �Ѹ���
	final String SELECT_SiGN_UP_PAGE = "SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
			+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page" + " FROM QNA_BOARD WHERE CATEGORY='signup' "
			+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";

	@Select(SELECT_SiGN_UP_PAGE)
	@Results(value = { @Result(property = "idx", column = "id_x"), @Result(property = "subject", column = "subject"),
			@Result(property = "id", column = "id"), @Result(property = "created_date", column = "created_date"),
			@Result(property = "content", column = "content"), @Result(property = "hits", column = "hits") })
	ArrayList<QNABoard_Bean> getSignUpList(@Param("page") int page, @Param("rowsPerPage") int rowsPerPage);

	// ������ �۰���
	final String SELECT_SAVINGS_CNT = "select count(1) from qna_board where category='savings'";

	@Select(SELECT_SAVINGS_CNT)
	int getSavingsCnt();

	// ������ ����Ʈ �Ѹ���
	final String SELECT_SAVINGS_PAGE = "SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
			+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page" + " FROM QNA_BOARD WHERE CATEGORY='savings' "
			+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";

	@Select(SELECT_SAVINGS_PAGE)
	@Results(value = { @Result(property = "idx", column = "id_x"), @Result(property = "subject", column = "subject"),
			@Result(property = "id", column = "id"), @Result(property = "created_date", column = "created_date"),
			@Result(property = "content", column = "content"), @Result(property = "hits", column = "hits") })
	ArrayList<QNABoard_Bean> getSavingsList(@Param("page") int page, @Param("rowsPerPage") int rowsPerPage);

	// ��Ÿ �۰���
		final String SELECT_ETC_CNT = "select count(1) from qna_board where category='etc'";

		@Select(SELECT_ETC_CNT)
		int getEtcCnt();

		// ������ ����Ʈ �Ѹ���
		final String SELECT_ETC_PAGE = "SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
				+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page" + " FROM QNA_BOARD WHERE CATEGORY='etc' "
				+ " ORDER BY CREATED_DATE DESC) WHERE page=#{page}";

		@Select(SELECT_ETC_PAGE)
		@Results(value = { @Result(property = "idx", column = "id_x"), @Result(property = "subject", column = "subject"),
				@Result(property = "id", column = "id"), @Result(property = "created_date", column = "created_date"),
				@Result(property = "content", column = "content"), @Result(property = "hits", column = "hits") })
		ArrayList<QNABoard_Bean> getEtcList(@Param("page") int page, @Param("rowsPerPage") int rowsPerPage);

		//�ۺ���-�ۺ��⿡ �Ѹ� bean ��������: idx, subject, id, created_date, content, hits, filename,category
		final String SELECT_BY_ID="select id_x,subject, id, created_date, content, hits, filename,category"
				+ " from qna_board where id_x=#{idx}";
		
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
				@Result(property = "category", column="category")
		})
		//						input
		QNABoard_Bean getSpecificRow(@Param("idx") int idx);
		
		// ����
		final String UPDATE_BY_ID="update qna_board set subject = #{subject},category=#{category}, "
				+ " content=#{content} where id_x=#{idx}";
		
		@Update(UPDATE_BY_ID)
		void updateBoard(@Param("idx") int idx, @Param("subject") String subject,
				@Param("category") String category, @Param("content") String content);
		
		//�� ����
		final String DELETE_BY_ID="delete from qna_board where id_x=#{idx}";
		
		@Delete(DELETE_BY_ID)
		void deleteSpecificRow(@Param("idx") int idx);
		
		// �˻��� �� ��
		final String SELECT_CNT_BY_SUBJECT="select count(1) from qna_board where "
				+ " subject like '%'||'${searchThis}'||'%'";
		
		@Select(SELECT_CNT_BY_SUBJECT)
		int getTotalCntBySubject(@Param("searchThis") String includingThis);

		//�۰˻�
		final String SELECT_ROWS_BY_SUBJECT=
			"SELECT * FROM (SELECT ID_X, SUBJECT, ID, CREATED_DATE,"
						+ " CONTENT, HITS, ceil(rownum/ #{rowsPerPage}) as page"
						+ " FROM QNA_BOARD WHERE SUBJECT LIKE '%'||'${likeThis}'||'%' "
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
		public ArrayList<QNABoard_Bean> getSearchedList(
				@Param("page") int page,
				@Param("rowsPerPage") int rowsPerPage,
				@Param("likeThis") String likeThis);
	
		
}
