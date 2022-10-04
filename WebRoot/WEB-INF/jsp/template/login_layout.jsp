<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<spring:url value="/resources/css/custom.css" var="url" />
<link href="${url}" rel="stylesheet" type="text/css">
</head>
<body class="bg-gradient-fms">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-5 col-lg-6 col-md-7 col-sm-10">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<tiles:insertAttribute name="message" />
							<tiles:insertAttribute name="body" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<spring:url value="/resources/js/jquery.min.js" var="jsUrl" />
	<script src="${jsUrl }"></script>
	<spring:url value="/resources/js/bootstrap.bundle.min.js" var="jsUrl" />
	<script src="${jsUrl }"></script>

	<!-- Core plugin JavaScript-->
	<spring:url value="/resources/js/jquery.easing.min.js" var="jsUrl" />
	<script src="${jsUrl }"></script>

	<!-- Custom scripts for all pages-->
	<spring:url value="/resources/js/sb-admin-2.min.js" var="jsUrl" />
	<script src="${jsUrl }"></script>

</body>
</html>