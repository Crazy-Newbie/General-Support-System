<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<spring:url value="/helper/employee.do" var="getEmployeeURL" />
<script type="text/javascript">
	var itemtable;
	$(document).ready(
			function() {
				itemtable = $("#employeetable").DataTable(
						{
							responsive : true,
							searching : false,
							serverSide : true,
							pageLength : 10,
							displayStart : 0,
							processing : true,
							paging : true,
							lengthChange : false,
							filter : false,
							info : true,
							autoWidth : false,
							ordering : false,
							ajax : {
								url : "${getEmployeeURL}",
								dataSrc : 'data'
							},
							columns : [
									{
										data : 'emplid',
										responsivePriority : 3
									},
									{
										data : 'nameDisplay',
										responsivePriority : 1
									},
									{
										data : 'deptDesc'
									},
									{
										data : 'sectionDesc'
									},
									{
										data : null,
										responsivePriority : 2,
										render : function(data, type, full, meta) {
											return '<button type="button" class="btn btn-success btn-circle" onclick="selectItem(\'' + data.emplid + '\',\'' + data.nameDisplay + '\',\''
													+ (data.email == null ? '' : data.email) + '\',\'' + data.deptId + '\',\'' + data.deptDesc + '\',\'' + data.sectionId + '\',\'' + data.sectionDesc
													+ '\',\'' + data.positionNbr + '\',\'' + data.positionDesc + '\')"' + '><i class="fas fa-check"></i></button>';

										}
									}]
						});

				$("form").submit(function(e) {
					e.preventDefault();
					itemtable.ajax.url("${getEmployeeURL}?" + $("#LookupForm").serialize()).load();
				})
			})

	function selectItem(emplid, name, email, deptid, deptname, sectid, sectname, posid, postitle, salGrade, hireDt, spouseNm, address) {
		var obj = new Object();
		obj.form = "employee";
		obj.employeeId = emplid;
		obj.emplId = emplid;
		obj.name = name;
		obj.email = email;
		obj.deptId = deptid;
		obj.deptName = deptname;
		if (sectid == 'null')
			obj.sectId = '';
		else
			obj.sectId = sectid;
		if (sectname == 'null')
			obj.sectName = '';
		else
			obj.sectName = sectname;
		obj.posId = posid;
		obj.posTitle = postitle;

		window.opener.receiveCallback(obj);
		window.close();
	}
</script>
<div class="col-12">
	<div class="card border-top-primary shadow">
		<div class="card-body">
			<form:form commandName="LookupForm" cssClass="form-horizontal">
				<div class="row gy-2 gx-3 align-items-center">
					<div class="col-auto">
						<form:label path="keyword" for="keyword" cssClass="col-form-label">Keyword</form:label>
					</div>
					<div class="col-auto">
						<form:input path="keyword" cssClass="form-control" required="required" autofocus="autofocus" autocomplete="off" />
					</div>
					<div class="col-auto">
						<button type="submit" value="SEARCH" class="btn btn-primary">
							<span class="fas fa-search"></span> SEARCH
						</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div class="card boder-top-success shadow">
		<div class="card-body">
			<table class="table table-striped table-hover table-sm" id="employeetable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Department</th>
						<th>Section</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</div>