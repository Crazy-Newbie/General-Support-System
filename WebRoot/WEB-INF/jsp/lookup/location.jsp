<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/lookup/location.do" var="getLocUrl" />
<script type="text/javascript">
	function selectItem(id, name, idpath) {
		var obj = new Object();
		obj.form = "location";
		obj.locId = id;
		obj.locDesc = name;
		obj.locPathId = idpath;

		window.opener.receiveCallback(obj);
		window.close();
	}
</script>
<div class="col-12">
	<div class="card border-top-primary shadow">
		<div class="card-body">
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
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#itemtable").DataTable(
						{
							responsive : true,
							searching : true,
							paging : true,
							bLengthChange : false,
							bFilter : false,
							bSort : false,
							bInfo : true,
							autoWidth : false,
							ajax : {
								url : "${getLocUrl}",
								dataSrc : '',
								method : 'POST'
							},
							columns : [
									{
										data : 'locationId'
									},
									{
										data : 'locationDesc',
										responsivePriority : 1
									},
									{
										orderable : false,
										data : null,
										responsivePriority : 2,
										className : 'action-row',
										render : function(data, type, full, meta) {
											/* return '<button type="button" class="btn btn-success btn-circle" onclick="selectItem(\''
											 + data
											 + '\')"><i class="fa fa-plus"></i></button>'; */
											return '<button type="button" class="btn btn-success btn-sm" onclick="selectItem(\'' + data.locationId + '\',\'' + data.locationDesc + '\',\''
													+ data.pathId + '\')"' + '><i class="fa fa-check"></i></button>';

										}
									}]
						});
				$("input").focus();
			});
</script>