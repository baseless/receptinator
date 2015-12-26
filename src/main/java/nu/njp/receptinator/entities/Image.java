package nu.njp.receptinator.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Andreas och Mattias on 2015-12-18.
 */
@Entity
@Table(name = "images")
@NamedQueries({
        @NamedQuery(name="deleteImageByImageId", query="DELETE FROM Image i WHERE i.imageId = :imageId"),
        @NamedQuery(name="setNewImageById", query="UPDATE Image i SET i.imageURL = :imageURL WHERE i.imageId = :imageId"),
        @NamedQuery(name="getImageByImageId", query="SELECT i FROM Image i WHERE i.imageId = :imageId")

})
public class Image implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageId")
    private int imageId;
    private String imageURL;

    @ManyToOne(optional = false)
    @JoinColumn(name = "recipeId")
    private Recipe recipe;

    public Image() {
    }

    public Image(String imageURL) {
        this.setImageURL(imageURL);
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
