package com.utkarshprojects.salestrackingtool.config;
import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class ResponseWebFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Strict-Transport-Security", "max-age=63072000; includeSubDomains; preload");
        httpServletResponse.setHeader("Content-Security-Policy", "default-src 'self';");
        httpServletResponse.setHeader("X-Frame-Options", "SAMEORIGIN");
        httpServletResponse.setHeader("X-XSS-Protection", "1; mode=block");
        httpServletResponse.setHeader("X-Content-Type-Options", "nosniff");
        httpServletResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // ...
    }

    @Override
    public void destroy() {
        // ...
    }
}
