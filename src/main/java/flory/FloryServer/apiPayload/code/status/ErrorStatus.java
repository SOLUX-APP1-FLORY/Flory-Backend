package flory.FloryServer.apiPayload.code.status;

import flory.FloryServer.apiPayload.code.BaseErrorCode;
import flory.FloryServer.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 유저 관련 에러
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4001", "존재하지 않는 회원입니다."),
    USER_EXISTED(HttpStatus.BAD_REQUEST, "USER4002", "이미 존재하는 회원입니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "USER4003", "닉네임은 필수 입니다."),

    // 예시,,,
    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND, "DIARY4001", "다이어리가 없습니다."),
    // AWS AWS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "AWS001", "aws client error")


    // For TEST
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "테스트용 예외 처리")
    ;



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }

}
