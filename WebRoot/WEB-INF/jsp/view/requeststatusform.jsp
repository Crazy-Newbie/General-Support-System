<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="row">
	<div class="col-md-6">
		<div class="card card-block">
			<div class="card-body">
				<form:form action="statusform.do" autocomplete="false" method="POST"
					commandName="RequestStatusForm" cssClass="row">
					<form:hidden path="action" />
					<div class="mb-3">
						<form:label path="reqId" for="reqId">Request ID</form:label>
						<form:input path="reqId" cssClass="form-control" readonly="true" />
						<span class="text-danger"><form:errors path="reqId" /> </span>
					</div>
					<div class="mb-3">
						<form:label path="status" for="status">Status</form:label>
						<form:select path="status" class="form-select">
							<c:forEach items="${CatalogOrdered.codes['REQ_STATUS'] }"
								var="item">
								<c:if test="${item.tattr1 eq 1 }">
									<form:option value="${item.id.tcode }">${item.tval }</form:option>
								</c:if>
							</c:forEach>
						</form:select>
						<span class="text-danger"><form:errors path="Status" /> </span>
					</div>
					<div class="mb-3">
						<form:label path="statusNote" for="statusNote">Status Note :</form:label>
						<form:textarea path="statusNote" cssClass="form-control"
							placeholder="Status Note" />
						<span class="text-danger"><form:errors path="statusNote" />
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