<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-12">
		<div class="card border-top-primary shadow">
			<div class="card-body row">
				<div class="col align-self-center">
					<div id="qrcode"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<spring:url value="/resources/js2/qrcode.min.js" var="jsUrl" />
<script src="${jsUrl }"></script>
<script type="text/javascript">
	new QRCode(document.getElementById("qrcode"), "${link}");
</script>