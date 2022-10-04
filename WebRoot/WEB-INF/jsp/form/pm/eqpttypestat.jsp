<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/pm/eqpt/type/status.do" var="form" />
<script type="text/javascript">
	function editStatusDesc(statusId, statusDesc) {
		Swal.fire({
			title : 'Status Desc for ' + statusId,
			input : 'text',
			inputValue : statusDesc,
			showCancelButton : true,
			inputValidator : function(value) {
				if (!value) {
					return 'Description required!'
				}
			}
		}).then(function(resp) {
			if (resp.isConfirmed) {
				$.post("${form}", {
					typeId : '${BaseForm.typeId}',
					statusId : statusId,
					statusDesc : resp.value,
					action : 'UPDATE'
				}).done(function(resp) {
					if (resp.success)
						window.location.reload();
					else
						Swal.fire({
							title : 'Action Failed',
							text : resp.desc,
							icon : resp.icon
						})
				})
			}
		})
	}

	function deleteStatus(statusId) {
		Swal.fire({
			title : 'Action Confirmation?',
			text : 'Do you want to delete this status [' + statusId + ']?',
			icon : 'question',
			showCancelButton : true
		}).then(function(resp) {
			if (resp.isConfirmed) {
				$.post("${form}", {
					typeId : '${BaseForm.typeId}',
					statusId : statusId,
					action : 'REMOVE'
				}).done(function(resp) {
					if (resp.success)
						window.location.reload();
					else
						Swal.fire({
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
			<div class="card-header">
				<span class="card-title">${eqptdata.typeDesc }</span>
			</div>
			<div class="card-body">
				<form:form commandName="EqptTypeForm">
					<div class="row g-1 align-items-end">
						<div class="col-12 col-sm-auto mb-2">
							<form:label path="statusId" cssClass="form-label">Status ID</form:label>
							<form:input path="statusId" cssClass="form-control" placeholder="Status ID..." required="required" maxlength="2" />
						</div>
						<div class="col-12 col-sm-6 col-md-4 mb-2">
							<form:label path="statusDesc" cssClass="form-label">Status Desc</form:label>
							<form:input path="statusDesc" cssClass="form-control" placeholder="Status Description..." required="required" />
						</div>
						<div class="col-12 col-sm-auto mb-2 align-items-bottom">
							<button type="submit" class="btn btn-primary" title="ADD">
								<span class="fas fa-plus"></span> ADD
							</button>
							<button type="button" onclick="window.location.reload()" class="btn btn-outline-info" title="REFRESH">
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
				<table class="table table-sm table-striped table-bordered">
					<thead>
						<tr>
							<th>No</th>
							<th>Status ID</th>
							<th>Status Desc</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty liststatus }">
							<tr>
								<td colspan="5" class="text-center">Data Not Found</td>
							</tr>
						</c:if>
						<c:forEach items="${liststatus }" var="item" varStatus="status">
							<tr>
								<td>${status.index + 1 }</td>
								<td>${item.id.statusId }</td>
								<td>${item.statusDesc }</td>
								<td class="action-row">
									<div class="btn-group">
										<c:if test="${BaseForm.accessCode >= 30 }">
											<button type='button' class="btn btn-outline-primary btn-sm" onclick="editStatusDesc('${item.id.statusId}','${item.statusDesc }')">
												<span class="fas fa-edit"></span>
											</button>
										</c:if>
										<c:if test="${BaseForm.accessCode >= 40 }">
											<button type='button' class="btn btn-outline-danger btn-sm" onclick="deleteStatus('${item.id.statusId}')">
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
		$("form").submit(function(e) {
			e.preventDefault();
			$.post("${form}", {
				typeId : '${BaseForm.typeId}',
				statusId : $("#statusId").val(),
				statusDesc : $("#statusDesc").val(),
				action : 'ADD'
			}).done(function(resp) {
				if (resp.success)
					window.location.reload();
				else
					Swal.fire({
						title : 'Action Failed',
						text : resp.desc,
						icon : resp.icon
					})
			})
		})
	});
</script>