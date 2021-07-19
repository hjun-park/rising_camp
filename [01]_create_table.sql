-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.
show databases;

CREATE DATABASE baemin_db;
DROP database baemin_db;
ALTER DATABASE baemin_db CHARACTER SET UTF8MB4;

USE baemin_db;

-- STORE_CATEGORY Table Create SQL
CREATE TABLE STORE_CATEGORY
(
    `name`       VARCHAR(20)    NOT NULL    COMMENT '( Unknown, 치킨, 피자, 족발, 야식 등등 )',
    `imageUrl`   TEXT           NOT NULL    COMMENT '카테고리 이미지',
    `createdAt`  TIMESTAMP      NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
    `updatedAt`  TIMESTAMP      NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
    `status`     VARCHAR(20)    NOT NULL    DEFAULT 'Used' COMMENT 'Used, Deleted',
    CONSTRAINT PK_STORE_CATEGORY PRIMARY KEY (name)
) CHARSET UTF8;

ALTER TABLE STORE_CATEGORY COMMENT '가게 카테고리';


-- DISTRICT Table Create SQL
CREATE TABLE DISTRICT
(
    `code`          VARCHAR(25)    NOT NULL    COMMENT 'ex) 1144012700 (배달팁 규칙)',
    `region1Depth`  VARCHAR(25)    NOT NULL    COMMENT '시/도',
    `region2Depth`  VARCHAR(25)    NOT NULL    COMMENT '시/군/구',
    `region3Depth`  VARCHAR(25)    NOT NULL    COMMENT '동/읍/면',
    `createdAt`     TIMESTAMP      NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
    `updatedAt`     TIMESTAMP      NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    `status`        VARCHAR(20)    NOT NULL    COMMENT '상태',
    CONSTRAINT PK_ZONE_CODE PRIMARY KEY (code)
) CHARSET UTF8;

ALTER TABLE DISTRICT COMMENT '행정구역';


-- ADDRESS Table Create SQL
CREATE TABLE ADDRESS
(
    `buildingNum`  VARCHAR(25)       NOT NULL    COMMENT 'ex) 1144012700115950000000001',
    `latitude`     DECIMAL(10, 7)    NOT NULL    COMMENT '배달팁 산정 위한 거리계산',
    `longitude`    DECIMAL(10, 7)    NOT NULL    COMMENT '배달팁 산정 위한 거리계산',
    `addressName`  VARCHAR(200)      NOT NULL    COMMENT 'ex) 서울특별시 마포구 성암로 301',
    `zipcode`      VARCHAR(5)        NOT NULL    COMMENT 'ex) 03923',
    CONSTRAINT PK_ADDRESS PRIMARY KEY (buildingNum)
) CHARSET UTF8;

ALTER TABLE ADDRESS COMMENT '주소';


-- STORE Table Create SQL
CREATE TABLE STORE
(
    `id`                  BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '가게 ID',
    `storeCategoryName`   VARCHAR(20)        NOT NULL    COMMENT '카테고리 이름',
    `name`                VARCHAR(45)        NOT NULL    COMMENT '가게 이름',
    `notice`              TEXT               NULL        COMMENT '가게 소개',
    `info`                TEXT               NULL        COMMENT '안내 및 혜택',
    `paymentMethod`       VARCHAR(20)        NOT NULL    COMMENT '( ''만나서 결제'', 카드 결제 )',
    `deliveryType`        VARCHAR(20)        NULL        COMMENT '( ''배달'', 포장 )',
    `originInform`        TEXT               NULL        COMMENT '원산지 정보',
    `address`             VARCHAR(255)       NOT NULL    COMMENT '주소',
    `phoneNumber`         VARCHAR(15)        NOT NULL    COMMENT '가게 전화번호',
    `createdAt`           TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '가게 최초 등록일',
    `updatedAt`           TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '가게 수정일',
    `status`              VARCHAR(20)        NOT NULL    DEFAULT 'READY' COMMENT 'READY, USED, DELETED',
    `hours`               VARCHAR(45)        NOT NULL    COMMENT '영업 시간',
    `addressBuildingNum`  VARCHAR(25)        NOT NULL    COMMENT '건물관리번호',
    `districtCode`        VARCHAR(25)        NOT NULL    COMMENT '행정구역코드',
    `addressDetail`       VARCHAR(100)       NOT NULL    COMMENT '상세주소',
    `deliveryTime`        VARCHAR(45)        NOT NULL    COMMENT '배달 소요 시간',
    `closedDay`           VARCHAR(20)        NOT NULL    DEFAULT '연중무휴' COMMENT '연중무휴, 주말, 평일',
    `deliveryArea`        VARCHAR(50)        NOT NULL    COMMENT '배달 지역',
    `minOrderPrice`       INT UNSIGNED       NOT NULL    DEFAULT 0 COMMENT '주문 최소 금액',
    `tips`                INT UNSIGNED       NOT NULL    DEFAULT 0 COMMENT '배달팁',
    `storeImageUrl`       TEXT               NULL        COMMENT '가게 이미지 URL',
    CONSTRAINT PK_STORE PRIMARY KEY (id)
) CHARSET UTF8;

