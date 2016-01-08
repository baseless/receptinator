package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.AuthenticationProvider;
import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Category;
import nu.njp.receptinator.entities.Recipe;
import nu.njp.receptinator.interfaces.AccountServiceLocal;
import nu.njp.receptinator.interfaces.CategoryServiceLocal;
import nu.njp.receptinator.interfaces.RecipeServiceLocal;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

/**
 * Created by Mattias on 2016-01-04.
 */

@Named("recipeAdd")
@RequestScoped
public class RecipeAddBacking extends BackingBase {

    @Inject
    @DefaultLogger
    private Logger logger;

    @Inject
    RecipeServiceLocal recipesService;

    @Inject
    CategoryServiceLocal categoryService;

    @Inject
    AuthenticationProvider authenticationProvider;

    private Recipe recipe;

    private int categoryId;

    private Collection<Category> categoryList;

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Collection<Category> getCategoryList() {
        if(categoryList == null)
            categoryList = categoryService.allCategories();
        return categoryList;
    }

    public void setCategoryList(Collection<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Recipe getRecipe() {
        if(recipe == null)
            recipe = new Recipe();
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String save() {
        recipe.setAccount(authenticationProvider.getAccount());
        recipe.setCategory(categoryService.findCategory(categoryId));
        recipe.setCreated(new java.sql.Date(new java.util.Date().getTime()));
        JsfMessage result = recipesService.addRecipe(recipe);

        if(result.getMessageType().equals(JsfMessage.MessageType.SUCCESS)) {
            return "list";
        } else { setMessage(result); return null; }
    }


}
