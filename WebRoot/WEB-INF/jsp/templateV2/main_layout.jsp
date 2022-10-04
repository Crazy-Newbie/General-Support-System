<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style data-merge-styles="true"></style>
<meta charset="utf-8">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="DZL">
<meta name="keywords" content="badaklng, ptbadak, lhkpn">
<!-- <link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="shortcut icon" href="img/icons/icon-48x48.png">-->
<title><tiles:insertAttribute name="title" /></title>
<spring:url value="/resources/css2/gfonts.inter.css" var="url" />
<link href="${url }" rel="stylesheet">
<spring:url value="/resources/css2/gfonts.nunito.css" var="url" />
<link href="${url }" rel="stylesheet">
<%-- <spring:url value="/resources/css2/bootstrap.min.css" var="url" />
<link href="${url }" rel="stylesheet"> --%>
<spring:url value="/resources/css2/adminkit.light.css" var="url" />
<link href="${url }" rel="stylesheet">
<spring:url value="/resources/css2/animate.min.css" var="url" />
<link href="${url }" rel="stylesheet">
<spring:url value="/resources/css2/fontawesome.all.min.css" var="url" />
<link href="${url }" rel="stylesheet">
<spring:url value="/resources/css2/datatables.min.css" var="url" />
<link href="${url }" rel="stylesheet">
<spring:url value="/resources/css2/sweetalert2.min.css" var="url" />
<link href="${url }" rel="stylesheet">
<spring:url value="/resources/css2/flatpickr.min.css" var="url" />
<link href="${url }" rel="stylesheet">
<spring:url value="/resources/css2/flatpickr.matblue.css" var="url" />
<link href="${url }" rel="stylesheet">
<spring:url value="/resources/css2/select2.min.css" var="url" />
<link href="${url }" rel="stylesheet">
<spring:url value="/resources/css2/notyf.min.css" var="url" />
<link href="${url }" rel="stylesheet">
<spring:url value="/resources/css2/custom.css" var="url" />
<link href="${url }" rel="stylesheet">
<!-- Javascript -->
<spring:url value="/resources/js2/jquery.min.js" var="jsUrl" />
<script src="${jsUrl }"></script>
<spring:url value="/resources/js2/datatables.min.js" var="jsUrl" />
<script src="${jsUrl }"></script>
<spring:url value="/resources/js2/sweetalert2.all.min.js" var="jsUrl" />
<script src="${jsUrl }"></script>
<spring:url value="/resources/js2/dayjs.all.min.js" var="jsUrl" />
<script src="${jsUrl }"></script>
<spring:url value="/resources/js2/notyf.min.js" var="jsUrl" />
<script src="${jsUrl }"></script>
<style>
body {
	opacity: 0;
}
</style>
<script type="text/javascript">
	// DayJS Timezone init
	dayjs.extend(window.dayjs_plugin_utc);
	dayjs.extend(window.dayjs_plugin_timezone);

	$.extend(true, $.fn.dataTable.defaults, {
		autoWidth : false,
		responsive : true,
		processing : true,
		language : {
			processing : '<span class="fas fa-sync fa-spin text-success"></span> Loading data...'
		}
	});
</script>
</head>
<body data-theme="colored" data-layout="fluid"
	data-sidebar-position="left" data-sidebar-layout="default">
	<div class="wrapper">
		<tiles:insertAttribute name="sidebar" />
		<div class="main">
			<tiles:insertAttribute name="navbar" />
			<main class="content">
				<div class="container-fluid p-0">
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1
							class="mb-0 text-gray-800 animate__fadeInRight animate__animated animate__faster">${FormPair.codes[BaseForm.formCode]}</h1>
					</div>
					<tiles:insertAttribute name="message" />
					<tiles:insertAttribute name="body" />
				</div>
			</main>
		</div>
	</div>
	<%-- 	<spring:url value="/resources/js2/bootstrap.min.js" var="url" />
	<script src="${url }"></script> --%>
	<spring:url value="/resources/js2/adminkit.js" var="url" />
	<script src="${url }"></script>
	<spring:url value="/resources/js2/flatpickr.min.js" var="url" />
	<script src="${url }"></script>
	<spring:url value="/resources/js2/select2.min.js" var="url" />
	<script src="${url }"></script>
	<spring:url value="/resources/js2/custom.js" var="jsUrl" />
	<script src="${jsUrl }"></script>
	<spring:url value="/resources/js/badak-app.js" var="jsUrl" />
	<script src="${jsUrl }"></script>
	<script type="text/javascript">
		// Sidebar menu
		var selected = $('a[href="' + window.location.pathname + '"]');
		selected.parent().addClass('active');
		selected.parents('ul.sidebar-dropdown').addClass('show');

		// DataTable Global Modification
		$.fn.dataTable.ext.errMode = 'none';
		$(".table").on("error.dt", function(e, settings, techNote, message) {
			console.log("Error : " + message);
			Swal.fire({
				title : 'Data retrieval failed',
				text : 'Do you want to reload page?',
				showCancelButton : true
			}).then(function(resp) {
				if (resp.isConfirmed)
					window.location.reload();
			})
		});
	</script>
</body>
</html>