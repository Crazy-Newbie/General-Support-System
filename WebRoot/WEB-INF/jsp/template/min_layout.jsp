<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title><tiles:insertAttribute name="title" /></title>

<spring:url value="/resources/img/ptb_logo_icon.png" var="url" />
<link rel="icon" href="${url }">
<spring:url value="/resources/css/all.min.css" var="url" />
<link href="${url}" rel="stylesheet" type="text/css">
<spring:url value="/resources/css/nunito.css" var="url" />
<link href="${url}" rel="stylesheet" type="text/css">

<spring:url value="/resources/css/sb-admin-2.min.css" var="url" />
<link href="${url}" rel="stylesheet" type="text/css">
<spring:url value="/resources/css/animate.css" var="url" />
<link href="${url}" rel="stylesheet" type="text/css">


<spring:url value="/resources/css/datatables.min.css" var="url" />
<link href="${url}" rel="stylesheet" type="text/css">
<spring:url value="/resources/css/bootstrap-toggle.min.css" var="url" />
<link href="${url}" rel="stylesheet" type="text/css">
<spring:url value="/resources/css/datetimepicker.css" var="url" />
<link href="${url}" rel="stylesheet" type="text/css">
<spring:url value="/resources/css/waves.min.css" var="url" />
<link href="${url}" rel="stylesheet" type="text/css">
<spring:url value="/resources/css/custom.css" var="url" />
<link href="${url}" rel="stylesheet" type="text/css">

<!-- Bootstrap core JavaScript-->
<spring:url value="/resources/js/jquery.min.js" var="jsUrl" />
<script src="${jsUrl }"></script>
<spring:url value="/resources/js/bootstrap.bundle.min.js" var="jsUrl" />
<script src="${jsUrl }"></script>

<!-- Core plugin JavaScript-->
<spring:url value="/resources/js/moment.js" var="jsUrl" />
<script src="${jsUrl }"></script>
<spring:url value="/resources/js/datetimepicker.min.js" var="jsUrl" />
<script src="${jsUrl }"></script>
<spring:url value="/resources/js/jquery.easing.min.js" var="jsUrl" />
<script src="${jsUrl }"></script>
<spring:url value="/resources/js/bootbox.min.js" var="jsUrl" />
<script src="${jsUrl }"></script>
<spring:url value="/resources/js/bootstrap-toggle.min.js" var="jsUrl" />
<script src="${jsUrl }"></script>
</head>
<body id="page-top">

	<div id="wrapper">
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<div class="container-fluid">
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4 mt-2">
						<h1 class="h3 mb-0 text-gray-800 fadeInRight animated faster">${FormPair.codes[BaseForm.formCode]}</h1>
					</div>
					<div class="row">
						<tiles:insertAttribute name="message" />
					</div>
					<tiles:insertAttribute name="body" />
				</div>
			</div>
		</div>
	</div>
	<spring:url value="/resources/js/sb-admin-2.min.js" var="jsUrl" />
	<script src="${jsUrl }"></script>
	<spring:url value="/resources/js/datatables.min.js" var="jsUrl" />
	<script src="${jsUrl }"></script>
	<spring:url value="/resources/js/waves.min.js" var="jsUrl" />
	<script src="${jsUrl }"></script>
	<spring:url value="/resources/js/badak-app.js" var="jsUrl" />
	<script src="${jsUrl }"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".btn:not(.toggle, .toggle-on, .toggle-off, .toggle-handle)").addClass('btn-waves');
			Waves.attach(".btn-waves", [ 'waves-light' ]);
			$(".btn:not(.toggle, .toggle-on, .toggle-off, .toggle-handle)").removeClass('btn-waves');
			Waves.init();
		})
		$.fn.dataTable.ext.errMode = 'none';
		$(".table").on("error.dt", function(e, settings, techNote, message) {
			bootbox.confirm("Error on loading data from Server. Do you want to reload page?", function(e) {
				if (e)
					window.location.reload();
			});
		});
	</script>
</body>
</html>