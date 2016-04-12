package model;

import java.io.Serializable;

public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4235622082866310536L;
	private int matchingPostId;
	private String text;
	private String email;
	private int commentId;
	private String firstName;
	
	public Comment(int postId, String text, String email, int commentId, String firstName) {
		
		this.matchingPostId = postId;
		this.text = text;
		this.email = email;
		this.commentId = commentId;
		this.firstName = firstName;
	}
	

	public int getMatchingPostId() {
		return matchingPostId;
	}

	public void setMatchingPostId(int postId) {
		if(postId > 0) {
			this.matchingPostId = postId;
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if(text != null) {
			this.text = text;
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email != null) {
			this.email = email;
		}
	}
	public int getCommentId() {
		return commentId;
	}
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		return "Comment [matchingPostId=" + matchingPostId + ", text=" + text + ", email=" + email + "]";
	}
	
	

}
