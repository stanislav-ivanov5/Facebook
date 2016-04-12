package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

public class CommentDAO implements ICommentDAO {
	
	public void putCommentOnPost(int postId, String email, String comment) {
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			String query = "INSERT INTO comments (post_id, publisher_email, comment) VALUES(?,?,?);";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, postId);
			stm.setString(2, email);
			stm.setString(3, comment);

			stm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
