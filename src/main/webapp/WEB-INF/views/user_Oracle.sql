--0: �Ϲ� ���� 1:����

create table tlb_user_board(
	id varchar2(50) not null,
 	passwd varchar2(50) not null,
 	user_name varchar2(50) not null,
 	user_birth varchar2(50) not null,
 	user_email varchar2(50) not null,
 	user_phone varchar2(50) not null,
 	user_address varchar2(1000) ,
	admin char(1) check(admin in('0','1')),
	primary key(id) enable
);

drop table tlb_user_board;
select count(*) from tlb_user_board where  id='js961223' and passwd='11121';
select * from tlb_user_board;
select id from tlb_user_board where user_name='����' and user_birth='2020-11-31' and user_phone='010-5068-7058';


drop table zipcode;

 select * from zipcode where rownum<10;

 
 --������- �����ϴ� ����

insert into faq_board(board_idx,id_x, id, subject, created_date, category, content, filename, filesize)
values(board_seq_idx.nextval,faq_seq.nextval,'admin','Ŀ�´�Ƽ�� ��� ����ϳ���?',SYSDATE, 'etc','����Ʈ ȸ������ �� �̿��Ͻ� �� �ֽ��ϴ�.',null,null);

insert into faq_board(board_idx,id_x, id, subject, created_date, category, content, filename, filesize)
values(board_seq_idx.nextval,faq_seq.nextval,'admin','������ �ȵǿ� ����ϳ���?',SYSDATE, 'signup','�����ͷ� ������Ź�帳�ϴ�.',null,null);

insert into faq_board(board_idx,id_x, id, subject, created_date, category, content, filename, filesize)
values(board_seq_idx.nextval,faq_seq.nextval,'admin','��й�ȣ �н��߾�� ��� ã����?',SYSDATE, 'signup','����Ʈ���� ��й�ȣ ã�� ���ֽø� �˴ϴ�.',null,null);

insert into faq_board(board_idx,id_x, id, subject, created_date, category, content, filename, filesize)
values(board_seq_idx.nextval,faq_seq.nextval,'admin','���� ������� ������� �� �ֳ���?',SYSDATE, 'etc','�ڼ��� ���Ǵ� �� ������ ���� ���ּ���.',null,null);

insert into faq_board(board_idx,id_x, id, subject, created_date, category, content, filename, filesize)
values(board_seq_idx.nextval,faq_seq.nextval,'admin','�ܸ��� ������ �����ΰ���?',SYSDATE, 'etc','���� ���� �ܸ��� ������ ������ ���ݿ� ���ؼ��� ���ڰ� �߻��ϸ�, ������ ���ݿ� ���ڸ� ���� �ݾ�, �� ������ ���ο� ���� ���ڰ� �ݺ������� �߻��ϰ� �˴ϴ�. A������ ������ 5%(�ܸ�)�̸�, B������ ������ 5%(������) �Դϴ�.',null,null);

insert into faq_board(board_idx,id_x, id, subject, created_date, category, content, filename, filesize)
values(board_seq_idx.nextval,faq_seq.nextval,'admin','���ݰ� ������ ���̴� �����ΰ���?',SYSDATE, 'savings','������ �򵷿� ���ڰ� �ٰ�, ������ �����ݾ��� �����Ⱓ���� �����ϴ� ����Դϴ�.',null,null);

insert into faq_board(board_idx,id_x, id, subject, created_date, category, content, filename, filesize)
values(board_seq_idx.nextval,faq_seq.nextval,'admin','ȸ�������ϸ� ����� ��������?',SYSDATE, 'signup','����Ʈ Ŀ�´�Ƽ �Խ����� �̿��Ͻ� �� �ֽ��ϴ�',null,null);

--����
insert into review_board (board_idx, best_idx, id_x, id, subject, created_date, review_case, content, filename, filesize, step, ref, depth, satisfaction, banks, re_productname) 
values(board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'user01','[����] ���ݻ�ǰ ��� ����',SYSDATE, '��Ÿ����','�������� OO���� ������ ���� ���ƿ�',null,null, 0, 0, 0, '�ڡڡڡڡ�  �ſ츸��', '��������', 'OO����');

