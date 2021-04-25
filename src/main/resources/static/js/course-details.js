$('#inputFilter').keyup(function() {
	let filter = $(this).val().toUpperCase();
	filterSubjectsBy(filter);
});

$('#semesterSelect').change(function() {
	let selectedSemester = $(this).val();
	filterSubjectsBySemester(selectedSemester);
});

function filterSubjectsBy(searchValue) {
	getVisibleDisciplines().each(function() {
		let subjectContainer = $(this);
		let name = subjectContainer.find('.card-header').first().text();

		let subject = subjects.find(d => d.name === name);

		if (subject.name.toUpperCase().indexOf(searchValue) > -1 ||
			subject.code.toUpperCase().indexOf(searchValue) > -1 ||
			subject.description.toUpperCase().indexOf(searchValue) > -1 ||
			subject.objective.toUpperCase().indexOf(searchValue) > -1
		) {
			subjectContainer.removeAttr('style');
		} else {
			subjectContainer.hide();
		}
	});
}

function filterSubjectsBySemester(selectedSemester) {
	$('#subjectsContainer').children().each(function() {
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
	let subjects = [];

	let selectedSemester = parseInt($('#semesterSelect').val());

	if (selectedSemester != 0) {
		let selector = 'div[data-semester="' + selectedSemester + '"]';

		subjects = $('#subjectsContainer').find(selector);
	} else { // get all subjects
		subjects = $('#subjectsContainer').children();
	}

	return subjects;
}

function showSubject(subjectId) {
	location.href = '/subjects/' + subjectId;
}

setTimeout(() => {
	filterSubjectsBySemester($('#semesterSelect').val());
}, 500);

