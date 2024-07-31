package flory.FloryServer.login.config;
import flory.FloryServer.login.exception.JwtAccessDeniedHandler;
import flory.FloryServer.login.exception.JwtAuthenticationEntryPoint;
import flory.FloryServer.login.jwt.JwtFilter;
import flory.FloryServer.login.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Configuration
@PropertySource("classpath:application.yml")
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // CSRF 설정 Disable
        http.csrf( csrf -> csrf.disable()) //.disable()

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                // 우리가 만든 exception handling 클래스 설정
                .exceptionHandling( exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 헤더 설정
                .headers(headers -> headers
                        .frameOptions(frameOption -> frameOption.sameOrigin()))
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin()

                // 시큐리티는 기본적으로 세션을 사용
                // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // URI 인증 설정
                // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정, 스웨거url도 허용!!
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/v1/auth/**","/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                );
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/auth/**").permitAll()
//                .anyRequest().authenticated()   // 나머지 API 는 전부 인증 필요

        // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
//                .and()
//                .apply(new JwtSecurityConfig(tokenProvider));
        // JwtFilter를 직접 생성해 HttpSecurity에 추가
        JwtFilter jwtFilter = new JwtFilter(tokenProvider);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
            /* Spring Security 6.x 부터는
            메서드 체이닝 사용을 지양하고
            람다식을 통해 함수형으로 설정하게 하는 방식을 도입 */
}
