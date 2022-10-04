<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<spring:url value="/helper/group.do?keyword=" var="getGroupURL" />
<script type="text/javascript">
	var itemtable;
	$(document)
		.ready(
			function() {
				itemtable = $("#itemtable")
					.DataTable(
						{
							responsive : true,
							searching : false,
							paging : true,
							bLengthChange : false,
							bFilter : false,
							bInfo : true,
							ajax : {
								url : "${getGroupURL}",
								dataSrc : ''
							},
							columns : [ {
								data : 'groupId'
							}, {
								data : 'groupName',
								responsivePriority : 1
							} ],
							columnDefs : [ {
								targets : 2,
								orderable : false,
								data : null,
								responsivePriority : 2,
								render : function(data, type,
									full, meta) {
									return '<button type="button" class="btn btn-success btn-circle" onclick="selectItem(\''
										+ data.groupId
										+ '\',\''
										+ data.groupName
										+ '\')"'
										+ '><i class="fa fa-check"></i></button>';

								}
							} ]
						});

				$("form").submit(
					function(e) {
						e.preventDefault();
						itemtable.ajax.url(
							"${getGroupURL}"
							+ $("#keyword").val())
							.load();
					})
			})

	function selectItem(groupid, groupname) {
		var obj = new Object();
		obj.form = "group";
		obj.groupId = groupid;
		obj.groupName = groupname;

		window.opener.receiveCallback(obj);
		window.close();
	}
</script>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="col-md-12">
					<form:form commandName="LookupForm" cssClass="form-horizontal">
						<div class="form-group">
							<form:label path="keyword" for="keyword" cssClass="col-xs-2 col-form-label">Keyword</form:label>
							<div class="col-xs-8">
								<form:input path="keyword" cssClass="form-control form-control-sm"
									required="required" autofocus="autofocus" autocomplete="off" />
							</div>
							<div class="col-xs-2">
								<button type="submit" value="SEARCH"
									class="btn btn-primary btn-sm">SEARCH</button>
							</div>
						</div>
					</form:form>
				</div>
				<div class="datatablediv col-md-12">
					<table class="table table-striped table-hover" id="itemtable">
						<thead>
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>