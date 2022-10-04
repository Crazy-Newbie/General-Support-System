<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	var itemtable;

	function setAction(val) {
		$("#action").val(val);
	}
</script>
<div class="row">
	<div class="col-12">
		<div class="card border-top-success shadow">
			<div class="card-body">
				<table
					class="table table-sm table-striped table-bordered table-hover"
					id="itemtable">
					<thead>
						<tr>
							<th>No</th>
							<th>Log Date</th>
							<th>Status</th>
							<th>Status Note</th>
							<th>Updated By</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${logs }" var="item" varStatus="status">
							<tr>
								<td>${status.index + 1 }</td>
								<%-- <td>${item.logDateString }</td> --%>
								<td><fmt:formatDate value="${item.id.logDate }"
										pattern="dd-MM-YYYY hh:mm a" /></td>
								<td>${item.statusDesc }</td>
								<td>${item.statusNote }</td>
								<td>${item.updatedBy }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#itemtable").DataTable({
			searching : true,
			ordering : false,
			paging : false,
			lengthChange : false
		})
	})
</script>
