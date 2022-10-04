<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/sys/role/form.do" var="formurl" />
<spring:url value="/sys/role/menu.do" var="editMenu" />
<script type="text/javascript">
	var roletable;

	function editMenu(roleId) {
		var url = '${editMenu}' + '?roleId=' + roleId;
		window.location = url;
	}

	function deleteRole(roleId) {
		/* bootbox.confirm("Are you sure you want to delete this Role?", function(result) {
			if (result) {
				$.post("${formurl}", {
					roleId : roleId,
					action : 'DELETE'
				}, function(data, status) {
					if (data.success)
						roletable.ajax.reload();
					else
						bootbox.alert(data.desc);
				})
			}
		}) */
		Swal.fire({
			title : "Are you sure you want to delete this Role?",
			text : "This action cannot be undone",
			icon : "warning",
			showCancelButton : true
		}).then(function(resp) {
			if (resp.isConfirmed) {
				$.post("${formurl}", {
					roleId : roleId,
					action : 'DELETE'
				}, function(data, status) {
					if (data.success)
						roletable.ajax.reload();
					else
						Swal.fire({
							title : 'Failed',
							text : data.desc,
							icon : 'error'
						});
				})
			}
		});

	}
</script>
<div class="row">
	<div class="col-12">
		<c:if test="${BaseForm.accessCode >= 20 }">
			<div class="card card-block border-top-primary shadow">
				<div class="card-body">
					<a href="${formurl }" class="btn btn-primary">
						<span class="fas fa-plus"></span> CREATE
					</a>
				</div>
			</div>
		</c:if>
		<div class="card card-block border-top-success shadow">
			<div class="card-body">
				<table class="table table-striped table-bordered table-hover" id="roletable">
					<thead>
						<tr>
							<th>Role ID</th>
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
<spring:url value="/helper/role.do" var="roleURL" />
<script type="text/javascript">
	var accessCode = Number('${BaseForm.accessCode}');
	$(document).ready(
			function() {
				roletable = $("#roletable").DataTable(
						{
							responsive : true,
							searching : true,
							paging : true,
							bSort : false,
							bLengthChange : false,
							bFilter : false,
							bInfo : true,
							autoWidth : false,
							ajax : {
								url : "${roleURL}",
								dataSrc : ''
							},
							columns : [
									{
										data : 'roleId',
										responsivePriority : 1
									},
									{
										data : 'roleDesc'
									},
									{
										data : null,
										responsivePriority : 2,
										data : null,
										className : 'action-row',
										render : function(data, type, full, meta) {
											var butt1 = '', butt2 = '', butt3 = '';
											if (accessCode >= 20) {
												butt3 = '<button type="button" class="btn btn-danger btn-sm" title="DELETE" onclick="deleteRole(\'' + data.roleId
														+ '\')"><span class="fas fa-trash"></span></button>';
												butt4 = '<a href="${formurl}?roleId=' + data.roleId
														+ '&action=UPDATE" class="btn btn-outline-secondary btn-sm" title="Edit"><span class="fas fa-cog"></span></a>';
											}
											var butt1 = '<button type="button" class="btn btn-outline-secondary btn-sm" title="MENU" onclick="editMenu(\'' + data.roleId
													+ '\')"><span class="fas fa-list"></span></button>';

											return '<div class="btn-group">' + butt1 + butt4 + butt3 + '</div>';
										}
									}]
						})
			})
</script>