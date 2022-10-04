<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/lookup/employee.do" var="url" />
<spring:url value="/lookup/organization.do" var="orgurl" />
<script type="text/javascript">
	var email_temp = '';
	function openLookup() {
		windowLookup("${url}", 640, 480);
	}

	function openOrgLookup() {
		windowLookup("${orgurl}", 640, 480);
	}

	function receiveCallback(obj) {
		if (obj.form == "organization") {
			$("#filterValue").val(obj.orgId);
		}
		if (obj.form == "employee") {
			$("#userId").val(obj.employeeId);
			$("#name").val(obj.name);
			$("#email").val(obj.email);
			email_temp = obj.email;
			var str = obj.email.split("@");
			$("#ldapUser").val(str[0]);
			$("#ldapFlag").val(1);
			$("#pwd").removeAttr("required");
			toggleLDAP(1);
		}
	}

	function emailChange(val) {
		var str = val.split("@");
		$("#ldapUser").val(str[0]);
	}

	function toggleLDAP(val) {
		if (val == 1) {
			$("#pwd").removeAttr("required");
			$("#email").attr("required", "required");
			$("#email").val(email_temp);
			$("#email").on("keydown", function() {
				return false;
			});
			$("#passworddiv").slideUp();
			$("#ldapdiv").slideDown();
		} else {
			$("#pwd").attr("required", "required");
			$("#email").removeAttr("required");
			$("#email").off("keydown");
			$("#email").val("");
			$("#passworddiv").slideDown();
			$("#ldapdiv").slideUp();
		}
	}

	function filterChange(val) {
		if (val == 'SEC' || val == 'DEP' || val == 'DIV') {
			$("#filterdiv").slideDown();
			$("#filterValue").attr("required", "required");
		} else {
			$("#filterdiv").slideUp();
			$("#filterValue").removeAttr("required");
		}
	}
