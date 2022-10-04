<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/team/member.do" var="form" />
<script type="text/javascript">
	var itemtable;

	function deleteItem(usrId) {
		$.post("${form}", {
			teamId : $("#teamId").val(),
			memberId : usrId,
			action : 'REMOVE'
		}, function(data, status) {
			if (data.success) {
				itemtable.ajax.reload(null, false);
			} else {
				Swal.fire(data.desc);
			}
		})
	}

	function addItem(usrId) {
		$.post("${form}", {
			teamId : $("#teamId").val(),
			memberId : usrId,
			action : 'ADD'
		}, function(data, status) {
			if (data) {
				if (data.success)
					itemtable.ajax.reload(null, false);
				else
					Swal.fire(data.desc);
			}
		})
	}
</script>
<div class="row">
	<form:form commandName="TeamMemberForm" action="member.do">
		<form:hidden path="memberId" />
		<form:hidden path="teamId" />
	</form:form>
	<div class="col-md-6">
		<div class="card card-primary">
			<div class="card-header bg-primary">
				<h4 class="card-title mb-0 text-white">Current Member</h4>
			</div>
			<div class="card-body">
				<table class="table table-bordered table-hover" id="itemtable">
					<thead>
						<tr>
							<th>User ID</th>
							<th>User Name</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="card card-info">
			<div class="card-header bg-info">
				<h4 class="card-title mb-0 text-white">Available User</h4>
			</div>
			<div class="card-body">
				<div>
					<input id="user_filter" class="form-control"
						placeholder="User Filter" />
				</div>
				<div>
					<table class="table table-bordered table-hover" id="employee">
						<thead>
							<tr>
								<th>Empl. ID</th>
								<th>Name</th>
								<th>Action</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<spring:url value="/team/member/ajax.do" var="getPartyURL">
	<spring:param name="teamId">${TeamMemberForm.teamId }</spring:param>
</spring:url>
<spring:url value="/helper/user.do" var="getUserURL" />
<script type="text/javascript">
	var accessCode = Number('${TeamMemberForm.accessCode}');
	var emptable;
	var typingTimer;                //timer identifier
	var doneTypingInterval = 500;  //time in ms, 0.5 second
	$(document).ready(function() {

		itemtable = $("#itemtable")
			.DataTable(
				{
					responsive : true,
					searching : true,
					paging : true,
					bInfo : true,
					bLengthChange : false,
					autoWidth : false,
					ajax : {
						url : "${getPartyURL}",
						dataSrc : null
					},
					columns : [ {
						data : 'id.memberId',
						className : 'action-row'
					}, {
						data : 'appUser.name'
					}, {
						data : null, //alias all data
						orderable : false,
						className : 'action-row',
						render : function(data, type,
							full, meta) {
							if (accessCode >= 40)
								return '<button type="button" class="btn btn-outline-danger btn-sm" onclick="deleteItem(\''
									+ data.id.memberId
									+ '\')"><i class="far fa-trash-alt"></i></button>';
							else return '';
						}
					} ]
				});


		emptable = $("#employee")
			.DataTable(
				{
					responsive : true,
					searching : false,
					processing : true,
					paging : true,
					bLengthChange : false,
					bFilter : false,
					bInfo : false,
					autoWidth : false,
					serverSide : true,
					pageLength : 10,
					displayStart : 0,
					ordering : false,
					ajax : {
						url : "${getUserURL}",
						dataSrc : 'data'
					},
					columns : [ {
						data : 'userId'
					}, {
						data : 'name'
					} ],
					columnDefs : [ {
						targets : 2,
						orderable : false,
						data : null,
						class : 'action-row',
						render : function(data, type,
							full, meta) {
							return '<button type="button" class="btn btn-outline-success btn-sm" onclick="addItem(\''
								+ data.userId
								+ '\')"><i class="fas fa-plus"></i></button>';
						}
					} ]
				});
		
		$("#user_filter").keyup(function() {
			clearTimeout(typingTimer);
  			typingTimer = setTimeout(function(){
				emptable.ajax.url("${getUserURL}?keyword=" + $("#user_filter").val()).load();
  			}, doneTypingInterval);
		});
		
		$("#user_filter").keydown(function() {
			clearTimeout(typingTimer);
		});

	})
</script>