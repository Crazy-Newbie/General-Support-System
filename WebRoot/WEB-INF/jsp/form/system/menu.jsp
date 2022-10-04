<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/lookup/menu.do" var="url" />
<script type="text/javascript">
	function openLookup() {
		windowLookup("${url}", 640, 480);
	}

	function resetParent() {
		$("#menuParentId").val("0");
		$("#menuParentName").val("");
	}

	function receiveCallback(obj) {
		if (obj.form == 'menu') {
			$("#menuParentId").val(obj.menuId);
			$("#menuParentName").val(obj.menuName);
		}
	}
</script>
<div class="row">
	<div class="col-md-6">
		<div class="card card-block shadow border-top-primary">
			<div class="card-body">
				<form:form action="form.do" autocomplete="false" method="POST" commandName="MenuForm">
					<form:hidden path="action" />
					<form:hidden path="menuId" />
					<div class="mb-3 row">
						<form:label path="menuName" for="menuName" cssClass="col-md-5 col-form-label">Menu Name :</form:label>
						<div class="col-md-7">
							<form:input path="menuName" cssClass="form-control" required="required" placeholder="Menu Name" />
							<span class="text-danger"><form:errors path="menuName" /> </span>
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="menuOrder" for="menuOrder" cssClass="col-md-5 col-form-label">Menu Order :</form:label>
						<div class="col-md-7">
							<form:input path="menuOrder" cssClass="form-control" placeholder="Order (Default : last order)" type="number" min="1" max="99" />
							<span class="text-danger"><form:errors path="menuOrder" /> </span>
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="menuUrl" for="menuUrl" cssClass="col-md-5 col-form-label">Menu URL :</form:label>
						<div class="col-md-7">
							<form:input path="menuUrl" cssClass="form-control" placeholder="/action/type.do" required="required" />
							<span class="text-danger"><form:errors path="menuUrl" /> </span>
						</div>
					</div>
					<div class="mb-3 row">
						<form:hidden path="menuParentId" />
						<form:label path="menuParentName" for="menuParentName" cssClass="col-md-5 col-form-label">Menu Parent :</form:label>
						<div class="col-md-7">
							<div class=" input-group">
								<form:input path="menuParentName" cssClass="form-control" placeholder="Choose parent menu..." onkeydown="return false" />
								<button type="button" class="btn btn-outline-info" onclick="openLookup()">
									<i class="fas fa-fw fa-search"></i>
								</button>
								<button type="button" class="btn btn-warning" onclick="resetParent()">
									<i class="fas fa-fw fa-times"></i>
								</button>
							</div>
						</div>
						<span class="text-danger"><form:errors path="menuParentName" /> </span>
					</div>
					<div class="mb-3 row">
						<form:label path="formId" for="formId" cssClass="col-md-5 col-form-label">Form ID :</form:label>
						<div class="col-md-7">
							<form:input path="formId" cssClass="form-control" placeholder="Form ID of menu" />
							<span class="text-danger"><form:errors path="formId" /> </span>
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="formName" for="formName" cssClass="col-md-5 col-form-label">Form Name :</form:label>
						<div class="col-md-7">
							<form:input path="formName" cssClass="form-control" placeholder="Form Name" />
							<span class="text-danger"><form:errors path="formName" /> </span>
						</div>
					</div>
					<div class="mb-3 row align-items-center">
						<form:label path="isMenu" for="isMenu" cssClass="col-md-5 col-form-label">Is Menu :</form:label>
						<div class="col-md-7">
							<div class="form-check form-switch">
								<form:checkbox path="isMenu" cssClass="form-check-input" />
							</div>
							<span class="text-danger"><form:errors path="isMenu" /> </span>
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="icon" for="icon" cssClass="col-md-5 col-form-label">Icon :</form:label>
						<div class="col-md-7">
							<form:input path="icon" cssClass="form-control" placeholder="Icon" />
							<span class="text-danger"><form:errors path="icon" /> </span>
						</div>
					</div>
					<div class="mb-3 row">
						<label class="col-md-5 col-form-label"></label>
						<div class="col-md-7">
							<form:button value="SUBMIT" class="btn btn-outline-primary">${MenuForm.action }</form:button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>