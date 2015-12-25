package nu.njp.receptinator.services;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.entities.Image;
import nu.njp.receptinator.interfaces.ImageServiceLocal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Created by Andreas och Mattias on 2015-12-21.
 */
@Stateless
public class ImageService implements ImageServiceLocal{


    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Override
    public JsfMessage addImage(Image image) {
        try {
            em.persist(image);
        } catch (Exception e) {
            return new JsfMessage("Error creating Image!", "Error in creating image, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Image created!", "Image successfully created.", JsfMessage.MessageType.SUCCESS);
    }


    @Override
    public JsfMessage removeImage(String imageUrl) {
        Image selectedImage;
        try{
            selectedImage = (Image) em.createNamedQuery("findIdByImageURL").setParameter("imageURL", imageUrl).getSingleResult();
        }catch (NoResultException e){
            return new JsfMessage("Error removing image!", "Error in removing image, please try again.", JsfMessage.MessageType.ERROR);
        }
        em.createNamedQuery("deleteImageByImageURL").setParameter("imageId", selectedImage.getImageId()).executeUpdate();
        return new JsfMessage("Image deleted!", "Image successfully deleted.", JsfMessage.MessageType.SUCCESS);
    }


    @Override
    public JsfMessage updateImage(String currentImageURL, String newImageURL) {
        Image selectedImage;
        try {
            selectedImage = (Image) em.createNamedQuery("findIdByImageURL").setParameter("imageURL", currentImageURL).getSingleResult();
        } catch (NoResultException e) {
            return new JsfMessage("Error updating image!", "Error in updating image, please try again.", JsfMessage.MessageType.ERROR);
        }
        em.createNamedQuery("setNewImageById").setParameter("imageURL", newImageURL).setParameter("imageId", selectedImage.getImageId()).executeUpdate();
        return new JsfMessage("Category updated!", "Category successfully updated.", JsfMessage.MessageType.SUCCESS);
    }
}
