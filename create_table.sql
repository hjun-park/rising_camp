show databases;

use baemin_db;

show tables;

show character set; -- 캐릭터 셋 확인

# alter table ADDRESS convert to character set utf8;
# alter table DELIVERY_POLICY convert to character set utf8;
# alter table MEMBER convert to character set utf8;
# alter table MEMBERSHIP convert to character set utf8;
# alter table MENU convert to character set utf8;
# alter table MENU_GROUP convert to character set utf8;
# alter table ORDERS convert to character set utf8;
# alter table ORDER_HISTORY convert to character set utf8;
# alter table ORDER_OPTION convert to character set utf8;
# alter table REVIEW convert to character set utf8;
# alter table RIDER convert to character set utf8;
# alter table STORE convert to character set utf8;
# alter table STORE_BASKET convert to character set utf8;
# alter table STORE_CATEGORY convert to character set utf8;
# alter table STORE_HOURS convert to character set utf8;

# alter table 테이블명 convert to character set utf8;
# drop table ADDRESS

desc ADDRESS;

# https://www.lesstif.com/dbms/mysql-foreign-key-constraint-100205962.html
# DDL 작업 시 원본 테이블의 컬럼 유형과 다르다는 3780 에러 해결 방법
# 1. 외래키 제약 조건을 끈다.
SET FOREIGN_KEY_CHECKS = 0;
# 2. 문제가 일어나는 작업 수행 후 다시 제약 조건을 켜준다.
SET FOREIGN_KEY_CHECKS = 1;

alter table REVIEW
	add constraint FK_REVIEW_store_basket_id_STORE_BASKET_ID_id
		foreign key (store_basket_id) references STORE_BASKET (id);
# # update_at 의 timestamp 내용 수정
# # ALTER TABLE MEMBER CHANGE modified_at updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
