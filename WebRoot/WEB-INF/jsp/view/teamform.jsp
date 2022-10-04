<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="row">
	<div class="col-md-6">
		<div class="card card-block">
			<div class="card-body">
				<form:form action="form.do" autocomplete="false" method="POST"
					commandName="TeamForm" cssClass="row">
					<form:hidden path="action" />
					<div class="mb-3">
						<form:label path="teamId" for="teamId">Team ID :</form:label>
						<c:if test="${BaseForm.action eq 'CREATE' }">
							<form:input path="teamId" cssClass="form-control"
								required="required" placeholder="Team ID" />
							<span class="text-danger"><form:errors path="teamId" /> </span>
						</c:if>
						<c:if test="${BaseForm.action eq 'UPDATE' }">
							<form:input path="teamId" cssClass="form-control"
								placeholder="Team ID" readonly="true" />
						</c:if>
					</div>
					<div class="mb-3">
						<form:label path="teamName" for="teamName">Team Name :</form:label>
						<form:input path="teamName" cssClass="form-control"
							placeholder="Team Name" />
						<span class="text-danger"><form:errors path="teamName" />
						</span>
					</div>
					<div class="mb-3">
						<button type="SUBMIT" title="${BaseForm.action }"
							class="btn btn-success">
							<span class="fas fa-check"></span> ${BaseForm.action }
						</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	/*$(document).ready(function() {
		if ($("#action").val() == 'UPDATE')
			$("#groupId").attr("readonly", "readonly");
	})*/
</script>