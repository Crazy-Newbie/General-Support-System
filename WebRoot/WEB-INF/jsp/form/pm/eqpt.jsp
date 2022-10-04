<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/lookup/location.do" var="url" />
<script type="text/javascript">
	function openLookup() {
		windowLookup("${url}", 640, 480);
	}

	function receiveCallback(obj) {
		if (obj.form == 'location') {
			$("#locationId").val(obj.locId);
			$("#locationName").val(obj.locDesc);
		}
	}
</script>
<div class="row">
	<div class="col-md-8 col-sm-10">
		<div class="card card-block shadow border-top-primary">
			<div class="card-header">
				<spring:url value="/pm/eqpt/list.do" var="list" />
				<a href="${list }" class="btn btn-outline-secondary"><span
					class="fas fa-chevron-left"></span> EQUIPMENT LIST</a>
			</div>
			<div class="card-body">
				<form:form action="form.do" autocomplete="false" method="POST"
					commandName="EqptForm">
					<form:hidden path="action" />
					<form:hidden path="eqptId" />
					<form:hidden path="locationId" />
					<div class="mb-3 row">
						<form:label path="eqptType" for="eqptType"
							cssClass="col-md-5 col-form-label">Equipment Type :</form:label>
						<div class="col-md-7">
							<c:if test="${BaseForm.action eq 'CREATE' }">
								<form:select path="eqptType" cssClass="form-select"
									required="required">
									<form:option value="">Select Equipt. Type...</form:option>
									<form:options items="${type }" itemLabel="typeDesc"
										itemValue="typeId" />
								</form:select>
								<span class="text-danger"><form:errors path="eqptType" />
								</span>
							</c:if>
							<c:if test="${BaseForm.action eq 'UPDATE' }">
								<form:hidden path="eqptType" />
								<form:select path="eqptType" cssClass="form-select"
									disabled="true">
									<form:options items="${type }" itemLabel="typeDesc"
										itemValue="typeId" />
								</form:select>
							</c:if>
						</div>
					</div>
					<div class="mb-3 row">
						<form:hidden path="locationId" />
						<form:label path="locationName" for="locationName"
							cssClass="col-md-5 col-form-label">Location :</form:label>
						<div class="col-md-7">
							<div class="input-group" onclick="openLookup()">
								<form:input path="locationName" cssClass="form-control"
									placeholder="Loc. Group" required="required"
									onkeydown="return false" autocomplete="off" />
								<button type="button" class="btn btn-primary">
									<span class="fas fa-search"></span>
								</button>
							</div>
							<span class="text-danger"><form:errors path="locationName" />
							</span>
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="locationDetail" for="locationDetail"
							cssClass="col-md-5 col-form-label">Location Detail :</form:label>
						<div class="col-md-7">

							<form:input path="locationDetail" cssClass="form-control"
								placeholder="Loc. Detail" required="required" />

							<span class="text-danger"><form:errors
									path="locationDetail" /> </span>
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="model" for="model"
							cssClass="col-md-5 col-form-label">Model :</form:label>
						<div class="col-md-7">

							<form:input path="model" cssClass="form-control"
								placeholder="Model... (if exist)" />

							<span class="text-danger"><form:errors path="model" /> </span>
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="serialNo" for="serialNo"
							cssClass="col-md-5 col-form-label">Serial No :</form:label>
						<div class="col-md-7">
							<form:input path="serialNo" cssClass="form-control"
								placeholder="Serial No... (if exist)" />
							<span class="text-danger"><form:errors path="serialNo" />
							</span>
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="eqptDetail" for="eqptDetail"
							cssClass="col-md-5 col-form-label">Equipment Detail :</form:label>
						<div class="col-md-7">
							<form:textarea path="eqptDetail" rows="3"
								placeholder="Equipment Detail..." cssClass="form-control" />
							<span class="text-danger"><form:errors path="eqptDetail" />
							</span>
						</div>
					</div>
					<div class="mb-3 row">

						<div class="offset-md-5 col-md-7">
							<button type="submit" class="btn btn-primary"
								title="${BaseForm.action }">
								<span class="fas fa-check"></span> ${BaseForm.action }
							</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>