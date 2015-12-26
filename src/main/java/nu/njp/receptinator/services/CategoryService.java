package nu.njp.receptinator.services;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Category;
import nu.njp.receptinator.interfaces.CategoryServiceLocal;
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
public class CategoryService implements CategoryServiceLocal {

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Inject @DefaultLogger
    private Logger logger;

    @Override
    public JsfMessage addCategory(Category category) {
        try {
            em.persist(category);
        } catch (Exception e) {
            return new JsfMessage("Error creating category!", "Error in creating category, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Category created!", "Category successfully created.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public JsfMessage updateCategory(Category category) {
        try {
            em.merge(category);
        } catch (NoResultException e) {
            return new JsfMessage("Error updating category!", "Error in updating category, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Category updated!", "Category successfully updated.", JsfMessage.MessageType.SUCCESS);
    }

    public JsfMessage removeCategory(int categoryId) {
        try {
            em.remove(em.find(Category.class, categoryId));
        } catch (NoResultException e) {
            return new JsfMessage("Error removing category!", "Error in removing category, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Category deleted!", "Category successfully deleted.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public Category findCategory(int categoryId) {
        return em.find(Category.class, categoryId);
    }

    @Override
    public Collection<Category> allCategories() {
        Collection<Category> result = null;
        try {
            result = em.createNamedQuery("getAllCategories", Category.class).getResultList();
        }catch(Exception e){
                logger.warn(e.getMessage());
        }
            return result;
        }
}


