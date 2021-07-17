$('input:checkbox').click(function() {
	$.post('/notifications/' + $(this).val() + '/read');
});

$('#readall-notifications-btn').click(function() {
	$.post('/notifications/readall');
});