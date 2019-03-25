package in.hocg.springcloud.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

/**
 * Created by hocgin on 2019/3/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Configuration
public class GatewayRouter {
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        StripPrefixGatewayFilterFactory.Config config = new StripPrefixGatewayFilterFactory.Config();
//        config.setParts(1);
        return builder.routes()
                .route(p -> p.path("/baidu")
                        .uri(URI.create("http://www.baidu.com")))
                .route(p -> p.path("/c")
                        .uri("http://consumer-app"))
                .build();
    }
}
