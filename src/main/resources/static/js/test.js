(function() {
	$('.question-editor').each(function() {
		new Quill($(this).get(0), EditorOptions.READ_ONLY);
	});
	
	$('.alternativeEditor').each(function() {
		new Quill($(this).get(0), EditorOptions.READ_ONLY);
	});;

	$('#finishTest').click(function() {
		if ($('.question:not(:has(input:checked))').length > 0) {
			$('html,body').animate({
				scrollTop: $('.question:not(:has(input:checked))').first().offset().top
			}, 500);

			return false;
		}

		$(this).remove();
		$('#spinner').show();

		const alternativeIds = $.map($('.alternative input:checked'), alternative => alternative.value);

		$.ajax({
			method: 'POST',
			url: '/test/check',
			data: JSON.stringify(alternativeIds),
			contentType: "application/json"
		})
			.done(function(feedbacks) {
				feedbacks.forEach(feedback => showFeedback(feedback));
				$('.feedbackEditor').each(function() {
					new Quill($(this).get(0), EditorOptions.READ_ONLY);
				});;
				showResult(feedbacks);
			})
			.fail(function(jqXHR, textStatus) {
				alert(textStatus);
			});
	});

	function showFeedback(feedback) {
		const color = feedback.correct ? 'success' : 'danger';

		$('.alternative:has(input[value=' + feedback.alternative.id + '])').append(
			`<div class="border border-2 border-${color} p-2 mb-2 rounded ms-3">
				<span>
					<b class="text-${color}">${feedback.title ?? ''}</b>
					<div class="feedbackEditor">${feedback.description ?? ''}</div>
				</span>
			</div>`
		);
	}

	function showResult(feedbacks) {
		const totalQuestions = $('.question').length;

		const rightAnswers = feedbacks.reduce((total, feedback) => total + (feedback.correct ? 1 : 0), 0);

		const percent = rightAnswers / totalQuestions * 100;

		$('#ponctuation').html(
			`<span class="${percent >= 60 ? 'text-success' : 'text-danger'}">${rightAnswers}/${totalQuestions}</span>`
		);
		
		clearInterval(timer);
		$('.timer-container').empty();
		const elapsedTime = Date.now() - startTime;
		$('#elapsedTime').text(new Date(elapsedTime).toISOString().substr(11, 8));
		
		$('html,body').animate({ scrollTop: 0 }, 4000, function() {
			$('#test-result-container').show();
			$('#spinner').hide();
		});
	}
})();