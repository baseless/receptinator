package nu.njp.receptinator.core.filter;

import nu.njp.receptinator.core.AuthenticationProvider;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Account;
import org.slf4j.Logger;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;

/**
 * Servlet authentication filter
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@WebFilter("/faces/*")
public class AuthenticationFilter implements Filter {

    //todo: path
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
        String uri = request.getRequestURI().toLowerCase();

        boolean allowed = false;

        logger.info("Request for url '" + uri + "' from address " + request.getRemoteAddr());

        if(uri.startsWith("/receptinator/faces/member")) {
            if(auth.isAuthenticated()) {
                allowed = true;
            }
        } else if(uri.startsWith("/receptinator/faces/admin")) {
            if(auth.isAuthenticated() && auth.getAccount().getPermission().equals(Account.Permission.ADMINISTRATOR)) {
                allowed = true;
            }
        } else {
            allowed = true;
        }

        if(allowed) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/receptinator/faces/notallowed.xhtml");
        }
    }

    @Override
    public void destroy() {

    }
}
