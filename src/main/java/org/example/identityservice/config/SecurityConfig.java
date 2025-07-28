package org.example.identityservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final String[] PUBLIC_URLS = {"/users/create", "/auth/log-ịn", "/auth/introspect", "/auth/log-out", "/auth/refresh"};
    private CustomJwtDecoder jwtDecoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationConverter jwtAuthenticationConverter) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests ->
                                requests.requestMatchers(HttpMethod.POST, PUBLIC_URLS).permitAll()
//                                .requestMatchers(HttpMethod.GET, "/users/get-all").hasRole(Role.ADMIN.name())
                                        .anyRequest().authenticated()
                );


        // nhận và xác thực JWT từ client trong các request
        // oauth2.jwt(): yêu cầu xác thực các request bằng JWT
        // jwtConfigurer.decoder(jwtDecoder()): chỉ định decoder tùy chỉnh để giải mã và xác thực JWT.
        // 👉 Tóm lại: Đây là nơi nói với Spring Security rằng:
        //"Mỗi request phải có JWT trong header. Và khi nhận được, hãy dùng hàm jwtDecoder() để kiểm tra và phân tích token đó."
        http.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwtConfigurer ->
                                jwtConfigurer.decoder(jwtDecoder)
                                        .jwtAuthenticationConverter(jwtAuthenticationConverter())) // chuyển JWT -> Authentication
                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint()) // xảy ra ngoại lệ trong filterChain sẽ xử lý ở đây
        );

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();// lâấy ra quyền từ claim scope
        grantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return converter;
    }

    /**
     * kiểm tra phân tích token
     * Token phải được ký bởi server bằng secret key signerKey,
     * và server sẽ dùng NimbusJwtDecoder để giải mã,
     * xác minh chữ ký, kiểm tra hạn token.
     */
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
//        return NimbusJwtDecoder
//                .withSecretKey(secretKeySpec)
//                .macAlgorithm(MacAlgorithm.HS512)
//                .build();
//    }

}
