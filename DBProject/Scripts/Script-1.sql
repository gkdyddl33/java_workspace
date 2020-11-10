SELECT  * FROM PRODUCT WHERE PRODUCT_NAME LIKE '%가%';
SELECT  * FROM PRODUCT WHERE BRAND LIKE '%a%';
SELECT  * FROM PRODUCT WHERE PRICE LIKE '1%';
-- 첫번째 셀렉박스에 선택될 카테고리 구하기
--SELECT * FROM TOPCATEGORY WHERE TOPCATEGORY_ID =(유저가 선택한 subcategory_id);
select * from topcategory where topcategory_id =(
	select * from topcategory_id form subcategory where subcategory _id=7
);
select * from topcategory where topcategory_id =(select * from topcategory_id form subcategory where subcategory _id=10);
select * from topcategory where topcategory_id =(select * from topcategory_id form subcategory where subcategory _id=2);

SELECT * FROM SUBCATEGORY WHERE SUBCATEGORY_ID = SUBCATEGORY_ID;