const questionEditor = new Quill('#question', EditorOptions.READ_ONLY);

let currentQuestionIndex = -1;
const totalQuestions = questions.length;
let feedbacks = [];

$('#startQuiz').click(function() {
	$(this).remove();

	$('#quiz-container').removeClass('bg-initial-quiz');

	nextQuestion();

	$('#questionContainer').show();
});

function nextQuestion() {
	currentQuestionIndex = ++currentQuestionIndex % totalQuestions;

	$('#questionIndex').text('Questão ' + (currentQuestionIndex + 1) + ' de ' + totalQuestions);

	let currentQuestion = questions[currentQuestionIndex];

	QuillUtils.setContent(questionEditor, currentQuestion.description);

	// Removes old alternatives
	$('.alternative').remove();

	// Add new alternatives
	currentQuestion.alternatives.forEach(alternative => {
		let alternativeRadio = `
			<div class="alternative mb-3">
				<div class="form-check d-flex">
					<input class="form-check-input me-3" type="radio" name="alternativeRadio" value="` + alternative.id + `">
					<label class="form-check-label align-self-center mt-1">` + alternative.description + `</label>
				</div
			</div>		
		`;

		$('#questionContainer').append(alternativeRadio);
	});

	$('#nextQuestion').hide();
	$('#answerQuestion').show();
}

$('#answerQuestion').click(function() {
	const alternativeId = $('input[name="alternativeRadio"]:checked').val();

	if (alternativeId == null) {
		return false;
	}

	const questionId = questions[currentQuestionIndex].id;

	$.post(
		'/questions/' + questionId + '/answer/' + alternativeId
	).done(feedback => {
		feedbacks.push(feedback);

		const color = feedback.correct ? 'success' : 'danger';

		const htmlFeedback = `
			<div class="border border-2 border-${color} p-2 mb-2 rounded ms-5">
				<span>
					<b class="text-${color}">${feedback.title ?? ''}</b>
					<br>
					${feedback.description ?? ''}
				</span>
			</div>
		`;

		$('input[name="alternativeRadio"]:checked').first().closest('.alternative').append(htmlFeedback);

		if (currentQuestionIndex + 1 == totalQuestions) { // Quiz is over
			$('#answerQuestion').remove();
			$('#nextQuestion').remove();
			$('#finishQuiz').show();
		} else {
			$('#answerQuestion').hide();
			$('#nextQuestion').show();
		}
	}).fail(() => {
		console.log('Error ao registrar resposta. Por favor, tente mais tarde.');
	});
});

$('#finishQuiz').click(() => {
	$('#finishQuiz').remove();
	$('#questionContainer').remove();

	const rightAnswers = feedbacks.reduce((total, feedback) => total + (feedback.correct ? 1 : 0), 0);

	const result = rightAnswers + '/' + totalQuestions;

	$('#quizResult').append(`
		<span class="h1 fw-bolder">` + result + `</span>
		<br>
		<div class="text-muted text-right mt-3">Finalizado em: ${new Date().toLocaleString()}</div>
	`);

	$('#quizResult').show();
});

