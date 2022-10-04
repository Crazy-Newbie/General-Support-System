<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style data-merge-styles="true"></style>
<meta charset="utf-8">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
<meta name="author" content="DZL">
<meta name="keywords" content="badaklng, ptbadak, lhkpn">
<title><tiles:insertAttribute name="title" /></title>
<spring:url value="/resources/css2/gfonts.inter.css" var="url" />
<link href="${url }" rel="stylesheet">
<spring:url value="/resources/css2/adminkit.light.css" var="url" />
<link href="${url }" rel="stylesheet">
<spring:url value="/resources/css2/animate.min.css" var="url" />
<link href="${url }" rel="stylesheet">
<spring:url value="/resources/css2/fontawesome.all.min.css" var="url" />
<link href="${url }" rel="stylesheet">
<spring:url value="/resources/css2/custom.css" var="url" />
<link href="${url }" rel="stylesheet">
<style>
body {
	opacity: 0;
}
</style>
</head>
<body data-theme="colored" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
	<main class="d-flex w-100 h-100">
		<div class="container d-flex flex-column">
			<tiles:insertAttribute name="message" />
			<tiles:insertAttribute name="body" />
		</div>
	</main>
	<spring:url value="/resources/js2/adminkit.js" var="url" />
	<script src="${url }"></script>
</body>
</html>