package nu.njp.receptinator.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Andreas och Mattias on 2015-12-18.
 */
@Entity
@Table(name = "recipes")
@NamedQueries({
        @NamedQuery(name="getAllRecipes", query="SELECT r FROM Recipe r"),
})
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipeId")
    private int recipeId;
    private String recipeName;
    @Size(max=2000)
    private String recipeText;

    @ManyToOne(optional = false)
    @JoinColumn(name="categoryId")
    private Category category;

    @ManyToOne(optional = false)
    @JoinColumn(name="accountId")
    private Account account;

    private Date created;

    public Recipe() {
    }

    public Recipe(String recipeName, String recipeText){
        this.setRecipeName(recipeName);
        this.setRecipeText(recipeText);
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
