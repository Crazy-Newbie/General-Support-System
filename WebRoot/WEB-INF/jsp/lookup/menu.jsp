<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/helper/menu.do" var="getMenuURL" />
<script type="text/javascript">
	function selectItem(id, name, order, url, lvl, parent) {
		var obj = new Object();
		obj.form = "menu";
		obj.menuId = id;
		obj.menuName = name;
		obj.menuOrder = order;
		obj.menuUrl = url;
		obj.menuLevel = lvl;
		obj.menuParent = parent;

		window.opener.receiveCallback(obj);
		window.close();
	}
</script>
<div class="col-12">
	<div class="card border-top-primary shadow">
		<div class="card-body">
			<table class="table table-striped table-hover" id="menutable">
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
			$("#menutable")
				.DataTable(
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
							url : "${getMenuURL}",
							dataSrc : ''
						},
						columns : [ {
							data : 'menuId'
						}, {
							data : 'menuName',
							responsivePriority : 1
						} ],
						columnDefs : [ {
							targets : 2,
							orderable : false,
							data : null,
							responsivePriority : 2,
							className : 'action-row',
							render : function(data, type,
								full, meta) {
								/* return '<button type="button" class="btn btn-success btn-circle" onclick="selectItem(\''
								 + data
								 + '\')"><i class="fa fa-plus"></i></button>'; */
								return '<button type="button" class="btn btn-success btn-sm" onclick="selectItem(\''
									+ data.menuId
									+ '\',\''
									+ data.menuName
									+ '\',\''
									+ data.menuOrder
									+ '\',\''
									+ data.menuUrl
									+ '\',\''
									+ data.menuLvl
									+ '\',\''
									+ data.menuParent
									+ '\')"'
									+ '><i class="fa fa-check"></i></button>';

							}
						} ]
					});
			$("input").focus();
		})
</script>