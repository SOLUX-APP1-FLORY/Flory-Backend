package flory.FloryServer.apiPayload.code;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ReasonDTO {
    private String code;
    private String message;
    private Boolean isSuccess;
    private HttpStatus httpStatus;
}
