package flory.FloryServer.login.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenRequestDto {
    // for reissue
    private String accessToken;
    private String refreshToken;
}
