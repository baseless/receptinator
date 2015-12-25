package nu.njp.receptinator.interfaces;
import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.entities.Recipe;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Mattias and Andreas on 2015-12-18.
 */

public interface RecipeServiceLocal /*extends Serializable */{

    Collection<Recipe> allRecipe(int recipeId);
    Recipe getRecipe(int recipeId);
    JsfMessage addRecipe(Recipe recipe);
    JsfMessage removeRecipe(int id);
    JsfMessage updateRecipe(Recipe recipe);

}
