var questionEditor = new Quill('#question', EditorOptions.READ_ONLY);

updateGame();

let currentRound = -1;

function updateGame() {
	$.get(window.location.origin + '/game/current', function(game) {
		if (game.status === 'FINISHED') {
			$('#question-container').hide();
			$('#leave-game-button').show();
		} else {
			let round = game.rounds[game.currentRound];

			if (currentRound !== game.currentRound) {
				currentRound = game.currentRound;

				$('#round').text(`Round ${currentRound + 1} de ${game.totalRounds}`);
				
				QuillUtils.setContent(questionEditor, round.question.description);

				$('#alternatives').empty();

				round.question.alternatives.forEach(function(alternative) {
					let html = `
							<div class="alternative mb-3">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="alternative" value="${alternative.id}" id="alternative${alternative.id}">
									<label class="form-check-label" for="alternative${alternative.id}">${alternative.description}</label>
								</div>
							</div>
						`;

					$('#alternatives').append(html);
				});

				$('#answer-question-button').show();
			}

			updateTimer(round.secondsLeft);
			updateProgressBar(game.answerTime, round.secondsLeft);

			setTimeout(updateGame, 500);
		}
	});
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
	var date = new Date(0);
	date.setSeconds(secondsLeft);
	return date.toISOString().substr(15, 4);
}

$('#answer-question-button').click(function() {
	if ($('input[name="alternative"]:checked').length == 0) {
		return false;
	}
	
	let alternativeId = $('input[name="alternative"]:checked').val();
	
	$('#answer-question-button').hide();

	$.post('/game/answer/' + alternativeId)
		.done(function(result) {
			console.log(result);
			let color = result.feedback.correct ? 'success' : 'danger';
			let text = result.feedback.correct ? 'Correto' : 'Errado';
			let feedback = result.feedback.feedback !== null ? result.feedback.feedback : '';

			let htmlFeedback = `
						<div class="border border-2 border-${color} p-2 mb-2 rounded ms-3">
							<span>
								<b class="text-${color}">${text}</b>
								<br>
								${feedback}
							</span>
						</div>
					`;

			$('input[name="alternative"]:checked').first().closest('.alternative').append(htmlFeedback);
			$('input[name="alternative"]').prop("disabled", true);
		})
		.fail(function() {
			$('#answer-question-button').show();
		});
});