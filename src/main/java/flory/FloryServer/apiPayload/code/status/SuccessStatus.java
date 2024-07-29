package flory.FloryServer.apiPayload.code.status;

import flory.FloryServer.apiPayload.code.BaseCode;
import flory.FloryServer.apiPayload.code.ReasonDTO;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    _OK(HttpStatus.OK, "2000", "OK"),;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason(){
        return ReasonDTO.builder()
                .code(message)
                .message(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .code(message)
                .message(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }

}
