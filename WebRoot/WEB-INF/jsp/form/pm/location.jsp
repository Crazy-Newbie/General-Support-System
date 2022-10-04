<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/lookup/location.do" var="url" />
<script type="text/javascript">
	function openLookup() {
		windowLookup("${url}", 640, 480);
	}

	function clearParent() {
		$("#locationParent").val("");
		$("#locationParentDesc").val("");
	}

	function receiveCallback(obj) {
		if (obj.form == 'location') {
			$("#locationParent").val(obj.locId);
			$("#locationParentDesc").val(obj.locDesc);
		}
	}
</script>
<div class="row">
	<div class="col-md-6">
		<div class="card card-block shadow border-top-primary">
			<div class="card-body">
				<form:form action="form.do" autocomplete="false" method="POST"
					commandName="LocationForm">
					<form:hidden path="action" />
					<form:hidden path="locationId" />
					<%-- <div class="mb-3 row">
						<form:label path="locationId" for="locationId" cssClass="col-md-5 col-form-label">Location Id :</form:label>
						<div class="col-md-7">
							<form:input path="locationId" cssClass="form-control" required="required" placeholder="Location Id" />
							<span class="text-danger"><form:errors path="locationParent" /> </span>
						</div>
					</div> --%>
					<div class="mb-3 row">
						<form:label path="locationDesc" for="locationDesc"
							cssClass="col-md-5 col-form-label">Location Description :</form:label>
						<div class="col-md-7">
							<form:input path="locationDesc" cssClass="form-control"
								required="required" placeholder="Loc. Description" />
							<span class="text-danger"><form:errors path="locationDesc" />
							</span>
						</div>
					</div>
					<div class="mb-3 row">
						<form:hidden path="locationParent" />
						<form:label path="locationParentDesc" for="locationParent"
							cssClass="col-md-5 col-form-label">location Parent :</form:label>
						<div class="col-md-7">
							<div class="input-group">
								<form:input path="locationParentDesc" cssClass="form-control"
									placeholder="Loc. Parent (leave blank if top)" readonly="true" />
								<button type="button" onclick="openLookup()"
									class="btn btn-primary">
									<span class="fas fa-search"></span>
								</button>
								<button type="button" onclick="clearParent()"
									class="btn btn-outline-warning">
									<span class="fas fa-times"></span>
								</button>
							</div>
							<span class="text-danger"><form:errors
									path="locationParentDesc" /> </span>
						</div>
					</div>
					<%-- <div class="mb-3 row">
						<form:hidden path="locationId" />
						<form:label path="locationParent" for="locationParent" cssClass="col-md-5 col-form-label">Location Parent :</form:label>
						<div class="col-md-7">
							<div class=" input-group">
								<form:input path="locationParent" cssClass="form-control" placeholder="Choose parent..." onkeydown="return false" />
								<button type="button" class="btn btn-outline-info" onclick="openLookup()">
									<i class="fas fa-fw fa-search"></i>
								</button>
								<button type="button" class="btn btn-warning" onclick="resetParent()">
									<i class="fas fa-fw fa-times"></i>
								</button>
							</div>
						</div>
						<span class="text-danger"><form:errors path="locationParent" /> </span>
					</div> --%>
					<div class="mb-3 row">
						<label class="col-md-5 col-form-label"></label>
						<div class="col-md-7">
							<form:button value="SUBMIT" class="btn btn-outline-primary">${LocationForm.action }</form:button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>