insert into review_board (board_idx, best_idx, id_x, id, subject, created_date, review_case, content, filename, filesize, step, ref, depth, satisfaction, banks, re_productname) 
values(board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'user51','[����]OurBank �̿� ����',SYSDATE, '��Ÿ����','��/���� ��ǰ���� �� �� �־ ���ƿ�',null,null, 0, 0, 0, '�ڡڡڡڡ�  �ſ츸��', '��������', 'OO����');

insert into review_board (board_idx, best_idx, id_x, id, subject, created_date, review_case, content, filename, filesize, step, ref, depth, satisfaction, banks, re_productname) 
values(board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'user12','[����]�������� OO���� ����^^',SYSDATE, '���ݸ���','OO���� �������� �ʾƼ� ���ƿ�',null,null, 0, 0, 0, '�ڡڡ�  ����', '��������', 'OO����');

insert into review_board (board_idx, best_idx, id_x, id, subject, created_date, review_case, content, filename, filesize, step, ref, depth, satisfaction, banks, re_productname) 
values(board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'user56','[����]OOOO ����',SYSDATE, '���ݸ���','OOOO���� �̿����� ������',null,null, 0, 0, 0, '��  �ſ�Ҹ���', '�츮����', 'OO����');

insert into review_board (board_idx, best_idx, id_x, id, subject, created_date, review_case, content, filename, filesize, step, ref, depth, satisfaction, banks, re_productname) 
values(board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'user25','[����]�������� OO����',SYSDATE, '���ݸ���','�׳� ����ϱ� ���ؿ�',null,null, 0, 0, 0, '�ڡڡ�  ����', '��������', 'OO����');

insert into review_board (board_idx, best_idx, id_x, id, subject, created_date, review_case, content, filename, filesize, step, ref, depth, satisfaction, banks, re_productname) 
values(board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'user03','[����]�������� OO���� ����^^',SYSDATE,'���ݸ���','�������� OO���� �ʹ� ���ؿ�!',  null,null, 0, 0, 0, '�ڡڡڡڡ�  �ſ츸��', '��������', 'OO����');




--�����Խ���


insert into tlb_free_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, free_seq_idnum.nextval, 'user01', '[�����Խ���] ���ݻ�ǰ ��õ���ּ���', '20�� �������ε� ���ݻ�ǰ ��õ���ּ���', SYSDATE, null, null, 0, 0, 0);

insert into tlb_free_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, free_seq_idnum.nextval, 'user51', '[�����Խ���] ���ó��� �����', '���ó��� �����??? ���������� ���ھ��', SYSDATE, null, null, 0, 0, 0);

insert into tlb_free_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, free_seq_idnum.nextval, 'user12', '[�����Խ���] ���ݻ�ǰ ��õ���ּ���', '�������� ���ݻ�ǰ ��õ���ּ���', SYSDATE, null, null, 0, 0, 0);

insert into tlb_free_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, free_seq_idnum.nextval, 'user03', '[�����Խ���] ���������� ���� ���� �� ���ƿ�!!', '�� �ְŷ� �����ε� ���� ���ƿ�', SYSDATE, null, null, 0, 0, 0);

insert into tlb_free_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, free_seq_idnum.nextval, 'user56', '[�����Խ���] OO���� ���ź� �ֳ���?', 'OO���� �ı� �˰�;��', SYSDATE, null, null, 0, 0, 0);

insert into tlb_free_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, free_seq_idnum.nextval, 'user25', '[�����Խ���] OO���� �̺�Ʈ ���̳���', '�ٵ� �����ϼ���!!', SYSDATE, null, null, 0, 0, 0);

insert into tlb_free_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, free_seq_idnum.nextval, 'user27', '[�����Խ���] OO���� ���̳���??', 'OO���� �̿��� ���� �� �ı� �˷��ּ���', SYSDATE, null, null, 0, 0, 0);



--����

insert into tlb_meeting_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, meeting_seq_idnum.nextval, 'user01', '[���ӰԽ���] ������ �����ؿ� ', '������ ��ô� �е� �����ؿ�!', SYSDATE, null,null, 0, 0, 0);

insert into tlb_meeting_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, meeting_seq_idnum.nextval, 'user55', '[���ӰԽ���] ��õ ����� ', '��õ ��ô� �е� �����ؿ�!', SYSDATE, null,null, 0, 0, 0);

