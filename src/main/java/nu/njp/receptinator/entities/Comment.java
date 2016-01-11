package nu.njp.receptinator.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * Created by Andreas and Mattias on 2015-12-18.
 * Added named query //Daniel
 */
@Entity
@Table(name = "comments")
@NamedQueries({
        @NamedQuery(name= "getAllActiveComments", query="SELECT c FROM Comment c WHERE c.status = 'ACTIVE'"),
        @NamedQuery(name= "getAllActiveCommentsForRecipe", query="SELECT c FROM Comment c WHERE c.status = 'ACTIVE' AND c.recipe.recipeId = :recipeId")
})
public class Comment extends BaseEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private int commentId;
    @Size(max=500)
    @NotNull
    private String commentText;

    @ManyToOne(optional = false)
    @JoinColumn(name = "accountId")
    private Account account;

    @ManyToOne (optional = false)
    @JoinColumn(name = "recipeId")
    private Recipe recipe;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    public Comment() {
        status = Status.ACTIVE;
    }

    public Comment(String commentText) {
        this.setCommentText(commentText);
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @XmlTransient
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public BaseEntity.Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
