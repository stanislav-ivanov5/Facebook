package model;

import java.sql.Connection;
import java.sql.SQLException;

import database.connection.DatabaseConnection;

public class AbstractDAO {

	private Connection con;

	public AbstractDAO() {
		try {
			con = DatabaseConnection.getInstance().getConnection();

		} catch (ClassNotFoundException e) {
			// TODO error page
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
