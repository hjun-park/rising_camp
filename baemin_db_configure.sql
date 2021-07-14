show tables;

show databases;


desc STORE_CATEGORY;

# 1. 메인 페이지
SELECT name, image_url
FROM STORE_CATEGORY
WHERE status = 'Used';


# 2. 지역코드에 따른 치킨 집 리스트 출력
-- 위도 경도 거리계산은 백엔드 처리
SELECT  /* 영업 중이며 사용자를 기준으로 치킨 가게 출력 */
       store_image_url,  -- 가게 이미지
       name,             -- 가게 이름
       delivery_type,    -- 배달 타입
       (SELECT AVG(rating) FROM REVIEW WHERE S.id = REVIEW.store_id) AS rating, -- 평점
       (SELECT COUNT(rating) FROM REVIEW WHERE S.id = REVIEW.store_id) AS review_count, -- 리뷰 개수
       delivery_time,     -- 배달 예상시간
       min_order_price,   -- 최소 주문 가격
       (S.tips + DP.additional_tips) AS 'delivery_tips' -- 배달팁
FROM STORE S
    LEFT JOIN(SELECT store_id,      -- 상점 ID
                    district_code,   -- 배달가능 행정코드
                    additional_tips -- 추가 배달 팁
              FROM DELIVERY_POLICY
              WHERE status = 'Used') AS DP
          ON DP.store_id = S.id
    JOIN(SELECT id,            -- 사용자 ID
                district_code       -- 행정코드
         FROM MEMBER
         WHERE id = 4
           AND status != 'Deleted') AS M
      ON M.district_code = DP.district_code
WHERE store_category_name = '치킨'
    AND status = 'Used';


# 3. 내가 클릭 한 특정 치킨 집 정보 출력
SELECT /* 내가 클릭 한 특정 치킨 집 정보 출력 */
    name, -- 가게 이름
    (SELECT AVG(rating) FROM REVIEW WHERE id = S.id ) AS rating,             -- 평점
    (CONCAT('최근리뷰 ', (SELECT COUNT(rating) FROM REVIEW))) AS reviwe_count, -- 최근 리뷰 개수
    (CONCAT('최소주문금액\t', FORMAT(min_order_price, 0), '원')) AS min_order_price, -- 최소주문금액
    (CONCAT('결제방법\t', payment_method)) AS payment_method, -- 결제방법
    (CONCAT('배달시간\t', delivery_time, '분 소요 예상')) AS delivery_time,-- 배달시간
    (CONCAT('배달팁\t', tips, '원')) AS delivery_tips, -- 배달팁
    origin_inform, -- 원산지 표기
    notice -- 가게 알림
FROM STORE S
WHERE S.status = 'Used' AND S.id = 3;

# 4. 메뉴 출력
SELECT  /* 가게 메뉴 출력 */
    MG.name AS menu_group_name, -- 메뉴 그룹 이름
    picture, -- 메뉴 이미지
    M.name, -- 메뉴 이름
    content, -- 메뉴 콘텐츠
    price, -- 메뉴 가격
    is_signature -- 메인메뉴 여부
FROM MENU M
    LEFT JOIN(SELECT /* 메뉴그룹 ID와 이름 선택 */
                    id,   -- 메뉴그룹 ID
                    store_id, -- 가게 ID
                    name  -- 메뉴그룹 이름
              FROM MENU_GROUP
              WHERE status = 'Used') AS MG
      ON MG.id = M.menu_group_id
    JOIN(SELECT /* 상점 ID와 메뉴그룹 ID 일치 쿼리 */
                id         -- 상점 ID
         FROM STORE
         WHERE status = 'Used' AND id = 3) AS S
      ON S.id = MG.store_id
WHERE status = 'Full';

# 5. 가게 상세정보 출력
SELECT /* 가게 상세정보 출력 */
    info, -- 가게 정보
    (CONCAT('상호명\t', name)) AS store_name,               -- 상호명
    (CONCAT('운영시간\t', hours)) AS hours,                 -- 운영시간
    (CONCAT('휴무일\t', closed_day)) AS 'closed_day',       -- 휴무일
    (CONCAT('전화번호\t', phone_number)) AS 'phone_number', -- 전화번호
    (CONCAT('배달지역\t', delivery_area)) AS 'delivery_area',       -- 배달지역
    notice, -- 안내(notice)
    address -- 사업자주소
FROM STORE S
WHERE status = 'Used' AND id = 3;

