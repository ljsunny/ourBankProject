package com.ourbank.app.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ourbank.app.bean.BestBoard_Bean;
import com.ourbank.app.bean.DepositBoard_Bean;
import com.ourbank.app.bean.NewnoticeBoard_Bean;
import com.ourbank.app.bean.NewsBoard_Bean;

@Repository
public interface MainPage_Mapper {
	final String SELECT_NOTICE_NEW="select id_x, substr(subject,7,16) subject, to_char(created_date,'YYYY-MM-DD') created_date "
			+ " from (select * from newnotice_board order by created_date desc)"
			+ " where rownum<=5 ";
	
	@Select(SELECT_NOTICE_NEW)
	@Results(value= {
			@Result(property = "idx",column = "id_x"),
			@Result(property = "subject",column = "subject"),
			@Result(property = "created_date" , column = "created_date")
	})
	public ArrayList<NewnoticeBoard_Bean> selectNotice();
	
	final String BEST_HITS_COMMUNITY=
			"select category, category_num, best_idx, id, subject, to_char(created_date,'YYYY-MM-DD') created_date, hits " 
			+"from commuboardview "
			+"where rownum <=5 and hits is not null order by hits desc ";
	@Select(BEST_HITS_COMMUNITY)
	@Results(value = {
			@Result(property = "category",column = "category"),
			@Result(property = "category_num", column = "category_num"),
			@Result(property = "best_idx",column="best_idx"),
			@Result(property = "id", column="id"),
			@Result(property = "subject",column = "subject"),
			@Result(property = "created_date", column = "created_date"),
			@Result(property = "hits" , column = "hits")
	})
	public ArrayList<BestBoard_Bean> selectBest();
	
	final String BEST_DEPOSIT="select * "
								+ " from "
								+ " (select kor_co_nm, fin_prdt_cd, fin_prdt_nm, intr_rate2 from tlb_deposit_board"
								+ " order by intr_rate2 desc)"
								+ " where rownum=1";
	@Select(BEST_DEPOSIT)
	@Results(value= {
			@Result(property ="kor_co_nm", column = "kor_co_nm" ),
			@Result(property ="fin_prdt_cd",column = "fin_prdt_cd" ),
			@Result(property = "fin_prdt_nm", column = "fin_prdt_nm"),
			@Result(property = "intr_rate2",column = "intr_rate2")
	})
	public DepositBoard_Bean bestDeposit();
	

}
