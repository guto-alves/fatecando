$('.mark-notification-as-read-input').click(function() {
	const _this = $(this);
	$.post('/notifications/' + $(this).val() + '/read')
		.done(function () {
			_this.remove();
		});
});

$('#readallNotificationsButton').click(function() {
	$.post('/notifications/readall')
		.done(function () {
			$('#showNotificationsButton span').remove();
			$('.mark-notification-as-read-input').remove();
		});
});