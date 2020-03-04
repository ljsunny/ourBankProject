package com.ourbank.app.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ourbank.app.bean.DepositBoard_Bean;

@Repository
public interface DepositProduct_Mapper {
	//���� url ��������
	final String SELECT_BANK_URL="select homp_url from TLB_BANK_BOARD where fin_co_no=#{fin_co_no}";
	
	@Select(SELECT_BANK_URL)
	public String selectBankUrl(@Param("fin_co_no") String fin_co_no);
	
	final String SELECT_DEPOSIT_LIST = "select * from("
			+ " select d.fin_prdt_nm, d.fin_prdt_cd, d.kor_co_nm, d.intr_rate_type_nm, "
			+ " d.save_trm, d.intr_rate, d.intr_rate2, b.homp_url,ceil(rownum/#{rowPerPage}) "
			+ " as page from TLB_DEPOSIT_BOARD d, TLB_BANK_BOARD b"
			+ " where d.fin_co_no=b.fin_co_no) where page=#{page}";

	@Select(SELECT_DEPOSIT_LIST)
	@Results(value = { @Result(property = "fin_prdt_nm", column = "fin_prdt_nm"),
			@Result(property = "fin_prdt_cd", column = "fin_prdt_cd"),
			@Result(property = "kor_co_nm", column = "kor_co_nm"),
			@Result(property = "intr_rate_type_nm", column = "intr_rate_type_nm"),
			@Result(property = "save_trm", column = "save_trm"), @Result(property = "intr_rate", column = "intr_rate"),
			@Result(property = "intr_rate2", column = "intr_rate2") })
	ArrayList<DepositBoard_Bean> selectDeposit(@Param("page") int Page, @Param("rowPerPage") int rowPerPage);

	final String SELECT_DEPOSIT_ALL = "select count(*) from tlb_deposit_board";

	@Select(SELECT_DEPOSIT_ALL)
	int nDepositProduct();

	final String SELECT_DEPOSIT_BANK = "select distinct kor_co_nm from tlb_deposit_board";

	@Select(SELECT_DEPOSIT_BANK)
	ArrayList<DepositBoard_Bean> all_bank();

	final String SELECT_DEPOSIT_CONTENT = "select mtrt_int, fin_co_subm_day,fin_co_no, kor_co_nm, fin_prdt_nm, join_way, "
			+ " spcl_cnd, join_deny, join_member, etc_note, intr_rate_type_nm, save_trm, intr_rate, intr_rate2 "
			+ " from tlb_deposit_board where fin_prdt_cd=#{fin_prdt_cd}";

	@Select(SELECT_DEPOSIT_CONTENT)
	@Results(value = { @Result(property = "mtrt_int", column = "mtrt_int"),
			@Result(property = "fin_co_subm_day", column = "fin_co_subm_day"),
			@Result(property = "fin_co_no", column = "fin_co_no"),
			@Result(property = "kor_co_nm", column = "kor_co_nm"),
			@Result(property = "fin_prdt_nm", column = "fin_prdt_nm"),
			@Result(property = "join_way", column = "join_way"), @Result(property = "spcl_cnd", column = "spcl_cnd"),
			@Result(property = "join_deny", column = "join_deny"),
			@Result(property = "join_member", column = "join_member"),
			@Result(property = "etc_note", column = "etc_note"),
			@Result(property = "intr_rate_type_nm", column = "intr_rate_type_nm"),
			@Result(property = "save_trm", column = "save_trm"), 
			@Result(property = "intr_rate", column = "intr_rate"),
			@Result(property = "intr_rate2", column = "intr_rate2")
			})
	DepositBoard_Bean selectDepositContent(@Param("fin_prdt_cd") String fin_prdt_cd);

	final String SELECT_DEPOSIT_NBank = "select count(*) from tlb_deposit_board where kor_co_nm=#{kor_co_nm}";

	// ���ະ����
	@Select(SELECT_DEPOSIT_NBank)
	int nDepositProductBank(@Param("kor_co_nm") String kor_co_nm);

	// �������� ����
	final String SELECT_DEPOSIT_BY_BANK = "select * from("
			+ " select d.fin_prdt_nm, d.fin_prdt_cd, d.kor_co_nm, d.intr_rate_type_nm, "
			+ " d.save_trm, d.intr_rate, d.intr_rate2, b.homp_url,ceil(rownum/#{rowPerPage}) "
			+ " as page from TLB_DEPOSIT_BOARD d, TLB_BANK_BOARD b"
			+ " where d.fin_co_no=b.fin_co_no and d.kor_co_nm=#{kor_co_nm}) where page=#{page}";

	@Select(SELECT_DEPOSIT_BY_BANK)
	@Results(value = { @Result(property = "fin_prdt_nm", column = "fin_prdt_nm"),
			@Result(property = "fin_prdt_cd", column = "fin_prdt_cd"),
			@Result(property = "kor_co_nm", column = "kor_co_nm"),
			@Result(property = "intr_rate_type_nm", column = "intr_rate_type_nm"),
			@Result(property = "save_trm", column = "save_trm"), @Result(property = "intr_rate", column = "intr_rate"),
			@Result(property = "intr_rate2", column = "intr_rate2") })
	ArrayList<DepositBoard_Bean> selectDepositByBank(@Param("page") int Page, @Param("rowPerPage") int rowPerPage,
			@Param("kor_co_nm") String kor_co_nm);
	// �˻�������
	final String SELECT_N_SEARCHED = "select count(*) from tlb_deposit_board where fin_prdt_nm LIKE '%'||'${searchStr}'||'%' ";

	
	@Select(SELECT_N_SEARCHED)
	int nDepositSearched(@Param("searchStr") String searchStr);

	final String SELECT_SEARCHED = "select * from("
			+ " select d.fin_prdt_nm, d.fin_prdt_cd, d.kor_co_nm, d.intr_rate_type_nm, "
			+ " d.save_trm, d.intr_rate, d.intr_rate2, b.homp_url,ceil(rownum/#{rowPerPage}) "
			+ " as page from TLB_DEPOSIT_BOARD d, TLB_BANK_BOARD b"
			+ " where upper(d.fin_prdt_nm) LIKE '%'||upper('${searchStr}')||'%' and d.fin_co_no=b.fin_co_no ) where page=#{page}";

	@Select(SELECT_SEARCHED)
	@Results(value = { @Result(property = "fin_prdt_nm", column = "fin_prdt_nm"),
			@Result(property = "fin_prdt_cd", column = "fin_prdt_cd"),
			@Result(property = "kor_co_nm", column = "kor_co_nm"),
			@Result(property = "intr_rate_type_nm", column = "intr_rate_type_nm"),
			@Result(property = "save_trm", column = "save_trm"), @Result(property = "intr_rate", column = "intr_rate"),
			@Result(property = "intr_rate2", column = "intr_rate2") })
	ArrayList<DepositBoard_Bean> selectSearched(@Param("page") int Page, @Param("rowPerPage") int rowPerPage,
			@Param("searchStr") String searchStr);

}
