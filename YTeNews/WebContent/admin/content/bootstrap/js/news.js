/**
 * Xử lý khi click vào button delete
 * 
 * @param newsId 
 * 
 */
function comfirmDeleteAdmin(newsId) {
	if (confirm("Are you sure??????????")) {
		var deleForm = document.createElement("form");
		deleForm.method = "POST";
		deleForm.action = "deletenews.do";
		// Create an input
		var mapInput = document.createElement("input");
		mapInput.type = "hidden";
		mapInput.name = "newsid";
		mapInput.value = newsId;
		// Add the input to the form
		deleForm.appendChild(mapInput);
		// submit form
		document.body.appendChild(deleForm);
		deleForm.submit();

	}
}

function comfirmDeleteContact(contactId) {
	if (confirm("Are you sure??????????")) {
		var deleForm = document.createElement("form");
		deleForm.method = "POST";
		deleForm.action = "deletecontact.do";
		// Create an input
		var mapInput = document.createElement("input");
		mapInput.type = "hidden";
		mapInput.name = "contactid";
		mapInput.value = contactId;
		// Add the input to the form
		deleForm.appendChild(mapInput);
		// submit form
		document.body.appendChild(deleForm);
		deleForm.submit();

	}
}
function comfirmDeleteAu(newsId) {
	if (confirm("Are you sure??????????")) {
		var deleForm = document.createElement("form");
		deleForm.method = "POST";
		deleForm.action = "deletenews.at";
		// Create an input
		var mapInput = document.createElement("input");
		mapInput.type = "hidden";
		mapInput.name = "newsid";
		mapInput.value = newsId;
		// Add the input to the form
		deleForm.appendChild(mapInput);
		// submit form
		document.body.appendChild(deleForm);
		deleForm.submit();
	}
}