ALTER TABLE STORE COMMENT '가게';

ALTER TABLE STORE
    ADD CONSTRAINT FK_STORE_addressBuildingNum_ADDRESS_buildingNum FOREIGN KEY (addressBuildingNum)
        REFERENCES ADDRESS (buildingNum) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE STORE
    ADD CONSTRAINT FK_STORE_storeCategoryName_STORE_CATEGORY_name FOREIGN KEY (storeCategoryName)
        REFERENCES STORE_CATEGORY (name) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE STORE
    ADD CONSTRAINT FK_STORE_districtCode_DISTRICT_code FOREIGN KEY (districtCode)
        REFERENCES DISTRICT (code) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- RIDER Table Create SQL
CREATE TABLE RIDER
(
    `id`           BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '라이더 ID',
    `name`         VARCHAR(20)        NOT NULL    COMMENT '라이더 이름',
    `phoneNumber`  VARCHAR(15)        NOT NULL    COMMENT '휴대폰 번호',
    `email`        VARCHAR(45)        NOT NULL    COMMENT '이메일 주소',
    `createdAt`    TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
    `updatedAt`    TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최근 수정일자',
    `status`       VARCHAR(20)        NOT NULL    DEFAULT 'Joined' COMMENT 'Joined, Deleted, Suspend',
    CONSTRAINT PK_RIDER PRIMARY KEY (id)
) CHARSET UTF8;

ALTER TABLE RIDER COMMENT '라이더';


-- MEMBER Table Create SQL
CREATE TABLE MEMBER
(
    `id`                  BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '멤버 ID',
    `password`            TEXT               NOT NULL    COMMENT '암호화',
    `email`               VARCHAR(30)        NOT NULL    COMMENT '멤버 이메일',
    `name`                VARCHAR(30)        NOT NULL    COMMENT '멤버 닉네임',
    `phoneNumber`         VARCHAR(15)        NOT NULL    COMMENT '휴대폰 번호',
    `profileImageUrl`     TEXT               NULL        COMMENT '프로필사진 URL',
    `mailAccept`          VARCHAR(1)         NOT NULL    DEFAULT 'N' COMMENT '메일 수신동의',
    `smsAccept`           VARCHAR(1)         NOT NULL    DEFAULT 'N' COMMENT 'SMS 수신동의',
    `addressBuildingNum`  VARCHAR(25)        NOT NULL    COMMENT '건물관리번호',
    `districtCode`        VARCHAR(25)        NOT NULL    COMMENT '행정구역코드',
    `addressDetail`       VARCHAR(100)       NOT NULL    DEFAULT '0' COMMENT '상세주소',
    `grade`               TINYINT(1)         NOT NULL    DEFAULT 0 COMMENT '0: 고마운분, 1: 귀한분, 2:더귀한분, 3:천생연분',
    `status`              VARCHAR(20)        NOT NULL    DEFAULT 'Joined' COMMENT '( Joined, Deleted, Suspend )',
    `createdAt`           TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '최초 가입일',
    `updatedAt`           TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 정보 수정일',
    `latitude`            DECIMAL(10, 7)     NOT NULL    DEFAULT 0 COMMENT '거리계산',
    `longitude`           DECIMAL(10, 7)     NOT NULL    DEFAULT 0 COMMENT '거리계산',
    `birthDate`           DATE               NOT NULL    COMMENT '생년월일',
    CONSTRAINT PK_MEMBER PRIMARY KEY (id)
) CHARSET UTF8;

ALTER TABLE MEMBER COMMENT '고객';

ALTER TABLE MEMBER
    ADD CONSTRAINT FK_MEMBER_addressBuildingNum_ADDRESS_buildingNum FOREIGN KEY (addressBuildingNum)
        REFERENCES ADDRESS (buildingNum) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE MEMBER
    ADD CONSTRAINT FK_MEMBER_districtCode_DISTRICT_code FOREIGN KEY (districtCode)
        REFERENCES DISTRICT (code) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- MENU_GROUP Table Create SQL
