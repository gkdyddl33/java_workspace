create table board_member(
	member_id number primary key
	,m_id varchar(20)
	,m_pass varchar(20)
	,m_name varchar(20)
	,regdate date default sysdate
);
CREATE SEQUENCE seq_board_member
INCREMENT BY 1
START WITH 1;