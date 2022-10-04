<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<spring:url value="/helper/organization.do" var="getURL" />
<script type="text/javascript">
	var itemtable;
	$(document)
		.ready(
			function() {
				itemtable = $("#orgtable")
					.DataTable(
						{
							responsive : true,
							searching : false,
							paging : true,
							bLengthChange : false,
							bFilter : false,
							bInfo : true,
							autoWidth : false,
							ajax : {
								url : "${getURL}",
								dataSrc : ''
							},
							columns : [ {
								data : 'orgId'
							}, {
								data : 'description',
								responsivePriority : 1
							}, {
								data : 'orgLevel'
							} ],
							columnDefs : [ {
								targets : 3,
								orderable : false,
								data : null,
								responsivePriority : 2,
								render : function(data, type,
									full, meta) {
									return '<button type="button" class="btn btn-outline-success btn-sm btn-circle" onclick="selectItem(\''
										+ data.orgId
										+ '\',\''
										+ data.description
										+ '\',\''
										+ data.orgLevel
										+ '\')"'
										+ '><i class="fa fa-check"></i></button>';

								}
							} ]
						});

				$("form").submit(
					function(e) {
						e.preventDefault();
						itemtable.ajax.url(
							"${getURL}?keyword="
							+ $("#keyword").val())
							.load();
					})
			})

	function selectItem(orgid, orgname, type) {
		var obj = new Object();
		obj.form = "organization";
		obj.orgId = orgid;
		obj.orgName = orgname;
		obj.type = type;

		window.opener.receiveCallback(obj);
		window.close();
	}
</script>
<div class="row">
	<div class="col-12">
		<div class="card card-block">
			<form:form commandName="LookupForm" cssClass="form-horizontal">
				<div class="form-group">
					<form:label path="keyword" for="keyword"
						cssClass="col-xs-4 col-form-label">Keyword</form:label>
					<div class="col-xs-8">
						<div class="input-group">
							<form:input path="keyword"
								cssClass="form-control form-control-sm" required="required"
								autofocus="autofocus" autocomplete="off" />
							<div class="input-group-append">
								<button type="submit" value="SEARCH"
									class="btn btn-primary btn-sm">SEARCH</button>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div class="col-12">
		<div class="card card-block">
			<div class="datatablediv col-md-12">
				<table class="table table-striped table-hover" id="orgtable">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Type</th>
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