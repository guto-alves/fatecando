function connect() {
	const socket = new SockJS(fatecandoApiUrl + '/secured/room/?access_token=' + accessToken);
	stompClient = Stomp.over(socket);
	stompClient.debug = null;
	let sessionId = '';

	stompClient.connect({}, function(frame) {
		let url = stompClient.ws._transport.url;
		url = url.replace('ws' + fatecandoApiUrl.split('http')[1] + '/secured/room/' , '');
		url = url.replace('/websocket', '');
		url = url.replace(/^[0-9]+\//, '');
		url = url.substring(0, url.indexOf('?'));
		sessionId = url;

		stompClient.subscribe('/secured/user/queue/specific-user-user' + sessionId, function(data) {
			chats = JSON.parse(data.body);
			chats = chats.map(function (chat) {
				if (chat.name == null) {
					chat.from = chat.lastMessage.from.email == from ? chat.lastMessage.to : chat.lastMessage.from;
					chat.name = chat.from.fullName;					
				}
				return chat;
			});
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
}

function sendMessage(message) {
	stompClient.send("/fatecando/messages", {}, JSON.stringify(message));
}

connect();