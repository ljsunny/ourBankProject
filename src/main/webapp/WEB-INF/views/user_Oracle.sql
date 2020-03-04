--0: 일반 유저 1:어드민

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
select id from tlb_user_board where user_name='어드민' and user_birth='2020-11-31' and user_phone='010-5068-7058';


drop table zipcode;

 select * from zipcode where rownum<10;

 
 --고객센터- 자주하는 질문

insert into faq_board(board_idx,id_x, id, subject, created_date, category, content, filename, filesize)
values(board_seq_idx.nextval,faq_seq.nextval,'admin','커뮤니티는 어떻게 사용하나요?',SYSDATE, 'etc','사이트 회원가입 후 이용하실 수 있습니다.',null,null);

insert into faq_board(board_idx,id_x, id, subject, created_date, category, content, filename, filesize)
values(board_seq_idx.nextval,faq_seq.nextval,'admin','인증이 안되요 어떻게하나요?',SYSDATE, 'signup','고객센터로 연락부탁드립니다.',null,null);

insert into faq_board(board_idx,id_x, id, subject, created_date, category, content, filename, filesize)
values(board_seq_idx.nextval,faq_seq.nextval,'admin','비밀번호 분실했어요 어떻게 찾나요?',SYSDATE, 'signup','사이트에서 비밀번호 찾기 해주시면 됩니다.',null,null);

insert into faq_board(board_idx,id_x, id, subject, created_date, category, content, filename, filesize)
values(board_seq_idx.nextval,faq_seq.nextval,'admin','나도 비과세를 적용받을 수 있나요?',SYSDATE, 'etc','자세한 문의는 각 은행을 통해 해주세요.',null,null);

insert into faq_board(board_idx,id_x, id, subject, created_date, category, content, filename, filesize)
values(board_seq_idx.nextval,faq_seq.nextval,'admin','단리와 복리는 무엇인가요?',SYSDATE, 'etc','쉽게 말해 단리는 기존에 불입한 원금에 대해서만 이자가 발생하며, 복리는 원금에 이자를 합한 금액, 즉 원리금 전부에 대해 이자가 반복적으로 발생하게 됩니다. A은행은 연이율 5%(단리)이며, B은행은 연이율 5%(월복리) 입니다.',null,null);

insert into faq_board(board_idx,id_x, id, subject, created_date, category, content, filename, filesize)
values(board_seq_idx.nextval,faq_seq.nextval,'admin','예금과 적금의 차이는 무엇인가요?',SYSDATE, 'savings','예금은 몫돈에 이자가 붙고, 적금은 일정금액을 일정기간동안 납부하는 방법입니다.',null,null);

insert into faq_board(board_idx,id_x, id, subject, created_date, category, content, filename, filesize)
values(board_seq_idx.nextval,faq_seq.nextval,'admin','회원가입하면 어떤점이 좋은가요?',SYSDATE, 'signup','사이트 커뮤니티 게시판을 이용하실 수 있습니다',null,null);

--리뷰
insert into review_board (board_idx, best_idx, id_x, id, subject, created_date, review_case, content, filename, filesize, step, ref, depth, satisfaction, banks, re_productname) 
values(board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'user01','[리뷰] 적금상품 상담 리뷰',SYSDATE, '기타리뷰','국민은행 OO적금 이율도 높고 좋아요',null,null, 0, 0, 0, '★★★★★  매우만족', '국민은행', 'OO적금');

insert into review_board (board_idx, best_idx, id_x, id, subject, created_date, review_case, content, filename, filesize, step, ref, depth, satisfaction, banks, re_productname) 
values(board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'user51','[리뷰]OurBank 이용 리뷰',SYSDATE, '기타리뷰','예/적금 상품별로 볼 수 있어서 좋아요',null,null, 0, 0, 0, '★★★★★  매우만족', '신한은행', 'OO예금');

