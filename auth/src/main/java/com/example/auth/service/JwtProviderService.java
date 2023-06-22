package com.example.auth.service;

import com.example.auth.config.SecretKeyProvider;
import com.example.auth.domain.User;
import com.example.auth.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtProviderService {
    public static final String WRONG_PASSWORD = "Неверный пароль";

    private final UserService userService;
    private final SecretKeyProvider secretKeyProvider;

    public String getToken(String login, String password) {
        User user = userService.getUserByLogin(login);

        validateAuthParams(password, user);

        SecretKey key = secretKeyProvider.secretKey();

        Map<String, List<String>> claims = new HashMap<>();
        List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        claims.put("ROLES", roles);
        claims.put("LOGIN", Collections.singletonList(login));

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + DateUtils.MILLIS_PER_DAY))
//                .setSubject(login)
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    private void validateAuthParams(String password, User user) {
        PasswordEncoder passwordEncoder = createPasswordEncoder();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalStateException(WRONG_PASSWORD);
        }
    }

    public UserDto getUserFromToken(String token) {
        try {
//            Claims jwsBody = Jwts.parserBuilder()
//                    .setSigningKey(secretKeyProvider.secretKey())
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//            String login = jwsBody.getSubject();


//            Jws<Claims> claimsJws = Jwts.parserBuilder()
//                    .setSigningKey(secretKeyProvider.secretKey())
//                    .build()
//                    .parseClaimsJws(token);

            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKeyProvider.secretKey())
                    .build()
                    .parseClaimsJws(token);

            List<String> authorities = (List<String>) claims.getBody().get("ROLES");
            String login = ((List<String>) claims.getBody().get("LOGIN")).get(0);

            return new UserDto(login, authorities);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    // toDo вытащить в конфиг и сделать бин, наверное. Если будут разные экземляры, то может ли возникнуть ошибка несовпадения?
    private PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
