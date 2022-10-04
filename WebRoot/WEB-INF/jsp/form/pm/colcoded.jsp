<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form:form commandName="ColCodedForm" action="form.do">
	<form:hidden path="action" />
	<form:hidden path="createBy" />
	<form:hidden path="createDate" />
	<div class="row">
		<div class="col-12 col-sm-8 col-md-6">
			<div class="card border-top-primary shadow">
				<div class="card-body">
					<c:if test="${BaseForm.action eq 'CREATE' }">
						<div class="row mb-3">
							<form:label path="year" for="year" cssClass="col-sm-4 col-form-label">Year</form:label>
							<div class="col-sm-8">
								<form:input path="year" cssClass="form-control" cssErrorClass="form-control is-invalid" type="number" step="1" min="1970" max="2999" placeholder="Year.." required="required" />
								<div class="invalid-feedback">
									<form:errors path="year" />
								</div>
							</div>
						</div>
						<div class="row mb-3">
							<form:label path="semester" for="semester" cssClass="col-sm-4 col-form-label">Semester</form:label>
							<div class="col-sm-8">
								<form:select path="semester" cssClass="form-select" cssErrorClass="form-select is-invalid" required="required">
									<form:option value="">Select Semester...</form:option>
									<form:options items="${CatalogOrdered.codes['SEMESTER'] }" itemLabel="tval" itemValue="id.tcode" />
								</form:select>
								<div class="invalid-feedback">
									<form:errors path="semester" />
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${BaseForm.action eq 'UPDATE' }">
						<div class="row mb-3">
							<form:label path="year" for="year" cssClass="col-sm-4 col-form-label">Year</form:label>
							<div class="col-sm-8">
								<form:input path="year" cssClass="form-control" cssErrorClass="form-control is-invalid" type="number" step="1" min="1970" max="2999" placeholder="Year.." readonly="true" />
							</div>
						</div>
						<div class="row mb-3">
							<form:hidden path="semester" />
							<form:label path="semester" for="semester" cssClass="col-sm-4 col-form-label">Semester</form:label>
							<div class="col-sm-8">
								<form:select path="semester" cssClass="form-select" disabled="true">
									<form:option value="">Select Semester...</form:option>
									<form:options items="${CatalogOrdered.codes['SEMESTER'] }" itemLabel="tval" itemValue="id.tcode" />
								</form:select>
							</div>
						</div>
					</c:if>
					<div class="row mb-3">
						<form:label path="color" for="color" cssClass="col-sm-4 col-form-label">Color</form:label>
						<div class="col-sm-8">
							<form:input path="color" type="color" placeholder="Color.." required="required" />
							<div class="invalid-feedback">
								<form:errors path="color" />
							</div>
						</div>
					</div>
					<div class="row mb-3">
						<div class="offset-sm-4 col-sm-8">
							<button type="submit" class="btn btn-primary" title="${BaseForm.action }">
								<span class="fas fa-check"></span> ${BaseForm.action }
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>