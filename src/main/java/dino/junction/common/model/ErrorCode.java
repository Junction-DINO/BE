package dino.junction.common.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {
    // Basic HTTP Status Code
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    // 1000: Authentication
    INVALID_VERIFICATION_CODE(1000, "인증 코드가 유효하지 않습니다"),
    VERIFICATION_CODE_NOT_FOUND(1001, "인증 코드를 찾을 수 없습니다"),
    LOGIN_FAILED(1002, "로그인 실패"),
    INVALID_PHONE_NUMBER(1003, "휴대폰 번호가 유효하지 않습니다"),
    EXPIRED_JWT_TOKEN(1100, "JWT 토큰의 유효기간이 만료되었습니다"),
    INVALID_JWT_TOKEN(1101, "JWT 토큰이 유효하지 않습니다"),

    TEMPLATE_NOT_FOUND(2000, "템플릿이 존재하지 않습니다.");

    private final int code;
    private final String message;
}