package service;

import java.util.List;

import dao.UserDao;
import model.User;

public class UserService {
	private UserDao userDao;

	public UserService() {
		userDao = new UserDao();
	}

	public List<User> getAllUser() {
		return userDao.getAllUsers();
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	public void addUser(User user) {
		userDao.addUser(user);
	}

	public void deleteUser(int id) {
		userDao.deleteUserById(id);
	}

	public void editUser(User user) {
		userDao.editUser(user);

	}

	public int getTotalUsers() {
		return userDao.countAllUsers();
	}

	public List<User> getUsers(int start, int size) {
		return userDao.findUsers(start, size);
	}
	
	public boolean checkUserExist(String username) {
		return userDao.checkUserExist(username);
	}

	public String hashPassword(String password) {
		try {
			return userDao.hashPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}

	public User findByUsernameAndPassword(String username, String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}
}