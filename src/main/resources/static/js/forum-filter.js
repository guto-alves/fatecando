$('#apply-filters').click(function() {
	$.get(location.href.split('?')[0] + '/forums', $('#filters-form').serialize(), function(threads) {
		$('#forums').empty();

		threads.forEach(thread => {
			let tags = thread.tags.map(tag => `<span class="badge rounded-pill bg-dark me-2">${tag.name}</span>`).join('\n');

			$('#forums').append(`
				<div class="list-group-item list-group-item-action list-card-item mb-3 border-1 shadow"
					onclick="openThread(${thread.id})">

					<div class="d-flex">
						<div class="me-4 text-center align-self-center">
							<div class="mb-1">
								<small class="d-block text-muted">votos</small> 
								<small>${thread.voteCount}</small>
							</div>
							<div class="mb-1 ${thread.answered ? 'badge bg-success' : ''}">
								<small class="d-block ${!thread.answered ? 'text-muted' : ''}">respostas</small> 
								<small>${thread.totalComments}</small>
							</div>
							<div class="mb-1">
								<small class="d-block text-muted">visitas</small> 
								<small>${thread.viewCount}</small>
							</div>
						</div>
						<div class="flex-fill">
							<div class="d-flex w-100 justify-content-between">
								<h5 class="mb-1">${thread.title}</h5>
								<time class="text-muted timeago" datetime="${thread.creationDate}"></time>
							</div>
							<div class="forum-thread-body mb-1 truncate">${thread.bodyHtml}</div>

							<div class="d-flex justify-content-between align-items-center">
								<div class="tags align-self-center">
									${tags}
								</div>

								<small>Criado por ${thread.user.fullName}</small>
							</div>
						</div>
					</div>
				</div>
			`);
		});
		
		$('.forum-thread-body').each(function() {
			$(this).html($(this).text());
		});

		$("time.timeago").timeago();
		$('#expand-filters').click();
	});
});

function openThread(threadId) {
	location.href = '/subjects/' + subjectId + '/forum-topics/' + threadId;
}