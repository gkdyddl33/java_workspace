 create table shop_member(
    member_id  number primary key 
   , pass varchar(20)
   , name varchar(20)
   , phone varchar(20)
   , email varchar(50)
 );
  create  sequence  seq_shop_member
  increment by 1
  start with 1;

