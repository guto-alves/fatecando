const questionEditor = new Quill('#question', EditorOptions.READ_ONLY);

let currentRound = -1;

updateGame();

function updateGame() {
	$.get('/games/current', function(game) {
		if (game.status === 'FINISHED') {
			$('#question-container').hide();
			$('#leave-game-button').show();
		} else {
			const round = game.rounds[game.currentRound];

			if (currentRound !== game.currentRound) {
				currentRound = game.currentRound;

				$('#round').text(`Round ${currentRound + 1} de ${game.totalRounds}`);

				QuillUtils.setContent(questionEditor, round.question.description);

				$('#alternatives').empty();

				round.question.alternatives.forEach(function(alternative) {
					$('#alternatives').append(`
						<div class="alternative mb-3">
							<div class="form-check">
								<input class="form-check-input" type="radio" id="alternative${alternative.id}" name="alternative" value="${alternative.id}">
								<label class="form-check-label" for="alternative${alternative.id}"><div class="alternativeGameEditor">${alternative.description}</div></label>
							</div>
						</div>
					`);
				});
				
				$('.alternativeGameEditor').each(function() {
					new Quill($(this).get(0), EditorOptions.READ_ONLY);
				});;
			}

			const answer = round.answers.find(answer => answer.user.id == currentPlayerId);

			if (answer != null) {
				showFeedback(answer.feedback);
			} else {
				$('#answer-question-button').show();
			}

			updateTimer(round.secondsLeft);
			updateProgressBar(game.answerTime, round.secondsLeft);

			setTimeout(updateGame, 500);
		}

		updateScoreboard(game);
	});
}

function updateScoreboard(game) {
	const table = document.createElement('table');
	table.id = 'scoreboard';
	table.className = 'table table-bordered table-sm text-center align-middle mb-4';

	// Add the header row
	const totalColumns = game.rounds.length + 1;
	const thead = table.createTHead();
	const headerRow = thead.insertRow();

	for (let i = 0; i < totalColumns; i++) {
		let headerCell = document.createElement("th");
		headerCell.innerText = i == 0 ? 'Jogador' : 'Round ' + i;
		headerRow.appendChild(headerCell);
	}

	// Add the data rows
	const tbody = table.createTBody();

	game.players.forEach(function(player) {
		const tr = tbody.insertRow();

		if (currentPlayerId == player.id) {
			tr.className = 'border border-2 border-primary fw-bold';
		}

		for (let column = 0; column < totalColumns; column++) {
			const td = tr.insertCell();

			if (column == 0) {
				td.innerText = player.fullName;
			} else {
				const round = game.rounds[column - 1];
				const answer = round.answers.find(answer => answer.user.id == player.id);

				if (answer == null) {
					$(td)
						.addClass('no-answer-icon')
						.append('<i class="bi bi-dash"></i>');
				} else if (answer.feedback.correct) {
					$(td)
						.addClass('right-answer-icon')
						.append('<i class="bi bi-check2"></i>');
				} else {
					$(td)
						.addClass('wrong-answer-icon')
						.append('<i class="bi bi-x"></i>');
				}
			}

		}
	});

	$('#scoreboard').remove();
	$('.game-name').after(table);
}

function updateTimer(secondsLeft) {
	$('h4.timer').text(formatTime(secondsLeft));

	if (secondsLeft <= 10) {
		$('.timer').addClass('text-danger');
	} else {
		$('.timer').removeClass('text-danger');
	}
}

function updateProgressBar(totalSeconds, secondsLeft) {
	$('.progress-bar').css('width', secondsLeft * 100 / totalSeconds + '%');
}

function formatTime(secondsLeft) {
	const date = new Date(0);
	date.setSeconds(secondsLeft);
	return date.toISOString().substr(15, 4);
}

$('#answer-question-button').click(function() {
	const alternative = $('.alternative:has(input:checked)');

	if (alternative.length == 0) {
		return false;
	}

	$('#answer-question-button').hide();

	const alternativeId = alternative.find('input:checked').val();

	$.post('/games/answer/' + alternativeId)
		.done(feedback => showFeedback(feedback))
		.fail(function() {
			$('#answer-question-button').show();
		});
});

function showFeedback(feedback) {
	if ($('.alternative:has(:disabled)').length != 0) {
		return;
	}

	const color = feedback.correct ? 'success' : 'danger';

	$('.alternative input[value="' + feedback.alternative.id + '"]').prop('checked', true);

	$('.alternative:has(input:checked)').append(`
		<div class="border border-2 border-${color} p-2 mb-2 rounded ms-3">
			<span>
				<b class="text-${color}">${feedback.title ?? ''}</b>
				<div id="feedbackGameEditor">${feedback.description ?? ''}</div>
			</span>
		</div>
	`);
	
	new Quill($('#feedbackGameEditor').get(0), EditorOptions.READ_ONLY);

	$('.alternative input').prop("disabled", true);
}
