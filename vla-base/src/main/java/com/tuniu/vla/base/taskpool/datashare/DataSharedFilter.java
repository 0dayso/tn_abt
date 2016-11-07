package com.tuniu.vla.base.taskpool.datashare;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 共享数据过滤器，在请求初始化时，共享数据容器放入ThreadLocal中。
 * 结束后从ThreadLocal删除
 *
 * Created by chengyao on 2016/3/5.
 */
public class DataSharedFilter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        DataSharedSupport.putData(new ConcurrentHashMap<String, Object>());
        try {
            chain.doFilter(request, response);
        } finally {
            DataSharedSupport.removeData();
        }
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
