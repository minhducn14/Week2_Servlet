let ws;

function connect() {
	const username = document.getElementById("userName").value;

	if ("WebSocket" in window) {
		// Open WebSocket
		ws = new WebSocket("ws://localhost:8080/WebSocketDemo/chatEndpoint/" + username);

		ws.onopen = function() {
			// Perform action when connection is opened
		};

		ws.onmessage = function(evt) {
			const json = JSON.parse(evt.data);
			const currentValue = document.getElementById('output').innerHTML;
			document.getElementById('output').innerHTML = currentValue + "<br/>" + json.username + ": " + json.message;
		};

		ws.onclose = function() {
			// WebSocket is closed.
			alert("Connection is closed...");
		};
	} else {
		// The browser doesn't support WebSocket
		alert("WebSocket NOT supported by your Browser!");
	}
}

function sendMessage() {
	const username = document.getElementById('userName').value;
	const message = document.getElementById('message').value;
	const json = {
		'username': username,
		'message': message
	};

	ws.send(JSON.stringify(json));
	return false;
}
