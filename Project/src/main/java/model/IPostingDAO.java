package model;

import java.util.List;

import exceptions.AutoGeneratedIDException;

public interface IPostingDAO {

	int writeAPost(String publisherEmail, String recepientEmail, String post) throws AutoGeneratedIDException;

	List<Post> getAllPosts(String email, boolean publisherOnly);

}