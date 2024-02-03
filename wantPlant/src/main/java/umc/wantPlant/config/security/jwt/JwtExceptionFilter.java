package umc.wantPlant.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import umc.wantPlant.apipayload.exceptions.GeneralException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response); // JwtAuthenticationFilter로 이동
        } catch (GeneralException exception) {
            setErrorResponse(response, exception);
        }
    }

    public void setErrorResponse(HttpServletResponse res, GeneralException exception)
            throws IOException {
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // 수정 필요(ErrorCode 리팩토링 후 수정)
//        res.setStatus(exception.getStatus().value());
        final Map<String, Object> body = new HashMap<>();
        body.put("code", exception.getCode());
        body.put("message", exception.getMessage());
        body.put("timestamp", LocalDateTime.now().toString());
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(res.getOutputStream(), body);
    }
}