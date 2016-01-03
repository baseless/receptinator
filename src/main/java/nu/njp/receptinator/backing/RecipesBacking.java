package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Category;
import nu.njp.receptinator.entities.Recipe;
import nu.njp.receptinator.interfaces.CategoryServiceLocal;
import nu.njp.receptinator.interfaces.RecipeServiceLocal;
import org.slf4j.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

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

    @Inject
    RecipeServiceLocal recipesService;

    @Inject
    CategoryServiceLocal categoryService;

    private Collection<Recipe> recipeList;
    private Collection<Category> categories;

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Collection<Recipe> getRecipeList() {
        recipeList = recipesService.allRecipes();
        return recipeList;
    }

    public void setRecipeList(Collection<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public Collection<Category> getCategories() {
        categories = categoryService.allCategories();
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }
}
