--create db
-----------------------product- saving table
create table tlb_deposit_board(
	dcls_month varchar2(10),
 	fin_co_no  varchar2(10) not null,
 	kor_co_nm varchar2(100) not null,
 	fin_prdt_cd varchar2(100) not null,
 	fin_prdt_nm varchar2(100) not null,
 	join_way varchar2(100) not null,
 	mtrt_int varchar2(1000) ,
	spcl_cnd varchar2(1000) ,
	join_deny varchar2(10) ,
	join_member varchar2(1000) ,
	etc_note varchar2(1000) ,
	max_limit varchar2(200) default null,
	dcls_strt_day varchar2(100) default null,
	dcls_end_day varchar2(200) default null,
	fin_co_subm_day varchar2(100) ,
	intr_rate_type varchar2(10) ,
	intr_rate_type_nm varchar2(10) ,
	save_trm varchar2(10) ,
	intr_rate varchar2(10) ,
	intr_rate2 varchar2(10) ,
	primary key(fin_prdt_cd) enable
);

----------------------- product - saving

create table tlb_saving_board(
	dcls_month varchar2(10),
 	fin_co_no  varchar2(10) not null,
 	kor_co_nm varchar2(100) not null,
 	fin_prdt_cd varchar2(100) not null,
 	fin_prdt_nm varchar2(100) not null,
 	join_way varchar2(100) not null,
 	mtrt_int varchar2(1000) ,
	spcl_cnd varchar2(1000) ,
	join_deny varchar2(10) ,
	join_member varchar2(1000) ,
	etc_note varchar2(1000) ,
	max_limit varchar2(200) default null,
	dcls_strt_day varchar2(100) default null,
	dcls_end_day varchar2(200) default null,
	fin_co_subm_day varchar2(100) ,
	intr_rate_type varchar2(10) ,
	intr_rate_type_nm varchar2(10) ,
	rsrv_type varchar2(30),
	rsrv_type_nm varchar2(30),
	save_trm varchar2(10) ,
	intr_rate varchar2(10) ,
	intr_rate2 varchar2(10) ,
	primary key(fin_prdt_cd) enable
);

---------------------은행정보 db(은행 홈페이지 주소)
create table tlb_bank_board(
 	fin_co_no  varchar2(10) not null,
 	kor_co_nm varchar2(100) not null,
 	homp_url varchar2(100) ,
 	primary key(fin_co_no) enable
 )
 select * from tlb_bank_board;
 
 
---테이블 drop
 drop table tlb_deposit_board;
 drop table tlb_saving_board;
 drop table tlb_bank_board;
 
----show db 
select * from tlb_saving_board;
select * from TLB_DEPOSIT_BOARD;
