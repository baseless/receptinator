package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Comment;
import nu.njp.receptinator.entities.Recipe;
import nu.njp.receptinator.interfaces.CommentServiceLocal;
import nu.njp.receptinator.interfaces.RecipeServiceLocal;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

/**
 * Created by Mattias on 2016-01-18.
 */

@Named("index")
@RequestScoped
public class IndexBacking {

    private Collection<Comment> comments;

    @Inject
    CommentServiceLocal commentService;

    @Inject
    RecipeServiceLocal recipeService;

    @Inject @DefaultLogger
    Logger logger;

    private Recipe recipe;

    public Recipe getRecipe() {
        if(recipe == null) {
            recipe = recipeService.findRecipe(1);
        }
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Collection<Comment> getComments() {
        if(comments == null) {
            comments = commentService.allCommentsForRecipe(recipe.getRecipeId());
        }
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

}
