<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<form:form commandName="EqptInspForm" method="POST">
	<div class="row justify-content-between mx-md-3 mx-md-5 my-5">
		<div class="col-12 mb-2">
			<h1 class="title display-4">Equipment Inspection Form</h1>
		</div>
		<div class="col-12 col-sm-10 col-md-8">
			<div class="card border-top-primary mb-3">
				<div
					class="card-body row justify-content-md-start justify-content-center align-items-top">
					<div class="col-12 col-md-auto">
						<dl>
							<dt>Equipment Id</dt>
							<dd>${eqpt.eqptId }</dd>
							<dt>Type</dt>
							<dd>${eqpt.eqptTypeDesc }</dd>
						</dl>
					</div>
					<div class="col-12 col-md-auto">
						<dl>
							<dt>Location</dt>
							<dd>${eqpt.locationDesc }</dd>
							<dt>Location Detail</dt>
							<dd>${eqpt.locationDetail }</dd>
						</dl>
					</div>
					<div class="col-12 col-md-auto">
						<dl>
							<dt>Model</dt>
							<dd>${empty eqpt.model ? 'N/A' : eqpt.model }</dd>
							<dt>Serial No</dt>
							<dd>${empty eqpt.serialNo ? 'N/A' : eqpt.serialNo }</dd>
						</dl>
					</div>
					<div class="col-12 col-md-auto">
						<dl>
							<dt>Last Inspect</dt>
							<dd>${eqpt.lastInspectStr }</dd>
							<dt>Last Status</dt>
							<dd>${eqpt.inspStatusDesc }</dd>
						</dl>
					</div>
				</div>
			</div>
		</div>
		<div class="col-12 col-sm-10 col-md-8">
			<div class="card">
				<div class="card-body row g-2">
					<form:hidden path="eqptId" />
					<div class="col-12 col-md-6">
						<form:label path="status" cssClass="form-label">Current Status</form:label>
						<form:select path="status" cssClass="form-select"
							required="required">
							<form:option value="">Select Current Status...</form:option>
							<form:options items="${options}" itemLabel="statusDesc"
								itemValue="id.statusId" />
						</form:select>
					</div>
					<div class="col-12 col-md-6">
						<form:label path="notes" cssClass="form-label">Notes</form:label>
						<form:input path="notes" cssClass="form-control"
							placeholder="Notes... (optional)" />
					</div>
					<div class="col-12 col-md-6">
						<div class="form-check">
							<form:checkbox path="isLocAnomaly" cssClass="form-check-input" />
							<form:label path="isLocAnomaly" for="isLocAnomaly">Location Incorrent?</form:label>
						</div>
					</div>
					<div class="col-12">
						<button type="submit" class="btn btn-block btn-success">
							<span class="fas fa-fw fa-check"></span> SUBMIT
						</button>
						<spring:url value="/pm/inspect/start.do" var="url" />
						<a class="btn btn-block btn-outline-warning" href="${url }"> <span
							class="fas fa-fw fa-undo"></span> BACK
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
