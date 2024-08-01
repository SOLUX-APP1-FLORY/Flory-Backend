package flory.FloryServer.login.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    // JWT 인증에 실패했을 때 호출되는 진입점

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException)
            throws IOException {
        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401 (eg. 잘못된 유저 정보, 유효하지 않은 토큰)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
