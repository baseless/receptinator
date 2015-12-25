package nu.njp.receptinator.interfaces;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.entities.Comment;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Asiohu on 2015-12-21.
 */
public interface CommentServiceLocal extends Serializable {
    JsfMessage addComment(Comment comment);

    JsfMessage removeComment(String commentText);

    JsfMessage updateComment(String currentCommentText, String newCommentText);

    Collection<Comment> allComment(int commentId);
}
