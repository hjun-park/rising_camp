show databases;
		
ALTER DATABASE baemin_db CHARACTER SET UTF8MB4;

USE baemin_db;

show tables;
-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.
-- STORE_CATEGORY Table Create SQL
-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

DROP TABLE STORE_CATEGORY;


-- STORE_CATEGORY Table Create SQL
CREATE TABLE STORE_CATEGORY
(
    `id`           BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '목록 ID', 
    `name`         VARCHAR(20)        NOT NULL    COMMENT '( DEFAULT, 치킨, 피자, 족발, 야식 등등 )', 
    `image_url`    TEXT               NOT NULL    COMMENT '목록 이미지', 
    `created_at`   TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자', 
    `modified_at`  TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자', 
    `status`       VARCHAR(20)        NOT NULL    COMMENT '상태', 
    CONSTRAINT PK_STORE_CATEGORY PRIMARY KEY (id)
);

ALTER TABLE STORE_CATEGORY COMMENT '가게 목록';

-- ADDRESS Table Create SQL
CREATE TABLE ADDRESS
(
    `building_manage_no`  VARCHAR(25)       NOT NULL    COMMENT 'ex) 1144012700115950000000001', 
    `latitude`            DECIMAL(10, 7)    NOT NULL    COMMENT '배달팁 산정 위한 거리계산', 
    `longitude`           DECIMAL(10, 7)    NOT NULL    COMMENT '배달팁 산정 위한 거리계산', 
    `address_name`        VARCHAR(200)      NOT NULL    COMMENT 'ex) 서울특별시 마포구 성암로 301', 
    `distinct_code`       VARCHAR(25)       NOT NULL    COMMENT 'ex) 1144012700 (배달팁 규칙)', 
    `zipcode`             VARCHAR(5)        NOT NULL    COMMENT 'ex) 03923', 
    `region_1depth`       VARCHAR(20)       NULL        COMMENT 'ex) 서울특별시', 
    `region_2depth`       VARCHAR(20)       NULL        COMMENT 'ex) 마포구', 
    `road_name`           VARCHAR(80)       NULL        COMMENT 'ex) 성암로', 
    `underground_yn`      VARCHAR(1)        NULL        DEFAULT 'N' COMMENT 'Y, N', 
    `main_building_no`    VARCHAR(10)       NULL        COMMENT 'ex) 301', 
    `sub_building_no`     VARCHAR(10)       NULL        COMMENT 'ex) 21', 
    `building_name`       VARCHAR(45)       NULL        COMMENT 'ex) KLID Tower', 
    CONSTRAINT PK_ADDRESS PRIMARY KEY (building_manage_no)
);

ALTER TABLE ADDRESS COMMENT '주소';


-- STORE Table Create SQL
CREATE TABLE STORE
(
    `id`              BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '가게 ID', 
    `store_type_id`   BIGINT UNSIGNED    NOT NULL    COMMENT '가게 유형 ID', 
    `name`            VARCHAR(45)        NOT NULL    COMMENT '가게 이름', 
    `notice`          TEXT               NULL        COMMENT '가게 소개', 
    `info`            TEXT               NULL        COMMENT '안내 및 혜택', 
    `payment_method`  VARCHAR(20)        NOT NULL    COMMENT '( ''만나서 결제'', 카드 결제 )', 
    `delivery_type`   VARCHAR(20)        NOT NULL    COMMENT '( ''배달'', 포장 )', 
    `origin_inform`   VARCHAR(255)       NULL        COMMENT '원산지 정보', 
    `address`         VARCHAR(255)       NOT NULL    COMMENT '주소', 
    `main_menu_id`    VARCHAR(45)        NOT NULL    COMMENT '대표 메뉴', 
    `phone_number`    VARCHAR(12)        NOT NULL    COMMENT '가게 전화번호', 
    `created_at`      TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '가게 최초 등록일', 
    `modified_at`     TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '가게 수정일', 
    `status`          VARCHAR(20)        NOT NULL    DEFAULT 'READY' COMMENT 'READY, USED, DELETED', 
    `condition`       VARCHAR(20)        NOT NULL    DEFAULT 'Closed' COMMENT '(오픈, 마감)', 
    `hours`           VARCHAR(45)        NOT NULL    COMMENT '영업 시간', 
    `address_code`    VARCHAR(25)        NOT NULL    COMMENT '주소관리코드', 
    `address_detail`  VARCHAR(100)       NOT NULL    COMMENT '상세주소', 
    `delivery_time`   VARCHAR(45)        NOT NULL    COMMENT '배달 소요 시간', 
    `closed_day`      VARCHAR(20)        NOT NULL    DEFAULT '연중무휴' COMMENT '연중무휴, 주말, 평일', 
    `delivery_area`   VARCHAR(50)        NOT NULL    COMMENT '배달 지역', 
    CONSTRAINT PK_STORE PRIMARY KEY (id)
);

