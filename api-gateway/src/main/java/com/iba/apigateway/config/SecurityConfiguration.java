package com.iba.apigateway.config;



//@Configuration
//@EnableWebFluxSecurity
public class SecurityConfiguration {
   /* @Order(1)
    @Bean
    SecurityWebFilterChain actuatorHttpSecurity(ServerHttpSecurity http) {
        http
                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/actuator/**"))
                .authorizeExchange((exchanges) -> exchanges
                        .anyExchange().permitAll()
                );
        return http.build();
    }
    @Bean
    SecurityWebFilterChain apiHttpSecurity(ServerHttpSecurity http) {
        http
                .authorizeExchange((exchanges) -> exchanges
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer((resourceServer) -> resourceServer
                        .jwt(Customizer.withDefaults()))
                .csrf(csrfSpec -> csrfSpec.disable())
                .cors(corsSpec -> corsSpec.disable());
        return http.build();
    }*/
}