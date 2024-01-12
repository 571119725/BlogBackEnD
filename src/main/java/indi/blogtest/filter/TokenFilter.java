package indi.blogtest.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import indi.blogtest.util.ResultUtils;
import indi.blogtest.util.Token;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter(filterName = "TokenFilter", urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        String method = request.getMethod();
        if(uri.contains("/addBlog") || uri.contains("/editBlog") || uri.contains("/deleteBlog") || uri.contains("/updateUserInfo")){
            if("post".equalsIgnoreCase(method)){
                String reqToken = request.getHeader("Authorization");
                if (reqToken != null) {
                    if (reqToken.equals(Token.getToken())) {
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    }
                }
                servletResponse.setCharacterEncoding("utf-8");
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(servletResponse.getWriter(), ResultUtils.error("请先登录！"));
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
//        Filter.super.destroy();
    }
}
