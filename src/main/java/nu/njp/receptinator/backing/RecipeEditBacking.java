package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.AuthenticationProvider;
import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.entities.BaseEntity;
import nu.njp.receptinator.entities.Category;
import nu.njp.receptinator.entities.Recipe;
import nu.njp.receptinator.interfaces.CategoryServiceLocal;
import nu.njp.receptinator.interfaces.RecipeServiceLocal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;

/**
 * AccountList backing bean
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@ManagedBean
@ViewScoped
public class RecipeEditBacking extends BackingBase {

    @Inject
    RecipeServiceLocal recipesService;

    @Inject
    CategoryServiceLocal categoryService;

    @Inject
    AuthenticationProvider authenticationProvider;



    private Recipe recipe;
    private Collection<Category> categories;
    private int categoryId;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String update() {
        recipe.setCategory(categoryService.findCategory(categoryId));
        JsfMessage result = recipesService.updateRecipe(recipe);
        setMessage(result);
        if(result.getMessageType().equals(JsfMessage.MessageType.SUCCESS)) {
            return "list";
        } else { return null; }
    }

    public String delete() {
        recipe.setStatus(BaseEntity.Status.INACTIVE);
        JsfMessage result = recipesService.updateRecipe(recipe);
        setMessage(result);
        if(result.getMessageType().equals(JsfMessage.MessageType.SUCCESS)) {
            return "list";
        } else { return null; }
    }

    public Collection<Category> getCategories() {
        if(categories == null)
            categories = categoryService.allCategories();
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
