(function() {
	const annotationEditor = new Quill('#annotationEditor', EditorOptions.TOPIC);

	$('#open-sidebar').click(function openNav() {
		if ($('#annotationSideNav').hasClass('active')) {
			$('#close-sidebar').click();
		} else {
			$('#annotationSideNav').addClass('active');
			saveAnnotation();

			if ($(window).width() > 680) {
				$('#annotationSideNav').css('width', '340px');
				$('body').css('marginLeft', '340px');
			} else {
				$('#annotationSideNav').css('width', '100%');
				$('body').css('marginLeft', '0px');
			}
		}
	});

	$('#close-sidebar').click(function closeNav() {
		$('#annotationSideNav').removeClass('active');
		$('#annotationSideNav').css('width', '0px');
		$('body').css('marginLeft', '0px');
		saveAnnotation();
	});

	$(window).on('resize', function() {
		const window = $(this);

		if ($('#annotationSideNav').hasClass('active')) {
			if (window.width() <= 680) {
				$('#annotationSideNav').css('width', '100%');
				$('body').css('marginLeft', '0px');
			} else if (window.width() > 680) {
				$('#annotationSideNav').css('width', '340px');
				$('body').css('marginLeft', '340px');
			}
		}
	});

	let currentAnnotations = annotationEditor.root.innerHTML;

	function saveAnnotation() {
		if (currentAnnotations !== annotationEditor.root.innerHTML) {
			currentAnnotations = annotationEditor.root.innerHTML;
			$.ajax({
				method: 'POST',
				url: location.href.split('#')[0] + '/annotation',
				data: annotationEditor.root.innerHTML,
				contentType: 'text/plain'
			});
		}
	}

	setInterval(saveAnnotation, 2000);
})();
