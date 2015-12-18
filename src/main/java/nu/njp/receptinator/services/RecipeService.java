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
    public Collection<Recipe> all() {
        return null;
    }

    @Override
    public Recipe get(int recipeId) {
        return null;
    }

    @Override
    public JsfMessage add(Recipe recipe) {
        return new JsfMessage("Recipe created!", "Recipe successfully created.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public JsfMessage remove(int id) {
        return new JsfMessage("Recipe removed!", "Recipe successfully removed.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public JsfMessage edit(Recipe recipe) {
        return null;
    }

}
