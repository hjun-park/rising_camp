use baemin_db;

# ALTER TABLE ORDERS
#     ADD CONSTRAINT FK_ORDERS_basketId_BASKET_id FOREIGN KEY (basketId)
#         REFERENCES BASKET (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


SET FOREIGN_KEY_CHECKS = 0;
# 2. 문제가 일어나는 작업 수행 후 다시 제약 조건을 켜준다.
SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE CART
    ADD CONSTRAINT FK_CART_storeId_STORE_id FOREIGN KEY (storeId)
        REFERENCES STORE (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE ORDER_ITEM
(
    `id`         BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '주문상품 ID',
    `orderId`    BIGINT UNSIGNED    NOT NULL    COMMENT '주문 ID',
    `amount`     INT UNSIGNED       NOT NULL    COMMENT '메뉴 수량',
    `menuId`     BIGINT UNSIGNED    NOT NULL    COMMENT '메뉴 ID',
    `createdAt`  TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '주문 생성일자',
    `updatedAt`  TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '주문 수정일자',
    `status`     VARCHAR(20)        NOT NULL    DEFAULT 'Used' COMMENT '상태',
    CONSTRAINT PK_PAYMENT PRIMARY KEY (id)
)CHARSET UTF8;

ALTER TABLE ORDER_ITEM COMMENT '주문 아이템';

ALTER TABLE ORDER_ITEM
    ADD CONSTRAINT FK_ORDER_ITEM_orderId_ORDERS_id FOREIGN KEY (orderId)
        REFERENCES ORDERS (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE ORDER_ITEM
    ADD CONSTRAINT FK_ORDER_ITEM_menuId_MENU_id FOREIGN KEY (menuId)
        REFERENCES MENU (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

SELECT name, amount, price
FROM ORDER_ITEM OI
INNER JOIN (SELECT id FROM ORDERS) O
ON OI.orderId = O.id
INNER JOIN (SELECT id, name, price FROM MENU) M
ON OI.menuId = M.id
WHERE orderId = 1 and status = 'Used';

CREATE TABLE CART
(
    `id`         BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '카트 ID',
    `memberId`   BIGINT UNSIGNED    NOT NULL    COMMENT '멤버 ID',
    `menuId`     BIGINT UNSIGNED    NOT NULL    COMMENT '메뉴 ID',
    `amount`     INT UNSIGNED       NOT NULL    COMMENT '수량',
    `createdAt`  TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '카트 생성일자',
    `updatedAt`  TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '카트 수정일자',
    `status`     VARCHAR(20)        NOT NULL    COMMENT '상태',
     PRIMARY KEY (id)
)CHARSET UTF8;

ALTER TABLE CART COMMENT '카트';

ALTER TABLE CART
    ADD CONSTRAINT FK_CART_memberId_MEMBER_id FOREIGN KEY (memberId)
        REFERENCES MEMBER (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE CART
    ADD CONSTRAINT FK_CART_menuId_MENU_id FOREIGN KEY (menuId)
        REFERENCES MENU (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

SELECT name, price, amount
FROM CART C
INNER JOIN (SELECT id, name, price FROM MENU) M
ON M.id = C.menuId
WHERE memberId = 1 AND status = 'Used';



SELECT *
			 FROM MEMBER
			WHERE id = 1
			AND status = 'Joined';



select * from CART where memberId = 1 AND status = 'Used' LIMIT 1;
INSERT INTO CART(memberId, menuId, amount, storeId) VALUES(1, 8, 20, 1);
UPDATE CART SET amount = 100 WHERE memberId = 1 AND id = 6;

UPDATE CART SET status = 'Deleted' WHERE memberId = 1;

# 1. 배달팁 계산
# 2.
INSERT INTO ORDERS(storeId, memberId, addressBuildingNum, addressDetail, tips, status, storeRequest, riderRequest)
    VALUES(1, 1, 1, '2층', 2000, '결제전', '리뷰이벤트','천천히');

INSERT INTO DELIVERY_POLICY(storeId, districtCode, additionalTips)
    VALUES(2, 5555, 10000);

SELECT *
			FROM STORE
			WHERE id = 1
			AND status = 'Used';

SELECT exists(select id from MEMBER WHERE email = 'jun@gmail.com'
    AND password = 'mypwdpwd123' AND status = 'Joined');

UPDATE MEMBER SET name = '네임1' WHERE id = 1;

SELECT * FROM STORE_CATEGORY WHERE status = 'Used';

SELECT * FROM STORE WHERE storeCategoryName = '치킨' AND status = 'Used';

CREATE TABLE REVIEW
(
    `id`              BIGINT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '리뷰 ID',
    `storeId`         BIGINT UNSIGNED    NOT NULL    COMMENT '가게 ID',
    `memberId`        BIGINT UNSIGNED    NOT NULL    COMMENT '멤버 ID',
    `orderId`         BIGINT UNSIGNED    NOT NULL    COMMENT '주문 ID',
    `content`         TEXT               NOT NULL    COMMENT '리뷰 내용',
    `createdAt`       TIMESTAMP          NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
    `updatedAt`       TIMESTAMP          NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
    `status`          VARCHAR(20)        NOT NULL    DEFAULT 'Used' COMMENT '(Used, Deleted)',
    `rating`          DECIMAL(2, 1)      NOT NULL    COMMENT '리뷰 별점',
    `reviewImageUrl`  TEXT               NULL        COMMENT '리뷰 이미지',
    CONSTRAINT PK_REVIEW PRIMARY KEY (id)
)CHARSET UTF8;

ALTER TABLE REVIEW COMMENT '리뷰';

ALTER TABLE REVIEW
    ADD CONSTRAINT FK_REVIEW_storeId_STORE_id FOREIGN KEY (storeId)
        REFERENCES STORE (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE REVIEW
    ADD CONSTRAINT FK_REVIEW_memberId_MEMBER_id FOREIGN KEY (memberId)
        REFERENCES MEMBER (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE REVIEW
    ADD CONSTRAINT FK_REVIEW_orderId_ORDERS_id FOREIGN KEY (orderId)
        REFERENCES ORDERS (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

SELECT * FROM REVIEW
WHERE status = 'Used' AND storeId = 1;

UPDATE STORE_CATEGORY SET status = 'Deleted'
			WHERE name = '피자';

SELECT * FROM DELIVERY_POLICY
			WHERE status = 'Used' AND storeId = 1;

UPDATE DELIVERY_POLICY SET additionalTips = 7000000
			WHERE storeId = 1 AND id = 7 AND status = 'Used';