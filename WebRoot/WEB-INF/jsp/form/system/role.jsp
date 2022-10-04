<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="row">
	<div class="col-md-6">
		<div class="card card-block shadow border-top-success">
			<div class="card-body">
				<form:form action="form.do" autocomplete="false" method="POST" commandName="RoleForm">
					<form:hidden path="action" />
					<div class="mb-3">
						<form:label path="roleId" for="roleId" cssClass="form-label">Role ID :</form:label>
						<c:if test="${BaseForm.action eq 'CREATE' }">
							<form:input path="roleId" cssClass="form-control" required="required" placeholder="Role ID" />
						</c:if>
						<c:if test="${BaseForm.action eq 'UPDATE' }">
							<form:input path="roleId" cssClass="form-control" readonly="true" placeholder="Role ID" />
						</c:if>
						<span class="text-danger"><form:errors path="roleId" /> </span>
					</div>
					<div class="mb-3">
						<form:label path="roleName" for="roleName" cssClass="form-label">Role Name :</form:label>
						<form:input path="roleName" cssClass="form-control" placeholder="Name" />
						<span class="text-danger"><form:errors path="roleName" /> </span>
					</div>
					<button type="submit" title="${BaseForm.action }" class="btn btn-success">
						<span class="fas fa-check"></span> ${BaseForm.action }
					</button>
				</form:form>
			</div>
		</div>
	</div>
</div>
