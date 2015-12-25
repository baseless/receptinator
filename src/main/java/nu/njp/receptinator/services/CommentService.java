package nu.njp.receptinator.services;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Comment;
import nu.njp.receptinator.interfaces.CommentServiceLocal;
import org.slf4j.Logger;

import javax.inject.Inject;
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

    @Inject @DefaultLogger
    private Logger logger;

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
    public JsfMessage updateComment(Comment comment) {
        Comment selectedComment;
        try {
            selectedComment = (Comment) em.createNamedQuery("getCommentById").setParameter("commentId", comment.getCommentId()).getSingleResult();
        } catch (NoResultException e) {
            return new JsfMessage("Error updating comment!", "Error in updating comment, please try again.", JsfMessage.MessageType.ERROR);
        }
        em.createNamedQuery("setCommentById").setParameter("commentText", comment.getCommentText()).setParameter("commentId", selectedComment.getCommentId()).executeUpdate();
        return new JsfMessage("Category updated!", "Category successfully updated.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public JsfMessage removeComment(int commentId) {
        Comment selectedComment;
        try {
            selectedComment = (Comment) em.createNamedQuery("getCommentById").setParameter("commentId", commentId).getSingleResult();
        } catch (NoResultException e) {
            return new JsfMessage("Error updating comment!", "Error in updating comment, please try again.", JsfMessage.MessageType.ERROR);
        }
        em.createNamedQuery("deleteCommentByCommentId").setParameter("commentId", selectedComment.getCommentId()).executeUpdate();
        return new JsfMessage("Category deleted!", "Category successfully deleted.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public Collection<Comment> allComment(int commentId) {
        Collection<Comment> result = null;
        try {
            Collection<Comment> selectedCategories = em.createNamedQuery("getCommentById", Comment.class).setParameter("commentId", commentId).getResultList();
            result = selectedCategories;
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return result;
    }

}
