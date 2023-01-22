package mg.cloud.enchere_back_end;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
                        .allowedOriginPatterns("http://localhost:[*]")
                        .allowedOriginPatterns("https://auctions-app.netlify.app")
                        .allowedMethods("*");
            }
        };
    }
}
