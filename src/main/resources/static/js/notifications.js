$('.isread-notification-input').click(function() {
	$.post('/notifications/' + $(this).val() + '/read');
});

$('#readall-notifications-btn').click(function() {
	$.post('/notifications/readall');
});