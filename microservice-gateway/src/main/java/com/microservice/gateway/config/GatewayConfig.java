package com.microservice.gateway.config;

import com.microservice.gateway.filters.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                //rutas del modulo de usuarios:
                .route("usuarios", r -> r.path("/api/auth/profile/**")
                        .filters(f -> f.filter (authenticationFilter))
                        .uri("http://localhost:8080"))
                .route("usuarios", r -> r.path("/api/auth/validate**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8080"))
                .route("usuarios", r -> r.path("/api/disponibilidades/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8080"))
                .route("usuarios", r -> r.path("/api/mascotas/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8080"))
                .route("usuarios", r -> r.path("/api/usuarios/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8080"))
                .route("usuarios", r -> r.path("/api/veterinarios/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8080"))
                //rutas de la tienda:
                .route("tienda_veterinaria", r -> r.path("/api/tienda/productos/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:5050"))
                .route("tienda_veterinaria", r -> r.path("/api/tienda/carritos/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:5050"))
                .route("tienda_veterinaria", r -> r.path("/api/tienda/carrito-productos/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:5050"))
                //rutas del modulo citas
                .route("ModuloCitas", r -> r.path("/agenda/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8081"))
                .route("ModuloCitas", r -> r.path("/historiaClinica/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8081"))
                .route("ModuloCitas", r -> r.path("/servicios/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8081"))
                //rutas del modulo de pagos:
                .route("ModuloPagosStripe", r -> r.path("/pagos/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:5500"))
                //rutas del modulo de notificaciones
                .route("ModuloNotificaciones", r -> r.path("/crear-sesion-pago/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8000"))
                .build();
    }
}
