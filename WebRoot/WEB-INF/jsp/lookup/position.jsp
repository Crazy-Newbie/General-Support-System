<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<section class="section">
	<div class="col-12 p-0">
		<div class="card card-block">
			<form:form commandName="LookupForm" cssClass="form-horizontal">
				<div class="form-group row">
					<form:label path="keyword" for="keyword"
						cssClass="col-2 col-form-label">Keyword</form:label>
					<div class="col-8">
						<form:input path="keyword" cssClass="form-control form-control-sm"
							required="required" autofocus="autofocus" autocomplete="off" />
					</div>
					<div class="col-2">
						<button type="submit" value="SEARCH"
							class="btn btn-primary btn-sm">SEARCH</button>
					</div>
				</div>
			</form:form>
		</div>
		
		<div class="card card-block">
			<table class="table table-striped table-hover" id="itemtable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</section>

<spring:url value="/helper/ptbposition.do" var="getPosition" />
<script type="text/javascript">
var itemtable;
$(document).ready(function() {
			
	$("form").submit(function(e) {
		e.preventDefault();
		itemtable.ajax.url(
			"${getPosition}?"
			+ $("#LookupForm").serialize())
			.load();
	})

	itemtable = $("#itemtable").DataTable({
		responsive : true,
		searching : false,
		serverSide : true,
		displayLength : 10,
		displayStart : 0,
		processing : true,
		paging : true,
		bLengthChange : false,
		bFilter : false,
		bInfo : true,
		autoWidth : false,
		ajax : {
			url : "${getPosition}",
			dataSrc : 'data'
		},
		columns : [ {
			data : 'positionId'
		}, {
			data : 'posTitle',
			responsivePriority : 1
		}],
		columnDefs : [ {
			targets : 2,
			orderable : false,
			data : null,
			responsivePriority : 2,
			render : function(data, type,
				full, meta) {
				return '<button type="button" class="btn btn-success btn-circle" onclick="selectItem(\''
					+ data.positionId
					+ '\',\''
					+ data.posTitle
					+ '\',\''
					+ data.department
					+ '\',\''
					+ data.section
					+ '\')"'
					+ '><i class="fa fa-check"></i></button>';

			}
		} ]
	})
});

function selectItem(posid, name) {
	var obj = new Object();
	obj.form = "position";
	obj.posId = posid;
	obj.posTitle = name;

	window.opener.receiveCallback(obj);
	window.close();
}
</script>