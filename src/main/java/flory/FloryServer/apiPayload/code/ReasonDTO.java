package flory.FloryServer.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ReasonDTO {

    private final HttpStatus httpStatus;

    private final boolean isSuccess;
    private final String code;
    private final String message;

    // 코드 추가 -> ?
    public boolean getIsSuccess(){return isSuccess;}

}
