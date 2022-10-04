<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Form List</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<spring:url value="/sys/form.do" var="url" />
						<a href="${url }" class="btn btn-primary">ADD</a>
					</div>
					<div class="col-lg-12 col-md-12">
						<table class="table table-striped table-bordered table-hover" id="formtable">
							<thead>
								<tr>
									<!-- <th>Survey ID</th> -->
									<th>Form No</th>
									<th>Form Desc</th>
									<th>Action</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<spring:url value="/sys/form/getforms.do" var="url" />
<spring:url value="/sys/form.do" var="urlform" />
<script type="text/javascript">
	var formtable;
	$(document).ready(function() {
		formtable = $("#formtable")
			.DataTable(
				{
					responsive : true,
					searching : true,
					paging : true,
					bLengthChange : false,
					bFilter : false,
					bInfo : true,
					autoWidth : false,
					ajax : {
						url : "${url}",
						dataSrc : ''
					},
					columns : [ {
						data : 'formNo'
					}, {
						data : 'formDescription'
					} ],
					columnDefs : [
						{
							targets : 0,
							responsivePriority : 1
						},
						{
							targets : 2,
							responsivePriority : 2,
							orderable : false,
							data : null,
							class : 'action-row',
							render : function(data,
								type, full,
								meta) {
								var butt1 = '<a href="${urlform}?formNo='
									+ data.formNo
									+ '&action=UPDATE" class="btn btn-secondary" title="Edit"><span class="fa fa-gear"></span></a>';
								return butt1;
							}
						} ]
				});
	})
</script>