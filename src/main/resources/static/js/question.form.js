$('.alternativeEditor').each(function() {
	new Quill($(this).get(0), EditorOptions.QUESTION_ALTERNATIVE);
});

$('.feedbackEditor').each(function() {
	new Quill($(this).get(0), EditorOptions.QUESTION_ALTERNATIVE_FEEDBACK);
});

const newQuestionEditor = new Quill('#newQuestionEditor', EditorOptions.QUESTION);

$('#newQuestionForm').submit(function() {
	if (newQuestionEditor.root.innerText.trim() !== '') {
		$('#description').val(newQuestionEditor.root.innerHTML);		
	
		$('.alternativeEditor').each(function () {
			if ($(this).find('.ql-editor').prop('innerText').trim() !== '') {
				const content = $(this).find('.ql-editor').prop('innerHTML');
				$(this).closest('.new-alternative').find('.alternativeInput').first().val(content);
			}
		});
		$('.feedbackEditor').each(function () {
			if ($(this).find('.ql-editor').prop('innerText').trim() !== '') {
				const content = $(this).find('.ql-editor').prop('innerHTML');
				$(this).closest('.new-alternative').find('.feedbackInput').first().val(content);			
			}
		});
	}
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
	new Quill(newAlternative.find('.alternativeEditor').get(0), EditorOptions.QUESTION_ALTERNATIVE);
	new Quill(newAlternative.find('.feedbackEditor').get(0), EditorOptions.QUESTION_ALTERNATIVE_FEEDBACK);
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
		
		self.find('.alternativeInput').first()
			.prop('id', `alternatives${alternativeCount}.description`)
			.prop('name', `alternatives[${alternativeCount}].description`);;

		self.find('.feedbackInput').first()
			.prop('id', `alternatives${alternativeCount}.feedback.description`)
			.prop('name', `alternatives[${alternativeCount}].feedback.description`);

		self.find('input[type="checkbox"]').first()
			.prop('id', `alternatives${alternativeCount}.feedback.correct1`)
			.prop('name', `alternatives[${alternativeCount}].feedback.correct`);

		self.find('input[type="hidden"]').first().prop('name', `_alternatives[${alternativeCount}].feedback.correct`);

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