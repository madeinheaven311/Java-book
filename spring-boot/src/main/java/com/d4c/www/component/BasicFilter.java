package com.d4c.www.component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class BasicFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) {
        // 初始化逻辑
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                         FilterChain chain) throws IOException, ServletException {
        // 1. 前置处理
        System.out.println("请求开始处理");
        long startTime = System.currentTimeMillis();

        // 2. 放行请求
        chain.doFilter(request, response);

        // 3. 后置处理
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("请求处理完成，耗时：" + costTime + "ms");
    }

    @Override
    public void destroy() {
        // 销毁逻辑
    }
}
