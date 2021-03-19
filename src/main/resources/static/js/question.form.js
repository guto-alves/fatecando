$('#contribute').click(function(event) {
	if ($('button[class*="accordion-button"]').hasClass('collapsed')) {
		event.preventDefault();
		$('button[class*="accordion-button"]').click();
		setTimeout(() => location.href = location.href.split('#')[0] + "#uploadQuestion", 200);
	}
});

$('.is-correct-alternative').change(function() {
	markAsRight($(this));
});

$('#addAlternative').click(function() {
	let totalAlternatives = $('#alternatives').children().length;

	if (totalAlternatives >= 6) {
		return false;
	}

	$('#alternatives').append(
		`
			<div class="new-alternative mb-3 d-flex justify-content-start align-items-center">
				<span class="text-muted me-2">${totalAlternatives + 1}.</span>
				<div class="container">
					<textarea class="form-control me-5" name="alternatives[${totalAlternatives}].description" rows="1">Alternativa ${totalAlternatives + 1}</textarea>
					<input type="text" class="form-control" placeholder="Feedback da alternativa" id="alternatives${totalAlternatives}.feedback" name="alternatives[${totalAlternatives}].feedback" value="">
				</div>
				<div class="form-check me-3">
					<input type="checkbox" class="is-correct-alternative form-check-input" id="alternatives${totalAlternatives}.correct1" name="alternatives[${totalAlternatives}].correct" value="true"><input type="hidden" name="_alternatives[${totalAlternatives}].correct" value="on">
				</div>
				<button type="button" class="remove-alternative btn btn-outline-danger btn-sm">
					<i class="fa fa-trash fa-lg" aria-hidden="true"></i>
				</button>
			</div>
		`
	);

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
		!$(alternative).closest('.new-alternative').find('input[type*="checkbox"]').first().prop('checked')) {
		$(alternative).closest('.new-alternative').remove();
		return true;
	}

	return false;
}

function updateAlternatives() {
	let alternativeCount = 0;

	$('#alternatives').children().each(function() {
		let self = $(this);

		self.find('span').text((alternativeCount + 1) + '.');
		self.find('textarea').first().attr('name', `alternatives[${alternativeCount}].description`);
		
		self.find('input[type="text"]').first()
			.prop('id', `alternatives${alternativeCount}.feedback`)
			.prop('name', `alternatives[${alternativeCount}].feedback`);
			
		self.find('input[type="checkbox"]').first()
			.prop('id', `alternatives${alternativeCount}.correct1`)
			.prop('name', `alternatives[${alternativeCount}].correct`);
		
		self.find('input[type="hidden"]').first().prop('name', `_alternatives[${alternativeCount}].correct`);

		alternativeCount++;
	});
}
