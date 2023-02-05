package mg.cloud.enchere_back_end.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import mg.cloud.enchere_back_end.Service.AdminTokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class AdminFilter extends OncePerRequestFilter {
    private final AdminTokenService tokenService;

    public AdminFilter(AdminTokenService tokenService) {
        this.tokenService = tokenService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String[] urls = new String[]{
                "/categories","/categories/.*",
                "/users","/users/.*", "/auctions/.*",
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
        if(tokenService.authenticate(token)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(401);
        }
    }
}