insert into review_board (board_idx, best_idx, id_x, id, subject, created_date, review_case, content, filename, filesize, step, ref, depth, satisfaction, banks, re_productname) 
values(board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'user12','[리뷰]농협은행 OO적금 리뷰^^',SYSDATE, '적금리뷰','OO적금 위험하지 않아서 좋아요',null,null, 0, 0, 0, '★★★  보통', '농협은행', 'OO적금');

insert into review_board (board_idx, best_idx, id_x, id, subject, created_date, review_case, content, filename, filesize, step, ref, depth, satisfaction, banks, re_productname) 
values(board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'user56','[리뷰]OOOO 예금',SYSDATE, '예금리뷰','OOOO예금 이용하지 마세요',null,null, 0, 0, 0, '★  매우불만족', '우리은행', 'OO적금');

insert into review_board (board_idx, best_idx, id_x, id, subject, created_date, review_case, content, filename, filesize, step, ref, depth, satisfaction, banks, re_productname) 
values(board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'user25','[리뷰]국민은행 OO예금',SYSDATE, '예금리뷰','그냥 사용하기 편해요',null,null, 0, 0, 0, '★★★  보통', '국민은행', 'OO예금');

insert into review_board (board_idx, best_idx, id_x, id, subject, created_date, review_case, content, filename, filesize, step, ref, depth, satisfaction, banks, re_productname) 
values(board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'user03','[리뷰]신한은행 OO적금 리뷰^^',SYSDATE,'적금리뷰','신한은행 OO적금 너무 편해요!',  null,null, 0, 0, 0, '★★★★★  매우만족', '신한은행', 'OO적금');




--자유게시판


insert into tlb_free_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, free_seq_idnum.nextval, 'user01', '[자유게시판] 예금상품 추천해주세요', '20대 직장인인데 예금상품 추천해주세요', SYSDATE, null, null, 0, 0, 0);

insert into tlb_free_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, free_seq_idnum.nextval, 'user51', '[자유게시판] 오늘날씨 어떤가요', '오늘날씨 어떤가요??? 따뜻했으면 좋겠어요', SYSDATE, null, null, 0, 0, 0);

insert into tlb_free_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, free_seq_idnum.nextval, 'user12', '[자유게시판] 적금상품 추천해주세요', '이율높은 적금상품 추천해주세요', SYSDATE, null, null, 0, 0, 0);

insert into tlb_free_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, free_seq_idnum.nextval, 'user03', '[자유게시판] 신한은행이 제일 좋은 것 같아요!!', '제 주거래 은행인데 제일 좋아요', SYSDATE, null, null, 0, 0, 0);

insert into tlb_free_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, free_seq_idnum.nextval, 'user56', '[자유게시판] OO예금 들어보신분 있나요?', 'OO예금 후기 알고싶어요', SYSDATE, null, null, 0, 0, 0);

insert into tlb_free_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, free_seq_idnum.nextval, 'user25', '[자유게시판] OO적금 이벤트 보셨나요', '다들 참여하세요!!', SYSDATE, null, null, 0, 0, 0);

insert into tlb_free_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, free_seq_idnum.nextval, 'user27', '[자유게시판] OO적금 들어보셨나요??', 'OO적금 이용해 보신 분 후기 알려주세요', SYSDATE, null, null, 0, 0, 0);



--모임

insert into tlb_meeting_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, meeting_seq_idnum.nextval, 'user01', '[모임게시판] 의정부 정모해요 ', '의정부 사시는 분들 정모해요!', SYSDATE, null,null, 0, 0, 0);

insert into tlb_meeting_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, meeting_seq_idnum.nextval, 'user55', '[모임게시판] 인천 정모방 ', '인천 사시는 분들 정모해요!', SYSDATE, null,null, 0, 0, 0);

insert into tlb_meeting_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, meeting_seq_idnum.nextval, 'user12', '[모임게시판] 우리은행 모임방^^ ', '우리은행 자주 사용하시는 분들 있나요??', SYSDATE, null,null, 0, 0, 0);

