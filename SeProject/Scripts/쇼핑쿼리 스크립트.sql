/*�ֻ��� ī�װ�*/
CREATE TABLE topcategory(
	topcategory_id NUMBER
	,name varchar(25)
	,PRIMARY key(topcategory_id)
);

CREATE SEQUENCE seq_topcategory
INCREMENT BY 1
START WITH 1;

/*���� ī�װ�*/
CREATE TABLE subcategory(
	subcategory_id NUMBER	
	, topcategory_id number
	,name varchar(25)
	,PRIMARY KEY(subcategory_id)
	,CONSTRAINT fk_topcategory FOREIGN key(topcategory_id) 
	REFERENCES topcategory(topcategory_id)
);

CREATE SEQUENCE seq_subcategory
INCREMENT BY 1
START WITH 1;

/*������ �ֱ�*/
INSERT INTO TOPCATEGORY (TOPCATEGORY_ID,NAME) VALUES(seq_topcategory.nextval,'����');
INSERT INTO TOPCATEGORY (TOPCATEGORY_ID,NAME) VALUES(seq_topcategory.nextval,'����');
INSERT INTO TOPCATEGORY (TOPCATEGORY_ID,NAME) VALUES(seq_topcategory.nextval,'�Ǽ��縮');
INSERT INTO TOPCATEGORY (TOPCATEGORY_ID,NAME) VALUES(seq_topcategory.nextval,'�Ź�');

SELECT * FROM TOPCATEGORY;

SELECT * FROM SUBCATEGORY;
INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,1,'�����');
INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,1,'����');
INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,1,'Ƽ����');
INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,1,'��Ʈ');

INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,2,'û����');
INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,2,'ġ��');
INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,2,'������');
INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,2,'�����');

INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,3,'�Ͱ���');
INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,3,'����');
INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,3,'�����');
INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,3,'����');

INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,4,'����');
INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,4,'����');
INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,4,'������');
INSERT INTO SUBCATEGORY (subcategory_id,topcategory_id,name) VALUES(seq_subcategory.nextval,4,'�ȭ');
COMMIT;

SELECT * FROM SUBCATEGORY WHERE TOPCATEGORY_ID = 1;
SELECT * FROM SUBCATEGORY WHERE TOPCATEGORY_ID = 3;

drop table subcategory;
drop table topcategory;
drop sequence seq_topcategory;
drop sequence seq_subcategory;

CREATE TABLE topcategory(
	topcategory_id  NUMBER
   , name varchar(25)
   , PRIMARY key(topcategory_id)   
);
CREATE SEQUENCE seq_topcategory
INCREMENT BY 1
START WITH 1;

CREATE TABLE subcategory(
	subcategory_id NUMBER
	, topcategory_id number
	, name varchar(25)
	, PRIMARY key(subcategory_id)
	, CONSTRAINT fk_topcategory FOREIGN KEY (topcategory_id) 
		REFERENCES topcategory(topcategory_id)
);

CREATE SEQUENCE seq_subcategory
INCREMENT BY 1
START WITH 1;

CREATE TABLE product(
	product_id NUMBER
	,subcategory_id NUMBER
	,product_name varchar(30)
	,brand varchar(20)
	,price NUMBER DEFAULT 0
	,filename varchar(20)
	,PRIMARY key(product_id)
	,CONSTRAINT fk_subcategory FOREIGN key(subcategory_id)
	REFERENCES subcategory(subcategory_id)
);

CREATE SEQUENCE seq_product
INCREMENT BY 1
START WITH 1;

INSERT INTO TOPCATEGORY (TOPCATEGORY_ID,NAME) values(seq_topcategory.nextval,'top');
INSERT INTO TOPCATEGORY (TOPCATEGORY_ID,NAME) values(seq_topcategory.nextval,'down');
INSERT INTO TOPCATEGORY (TOPCATEGORY_ID,NAME) values(seq_topcategory.nextval,'accerary');
INSERT INTO TOPCATEGORY (TOPCATEGORY_ID,NAME) values(seq_topcategory.nextval,'shoes');

INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,1,'cadigan');
INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,1,'shirt');
INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,1,'tshirt');
INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,1,'neat');

INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,2,'jeans');
INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,2,'skirt');
INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,2,'slacks');
INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,2,'cotton jeans');

INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,3,'earing');
INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,3,'barleck');
INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,3,'neck');
INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,3,'ring');

INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,4,'shoes');
INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,4,'sandle');
INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,4,'slipper');
INSERT INTO subcategory(subcategory_id,topcategory_id, name) VALUES(seq_subcategory.nextval,4,'running');
COMMIT;

