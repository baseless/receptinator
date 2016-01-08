package nu.njp.receptinator.services;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Image;
import nu.njp.receptinator.interfaces.ImageServiceLocal;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * Created by Andreas och Mattias on 2015-12-21.
 */
@Stateless
public class ImageService implements ImageServiceLocal{

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Inject
    @DefaultLogger
    private Logger logger;

    @Override
    public JsfMessage addImage(Image image) {
        try {
            em.persist(image);
            em.flush();
        } catch (Exception e) {
            return new JsfMessage("Error creating Image!", "Error in creating image, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Image created!", "Image successfully created.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public JsfMessage updateImage(Image image) {
        try {
            em.merge(image);
            em.flush();
        } catch (NoResultException e) {
            return new JsfMessage("Error updating image!", "Error in updating image, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Category updated!", "Category successfully updated.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public JsfMessage removeImage(int imageId) {
        try {
            em.remove(em.find(Image.class, imageId));
            em.flush();
        } catch (NoResultException e) {
            return new JsfMessage("Error updating image!", "Error in updating image, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Image deleted!", "Image successfully deleted.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public Collection<Image> allImages() {
        Collection<Image> result = null;
        try {
            result = em.createNamedQuery("getAllActiveImages", Image.class).getResultList();
            em.flush();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return result;
    }

    @Override
    public Image findImage(int imageId) {
        return em.find(Image.class, imageId);
    }
}
