<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/pm/eqpt/form.do" var="form" />
<spring:url value="/pm/eqpt/qrcode/id.do" var="qr" />
<spring:url value="/lookup/location.do" var="url" />
<script type="text/javascript">
	var itemtable;
	function inspDetail(by, byname, time, notes) {
		var html = '<dl>';
		html += '<dt>Inspect By</dt><dd>' + by + ' # ' + byname + '</dd>';
		html += '<dt>On</dt><dd>' + time + '</dd>';
		html += '<dt>Notes</dt><dd>' + notes + '</dd>';
		html += '</dl>';
		Swal.fire({
			icon : 'info',
			title : 'Inspection Detail',
			html : html
		});
	}

	function showQrCode(eqptid) {
		windowLookup("${qr}".replace('id', eqptid), 640, 480);
	}

	function detailSwal(notes) {
		Swal.fire({
			text : notes
		});
	}

	function openLookup() {
		windowLookup("${url}", 640, 480);
	}

	function clearParent() {
		$("#location").val("");
		$("#locationDesc").val("");
	}

	function receiveCallback(obj) {
		if (obj.form == 'location') {
			$("#location").val(obj.locPathId);
			$("#locationDesc").val(obj.locDesc);
		}
	}

	function setAction(val) {
		$("#action").val(val);
	}
</script>
<div class="row">
	<div class="col-12">
		<div class="card border-top-primary shadow">
			<div class="card-body">
				<form:form commandName="EqptListForm">
					<form:hidden path="action" />
					<form:hidden path="accessCode" />
					<div class="row g-1">
						<div class="col-12 col-sm-auto">
							<form:input path="eqptId" cssClass="form-control"
								placeholder="Equipment ID..." />
						</div>
						<div class="col-12 col-sm-auto">
							<form:select path="eqptType" cssClass="form-select">
								<form:option value="">ALL TYPE</form:option>
								<form:options items="${type }" itemValue="typeId"
									itemLabel="typeDesc" />
							</form:select>
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
						</div>
						<div class="col-12 col-sm-auto">
							<spring:url value="/pm/eqpt/form.do" var="createMenu" />
							<button type="submit" class="btn btn-outline-primary"
								title="SEARCH" onclick="setAction('SEARCH')">
								<span class="fas fa-search"></span> SEARCH
							</button>
							<button type="submit" class="btn btn-outline-primary"
								title="DOWNLOAD" onclick="setAction('DOWNLOAD')">
								<span class="fas fa-download"></span> DOWNLOAD
							</button>
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
				<table
					class="table table-sm table-striped table-bordered table-hover"
					id="itemtable">
					<thead>
						<tr>
							<th>Eqpt. ID</th>
							<th>Eqpt. Type</th>
							<th>Location</th>
							<th>Model</th>
							<th>Serial No</th>
							<th>Last Inpsect</th>
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
<spring:url value="/pm/eqpt/list/data.do" var="dataurl" />
<script type="text/javascript">
	$(document).ready(
			function() {
				$("form").submit(function(e) {
					if ($("#action").val() == 'SEARCH') {
						e.preventDefault();
						itemtable.ajax.url("${dataurl}?" + $("form").serialize()).load();
					}
				})

				itemtable = $("#itemtable").DataTable(
						{
							searching : true,
							serverSide : true,
							sort : false,
							lengthChange : false,
							bFilter : false,
							ajax : {
								url : "${dataurl}",
								dataSrc : 'data',
								method : 'POST'
							},
							columns : [
									{
										data : 'eqptId'
									},
									{
										data : 'eqptTypeDesc'
									},
									{
										data : null,
										render : function(data) {
											if (data.locationDetail != null)
											/* return data.locationDesc + ' <button type="button" class="btn btn-sm btn-outline-info" title="DETAIL" onclick="detailSwal(\'' + data.locationDetail
													+ '\')"><span class="fas fa-sticky-note"></span></button>'; */
											return data.locationDesc + ' [' + data.locationDetail + ']';
											return data.locationDesc;
										}
									},
									{
										data : 'model'
									},
									{
										data : 'serialNo'
									},
									{
										data : null,
										className : 'action-row',
										render : function(data) {
											if (data.lastInspect != null) {
												var dt = dayjs.tz(data.lastInspect, 'Asia/Makassar');
												return '<button type="button" class="btn btn-outline-info btn-sm" onclick="inspDetail(\'' + data.inspBy + '\',\'' + data.inspByName + '\',\''
														+ dt.format('DD-MM-YY HH:mm') + '\',\'' + (data.inspNotes != null ? data.inspNotes : '-') + '\')">' + data.inspStatusDesc + ' : '
														+ dt.format('DD-MM-YYYY') + '</button>';
											} else return '';
										}
									},
									{
										data : null,
										className : 'action-row',
										render : function(data) {
											var edit = '';
											var qrcode = '<button type="button" onclick="showQrCode(\'' + data.eqptId
													+ '\')" class="btn btn-sm btn-outline-info"><span class="fas fa-qrcode"></span></button>';
											if ($("#accessCode").val() >= 30)
												edit = '<a href="${form}?eqptId=' + data.eqptId + '" class="btn btn-sm btn-outline-primary"><span class="fas fa-edit"></span></a>';
											return '<div class="btn-group">' + edit + qrcode + '</div>';
										}
									}]
						})
			});
</script>