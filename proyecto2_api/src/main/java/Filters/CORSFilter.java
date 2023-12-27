package Filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CORSFilter implements Filter {

    private List<String> allowedOrigins;

    public void init(FilterConfig filterConfig) throws ServletException {
        // Configura los orígenes permitidos
        allowedOrigins = new ArrayList<>();
        allowedOrigins.add("http://localhost:4200");
    
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String originHeader = request.getHeader("Origin");
        if (allowedOrigins.contains(originHeader)) {
            response.setHeader("Access-Control-Allow-Origin", originHeader);
            response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Max-Age", "3600");

            chain.doFilter(request, response);
        } else {
            // Origen no permitido
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    public void destroy() {

    }
}