insert into tlb_meeting_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, meeting_seq_idnum.nextval, 'user12', '[���ӰԽ���] �츮���� ���ӹ�^^ ', '�츮���� ���� ����Ͻô� �е� �ֳ���??', SYSDATE, null,null, 0, 0, 0);

insert into tlb_meeting_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, meeting_seq_idnum.nextval, 'user27', '[���ӰԽ���] OO���� ����� ', 'OO���� ����ںе� ������ �̾߱��ؿ�', SYSDATE, null,null, 0, 0, 0);

insert into tlb_meeting_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, meeting_seq_idnum.nextval, 'user25', '[���ӰԽ���] �������� ���ӹ��Դϴ� ', '�������� ����ںе� �𿩿�', SYSDATE, null,null, 0, 0, 0);

insert into tlb_meeting_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, meeting_seq_idnum.nextval, 'user03', '[���ӰԽ���] OO���� �����Ͻźе�!! ', 'OO���� �����Ͻźе� ���� �̾߱��ؿ�', SYSDATE, null,null, 0, 0, 0);

insert into tlb_meeting_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, meeting_seq_idnum.nextval, 'user12', '[���ӰԽ���] �������� OO���� �����մϴ� ', '�������� OO���� ����Ͻô� �е� �ֳ���??', SYSDATE, null,null, 0, 0, 0);


--���


insert into tlb_debate_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, debate_seq_idnum.nextval, 'user01', '[��аԽ���] ������� OO���� �̾߱��ؿ�', 'OO���� �����ϰ� ������ ����̿���', SYSDATE, null, null, 0, 0, 0);

insert into tlb_debate_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, debate_seq_idnum.nextval, 'user33', '[��аԽ���] OO���� �̺�Ʈ ���̳���??', '�̺�Ʈ �����ϼ���', SYSDATE, null, null, 0, 0, 0);

insert into tlb_debate_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, debate_seq_idnum.nextval, 'user55', '[��аԽ���] ��õ���� ��й��Դϴ�', '��õ�����е� �ֱ� �̽��Ǵ� ������ ����ؿ�', SYSDATE, null, null, 0, 0, 0);

insert into tlb_debate_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, debate_seq_idnum.nextval, 'user12', '[��аԽ���] �츮���� OO���� ���', '�츮���� ���� ��������??', SYSDATE, null, null, 0, 0, 0);

insert into tlb_debate_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, debate_seq_idnum.nextval, 'user33', '[��аԽ���] OO���� ���̳���', 'OO���� ���źе� �� ��� ����ּ���', SYSDATE, null, null, 0, 0, 0);

insert into tlb_debate_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, debate_seq_idnum.nextval, 'user25', '[��аԽ���] ����սô�', '�پ��� ������ ����ؿ�', SYSDATE, null, null, 0, 0, 0);

insert into tlb_debate_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, debate_seq_idnum.nextval, 'user27', '[��аԽ���] OO���� ��й�', 'OO���ݿ� ���� �̾߱� ������ �;��', SYSDATE, null, null, 0, 0, 0);




--����ũ ���Ͽ�

insert into invest_board(board_idx, best_idx, id_x, id, subject, created_date, invest_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'admin','[����ũ���Ͽ�] ��ȸ �ʳ�� �����ϴ� ����ũ ���Ͽ�',SYSDATE, '�������', 'content',null,null, 0, 0, 0);

insert into invest_board(board_idx, best_idx, id_x, id, subject, created_date, invest_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'admin','[����ũ���Ͽ�] �������� 2�� ������',SYSDATE, '��Ÿ', 'content',null,null, 0, 0, 0);

insert into invest_board(board_idx, best_idx, id_x, id, subject, created_date, invest_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'admin','[����ũ���Ͽ�] ������ ���ױ� ���',SYSDATE, '�������', 'content',null,null, 0, 0, 0);

insert into invest_board(board_idx, best_idx, id_x, id, subject, created_date, invest_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'admin','[����ũ���Ͽ�] ����ũ 20���� ��Ģ',SYSDATE, '��Ÿ', 'content',null,null, 0, 0, 0);

