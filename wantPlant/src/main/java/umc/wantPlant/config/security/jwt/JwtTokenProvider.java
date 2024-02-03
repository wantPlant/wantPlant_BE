package umc.wantPlant.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.apipayload.exceptions.GeneralException;
import umc.wantPlant.config.security.oauth.PrincipalDetails;
import umc.wantPlant.config.security.oauth.PrincipalDetailsService;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${jwt.secret_key}")
    private String jwtSecretKey;

    private final PrincipalDetailsService principalDetailsService;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String REFRESH_HEADER = "refreshToken";
    private static final long TOKEN_VALID_TIME = 1000 * 60L * 60L * 24L;  // 유효기간 1일
    private static final long REF_TOKEN_VALID_TIME = 1000 * 60L * 60L * 24L * 14L;  // 유효기간 14일

    public String generateAccessToken(Long memberId) {
        Date now = new Date();
        Date accessTokenExpirationTime = new Date(now.getTime() + TOKEN_VALID_TIME);

        Claims claims = Jwts.claims();
        claims.put("memberId", memberId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(accessTokenExpirationTime)
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .compact();
    }

    public String generateRefreshToken(Long memberId) {
        Date now = new Date();
        Date refreshTokenExpirationTime = new Date(now.getTime() + REF_TOKEN_VALID_TIME);

        Claims claims = Jwts.claims();
        claims.put("memberId", memberId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(refreshTokenExpirationTime)
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .compact();
    }

    public TokenInfo generateToken(Long memberId) {

        String accessToken = generateAccessToken(memberId);
        String refreshToken = generateRefreshToken(memberId);

        return new TokenInfo(accessToken, refreshToken);
    }
    //Todo:Error Handling
    public Authentication getAuthentication(String token) {
        try {
            PrincipalDetails principalDetails = principalDetailsService.loadUserByUsername(
                    getMemberIdByToken(token));
            return new UsernamePasswordAuthenticationToken(principalDetails,
                    "", principalDetails.getAuthorities());
        } catch (UsernameNotFoundException exception) {
            throw new GeneralException(ErrorStatus.LOGIN_GENERAL_ERROR);
        }
    }

    public Authentication getRefreshAuthentication(String token) {
        try {
            PrincipalDetails principalDetails = principalDetailsService.loadUserByUsername(
                    getMemberIdByRefreshToken(token));
            return new UsernamePasswordAuthenticationToken(principalDetails,
                    "", principalDetails.getAuthorities());
        } catch (UsernameNotFoundException exception) {
            throw new GeneralException(ErrorStatus.LOGIN_GENERAL_ERROR);
        }
    }

    public String getMemberIdByToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).
                getBody().get("memberId").toString();
    }
    public String getMemberIdByRefreshToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).
                getBody().get("memberId").toString();
    }
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER);
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader(REFRESH_HEADER);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public boolean validateRefreshToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
