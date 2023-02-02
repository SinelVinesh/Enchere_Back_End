package mg.cloud.enchere_back_end.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.cloud.enchere_back_end.Service.AppUserTokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AppUserFilter extends OncePerRequestFilter {
    private final AppUserTokenService appUserTokenService;

    public AppUserFilter(AppUserTokenService appUserTokenService) {
        this.appUserTokenService = appUserTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String[] urls = new String[]{
                "/auctions"
        };
        if(request.getMethod().equals("GET")){
            for(String url : urls){
                if(request.getRequestURI().matches(url)){
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        }
        if(request.getMethod().equals("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");
        if(token != null) {
            token = token.split(" ")[1];
        }
        try {
            if (appUserTokenService.authenticate(token)) {
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(401);
            }
        } catch (Exception e) {
            response.setStatus(401);
            throw new ServletException(e.getMessage());
        }
    }
}
