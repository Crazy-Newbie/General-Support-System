<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/dashboard.do" var="homeURL" />
<spring:url value="${BaseForm.redirectURL }" var="redirURL" />
<script type="text/javascript">
	$("document").ready(function() {
		if (window.opener && !window.opener.closed) {
			try {
				window.opener.reloadTable();
			} catch (e) {
			}
		}

		setTimeout(function() {
			if (window.opener && !window.opener.closed) {
				window.close();
			} else {
				var home = '${homeURL}';
				var redir = '${redirURL}';
				window.location = redir !== '' ? redir : home;
			}
		}, 3 * 1000);
	});
</script>
<div
	class="alert alert-coloured alert-info fade show animate__animated animate__flash"
	role="alert">
	<div class="alert-icon">
		<span
			class="fas fa-info-circle animate__animated animate__flash animate__delay-1s animate__repeat-3 animate__slower"></span>
	</div>
	<div class="alert-message">
		<strong class="animate__fadeInRight animate__animated text-info">This
			pop-up will be dismissed after 3 seconds</strong>
	</div>
</div>