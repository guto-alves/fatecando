$('#fav-button').click(function() {
	$.post(location.href.split('#')[0] + '/fav');
});


let waiting = false;

$('#finishButton').click(function() {
	if (waiting) {
		return false;
	}

	waiting = true;

	let button = $(this);
	let buttonLabel = $('#finishButton span');

	buttonLabel.text('Aguarde ...');

	$.post(location.href.split('#')[0] + '/finished')
		.done(function() {
			if (button.hasClass('active')) {
				button.removeClass('active');
				buttonLabel.text("Marcar como concluído");
			} else {
				button.addClass('active');
				buttonLabel.text("Concluído");
			}

			waiting = false;
		}).fail(function() {
			button.removeClass('active');

			if (button.hasClass('active')) {
				buttonLabel.text("Erro ao desmarcar tópico como concluído!");
				buttonLabel.text("Marcar como concluído");
			} else {
				buttonLabel.text("Erro ao marcar tópico como concluído!");
				buttonLabel.text("Concluído");
			}
		});
});