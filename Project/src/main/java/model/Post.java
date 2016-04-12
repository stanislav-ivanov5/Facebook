package model;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Post implements Serializable {
		
	private static final long serialVersionUID = -4746346808422319712L;

	private BlockingQueue<Comment> allComments = new LinkedBlockingQueue<>();

	private String firstName;
	private String lastName;
	private String text;
	private String date;
	private int countLikes;
	private int postId;
	private String publisherEmail;
	private String recepientEmail;

	public Post(String firstName, String LastName, String post, String date,
			int id, int countLikes, String publisherEmail, String recepientEmail) {
		setFirstName(firstName);
		setLastName(LastName);
		setText(post);
		setDate(date);
		setPostId(id);
		setCountLikes(countLikes);
		this.publisherEmail = publisherEmail;
		this.recepientEmail = recepientEmail;
	}
	
	public BlockingQueue<Comment> getAllComments() {
		return allComments;
	}

	public boolean addAllComment(List<Comment> comments) {
		if(comments != null) {
			try {
				for(Comment c : comments) {
					allComments.put(c);					
				}
				
				return true;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	// Getters and setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		if (name != null)
			this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String name) {
		if (name != null)
			this.lastName = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String post) {
		if (post != null)
			this.text = post;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		if (date != null) {
			this.date = date;
		}
	}

	public int getCountLikes() {
		return countLikes;
	}

	public void setCountLikes(int countLikes) {
		if (countLikes >= 0)
			this.countLikes = countLikes;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postID) {
		if (postID > 0)
			this.postId = postID;
	}

	public String getPublisherEmail() {
		return publisherEmail;
	}

	public String getRecepientEmail() {
		return recepientEmail;
	}
	@Override
	public String toString() {
		return "Post [firstName=" + firstName + ", lastName=" + lastName + ", text=" + text + ", date=" + date
				+ ", countLikes=" + countLikes + ", postId=" + postId + "]";
	}

}
