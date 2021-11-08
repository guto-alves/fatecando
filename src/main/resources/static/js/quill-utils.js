hljs.configure({
	tabReplace: '    ',
	languages: ['java']
});
hljs.initHighlighting();
hljs.initHighlightingOnLoad();
hljs.initLineNumbersOnLoad();

const EditorOptions = {
	TOPIC: {
		modules: {
			syntax: true,
			toolbar: [
				[{ 'font': [] }],
				[{ 'size': ['small', false, 'large' /*, 'huge'*/ ] }], // custom dropdown
				[{ 'header': 1 }, { 'header': 2 }], // custom button values
				[{ 'header': [1, 2, 3, 4, 5, 6, false] }],
				['bold', 'italic', 'underline', 'strike'], // toggled buttons
				[{ 'color': [] }, { 'background': [] }], // dropdown with defaults from theme
				[{ 'align': '' }, { 'align': 'center' }, { 'align': 'right' }, { 'align': 'justify' }],
				[{ 'indent': '-1' }, { 'indent': '+1' }], // outdent/indent
				[{ 'script': 'sub' }, { 'script': 'super' }], // superscript/subscript
				[{ 'list': 'ordered' }, { 'list': 'bullet' }],
				[{ 'direction': 'rtl' }], // text direction
				['blockquote', 'code-block'],
				['link', 'image', 'video', 'formula'],
				['emoji'],
				['clean'] // remove formatting button
			],
			history: {
				delay: 2000,
				maxStack: 500,
				userOnly: true
			},
			imageDrop: true,
			imageResize: {
				modules: ['Resize', 'DisplaySize', 'Toolbar']
			},
			"emoji-toolbar": true
		},
		theme: 'snow'
	},
	QUESTION: {
		modules: {
			syntax: true,
			toolbar: [
				['bold', 'italic', 'underline', 'strike'], // toggled buttons
				[{ 'header': 1 }, { 'header': 2 }], // custom button values
				[{ 'header': [1, 2, 3, false] }],
				[{ 'color': [] }, { 'background': [] }], // dropdown with defaults from theme
				[{ 'align': '' }, { 'align': 'center' }, { 'align': 'right' }, { 'align': 'justify' }],
				[{ 'script': 'sub' }, { 'script': 'super' }], // superscript/subscript
				[{ 'list': 'ordered' }, { 'list': 'bullet' }],
				[{ 'indent': '-1' }, { 'indent': '+1' }], // outdent/indent
				[{ 'direction': 'rtl' }], // text direction
				['blockquote', 'code-block'],
				['link', 'image', 'video', 'formula'],
				['clean'] // remove formatting button
			],
			history: {
				delay: 2000,
				maxStack: 500,
				userOnly: true
			},
			imageDrop: true,
			imageResize: {
				modules: ['Resize', 'DisplaySize', 'Toolbar']
			}
		},
		theme: 'snow'
	},
	QUESTION_ALTERNATIVE: {
		modules: {
			syntax: true,
			toolbar: [
				['bold', 'italic', 'underline', 'strike'], // toggled buttons
				[{ 'header': 1 }, { 'header': 2 }], // custom button values
				[{ 'color': [] }, { 'background': [] }], // dropdown with defaults from theme
				[{ 'script': 'sub' }, { 'script': 'super' }], // superscript/subscript
				['blockquote', 'code-block'],
				['link', 'image', 'video', 'formula'],
				['clean'] // remove formatting button
			],
			history: {
				delay: 2000,
				maxStack: 500,
				userOnly: true
			},
			imageDrop: true,
			imageResize: {
				modules: ['Resize', 'DisplaySize', 'Toolbar']
			}
		},
		placeholder: 'Alternativa',
		theme: 'snow'
	},
	QUESTION_ALTERNATIVE_FEEDBACK: {
		modules: {
			syntax: true,
			toolbar: [
				['bold', 'italic', 'underline', 'strike'], // toggled buttons
				[{ 'header': 1 }, { 'header': 2 }], // custom button values
				[{ 'color': [] }, { 'background': [] }], // dropdown with defaults from theme
				[{ 'script': 'sub' }, { 'script': 'super' }], // superscript/subscript
				['blockquote', 'code-block'],
				['link', 'image', 'video', 'formula'],
				['clean'] // remove formatting button
			],
			history: {
				delay: 2000,
				maxStack: 500,
				userOnly: true
			},
			imageDrop: true,
			imageResize: {
				modules: ['Resize', 'DisplaySize', 'Toolbar']
			}
		},
		placeholder: 'Feedback',
		theme: 'snow'
	},
	FORUM_TOPIC: {
		modules: {
			syntax: true,
			toolbar: [
				['bold', 'italic', 'underline'], // toggled buttons
				[{ 'header': 1 }, { 'header': 2 }], // custom button values
				[{ 'color': [] }, { 'background': [] }], // dropdown with defaults from theme
				[{ 'align': '' }, { 'align': 'center' }, { 'align': 'right' }, { 'align': 'justify' }],
				[{ 'script': 'sub' }, { 'script': 'super' }], // superscript/subscript
				[{ 'list': 'ordered' }, { 'list': 'bullet' }],
				[{ 'indent': '-1' }, { 'indent': '+1' }], // outdent/indent
				['blockquote', 'code-block'],
				['link', 'image', 'video', 'formula'],
				['clean'] // remove formatting button
			],
			history: {
				delay: 2000,
				maxStack: 500,
				userOnly: true
			},
			imageDrop: true,
			imageResize: {
				modules: ['Resize', 'DisplaySize', 'Toolbar']
			}
		},
		theme: 'snow'
	},
	FORUM_TOPIC_COMMENT: {
		modules: {
			syntax: true,
			toolbar: [
				[{ 'header': 2 }, { 'header': 2 }], // custom button values
				['bold', 'italic', 'underline'], // toggled buttons
				[{ 'color': [] }, { 'background': [] }], // dropdown with defaults from theme
				[{ 'script': 'sub' }, { 'script': 'super' }], // superscript/subscript
				[{ 'list': 'ordered' }, { 'list': 'bullet' }],
				[{ 'indent': '-1' }, { 'indent': '+1' }], // outdent/indent
				['blockquote', 'code-block'],
				['link', 'image', 'video', 'formula'],
				['clean'] // remove formatting button
			],
			history: {
				delay: 2000,
				maxStack: 500,
				userOnly: true
			},
			imageDrop: true,
			imageResize: {
				modules: ['Resize', 'DisplaySize', 'Toolbar']
			}
		},
		theme: 'snow'
	},
	MESSAGE: {
		modules : {
			syntax : true,
			toolbar: [
				[{ 'font': [] }],
				['bold', 'italic', 'underline'], // toggled buttons
				[{ 'color': [] }, { 'background': [] }], // dropdown with defaults from theme
				[{ 'script': 'sub' }, { 'script': 'super' }], // superscript/subscript
				[{ 'list': 'ordered' }, { 'list': 'bullet' }],
				['code-block'],
				['link', 'image', 'video', 'formula'],
				['emoji'],
				['clean'] // remove formatting button
			],
			history : {
				delay : 2000,
				maxStack : 500,
				userOnly : true
			},
			imageDrop : true,
			imageResize : {
				modules : [ 'Resize', 'DisplaySize', 'Toolbar' ]
			},
			'emoji-toolbar' : true
		},
		theme : 'snow'
	},
	TICKET: {
		modules : {
			toolbar: [
				['link', 'image', 'video'],
				['clean']
			],
			history : {
				delay : 2000,
				maxStack : 500,
				userOnly : true
			},
			imageDrop : true,
			imageResize : {
				modules : [ 'Resize', 'DisplaySize', 'Toolbar' ]
			}
		},
		theme : 'snow'
	},
	READ_ONLY: {
		modules: {
			syntax: true,
			toolbar: false
		},
		readOnly: true,
		theme: 'snow'
	}
}

class QuillUtils {

	static setContent(editor, html) {
		editor.setContents([]);
		editor.clipboard.dangerouslyPasteHTML(0, html);
	}

	static removeToolbar(editor) {
		let toolbar = editor.getModule('toolbar');
		$(toolbar.container).remove(0);
	}
}
