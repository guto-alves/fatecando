let currentQuestionIndex = -1;
let questionsLenght = Object.keys(questions).length;
let feedbacks = [];

$('#startQuiz').click(function() {
	$(this).remove();
	$('#quiz-container').removeClass('bg-initial-quiz');

	nextQuestion();

	$('#questionContainer').show();
});

function nextQuestion() {
	currentQuestionIndex = ++currentQuestionIndex % questionsLenght;

	$('#questionIndex').text('Questão ' + (currentQuestionIndex + 1) + ' de ' + questionsLenght);

	let currentQuestion = questions[currentQuestionIndex];

	$('#question').html(currentQuestion.description);

	// Removes old alternatives
	$('.form-check').remove();

	// Add new alternatives
	currentQuestion.alternatives.forEach(alternative => {
		let alternativeRadio = `
			<div class="form-check mb-3">
				<input class="form-check-input" type="radio"
					name="alternativeRadio" value="` + alternative.id + `">
				<label class="form-check-label">` + alternative.description + `</label>
			</div>		
		`;

		$('#questionContainer').append(alternativeRadio);
	});

	$('#nextQuestion').hide();
	$('#answerQuestion').show();
}

$('#answerQuestion').click(() => {
	let alternativeId = $('input[name="alternativeRadio"]:checked').val();
	let questionId = questions[currentQuestionIndex].id;

	$.post(
		'http://localhost:8080/questions/' + questionId + '/answer/' + alternativeId
	).done(feedback => {
		feedbacks.push(feedback);

		let color = feedback.correct ? 'success' : 'danger';
		let text = feedback.correct ? 'Correto' : 'Errado';

		let htmlFeedback = `
			<div class="border border-2 border- ` + color + ` p-2 mt-2 rounded ">
				<span>
					<b class="text-` + color + `">` + text + `</b>
					<br>
					` + feedback.feedback + `
				</span>
			</div>
		`;

		$('input[name="alternativeRadio"]:checked').closest('.form-check').append(htmlFeedback);

		if (currentQuestionIndex + 1 == questionsLenght) { // Quiz is over
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

	let rightAnswers = feedbacks.reduce((total, feedback) => total + (feedback.correct ? 1 : 0), 0);
	
	let result = rightAnswers + '/' + questionsLenght;

	$('#quizResult').append(
		`<span class="h1 fw-bolder">` + result + `</span>
		<br>
		<div class="text-muted text-right mt-3">Finalizado em: ` + new Date().toLocaleString() + `</div>`
	);

	$('#quizResult').show();
});

