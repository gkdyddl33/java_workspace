SELECT  * FROM PRODUCT WHERE PRODUCT_NAME LIKE '%��%';
SELECT  * FROM PRODUCT WHERE BRAND LIKE '%a%';
SELECT  * FROM PRODUCT WHERE PRICE LIKE '1%';
-- ù��° �����ڽ��� ���õ� ī�װ� ���ϱ�
--SELECT * FROM TOPCATEGORY WHERE TOPCATEGORY_ID =(������ ������ subcategory_id);
select * from topcategory where topcategory_id =(
	select * from topcategory_id form subcategory where subcategory _id=7
);
select * from topcategory where topcategory_id =(select * from topcategory_id form subcategory where subcategory _id=10);
select * from topcategory where topcategory_id =(select * from topcategory_id form subcategory where subcategory _id=2);

SELECT * FROM SUBCATEGORY WHERE SUBCATEGORY_ID = SUBCATEGORY_ID;