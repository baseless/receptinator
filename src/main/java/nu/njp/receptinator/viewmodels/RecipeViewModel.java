package nu.njp.receptinator.viewmodels;
import java.sql.Date;

/**
 * Created by Mattias on 2016-01-11.
 */
public class RecipeViewModel {


    private String recipeName;
    private String recipeText;
    private int accountId;
    private int categoryId;
    private Date created;

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeText() {
        return recipeText;
    }

    public void setRecipeText(String recipeText) {
        this.recipeText = recipeText;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
