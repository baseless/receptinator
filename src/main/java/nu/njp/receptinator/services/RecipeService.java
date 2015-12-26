package nu.njp.receptinator.services;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Comment;
import nu.njp.receptinator.entities.Recipe;
import nu.njp.receptinator.interfaces.RecipeServiceLocal;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * Created by Mattias and Andreas on 2015-12-18.
 */

@Stateless
public class RecipeService implements RecipeServiceLocal {

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Inject
    @DefaultLogger
    private Logger logger;

    @Override
    public Collection<Recipe> allRecipes() {
        Collection<Recipe> result = null;
        try {
            result = em.createNamedQuery("getAllRecipes", Recipe.class).getResultList();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return result;
    }

    @Override
    public Recipe findRecipe(int recipeId) {
        return em.find(Recipe.class, recipeId);
    }

    @Override
    public JsfMessage addRecipe(Recipe recipe) {
        try {
            em.persist(recipe);
        } catch (Exception e) {
            return new JsfMessage("Error creating recipe!", "Error in creating recipe, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Recipe created!", "Recipe successfully created.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public JsfMessage removeRecipe(int recipeId) {
        try {
            em.remove(em.find(Recipe.class, recipeId));
        } catch (NoResultException e) {
            return new JsfMessage("Error updating recipe!", "Error in updating recipe, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Comment deleted!", "Comment successfully deleted.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public JsfMessage updateRecipe(Recipe recipe) {
        try {
            em.merge(recipe);
        } catch (NoResultException e) {
            return new JsfMessage("Error updating recipe!", "Error in updating recipe, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Recipe updated!", "Recipe successfully updated.", JsfMessage.MessageType.SUCCESS);
    }
}




