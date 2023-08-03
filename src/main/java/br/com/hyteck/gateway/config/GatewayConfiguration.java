package br.com.hyteck.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
    @Bean
    public RouteLocator customRouterLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("cloud config", r -> r
                        .path("/config/**")
                                .filters(f -> f.rewritePath("/config/(?<params>.*)","/${params}" ))
                        .uri("lb://configServer")
                        )
                .route("service discovery", r -> r
                        .path("/eureka/**")
                        .filters(f -> f.rewritePath("/config/(?<params>.*)","/${params}" ))
                        .uri("lb://discovery")
                )
                .build();
    }
}
