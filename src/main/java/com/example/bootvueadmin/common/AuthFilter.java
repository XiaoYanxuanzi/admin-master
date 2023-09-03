package com.example.bootvueadmin.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class AuthFilter implements Filter {

    // 白名单 表示应该放行的请求
    private static final String[] WHITE_URLS = {"/login.html", "/register.html", "/user/login", "/user/register"};
    private static final String[] FILE_SUFFIX = {"jpeg", "jpg","png","gif","bmp", "webp", "css","js", "woff", "woff2"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = request.getServletPath();
        if (Arrays.stream(WHITE_URLS).anyMatch(url -> url.equals(servletPath)) || endWith(servletPath)) {  // java8 Stream API
            filterChain.doFilter(request, response);  // 放行请求
        } else {
            Object user = request.getSession().getAttribute("user");
            if (user != null) {
                filterChain.doFilter(request, response);  // 放行请求
            } else {
                response.sendRedirect("/login.html");
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean endWith(String path) {
        for (String fileSuffix : FILE_SUFFIX) {
            if (path.endsWith(fileSuffix)) {
                return true;
            }
        }
        return false;
    }
}
