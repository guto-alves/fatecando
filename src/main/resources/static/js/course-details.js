$('#inputFilter').keyup(function() {
	let filter = $(this).val().toUpperCase();

	let selectedSemester = parseInt($('#semesterSelect').val());

	let disciplinesFilteredBySemester = [];

	if (selectedSemester != 0) {
		let selector = 'div[data-semester="' + selectedSemester + '"]';

		disciplinesFilteredBySemester = $('#disciplinesContainer').find(selector);
	} else {
		disciplinesFilteredBySemester = $('#disciplinesContainer').children();
	}

	disciplinesFilteredBySemester.each(function() {
		let disciplineContainer = $(this);
		let semester = disciplineContainer.data('semester');
		let name = disciplineContainer.find('.card-title').first().text();

		for (let i = 0; i < disciplines.length; i++) {
			let discipline = disciplines[i];

			if (discipline.name === name && (
				discipline.name.toUpperCase().indexOf(filter) > -1 ||
				discipline.code.toUpperCase().indexOf(filter) > -1 ||
				discipline.description.toUpperCase().indexOf(filter) > -1 ||
				discipline.objetive.toUpperCase().indexOf(filter) > -1
			)) {
				disciplineContainer.show();
				disciplineContainer.removeAttr('style');
				break;
			} else {
				disciplineContainer.hide();
			}
		}
	});
});

$('#semesterSelect').change(function() {
	let selectedSemester = $(this).val();

	$('#disciplinesContainer').children().each(function() {
		let semester = $(this).data('semester');

		if (selectedSemester == 0 || selectedSemester == semester) {
			$(this).show();
			$(this).removeAttr('style');
		} else {
			$(this).hide();
		}
	});

	$('#inputFilter').trigger('keyup');
});

function go(disciplineId) {
	location.href = '/disciplines/' + disciplineId;
}