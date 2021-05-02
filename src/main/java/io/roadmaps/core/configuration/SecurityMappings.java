package io.roadmaps.core.configuration;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class SecurityMappings extends AbstractHttpConfigurer<SecurityMappings, HttpSecurity> {

//    @Override
//    public void init(HttpSecurity builder) throws Exception {
//        builder.authorizeRequests()
//                // Authentication & authorization
//                .antMatchers("/1.0/access-denied").permitAll()
//                .antMatchers("/1.0/login").permitAll()
//                .antMatchers("/1.0/registration").permitAll()
//                .antMatchers("/1.0/registration/confirm/**").authenticated()
//                .antMatchers("/1.0/logout").permitAll()
//                .antMatchers("/1.0./change-password").hasAuthority("UPDATE_OWN_PASSWORD")
//                .antMatchers("/1.0/change-password/caonfirm/**").hasAuthority("UPDATE_OWN_PASSWORD")
//                .antMatchers("/1.0/auth").hasAuthority("READ_AUTHENTICATION")
//                .anyRequest().authenticated();
//    }
//
//    public static SecurityMappings securityMappings() {
//        return new SecurityMappings();
//    }
}