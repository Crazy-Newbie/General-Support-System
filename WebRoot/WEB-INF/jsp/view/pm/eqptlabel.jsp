<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="row">
	<div class="col-12">
		<div class="card shadow" style="border-top-color: ${data.inspColor}">
			<div class="card-header">
				<div class="card-title h4 mb-0">${EqptForm.data.eqptId }</div>
			</div>
			<div class="card-body">
				<%-- <form:form commandName="EqptForm">
					<div class="mb-3">
						<form:label path="data.inspTimeStr"
							cssClass="form-label font-weight-bolder">Last Inspect Date</form:label>
						<form:input path="data.inspTimeStr"
							cssClass="form-control-plaintext" readonly="true" />
					</div>
					<div class="mb-3">
						<form:label path="data.inspByName"
							cssClass="form-label font-weight-bolder">Inspector</form:label>
						<form:input path="data.inspByName"
							cssClass="form-control-plaintext" readonly="true" />
					</div>
				</form:form> --%>
				<button type="button" class="btn btn-block"
					style="background-color: ${data.inspColor}"></button>
				<dl>
					<dt>Eqpt Type</dt>
					<dd>${data.eqptTypeDesc }</dd>
					<dt>Last Inspect Date</dt>
					<dd>${data.inspTimeStr }</dd>
					<dt>Inspect By</dt>
					<dd>${data.inspBy }&nbsp;#&nbsp;${data.inspByName }</dd>
					<dt>Status</dt>
					<dd>${data.statusDesc }</dd>
					<dt>Next Inspection</dt>
					<dd>${data.nextInspStr }</dd>
				</dl>
				<hr />
				<dl>
					<dt>Model</dt>
					<dd>${not empty data.eqptModel ? data.eqptModel : 'N / A' }</dd>
					<dt>Serial No</dt>
					<dd>${not empty data.eqptSerialNo ? data.eqptSerialNo : 'N /
						A' }</dd>
				</dl>
			</div>
		</div>
	</div>
</div>