package nu.njp.receptinator.core.util;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 * Helper class used to handle all resource responses in a single class.
 * Created by Daniel Ryhle.
 */
public class ResponseHelper<TEntity> {

    public final Response getOk(GenericEntity obj) { return Response.status(Response.Status.OK).entity(obj).build(); }
    public final Response getOk(TEntity obj) { return Response.status(Response.Status.OK).entity(obj).build(); }
    public final Response getFailed(String reason) { return badRequest(reason); }
    public final Response getNotFound() { return Response.status(Response.Status.NOT_FOUND).build(); }
    public final Response postOk(String message) { return  created(message); }
    public final Response postFailed(String reason) { return badRequest(reason); }
    public final Response created() { return Response.status(Response.Status.CREATED).build(); }
    public final Response created(String message) { return Response.status(Response.Status.CREATED).header("X-Reason", message).build(); }
    private final Response badRequest(String reason) { return Response.status(Response.Status.BAD_REQUEST).header("X-Reason", reason).build(); }

}
