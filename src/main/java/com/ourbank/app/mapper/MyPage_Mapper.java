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
import com.ourbank.app.bean.SavingBoard_Bean;

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
	////////////유저 업데이트 수정함////////////////////
	final String UPDATE_MY_INFO="update tlb_user_board set "
			+ " user_name=#{user_name}, user_birth=#{user_birth}, user_phone=#{user_phone},"
			+ " user_address=#{user_address}, user_email=#{user_email} where id=#{id}";
	
	@Update(UPDATE_MY_INFO)
	void updateMyInfo(@Param("user_name") String user_name,
			@Param("user_birth") String user_birth, @Param("user_phone") String user_phone,
			@Param("user_address") String user_address, @Param("user_email") String user_email,
			@Param("id") String id);
	
	//유저 삭제
	final String DELETE_ID="delete from tlb_user_board where id=#{id}";
	
	@Delete(DELETE_ID)
	void deleteId(@Param("id") String id);
	
	final String DELETE_PRODUCT_DATA="delete from tlb_my_product where id=#{id}";
	@Delete(DELETE_PRODUCT_DATA)
	void deleteProductData(@Param("id") String id);
	
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
	
	//이미 존재하는 지 확인하기
	final String SELECT_CNT_EXIST="select count(*) from tlb_my_product "
								+ " where id=#{id} and fin_prdt_cd=#{fin_prdt_cd}";
	
	@Select(SELECT_CNT_EXIST)
	int selectCntExist(@Param("id") String id, @Param("fin_prdt_cd") String fin_prdt_cd);
	
	//이미 존재하는 지 확인하기
	final String SELECT_WANT_EXIST="select count(*) from tlb_my_want "
									+ " where id=#{id} and fin_prdt_cd=#{fin_prdt_cd}";
		
	@Select(SELECT_WANT_EXIST)
	int selectWantExist(@Param("id") String id, @Param("fin_prdt_cd") String fin_prdt_cd);
	
	final String INSERT_MY_DEPOSIT="insert into tlb_my_product "
									+ " values (#{id} ,#{fin_co_no} ,"
									+ " #{kor_co_nm} , #{fin_prdt_cd} ,"
									+ " #{fin_prdt_nm} , '예금' )";
	
	@Insert(INSERT_MY_DEPOSIT)
	void insertMyDeposit(@Param("id") String id,
						@Param("fin_co_no") String fin_co_no,
						@Param("kor_co_nm") String kor_co_nm,
						@Param("fin_prdt_cd") String fin_prdt_cd,
						@Param("fin_prdt_nm") String fin_prdt_nm);
	


	//적금------------------------------------------------------------------------------------
	final String SELECT_SAVING_BANK = "select distinct fin_co_no, kor_co_nm"
			+ " from TLB_SAVING_BOARD order by fin_co_no asc";

	@Select(SELECT_SAVING_BANK)
	ArrayList<SavingBoard_Bean> selectSavingBank();

	final String SELECT_SAVING_PRODUCT = "select  fin_co_no, kor_co_nm, fin_prdt_nm, fin_prdt_cd from TLB_SAVING_BOARD"
			+ " order by fin_co_no asc";

	@Select(SELECT_SAVING_PRODUCT)
	ArrayList<SavingBoard_Bean> selectSavingProduct();

