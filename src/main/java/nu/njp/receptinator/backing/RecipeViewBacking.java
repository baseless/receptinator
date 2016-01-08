package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.AuthenticationProvider;
import nu.njp.receptinator.entities.Recipe;
import nu.njp.receptinator.interfaces.RecipeServiceLocal;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
* AccountList backing bean
* @author Daniel Ryhle <daniel@ryhle.se>
*/
@ManagedBean
@RequestScoped
public class RecipeViewBacking extends BackingBase {

    @Inject
    AuthenticationProvider authenticationProvider;

    @Inject
    RecipeServiceLocal recipesService;

    private Recipe recipe;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public boolean CurrentUserisAllowedToEdit() {
        return authenticationProvider.getAccount().getUserName().equalsIgnoreCase(recipe.getAccount().getUserName());
    }
}
