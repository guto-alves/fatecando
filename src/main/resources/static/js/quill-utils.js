hljs.configure({
	tabReplace: '    ',
	languages: ['java']
});
hljs.highlightAll();

const EditorOptions = {
	TOPIC: {
		modules: {
			syntax: true,
			toolbar: [
				[{ 'font': [] }],
				[{ 'size': ['small', false, 'large', 'huge'] }], // custom dropdown
				['bold', 'italic', 'underline', 'strike'], // toggled buttons
				[{ 'header': 1 }, { 'header': 2 }], // custom button values
				[{ 'header': [1, 2, 3, 4, 5, 6, false] }],
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
			}
		},
		theme: 'snow'
	},
	QUESTION: {
		modules: {
			syntax: true,
			toolbar: [
				[{ 'font': [] }],
				[{ 'size': ['small', false, 'large', 'huge'] }], // custom dropdown
				['bold', 'italic', 'underline', 'strike'], // toggled buttons
				[{ 'header': 1 }, { 'header': 2 }], // custom button values
				[{ 'header': [1, 2, 3, 4, 5, 6, false] }],
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
			}
		},
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
			}
		},
		theme: 'snow'
	},
	FORUM_TOPIC_COMMENT: { 
		modules: {
			syntax: true,
			toolbar: [
				['bold', 'italic', 'underline'], // toggled buttons
				[{ 'header': 1 }, { 'header': 2 }, { 'header': 2 }], // custom button values
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
			}
		},
		theme: 'snow'
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
		var toolbar = editor.getModule('toolbar');
		$(toolbar.container).remove(0);
	}
}
