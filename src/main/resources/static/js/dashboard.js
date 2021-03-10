function showDiscipline(disciplineId) {
	location.href = '/disciplines/' + disciplineId;
}

function showCourse(courseId) {
	location.href = '/course/' + courseId;
}

function getCoursesFilteredByInstitution() {
	let selectedInstitution = parseInt($('#institutionSelect').val());

	let filteredCourses = [];

	if (selectedInstitution == 0) {
		filteredCourses = $('#coursesContainer').children();
	} else {
		let selector = 'div[data-institution="' + selectedInstitution + '"]';
		filteredCourses = $('#coursesContainer').find(selector);
	}

	return filteredCourses;
}

$('#inputFilter').keyup(function() {
	let filter = $(this).val().toUpperCase();

	getCoursesFilteredByInstitution().each(function() {
		let courseContainer = $(this);
		let cardTitle = courseContainer.find('.card-title').first().text();

		let course = courses.find(course => course.name === cardTitle);

		if (filter == '' ||
			course.name.toUpperCase().indexOf(filter) > -1 ||
			course.code.toUpperCase().indexOf(filter) > -1 ||
			course.description.toUpperCase().indexOf(filter) > -1 ||
			course.institution.name.toUpperCase().indexOf(filter) > -1 ||
			`${course.semesters}`.indexOf(filter) > -1
		) {
			courseContainer.show();
			courseContainer.removeAttr('style');
		} else {
			courseContainer.hide();
		}
	});

	updateTotalCourses();
});

$('#institutionSelect').change(function() {
	let selectedInstitution = $(this).val();

	$('#coursesContainer').children().each(function() {
		let institution = $(this).data('institution');

		if (selectedInstitution == 0 || selectedInstitution == institution) {
			$(this).show();
			$(this).removeAttr('style');
		} else {
			$(this).hide();
		}
	});

	$('#inputFilter').trigger('keyup');

	updateTotalCourses();
});

function updateTotalCourses() {
	let totalCourses = $('#coursesContainer').find('div[class="col"]:not([style])')['length'];
	$('#totalCourses').text(totalCourses > 0 ? `${totalCourses} Curso(s)` : 'Nenhum curso encontrado');
}
