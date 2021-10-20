const newQuestionEditor = new Quill('#newQuestionEditor', EditorOptions.QUESTION);

$('.alternative-container').each(function() {
	const content = $(this).find('.alternative-input:first').val();
	const alternativeEditorDiv = $(this).find('.alternative-editor:first');
	alternativeEditorDiv.html(content);
	new Quill($(alternativeEditorDiv).get(0), EditorOptions.QUESTION_ALTERNATIVE);
});

$('.feedback-container').each(function() {
	const content = $(this).find('.feedback-input:first').val();
	const feedbackEditorDiv = $(this).find('.feedback-editor:first');
	feedbackEditorDiv.html(content);
	new Quill($(feedbackEditorDiv).get(0), EditorOptions.QUESTION_ALTERNATIVE_FEEDBACK);
});

$('#newQuestionForm').submit(function() {
	$('#description').val(newQuestionEditor.root.innerHTML);

	$('.alternative-editor').each(function() {
		const content = $(this).find('.ql-editor').prop('innerHTML');
		console.log(content);
		$(this).closest('.new-alternative').find('.alternative-input').first().val(content);
	});
	$('.feedback-editor').each(function() {
		const content = $(this).find('.ql-editor').prop('innerHTML');
		$(this).closest('.new-alternative').find('.feedback-input').first().val(content);
	});
});

$('.is-correct-alternative').change(function() {
	markAsRight($(this));
});

$('#addAlternative').click(function() {
	const totalAlternatives = $('#alternatives').children().length;

	if (totalAlternatives >= 6) {
		return false;
	}

	const newAlternative = $('.new-alternative').first().clone();
	newAlternative.find('.ql-editor,.ql-toolbar').remove();
	new Quill(newAlternative.find('.alternative-editor').get(0), EditorOptions.QUESTION_ALTERNATIVE);
	new Quill(newAlternative.find('.feedback-editor').get(0), EditorOptions.QUESTION_ALTERNATIVE_FEEDBACK);
	newAlternative.find('.is-correct-alternative').prop('checked', false);
	$('#alternatives').append(newAlternative);
	updateAlternatives();

	$('.is-correct-alternative').off('change');
	$('.is-correct-alternative').change(function() {
		markAsRight($(this));
	});

	$('.remove-alternative').off('click');
	$('.remove-alternative').click(function() {
		if (removeAlternative($(this))) {
			updateAlternatives();
		}
	});
});

$('.remove-alternative').click(function() {
	if (removeAlternative($(this))) {
		updateAlternatives();
	}
});

function markAsRight(alternative) {
	if ($('.is-correct-alternative:checked').length >= 1) {
		$('.is-correct-alternative:checked').prop('checked', false);
	}

	$(alternative).prop('checked', true);
}

function removeAlternative(alternative) {
	if ($('#alternatives').children().length > 2 &&
		!$(alternative).closest('.new-alternative').find('.is-correct-alternative').first().prop('checked')) {
		$(alternative).closest('.new-alternative').remove();
		return true;
	}

	return false;
}

function updateAlternatives() {
	let alternativeCount = 0;

	$('#alternatives .new-alternative').each(function() {
		const self = $(this);

		self.find('.alternative-number').first().text((alternativeCount + 1) + '.');

		self.find('.alternative-input').first()
			.prop('id', `alternatives${alternativeCount}.description`)
			.prop('name', `alternatives[${alternativeCount}].description`);

		self.find('.feedback-input').first()
			.prop('id', `alternatives${alternativeCount}.feedback.description`)
			.prop('name', `alternatives[${alternativeCount}].feedback.description`);

		self.find('input[type="checkbox"]').first()
			.prop('id', `alternatives${alternativeCount}.feedback.correct1`)
			.prop('name', `alternatives[${alternativeCount}].feedback.correct`);

		self.find('.form-check input[type="hidden"]').first().prop('name', `_alternatives[${alternativeCount}].feedback.correct`);

		alternativeCount++;
	});
}

$('#contribute').click(function(event) {
	if ($('button[class*="accordion-button"]').hasClass('collapsed')) {
		event.preventDefault();
		$('button[class*="accordion-button"]').click();
		setTimeout(() => location.href = location.href.split('#')[0] + "#uploadQuestion", 200);
	}
});