package com.ourbank.app.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Property;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ourbank.app.bean.DepositBoard_Bean;
import com.ourbank.app.bean.UserBoard_Bean;
import com.ourbank.app.bean.FreeBoard_Bean;

@Repository
public interface MyPage_Mapper {

	final String SELECT_MY_INFO="select * from tlb_user_board where id=#{id}";
	
	@Select(SELECT_MY_INFO)
	@Results(value= {
			@Result(property="id",column="id"),
			@Result(property="passwd",column="passwd"),
			@Result(property = "user_name", column = "user_name"),
			@Result(property = "user_birth",column = "user_birth"),
			@Result(property = "user_phone",column = "user_phone"),
			@Result(property = "user_address",column = "user_address"),
			@Result(property = "user_email",column = "user_email")
		})
		UserBoard_Bean selectMyInfo(@Param("id") String id);
	
	final String UPDATE_MY_INFO="update tlb_user_board set passwd=#{passwd},"
			+ " user_name=#{user_name}, user_birth=#{user_birth}, user_phone=#{user_phone},"
			+ " user_address=#{user_address}, user_email=#{user_email} where id=#{id}";
	
	@Update(UPDATE_MY_INFO)
	void updateMyInfo(@Param("passwd") String passwd, @Param("user_name") String user_name,
			@Param("user_birth") String user_birth, @Param("user_phone") String user_phone,
			@Param("user_address") String user_address, @Param("user_email") String user_email,
			@Param("id") String id);
	
	//유저 삭제
	final String DELETE_ID="delete from tlb_user_board where id=#{id}";
	
	@Delete(DELETE_ID)
	void deleteId(@Param("id") String id);
	
	//내가 쓴글 - 리스트
	final String SELECT_MY_BOARDLIST =
			"select * from (select board_idx, id, subject, content, created_date, hits, filename, "
					+ "ceil(rownum / #{rowsPerPage}) as page "
					+ "from (select * from myboardview order by created_date desc))"
					+ "where page=#{page}";
			
	@Select(SELECT_MY_BOARDLIST)
	@Results(value = {
			@Result(property = "board_idx", column = "board_idx"),
			@Result(property = "id", column = "id"), 
			@Result(property = "subject", column = "subject"), 
			@Result(property = "created_date", column = "created_date"),
			@Result(property = "hits", column = "hits"), 
			@Result(property = "step", column = "step"),
			@Result(property = "ref", column = "ref")
	})
	ArrayList<FreeBoard_Bean> getBoardList(@Param("id") String id, @Param("page") int page,@Param("rowsPerPage") int rowsPerPage);
	
	//내가쓴 글 -글보기
	final String SELECT_MY_BOARDVIEW =
			"select * from myboardview where board_idx=#{board_idx}";
	@Select(SELECT_MY_BOARDVIEW)
	@Results(value= {
			@Result(property = "board_idx",column="board_idx"),
			@Result(property="subject",column="subject"),
			@Result(property = "id",column="id"),
			@Result(property="created_date",column="created_date"),
			@Result(property="content",column="content"),
			@Result(property = "hits", column="hits"),
			@Result(property = "filename", column="filename")
	})
	FreeBoard_Bean getSpecificRow(@Param("board_idx") int board_idx);
	/////////////////////////////////////////////////////////////////////////////////////////////////
	//Welcome-가입한 예금상품
	final String SELECT_DEPOSIT_BANK="select distinct fin_co_no, kor_co_nm"
									+ " from TLB_DEPOSIT_BOARD order by fin_co_no asc";
	
	@Select(SELECT_DEPOSIT_BANK)
	ArrayList<DepositBoard_Bean> selectDepositBank();
	
	final String SELECT_DEPOSIT_PRODUCT="select  fin_co_no, kor_co_nm, fin_prdt_nm, fin_prdt_cd from TLB_DEPOSIT_BOARD"
										+" order by fin_co_no asc";
	
	@Select(SELECT_DEPOSIT_PRODUCT) 
	ArrayList<DepositBoard_Bean> selectDepositProduct();
	
	//상품코드와 일치하는 데이터 가져오기
	final String SELECT_ONE_DEPOSIT="select  fin_co_no, kor_co_nm, fin_prdt_nm, fin_prdt_cd from TLB_DEPOSIT_BOARD"
								  + " where fin_prdt_cd=#{fin_prdt_cd}";
	
	@Select(SELECT_ONE_DEPOSIT)
	DepositBoard_Bean selectOneDeposit(@Param("fin_prdt_cd") String fin_prdt_cd);
	
	
	final String INSERT_MY_DEPOSIT="insert into tlb_my_product "
									+ " values (my_product_seq.nextval,#{id} ,#{fin_co_no} ,"
									+ " #{kor_co_nm} , #{fin_prdt_cd} ,"
									+ " #{fin_prdt_nm} , '예금' )";
	
	@Insert(INSERT_MY_DEPOSIT)
	void insertMyDeposit(@Param("id") String id,
						@Param("fin_co_no") String fin_co_no,
						@Param("kor_co_nm") String kor_co_nm,
						@Param("fin_prdt_cd") String fin_prdt_cd,
						@Param("fin_prdt_nm") String fin_prdt_nm);

	//아이디에 해당하는 수
	final String SELECT_CNT_MY_PRODUCT="select count(*) from tlb_my_product where id=#{id}";
	@Select(SELECT_CNT_MY_PRODUCT)
	int selectCntMyProduct(@Param("id") String id);
	
	//리스트 출력 요소 가져오기
	final String SELECT_ALL_PRODUCT="select * from (select service_num,id,fin_co_no,kor_co_nm,"
									+ " fin_prdt_cd, fin_prdt_nm, dep_or_sav, rownum/#{pageforView} as page"
									+ " from "
									+ " (select * from tlb_my_product "
									+ "	order by dep_or_sav asc, fin_co_no asc)"
									+ " where id=#{id})"
									+ " where page=#{current_page}";
	
	@Select(SELECT_ALL_PRODUCT)
	@Results(value = {
			@Result(property="service_num", column="service_num"),
			@Result(property="id", column="id"),
			@Result(property = "fin_co_no",column = "fin_co_no"),
			@Result(property = "kor_co_nm",column = "kor_co_nm"),
			@Result(property = "fin_prdt_cd",column = "fin_prdt_cd"),
			@Result(property = "fin_prdt_nm",column = "fin_prdt_nm"),
			@Result(property = "dep_or_sav",column = "fin_co_no"),
	})
	ArrayList<DepositBoard_Bean> selectAllProduct(@Param("id") String id,
												@Param("current_page") int current_page,
												@Param("pageforView") int pageforView);
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	

}