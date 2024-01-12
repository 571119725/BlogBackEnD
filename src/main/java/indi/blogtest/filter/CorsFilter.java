package indi.blogtest.filter;

import com.mysql.cj.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@WebFilter(filterName="CorsFilter", urlPatterns = "/*",
    initParams = {@WebInitParam(name="allowOrigin", value="*"),
            @WebInitParam(name="allowMethods", value="POST,GET,PUT,DELETE,OPTIONS"),
            @WebInitParam(name="allowCredentials", value="true"),
            @WebInitParam(name="allowHeaders", value="Content-Type,X-token,Authorization")
    })
public class CorsFilter implements Filter {
    private String allowOrigin;
    private String allowMethods;
    private String allowCredentials;
    private String allowHeaders;
    @Override
    public void init(FilterConfig filterConfig)  {
        allowOrigin = filterConfig.getInitParameter("allowOrigin");
        allowMethods = filterConfig.getInitParameter("allowMethods");
        allowCredentials = filterConfig.getInitParameter("allowCredentials");
        allowHeaders = filterConfig.getInitParameter("allowHeaders");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!StringUtils.isNullOrEmpty(allowOrigin)) {
            if (allowOrigin.equals("*")) {
                response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            }else {
                List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
                if (!allowOriginList.isEmpty()) {
                    String currenOrigin = request.getHeader("Origin");
                    if (allowOriginList.contains(currenOrigin)) {
                        response.setHeader("Access-Control-Allow-Origin", currenOrigin);
                    }
                }
            }
        }
        if (!StringUtils.isNullOrEmpty(allowMethods)) {
            response.setHeader("Access-Control-Allow-Methods", allowMethods);
        }
        if (!StringUtils.isNullOrEmpty(allowCredentials)) {
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        }
        if (!StringUtils.isNullOrEmpty(allowHeaders)) {
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }

        filterChain.doFilter(request, response);
    }


    @Override
    public void destroy() {
//        Filter.super.destroy();
    }
}
