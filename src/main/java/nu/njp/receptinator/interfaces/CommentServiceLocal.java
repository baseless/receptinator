package nu.njp.receptinator.interfaces;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.entities.Comment;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Andreas on 2015-12-21.
 */
public interface CommentServiceLocal extends Serializable {
    JsfMessage addComment(Comment comment);

    Collection<Comment> allComments();

    JsfMessage updateComment(Comment comment);

    JsfMessage removeComment(int commentId);

    Comment findComment(int commentId);

    Collection<Comment> allCommentsForRecipe(int recipeId);
}
