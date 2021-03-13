$('#inputFilter').keyup(function() {
	let filter = $(this).val().toUpperCase();
	filterDisciplinesBy(filter);
});

$('#semesterSelect').change(function() {
	let selectedSemester = $(this).val();
	filterDisciplinesBySemester(selectedSemester);
});

function filterDisciplinesBy(searchValue) {
	getVisibleDisciplines().each(function() {
		let disciplineContainer = $(this);
		let name = disciplineContainer.find('.card-header').first().text();

		let discipline = disciplines.find(d => d.name === name);

		if (discipline.name.toUpperCase().indexOf(searchValue) > -1 ||
			discipline.code.toUpperCase().indexOf(searchValue) > -1 ||
			discipline.description.toUpperCase().indexOf(searchValue) > -1 ||
			discipline.objective.toUpperCase().indexOf(searchValue) > -1
		) {
			disciplineContainer.removeAttr('style');
		} else {
			disciplineContainer.hide();
		}
	});
}

function filterDisciplinesBySemester(selectedSemester) {
	$('#disciplinesContainer').children().each(function() {
		let semester = $(this).data('semester');

		if (selectedSemester == 0 || selectedSemester == semester) {
			$(this).removeAttr('style');
		} else {
			$(this).hide();
		}
	});

	$('#inputFilter').trigger('keyup');
}

function getVisibleDisciplines() {
	let disciplines = [];

	let selectedSemester = parseInt($('#semesterSelect').val());

	if (selectedSemester != 0) {
		let selector = 'div[data-semester="' + selectedSemester + '"]';

		disciplines = $('#disciplinesContainer').find(selector);
	} else { // get all disciplines
		disciplines = $('#disciplinesContainer').children();
	}

	return disciplines;
}

function showDiscipline(disciplineId) {
	location.href = '/disciplines/' + disciplineId;
}

setTimeout(() => {
	filterDisciplinesBySemester($('#semesterSelect').val());
}, 500);

