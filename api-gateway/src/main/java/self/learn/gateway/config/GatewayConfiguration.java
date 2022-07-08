package self.learn.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		/*Function<PredicateSpec, Buildable<Route>> router = p->p.path("/get")
				.filters(f->f
						.addRequestHeader("MyHeader", "MYURI")
						.addRequestParameter("MyParam", "MyValues"))
				.uri("http://httpbin.org:80");*/
				
		
		return builder.routes()
				.route( p->p.path("/get")
						.filters(f->f
								.addRequestHeader("MyHeader", "MYURI")
								.addRequestParameter("MyParam", "MyValues"))
						.uri("http://httpbin.org:80"))
				//p.path("/currency-exchange/**") : it says that when the path consist as mentioned 
				//that needs to be redirected to the mentioned service in uri and the name in uri
				//should be as per registered in eureka; lb means load balanced
				
				.route(p->p.path("/currency-exchange/**").uri("lb://currency-exchange-service"))
				.route(p->p.path("/currency-conversion/**").uri("lb://currency-conversion-service"))
				.route(p->p.path("/currency-conversion-feign/**").uri("lb://currency-conversion-service"))
				//if want to rewrite the url then below filter should be there
				.route(p -> p.path("/convert/**")
						.filters(f -> f.rewritePath
								("/convert/(?<segment>.*)", //replace convert with below context
								"/currency-conversion-feign/${segment}"))
						.uri("lb://currency-conversion-service"))
				
				.build();
	}

}
