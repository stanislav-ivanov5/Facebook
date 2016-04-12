package model;

public interface ICommentDAO {
	public void putCommentOnPost(int postId, String email, String comment);
}
