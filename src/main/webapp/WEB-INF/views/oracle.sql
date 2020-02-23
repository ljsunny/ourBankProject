/* best게시물 시퀀스*/
create sequence best_seq_idx minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
drop sequence best_seq_idx;

/* 자유게시판 */
create sequence free_seq_idnum minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
 create table tlb_free_board (
    category varchar2(20) default '자유',
    category_num number(10,0) default 1,
 	best_idx number(10,0) not null,
 	id varchar2(20) not null,
 	idx_num number(10,0) not null,
 	subject varchar2(50),
 	content varchar2(200),
 	created_date date,
 	hits number(10,0),
 	filename varchar2(50) default null,
 	filesize number default null,
 	step number(10,0) default null,
 	ref number(10,0) default null,
 	depth number(10,0) default null,
primary key (idx_num) enable
 ) ;

 drop table tlb_free_board;
 select * from tlb_free_board;
 drop sequence free_seq_idnum;
 insert into tlb_free_board (id, idx_num) values ('ex_id', 1);

 
 /*모임방 게시판*/
 create sequence meeting_seq_idnum minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
 create table tlb_meeting_board (
 	category varchar2(20) default '모임',
 	category_num number(10,0) default 2,
 	best_idx number(10,0) not null,
 	id varchar2(20) not null,
 	idx_num number(10,0) not null,
 	subject varchar2(50),
 	content varchar2(200),
 	created_date date,
 	hits number(10,0),
 	filename varchar2(50) default null,
 	filesize number default null,
 	step number(10,0) default null,
 	ref number(10,0) default null,
 	depth number(10,0) default null,
primary key (idx_num) enable
 ) ;
  select * from tlb_meeting_board;
   drop table tlb_meeting_board;
    drop sequence meeting_seq_idnum;
  
   /*토론방 게시판*/
 create sequence debate_seq_idnum minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
 create table tlb_debate_board (
 	category varchar2(20) default '토론',
 	category_num number(10,0) default 3,
 	best_idx number(10,0) not null,
 	id varchar2(20) not null,
 	idx_num number(10,0) not null,
 	subject varchar2(50),
 	content varchar2(200),
 	created_date date,
 	hits number(10,0),
 	filename varchar2(50) default null,
 	filesize number default null,
 	step number(10,0) default null,
 	ref number(10,0) default null,
 	depth number(10,0) default null,
primary key (idx_num) enable
 ) ;
  select * from tlb_debate_board;
   drop table tlb_debate_board;
   drop sequence debate_seq_idnum;
    
    

	(select * from TLB_FREE_BOARD) 
union 
	(select * from tlb_meeting_board)
union
	(select * from tlb_debate_board)

	

 select * from (select category, category_num,best_idx, id, subject, content, created_date, hits, filename, 
					ceil(rownum / 10) as page 
					from ((select * from TLB_FREE_BOARD) 
					union 
					(select * from tlb_meeting_board) 
					union 
					(select * from tlb_debate_board)) where rownum <=10 and hits is not null  order by hits desc) 
					where page=1;
					

	


