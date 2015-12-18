package nu.njp.receptinator.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Andreas och Mattias on 2015-12-18.
 */
@Entity
@Table(name = "recipes")
public class Recipe implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipeId")
    private int recipeId;
    private String recipeName;
    @Size(max=2000)
    private String recipeText;

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
}
