package nu.njp.receptinator.core.filter;

import nu.njp.receptinator.core.AuthenticationProvider;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import org.slf4j.Logger;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet authentication filter
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Inject
    AuthenticationProvider auth;

    @Inject @DefaultLogger
    private Logger logger;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        logger.info("request detected from auth filter");

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
