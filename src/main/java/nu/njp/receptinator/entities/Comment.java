package nu.njp.receptinator.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Andreas and Mattias on 2015-12-18.
 */
@Entity
@Table(name = "comments")
public class Comment implements Serializable{
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private int commentId;
    @Size(max=500)
    private String commentText;

    @ManyToOne(optional = false)
    @JoinColumn(name = "accountId")
    private Account account;

    @ManyToOne (optional = false)
    @JoinColumn(name = "recipeId")
    private Recipe recipe;

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
}
