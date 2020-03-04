create sequence news_seq minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
create sequence board_seq_idx minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
create sequence best_seq_idx minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;


create table tlb_news_board (
	board_idx number(10,0) not null,
	category varchar2(20) default '금융회사정보',
    category_num number(10,0) default 1,
    best_idx number(10,0) not null,
 	idx_num number(10,0) not null,
 	id varchar2(20) not null,
 	subject varchar2(100),
 	content varchar2(700),
 	created_date date,
 	hits number(10,0),
 	filename varchar2(50) default null,
 	filesize number default null,
 	step number(10,0) default null,
 	ref number(10,0) default null,
 	depth number(10,0) default null,
 	news_case char(20) default '금융회사정보' check (news_case in ('금융회사정보', '관련뉴스')),
 	
primary key (idx_num) enable
 ) ;

 
 drop table tlb_news_board;
 
 select * from tlb_news_board;
 
 insert into tlb_news_board(
 		board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (121, 1212, 1112, 'exid', '금융회사정보11', sysdate, '금융회사정보','ㅇㅇ',null,null, 0, 0, 0)	

insert into tlb_news_board(
 		board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (119, 1210, 1115, 'exid', '관련뉴스11', sysdate, '관련뉴스','ㅇㅇ',null,null, 0, 0, 0)	

