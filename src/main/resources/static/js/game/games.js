$('.select2').select2();

$('#filter-game-input').keyup(function() {
	let filter = $(this).val().toUpperCase();

	$('.game').each(function() {
		if ($(this).text().toUpperCase().indexOf(filter) > -1) {
			$(this).show();
		} else {
			$(this).hide();
		}
	});
});

$('#filter-topics').change(function() {
	$('.game').hide();

	let topics = [];
	
	$('#filter-topics').find(':selected').each(function() {
		topics.push($(this).text().toUpperCase());
	});
	
	$('.game').each(function() {
		let game = $(this);
		
		let show = true;
		
		for (let i = 0; i < topics.length; i++) {
			if (game.text().toUpperCase().indexOf(topics[i]) == -1) {
				show = false;
				break;
			}	
		}
		
		if (show) {
			game.show();		
		}
	});
});

function updateGames() {
	$.get(window.locatio.origin + '/game/games', function(games) {
		$('#games-container').empty();

		games.forEach(function(game) {

		});

		setTimeout(checkGames, 500);
	});
}