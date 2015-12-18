package nu.njp.receptinator.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Andreas och Mattias on 2015-12-18.
 */
@Entity
@Table(name = "images")
public class Image implements Serializable{

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageId")
    private int imageId;
    private String imageURL;

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
}
