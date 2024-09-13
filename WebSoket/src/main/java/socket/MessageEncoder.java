package socket;

import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;
import model.Message;

import com.google.gson.Gson;

public class MessageEncoder implements Encoder.Text<Message> {
	private static final Gson gson = new Gson();

	@Override
	public String encode(Message message) throws EncodeException {
		return gson.toJson(message);
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