ALTER TABLE STORE COMMENT '가게';

ALTER TABLE STORE
    ADD CONSTRAINT FK_STORE_store_type_id_STORE_CATEGORY_id FOREIGN KEY (store_type_id)
        REFERENCES STORE_CATEGORY (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE STORE
    ADD CONSTRAINT FK_STORE_address_code_ADDRESS_building_manage_no FOREIGN KEY (address_code)
        REFERENCES ADDRESS (building_manage_no) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- RIDER Table Create SQL
CREATE TABLE RIDER
(
    `id`            BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '라이더 ID', 
    `name`          VARCHAR(20)        NOT NULL    COMMENT '라이더 이름', 
    `phone_number`  VARCHAR(12)        NOT NULL    COMMENT '휴대폰 번호', 
    `email`         VARCHAR(45)        NOT NULL    COMMENT '이메일 주소', 
    CONSTRAINT PK_RIDER PRIMARY KEY (id)
);

ALTER TABLE RIDER COMMENT '라이더';

-- DROP TABLE MEMBER;

-- MEMBER Table Create SQL
CREATE TABLE MEMBER
(
    `id`                 BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '멤버 ID', 
    `password`           VARCHAR(100)       NOT NULL    COMMENT '멤버 비밀번호', 
    `email`              VARCHAR(30)        NOT NULL    COMMENT '멤버 이메일', 
    `name`               VARCHAR(30)        NOT NULL    COMMENT '멤버 닉네임', 
    `phone_number`       VARCHAR(12)        NOT NULL    COMMENT '휴대폰 번호', 
    `profile_image_url`  TEXT               NULL        COMMENT '프로필사진 URL', 
    `mail_agree`         TINYINT         NOT NULL    DEFAULT 0 COMMENT '0: 거절, 1: 동의', 
    `sms_agree`          TINYINT         NOT NULL    DEFAULT 0 COMMENT '0: 거절, 1: 동의', 
    `address_code`       VARCHAR(25)        NOT NULL    COMMENT '주소관리코드', 
    `address_detail`     VARCHAR(100)       NOT NULL    COMMENT '상세주소', 
    `grade`              TINYINT         NOT NULL    DEFAULT 0 COMMENT '0: 고마운분, 1: 귀한분, 2:더귀한분, 3:천생연분', 
    `status`             VARCHAR(20)        NOT NULL    DEFAULT 'Joined' COMMENT '( Joined, Deleted, Suspend )', 
    `created_at`         TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '최초 가입일', 
    `updated_at`         TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '최종 정보 수정일', 
    CONSTRAINT PK_MEMBER PRIMARY KEY (id)
);

ALTER TABLE MEMBER COMMENT '고객';

ALTER TABLE MEMBER
    ADD CONSTRAINT FK_MEMBER_address_code_ADDRESS_building_manage_no FOREIGN KEY (address_code)
        REFERENCES ADDRESS (building_manage_no) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- MENU_GROUP Table Create SQL
CREATE TABLE MENU_GROUP
(
    `id`           BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '메뉴 그룹 ID', 
    `store_id`     BIGINT UNSIGNED    NOT NULL    COMMENT '가게 ID', 
    `name`         VARCHAR(45)        NOT NULL    COMMENT '메뉴 그룹 이름', 
    `status`       VARCHAR(20)        NOT NULL    COMMENT '상태', 
    `content`      TEXT               NULL        COMMENT '메뉴 그룹 설명', 
    `created_at`   TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '등록일', 
    `modified_at`  TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '최종 수정일', 
    CONSTRAINT PK_MENU_GROUP PRIMARY KEY (id)
);

ALTER TABLE MENU_GROUP COMMENT '메뉴 그룹';

ALTER TABLE MENU_GROUP
    ADD CONSTRAINT FK_MENU_GROUP_store_id_STORE_id FOREIGN KEY (store_id)
        REFERENCES STORE (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- MENU Table Create SQL
CREATE TABLE MENU
(
    `id`            BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '메뉴 ID', 
    `name`          VARCHAR(45)        NOT NULL    COMMENT '메뉴 이름', 
    `price`         BIGINT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '메뉴 가격', 
    `picture`       TEXT               NULL        COMMENT '메뉴 사진', 
    `status`        VARCHAR(20)        NOT NULL    DEFAULT 'FULL' COMMENT '( 소진 시 EMPTY )', 
    `created_at`    TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '등록일', 
    `modified_at`   TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '수정일', 
    `content`       TEXT               NULL        COMMENT '메뉴 소개', 
    `menu_id`       BIGINT UNSIGNED    NOT NULL    COMMENT '메뉴 그룹 ID', 
    `is_signature`  TINYINT(1)         NOT NULL    DEFAULT 0 COMMENT '0: 일반메뉴, 1: 대표메뉴', 
    CONSTRAINT PK_MENU PRIMARY KEY (id)
);

ALTER TABLE MENU COMMENT '메뉴';

ALTER TABLE MENU
    ADD CONSTRAINT FK_MENU_menu_id_MENU_GROUP_id FOREIGN KEY (menu_id)
        REFERENCES MENU_GROUP (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- ORDERS Table Create SQL
CREATE TABLE ORDERS
(
    `id`             BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '주문 ID', 
    `member_id`      BIGINT UNSIGNED    NOT NULL    COMMENT '주문 고객 ID', 
    `address`        VARCHAR(255)       NOT NULL    COMMENT '상세주소', 
    `tips`           BIGINT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '배달 팁', 
    `zipcode`        VARCHAR(20)        NOT NULL    COMMENT '우편번호', 
    `status`         VARCHAR(20)        NOT NULL    COMMENT '''결제 전'', ''주문 요청 확인'',  ''준비 중'', ''배달 전'', ''배달 중'', ''배달 완료''', 
    `store_request`  VARCHAR(45)        NULL        COMMENT '사장님께 요청사항', 
    `rider_request`  VARCHAR(45)        NULL        COMMENT '라이더님께 요청', 
    `rider_id`       BIGINT UNSIGNED    NOT NULL    COMMENT '담당 라이더 ID', 
    `order_time`     TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '최초 주문 시간', 
    `arrive_time`    TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '도착 시간', 
    CONSTRAINT PK_ORDERS PRIMARY KEY (id)
);

ALTER TABLE ORDERS COMMENT '주문';

ALTER TABLE ORDERS
    ADD CONSTRAINT FK_ORDERS_member_id_MEMBER_id FOREIGN KEY (member_id)
        REFERENCES MEMBER (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE ORDERS
    ADD CONSTRAINT FK_ORDERS_rider_id_RIDER_id FOREIGN KEY (rider_id)
        REFERENCES RIDER (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- STORE_BASKET Table Create SQL
CREATE TABLE STORE_BASKET
(
    `id`          BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '장바구니 ID', 
    `member_id`   BIGINT UNSIGNED    NOT NULL    COMMENT '멤버 ID', 
    `store_id`    BIGINT UNSIGNED    NOT NULL    COMMENT '가게 ID', 
    `menu_id`     BIGINT UNSIGNED    NOT NULL    COMMENT '메뉴 ID', 
    `amount`      BIGINT UNSIGNED    NOT NULL    COMMENT '메뉴 수량', 
    `created_at`  TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '최초 등록일자', 
    `updated_at`  TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자', 
    `status`      VARCHAR(20)        NOT NULL    DEFAULT 'Empty' COMMENT 'Empty, Used', 
     PRIMARY KEY (id)
);

ALTER TABLE STORE_BASKET COMMENT '장바구니';

ALTER TABLE STORE_BASKET
    ADD CONSTRAINT FK_STORE_BASKET_member_id_MEMBER_id FOREIGN KEY (member_id)
        REFERENCES MEMBER (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE STORE_BASKET
    ADD CONSTRAINT FK_STORE_BASKET_store_id_STORE_id FOREIGN KEY (store_id)
        REFERENCES STORE (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE STORE_BASKET
    ADD CONSTRAINT FK_STORE_BASKET_menu_id_MENU_id FOREIGN KEY (menu_id)
        REFERENCES MENU (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- MEMBERSHIP Table Create SQL
CREATE TABLE MEMBERSHIP
(
    `id`         BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '회원 등급 ID', 
    `name`       VARCHAR(45)        NOT NULL    DEFAULT '고마운분' COMMENT '(고마운분, 귀한분, 더귀한분, 천생연분)', 
    `count`      BIGINT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '주문 횟수', 
    `member_id`  BIGINT UNSIGNED    NOT NULL    COMMENT '멤버 ID', 
    CONSTRAINT PK_MEMBERSHIP PRIMARY KEY (id)
);

ALTER TABLE MEMBERSHIP COMMENT '회원 등급';

ALTER TABLE MEMBERSHIP
    ADD CONSTRAINT FK_MEMBERSHIP_member_id_MEMBER_id FOREIGN KEY (member_id)
        REFERENCES MEMBER (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- DELIVERY_POLICY Table Create SQL
CREATE TABLE DELIVERY_POLICY
(
    `id`               BIGINT UNSIGNED    NULL        AUTO_INCREMENT COMMENT '배달정책 ID', 
    `store_id`         BIGINT UNSIGNED    NOT NULL    COMMENT '가게 ID', 
    `tips`             VARCHAR(20)        NOT NULL    COMMENT '기본 배달팁', 
    `min_order_price`  BIGINT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '주문 최소 금액', 
    `town_code`        VARCHAR(25)        NOT NULL    COMMENT '배달 지역에 따른 수수료', 
    `additional_tips`  BIGINT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '배달 지역에 따른 수수료', 
    CONSTRAINT PK_DELIVERY_POLICY PRIMARY KEY (id)
);

ALTER TABLE DELIVERY_POLICY COMMENT '배달 정책';

ALTER TABLE DELIVERY_POLICY
    ADD CONSTRAINT FK_DELIVERY_POLICY_store_id_STORE_id FOREIGN KEY (store_id)
        REFERENCES STORE (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE DELIVERY_POLICY
    ADD CONSTRAINT FK_DELIVERY_POLICY_town_code_ADDRESS_distinct_code FOREIGN KEY (town_code)
        REFERENCES ADDRESS (distinct_code) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- REVIEW Table Create SQL
CREATE TABLE REVIEW
(
    `id`           BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '리뷰 ID', 
    `store_id`     BIGINT UNSIGNED    NOT NULL    COMMENT '가게 ID', 
    `member_id`    BIGINT UNSIGNED    NOT NULL    COMMENT '멤버 ID', 
    `content`      TEXT               NOT NULL    COMMENT '리뷰 내용', 
    `created_at`   TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '리뷰 등록일자', 
    `modified_at`  TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '리뷰 수정일자', 
    `status`       VARCHAR(20)        NOT NULL    DEFAULT 'Used' COMMENT '(Used, Deleted)', 
    CONSTRAINT PK_REVIEW PRIMARY KEY (id)
);

ALTER TABLE REVIEW COMMENT '리뷰';

ALTER TABLE REVIEW
    ADD CONSTRAINT FK_REVIEW_store_id_STORE_id FOREIGN KEY (store_id)
        REFERENCES STORE (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE REVIEW
    ADD CONSTRAINT FK_REVIEW_member_id_MEMBER_id FOREIGN KEY (member_id)
        REFERENCES MEMBER (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- STORE_HOURS Table Create SQL
CREATE TABLE STORE_HOURS
(
    `id`           BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '가게 운영시간 ID', 
    `store_id`     BIGINT UNSIGNED    NULL        COMMENT '가게 ID', 
    `start_time`   TIMESTAMP          NOT NULL    COMMENT '시작 시간', 
    `end_time`     TIMESTAMP          NULL        COMMENT '종료 시간', 
    `created_at`   TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자', 
    `modified_at`  TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자', 
    CONSTRAINT PK_STORE_HOURS PRIMARY KEY (id)
);

ALTER TABLE STORE_HOURS COMMENT '가게 운영 시간';

ALTER TABLE STORE_HOURS
    ADD CONSTRAINT FK_STORE_HOURS_store_id_STORE_id FOREIGN KEY (store_id)
        REFERENCES STORE (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- ORDER_OPTION Table Create SQL
CREATE TABLE ORDER_OPTION
(
    `menu_id`      BIGINT UNSIGNED    NOT NULL    COMMENT '메뉴 ID', 
    `order_id`     BIGINT UNSIGNED    NOT NULL    COMMENT '주문 ID', 
    `amount`       BIGINT UNSIGNED    NOT NULL    DEFAULT 1 COMMENT '메뉴 수량', 
    `created_at`   TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자', 
    `modified_at`  TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자', 
    CONSTRAINT PK_ORDER_OPTION PRIMARY KEY (menu_id)
);

ALTER TABLE ORDER_OPTION COMMENT '주문 옵션';

ALTER TABLE ORDER_OPTION
    ADD CONSTRAINT FK_ORDER_OPTION_menu_id_MENU_id FOREIGN KEY (menu_id)
        REFERENCES MENU (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE ORDER_OPTION
    ADD CONSTRAINT FK_ORDER_OPTION_order_id_ORDERS_id FOREIGN KEY (order_id)
        REFERENCES ORDERS (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- ORDER_HISTORY Table Create SQL
CREATE TABLE ORDER_HISTORY
(
    `id`           BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '결제 ID', 
    `member_id`    BIGINT UNSIGNED    NOT NULL    COMMENT '멤버 ID', 
    `order_id`     BIGINT UNSIGNED    NOT NULL    COMMENT '주문 ID', 
    `type`         VARCHAR(20)        NOT NULL    COMMENT '( ''카드결제'', ''만나서결제'', 카카오페이, 네이버페이 등등 )', 
    `price`        BIGINT UNSIGNED    NOT NULL    DEFAULT 0 COMMENT '결제 금액', 
    `status`       VARCHAR(20)        NOT NULL    COMMENT '( ''주문 전'', ''주문 완료'', ''주문 실패'' )', 
    `created_at`   TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '주문 생성일자', 
    `modified_at`  TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '주문 수정일자', 
    CONSTRAINT PK_PAYMENT PRIMARY KEY (id)
);

ALTER TABLE ORDER_HISTORY COMMENT '주문내역';

ALTER TABLE ORDER_HISTORY
    ADD CONSTRAINT FK_ORDER_HISTORY_order_id_ORDERS_id FOREIGN KEY (order_id)
        REFERENCES ORDERS (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE ORDER_HISTORY
    ADD CONSTRAINT FK_ORDER_HISTORY_member_id_MEMBER_id FOREIGN KEY (member_id)
        REFERENCES MEMBER (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


