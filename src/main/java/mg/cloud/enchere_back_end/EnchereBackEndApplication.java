package mg.cloud.enchere_back_end;

//import mg.cloud.enchere_back_end.filters.Filter;
import mg.cloud.enchere_back_end.filters.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.lang.NonNull;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class EnchereBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnchereBackEndApplication.class, args);
    }

    @Bean
    WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("https://auctions-app.netlify.app")
                        .allowedOriginPatterns("http://localhost:[*]")
                        .allowedOrigins("localhost:3000")
                        .allowedMethods("*");
            }
        };
    }
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean(Filter filter) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.addUrlPatterns("/admin/logout");
        filterRegistrationBean.addUrlPatterns("/categories");
        filterRegistrationBean.addUrlPatterns("/categories/*");
        filterRegistrationBean.addUrlPatterns("/settings");
        filterRegistrationBean.addUrlPatterns("/settings/*");
        filterRegistrationBean.addUrlPatterns("/reloads");
        filterRegistrationBean.addUrlPatterns("/reloads/validations");
        filterRegistrationBean.addUrlPatterns("/statistics/*");
        filterRegistrationBean.addUrlPatterns("/users");
        filterRegistrationBean.addUrlPatterns("/users/*");
        filterRegistrationBean.addUrlPatterns("/auctions");
        filterRegistrationBean.addUrlPatterns("/auctions/*");
        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("localhost:3000");
        config.addAllowedOrigin("https://auctions-app.netlify.app");
        config.addAllowedOrigin("auctions-app.netlify.app");
        config.setAllowedMethods(Arrays.asList("POST", "OPTIONS", "GET", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
//    @Bean
//    public CorsFilter corsFilter() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        final CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        // Don't do this in production, use a proper list  of allowed origins
//        config.setAllowedOrigins(Collections.singletonList("*"));
//        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
//        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
}
