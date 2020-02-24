--0: 일반 유저 1:어드민

create table tlb_user_board(
	id varchar2(50) not null,
 	passwd varchar2(200) not null,
 	user_name varchar2(50) not null,
 	user_birth varchar2(50) not null,
 	user_email varchar2(50) not null,
 	user_phone varchar2(50) not null,
 	user_address varchar2(1000) ,
 	salt varchar2(50) not null,
	admin char(1) check(admin in('0','1')),
	primary key(id) enable
);

drop table tlb_user_board;
select count(*) from tlb_user_board where  id='js961223' and passwd='11121';
select * from tlb_user_board;
select id from tlb_user_board where user_name='어드민' and user_birth='2020-11-31' and user_phone='010-5068-7058';


/*my deposit*/
create sequence my_product_seq minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
create table tlb_my_product(
	service_num number(10,0) not null,
	id varchar2(50) not null,
 	fin_co_no  varchar2(10) not null,
 	kor_co_nm varchar2(100) not null,
 	fin_prdt_cd varchar2(100) not null,
 	fin_prdt_nm varchar2(100) not null,
 	dep_or_sav char(6) check(dep_or_sav in('예금','적금')),
	primary key(service_num) enable
);
drop table tlb_my_product;
select * from tlb_my_product;
/*deposit view */
create view view_my_deposit_product as (
select u.id, p.fin_co_no,p.kor_co_nm,p.fin_prdt_cd,p.fin_prdt_nm
from  tlb_user_board u,tlb_deposit_board p)

select * from view_my_deposit_product;
/*saving view */
create view view_my_saving_product as (
select u.id, p.fin_co_no,p.kor_co_nm,p.fin_prdt_cd,p.fin_prdt_nm
from  tlb_user_board u,tlb_saving_board p)

drop view tlb_my_product;