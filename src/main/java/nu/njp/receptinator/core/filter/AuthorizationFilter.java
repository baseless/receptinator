package nu.njp.receptinator.core.filter;

import nu.njp.receptinator.core.AuthenticationProvider;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Account;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.*;

/**
 * Created by Mattias and Daniel on 2016-01-16.
 */
@Provider
@PreMatching
public class AuthorizationFilter implements ContainerRequestFilter {

    @Inject @DefaultLogger
    private Logger logger;

    @Inject
    AuthenticationProvider authenticationProvider;

    @Context
    private HttpServletRequest servletRequest;

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        MultivaluedMap<String, String> headers = context.getHeaders();
        UriInfo uri = context.getUriInfo();
        Map.Entry<String, String> auth = getUserNameAndPassword(headers);
        logger.debug("clientAddress:'" + servletRequest.getRemoteAddr() + "', urlRequested:' " + uri.getAbsolutePath() + " [" + uri.getPath() + "]', username + '" + auth.getKey() + "', password '" + auth.getValue() + "'");
        if(!authenticateUser(uri, auth.getKey(), auth.getValue())) {
            context.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access the resource.").build());
        }
    }

    private boolean authenticateUser(UriInfo uri, String username, String password) {
        String path = uri.getPath();
        logger.debug("STARTED, CHECKING " + path);
        //Check user against database.
        if(authenticationProvider.authenticate(username, password)){
            logger.debug("clientAddress:'" + servletRequest.getRemoteAddr() + "', urlRequested:' " + uri.getAbsolutePath() + " [" + uri.getPath() + "]', username + '" + username + "', password '" + password + "', is OK.");
            //Also check permission.
            Account.Permission permission;
            permission = authenticationProvider.getAccount().getPermission();
            //Check if path is OK for user permission.
            return authenticatePath(path, permission);
        }
        logger.info(uri.getPath());
        return false;
    }

    private boolean authenticatePath(String path, Account.Permission permission){
        //If permission is set to USER, anything but accounts can be accessed.
        if(permission.equals(Account.Permission.USER)){
            if(path.equals("/accounts")){
                logger.error("Path is not allowed for user. Check user permission");
                return false;
            }
        }
        return true;
    }

    private Map.Entry<String,String> getUserNameAndPassword(MultivaluedMap<String, String> headers) {

        List<String> authHeaders = headers.get("authorization");
        if(authHeaders != null && authHeaders.size() > 0) {
            String authParam = authHeaders.get(0);
            final String[] authEncoded = authParam.split(" ");
            if(authEncoded.length == 2) {
                try {
                    String authDecoded = new String(Base64.getDecoder().decode(authEncoded[1]));
                    final StringTokenizer token = new StringTokenizer(authDecoded, ":");
                    final String username = token.nextToken();
                    final String password = token.nextToken();
                    return new AbstractMap.SimpleEntry<>(username, password);
                } catch (Exception e) {
                    logger.error("Error decoding username and password ( " + e.getMessage() + ")");
                }
            }
        }
        return new AbstractMap.SimpleEntry<>("", "");
    }
}
