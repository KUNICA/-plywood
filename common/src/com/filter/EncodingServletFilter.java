package com.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by user on 05.09.2016.
 */
public class EncodingServletFilter implements Filter {

    private static final String APPL_ENCODING = "encoding";

    private String encoding;

    public void init(FilterConfig filterConfig) throws ServletException {
        encoding =
                filterConfig.getInitParameter(APPL_ENCODING);
    }

    public void doFilter(
            ServletRequest req,
            ServletResponse res,
            FilterChain filterChain
    ) throws IOException, ServletException {
        req.setCharacterEncoding(encoding);
        filterChain.doFilter(req, res);
    }

    public void destroy() {
    }
}
