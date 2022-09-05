package cc.roadmaps.core.service.config;

import cc.roadmaps.core.service.integrations.externalAuthProviders.model.AuthProviderRepository;
import cc.roadmaps.core.service.integrations.externalAuthProviders.services.AuthProviderService;
import cc.roadmaps.core.service.integrations.externalAuthProviders.services.AuthProviderServiceImpl;
import cc.roadmaps.core.service.integrations.web.rest.authorization.filter.JwtFilter;
import cc.roadmaps.core.service.integrations.web.rest.filters.CORSFilter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @NonNull
    private final JwtFilter jwtFilter;
    @NonNull
    private final CORSFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // @formatter:off
        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
        // @formatter:on
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/api/auth/login/provider/google",
                "/api/swagger-ui/**",
                "/api/swagger/**",
                "/api/hrid/**"
        );
    }

    @Bean
    public AuthProviderService getAuthProviderService(
            AuthProviderRepository repository
    ) {
        return new AuthProviderServiceImpl(repository);
    }

}