insert into tlb_meeting_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, meeting_seq_idnum.nextval, 'user27', '[모임게시판] OO적금 정모방 ', 'OO적금 사용자분들 만나서 이야기해요', SYSDATE, null,null, 0, 0, 0);

insert into tlb_meeting_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, meeting_seq_idnum.nextval, 'user25', '[모임게시판] 국민은행 모임방입니다 ', '국민은행 사용자분들 모여요', SYSDATE, null,null, 0, 0, 0);

insert into tlb_meeting_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, meeting_seq_idnum.nextval, 'user03', '[모임게시판] OO적금 가입하신분들!! ', 'OO적금 가입하신분들 같이 이야기해요', SYSDATE, null,null, 0, 0, 0);

insert into tlb_meeting_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, meeting_seq_idnum.nextval, 'user12', '[모임게시판] 농협은행 OO예금 정모합니다 ', '농협은행 OO예금 사용하시는 분들 있나요??', SYSDATE, null,null, 0, 0, 0);


--토론


insert into tlb_debate_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, debate_seq_idnum.nextval, 'user01', '[토론게시판] 기업은행 OO예금 이야기해요', 'OO예금 가입하고 싶은데 고민이에요', SYSDATE, null, null, 0, 0, 0);

insert into tlb_debate_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, debate_seq_idnum.nextval, 'user33', '[토론게시판] OO적금 이벤트 보셨나요??', '이벤트 참여하세요', SYSDATE, null, null, 0, 0, 0);

insert into tlb_debate_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, debate_seq_idnum.nextval, 'user55', '[토론게시판] 인천지역 토론방입니다', '인천지역분들 최근 이슈되는 문제들 토론해요', SYSDATE, null, null, 0, 0, 0);

insert into tlb_debate_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, debate_seq_idnum.nextval, 'user12', '[토론게시판] 우리은행 OO예금 토론', '우리은행 예금 좋은가요??', SYSDATE, null, null, 0, 0, 0);

insert into tlb_debate_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, debate_seq_idnum.nextval, 'user33', '[토론게시판] OO적금 들어보셨나요', 'OO적금 들어보신분들 제 얘기 들어주세요', SYSDATE, null, null, 0, 0, 0);

insert into tlb_debate_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, debate_seq_idnum.nextval, 'user25', '[토론게시판] 토론합시다', '다양한 주제로 토론해요', SYSDATE, null, null, 0, 0, 0);

insert into tlb_debate_board(board_idx, best_idx, idx_num, id, subject, content, created_date, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, debate_seq_idnum.nextval, 'user27', '[토론게시판] OO적금 토론방', 'OO적금에 대해 이야기 나누고 싶어요', SYSDATE, null, null, 0, 0, 0);




--재태크 노하우

insert into invest_board(board_idx, best_idx, id_x, id, subject, created_date, invest_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'admin','[재테크노하우] 사회 초년생 성공하는 재테크 노하우',SYSDATE, '성공사례', 'content',null,null, 0, 0, 0);

insert into invest_board(board_idx, best_idx, id_x, id, subject, created_date, invest_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'admin','[재테크노하우] 적금으로 2억 모으기',SYSDATE, '기타', 'content',null,null, 0, 0, 0);

insert into invest_board(board_idx, best_idx, id_x, id, subject, created_date, invest_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'admin','[재테크노하우] 직장인 재테그 방법',SYSDATE, '성공사례', 'content',null,null, 0, 0, 0);

insert into invest_board(board_idx, best_idx, id_x, id, subject, created_date, invest_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'admin','[재테크노하우] 재테크 20가지 규칙',SYSDATE, '기타', 'content',null,null, 0, 0, 0);

insert into invest_board(board_idx, best_idx, id_x, id, subject, created_date, invest_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'admin','[재테크노하우] 평범한 직장인 재테크로..',SYSDATE, '성공사례', 'content',null,null, 0, 0, 0);

