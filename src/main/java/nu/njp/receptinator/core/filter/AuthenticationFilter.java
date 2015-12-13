package nu.njp.receptinator.core.filter;

import nu.njp.receptinator.core.AuthenticationProvider;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by base on 2015-12-13.
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Inject
    AuthenticationProvider auth;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
