package com.semanticsquare.thrillio.manager;

import java.util.List;

import com.semanticsquare.thrillio.constants.Gender;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.dao.UserDao;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.util.StringUtil;

public class UserManager {
	private static UserManager instance = new UserManager();
	private static UserDao dao = new UserDao();

	private UserManager() {}

	public static UserManager getInstance() {
		return instance;
	}

	public User createUser(long id, String email, String password, String firstName, String lastName, Gender gender, 
			UserType userType) {
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserType(userType);
		user.setGender(gender);
		
		return user;
	}
	
	public List<User> getUsers() {
		return dao.getUsers();
	}

	public User getUser(long userId) {
		return dao.getUsers(userId);
	}

	public long authenticate(String email, String password) {
		return dao.authenticate(email, StringUtil.encodePassword(password));
	}
}