insert into invest_board(board_idx, best_idx, id_x, id, subject, created_date, invest_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'admin','[재테크노하우] 단계별 재테크 노하우',SYSDATE, '성공사례', 'content',null,null, 0, 0, 0);

insert into invest_board(board_idx, best_idx, id_x, id, subject, created_date, invest_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, invest_seq.nextval,'admin','[재테크노하우] 이것만은 하지마세요',SYSDATE, '실패사례', 'content',null,null, 0, 0, 0);





--고객센터-q/a


insert into qna_board(board_idx, id_x, id, subject, created_date, reply,category, content, filename, filesize)
values(board_seq_idx.nextval, qna_seq.nextval,'user01','[질문] 커뮤니티 이용하고 싶어요',SYSDATE,0, 'signup','커뮤니티 이용하려면 어떻게 해야해요?',null,null);

insert into qna_board(board_idx, id_x, id, subject, created_date, reply,category, content, filename, filesize)
values(board_seq_idx.nextval, qna_seq.nextval,'user01','[질문] 상품가입 어떻게 해야되요??',SYSDATE,0, 'savings','상품가입은 어떻게 하나요?',null,null);

insert into qna_board(board_idx, id_x, id, subject, created_date, reply,category, content, filename, filesize)
values(board_seq_idx.nextval, qna_seq.nextval,'user55','[질문] 예금상품 추천해주세요',SYSDATE,0, 'savings','저에게 맞는 예금상품 추천해주세요',null,null);

insert into qna_board(board_idx, id_x, id, subject, created_date, reply,category, content, filename, filesize)
values(board_seq_idx.nextval, qna_seq.nextval,'user33','[질문] 적금상품 추천해주세요',SYSDATE,0, 'savings','저에게 맞는 적금상품 추천해주세요',null,null);

insert into qna_board(board_idx, id_x, id, subject, created_date, reply,category, content, filename, filesize)
values(board_seq_idx.nextval, qna_seq.nextval,'user12','[질문] 회원가입은 어디서 하나요?',SYSDATE,0, 'signup','회원가입은 어디서해요?',null,null);

insert into qna_board(board_idx, id_x, id, subject, created_date, reply,category, content, filename, filesize)
values(board_seq_idx.nextval, qna_seq.nextval,'user03','[질문] 고객센터 전화번호 알려주세요',SYSDATE,0, 'etc','질문이 있는데 고객센터 전화번호좀 알려주세요',null,null);


--공지사항 새소식


insert into newnotice_board(board_idx, best_idx, idx, id, subject, created_date, newnotice_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, newnotice_seq.nextval,'admin','[공지] 개인정보이용방침',SYSDATE, '기타공지','content',null,null,0, 0, 0);

insert into newnotice_board(board_idx, best_idx, idx, id, subject, created_date, newnotice_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, newnotice_seq.nextval,'admin','[공지] 상품가입 시 주의사항',SYSDATE, '상품별공지','content',null,null,0, 0, 0);

insert into newnotice_board(board_idx, best_idx, idx, id, subject, created_date, newnotice_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, newnotice_seq.nextval,'admin','[공지] 은행 이용 시 주의사항',SYSDATE, '은행별공지','content',null,null,0, 0, 0);

insert into newnotice_board(board_idx, best_idx, idx, id, subject, created_date, newnotice_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, newnotice_seq.nextval,'admin','[공지] 커뮤니티 이용 시 주의사항',SYSDATE, '기타공지','content',null,null,0, 0, 0);

insert into newnotice_board(board_idx, best_idx, idx, id, subject, created_date, newnotice_case, content, filename, filesize, step, ref, depth) 
values (board_seq_idx.nextval, best_seq_idx.nextval, newnotice_seq.nextval,'admin','[공지] 사이트 이용방법',SYSDATE, '기타공지','content',null,null,0, 0, 0);




