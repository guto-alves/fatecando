$('#table').DataTable({
	language: {
		url: '//cdn.datatables.net/plug-ins/1.10.22/i18n/Portuguese-Brasil.json'
	}
});

$('#institutionModal').on('show.bs.modal', function() {
	let institutionId = parseInt($(event.target).closest('tr').data('id'));

	for (let i = 0; i < institutions.length; i++) {
		let institution = institutions[i];

		if (institution.id == institutionId) {
			$('#modalTitle').html('Atualizar Instituição <br><b>' + institution.name + '</b>');
			$('#updatedName').val(institution.name);
			$('#updatedDescription').val(institution.description);

			$('#updateInstitution').off('click');

			$('#updateInstitution').on('click', () => {
				let updatedName = $('#updatedName').val();
				let updatedDescription = $('#updatedDescription').val();

				$.ajax({
					url: location.href + '/update',
					type: "POST",
					contentType: "application/json; charset=utf-8",
					data: JSON.stringify({
						id: institution.id,
						name: updatedName,
						description: updatedDescription
					}), // Stringified Json Object
					cache: false, // This will force requested pages not to be cached by the browser  
					processData: false
				}).done(function() {
					institution.name = updatedName;
					institution.description = updatedDescription;

					let rowSelector = 'tr[data-id="' + institution.id + '"]';

					$(rowSelector).find('#nameTd').first().text(updatedName);
					$(rowSelector).find('#descriptionTd').first().text(updatedDescription);
				}).fail(function(xhr, status, error) {
					alert(xhr.responseText);
				}).always(function() {
					$('#institutionModal').modal('hide');
				});
			});

			break;
		}
	}
});