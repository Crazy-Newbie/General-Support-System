<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/helper/form.do" var="getFormURL" />
<script type="text/javascript">
	function selectItem(no, desc) {
		var obj = new Object();
		obj.form = "form";
		obj.formNo = no;
		obj.formDesc = desc;

		window.opener.receiveCallback(obj);
		window.close();
	}
</script>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="datatablediv col-md-12">
					<table class="table table-striped table-hover" id="formtable">
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
<script type="text/javascript">
	$(document)
		.ready(
			function() {
				$("#formtable")
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
								url : "${getFormURL}",
								dataSrc : ''
							},
							columns : [ {
								data : 'formNo',
								responsivePriority : 1
							}, {
								data : 'formDescription'
							} ],
							columnDefs : [ {
								targets : 2,
								orderable : false,
								data : null,
								responsivePriority : 2,
								render : function(data, type,
									full, meta) {
									/* return '<button type="button" class="btn btn-success btn-circle" onclick="selectItem(\''
									    + data
									    + '\')"><i class="fa fa-plus"></i></button>'; */
									return '<button type="button" class="btn btn-success btn-circle" onclick="selectItem(\''
										+ data.formNo
										+ '\',\''
										+ data.formDescription
										+ '\')"'
										+ '><i class="fa fa-check"></i></button>';

								}
							} ]
						})
				$("input").focus();
			})
</script>