<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/sys/user/form.do" var="form" />
<spring:url value="/sys/user/role.do" var="urlrole" />
<script type="text/javascript">
	var usertable;

	function deleteUser(userId) {
		/* bootbox.confirm("Are you sure you want to delete this user?", function(
			result) {
			if (result) {
				$.post("${deleteURL}", {
					userId : userId,
					action : 'DELETE'
				}, function(data, status) {
					usertable.ajax.reload(null, false);
				})
			}
		}) */
		Swal.fire({
			title : 'Confirmation',
			text : "Are you sure you want to delete this user?",
			showCancelButton : true,
			icon : 'warning'
		}).then(function(resp) {
			if (resp.isConfirmed) {
				$.post("${form}", {
					userId : userId,
					action : 'DELETE'
				}, function(data, status) {
					usertable.ajax.reload(null, false);
				})
			}
		})
	}

	function userRole(userId) {
		windowWithoutToolbar("${urlrole}?userId=" + userId, 640, 480);
	}
</script>
<div class="row">
	<div class="col-12">
		<div class="card border-top-primary shadow">
			<div class="card-body">
				<form:form commandName="UserListForm" cssClass="row align-items-center gy-2">
					<div class="col-auto">
						<form:label path="keyword" for="keyword" cssClass="col-form-label">Keyword : </form:label>
					</div>
					<div class="col-auto">
						<form:input path="keyword" cssClass="form-control" />
					</div>
					<div class="col-auto">
						<button type="submit" class="btn btn-secondary">
							<span class="fas fa-search"></span> SEARCH
						</button>
						<c:if test="${UserListForm.accessCode >= 20 }">
							<a href="${form }" class="btn btn-primary">
								<span class="fas fa-plus"></span> ADD
							</a>
						</c:if>
					</div>
					<%-- <div class="form-group row">
						<form:label path="keyword" for="keyword" cssClass="col-2 col-form-label">Keyword : </form:label>
						<div class="col-6">
							<form:input path="keyword" cssClass="form-control form-control-sm" />
						</div>
						<div class="col-4">
							<button type="submit" class="btn btn-secondary btn-sm">SEARCH</button>
							<c:if test="${UserListForm.accessCode >= 20 }">
								<spring:url value="/sys/user.do" var="url" />
								<a href="${url }" class="btn btn-primary btn-sm">ADD</a>
							</c:if>
						</div>
					</div> --%>
				</form:form>
			</div>
		</div>
		<div class="card border-top-success shadow">
			<div class="card-body">
				<table class="table table-striped table-bordered table-hover" id="usertable">
					<thead>
						<tr>
							<th>User ID</th>
							<!-- <th>Role ID</th> -->
							<th>Active</th>
							<!-- <th>Allow Login As</th> -->
							<th>Name</th>
							<th>Email</th>
							<!-- <th>Role</th> -->
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
<spring:url value="/sys/user/ajax.do" var="url" />
<script type="text/javascript">
	$(document).ready(
			function() {
				$("form").submit(function(e) {
					e.preventDefault();
					usertable.ajax.url("${url}?keyword=" + $("#keyword").val()).load();
				});

				usertable = $("#usertable").DataTable(
						{
							responsive : true,
							searching : false,
							serverSide : true,
							processing : true,
							pageLength : Number('${BaseForm.pageSize}'),
							displayStart : 0,
							paging : true,
							lengthChange : false,
							filter : false,
							info : true,
							autoWidth : false,
							ordering : false,
							ajax : {
								url : "${url}",
								dataSrc : 'data'
							},
							columns : [
									{
										data : 'userId'
									},
									{
										data : null,
										render : function(data, type, full, meta) {
											return data.isActive ? "YES" : "NO";
										}
									},
									{
										data : 'name',
										responsivePriority : 1
									},
									{
										data : 'email',
										orderable : false
									},
									{
										data : null,
										responsivePriority : 2,
										orderable : false,
										data : null,
										//className : 'btn-group',
										render : function(data, type, full, meta) {
											var role = '<button type="button" class="btn btn-sm btn-outline-info" title="Role" onclick="userRole(\'' + data.userId
													+ '\')"><span class="fas fa-key"></span></button>';
											var butt1 = '<a href="${form}?userId=' + data.userId
													+ '" class="btn btn-sm btn-outline-secondary" title="Edit"><span class="fas fa-cog"></span></a>';
											var butt2 = '<button type="button" class="btn btn-sm btn-outline-warning" title="Delete" onclick="deleteUser(\'' + data.userId
													+ '\')"><span class="fas fa-trash"></span></button>';
											return '<div class="btn-group">' + role + butt1 + butt2 + '</div>';
										}
									}]
						});
			})
</script>