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
            em.flush();
        } catch (Exception e) {
            return new JsfMessage("Error creating comment!", "Error in creating comment, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Comment created!", "Comment successfully created.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public JsfMessage updateComment(Comment comment) {
        try {
            em.merge(comment);
            em.flush();
        } catch (NoResultException e) {
            return new JsfMessage("Error updating comment!", "Error in updating comment, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Category updated!", "Category successfully updated.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public JsfMessage removeComment(int commentId) {
        try {
            em.remove(em.find(Comment.class, commentId));
            em.flush();
        } catch (NoResultException e) {
            return new JsfMessage("Error updating comment!", "Error in updating comment, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Category deleted!", "Category successfully deleted.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public Comment findComment(int commentId) {
        return em.find(Comment.class, commentId);
    }

    @Override
    public Collection<Comment> allComments() {
        Collection<Comment> result = null;
        try {
            result = em.createNamedQuery("getAllActiveComments", Comment.class).getResultList();
            em.flush();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return result;
    }

}
