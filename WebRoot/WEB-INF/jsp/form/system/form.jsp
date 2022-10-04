<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function submitrole() {
		alert($("#access").val() + " " + $("#role").val());
	}
</script>
<div class="row">
	<div class="col-lg-4 col-md-6">
		<div class="card border-top-primary shadow">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-12">
						<form:form action="form.do" autocomplete="false" method="POST" commandName="FormForm">
							<form:hidden path="action" />
							<div class="mb-3">
								<form:label path="formNo" for="formNo">Form No :</form:label>
								<form:input path="formNo" cssClass="form-control" required="required" placeholder="Form No / Code" />
							</div>
							<div class="mb-3">
								<form:label path="formDesc" for="formDesc">Form Desc :</form:label>
								<form:input path="formDesc" placeholder="Description" cssClass="form-control" required="required" />
							</div>
							<div class="mb-3">
								<button type="submit" class="btn btn-success">${FormForm.action }</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		if ($("#action").val() == 'UPDATE')
			$("#formNo").attr("readonly", "readonly");
	})
</script>