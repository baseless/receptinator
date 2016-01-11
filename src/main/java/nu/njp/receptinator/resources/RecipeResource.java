package nu.njp.receptinator.resources;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.util.ResponseHelper;
import nu.njp.receptinator.entities.Recipe;
import nu.njp.receptinator.interfaces.AccountServiceLocal;
import nu.njp.receptinator.interfaces.CategoryServiceLocal;
import nu.njp.receptinator.interfaces.RecipeServiceLocal;
import nu.njp.receptinator.viewmodels.RecipeViewModel;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by Mattias on 2016-01-11.
 */
@Path("recipes")
@Stateless
public class RecipeResource {

    @Inject
    RecipeServiceLocal recipeService;

    @Inject
    AccountServiceLocal accountService;

    @Inject
    CategoryServiceLocal categoyService;

    @Inject
    ResponseHelper<Recipe> response;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        Collection<Recipe> recipes = recipeService.allRecipes();
        if(recipes.isEmpty()) {
            return response.getFailed("Could not retrieve recipe list");
        }
        return response.getOk(new GenericEntity<Collection<Recipe>>(recipes) {});
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(RecipeViewModel recipeModel) {

        Recipe recipeEntity = new Recipe();
        recipeEntity.setRecipeName(recipeModel.getRecipeName());
        recipeEntity.setRecipeText(recipeModel.getRecipeText());
        recipeEntity.setAccount(accountService.findAccount(recipeModel.getAccountId()));
        recipeEntity.setCategory(categoyService.findCategory(recipeModel.getCategoryId()));
        recipeEntity.setCreated(recipeModel.getCreated());

        if((recipeService.addRecipe(recipeEntity)).getMessageType().equals(JsfMessage.MessageType.SUCCESS))
            return response.postOk("Recipe created.");
        else
            return response.postFailed("Failed to create recipe.");
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id")int id) {
        Recipe recipe = recipeService.findRecipe(id);
        if(recipe != null) {
            return response.getOk(recipe);
        }
        return response.getNotFound();
    }

}
