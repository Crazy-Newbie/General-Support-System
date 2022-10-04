<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/sys/group/form.do" var="form" />
<script type="text/javascript">
	var itemtable;

	function editGroup(groupId) {
		var url = '${form}' + '?groupId=' + groupId;
		window.location = url;
	}

	function deleteGroup(groupId) {
		/* bootbox.confirm("Are you sure you want to delete this Group?", function(
			result) {
			if (result) {
				$.post("${delete}", {
					groupId : groupId,
					action : 'DELETE'
				}, function(data, status) {
					if (data.success)
						itemtable.ajax.reload();
					else
						bootbox.alert(data.desc);
				})
			}
		}) */
		Swal.fire({
			title : 'Are you sure you want to delete this Group',
			text : 'This action cannot be undone',
			showCancelButton : true,
			icon : 'warning'
		}).then(function(resp) {
			if (resp.isConfirmed) {
				$.post("${form}", {
					groupId : groupId,
					action : 'DELETE'
				}, function(data, status) {
					if (data.success)
						itemtable.ajax.reload();
					else
						Swal.fire({
							title : 'Process Failed',
							text : data.desc,
							icon : 'danger'
						});
				})
			}
		})

	}
</script>
<div class="row">
	<div class="col-12">
		<div class="card card-block">
			<div class="card-body">
				<spring:url value="/sys/group/form.do" var="create" />
				<a href="${create }" class="btn btn-primary">
					<span class="fas fa-plus"></span> CREATE
				</a>
			</div>
		</div>
		<div class="card card-block">
			<div class="card-body">
				<table class="table table-striped table-bordered table-hover" id="itemtable">
					<thead>
						<tr>
							<th>Group ID</th>
							<th>Group Name</th>
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
<spring:url value="/sys/group/list/ajax.do" var="listURL" />
<spring:url value="/sys/group/member.do" var="memberURL" />
<script type="text/javascript">
	var accessCode = Number('${BaseForm.accessCode}');
	$(document).ready(
			function() {
				itemtable = $("#itemtable").DataTable(
						{
							responsive : true,
							searching : true,
							paging : true,
							ordering : false,
							lengthChange : false,
							info : true,
							autoWidth : false,
							ajax : {
								url : "${listURL}",
								dataSrc : ''
							},
							columns : [
									{
										data : 'groupId',
										responsivePriority : 1
									},
									{
										data : 'name'
									},
									{
										data : null,
										responsivePriority : 2,
										data : null,
										className : 'action-row',
										render : function(data, type, full, meta) {
											var edit = '', del = '', member = '';
											if (accessCode >= 20)
												member = '<a href="${memberURL}?groupId=' + data.groupId
														+ '" class="btn btn-sm btn-outline-secondary" title="MEMBER"><span class="fas fa-user"></span></a>';
											if (accessCode >= 30)
												edit = '<button type="button" class="btn btn-sm btn-outline-secondary" title="EDIT" onclick="editGroup(\'' + data.groupId
														+ '\')"><span class="fas fa-cog"></span></button>';
											if (accessCode >= 40)
												del = '<button type="button" class="btn btn-sm btn-danger" title="DELETE" onclick="deleteGroup(\'' + data.groupId
														+ '\')"><span class="fas fa-trash"></span></button>';

											return '<div class="btn-group">' + edit + member + del + '</div>';
										}
									}]
						})
			})
</script>