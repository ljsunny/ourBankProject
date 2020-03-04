package com.ourbank.app.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ourbank.app.bean.DepositBoard_Bean;
import com.ourbank.app.bean.SavingBoard_Bean;

@Repository
public interface SavingProduct_Mapper {
	//은행 url 가져오기
		final String SELECT_BANK_URL="select homp_url from TLB_BANK_BOARD where fin_co_no=#{fin_co_no}";
		
		@Select(SELECT_BANK_URL)
		public String selectBankUrl(@Param("fin_co_no") String fin_co_no);
	
	final String SELECT_SAVING_LIST="select * from(" + 
			" select s.fin_prdt_cd, s.fin_prdt_nm, s.kor_co_nm, s.intr_rate_type_nm, " + 
			" s.save_trm, s.intr_rate, s.intr_rate2, b.homp_url,ceil(rownum/#{rowPerPage}) "+
			" as page from tlb_saving_board s, TLB_BANK_BOARD b" + 
			" where s.fin_co_no=b.fin_co_no) where page=#{page}";
	
	@Select(SELECT_SAVING_LIST)
	@Results(value = {
			@Result(property = "fin_prdt_cd", column = "fin_prdt_cd"),
			@Result(property = "fin_prtd_nm", column = "fin_prtd_nm"),
			@Result(property = "kor_co_nm",column = "kor_co_nm"),
			@Result(property = "intr_rate_type_nm",column = "intr_rate_type_nm"),
			@Result(property = "save_trm", column = "save_trm"),
			@Result(property = "intr_rate",column = "intr_rate"),
			@Result(property = "intr_rate2", column = "intr_rate2")
	})
	ArrayList<SavingBoard_Bean> selectSaving(@Param("page") int Page,
												@Param("rowPerPage") int rowPerPage);
	
	
	final String SELECT_SAVING_ALL="select count(*) from tlb_saving_board";
	
	@Select(SELECT_SAVING_ALL)
	int nSavingProduct();
	
	
	final String SELECT_SAVING_BANK="select distinct kor_co_nm from tlb_saving_board";
	
	@Select(SELECT_SAVING_BANK)
	ArrayList<SavingBoard_Bean> all_bank();
	

	
	final String SELECT_SAVING_CONTENT="select rsrv_type_nm, mtrt_int, fin_co_subm_day,fin_co_no, kor_co_nm, fin_prdt_nm, join_way, "
			+ " spcl_cnd, join_deny, join_member, etc_note, intr_rate_type_nm, save_trm, intr_rate, intr_rate2 "
			+ " from tlb_saving_board where fin_prdt_cd=#{fin_prdt_cd}";
	
	@Select(SELECT_SAVING_CONTENT)
	@Results(value= {
			@Result(property="rsrv_type_nm", column="rsrv_type_nm"),
			@Result(property="mtrt_int", column="mtrt_int"),
			@Result(property="fin_co_subm_day", column="fin_co_subm_day"),
			@Result(property="fin_co_no", column="fin_co_no"),
			@Result(property="kor_co_nm", column="kor_co_nm"),
			@Result(property="fin_prdt_nm", column="fin_prdt_nm"),
			@Result(property="join_way", column="join_way"),
			@Result(property="spcl_cnd", column="spcl_cnd"),
			@Result(property="join_deny", column="join_deny"),
			@Result(property="join_member", column="join_member"),
			@Result(property="etc_note", column="etc_note"),
			@Result(property="intr_rate_type_nm", column="intr_rate_type_nm"),
			@Result(property="save_trm", column="save_trm"),
			@Result(property="intr_rate", column="intr_rate"),
			@Result(property="intr_rate2", column="intr_rate2"),
	})
	SavingBoard_Bean selectSavingContent(@Param("fin_prdt_cd") String fin_prdt_cd);
	
		final String SELECT_SAVING_NBank="select count(*) from tlb_saving_board where kor_co_nm=#{kor_co_nm}";
		//은행별개수
		@Select(SELECT_SAVING_NBank)
		int nSavingProductBank(@Param("kor_co_nm") String kor_co_nm);
		
		//은행으로 정렬
		final String SELECT_SAVING_BY_BANK="select * from(" + 
				" select d.fin_prdt_nm, d.fin_prdt_cd, d.kor_co_nm, d.intr_rate_type_nm, " + 
				" d.save_trm, d.intr_rate, d.intr_rate2, b.homp_url,ceil(rownum/#{rowPerPage}) "+
				" as page from TLB_SAVING_BOARD d, TLB_BANK_BOARD b" + 
				" where d.fin_co_no=b.fin_co_no and d.kor_co_nm=#{kor_co_nm}) where page=#{page}";
		
		@Select(SELECT_SAVING_BY_BANK)
		@Results(value = {
				@Result(property = "fin_prdt_nm", column = "fin_prdt_nm"),
				@Result(property = "fin_prdt_cd",column = "fin_prdt_cd"),
				@Result(property = "kor_co_nm",column = "kor_co_nm"),
				@Result(property = "intr_rate_type_nm",column = "intr_rate_type_nm"),
				@Result(property = "save_trm", column = "save_trm"),
				@Result(property = "intr_rate",column = "intr_rate"),
				@Result(property = "intr_rate2", column = "intr_rate2")
		})
		ArrayList<SavingBoard_Bean> selectSavingByBank(@Param("page") int Page,
													@Param("rowPerPage") int rowPerPage,
													@Param("kor_co_nm") String kor_co_nm);
		
		// 검색별개수
		final String SELECT_N_SEARCHED = "select count(*) from tlb_saving_board where fin_prdt_nm LIKE '%'||'${searchStr}'||'%' ";

		
		@Select(SELECT_N_SEARCHED)
		int nSavingSearched(@Param("searchStr") String searchStr);

		final String SELECT_SEARCHED = "select * from("
				+ " select d.fin_prdt_nm, d.fin_prdt_cd, d.kor_co_nm, d.intr_rate_type_nm, "
				+ " d.save_trm, d.intr_rate, d.intr_rate2, b.homp_url,ceil(rownum/#{rowPerPage}) "
				+ " as page from TLB_SAVING_BOARD d, TLB_BANK_BOARD b"
				+ " where d.fin_prdt_nm LIKE '%'||'${searchStr}'||'%' and d.fin_co_no=b.fin_co_no ) where page=#{page}";

		@Select(SELECT_SEARCHED)
		@Results(value = { @Result(property = "fin_prdt_nm", column = "fin_prdt_nm"),
				@Result(property = "fin_prdt_cd", column = "fin_prdt_cd"),
				@Result(property = "kor_co_nm", column = "kor_co_nm"),
				@Result(property = "intr_rate_type_nm", column = "intr_rate_type_nm"),
				@Result(property = "save_trm", column = "save_trm"), 
				@Result(property = "intr_rate", column = "intr_rate"),
				@Result(property = "intr_rate2", column = "intr_rate2") })
		ArrayList<SavingBoard_Bean> selectSearched(@Param("page") int Page, 
												@Param("rowPerPage") int rowPerPage,
				@								Param("searchStr") String searchStr);
		
}