</script>
<form:form action="form.do" autocomplete="false" method="POST" commandName="UserForm">
	<form:hidden path="action" />
	<div class="row">
		<div class="col-md-6">
			<div class="card card-block border-top-primary shadow">
				<div class="card-body">
					<div class="mb-3 row">
						<form:label path="userId" for="userId" cssClass="col-md-5 col-form-label">User ID :</form:label>
						<div class="col-md-7">
							<c:if test="${UserForm.action == 'CREATE' }">
								<div class="input-group">
									<form:input path="userId" cssClass="form-control" required="required" placeholder="Employee ID" maxlength="6" />
									<button class="btn btn-info" type="button" onclick="openLookup()">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</c:if>
							<c:if test="${UserForm.action == 'UPDATE' }">
								<form:input path="userId" cssClass="form-control-plaintext" placeholder="Employee ID" maxlength="6" />
							</c:if>
						</div>
						<span class="text-danger"><form:errors path="userId" /></span>
					</div>
					<div class="mb-3 row">
						<form:label path="name" for="name" cssClass="col-md-5 col-form-label">Name :</form:label>
						<div class="col-md-7">
							<form:input path="name" cssClass="form-control" required="required" placeholder="Employee Name" />
						</div>
						<span class="text-danger"><form:errors path="name" /></span>
					</div>
					<%-- <div class="mb-3 row">
						<form:label path="roleId" for="roleId"
							cssClass="col-md-5 col-form-label">Role :</form:label>
						<div class="col-md-7">
							<form:select path="roleId" class="form-control"
								required="required">
								<form:option value="">SELECT ROLE...</form:option>
								<form:options items="${AppRoleList }" itemLabel="roleDesc"
									itemValue="roleId" />
							</form:select>
						</div>
					</div> --%>
					<div class="mb-3 row">
						<form:label path="email" for="email" cssClass="col-md-5 col-form-label">Email :</form:label>
						<div class="col-md-7">
							<form:input path="email" placeholder="Email" cssClass="form-control" autocomplete="off" type="email" onchange="emailChange(value)" />
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="active" for="active" cssClass="col-md-5 col-form-label">Active :</form:label>
						<div class="col-md-7">
							<form:select path="active" class="form-select">
								<form:option value="1">Yes</form:option>
								<form:option value="0">No</form:option>
							</form:select>
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="allowLoginAs" for="allowLoginAs" cssClass="col-md-5 col-form-label">Allow Login as Other User :</form:label>
						<div class="col-md-7">
							<form:select path="allowLoginAs" class="form-select">
								<form:option value="1">Yes</form:option>
								<form:option value="0">No</form:option>
							</form:select>
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="ldapFlag" for="ldapFlag" cssClass="col-md-5 col-form-label">Is LDAP :</form:label>
						<div class="col-md-7">
							<form:select path="ldapFlag" class="form-select" onchange="toggleLDAP(value)">
								<form:option value="1">Yes</form:option>
								<form:option value="0">No</form:option>
							</form:select>
						</div>
					</div>
					<div class="mb-3 row" id="passworddiv">
						<form:label path="pwd" for="pwd" cssClass="col-md-5 col-form-label">Password :</form:label>
						<div class="col-md-7">
							<form:password path="pwd" cssClass="form-control" autocomplete="off" placeholder="Password if this user is not based on LDAP Account" />
							<!-- <small class="form-text text-muted">Never share your
								password to someone / everyone.</small>  -->
							<span class="text-danger"><form:errors path="pwd" /></span>
						</div>
					</div>
					<div class="mb-3 row" id="ldapdiv">
						<form:label path="ldapUser" for="ldapUser" cssClass="col-md-5 col-form-label">LDAP ID :</form:label>
						<div class="col-md-7">
							<div class="input-group">
								<form:input path="ldapUser" cssClass="form-control" readonly="true" />
								<span class="input-group-text">@badaklng.com</span>
							</div>
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="filterType" for="filterType" cssClass="col-md-5 col-form-label">Filter Type :</form:label>
						<div class="col-md-7">
							<form:select path="filterType" cssClass="form-select" required="required" onchange="filterChange(value)">
								<form:option value="">Select Filter...</form:option>
								<form:options items="${CatalogOrdered.codes['ORG_FILTER'] }" itemLabel="tval" itemValue="id.tcode" />
							</form:select>
						</div>
					</div>
					<div id="filterdiv">
						<div class="mb-3 row">
							<form:label path="filterValue" for="filterValue" cssClass="col-md-5 col-form-label">Filter Code :</form:label>
							<div class="col-md-7">
								<div class="input-group">
									<form:input path="filterValue" cssClass="form-control" required="required" placeholder="Filter Org" maxlength="6" />
									<button class="btn btn-secondary" type="button" onclick="openOrgLookup()">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
							<span class="text-danger"><form:errors path="filterValue" /></span>
						</div>
						<div class="mb-3 row">
							<div class="col-md-7 offset-md-5">
								<div class="form-check form-switch">
									<form:checkbox path="includeDescendant" cssClass="form-check-input" />
									<form:label path="includeDescendant" for="includeDescendant" cssClass="form-check-label">Include Descendant</form:label>
								</div>
							</div>
						</div>
						<div class="mb-3 row">
							<%-- <form:label path="includePtb" for="includePtb" cssClass="col-md-5 col-form-label">Include PTB Empl. :</form:label>
							<div class="col-md-7">
								<form:checkbox path="includePtb" data-toggle="toggle" data-on="Yes" data-off="No" data-size="small" data-onstyle="primary" />
							</div> --%>
							<div class="col-md-7 offset-md-5">
								<div class="form-check form-switch">
									<form:checkbox path="includePtb" cssClass="form-check-input" />
									<form:label path="includePtb" for="includePtb" cssClass="form-check-label">Include PTB</form:label>
								</div>
							</div>
						</div>
					</div>
					<div class="mb-3 row">
						<label class="col-md-5 col-form-label"></label>
						<div class="col-md-7">
							<button type="submit" class="btn btn-success">
								<span class="fas fa-check"></span> SUBMIT
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:if test="${UserForm.action eq 'CREATE' }">
			<div class="col-md-6 col-lg-4">
				<div class="card border-top-info shadow">
					<div class="card-body">
						<%-- <form:checkboxes items="${AppRoleList }" path="roles" itemLabel="roleDesc" itemValue="roleId" /> --%>
						<c:forEach items="${AppRoleList }" var="item" varStatus="status">
							<div class="form-check form-switch">
								<%-- <input class="form-check-input" type="checkbox" value="${item.roleId }"
									id="roles${status.index+1 }" /> --%>
								<form:checkbox path="roles" cssClass="form-check-input" value="${item.roleId }" />
								<label class="form-check-label" for="roles${status.index+1 }">${item.roleDesc }</label>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:if>
	</div>
</form:form>
<script type="text/javascript">
	$(document).ready(function() {
		var ldap = $("#ldapFlag").val();
		var userId = $("#userId").val();
		var filter = $("#filterType").val();

		if ($("#action").val() == 'UPDATE') {
			$("#userId").attr("readonly", "readonly");
		}

		if (ldap == 1) {
			$("#pwd").removeAttr('required');
			$("#passworddiv").hide();
		} else {
			$("#ldapdiv").hide();
			if ($("#action").val() === "CREATE")
				$("#pwd").attr('required', 'required');
		}

		if (!isEmpty(filter) && (filter === 'SEC' || filter === 'DEP' | filter === 'DIV')) {
			$("#filterdiv").show();
		} else {
			$("#filterdiv").hide();
			$("#filterValue").removeAttr('required');
		}
	});
</script>