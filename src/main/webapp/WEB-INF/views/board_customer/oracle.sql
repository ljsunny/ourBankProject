/* faq�Խù�  */
create sequence faq_seq minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
 
create table faq_board (
board_idx number(10,0) not null, /*��ü�� ����*/
	category_num number(10,0) default 7, /*��ü �Խ��Ǻ� ����*/
 	id_x number(10,0) not null,
 	id varchar2(20) not null,
 	subject varchar2(50),
 	content varchar2(700),
 	created_date date,
 	hits number(10,0),
 	category char(10) default 'signup' check (category in ('signup','savings', 'etc')),
 	filename varchar2(50) default null,
 	filesize number default null,
primary key (id_x) enable
 ) ;
 
 drop table faq_board;
 
select * from faq_board;
  

/* qna �Խù� */
create sequence qna_seq minvalue 1 maxvalue 9999999999 increment by 1 start with 1 ;
 
-- 0�̸� ���� 1�̸� ���
 
create table qna_board (
	board_idx number(10,0) not null, /*��ü�� ����*/
	category_num number(10,0) default 6, /*��ü �Խ��Ǻ� ����*/
 	id_x number(10,0) not null,
 	id varchar2(20) not null,
 	subject varchar2(50),
 	content varchar2(700),
 	created_date date,
 	hits number(10,0),
 	reply number(10,0) default null,
 	category char(10) default 'signup' check (category in ('signup','savings', 'etc')),
 	filename varchar2(50) default null,
 	filesize number default null,
primary key (id_x) enable
 ) ;
 
 drop table qna_board;
  