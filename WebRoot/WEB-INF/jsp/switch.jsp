<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	var usertable;
	var submitted = false;
	function switchTo(userId) {
		$("#userId").val(userId);

		Swal.fire({
			title : 'Switch to this user?',
			icon : 'question',
			showCancelButton : true
		}).then(function(resp) {
			if (resp.isConfirmed) {
				submitted = true;
				$("form").submit();
			}
		})
	}
</script>
<div class="row">
	<div class="col-12">
		<div class="card border-top-primary shadow">
			<div class="card-body">
				<form:form commandName="UserForm" action="switch.do" method="POST" autocomplete="false">
					<div class="form-group row">
						<form:hidden path="userId" />
						<form:label path="keyword" for="keyword" cssClass="col-2 col-form-label">Keyword : </form:label>
						<div class="col-8">
							<form:input path="keyword" cssClass="form-control form-control-sm" />
						</div>
						<div class="col-2">
							<button type="submit" class="btn btn-primary btn-sm">SEARCH</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div class="card border-top-success shadow">
			<div class="card-body">
				<table class="table table-striped table-bordered table-hover" id="users">
					<thead>
						<tr>
							<th>User ID</th>
							<th>Name</th>
							<th>Email</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<spring:url value="/helper/user.do" var="userURL" />
<script type="text/javascript">
	$(document).ready(function() {
		$("form").submit(function(e) {
			if (!submitted) {
				e.preventDefault();
				usertable.ajax.url("${userURL}?" + $("form").serialize()).load();
			}
		});

		usertable = $("#users").DataTable({
			searching : false,
			serverSide : true,
			pageLength : Number('${BaseForm.pageSize}'),
			displayStart : 0,
			paging : true,
			info : true,
			lengthChange : false,
			ajax : {
				url : "${userURL}",
				dataSrc : 'data'
			},
			columns : [{
				data : "userId"
			}, {
				data : "name"
			}, {
				data : "email"
			}, {
				orderable : false,
				data : null,
				className : 'action-row',
				render : function(data, type, full, meta) {
					return '<button type="button" class="btn btn-sm btn-primary" onclick="switchTo(\'' + data.userId + '\')"><i class="fa fa-user"></i> Switch</button>';
				}
			}]
		})
	})
</script>