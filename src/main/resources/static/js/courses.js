$('#table').DataTable({
	language: {
		url: '//cdn.datatables.net/plug-ins/1.10.22/i18n/Portuguese-Brasil.json'
	}
});

$('#courseModal').on('show.bs.modal', function() {
	var courseId = parseInt($(event.target).closest('tr').data('id'));

	for (let i = 0; i < courses.length; i++) {
		let course = courses[i];

		if (course.id == courseId) {
			$('#modalTitle').html('Curso <br><b>' + course.name + '</b>');
			$('#updatedName').val(course.name);
			$('#updatedCode').val(course.code);
			$('#updatedImageUrl').val(course.imageUrl);
			$('#updatedSemesters').val(course.semesters);
			$('#updatedInstitution').val(course.institution.id);
			$('#updatedDescription').val(course.description);

			$('#updateCourse').off('click');

			$('#updateCourse').click(() => {
				let updatedName = $('#updatedName').val();
				let updatedCode = $('#updatedCode').val();
				let updatedImageUrl = $('#updatedImageUrl').val();
				let updatedSemesters = $('#updatedSemesters').val();
				let updatedInstitution = parseInt($('#updatedInstitution').val());
				let updatedDescription = $('#updatedDescription').val();

				let institution = institutions.find(i => i.id == updatedInstitution);

				$.ajax({
					url: location.href + '/update',
					type: "POST",
					contentType: "application/json; charset=utf-8",
					data: JSON.stringify({
						id: course.id,
						name: updatedName,
						code: updatedCode,
						imageUrl: updatedImageUrl,
						semesters: updatedSemesters,
						description: updatedDescription,
						institution: institution
					}),
					cache: false,
					processData: false
				}).done(function() {
					course.name = updatedName;
					course.code = updatedCode;
					course.imageUrl = updatedImageUrl;
					course.description = updatedDescription;
					course.semesters = updatedSemesters;
					course.institution = institution;

					let rowSelector = 'tr[data-id="' + course.id + '"]';
					$(rowSelector).find('#codeTd').first().text(updatedCode);
					$(rowSelector).find('#nameTd').first().text(updatedName);
					$(rowSelector).find('#semestersTd').first().text(updatedSemesters);
					$(rowSelector).find('#institutionTd').first().text(institution.name);
				}).fail(function(xhr, status, error) {
					alert(xhr.responseText);
				}).always(function() {
					$('#courseModal').modal('hide');
				});
			});

			break;
		}
	}
});