function windowWithoutToolbar(urlStr, width, height) {
	var newwindow = window
		.open(
			urlStr,
			'window0',
			'location=no,status=yes,scrollbars=yes,menubar=no,toolbar=no,directories=no,resizable=no,width='
			+ width + ',height=' + height);
	newwindow.opener = self;
	if (newwindow.height != height)
		newwindow.height = height;

	if (newwindow.width != width)
		newwindow.width = width;

	if (window.focus) {
		newwindow.focus();
	}

}

function windowLookup(urlStr, width, height) {
	var newwindow = window
		.open(
			urlStr,
			'window99',
			'location=no,status=yes,scrollbars=yes,menubar=no,toolbar=no,directories=no,resizable=no,width='
			+ width + ',height=' + height);
	newwindow.opener = self;
	if (newwindow.height != height)
		newwindow.height = height;

	if (newwindow.width != width)
		newwindow.width = width;

	if (window.focus) {
		newwindow.focus();
	}

}

var pleaseWait;
pleaseWait = pleaseWait
|| (function() {
	var dialog;
	return {
		show : function() {
			dialog = bootbox.dialog({
				message : '<p class="h2 text-center"><span class="fas fa-spinner fa-spin"></span> Please wait for a while...</p>',
				closeButton : false
			});
		},
		hide : function() {
			dialog.modal('hide');
		}
	}
})();

function getAbsolutePath() {
	var loc = window.location;
	return loc.pathname;
}

/*var pleaseWait;
pleaseWait = pleaseWait
|| (function() {

	var pleaseWaitDiv = $('<div class="modal fade" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h1>Please Wait...</h1></div></div></div></div>');

	return {
		show : function() {
			pleaseWaitDiv.modal();
		},
		hide : function() {
			pleaseWaitDiv.modal('hide');
		},
	};
})();*/

function isEmpty(str) {
	return (!str || str.trim().length === 0);
}

function currency(nStr) {
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1 + x2;
}

