package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

public class PictureDAO implements IPictureDAO{
	@Override
	public void uploadPicture(String pictureName, String email) {
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			String query= "INSERT INTO user_pictures(picture_id,picture_name,person_email) VALUES (null,?,?)";
			PreparedStatement stm= con.prepareStatement(query);
			stm.setString(1, pictureName);
			stm.setString(2, email);
			System.out.println("Upload picture name and email to db "+ pictureName +" "+email);
			stm.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public List<String> getAllPictures(String email){
		List<String>list= new ArrayList<String>();
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			String query = "SELECT picture_name FROM user_pictures WHERE person_email=?";
			PreparedStatement stm= con.prepareStatement(query);
			System.out.println("email of the user "+ email);
			stm.setString(1, email);
			ResultSet res= stm.executeQuery();
			
			while(res.next()){
				list.add(res.getString("picture_name"));
			}
			System.out.println(list.toString());
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
