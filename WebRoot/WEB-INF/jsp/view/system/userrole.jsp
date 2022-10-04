<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/sys/user/role.do" var="url" />
<script type="text/javascript">
	var itemtable;

	function deleteRole(val) {
		/*bootbox.confirm("Revoke this role from this user?",
			function(ans) {
				if (ans) {
					$.post("${url}", {
						userId : $("#userId").val(),
						roleId : val,
						action : 'REMOVE'
					}).done(function(e) {
						if (e.success)
							itemtable.ajax.reload();
						else
							bootbox.alert(e.desc);
					})
				}
			})*/
			
			Swal.fire({
				text : "Revoke this role from this user?",
				icon : 'question',
				showCancelButton : true
			}).then(function(ans) {
				if (ans.isConfirmed) {
					$.post("${url}", {
						userId : $("#userId").val(),
						roleId : val,
						action : 'REMOVE'
					}).done(function(e) {
						if (e.success)
							itemtable.ajax.reload();
						else
							Swal.fire(e.desc);
					})
				}
			})
	}

	function addRole(val) {
		/*bootbox.confirm("Add this role to this user?", function(e) {
			if (e) {
				$.post("${url}", {
					userId : $("#userId").val(),
					roleId : val,
					action : 'ADD'
				}).done(function(e) {
					if (e.success)
						itemtable.ajax.reload();
					else
						bootbox.alert(e.desc);
				})
			}
		})*/
		
		Swal.fire({
			text : "Add this role to this user?",
			icon : 'question',
			showCancelButton : true
		}).then(function(e) {
			if (e.isConfirmed) {
				$.post("${url}", {
					userId : $("#userId").val(),
					roleId : val,
					action : 'ADD'
				}).done(function(e) {
					if (e.success)
						itemtable.ajax.reload();
					else
						Swal.fire(e.desc);
				})
			}
		})
	}
</script>
<div class="row">
	<div class="col-12">
		<div class="card">
			<div class="card-body">
				<div class="row">
					<c:if test="${UserForm.accessCode >= 20 }">
						<div class="col-12">
							<form:form commandName="UserForm" class="form-horizontal p-2">
								<form:hidden path="userId" />
								<form:hidden path="accessCode" />
								<div class="form-group">
									<form:label path="roleId" for="roleId"
										class="col-xs-2 col-form-label">Available Role :</form:label>
									<div class="col-xs-10">
										<div class="input-group">
											<form:select path="roleId"
												cssClass="form-control form-control-sm" required="required">
												<form:option value="">SELECT ROLE...</form:option>
												<form:options items="${AppRoleList }" itemLabel="roleDesc"
													itemValue="roleId" />
											</form:select>
											<div class="input-group-append">
												<button type="submit" value="ADD"
													class="btn btn-success btn-sm">
													<span class="fa fa-plus"></span>
												</button>
											</div>
										</div>
									</div>
								</div>
							</form:form>
						</div>
					</c:if>
					<div class="col-12">
						<table class="table table-striped table-bordered table-hover"
							id="itemtable">
							<thead>
								<tr>
									<th>Role Id</th>
									<th>Role Name</th>
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
	</div>
</div>
<spring:url value="/sys/user/role/ajax.do" var="role">
	<spring:param name="userId">${UserForm.userId }</spring:param>
</spring:url>
<script type="text/javascript">
	$(document).ready(function() {
		$("form").submit(function(e) {
			e.preventDefault();
			addRole($("#roleId").val());
		});

		itemtable = $("#itemtable").DataTable({
			responsive : true,
			searching : false,
			paging : false,
			pageLength : 10,
			bLengthChange : false,
			bFilter : false,
			bInfo : true,
			autoWidth : false,
			order : [ [ 0, "desc" ] ],
			ajax : {
				url : "${role}",
				dataSrc : ''
			},
			columns : [ {
				data : 'id.roleId',
				responsivePriority : 1
			}, {
				data : 'appRole.roleDesc'
			} ],
			columnDefs : [
				{
					targets : 2,
					responsivePriority : 2,
					data : null,
					class : 'action-row',
					render : function(data,
						type, full,
						meta) {
						var del = '';
						if ($("#accessCode").val() >= 40)
							del = '<button type="button" class="btn btn-danger" title="REVOKE" onclick="deleteRole(\''
								+ data.id.roleId
								+ '\')"><span class="fa fa-times"></span></button>';
						return del;
					}
				} ]
		})
	});
</script>