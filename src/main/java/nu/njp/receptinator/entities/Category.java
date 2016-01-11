package nu.njp.receptinator.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Andreas och Mattias on 2015-12-18.
 */
@Entity
@Table(name = "categories")
@NamedQueries({
        @NamedQuery(name= "getAllActiveCategories", query = "SELECT c FROM Category c WHERE c.status = 'ACTIVE'"),
})
public class Category extends BaseEntity implements Serializable {

    //ta bort denna kommenteren.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private int categoryId;

    @NotNull
    @Size(min = 3, max = 50)
    private String categoryName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    public Category() {
        status = Status.ACTIVE;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String toString(){
        return categoryName;
    }
}
