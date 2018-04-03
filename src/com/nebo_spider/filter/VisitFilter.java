package com.nebo_spider.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author  nebo
 * @dec    拦截器为请求回复设置header属性 解决跨域问题
 */
public   class VisitFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println("==========");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig arg0) throws ServletException {

    }
}
