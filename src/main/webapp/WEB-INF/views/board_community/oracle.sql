/* best게시물 시퀀스*/
create sequence best_seq_idx minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
drop sequence best_seq_idx;

/* 전체게시판 마다 전체 글번호 부여해주기--> 나의 글보기에서 전체 조회가능 */
create sequence board_seq_idx minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
/*리뷰 게시판*/
create sequence review_seq minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
create table review_board (
	board_idx number(10,0) not null,
	category varchar2(20) default '리뷰',
    category_num number(10,0) default 1,
    best_idx number(10,0) not null,
 	id_x number(10,0) not null,
 	id varchar2(20) not null,
 	subject varchar2(100),
 	content varchar2(200),
 	created_date date,
 	hits number(10,0),
 	filename varchar2(50) default null,
 	filesize number default null,
 	step number(10,0) default null,
 	ref number(10,0) default null,
 	depth number(10,0) default null,
 	review_case char(20) default '예금리뷰' check (review_case in ('예금리뷰', '적금리뷰', '기타리뷰')),
 	satisfaction char(30) default '★★★★★  매우만족' check (satisfaction 
 		in ('★★★★★  매우만족', '★★★★  만족', '★★★  보통', '★★  불만족', '★  매우불만족')),
 	banks varchar2(30) ,
 	re_productname varchar2(50) ,
primary key (id_x) enable
 ) ;
 
 select review_case, satisfaction, banks, re_productname from review_board where ref = 29;
 
 drop sequence review_seq;
 select * from review_board;
 drop table review_board;
 
 
/* 자유게시판 */
create sequence free_seq_idnum minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
 create table tlb_free_board (
 board_idx number(10,0) not null,
    category varchar2(20) default '자유',
    category_num number(10,0) default 2,
 	best_idx number(10,0) not null,
 	id varchar2(20) not null,
 	idx_num number(10,0) not null,
 	subject varchar2(100),
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

 
 /*모임 게시판*/
 create sequence meeting_seq_idnum minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
 create table tlb_meeting_board (
 board_idx number(10,0) not null,
 	category varchar2(20) default '모임',
 	category_num number(10,0) default 3,
 	best_idx number(10,0) not null,
 	id varchar2(20) not null,
 	idx_num number(10,0) not null,
 	subject varchar2(100),
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
  
    
   /*토론 게시판*/
 create sequence debate_seq_idnum minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
 create table tlb_debate_board (
 board_idx number(10,0) not null,
 	category varchar2(20) default '토론',
 	category_num number(10,0) default 4,
 	best_idx number(10,0) not null,
 	id varchar2(20) not null,
 	idx_num number(10,0) not null,
 	subject varchar2(100),
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
    
   
 /*제태크 노하우 게시판*/
  create sequence invest_seq minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
  create table invest_board (
  board_idx number(10,0) not null,
 	category varchar2(20) default '제테크노하우',
 	category_num number(10,0) default 5,
 	best_idx number(10,0) not null,
 	id_x number(10,0) not null,
 	id varchar2(20) not null,
 	subject varchar2(100),
 	content varchar2(200),
 	created_date date,
 	hits number(10,0),
 	invest_case char(30) default '성공사례' check (invest_case in ('성공사례','실패사례', '기타')),
 	filename varchar2(50) default null,
 	filesize number default null,
 	step number(10,0) default null,
 	ref number(10,0) default null,
 	depth number(10,0) default null,
primary key (id_x) enable
 ) ;
 
drop table invest_board;
 drop sequence invest_seq;
 select * from invest_board;
 select invest_case from invest_board where ref = 1;
 
 delete invest_board 
 
 
 
 
 /***************************************/
 
/* Best 게시판 View */
 create view best_boardView as select * from (
 					(select category, category_num, best_idx, id, subject, content, created_date, hits, filename, depth from review_board) union
 					(select category, category_num, best_idx, id, subject, content, created_date, hits, filename, depth from tlb_free_board) union 
 					(select category, category_num, best_idx, id, subject, content, created_date, hits, filename, depth  from tlb_meeting_board) union 
 					(select category, category_num, best_idx, id, subject, content, created_date, hits, filename, depth from tlb_debate_board) union 
 					(select category, category_num, best_idx, id, subject, content, created_date, hits, filename, depth from invest_board)
 					);
 drop view best_boardView ;	
 select * from best_boardView where rownum <=5 and hits is not null order by hits desc;
 select category, category_num, best_idx, id, subject, created_date, hits, 
 from best_boardView where rownum <=5 and hits is not null order by hits desc;

* 내가 작성한 글 게시판 View */

create view myboardview as select * from (
					(select board_idx, id, subject, content, created_date, hits, filename, filesize, category_num from review_board) union
					(select board_idx, id, subject, content, created_date, hits, filename, filesize, category_num from TLB_free_BOARD) union 
					(select board_idx, id, subject, content, created_date, hits, filename, filesize, category_num from tlb_meeting_board) union 
					(select board_idx, id, subject, content, created_date, hits, filename, filesize, category_num from tlb_debate_board) union
					(select board_idx, id, subject, content, created_date, hits, filename, filesize, category_num from invest_board) union
					(select board_idx, id, subject, content, created_date, hits, filename, filesize, category_num from qna_board)
					) ;
					

drop view myboardview;		
select * from myboardview;



/* 검색용 전체 게시판 View */
 /*category_num 기준*/
 리뷰게시판 1 자유게시판 2 모임게시판 3 토론게시판 4 재테크노하우 5
 QnA 6 FAQ 7 관련뉴스 8 공시사항 9
 create view View_Full_Board as select * from (
					(select board_idx, category_num, id, subject, content, created_date, hits, filename, filesize from review_board) union
					(select board_idx, category_num, id, subject, content, created_date, hits, filename, filesize from TLB_free_BOARD) union 
					(select board_idx, category_num, id, subject, content, created_date, hits, filename, filesize from tlb_meeting_board) union 
					(select board_idx, category_num, id, subject, content, created_date, hits, filename, filesize from tlb_debate_board) union
					(select board_idx, category_num, id, subject, content, created_date, hits, filename, filesize from invest_board) union
					(select board_idx, category_num, id, subject, content, created_date, hits, filename, filesize from qna_board) union
					(select board_idx, category_num, id, subject, content, created_date, hits, filename, filesize from faq_board) union
					(select board_idx, category_num, id, subject, content, created_date, hits, filename, filesize from news_board) union
					(select board_idx, category_num, id, subject, content, created_date, hits, filename, filesize from newnotice_board)
					) ;






