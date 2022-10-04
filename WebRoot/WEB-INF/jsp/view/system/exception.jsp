<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/sys/exception/solved.do" var="solurl" />
<script type="text/javascript">
	var itemtable;
	function solved(excpid) {
		$.post("${solurl}", {
			exceptionId : excpid
		}).done(function(data) {
			if (data.success)
				itemtable.ajax.reload();
			else
				/*bootbox.alert(data.desc);*/
				Swal.fire(data.desc);
		})

	}
</script>
<div class="row">
	<div class="col-12">
		<div class="card card-block">
			<div class="card-body">
				<table class="table table-bordered table-hover" id="itemtable">
					<thead>
						<tr>
							<th>Log Date</th>
							<th>Message</th>
							<th>Action</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>
<spring:url value="/sys/exception/ajax.do" var="url" />
<script type="text/javascript">
	$(document).ready(
			function() {
				itemtable = $("#itemtable").DataTable(
						{
							responsive : true,
							searching : false,
							serverSide : true,
							processing : true,
							ordering : false,
							paging : true,
							lengthChange : false,
							pageLength : 1,
							displayStart : 0,
							bFilter : false,
							bInfo : true,
							autoWidth : false,
							dom : "p",
							orderFixed : [[0, 'asc']],
							ajax : {
								url : "${url}",
								dataSrc : 'data'
							},
							columns : [{
								data : null,
								render : function(data) {
									return dayjs.tz(data.createDate, 'Asia/Makassar').format("DD-MM-YY HH:mm:ss");
									//return dayjs(data.createDate).format("DD-MM-YY HH:mm:ss");
								}
							}, {
								data : 'messages'
							}],
							columnDefs : [
									{
										targets : 1,
										responsivePriority : 1
									},
									{
										targets : 2,
										responsivePriority : 2,
										orderable : false,
										data : null,
										className : 'action-row',
										render : function(data, type, full, meta) {
											var butt1 = '<button type="button" class="btn btn-success btn-sm" title="Solved" onclick="solved(\'' + data.exceptionId
													+ '\')"><span class="fa fa-check"></span> Solved</button>';
											return butt1;
										}
									}]
						});
			})
</script>