package br.com.marcelo.restwithspringbootinjava.securityJwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.marcelo.restwithspringbootinjava.data.vo.v1.security.TokenVO;
import br.com.marcelo.restwithspringbootinjava.exceptions.InvalidJwtAuthenticationException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
@Service
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; //1h em milisegundos
    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm  algorithm = null;

    @PostConstruct
    /*
     * When we annotate a method in Spring Bean with @PostConstruct annotation, it gets executed after the spring bean is initialized. We can have only one method annotated with @PostConstruct annotation. 
     */
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }
    public TokenVO createAccessToken(String username,List<String> roles){
        Date now = new Date();
        Date validity = new Date(now.getTime()+validityInMilliseconds);
        var accessToken = getAccessToken(username,roles,now,validity);
        var refreshToken = getRefreshToken(username,roles,now);
        return new TokenVO(username,true,now,validity,accessToken,refreshToken);
    }
    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
        String issueUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
        .build().toUriString();
        return JWT.create()
            .withClaim("roles", roles)
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .withSubject(username)
            .withIssuer(issueUrl)
            .sign(algorithm)
            .strip();
    }
    private String getRefreshToken(String username, List<String> roles, Date now) {
        Date validityRefreshToken = new Date(now.getTime()+(validityInMilliseconds*3)); 
        return JWT.create()
            .withClaim("roles", roles)
            .withIssuedAt(now)
            .withExpiresAt(validityRefreshToken)
            .withSubject(username)
            .sign(algorithm)
            .strip();
    }
    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = decodeToken(token);
        UserDetails userDetails = this.userDetailsService
                                .loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "",userDetails.getAuthorities());
    }
    private DecodedJWT decodeToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }
    public String resolveToken(HttpServletRequest req){
        String bearerToken = req.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }
    public Boolean validateToken(String token){
        DecodedJWT decodedJWT = decodeToken(token);
        try {
            if(decodedJWT.getExpiresAt().before(new Date()))
                return false;
            
            return true;
        } catch (Exception e) {
            throw new InvalidJwtAuthenticationException("Expired or Invalid JWT Token");
        }
    }
}
