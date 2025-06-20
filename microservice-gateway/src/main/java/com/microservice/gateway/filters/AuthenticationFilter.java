package com.microservice.gateway.filters;

import com.microservice.gateway.auth.AuthorizationRules;
import com.microservice.gateway.config.PublicPathConfig;
import com.microservice.gateway.utils.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


import io.jsonwebtoken.Claims;

import java.util.List;

@Component
public class AuthenticationFilter implements GatewayFilter {

    private final JwtUtil jwtUtil;
    private final AuthorizationRules authorizationRules;
    private final PublicPathConfig publicPathConfig;


    List<String> publicPaths = List.of(
            "/api/auth/login",
            "/api/auth/register",
            "/api/tienda/productos",
            "/api/tienda/productos/{idProductos}"
    );



    public AuthenticationFilter(JwtUtil jwtUtil, AuthorizationRules authorizationRules, PublicPathConfig publicPathConfig) {
        this.jwtUtil = jwtUtil;
        this.authorizationRules = authorizationRules;
        this.publicPathConfig = publicPathConfig;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getPath().toString();
        String method = exchange.getRequest().getMethod().name();

        System.out.println("🌐 Gateway: " + method + " " + requestPath);

        // Verificar si es una ruta pública
        if (publicPathConfig.isPublic(requestPath)) {
            System.out.println("✅ Acceso público permitido para: " + requestPath);
            return chain.filter(exchange);
        }

        // Resto de la lógica de autenticación...
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("❌ Falta token de autorización");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);
        try {
            Claims claims = jwtUtil.validateToken(token);
            String role = claims.get("role", String.class);

            if (role.startsWith("ROLE_")) {
                role = role.substring(5); // Elimina "ROLE_"
            }

            if (!authorizationRules.isAccessAllowed(requestPath, role)) {
                System.out.println("❌ Acceso denegado - Rol: " + role + ", Ruta: " + requestPath);
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }

            System.out.println("✅ Acceso autorizado - Rol: " + role + ", Ruta: " + requestPath);

            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(exchange.getRequest().mutate()
                            .header("X-User-Role", role)
                            .build())
                    .build();

            return chain.filter(modifiedExchange);

        } catch (Exception e) {
            System.out.println("❌ Token inválido: " + e.getMessage());
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}


