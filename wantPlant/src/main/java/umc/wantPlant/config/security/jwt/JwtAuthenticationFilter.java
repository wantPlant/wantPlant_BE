package umc.wantPlant.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import umc.wantPlant.config.security.jwt.JwtTokenProvider;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // refresh Token이 있다면 refresh Token 사용
        String token = jwtTokenProvider.resolveRefreshToken((HttpServletRequest) request);

        if(token != null) {
            if (token.startsWith("Bearer ")) token = token.substring(7);

            if (((HttpServletRequest) request).getRequestURI()
                    .equals("/members/token/refresh") && jwtTokenProvider.validateRefreshToken(token)) {
                Authentication authentication = jwtTokenProvider.getRefreshAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } else {
            token =  jwtTokenProvider.resolveToken((HttpServletRequest) request);
            if (token != null) {
                if (token.startsWith("Bearer ")) token = token.substring(7);

                if (jwtTokenProvider.validateToken(token)) {
                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        chain.doFilter(request, response);
    }
}