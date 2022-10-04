<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/pm/colcode/form.do" var="form" />
<script type="text/javascript">
	var itemtable;

	function newForm() {
		windowWithoutToolbar("${form}", 640, 480);
	}

	function editForm(year, smt) {
		windowWithoutToolbar("${form}?year=" + year + "&semester=" + smt, 640, 480);
	}

	function delForm(year, smt) {
		Swal.fire({
			text : 'Are you sure you want to delete this data?',
			icon : 'question',
			showCancelButton : true
		}).then(function(resp) {
			if (resp.isConfirmed) {
				$.post("${form}", {
					action : 'DELETE',
					year : year,
					semester : smt
				}).done(function(ret) {
					if (ret.success)
						itemtable.ajax.reload(null, false);
					else Swal.fire({
						icon : ret.icon,
						text : ret.desc
					})
				})
			}
		})
	}

	function reloadTable() {
		itemtable.ajax.reload(null, false);
	}
</script>
<div class="row">
	<div class="col-12">
		<div class="card border-top-primary shadow">
			<div class="card-body">
				<form:form commandName="ColCodedListForm">
					<form:hidden path="accessCode" />
					<div class="row g-1">
						<div class="col-12 col-sm-auto">
							<form:input path="tahun" cssClass="form-control"
								placeholder="Tahun..." type="number" step="1" min="1970"
								max="2999" />
						</div>
						<div class="col-12 col-sm-auto">
							<form:select path="semester" cssClass="form-select">
								<form:option value="">All Semester</form:option>
								<form:options items="${CatalogOrdered.codes['SEMESTER'] }"
									itemLabel="tval" itemValue="id.tcode" />
							</form:select>
						</div>
						<div class="col-12 col-sm-auto">
							<button type="submit" class="btn btn-outline-primary"
								title="SEARCH">
								<span class="fas fa-search"></span> SEARCH
							</button>
							<button type="button" class="btn btn-outline-success" title="ADD"
								onclick="newForm()">
								<span class="fas fa-plus"></span> ADD
							</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<div class="col-12">
		<div class="card border-top-success shadow">
			<div class="card-body">
				<table class="table table-striped table-bordered table-sm"
					id="itemtable">
					<thead>
						<tr>
							<th>Year</th>
							<th>Semester</th>
							<th>Color</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<spring:url value="/pm/colcode/list/data.do" var="data" />
<script type="text/javascript">
	$(document).ready(
			function() {

				$("form").submit(function(e) {
					e.preventDefault();
					itemtable.ajax.url("${data}?" + $("form").serialize()).load();
				})

				itemtable = $("#itemtable").DataTable(
						{
							paging : false,
							pageLength : 10,
							searching : false,
							ordering : false,
							ajax : {
								url : '${data}',
								method : 'POST',
								dataSrc : ''
							},
							columns : [
									{
										data : 'year'
									},
									{
										data : 'semester'
									},
									{
										data : 'color',
										render : function(dt) {
											//return '<span style="color:'+dt+'">' + dt + '</span>';
											return '<button type="button" class="btn btn-sm btn-active w-100" title="'+dt+'" style="background-color:'+dt+'; color:'+dt+'">' + dt + '</button>';
										}
									},
									{
										data : 'startDate',
										render : function(dt) {
											return dayjs(dt).format("DD-MMM-YYYY");
										}
									},
									{
										data : 'endDate',
										render : function(dt) {
											return dayjs(dt).format("DD-MMM-YYYY");
										}
									},
									{
										data : null,
										className : 'action-row',
										render : function(dt) {
											var edit = '';
											var del = '';
											if ($("#accessCode").val() >= 30)
												edit = '<button type="button" class="btn btn-outline-primary btn-sm" title="EDIT" onclick="editForm(\'' + dt.year + '\',\'' + dt.semester
														+ '\')"><span class="fas fa-fw fa-edit"></span></button>';
											if ($("#accessCode").val() >= 40)
												del = '<button type="button" class="btn btn-outline-danger btn-sm" title="DELETE" onclick="delForm(\'' + dt.year + '\',\'' + dt.semester
														+ '\')"><span class="fas fa-fw fa-trash"></span></button>';

											return '<div class="btn-group">' + edit + del + '</div>';
										}
									}]
						});
			})
</script>