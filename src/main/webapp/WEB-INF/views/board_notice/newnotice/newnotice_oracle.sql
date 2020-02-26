create sequence newnotice_seq minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
 
-- 0이면 본글 1이면 답글
 
create table newnotice_board (
	board_idx number(10,0) not null,
	category varchar2(20) default '기타공지',
    category_num number(10,0) default 1,
    best_idx number(10,0) not null,
 	idx number(10,0) not null,
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
 	newnotice_case char(20) default '은행별공지' check (newnotice_case in ('은행별공지', '상품별공지', '기타공지')),
 	
primary key (idx) enable
 ) ;
 
 drop table newnotice_board;
 
 select * from newnotice_board;

 delete from newnotice_board where id_x=23;
 
 insert into newnotice_board(board_idx, best_idx, idx, id, subject, created_date, newnotice_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, newnotice_seq.nextval,'exId','개인정보',SYSDATE, '은행별공지',  
					'ㅇㅇ',null,null,0, 0, 0)