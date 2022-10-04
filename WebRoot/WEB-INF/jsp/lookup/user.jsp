<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<spring:url value="/helper/user.do" var="getURL" />
<script type="text/javascript">
	var itemtable;
	$(function() {
		itemtable = $("#itemtable")
			.DataTable(
				{
					responsive : true,
					searching : false,
					serverSide : true,
					displayLength : 10,
					displayStart : 0,
					processing : true,
					paging : true,
					bLengthChange : false,
					bFilter : false,
					bInfo : true,
					autoWidth : false,
					ajax : {
						url : "${getURL}",
						dataSrc : 'data'
					},
					columns : [ {
						data : 'userId',
						responsivePriority : 3
					}, {
						data : 'name',
						responsivePriority : 1
					}, {
						data : 'appRole.roleId'
					}, {
						orderable : false,
						data : null,
						responsivePriority : 2,
						render : function(data, type,
							full, meta) {
							return '<button type="button" class="btn btn-outline-success btn-sm" onclick="selectItem(\''
								+ data.userId
								+ '\',\''
								+ data.name
								+ '\',\''
								+ data.email
								+ '\',\''
								+ data.appRole.roleId
								+ '\')"'
								+ '><i class="fa fa-check"></i></button>';

						}
					} ]
				});

		$("form").submit(
			function(e) {
				e.preventDefault();
				itemtable.ajax.url(
					"${getURL}?"
					+ $("#LookupForm").serialize())
					.load();
			});
	});

	function selectItem(userid, name, email, role) {
		var obj = new Object();
		obj.form = "user";
		obj.userId = userid;
		obj.name = name;
		obj.email = email;
		obj.roleId = role;
		window.opener.receiveCallback(obj);
		window.close();
	}
</script>
<section class="section">
	<div class="col-12">
		<div class="card card-block">
			<form:form commandName="LookupForm" cssClass="form-horizontal">
				<div class="form-group row">
					<form:label path="keyword" for="keyword"
						cssClass="col-2 col-form-label">Keyword</form:label>
					<div class="col-8">
						<form:input path="keyword" cssClass="form-control form-control-sm"
							required="required" autofocus="autofocus" autocomplete="off" />
					</div>
					<div class="col-2">
						<button type="submit" value="SEARCH"
							class="btn btn-primary btn-sm">SEARCH</button>
					</div>
				</div>
			</form:form>
		</div>
		<div class="card card-block">
			<table class="table table-striped table-hover" id="itemtable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Role</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</section>