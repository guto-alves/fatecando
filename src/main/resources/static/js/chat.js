function connect() {
	const socket = new SockJS('http://localhost:8081/secured/room/?access_token=' + accessToken);
	stompClient = Stomp.over(socket);
	stompClient.debug = null;
	let sessionId = '';

	stompClient.connect({}, function(frame) {
		let url = stompClient.ws._transport.url;
		url = url.replace("ws://localhost:8081/secured/room/", "");
		url = url.replace("/websocket", "");
		url = url.replace(/^[0-9]+\//, "");
		url = url.substring(0, url.indexOf('?'));
		sessionId = url;

		stompClient.subscribe('/secured/user/queue/specific-user-user' + sessionId, function(data) {
			conversations = JSON.parse(data.body);
			updateConversations();
			$(document.body)
				.append('<audio autoplay><source src="http://narutogame.com.br/images/media/battle.wav" type="audio/wav" /></audio>');
		});
	});
}

function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	console.log("Disconnected");
}

function sendMessage(message) {
	stompClient.send("/fatecando/secured/room", {}, JSON.stringify(message));
}

connect();