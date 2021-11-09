$("time.timeago").timeago();
		
$('#searchReviewForm').submit(function () {
	search();
	event.preventDefault();
});

$('#starsReviewsSelect').change(function () {
	search();
});

function search() {
	const searchedText = $('#searchInput').val();
	const selectedStars = $('#starsReviewsSelect option:selected').val();
	
	$('.review').each(function () {
		const starsReview = $(this).find('.star-rating').first().data('rating-value');
		
		if ((searchedText === '' || $(this).text().toUpperCase().indexOf(searchedText.toUpperCase())) > 0 && 
				(selectedStars === '' || selectedStars == starsReview)) {
			$(this).show();
		} else {
			$(this).hide();
		}
	});
}