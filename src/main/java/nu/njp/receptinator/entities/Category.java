package nu.njp.receptinator.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Andreas och Mattias on 2015-12-18.
 */
@Entity
@Table(name = "categories")
public class Category implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private int categoryId;
    private String categoryName;

    public Category(String categoryName) {
       this.setCategoryName(categoryName);
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}