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
@WebFilter("*")
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

        if(uri.startsWith("/receptinator/faces/")) {
            logger.warn("Tried to access a 'faces' url");
            ((HttpServletResponse) servletResponse).sendRedirect("/receptinator/notallowed.xhtml");
        }

        if(uri.startsWith("/receptinator/member")) {
            if(auth.isAuthenticated()) {
                allowed = true;
            }
        } else if(uri.startsWith("/receptinator/admin")) {
            if(auth.isAuthenticated() && auth.getAccount().getPermission().equals(Account.Permission.ADMINISTRATOR)) {
                allowed = true;
            }
        } else {
            allowed = true;
        }

        if(allowed) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            logger.warn("Invalid access attempt from ip address " + request.getRemoteAddr());
            ((HttpServletResponse) servletResponse).sendRedirect("/receptinator/notallowed.xhtml");
        }
    }

    @Override
    public void destroy() {

    }
}
