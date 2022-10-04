<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/pm/eqpt/type/form.do" var="form" />
<spring:url value="/pm/eqpt/type/status/dummy.do" var="statform" />
<script type="text/javascript">
	function editStatus(typeId) {
		windowWithoutToolbar("${statform}".replace('dummy', typeId), 640, 480);
	}

	function editTypeDesc(typeId, typeDesc, interval) {
		var html = '<input type="text" id="typeDesc" class="swal2-input" required="required" placeholder="Type Description" value="'+typeDesc+'">';
		html += '<input type="number" id="interval" class="swal2-input" required="required" placeholder="Insp. Interval" step="1" min="30" value="'+interval+'">';
		Swal.fire({
			title : 'Edit Information for ' + typeId,
			html : html,
			showCancelButton : true,
			preConfirm : function(){
				const desc = Swal.getPopup().querySelector("#typeDesc").value;
				const itvl = Swal.getPopup().querySelector("#interval").value;
				if(!desc || !itvl){
					Swal.showValidationMessage("Please input Type Desc and Interval");
				}else if(itvl < 30)
					Swal.showValidationMessage("Interval cannot less than 30");
				return {typeDesc : desc, interval : itvl};
			}
		}).then(function(resp) {
			if (resp.isConfirmed) {
				$.post("${form}", {
					typeId : typeId,
					typeDesc : resp.value.typeDesc,
					interval : resp.value.interval,
					action : 'UPDATE'
				}).done(function(resp) {
					if (resp.success)
						window.location.reload();
					else Swal.fire({
						title : 'Action Failed',
						text : resp.desc,
						icon : resp.icon
					})
				})
			}
		})
	}

	function deleteType(typeId) {
		Swal.fire({
			title : 'Action Confirmation?',
			text : 'Do you want to delete this type [' + typeId + ']?',
			icon : 'question'
		}).then(function(resp) {
			if (resp.isConfirmed) {
				$.post("${form}", {
					typeId : typeId,
					action : 'REMOVE'
				}).done(function(resp) {
					if (resp.success)
						window.location.reload();
					else Swal.fire({
						title : 'Action Failed',
						text : resp.desc,
						icon : resp.icon
					})
				})
			}
		})
	}
</script>
<div class="row">
	<div class="col-12">
		<div class='card border-top-primary shadow'>
			<div class="card-body">
				<form:form commandName="EqptTypeForm">
					<div class="row g-1 align-text-bottom">
						<div class="col-12 col-sm-auto">
							<form:label path="typeId" cssClass="form-label">Type ID</form:label>
							<form:input path="typeId" cssClass="form-control"
								placeholder="Type ID..." required="required" maxlength="8" />
						</div>
						<div class="col-12 col-sm-6 col-md-4">
							<form:label path="typeDesc" cssClass="form-label">Type Desc</form:label>
							<form:input path="typeDesc" cssClass="form-control"
								placeholder="Type Description..." required="required" />
						</div>
						<div class="col-12 col-sm-6 col-md-4">
							<form:label path="interval" cssClass="form-label">Days Interval</form:label>
							<div class="input-group">
								<form:input path="interval" cssClass="form-control"
									placeholder="Next Inspection Interval... (Min 30 days)"
									type="number" step="1" min="30" required="required" />
								<span class="input-group-text">Days</span>
							</div>
						</div>
						<div class="col-12 col-sm-auto mt-sm-auto mt-2">
							<button type="submit" class="btn btn-primary" title="ADD">
								<span class="fas fa-plus"></span> ADD
							</button>
							<button type="button" onclick="window.location.reload()"
								class="btn btn-outline-info" title="REFRESH">
								<span class="fas fa-sync"></span> REFRESH
							</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<div class="col-12">
		<div class='card border-top-success shadow'>
			<div class="card-body">
				<table class="table table-sm table-striped table-bordered"
					id="itemtable">
					<thead>
						<tr>
							<th>No</th>
							<th>Type ID</th>
							<th>Type Desc</th>
							<th class="action-row">Insp. Intvl (Days)</th>
							<th class="action-row">Eqpt Counter</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty listdata }">
							<tr>
								<td colspan="5" class="text-center">Data Not Found</td>
							</tr>
						</c:if>
						<c:forEach items="${listdata }" var="item" varStatus="status">
							<tr>
								<td>${status.index + 1 }</td>
								<td>${item.typeId }</td>
								<td>${item.typeDesc }</td>
								<td>${item.nextInspDays }</td>
								<td>${item.eqptCounter }</td>
								<td class="action-row">
									<div class="btn-group">
										<c:if test="${BaseForm.accessCode >= 20 }">
											<button type='button' class="btn btn-outline-primary btn-sm"
												onclick="editStatus('${item.typeId}')">
												<span class="fas fa-list"></span>
											</button>
										</c:if>
										<c:if test="${BaseForm.accessCode >= 30 }">
											<button type='button' class="btn btn-outline-primary btn-sm"
												onclick="editTypeDesc('${item.typeId}','${item.typeDesc }',${item.nextInspDays })">
												<span class="fas fa-edit"></span>
											</button>
										</c:if>
										<c:if
											test="${BaseForm.accessCode >= 40 and item.eqptCounter eq 0}">
											<button type='button' class="btn btn-outline-danger btn-sm"
												onclick="deleteType('${item.typeId}')">
												<span class="fas fa-times"></span>
											</button>
										</c:if>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#itemtable").DataTable({
			lengthChange : false,
			searching : false,
			ordering : false,
			columnDefs : [
				{
					targets : -1,
					responsivePriority : 2
				}
			]
		});
		
		$("form").submit(function(e) {
			e.preventDefault();
			$.post("${form}", {
				typeId : $("#typeId").val(),
				typeDesc : $("#typeDesc").val(),
				interval : $("#interval").val(),
				action : 'ADD'
			}).done(function(resp) {
				if (resp.success) {
					windowWithoutToolbar("${statform}".replace('dummy', $("#typeId").val().toUpperCase()), 640, 480);
					//window.location.reload();
				} else Swal.fire({
					title : 'Action Failed',
					text : resp.desc,
					icon : resp.icon
				})
			})
		})
	});
</script>