<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/sys/init/catalog.do" var="cat" />
<script type="text/javascript">
	function addRow() {
		var curr = $("#itemtable tbody tr").length;
		var body = '<tr id="row-' + curr + '">';
		body += '<td><input id="appCats' + curr + '.id.ttyp" class="form-control form-control-sm" name="appCats[' + curr + '].id.ttyp" required="required" type="text" placeholder="TTYP..." /></td>';
		body += '<td><input id="appCats' + curr + '.id.tcode" class="form-control form-control-sm" name="appCats[' + curr + '].id.tcode" required="required" type="text" placeholder="TCODE..." /></td>';
		body += '<td><input id="appCats' + curr + '.tval" class="form-control form-control-sm" name="appCats[' + curr + '].tval" required="required" type="text" placeholder="Catalog Value..." /></td>';
		body += '<td><input id="appCats' + curr + '.tattr1" class="form-control form-control-sm" name="appCats[' + curr + '].tattr1" type="text" /></td>';
		body += '<td><input id="appCats' + curr + '.tattr2" class="form-control form-control-sm" name="appCats[' + curr + '].tattr2" type="text" /></td>';
		body += '<td><input id="appCats' + curr + '.tattr3" class="form-control form-control-sm" name="appCats[' + curr + '].tattr3" type="text" /></td>';
		//body += '<td><input id="appCats' + curr + '.tattr4" class="form-control form-control-sm" name="appCats[' + curr + '].tattr4" type="text" /></td>';
		//body += '<td><input id="appCats' + curr + '.tattr5" class="form-control form-control-sm" name="appCats[' + curr + '].tattr5" type="text" /></td>';
		body += '<td><input id="appCats' + curr + '.tattr6" class="form-control form-control-sm" name="appCats[' + curr + '].order" type="text" /></td>';
		body += '<td colspan="2"><button type="button" onclick="deleteRow(' + curr
				+ ')" class="btn btn-block btn-sm btn-outline-danger"><span class="fas fa-times"></span></td>';
		body += '</tr>';
		$("#itemtable > tbody").append(body);
	}

	function deleteRow(row) {
		$("#row-" + row).remove();
	}

	function recat() {
		/* bootbox.confirm("Re-Initialize Catalog?", function(e) {
			if (e) {
				$.post("${cat}", null).done(function(e) {
					bootbox.alert(e.desc, function() {
						window.location.reload();
					});
				}).fail(function() {
					bootbox.alert("Request failed");
				})
			}
		}) */
		Swal.fire({
			title : 'Re-Initialize Catalog?',
			showCancelButton : true
		}).then(function(res) {
			if (res.isConfirmed) {
				$.post("${cat}", null).done(function(e) {
					Swal.fire({
						title : e.desc,
						icon : 'success'
					}).then(function() {
						window.location.reload();
					});
				}).fail(function() {
					/* bootbox.alert("Request failed"); */
					Swal.fire({
						title : "Request failed",
						icon : "error"
					});
				})
			}
		});
	}
</script>
<div class="row">
	<div class="col-12">
		<form:form action="catalog.do" autocomplete="false" method="POST" commandName="CatalogListForm">
			<div class="card card-block shadow border-top-primary">
				<div class="card-body row">
					<div class="col-12 mb-2">
						<button type="submit" class="btn btn-success" title="SUBMIT">
							<span class="far fa-save"></span> SUBMIT
						</button>
						<button type="button" class="btn btn-secondary" onclick="recat()">
							<span class="fas fa-sync"></span> RE-INIT CATALOG
						</button>
					</div>
					<div class="col-12">
						<table class="table table-stripped table-bordered table-hovered" id="itemtable">
							<thead>
								<tr>
									<th>Catalog Type</th>
									<th>Catalog ID</th>
									<th>Value</th>
									<th>Tattr1</th>
									<th>Tattr2</th>
									<th>Tattr3</th>
									<!-- <th>Tattr4</th>
								<th>Tattr5</th> -->
									<th class="action-row">Order</th>
									<th class="action-row">Active</th>
									<th class="action-row">Delete</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${CatalogListForm.appCats }" var="item" varStatus="status">
									<tr>
										<form:hidden path="appCats[${status.index }].id.ttyp" />
										<form:hidden path="appCats[${status.index }].id.tcode" />
										<td>${item.id.ttyp }</td>
										<td>${item.id.tcode }</td>
										<td><form:input path="appCats[${status.index }].tval" cssClass="form-control form-control-sm" required="required" /></td>
										<td><form:input path="appCats[${status.index }].tattr1" cssClass="form-control form-control-sm" /></td>
										<td><form:input path="appCats[${status.index }].tattr2" cssClass="form-control form-control-sm" /></td>
										<td><form:input path="appCats[${status.index }].tattr3" cssClass="form-control form-control-sm" /></td>
										<td><form:input path="appCats[${status.index }].orderBy" cssClass="form-control form-control-sm" /></td>
										<td><form:checkbox path="appCats[${status.index }].active" data-toggle="toggle" data-size="small" data-on="Y" data-off="N" /></td>
										<td><form:checkbox path="appCats[${status.index }].delete" data-toggle="toggle" data-size="small" data-on="DELETE" data-onstyle="danger" data-offstyle="secondary" data-off="KEEP" /></td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="11">
										<button class="btn btn-block btn-outline-primary" type="button" onclick="addRow()">
											<span class="fas fa-plus"></span> ADD
										</button>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>