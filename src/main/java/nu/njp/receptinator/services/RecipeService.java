package nu.njp.receptinator.services;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.entities.Recipe;
import nu.njp.receptinator.interfaces.RecipeServiceLocal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * Created by Mattias and Andreas on 2015-12-18.
 */

@Stateless
public class RecipeService implements RecipeServiceLocal {

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Override
    public Collection<Recipe> allRecipe() {
        return null;
    }

    @Override
    public Recipe getRecipe(int recipeId) {
        return null;
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
    public JsfMessage removeRecipe(int id) {
        return null;
    }

    @Override
    public JsfMessage editRecipe(Recipe recipe) {
        return null;
    }



}
