package dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import utils.JDBCConnection;

public class UserDao {

	public User findByUsernameAndPassword(String username, String password) {
		User user = null;
		try {
			Connection connection = JDBCConnection.getJDBCConnection();
			String sql = "SELECT * FROM user WHERE USERNAME = ? AND PASSWORD = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, hashPassword(password));
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("NAME"));
				user.setPhone(rs.getString("PHONE"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setAbout(rs.getString("ABOUT"));
				user.setFavorite(rs.getString("FAVORITE"));
				user.setRole(rs.getString("ROLE"));
				user.setAvatar(rs.getString("AVATAR"));
			}

			rs.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public String hashPassword(String password) throws Exception {
		if (password == null) {
			throw new IllegalArgumentException("Password cannot be null");
		}
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hash = md.digest(password.getBytes("UTF-8"));
		StringBuilder hexString = new StringBuilder();

		for (byte b : hash) {
			hexString.append(String.format("%02x", b));
		}
		return hexString.toString();
	}

	public List<User> getAllUsers() {
		String sql = "SELECT * FROM USER";
		List<User> users = new ArrayList<>();
		try {
			Connection connection = JDBCConnection.getJDBCConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("NAME"));
				user.setPhone(rs.getString("PHONE"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setAbout(rs.getString("ABOUT"));
				user.setFavorite(rs.getString("FAVORITE"));
				user.setRole(rs.getString("ROLE"));
				user.setAvatar(rs.getString("AVATAR"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public User getUserById(int id) {
		String sql = "SELECT * FROM USER WHERE ID = ?";

		try {
			Connection connection = JDBCConnection.getJDBCConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("NAME"));
				user.setPhone(rs.getString("PHONE"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setAbout(rs.getString("ABOUT"));
				user.setFavorite(rs.getString("FAVORITE"));
				user.setRole(rs.getString("ROLE"));
				user.setAvatar(rs.getString("AVATAR"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addUser(User user) {
		String sql = "INSERT INTO USER VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			Connection connection = JDBCConnection.getJDBCConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getPhone());
			preparedStatement.setString(4, user.getUsername());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setString(7, user.getAbout());
			preparedStatement.setString(8, user.getFavorite());
			preparedStatement.setString(9, user.getAvatar());

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("User added successfully.");
			} else {
				System.out.println("User could not be added.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User deleteUserById(int id) {
		String sql = "DELETE FROM USER WHERE ID = ?";

		try {
			Connection connection = JDBCConnection.getJDBCConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("User deleted successfully.");
			} else {
				System.out.println("User could not be deleted.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void editUser(User user) {
		String sql = "UPDATE USER SET NAME = ?, PHONE = ?, USERNAME = ?, PASSWORD = ?,ROLE = ?, ABOUT = ?, FAVORITE = ?,  AVATAR = ? WHERE ID = ?";

		try {
			Connection connection = JDBCConnection.getJDBCConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getPhone());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getRole());
			preparedStatement.setString(6, user.getAbout());
			preparedStatement.setString(7, user.getFavorite());
			preparedStatement.setString(8, user.getAvatar());
			preparedStatement.setInt(9, user.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int countAllUsers() {
		String sql = "SELECT COUNT(*) FROM user";
		int count = 0;
		try (Connection connection = JDBCConnection.getJDBCConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery()) {

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<User> findUsers(int start, int size) {
		List<User> list = new ArrayList<User>();
		try {
			Connection con = JDBCConnection.getJDBCConnection();
			PreparedStatement ps = con.prepareStatement("select * from user limit " + (start) + "," + size);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("NAME"));
				user.setPhone(rs.getString("PHONE"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setAbout(rs.getString("ABOUT"));
				user.setFavorite(rs.getString("FAVORITE"));
				user.setRole(rs.getString("ROLE"));
				user.setAvatar(rs.getString("AVATAR"));
				list.add(user);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public boolean checkUserExist(String username) {
	    boolean exists = false;
	    try {
	        Connection connection = JDBCConnection.getJDBCConnection();
	        String sql = "SELECT * FROM user WHERE USERNAME = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, username);
	        ResultSet rs = statement.executeQuery();

	        if (rs.next()) {
	            exists = true; 
	        }

	        rs.close();
	        statement.close();
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return exists;
	}
}
