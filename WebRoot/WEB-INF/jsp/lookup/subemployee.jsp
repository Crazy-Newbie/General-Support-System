<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<spring:url value="/helper/subemployee.do" var="getEmployeeURL" />
<script type="text/javascript">
	var itemtable;
	$(document)
		.ready(
			function() {
				itemtable = $("#employeetable")
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
								url : "${getEmployeeURL}",
								dataSrc : 'data'
							},
							columns : [ {
								data : 'emplid',
								responsivePriority : 3
							}, {
								data : 'nameDisplay',
								responsivePriority : 1
							}, {
								data : 'deptDesc'
							}, {
								data : 'sectionDesc'
							} ],
							columnDefs : [ {
								targets : 4,
								orderable : false,
								data : null,
								responsivePriority : 2,
								render : function(data, type,
									full, meta) {
									return '<button type="button" class="btn btn-success btn-circle" onclick="selectItem(\''
										+ data.emplid
										+ '\',\''
										+ data.nameDisplay
										+ '\',\''
										+ (data.email == null ? '' : data.email)
										+ '\',\''
										+ data.deptId
										+ '\',\''
										+ data.deptDesc
										+ '\',\''
										+ data.sectionId
										+ '\',\''
										+ data.sectionDesc
										+ '\',\''
										+ data.positionNbr
										+ '\',\''
										+ data.positionDesc
										+ '\')"'
										+ '><i class="fa fa-check"></i></button>';

								}
							} ]
						});

				$("form").submit(
					function(e) {
						e.preventDefault();
						itemtable.ajax.url(
							"${getEmployeeURL}?"
							+ $("#LookupForm").serialize())
							.load();
					})
			})

	function selectItem(emplid, name, email, deptid, deptname, sectid,
		sectname, posid, postitle, salGrade, hireDt, spouseNm, address) {
		var obj = new Object();
		obj.form = "subemployee";
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
<section class="section">
	<div class="col-12 p-0">
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
			<table class="table table-striped table-hover" id="employeetable">
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
</section>