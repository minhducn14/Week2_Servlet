package socket;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import model.Message;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/chatEndpoint/{username}", encoders = { MessageEncoder.class }, decoders = {
		MessageDecoder.class })
public class ChatServerEndpoint {
	ChatSession chatSession = new ChatSession();
	private static Session session;
	private static Set<Session> chatters = new CopyOnWriteArraySet<>();

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String userName) throws IOException, EncodeException {
		this.session = session;
		Map<String, String> chatusers = chatSession.getUsers();
		chatusers.put(session.getId(), userName);
		chatSession.setUsers(chatusers);
		chatters.add(session);
		System.out.println(chatusers);
		Message message = new Message();
		message.setUserName(userName);
		message.setMessage("Welcome " + userName);
		broadcast(message);
	}

	@OnMessage
	public void onMessage(Session session, Message message) throws IOException, EncodeException {
		Map<String, String> chatusers = chatSession.getUsers();
		message.setUserName(chatusers.get(session.getId()));
		broadcast(message);
	}

	@OnClose
	public void onClose(Session session) {
		chatters.remove(session);
		Message message = new Message();
		Map<String, String> chatusers = chatSession.getUsers();
		String chatuser = chatusers.get(session.getId());
		message.setUserName(chatuser);
		chatusers.remove(chatuser);
		message.setMessage("Disconnected from server");
		try {
			broadcast(message);
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		}
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		throwable.printStackTrace();
		System.out.println("There has been an error with session " + session.getId());
	}

	private static void broadcast(Message message) throws IOException, EncodeException {
		chatters.forEach(session -> {
			synchronized (session) {
				try {
					session.getBasicRemote().sendObject(message);
				} catch (IOException | EncodeException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
