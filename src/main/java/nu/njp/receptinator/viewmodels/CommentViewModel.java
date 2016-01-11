package nu.njp.receptinator.viewmodels;

/**
 * Created by Mattias on 2016-01-11.
 */
public class CommentViewModel {

    private String commentText;
    private int accountId;
    private int recipeId;

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}
