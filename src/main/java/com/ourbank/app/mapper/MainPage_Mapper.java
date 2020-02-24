package com.ourbank.app.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ourbank.app.bean.BestBoard_Bean;
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
			"select * from (select category, category_num, best_idx, id, subject, to_char(created_date,'YYYY-MM-DD') created_date, hits " 
					+" from ("
					+"	(select category, category_num, best_idx, id, subject, to_char(created_date,'YYYY-MM-DD') created_date, hits from review_board) "
					+"union " 
					+	"(select category, category_num, best_idx, id, subject, to_char(created_date,'YYYY-MM-DD') created_date, hits from tlb_free_board) "
					+"union "
					+	"(select category, category_num, best_idx, id, subject, to_char(created_date,'YYYY-MM-DD') created_date, hits from tlb_meeting_board) "
					+"union "
					+	"(select category, category_num, best_idx, id, subject, to_char(created_date,'YYYY-MM-DD') created_date, hits from tlb_debate_board) "
					+"union "
					+	"(select category, category_num, best_idx, id, subject, to_char(created_date,'YYYY-MM-DD') created_date, hits from invest_board)) "
					+"where rownum <=5 and hits is not null order by hits desc) ";
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
}
