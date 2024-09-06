package bai4.servlet;

import java.util.ArrayList;
import java.util.List;

import bai4.entities.User;

public class UserService {

	public List<User> initUser(int n) {
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < n; i++) {
			User user = new User("firstName" + i, "lastName" + i, i);
			users.add(user);
		}
		System.out.println(users);
		return users;

	}

	public String sayHello() {
		return "Hello";
	}
}
