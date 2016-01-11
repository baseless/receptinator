package nu.njp.receptinator.resources;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.util.ResponseHelper;
import nu.njp.receptinator.entities.Comment;
import nu.njp.receptinator.interfaces.AccountServiceLocal;
import nu.njp.receptinator.interfaces.CommentServiceLocal;
import nu.njp.receptinator.interfaces.RecipeServiceLocal;
import nu.njp.receptinator.viewmodels.CommentViewModel;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by Mattias on 2016-01-11.
 */

@Path("comments")
@Stateless
public class CommentResource {
    @Inject
    CommentServiceLocal commentService;

    @Inject
    AccountServiceLocal accountService;

    @Inject
    RecipeServiceLocal recipeService;

    @Inject
    ResponseHelper<Comment> response;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        Collection<Comment> comments = commentService.allComments();
        if(comments.isEmpty()) {
            return response.getFailed("Could not retrieve comment list");
        }
        return response.getOk(new GenericEntity<Collection<Comment>>(comments) {});
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(CommentViewModel commentModel) {

        Comment commentEntity = new Comment();
        commentEntity.setCommentText(commentModel.getCommentText());
        commentEntity.setAccount(accountService.findAccount(commentModel.getAccountId()));
        commentEntity.setRecipe(recipeService.findRecipe(commentModel.getRecipeId()));

        if((commentService.addComment(commentEntity)).getMessageType().equals(JsfMessage.MessageType.SUCCESS))
            return response.postOk("Comment created.");
        else
            return response.postFailed("Failed to create comment.");
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id")int id) {
        Comment comment = commentService.findComment(id);
        if(comment != null) {
            return response.getOk(comment);
        }
        return response.getNotFound();
    }
}
