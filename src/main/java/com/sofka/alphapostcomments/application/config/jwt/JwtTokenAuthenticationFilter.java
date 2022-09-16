package com.sofka.alphapostcomments.application.config.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter implements WebFilter {

    public static final String HEADER_PREFIX ="Bearer ";
    private final JwtTokenProvider jwtTokenProvider;

    private String resolveToken(ServerHttpRequest request){
        var bearerToken=request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        return StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX) ?
                bearerToken.substring(7) : null;

    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        var token = resolveToken(exchange.getRequest());
        if(StringUtils.hasText(token) && this.jwtTokenProvider.validateToken(token)){
            var authentication = this.jwtTokenProvider.getAuthentication(token);
            return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
        }
        if(!StringUtils.hasText(token)){
            log.error("Token isn't provider");
        }
        return chain.filter(exchange);
    }
}
