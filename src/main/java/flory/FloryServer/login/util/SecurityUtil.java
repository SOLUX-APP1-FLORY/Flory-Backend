package flory.FloryServer.login.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtil {
    // 유저 정보를 SecurityContext 에 저장
    private SecurityUtil() { }

    // Request 가 들어올 때 JwtFilter의 doFilter에서 저장
    public static Long getCurrentUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("Security Context에 인증 정보가 없습니다");
        }

        // userId를 저장하게 했으므로 꺼내서 Long 타입으로 파싱 하여 반환
        return Long.parseLong(authentication.getName());
    }
}
