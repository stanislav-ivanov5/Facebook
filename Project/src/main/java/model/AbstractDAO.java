package model;

import java.sql.Connection;
import java.sql.SQLException;

import database.DatabaseConnection;

public class AbstractDAO {

	private Connection con;

	public AbstractDAO() {
		try {
			con = DatabaseConnection.getInstance().getConnection();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return con;
	}

	public static void closeCon(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
