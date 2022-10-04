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
<spring:url value="/resources/img/bulb-icon.png" var="url" />
<link rel="icon" href="${url }">
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
<spring:url value="/resources/js2/sweetalert2.all.min.js" var="jsUrl" />
<script src="${jsUrl }"></script>
<spring:url value="/resources/js2/dayjs.all.min.js" var="jsUrl" />
<script src="${jsUrl }"></script>
<spring:url value="/resources/js2/notyf.min.js" var="jsUrl" />
<script src="${jsUrl }"></script>
</head>
<body id="page-top">

	<div id="wrapper">
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<div class="container-fluid">
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 mt-3 text-gray-800 fadeInRight animated faster">${FormPair.codes[BaseForm.formCode]}</h1>
					</div>
					<div class="row">
						<tiles:insertAttribute name="message" />
					</div>
					<tiles:insertAttribute name="body" />
				</div>
			</div>
		</div>
	</div>
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Custom scripts for all pages-->
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
</body>
</html>