//상품코드와 일치하는 데이터 가져오기
	final String SELECT_ONE_SAVING = "select  fin_co_no, kor_co_nm, fin_prdt_nm, fin_prdt_cd from TLB_SAVING_BOARD"
			+ " where fin_prdt_cd=#{fin_prdt_cd}";

	@Select(SELECT_ONE_SAVING)
	SavingBoard_Bean selectOneSaving(@Param("fin_prdt_cd") String fin_prdt_cd);

	final String INSERT_MY_SAVING = "insert into tlb_my_product " + " values (#{id} ,#{fin_co_no} ,"
			+ " #{kor_co_nm} , #{fin_prdt_cd} ," + " #{fin_prdt_nm} , '적금' )";

	@Insert(INSERT_MY_SAVING)
	void insertMySaving(@Param("id") String id, @Param("fin_co_no") String fin_co_no,
			@Param("kor_co_nm") String kor_co_nm, @Param("fin_prdt_cd") String fin_prdt_cd,
			@Param("fin_prdt_nm") String fin_prdt_nm);

	//아이디에 해당하는 수
	final String SELECT_CNT_MY_PRODUCT="select count(*) from tlb_my_product where id=#{id}";
	@Select(SELECT_CNT_MY_PRODUCT)
	int selectCntMyProduct(@Param("id") String id);
	
	//리스트 출력 요소 가져오기
	final String SELECT_ALL_PRODUCT="select * from (select  id, fin_co_no,kor_co_nm,"
									+ " fin_prdt_cd, fin_prdt_nm, dep_or_sav, ceil(rownum/#{pageforView}) as page"
									+ " from "
									+ " (select * from tlb_my_product "
									+ "	order by dep_or_sav asc, fin_co_no asc)"
									+ " where id=#{id})"
									+ " where page=#{current_page}";
	
	@Select(SELECT_ALL_PRODUCT)
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property = "fin_co_no",column = "fin_co_no"),
			@Result(property = "kor_co_nm",column = "kor_co_nm"),
			@Result(property = "fin_prdt_cd",column = "fin_prdt_cd"),
			@Result(property = "fin_prdt_nm",column = "fin_prdt_nm"),
			@Result(property = "dep_or_sav",column = "dep_or_sav")
	})
	ArrayList<DepositBoard_Bean> selectAllProduct(@Param("id") String id,
												@Param("current_page") int current_page,
												@Param("pageforView") int pageforView);
	
	final String DELETE_PRODUCT="delete from tlb_my_product where id=#{id} and fin_prdt_cd=#{fin_prdt_cd} ";
	
	@Delete(DELETE_PRODUCT)
	void deleteProduct(@Param("id") String id,
						@Param("fin_prdt_cd") String fin_prdt_cd) ;
	
	//////////장바구니//////////////////////////////////////////////////////
	//select one을 이용해서 데이터 가져오고
	//원하는 상품 추가
	final String INSERT_WANT="insert into tlb_my_want values( "
							+ " #{id},#{fin_co_no},#{kor_co_nm}, #{fin_prdt_cd}, "
							+ " #{fin_prdt_nm},#{dep_or_sav})";
	
	@Insert(INSERT_WANT)
	void insertMyWant(
			@Param("id") String id,
			@Param("fin_co_no") String fin_co_no,
			@Param("kor_co_nm") String kor_co_nm,
			@Param("fin_prdt_cd") String fin_prdt_cd,
			@Param("fin_prdt_nm") String fin_prdt_nm,
			@Param("dep_or_sav") String dep_or_sav);
	
	final String SELECT_WANT_CNT="select count(*) from tlb_my_want where id=#{id}";
	//원하는 상품수
	@Select(SELECT_WANT_CNT)
	int selectWantCnt(@Param("id") String id);
	
	final String SELECT_WANT_LIST="select * from (select  id, fin_co_no,kor_co_nm,"
								+ " fin_prdt_cd, fin_prdt_nm, dep_or_sav, ceil(rownum/#{pageforView}) as page"
								+ " from "
								+ " (select * from tlb_my_want "
								+ "	order by dep_or_sav asc, fin_co_no asc)"
								+ " where id=#{id})"
								+ " where page=#{current_page}";
	//원하는 상품 리스트// 예금빈 사용함
	@Select(SELECT_WANT_LIST)
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property = "fin_co_no",column = "fin_co_no"),
			@Result(property = "kor_co_nm",column = "kor_co_nm"),
			@Result(property = "fin_prdt_cd",column = "fin_prdt_cd"),
			@Result(property = "fin_prdt_nm",column = "fin_prdt_nm"),
			@Result(property = "dep_or_sav",column = "dep_or_sav")
	})
	
	ArrayList<DepositBoard_Bean> selectWantList(@Param("id") String id,
												@Param("current_page") int current_page,
												@Param("pageforView") int pageforView);
	
	//장바구니 삭제
	final String DELETE_MY_WANT="delete from tlb_my_want where id=#{id} and fin_prdt_cd=#{fin_prdt_cd}";
	@Delete(DELETE_MY_WANT)
	public void deleteMyWant(@Param("id") String id, @Param("fin_prdt_cd") String fin_prdt_cd);
	/////////////////////////////////////////////////////////////////////////////////////////////////
	

}