insert into invest_board(board_idx, best_idx, id_x, id, subject, created_date, invest_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'admin','[����ũ���Ͽ�] ����� ������ ����ũ��..',SYSDATE, '�������', 'content',null,null, 0, 0, 0);

insert into invest_board(board_idx, best_idx, id_x, id, subject, created_date, invest_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'admin','[����ũ���Ͽ�] �ܰ躰 ����ũ ���Ͽ�',SYSDATE, '�������', 'content',null,null, 0, 0, 0);

insert into invest_board(board_idx, best_idx, id_x, id, subject, created_date, invest_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'admin','[����ũ���Ͽ�] �̰͸��� ����������',SYSDATE, '���л��', 'content',null,null, 0, 0, 0);





--������-q/a


insert into qna_board(board_idx, id_x, id, subject, created_date, reply,category, content, filename, filesize)
values(board_seq_idx.nextval, qna_seq.nextval,'user01','[����] Ŀ�´�Ƽ �̿��ϰ� �;��',SYSDATE,0, 'signup','Ŀ�´�Ƽ �̿��Ϸ��� ��� �ؾ��ؿ�?',null,null);

insert into qna_board(board_idx, id_x, id, subject, created_date, reply,category, content, filename, filesize)
values(board_seq_idx.nextval, qna_seq.nextval,'user01','[����] ��ǰ���� ��� �ؾߵǿ�??',SYSDATE,0, 'savings','��ǰ������ ��� �ϳ���?',null,null);

insert into qna_board(board_idx, id_x, id, subject, created_date, reply,category, content, filename, filesize)
values(board_seq_idx.nextval, qna_seq.nextval,'user55','[����] ���ݻ�ǰ ��õ���ּ���',SYSDATE,0, 'savings','������ �´� ���ݻ�ǰ ��õ���ּ���',null,null);

insert into qna_board(board_idx, id_x, id, subject, created_date, reply,category, content, filename, filesize)
values(board_seq_idx.nextval, qna_seq.nextval,'user33','[����] ���ݻ�ǰ ��õ���ּ���',SYSDATE,0, 'savings','������ �´� ���ݻ�ǰ ��õ���ּ���',null,null);

insert into qna_board(board_idx, id_x, id, subject, created_date, reply,category, content, filename, filesize)
values(board_seq_idx.nextval, qna_seq.nextval,'user12','[����] ȸ�������� ��� �ϳ���?',SYSDATE,0, 'signup','ȸ�������� ����ؿ�?',null,null);

insert into qna_board(board_idx, id_x, id, subject, created_date, reply,category, content, filename, filesize)
values(board_seq_idx.nextval, qna_seq.nextval,'user03','[����] ������ ��ȭ��ȣ �˷��ּ���',SYSDATE,0, 'etc','������ �ִµ� ������ ��ȭ��ȣ�� �˷��ּ���',null,null);


--�������� ���ҽ�


insert into newnotice_board(board_idx, best_idx, idx, id, subject, created_date, newnotice_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, newnotice_seq.nextval,'admin','[����] ���������̿��ħ',SYSDATE, '��Ÿ����','content',null,null,0, 0, 0);

insert into newnotice_board(board_idx, best_idx, idx, id, subject, created_date, newnotice_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, newnotice_seq.nextval,'admin','[����] ��ǰ���� �� ���ǻ���',SYSDATE, '��ǰ������','content',null,null,0, 0, 0);

insert into newnotice_board(board_idx, best_idx, idx, id, subject, created_date, newnotice_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, newnotice_seq.nextval,'admin','[����] ���� �̿� �� ���ǻ���',SYSDATE, '���ະ����','content',null,null,0, 0, 0);

insert into newnotice_board(board_idx, best_idx, idx, id, subject, created_date, newnotice_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, newnotice_seq.nextval,'admin','[����] Ŀ�´�Ƽ �̿� �� ���ǻ���',SYSDATE, '��Ÿ����','content',null,null,0, 0, 0);

insert into newnotice_board(board_idx, best_idx, idx, id, subject, created_date, newnotice_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, newnotice_seq.nextval,'admin','[����] ����Ʈ �̿���',SYSDATE, '��Ÿ����','content',null,null,0, 0, 0);




