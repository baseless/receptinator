package nu.njp.receptinator.interfaces;
import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.entities.Recipe;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Mattias and Andreas on 2015-12-18.
 */

@Local
public interface RecipeServiceLocal /*extends Serializable */{

    Collection<Recipe> all();
    Recipe get(int recipeId);
    JsfMessage add(Recipe recipe);
    JsfMessage remove(int id);
    JsfMessage edit(Recipe recipe);

}