CREATE TABLE MENU_GROUP
(
    `id`         BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '메뉴 그룹 ID',
    `storeId`    BIGINT UNSIGNED    NOT NULL    COMMENT '가게 ID',
    `name`       VARCHAR(45)        NOT NULL    COMMENT '메뉴 그룹 이름',
    `status`     VARCHAR(20)        NOT NULL    COMMENT '상태',
    `content`    TEXT               NULL        COMMENT '메뉴 그룹 설명',
    `createdAt`  TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
    `updatedAt`  TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정일',
    CONSTRAINT PK_MENU_GROUP PRIMARY KEY (id)
) CHARSET UTF8;

ALTER TABLE MENU_GROUP COMMENT '메뉴 그룹';

ALTER TABLE MENU_GROUP
    ADD CONSTRAINT FK_MENU_GROUP_storeId_STORE_id FOREIGN KEY (storeId)
        REFERENCES STORE (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- MENU Table Create SQL
CREATE TABLE MENU
(
    `id`           BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '메뉴 ID',
    `name`         VARCHAR(45)        NOT NULL    COMMENT '메뉴 이름',
    `price`        INT UNSIGNED       NOT NULL    DEFAULT 0 COMMENT '메뉴 가격',
    `picture`      TEXT               NULL        COMMENT '메뉴 사진',
    `status`       VARCHAR(20)        NOT NULL    DEFAULT 'Full' COMMENT '( 소진 시 Empty )',
    `createdAt`    TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
    `updatedAt`    TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    `content`      TEXT               NULL        COMMENT '메뉴 소개',
    `menuGroupId`  BIGINT UNSIGNED    NOT NULL    COMMENT '메뉴 그룹 ID',
    `isSignature`  TINYINT(1)         NOT NULL    DEFAULT 0 COMMENT '0: 일반메뉴, 1: 대표메뉴',
    CONSTRAINT PK_MENU PRIMARY KEY (id)
) CHARSET UTF8;

ALTER TABLE MENU COMMENT '메뉴';

ALTER TABLE MENU
    ADD CONSTRAINT FK_MENU_menuGroupId_MENU_GROUP_id FOREIGN KEY (menuGroupId)
        REFERENCES MENU_GROUP (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- ORDERS Table Create SQL
CREATE TABLE ORDERS
(
    `id`                  BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '주문 ID',
    `storeId`             BIGINT UNSIGNED    NOT NULL    COMMENT '배달팁 계산',
    `basketId`            BIGINT UNSIGNED    NOT NULL    COMMENT '총 주문금액 계산',
    `memberId`            BIGINT UNSIGNED    NOT NULL    COMMENT '주문 고객 ID',
    `addressBuildingNum`  VARCHAR(25)        NOT NULL    COMMENT '주소관리코드',
    `addressDetail`       VARCHAR(255)       NOT NULL    COMMENT '상세주소',
    `tips`                INT UNSIGNED       NOT NULL    COMMENT '배달 팁',
    `status`              VARCHAR(20)        NOT NULL    COMMENT '''결제전'', ''확인'',  ''준비중'', ''배달 전'', ''배달 중'', ''배달완료''',
    `storeRequest`        VARCHAR(45)        NULL        COMMENT '사장님께 요청사항',
    `riderRequest`        VARCHAR(45)        NULL        COMMENT '라이더님께 요청',
    `riderId`             BIGINT UNSIGNED    NULL        COMMENT '담당 라이더 ID',
    `orderTime`           TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '최초 주문 시간',
    `arriveTime`          TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '도착 시간',
    `updatedAt`           TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
    CONSTRAINT PK_ORDERS PRIMARY KEY (id)
) CHARSET UTF8;

ALTER TABLE ORDERS COMMENT '주문';

ALTER TABLE ORDERS
    ADD CONSTRAINT FK_ORDERS_memberId_MEMBER_id FOREIGN KEY (memberId)
        REFERENCES MEMBER (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE ORDERS
    ADD CONSTRAINT FK_ORDERS_riderId_RIDER_id FOREIGN KEY (riderId)
        REFERENCES RIDER (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE ORDERS
    ADD CONSTRAINT FK_ORDERS_storeId_STORE_id FOREIGN KEY (storeId)
        REFERENCES STORE (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- ORDER_ITEM Table Create SQL
CREATE TABLE ORDER_ITEM
(
    `id`         BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '주문상품 ID',
    `orderId`    BIGINT UNSIGNED    NOT NULL    COMMENT '주문 ID',
    `amount`     INT UNSIGNED       NOT NULL    COMMENT '메뉴 수량',
    `menuId`     BIGINT UNSIGNED    NOT NULL    COMMENT '메뉴 ID',
    `createdAt`  TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '주문 생성일자',
    `updatedAt`  TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '주문 수정일자',
    CONSTRAINT PK_PAYMENT PRIMARY KEY (id)
) CHARSET UTF8;

ALTER TABLE ORDER_ITEM COMMENT '주문상세';

ALTER TABLE ORDER_ITEM
    ADD CONSTRAINT FK_ORDER_ITEM_orderId_ORDERS_id FOREIGN KEY (orderId)
        REFERENCES ORDERS (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE ORDER_ITEM
    ADD CONSTRAINT FK_ORDER_ITEM_menuId_MENU_id FOREIGN KEY (menuId)
        REFERENCES MENU (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- MEMBERSHIP Table Create SQL
CREATE TABLE MEMBERSHIP
(
    `name`       VARCHAR(25)    NOT NULL    DEFAULT '고마운분' COMMENT '(고마운분, 귀한분, 더귀한분, 천생연분)',
    `status`     VARCHAR(20)    NOT NULL    DEFAULT 'Used' COMMENT 'Deleted',
    `createdAt`  TIMESTAMP      NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
    `updatedAt`  TIMESTAMP      NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
    CONSTRAINT PK_MEMBERSHIP PRIMARY KEY (name)
) CHARSET UTF8;

ALTER TABLE MEMBERSHIP COMMENT '등급 정보 ( 추후에 쿠폰관리 )';


-- OWNER Table Create SQL
CREATE TABLE OWNER
(
    `id`           BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '사장님 ID',
    `storeId`      BIGINT UNSIGNED    NOT NULL    COMMENT '가게 ID',
    `email`        VARCHAR(45)        NOT NULL    COMMENT '사장님 이메일',
    `password`     TEXT               NOT NULL    COMMENT '사장님 비밀번호',
    `phoneNumber`  VARCHAR(15)        NOT NULL    COMMENT '핸드폰 번호',
    `createdAt`    TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
    `updatedAt`    TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
    `status`       VARCHAR(20)        NOT NULL    DEFAULT 'Joined' COMMENT 'Joined, Deleted, Suspend',
     PRIMARY KEY (id)
) CHARSET UTF8;

ALTER TABLE OWNER COMMENT '사장님';

ALTER TABLE OWNER
    ADD CONSTRAINT FK_OWNER_storeId_STORE_id FOREIGN KEY (storeId)
        REFERENCES STORE (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- MEMBER_LEVEL Table Create SQL
CREATE TABLE MEMBER_LEVEL
(
    `memberId`        BIGINT UNSIGNED    NOT NULL    COMMENT '멤버 ID',
    `membershipName`  VARCHAR(25)        NOT NULL    COMMENT '등급 이름',
    `status`          VARCHAR(20)        NOT NULL    COMMENT '멤버 테이블 기준으로 업데이트',
    `createdAt`       TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '추가일자',
    `updatedAt`       TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
    `count`           INT UNSIGNED       NOT NULL    DEFAULT 0 COMMENT '주문횟수에 따라 등급이름 업데이트',
    CONSTRAINT PK_MEMBER_LEVEL PRIMARY KEY (memberId)
) CHARSET UTF8;

ALTER TABLE MEMBER_LEVEL COMMENT '회원등급 관리';

ALTER TABLE MEMBER_LEVEL
    ADD CONSTRAINT FK_MEMBER_LEVEL_memberId_MEMBER_id FOREIGN KEY (memberId)
        REFERENCES MEMBER (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE MEMBER_LEVEL
    ADD CONSTRAINT FK_MEMBER_LEVEL_membershipName_MEMBERSHIP_name FOREIGN KEY (membershipName)
        REFERENCES MEMBERSHIP (name) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- BASKET Table Create SQL
CREATE TABLE BASKET
(
    `id`           BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '장바구니 ID',
    `memberId`     BIGINT UNSIGNED    NOT NULL    COMMENT '멤버 ID',
    `storeId`      BIGINT UNSIGNED    NOT NULL    COMMENT '가게 ID',
    `orderItemId`  BIGINT UNSIGNED    NOT NULL    COMMENT '주문상품 ID',
    `createdAt`    TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
    `updatedAt`    TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
    `status`       VARCHAR(20)        NOT NULL    DEFAULT 'Used' COMMENT '주문 완료 시 Deleted',
    CONSTRAINT PK_STORE_BASKET PRIMARY KEY (id)
) CHARSET UTF8;

ALTER TABLE BASKET COMMENT '장바구니';

ALTER TABLE BASKET
    ADD CONSTRAINT FK_BASKET_memberId_MEMBER_id FOREIGN KEY (memberId)
        REFERENCES MEMBER (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE BASKET
    ADD CONSTRAINT FK_BASKET_storeId_STORE_id FOREIGN KEY (storeId)
        REFERENCES STORE (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE BASKET
    ADD CONSTRAINT FK_BASKET_orderItemId_ORDER_ITEM_id FOREIGN KEY (orderItemId)
        REFERENCES ORDER_ITEM (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- DELIVERY_POLICY Table Create SQL
CREATE TABLE DELIVERY_POLICY
(
    `id`              BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '배달 정책 ID',
    `storeId`         BIGINT UNSIGNED    NOT NULL    COMMENT '가게 ID',
    `districtCode`    VARCHAR(25)        NOT NULL    COMMENT '배달 지역에 따른 수수료',
    `additionalTips`  INT UNSIGNED       NOT NULL    DEFAULT 0 COMMENT '배달 지역에 따른 수수료',
    `status`          VARCHAR(20)        NOT NULL    COMMENT '상태',
    `createdAt`       TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
    `updatedAt`       TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    CONSTRAINT PK_DELIVERY_POLICY PRIMARY KEY (id)
) CHARSET UTF8;

ALTER TABLE DELIVERY_POLICY COMMENT '배달 정책';

ALTER TABLE DELIVERY_POLICY
    ADD CONSTRAINT FK_DELIVERY_POLICY_storeId_STORE_id FOREIGN KEY (storeId)
        REFERENCES STORE (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE DELIVERY_POLICY
    ADD CONSTRAINT FK_DELIVERY_POLICY_districtCode_DISTRICT_code FOREIGN KEY (districtCode)
        REFERENCES DISTRICT (code) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- REVIEW Table Create SQL
CREATE TABLE REVIEW
(
    `id`              BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '리뷰 ID',
    `storeId`         BIGINT UNSIGNED    NOT NULL    COMMENT '가게 ID',
    `memberId`        BIGINT UNSIGNED    NOT NULL    COMMENT '멤버 ID',
    `orderItemId`     BIGINT UNSIGNED    NOT NULL    COMMENT '주문 ID',
    `content`         TEXT               NOT NULL    COMMENT '리뷰 내용',
    `createdAt`       TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
    `updatedAt`       TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
    `status`          VARCHAR(20)        NOT NULL    DEFAULT 'Used' COMMENT '(Used, Deleted)',
    `rating`          DECIMAL(2, 1)      NOT NULL    COMMENT '리뷰 별점',
    `reviewImageUrl`  TEXT               NULL        COMMENT '리뷰 이미지',
    CONSTRAINT PK_REVIEW PRIMARY KEY (id)
) CHARSET UTF8;

ALTER TABLE REVIEW COMMENT '리뷰';

ALTER TABLE REVIEW
    ADD CONSTRAINT FK_REVIEW_storeId_STORE_id FOREIGN KEY (storeId)
        REFERENCES STORE (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE REVIEW
    ADD CONSTRAINT FK_REVIEW_memberId_MEMBER_id FOREIGN KEY (memberId)
        REFERENCES MEMBER (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE REVIEW
    ADD CONSTRAINT FK_REVIEW_orderItemId_ORDERS_id FOREIGN KEY (orderItemId)
        REFERENCES ORDERS (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- STORE_HOURS Table Create SQL
CREATE TABLE STORE_HOURS
(
    `id`         BIGINT UNSIGNED      NOT NULL    AUTO_INCREMENT COMMENT '가게 운영시간 ID',
    `storeId`    BIGINT UNSIGNED      NULL        COMMENT '가게 ID',
    `startTime`  SMALLINT UNSIGNED    NOT NULL    COMMENT '백엔드처리로 시작시간 파악',
    `endTime`    SMALLINT UNSIGNED    NOT NULL    COMMENT '종료 시간',
    `createdAt`  TIMESTAMP            NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
    `updatedAt`  TIMESTAMP            NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
    CONSTRAINT PK_STORE_HOURS PRIMARY KEY (id)
) CHARSET UTF8;

ALTER TABLE STORE_HOURS COMMENT '가게 운영 시간';

ALTER TABLE STORE_HOURS
    ADD CONSTRAINT FK_STORE_HOURS_storeId_STORE_id FOREIGN KEY (storeId)
        REFERENCES STORE (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


