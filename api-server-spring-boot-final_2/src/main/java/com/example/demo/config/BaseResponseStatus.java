package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    // Common
    SUCCESS(true, 1000, "요청에 성공하였습니다."),



    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),
	EMPTY_CONTENT(false, 2004, "내용을 입력해주세요"),

    // [POST] /users
	POST_USERS_INVALID_EMAIL(false, 2012, "올바른 이메일 형식이 아닙니다."),
    POST_USERS_INVALID_PHONE_NUMBER(false, 2013, "올바른 번호형식이 아닙니다."),
	POST_USERS_EXISTS_PHONE_NUMBER(false,2014, "중복된 휴대전화입니다."),
    POST_USERS_EXISTS_EMAIL(false,2015,"중복된 이메일입니다."),
	POST_USERS_EXISTS_NICKNAME(false,2016,"중복된 닉네임입니다."),
	POST_USERS_EXISTS_USER(false,2017,"이미 존재하는 회원입니다."),

	// [POST] /users/:userId/baskets/:basketId
	POST_USERS_EXISTS_BASKET(false, 2020, "다른 가게의 장바구니가 이미 존재합니다."),

	// [GET] /users/:userId/reviews/:reviewId
	GET_NONUSER_INFO(false, 2025, "존재하지 않는 사용자 요청입니다."),

	// [GET] /stores/:storeId/menus/:menuId
	LESS_THAN_MINIMUM_PRICE(false, 2030, "주문최소금액을 맞춰주세요."),

	// [POST] /users/:userId/orders/:orderId/review
	POST_NON_EXIST_RATING(false, 2035, "평점을 입력해주세요."),



    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "요청 처리에 실패하였습니다."),
	FAILED_TO_AUTH(false, 3001, "인가되지 않은 사용자 요청입니다."),

	// [POST] /users/login
	FAILED_TO_LOGIN(false, 3010, "존재하지 않는 계정입니다."),



    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");


    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
