package mg.cloud.enchere_back_end;

import mg.cloud.enchere_back_end.filters.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
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
}
