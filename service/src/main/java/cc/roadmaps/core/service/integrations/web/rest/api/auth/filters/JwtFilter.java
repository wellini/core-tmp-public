package cc.roadmaps.core.service.integrations.web.rest.api.auth.filters;

import cc.roadmaps.core.service.integrations.web.rest.api.auth.exceptions.NoTokenException;
import cc.roadmaps.core.service.integrations.web.rest.api.auth.services.JwtTokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String TOKEN_HEADER_NAME = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private final JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        ArrayList<String> headers = new ArrayList<>();
        request.getHeaderNames().asIterator().forEachRemaining(headers::add);
        String token = extractToken(request.getHeader(TOKEN_HEADER_NAME));

        DecodedJWT decodedToken = jwtTokenService.decode(token);

        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(decodedToken.getSubject(), null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private String extractToken(String rawHeaderValue) {
        if(Objects.isNull(rawHeaderValue)) {
            throw new NoTokenException(String.format("%s header is empty, cannot extract token", TOKEN_HEADER_NAME));
        }

        return rawHeaderValue.replace(TOKEN_PREFIX, "");
    }
}