--뉴스와정보


insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[우리은행] 대포통장계좌 명의인의 금융거래제한 확대', sysdate, '관련뉴스','content',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[우리은행] 금리우대쿠폰 등록 방법', sysdate, '관련뉴스','금리우대쿠폰 이용방법 안내 1. 원터치개인을 설치합니다. - 메인 메뉴에서 금융화면을 터치합니다. - 금리우대쿠폰등록을 터치하여 주세요',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[국민은행] 수출입기업 대상 환리스크 전략제시', sysdate, '관련뉴스','KB국민은행은 지난 25일 대구 호텔인터불고에서 대구·경북지역 수출입 기업 재무담당자 80여 명을 초청해 우수 기업고객 초청 환리스크 관리 세미나를 개최했다고 밝혔다.
이날 세미나에서는 KB증권 문정희 수석 이코노미스트가 강사로 참여해 `2019년 하반기 환율전망`을 주제로 미국 트럼프 행정부 출범 이후의 국내외 금융시장의 이슈, 주요 통화별 동향을 살펴보고 하반기 환율전망에 대해 발표했다.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[신한은행] 신종코로나바이러스피해기업 금융지원', sysdate, '관련뉴스','신한은행은 신종 코로나 바이러스 확산으로 인해 피해를 입은 중소기업을 대상으로 금융지원을 실시한다고 31일 밝혔다.
신종 코로나 바이러스 감염병이 확산됨에 따라 해외여행 수요가 크게 감소하고 국내로 들어오는 중국 관광객 또한 줄었으며 이로 인해 여행, 숙박, 음식점 등 업종의 기업들이 매출 감소 등 피해를 입고 있다.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[농협은행] 제24대 농협중앙회장에 이성희 후보 당선 ', sysdate, '관련뉴스','농민대통령으로 불리는 농협중앙회장 선거에 최다 후보가 출마하며 농업계의 이목을 집중시킨 가운데 이성희 전 농협중앙회 감사위원장이 제24대 농협중앙회장에 당선됐다.
제24대 농협중앙회장 선거는 31일 서울시 중구 농협중앙회 본관 대회의실에서 개최됐다. 이날 투표소인 농협중앙회 본관은 신임 회장 선출에 대한 기대감으로 이른 아침부터 분주했다. ',null,null, 0, 0, 
0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[국민은행] 간평뱅킹 리브(Liiv) ATM 출금수수료 면제', sysdate, '관련뉴스','KB국민은행은 간편뱅킹앱 리브(Liiv)를 이용한 ATM 출금 시 모든 고객에게 수수료를 면제한다고 18일 밝혔다.
리브(Liiv)는 KB국민은행의 대표적인 비대면 플랫폼으로 공인인증서나 기타 보안매체 없이도 간편송금·대출·외환·결제·선물하기·교통충전 등의 다양한 금융생활을 편리하게 이용할 수 있다.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[신한은행] 네이버 해피빈과 업무협약 체결 ', sysdate, '관련뉴스','두 기업은 이미 지난 2019년 네이버해피빈의 ‘가볼까’라는 프로그램을 활용한 ‘신한은행과 함께 가볼까’라는 테마로 플랫폼에 기반한 온·오프라인 연계 사회가치 창출 모델을 사업화한 경험이 있다. 가볼까는 네이버 예약 시스템을 기반으로 사회적 기업이나 사회적 단체의 사업에 일반 참여자들이 동참할 수 있도록 만든 신개념의 봉사 및 사회 참여 프로그램이다.
이번 협약은 가볼까의 경험을 바탕으로 변화하는 시대적 트렌드를 반영한 새로운 사업 모델을 구축한다는 점에서 의미가 있다.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[우리은행] 우리은행 창립 118년', sysdate, '금융회사정보','우리은행(은행장 이광구, www.wooribank.com)은 4일 서울 중구 소공로 소재 본점 강당에서 창립 118주년 기념식을 개최했다고 밝혔다.
특히 민영화 이후 첫 번째로 개최된 동 행사에는 지난 12월 30일 새롭게 선임된 사외이사 5명도 참석하여 우리은행의 역사와 미래 비전을 공유하는 뜻 깊은 시간을 가졌다.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[우리은행] CEO 소개', sysdate, '금융회사정보','안녕하십니까?
우리은행장 손태승입니다.
우리은행을 찾아주신 고객님께 감사와 환영의 인사를 드립니다.
우리은행은 1899년, 자금흐름을 원활히 하여 국가 경제에 이바지 한다는
목적으로 설립된 국내 유일의 민족 정통 은행입니다.
우리은행은 구한말과 산업화시대, IMF 환란과 글로벌 금융위기를 겪으면서
대한민국 금융의 든든한 버팀목으로 성장해 왔습니다.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[국민은행] CEO 소개', sysdate, '금융회사정보','고객과 직원 중심의 디지털 KB로 거듭나
인공지능과 5G로 대표되는 초연결 시대가 도래하는 지금, 변화와 혁신은 생존을 위한 숙명(宿命)이 되었다며 제가 고객과 직원 중심의 디지털 KB와 역동적?혁신적인 조직문화를 강조하는 것도 이러한 은행산업 대전환기의 흐름을 감안한 것 이라 허인 KB은행장은 19년 하반기 조회사에서 말했다.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[신한은행] CEO 소개', sysdate, '금융회사정보','신한은행이 지난달 2일 서울 중구 세종대로 소재 신한은행 본점에서 2018년 시무식을 실시했다고 밝혔다. 이날 위성호 은행장은 고객과 주주들의 아낌없는 성원에 감사의 마음을 전하고 올해 전략목표인 Redefine 신한, Be the NEXT’를 선포하고 부제로 ‘2018 통·쾌·력(通·快·力) 영업현장을 설정했다고 밝혔다.',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[농협은행] CEO 소개', sysdate, '금융회사정보','존경하는 농협은행 고객님!
올 한해 저희 농협은행 앞에 놓인 길은 결코 순탄치 않을 것으로 예상됩니다.
하지만 저희 임직원 모두는 “멀리 내다보고 미래를 대비하여 준비 한다”는 시원예구(視遠豫具)의 자세로 미래 핵심 사업을 집중 육성하고
수익기반을 강화해 나가면서 변화하는 금융환경 속에서 선도은행으로 도약할 수 있도록 최선의 노력을 경주하겠습니다.
고객에게 더욱 신뢰받고 농업인과 국민이 행복한 세상을 만들기 위해 농협은행은 혼신의 힘을 다하겠습니다.
NH농협은행 은행장
이대훈 올림',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[국민은행] 미션/비전/핵심가치', sysdate, '금융회사정보','미션(Mission)
세상을 바꾸는 금융 - 고객의 행복과 더 나은 세상을 만들어갑니다.
비전(Vision)
최고의 인재와 담대한 혁신으로 가장 신뢰받는 평생금융파트너
핵심가치
고객중심',null,null, 0, 0, 0);

insert into tlb_news_board(board_idx, best_idx, idx_num, id, subject, created_date, news_case, content, filename, filesize, step, ref, depth)
values (board_seq_idx.nextval, best_seq_idx.nextval, news_seq.nextval, 'admin', '[신한은행] 실천프로그램', sysdate, '금융회사정보','신한은행이 사회적 약자 단체나 사회적 기업으로부터 포인트를 구매하고 직원들이 봉사활동을 하면 포인트를 나눠주는 새로운 개념의 사회공헌활동을 선보인다.
해당 아이디어는 지난 20일 열린 2019 사회공헌활동 우수직원 감사패 전달식에서 진옥동 신한은행장이 제안한 즉시 실무적 검토가 시작됐다.',null,null, 0, 0, 0);









