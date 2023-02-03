package mg.cloud.enchere_back_end;

//import mg.cloud.enchere_back_end.filters.Filter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import mg.cloud.enchere_back_end.Service.FirebaseMessagingService;
import mg.cloud.enchere_back_end.filters.AdminFilter;
import mg.cloud.enchere_back_end.filters.AppUserFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.NonNull;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.Arrays;

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
    public FilterRegistrationBean<AdminFilter> filterRegistrationBean(AdminFilter adminFilter) {
        FilterRegistrationBean<AdminFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(adminFilter);
        filterRegistrationBean.addUrlPatterns("/admin/logout");
        filterRegistrationBean.addUrlPatterns("/categories");
        filterRegistrationBean.addUrlPatterns("/categories/*");
        filterRegistrationBean.addUrlPatterns("/settings");
        filterRegistrationBean.addUrlPatterns("/settings/*");
        filterRegistrationBean.addUrlPatterns("/reloads");
        filterRegistrationBean.addUrlPatterns("/reloads/validations");
        filterRegistrationBean.addUrlPatterns("/statistics/*");
        filterRegistrationBean.addUrlPatterns("/auctions/{id}");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<AppUserFilter> appUserFilterRegistrationBean(AppUserFilter appUserFilter) {
        FilterRegistrationBean<AppUserFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(appUserFilter);
        filterRegistrationBean.addUrlPatterns("/auctions/bid");
        filterRegistrationBean.addUrlPatterns("/auctions");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("http://localhost:8100");
        config.addAllowedOrigin("localhost:3000");
        config.addAllowedOrigin("https://auctions-app.netlify.app");
        config.addAllowedOrigin("auctions-app.netlify.app");
        config.addAllowedOriginPattern("*");
        config.setAllowedMethods(Arrays.asList("POST", "OPTIONS", "GET", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(
                new ClassPathResource("enchere-cloud-firebase-adminsdk.json").getInputStream()
        );
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "enchere");
        return FirebaseMessaging.getInstance(app);
    }
}