# 5-1. 배달 주소 ( DELIVERY_POLICY + DISTRICT )
SELECT /* 주소별 배달 팁 안내 */
    GROUP_CONCAT(D.region_3depth), -- 동 이름
    additional_tips -- 배달팁
FROM DELIVERY_POLICY DP
LEFT JOIN (SELECT code,
                 region_3depth
           FROM DISTRICT
           WHERE status = 'Used') AS D
        ON D.code = DP.district_code
WHERE status = 'Used' AND store_id = 3
GROUP BY additional_tips;

# 6. 리뷰 정보 출력
SELECT /* 해당 치킨 가게에서 특정 유저가 적은 리뷰 */
#     (CONCAT('최근 리뷰 ', (SELECT COUNT(rating)), '개')) AS review_count, -- 리뷰 총 갯수
    M.name, -- 멤버 이름
    rating, -- 별점
    content,    -- 작성내용
    DATE(created_at) AS 'day', -- 작성날짜 (날짜만)
    review_image_url -- 리뷰 이미지 링크
#     (SELECT name FROM ORDERS JOIN REV)-- 주문메뉴 이름
FROM REVIEW R -- MEMBER, STORE_BASKET_ID
    LEFT JOIN(SELECT id, name
              FROM MEMBER
              WHERE status = 'Joined') AS M
           ON M.id = R.member_id
WHERE store_id = 3;

# 7. 장바구니 정보
/*
    장바구니 -> 가게 ID, 멤버 ID, 메뉴 ID
        -> 메뉴 ID  ==> LEFT JOIN 이용해서 메뉴 추출
        -> 멤버 ID  ==> 멤버의 ID만 추출
        -> 가게 ID  ==> 가게 ID만 추출

    총액을 구하는 방법은
      1) union all
      2) ROLL UP

 */

SELECT /* 특정가게를 방문한 특정 유저의 장바구니 */
    name, -- 메뉴 이름
    SB.amount, -- 수량
    (M.price * SB.amount) AS menu_price -- (메뉴 이름 * 수량 금액)
    -- 서브쿼리 이용한 총 주문금액
FROM STORE_BASKET SB   -- MEMBER, MENU
    JOIN(SELECT id,
                name,
                price
         FROM MENU) AS M
       ON M.id = SB.menu_id
    JOIN(SELECT id
         FROM STORE) AS S
       ON S.id = SB.store_id
WHERE  status = 'Used' AND member_id = 4
UNION ALL
SELECT
       'TOTAL' AS name,
       NULL AS amount,
       SUM(M.price * SB.amount) AS menu_price
FROM STORE_BASKET AS SB
    JOIN(SELECT id,
                name,
                price
         FROM MENU) AS M
       ON M.id = SB.menu_id
    JOIN(SELECT id
         FROM STORE) AS S
       ON S.id = SB.store_id
WHERE  status = 'Used' AND member_id = 4;


# 8. 주문하기 페이지
/*
    1. 도로명주소 -> ADDRESS 테이블(address_building_no)
    2. 전화번호 -> MEMBER 테이블(phone_number)
    3. 주문금액 -> store_basket_id
    4. 배달팁 -> store_id_tips +


    +. address_detail -CONCAT-
 */
SELECT /* 특정 사용자의 주문하기 페이지 */
    -- 도로명주소
    address_detail, -- 상세주소
    -- 전화번호
    store_request, -- 사장님 요청사항
    rider_request, -- 라이더 요청사항
    -- 주문금액
    -- 배달팁
    -- 주문금액 + 배달팁 AS 총 결제금액
FROM ORDERS
    LEFT JOIN()
WHERE member_id = 4;

SHOW DATABASES;


# 9. 회원정보 수정 페이지
    -- 회원사진
    -- 닉네임
    -- 이메일
    -- 비밀번호 (암호화 된 상태로)
    -- 휴대폰 번호
    -- 이메일 수신동의
    -- SMS 수신동의


SELECT /* 메뉴그룹 ID와 이름 선택 */
                    id,         -- 메뉴그룹 ID
                    name        -- 메뉴그룹 이름
              FROM MENU_GROUP
              WHERE status = 'Used';




    name,
    info,
    CONCAT('최소주문금액\t', FORMAT(min_order_price, 0), '원') AS min_order_price,
    CONCAT('결제방법\t', payment_method) AS payment_method,
    CONCAT('배달시간\t', delivery_time, '분 소요 예상') AS delivery_time,
    CONCAT('배달팁\t', FORMAT(tips, 0), '원') AS tips,
    CONCAT('운영시간\t', hours) AS hours,
    CONCAT('휴무일\t', closed_day) AS closed_day,
    CONCAT('전화번호\t', phone_number) AS phone_number,
    CONCAT('배달지역\t', delivery_area) AS delivery_area,
    origin_inform,
    CONCAT('사업자주소\t', address, address_detail) AS address,
    CONCAT('사업자번호\t', reg_number) AS reg_number,
    store_image_url
