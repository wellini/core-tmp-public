package cc.roadmaps.core.service.config;

import cc.roadmaps.core.service.integrations.web.rest.api.auth.filters.JwtFilter;
import cc.roadmaps.core.service.integrations.web.rest.filters.CORSFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends GlobalMethodSecurityConfiguration {

    private final JwtFilter jwtFilter;

    private final CORSFilter corsFilter;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(
                "/api/actuator/health/readiness",
                "/api/actuator/health/liveness",
                "/api/auth/login/provider/google",
                "/swagger-ui/*",
                "/swagger/*"
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, AnonymousAuthenticationFilter.class)
                .addFilterBefore(corsFilter, JwtFilter.class);
        // @formatter:on
        return http.build();
    }
}