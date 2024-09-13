package socket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatSession {
	private Map<String, String> users = new ConcurrentHashMap<>();

	public Map<String, String> getUsers() {
		return users;
	}

	public void setUsers(Map<String, String> users) {
		this.users = users;
	}
}
