package com.iba.authservice.service.impl;


import com.iba.authservice.service.SecurityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityServiceImpl implements SecurityService {

   /* private final SessionService sessionService;

    private final UserService userService;

    private final JwtPropertiesConfig jwtPropertiesConfig;

    @Override
    public UsernamePasswordAuthenticationToken getAuthenticationFromRequest(HttpServletRequest request) {
        try {
            String sessionId = extractTokenFromRequest(request);
            Session session = sessionService.getSessionById(UUID.fromString(sessionId)).orElseThrow();//TODO
            String accessToken = session.getAccessToken();
            var jwt = decodeToken(accessToken);
            var userId = getUserId(jwt);
            var grantedAuthorities = getGrantedAuthorities(jwt);
            request.setAttribute("username", userId);
            request.setAttribute("authorities", grantedAuthorities);
            request.setAttribute("jwt", accessToken);
            return new UsernamePasswordAuthenticationToken(userId, null, grantedAuthorities);
        } catch (final JWTVerificationException e) {
            if (e instanceof TokenExpiredException) throw e;
            throw new IllegalStateException("Access token is invalid");
        }
    }

    @Override
    public UsernamePasswordAuthenticationToken getAttemptFromRequest(HttpServletRequest request) {
        try{
            final var loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            return new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Session createNewSession(UserPrincipal userPrincipal) {
        String token = generateAccessToken(userPrincipal);
        return sessionService.createSession(Session
                .builder()
                .userId(userPrincipal.getUserId())
                .accessToken(token)
                .roles(userPrincipal.getAuthorities().stream().map(grantedAuthority -> RoleType.valueOf(grantedAuthority.getAuthority())).collect(Collectors.toSet()))
                .build()
        ).orElseThrow();//TODO

    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(Session session) {
        return session.getRoles().stream().map(RoleType::name).map(SimpleGrantedAuthority::new).toList();
    }


    private String extractTokenFromRequest(final HttpServletRequest request) {
        final String headerAuth = request.getHeader(SecurityConst.AUTHORIZATION);
        if (StringUtils.isNotBlank(headerAuth) && headerAuth.startsWith(SecurityConst.TOKEN_PREFIX)) {
            return headerAuth.replace(SecurityConst.TOKEN_PREFIX, StringUtils.EMPTY);
        }
        throw new IllegalStateException("Bad authorisation header");
    }

    private DecodedJWT decodeToken(final String token) {
        return JWT
                .require(Algorithm.HMAC256(Objects.requireNonNull(jwtPropertiesConfig.getJwtSecret())))
                .build()
                .verify(token);
    }


    private String generateAccessToken(UserPrincipal userPrincipal) {
        return JWT.create()
                .withSubject(userPrincipal.getUserId().toString())
                .withClaim("cat", LocalDateTime.now().toString())
                .withClaim(SecurityConst.AUTHORITIES, userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withExpiresAt(Timestamp.valueOf(LocalDateTime.now().plusMinutes(jwtPropertiesConfig.getAccessTokenMinutes())))
                .sign(Algorithm.HMAC256(Objects.requireNonNull(jwtPropertiesConfig.getJwtSecret())));
    }

    private Long getUserId(DecodedJWT jwt) {
        var userId = jwt.getSubject();
        try{
            return Long.parseLong(userId);
        } catch (Exception e) {
            log.warn("Someone tried to access protected resource with illegal token subject");
            throw new IllegalStateException("Access token is invalid");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(DecodedJWT jwt) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        try {
            final var authorities = jwt.getClaim(SecurityConst.AUTHORITIES);
            if (!authorities.isNull()) {
                return authorities.asList(String.class)
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
            }
        } catch (JWTDecodeException e) {
            log.warn("Someone tried to access protected resource with illegal 'authorities' claim");
        }
        return grantedAuthorities;
    }

    private HttpCookie createRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from(REFRESH_TOKEN_COOKIE_NAME, refreshToken)
                .maxAge(Duration.ofDays(jwtPropertiesConfig.getRefreshTokenDays()))
                .httpOnly(true)
                .sameSite("Strict")
                .path("/refresh-token")
                .build();
    }*/

}
