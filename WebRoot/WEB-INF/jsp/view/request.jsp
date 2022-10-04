<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/request/status/id.do" var="req" />
<script type="text/javascript">
	var itemtable;

	function setAction(val) {
		$("#action").val(val);
	}

	function viewLogs(reqId) {
		windowWithoutToolbar("${req}".replace("id", reqId), 640, 480);
	}
</script>
<div class="row">
	<div class="col-12">
		<div class="card border-top-primary shadow">
			<div class="card-body">
				<form:form commandName="RequestListForm">
					<form:hidden path="action" />
					<form:hidden path="accessCode" />
					<div class="row g-1">
						<div class="col-12 col-sm-auto">
							<form:input path="reqId" cssClass="form-control"
								placeholder="Request ID..." />
						</div>
						<div class="col-12 col-sm-auto">
							<form:select path="status" cssClass="form-select">
								<form:option value="">ALL STATUS</form:option>
								<form:options items="${CatalogOrdered.codes['REQ_STATUS'] }"
									itemLabel="tval" itemValue="id.tcode" />
							</form:select>
						</div>
						<div class="col-12 col-sm-auto">
							<form:select path="teamId" class="form-select">
								<form:option value="">ALL TEAM</form:option>
								<c:forEach items="${teams }" var="item">
									<form:option value="${item.teamId }">${item.teamName }</form:option>
								</c:forEach>
							</form:select>
						</div>
						<div class="col-12 col-sm-auto">
							<div class="input-group">
								<span class="input-group-text">Start Date</span>
								<form:input path="startDate" cssClass="form-control"
									placeholder="Start Date..." />
							</div>
						</div>
						<div class="col-12 col-sm-auto">
							<div class="input-group">
								<span class="input-group-text">End Date</span>
								<form:input path="endDate" cssClass="form-control"
									placeholder="End Date..." />
							</div>
						</div>
						<div class="col-12 col-sm-auto">
							<form:input path="location" cssClass="form-control"
								placeholder="Location..." />
						</div>
						<%-- <div class="col-12 col-sm-auto">
							<form:input path="eqptId" cssClass="form-control"
								placeholder="Equipment ID..." />
						</div>
						
						<div class="col-12 col-sm-auto">
							<form:input path="model" cssClass="form-control"
								placeholder="Model..." />
						</div>
						<div class="col-12 col-sm-auto">
							<form:input path="serialNo" cssClass="form-control"
								placeholder="Serial No..." />
						</div>
						<div class="col-12 col-sm-auto">
							<form:hidden path="location" />
							<div class="input-group">
								<form:input path="locationDesc" onkeydown="return false"
									placeholder="Location Group..." cssClass="form-control"
									onclick="openLookup()" autocomplete="off" />
								<form:select path="isLocExact" cssClass="form-select">
									<form:option value="N">Incl. Children</form:option>
									<form:option value="Y">Exact Location</form:option>
								</form:select>
								<button type="button" onclick="clearParent()"
									class="btn btn-outline-warning">
									<span class="fas fa-times"></span>
								</button>
							</div>
						</div> --%>
						<div class="col-12 col-sm-auto">
							<button type="submit" class="btn btn-outline-primary"
								title="SEARCH" onclick="setAction('SEARCH')">
								<span class="fas fa-search"></span> SEARCH
							</button>
							<%--<button type="submit" class="btn btn-outline-primary"
								title="DOWNLOAD" onclick="setAction('DOWNLOAD')">
								<span class="fas fa-download"></span> DOWNLOAD
							</button> --%>
						<spring:url value="/request/form.do" var="createMenu" />
						<a href="${createMenu }" class="btn btn-success"> <span
							class="fas fa-plus"></span> CREATE
						</a>
					</div>
			</div>
			</form:form>
		</div>
	</div>
	<div class="card border-top-success shadow">
		<div class="card-body">
			<table class="table table-sm table-bordered table-hover"
				id="itemtable">
				<thead>
					<tr>
						<th>Request ID</th>
						<th>Created Date</th>
						<th>Req By</th>
						<th>Description</th>
						<th>Location</th>
						<th>Team</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</div>
</div>
<spring:url value="/request/list/data.do" var="dataurl" />
<spring:url value="/request/form.do" var="formurl" />
<spring:url value="/request/statusform.do" var="statusformurl" />
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("form")
								.submit(
										function(e) {
											if ($("#action").val() == 'SEARCH') {
												e.preventDefault();
												itemtable.ajax
														.url(
																"${dataurl}?"
																		+ $(
																				"form")
																				.serialize())
														.load();
											}
										})

						var sd = $("#startDate").flatpickr({
							allowInput : true,
							dateFormat : 'd-m-Y',
							maxDate : new Date()
						});

						var ed = $("#endDate").flatpickr({
							allowInput : true,
							dateFormat : 'd-m-Y',
							maxDate : new Date(),
							onChange : function(seldate) {
								sd.maxDate = seldate;
							}
						});

						itemtable = $("#itemtable")
								.DataTable(
										{
											searching : false,
											serverSide : true,
											sort : false,
											lengthChange : false,
											bFilter : false,
											ajax : {
												url : "${dataurl}?"
														+ $("form").serialize(),
												dataSrc : 'data',
												method : 'POST'
											},
											createdRow : function(row, data,
													index) {

												if (data.status == "CANC") {
													$(row).addClass(
															'text-danger');
												} else if (data.status == "CMPL") {
													$(row).addClass(
															'text-success');
												}
											},
											columns : [
													{
														data : 'reqId'
													},
													{
														data : 'createdDate',
														render : function(data) {
															return dayjs
																	.tz(data,
																			'Asia/Makassar')
																	.format(
																			"ddd, DD-MM-YYYY HH:mm");
														}
													},
													{
														data : null,
														render : function(data) {
															return data.reqBy
																	+ (data.reqByName != null ? " # "
																			+ data.reqByName
																			: '');
														}
													},
													{
														data : 'reqDesc'
													},
													{
														data : 'location'
													},
													{
														data : null,
														render : function(data) {
															if (data.teamId != null)
																return data.teamName;
															return '';
														}
													},
													{
														data : null,
														render : function(data) {
															return data.statusDesc;
														}
													},
													{
														data : null,
														className : 'action-row text-center',
														render : function(data) {
															var edit = '';
															var logs = '';
															var follup = '';
															if ($("#accessCode")
																	.val() >= 30
																	&& data.status != 'CANC'
																	&& data.status != 'CMPL') {
																edit = '<a href="${formurl}?reqId='
																		+ data.reqId
																		+ '" class="btn btn-sm btn-outline-primary"><span class="fas fa-fw fa-edit"></span></a>';
																follup = '<a href="${statusformurl}?reqId='
																		+ data.reqId
																		+ '" class="btn btn-sm btn-primary"><span class="fas fa-fw fa-chevron-right"></span></a>';
															}
															logs = '<button class="btn btn-sm btn-outline-info" onclick="viewLogs(\''
																	+ data.reqId
																	+ '\')"><span class="fas fa-fw fa-info"></span></button>';
															return '<div class="btn-group">'
																	+ edit
																	+ logs
																	+ follup
																	+ '</div>';
														}
													} ]
										})
					});
</script>