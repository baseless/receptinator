package nu.njp.receptinator.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Andreas och Mattias on 2015-12-18.
 */
@Entity
@Table(name = "recipes")
@NamedQueries({
        @NamedQuery(name= "getAllActiveRecipes", query="SELECT r FROM Recipe r WHERE r.status = 'ACTIVE'"),
})
public class Recipe extends BaseEntity implements Serializable {

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

    private String pictureUrl;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    public Recipe() {
        status = Status.ACTIVE;
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

    @XmlTransient
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
