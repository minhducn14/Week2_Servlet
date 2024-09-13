package socket;

import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;
import model.Message;

import com.google.gson.Gson;

public class MessageDecoder implements Decoder.Text<Message> {
	private static final Gson gson = new Gson();

	@Override
	public Message decode(String s) throws DecodeException {
		return gson.fromJson(s, Message.class);
	}

	@Override
	public boolean willDecode(String s) {
		return (s != null);
	}

	@Override
	public void init(EndpointConfig config) {
		// Do nothing
	}

	@Override
	public void destroy() {
		// Do nothing
	}
}
