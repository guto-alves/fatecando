getGame();

function getGame() {
	$.get(window.location.origin + '/game/current', function(game) {
		let totalPlayers = game.totalPlayers - game.players.length;

		if (totalPlayers > 1) {
			$('#totalPlayers').text(`Faltam ${totalPlayers} jogadores para começar!`);
		} else {
			$('#totalPlayers').text(`Falta 1 jogador para começar!`);
		}

		if (game.status === 'PLAYING') {
			location.href = '/game/playing';
		}

		setTimeout(getGame, 3000);
	});
}