FROM STORE S
WHERE id = 1;
-- 리뷰
-- 사장님 댓글 따로




# 서브쿼리
SELECT additional_tips
FROM DELIVERY_POLICY DP
                    JOIN (SELECT distinct_code
                          FROM ADDRESS A
                                   JOIN (SELECT address_code
                                         FROM MEMBER
                                         WHERE name = '금천_박현준') M on M.address_code = A.building_manage_no
                         ) AS DC ON DC.distinct_code = DP.town_code


# 1. SELECT 절 옆에는 컬럼명을 기술하지 않는다.
# 2. SELECT절 옆에는 해당 SELECT문이 무슨 역할을 의미하는지를 작성한다.
# 3. SELECT절 아래에 컬럼명을 기술한다.
# 5. 컬럼명 뒤에 주석을 이용하여 해당 컬럼의 한글명을 작성한다.
# SELECT /*쿼리의 용도 (예)고객의 카드번호 조회*/
#        INDEX_KEY     --인덱스키
#      , CUST_NO       --고객번호
#      , CUST_NAME     --고객명
#      , CUST_CARD_NO  --고객카드번호
#   FROM test.NEW_TABLE m
#  WHERE m.INDEX_KEY = '1111'
#    AND m.CUST_NO   = '1111111'

# 4. 메뉴 리스트
SELECT /* 쿼리의 용도: 해당 지점의 메뉴 리스트 조회 */
         M.name AS menu_name       -- 메뉴 이름
        ,M.price AS menu_price      -- 가격
        ,M.picture AS menu_image    -- 이미지 URL
        ,A.name AS group_name       -- 카테고리 이름
FROM MENU M
#      (SELECT * from ADDRESS) AS AD
JOIN ( SELECT C.id, C.name /* 쿼리의 용도: menu_group 카테고리 반환 */
       FROM MENU_GROUP C
       JOIN (SELECT id /* 쿼리의 용도 : store id 반환 */
             FROM STORE) as B ON B.id = store_id
     ) AS A ON A.id = menu_group_id;
-- 메뉴 리스트 ( MENU_GROUP )
-- 대표 메뉴 ( MENU )

# 4-1. 메뉴 리스트
SELECT /* 해당 지점 메뉴 리스트 조회 */
         M.name                            AS menu_name      -- 메뉴 이름
        ,M.price                           AS menu_price     -- 가격
        ,M.picture                         AS menu_image     -- 이미지 URL
        ,MG.name                           AS group_name     -- 카테고리 이름
        , IF(M.is_signature = 1, '1', '0') AS 'is_signature' -- 대표메뉴

FROM MENU M
    LEFT JOIN MENU_GROUP MG ON M.menu_group_id = MG.id;


# 5. 장바구니
SELECT /* 해당 지점의 주문 예정 리스트 */
    S.name,     -- 가게 이름 ( STORE )
    M.name,     -- 메뉴 이름 ( MENU )
    M.price,    -- 메뉴 가격 ( MENU )
    SB.amount,   -- 수량 ( STORE_BASKET )
    SUM(M.price * SB.amount) AS 'menu_price'  -- 한 메뉴의 총 주문금액
FROM STORE_BASKET SB
    INNER JOIN (SELECT
                    id,
                    name
                FROM STORE) AS S ON S.id = SB.store_id
    INNER JOIN ( SELECT
                    id,
                    name,
                    price
                 FROM MENU ) AS M on M.id = SB.menu_id
WHERE member_id = 1;

# 6. 장바구니 총 주문금액
SELECT  /* 한 유저의 한 가게의 장바구니 총 주문금액 */
    SUM(price*amount) AS total_price-- 총 주문금액
FROM STORE_BASKET SB
    JOIN (SELECT
                id
          FROM STORE ) AS S ON S.id = SB.store_id
    JOIN (SELECT
                id,
                price
          FROM MENU ) AS M ON M.id = SB.menu_id
WHERE member_id = 1;


