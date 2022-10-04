<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="row">
	<div class="col-md-6">
		<div class="card card-block">
			<div class="card-body">
				<form:form action="form.do" autocomplete="false" method="POST" commandName="GroupForm" cssClass="row">
					<form:hidden path="action" />
					<div class="mb-3">
						<form:label path="groupId" for="groupId">Group ID :</form:label>
						<form:input path="groupId" cssClass="form-control" required="required" placeholder="Group ID" />
						<span class="text-danger"><form:errors path="groupId" /> </span>
					</div>
					<div class="mb-3">
						<form:label path="groupName" for="groupName">Group Name :</form:label>
						<form:input path="groupName" cssClass="form-control" placeholder="Name" />
						<span class="text-danger"><form:errors path="groupName" /> </span>
					</div>
					<div class="mb-3">
						<button type="SUBMIT" title="${BaseForm.action }" class="btn btn-success">
							<span class="fas fa-check"></span> ${BaseForm.action }
						</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		if ($("#action").val() == 'UPDATE')
			$("#groupId").attr("readonly", "readonly");
	})
</script>