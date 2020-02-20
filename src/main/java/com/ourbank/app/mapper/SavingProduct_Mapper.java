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
	

	
	final String SELECT_SAVING_CONTENT="select rsrv_type_nm, mtrt_int, fin_co_subm_day, kor_co_nm, fin_prdt_nm, join_way, "
			+ " spcl_cnd, join_deny, join_member, etc_note, intr_rate_type_nm, save_trm, intr_rate, intr_rate2 "
			+ " from tlb_saving_board where fin_prdt_cd=#{fin_prdt_cd}";
	
	@Select(SELECT_SAVING_CONTENT)
	@Results(value= {
			@Result(property="rsrv_type_nm", column="rsrv_type_nm"),
			@Result(property="mtrt_int", column="mtrt_int"),
			@Result(property="fin_co_subm_day", column="fin_co_subm_day"),
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
}
