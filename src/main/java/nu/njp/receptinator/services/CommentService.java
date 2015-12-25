package nu.njp.receptinator.services;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.entities.Comment;
import nu.njp.receptinator.interfaces.CommentServiceLocal;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * Created by Andreas och Mattias on 2015-12-21.
 */
public class CommentService implements CommentServiceLocal {

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Override
    public JsfMessage addComment(Comment comment) {
        try {
            em.persist(comment);
        } catch (Exception e) {
            return new JsfMessage("Error creating comment!", "Error in creating comment, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Comment created!", "Comment successfully created.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public JsfMessage removeComment(String commentText) {
        Comment selectedComment;
        try{
            selectedComment = (Comment) em.createNamedQuery("findComment").setParameter("commentText", commentText).getSingleResult();
        }catch (NoResultException e){
            return new JsfMessage("Error removing comment!", "Error in removing comment, please try again.", JsfMessage.MessageType.ERROR);
        }
        em.createNamedQuery("deleteCommentByCommentId").setParameter("commentId", selectedComment.getCommentId()).executeUpdate();
        return new JsfMessage("Comment deleted!", "Comment successfully deleted.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public JsfMessage updateComment(String currentCommentText, String newCommentText) {
        Comment selectedComment;
        try {
            selectedComment = (Comment) em.createNamedQuery("findComment").setParameter("commentText", currentCommentText).getSingleResult();
        } catch (NoResultException e) {
            return new JsfMessage("Error updating comment!", "Error in updating comment, please try again.", JsfMessage.MessageType.ERROR);
        }
        em.createNamedQuery("setCommentById").setParameter("commentText", newCommentText).setParameter("commentId", selectedComment.getCommentId()).executeUpdate();
        return new JsfMessage("Comment updated!", "Comment successfully updated.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public Collection<Comment> allComment() {
        return null;
    }
}
