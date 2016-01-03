package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Recipe;
import org.slf4j.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Password recovery backing bean
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@Named("recipes")
@RequestScoped
public class RecipesBacking {

    @Inject
    @DefaultLogger
    private Logger logger;

    private List<Recipe> recipeList;

    public RecipesBacking() {
        setRecipeList(new ArrayList<>());
        getRecipeList().add(new Recipe("Some stew", "Stir, shake, finished!"));
    }


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
