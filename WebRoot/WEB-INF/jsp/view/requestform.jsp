<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="row">
	<div class="col-md-6">
		<div class="card card-block">
			<div class="card-body">
				<form:form action="form.do" autocomplete="false" method="POST"
					commandName="RequestForm" cssClass="row">
					<form:hidden path="action" />
					<form:hidden path="reqId" />
					<div class="mb-3">
						<form:label path="teamId" for="teamId">Team</form:label>
						<form:select path="teamId" class="form-select">
							<c:if test="${empty RequestForm.teamId }">
								<form:option value="">-- NOT ASSIGNED --</form:option>
							</c:if>
							<c:forEach items="${teams }" var="item">
								<form:option value="${item.teamId }">${item.teamName }</form:option>
							</c:forEach>
						</form:select>
						<span class="text-danger"><form:errors path="teamId" /> </span>
					</div>
					<div class="mb-3">
						<c:if test="${BaseForm.action eq 'CREATE' }">
							<form:label path="reqBy" for="reqBy">Request By :</form:label>
							<form:input path="reqBy" cssClass="form-control"
								placeholder="Request By" required="required" />
							<span class="text-danger"><form:errors path="reqBy" /> </span>
						</c:if>
						<c:if test="${BaseForm.action eq 'UPDATE' }">
							<form:label path="reqBy" for="reqBy">Request By :</form:label>
							<form:input path="reqBy" cssClass="form-control"
								placeholder="Request By" readonly="true" />
							<span class="text-danger"><form:errors path="reqBy" /> </span>
						</c:if>
					</div>
					<div class="mb-3">
						<c:if test="${BaseForm.action eq 'CREATE' }">
							<form:label path="reqDesc" for="reqDesc">Description :</form:label>
							<form:textarea path="reqDesc" cssClass="form-control"
								placeholder="Description" required="required" />
							<span class="text-danger"><form:errors path="reqDesc" />
							</span>
						</c:if>
						<c:if test="${BaseForm.action eq 'UPDATE' }">
							<form:label path="reqDesc" for="reqDesc">Description :</form:label>
							<form:textarea path="reqDesc" cssClass="form-control"
								placeholder="Description" readonly="true" />
							<span class="text-danger"><form:errors path="reqDesc" />
							</span>
						</c:if>
					</div>
					<div class="mb-3">
						<c:if test="${BaseForm.action eq 'CREATE' }">
							<form:label path="location" for="location">Location :</form:label>
							<form:input path="location" cssClass="form-control"
								placeholder="Location" />
							<span class="text-danger"><form:errors path="location" />
							</span>
						</c:if>
						<c:if test="${BaseForm.action eq 'UPDATE' }">
							<form:label path="location" for="location">Location :</form:label>
							<form:input path="location" cssClass="form-control"
								placeholder="Location" readonly="true" />
							<span class="text-danger"><form:errors path="location" />
							</span>
						</c:if>
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