--����������


insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[�츮����] ����������� �������� �����ŷ����� Ȯ��', sysdate, '���ô���','content',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[�츮����] �ݸ�������� ��� ���', sysdate, '���ô���','�ݸ�������� �̿��� �ȳ� 1. ����ġ������ ��ġ�մϴ�. - ���� �޴����� ����ȭ���� ��ġ�մϴ�. - �ݸ������������� ��ġ�Ͽ� �ּ���',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[��������] �����Ա�� ��� ȯ����ũ ��������', sysdate, '���ô���','KB���������� ���� 25�� �뱸 ȣ�����ͺҰ��� �뱸��������� ������ ��� �繫����� 80�� ���� ��û�� ��� ����� ��û ȯ����ũ ���� ���̳��� �����ߴٰ� ������.
�̳� ���̳������� KB���� ������ ���� ���ڳ�̽�Ʈ�� ����� ������ `2019�� �Ϲݱ� ȯ������`�� ������ �̱� Ʈ���� ������ ��� ������ ������ ���������� �̽�, �ֿ� ��ȭ�� ������ ���캸�� �Ϲݱ� ȯ�������� ���� ��ǥ�ߴ�.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[��������] �����ڷγ����̷������ر�� ��������', sysdate, '���ô���','���������� ���� �ڷγ� ���̷��� Ȯ������ ���� ���ظ� ���� �߼ұ���� ������� ���������� �ǽ��Ѵٰ� 31�� ������.
���� �ڷγ� ���̷��� �������� Ȯ��ʿ� ���� �ؿܿ��� ���䰡 ũ�� �����ϰ� ������ ������ �߱� ������ ���� �پ����� �̷� ���� ����, ����, ������ �� ������ ������� ���� ���� �� ���ظ� �԰� �ִ�.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[��������] ��24�� �����߾�ȸ�忡 �̼��� �ĺ� �缱 ', sysdate, '���ô���','��δ�������� �Ҹ��� �����߾�ȸ�� ���ſ� �ִ� �ĺ��� �⸶�ϸ� ������� �̸��� ���߽�Ų ��� �̼��� �� �����߾�ȸ ������������ ��24�� �����߾�ȸ�忡 �缱�ƴ�.
��24�� �����߾�ȸ�� ���Ŵ� 31�� ����� �߱� �����߾�ȸ ���� ��ȸ�ǽǿ��� ���ֵƴ�. �̳� ��ǥ���� �����߾�ȸ ������ ���� ȸ�� ���⿡ ���� ��밨���� �̸� ��ħ���� �����ߴ�. ',null,null, 0, 0, 
0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[��������] �����ŷ ����(Liiv) ATM ��ݼ����� ����', sysdate, '���ô���','KB���������� �����ŷ�� ����(Liiv)�� �̿��� ATM ��� �� ��� ������ �����Ḧ �����Ѵٰ� 18�� ������.
����(Liiv)�� KB���������� ��ǥ���� ���� �÷������� ������������ ��Ÿ ���ȸ�ü ���̵� ����۱ݡ����⡤��ȯ�������������ϱ⡤�������� ���� �پ��� ������Ȱ�� ���ϰ� �̿��� �� �ִ�.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[��������] ���̹� ���Ǻ�� �������� ü�� ', sysdate, '���ô���','�� ����� �̹� ���� 2019�� ���̹����Ǻ��� ���������� ���α׷��� Ȱ���� ����������� �Բ� �������� �׸��� �÷����� ����� �¡��������� ���� ��ȸ��ġ â�� ���� ���ȭ�� ������ �ִ�. ������� ���̹� ���� �ý����� ������� ��ȸ�� ����̳� ��ȸ�� ��ü�� ����� �Ϲ� �����ڵ��� ������ �� �ֵ��� ���� �Ű����� ���� �� ��ȸ ���� ���α׷��̴�.
�̹� ������ �������� ������ �������� ��ȭ�ϴ� �ô��� Ʈ���带 �ݿ��� ���ο� ��� ���� �����Ѵٴ� ������ �ǹ̰� �ִ�.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[�츮����] �츮���� â�� 118��', sysdate, '����ȸ������','�츮����(������ �̱���, www.wooribank.com)�� 4�� ���� �߱� �Ұ��� ���� ���� ���翡�� â�� 118�ֳ� ������ �����ߴٰ� ������.
Ư�� �ο�ȭ ���� ù ��°�� ���ֵ� �� ��翡�� ���� 12�� 30�� ���Ӱ� ���ӵ� ����̻� 5�� �����Ͽ� �츮������ ����� �̷� ������ �����ϴ� �� ���� �ð��� ������.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[�츮����] CEO �Ұ�', sysdate, '����ȸ������','�ȳ��Ͻʴϱ�?
�츮������ ���½��Դϴ�.
�츮������ ã���ֽ� ���Բ� ����� ȯ���� �λ縦 �帳�ϴ�.
�츮������ 1899��, �ڱ��帧�� ��Ȱ�� �Ͽ� ���� ������ �̹��� �Ѵٴ�
�������� ������ ���� ������ ���� ���� �����Դϴ�.
�츮������ ���Ѹ��� ���ȭ�ô�, IMF ȯ���� �۷ι� �������⸦ �����鼭
���ѹα� ������ ����� ���������� ������ �Խ��ϴ�.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[��������] CEO �Ұ�', sysdate, '����ȸ������','���� ���� �߽��� ������ KB�� �ŵ쳪
�ΰ����ɰ� 5G�� ��ǥ�Ǵ� �ʿ��� �ô밡 �����ϴ� ����, ��ȭ�� ������ ������ ���� ����(��٤)�� �Ǿ��ٸ� ���� ���� ���� �߽��� ������ KB�� ������?�������� ������ȭ�� �����ϴ� �͵� �̷��� ������ ����ȯ���� �帧�� ������ �� �̶� ���� KB�������� 19�� �Ϲݱ� ��ȸ�翡�� ���ߴ�.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[��������] CEO �Ұ�', sysdate, '����ȸ������','���������� ������ 2�� ���� �߱� ������� ���� �������� �������� 2018�� �ù����� �ǽ��ߴٰ� ������. �̳� ����ȣ �������� ���� ���ֵ��� �Ƴ����� ������ ������ ������ ���ϰ� ���� ������ǥ�� Redefine ����, Be the NEXT���� �����ϰ� ������ ��2018 �롤�衤��(�ס��ᡤ��) ���������� �����ߴٰ� ������.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[��������] CEO �Ұ�', sysdate, '����ȸ������','�����ϴ� �������� ����!
�� ���� ���� �������� �տ� ���� ���� ���� ��źġ ���� ������ ����˴ϴ�.
������ ���� ������ ��δ� ���ָ� ���ٺ��� �̷��� ����Ͽ� �غ� �Ѵ١��� �ÿ�����(��������)�� �ڼ��� �̷� �ٽ� ����� ���� �����ϰ�
���ͱ���� ��ȭ�� �����鼭 ��ȭ�ϴ� ����ȯ�� �ӿ��� ������������ ������ �� �ֵ��� �ּ��� ����� �����ϰڽ��ϴ�.
������ ���� �ŷڹް� ����ΰ� ������ �ູ�� ������ ����� ���� ���������� ȥ���� ���� ���ϰڽ��ϴ�.
NH�������� ������
�̴��� �ø�',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[��������] �̼�/����/�ٽɰ�ġ', sysdate, '����ȸ������','�̼�(Mission)
������ �ٲٴ� ���� - ���� �ູ�� �� ���� ������ �����ϴ�.
����(Vision)
�ְ��� ����� ����� �������� ���� �ŷڹ޴� ���������Ʈ��
�ٽɰ�ġ
���߽�',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[��������] ��õ���α׷�', sysdate, '����ȸ������','���������� ��ȸ�� ���� ��ü�� ��ȸ�� ������κ��� ����Ʈ�� �����ϰ� �������� ����Ȱ���� �ϸ� ����Ʈ�� �����ִ� ���ο� ������ ��ȸ����Ȱ���� �����δ�.
�ش� ���̵��� ���� 20�� ���� 2019 ��ȸ����Ȱ�� ������� ������ ���޽Ŀ��� ������ ������������ ������ ��� �ǹ��� ���䰡 ���۵ƴ�.',null,null, 0, 0, 0);









