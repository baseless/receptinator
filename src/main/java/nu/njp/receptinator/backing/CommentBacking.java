package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.AuthenticationProvider;
import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.BaseEntity;
import nu.njp.receptinator.entities.Comment;
import nu.njp.receptinator.entities.Recipe;
import nu.njp.receptinator.interfaces.CommentServiceLocal;
import org.slf4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;

/**
 * Comment backing bean
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@ManagedBean
@ViewScoped
public class CommentBacking extends BackingBase {

    @Inject
    @DefaultLogger
    private Logger logger;

    @Inject
    CommentServiceLocal commentService;

    @Inject
    AuthenticationProvider authenticationProvider;

    private Recipe recipe;
    private Comment comment = new Comment();

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String insert() {
        comment.setAccount(authenticationProvider.getAccount());
        comment.setRecipe(recipe);
        comment.setCreated(new java.sql.Date(new java.util.Date().getTime()));
        JsfMessage result = commentService.addComment(comment);
        if(result.getMessageType().equals(JsfMessage.MessageType.SUCCESS)) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../recipes/view.xhtml?id=" + recipe.getRecipeId());
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        setMessage(result);
        return null;
    }

    public String update() {
        JsfMessage result = commentService.updateComment(comment);
        if(result.getMessageType().equals(JsfMessage.MessageType.SUCCESS)) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../recipes/view.xhtml?id=" + recipe.getRecipeId());
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        setMessage(result);
        return null;
    }

    public String delete() {
        comment.setStatus(BaseEntity.Status.INACTIVE);
        JsfMessage result = commentService.updateComment(comment);
        if(result.getMessageType().equals(JsfMessage.MessageType.SUCCESS)) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../recipes/view.xhtml?id=" + recipe.getRecipeId());
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        setMessage(result);
        return null;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
