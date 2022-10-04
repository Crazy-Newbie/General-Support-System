<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	var logtable;
</script>
<div class="row">
	<div class="col-12">
		<div class="card card-block shadow border-top-primary">
			<div class="card-body">
				<form:form action="list.do" cssClass="row gy-2 gx-3 align-items-end" autocomplete="false" method="POST" commandName="LogForm">
					<div class="col-auto">
						<form:label path="startDate" for="startDate" cssClass="col-form-label">Start Date :</form:label>
						<div class="input-group">
							<form:input path="startDate" class="form-control" placeholder="Start" required="required" />
							<div class="input-group-text">
								<span class="fas fa-calendar"></span>
							</div>
						</div>
					</div>
					<div class="col-auto">
						<form:label path="endDate" for="endDate" cssClass="col-form-label">End Date :</form:label>
						<div class="input-group">
							<form:input path="endDate" class="form-control" placeholder="End" required="required" />
							<div class="input-group-text">
								<span class="fas fa-calendar"></span>
							</div>
						</div>
					</div>
					<div class="col-auto">
						<form:label path="keyword" for="keyword" cssClass="col-form-label">Keyword :</form:label>
						<form:input path="keyword" cssClass="form-control" />
					</div>
					<div class="col-12 col-sm-auto">
						<button type="submit" class="btn btn-success">SEARCH</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<div class="col-12">
		<div class="card card-block shadow border-top-success">
			<div class="card-body">
				<table class="table table-striped table-bordered table-hover" id="logtable">
					<thead>
						<tr>
							<th>User ID</th>
							<th>IP Address</th>
							<th>Log Time</th>
							<th>Log Type</th>
							<th>Arguments</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<spring:url value="/sys/log/ajax.do" var="logURL" />
<script type="text/javascript">
	$(document).ready(function() {

		$("#startDate").flatpickr({
			enableTime : true,
			dateFormat : 'd-m-Y H:i',
			maxDate : new Date()
		});

		$("#endDate").flatpickr({
			enableTime : true,
			dateFormat : 'd-m-Y H:i',
			maxDate : new Date()
		});

		$("form").submit(function(e) {
			e.preventDefault();
			logtable.ajax.url("${logURL}?" + $("form").serialize()).load();
		});

		logtable = $("#logtable").DataTable({
			responsive : true,
			searching : false,
			processing : true,
			serverSide : true,
			displayStart : 0,
			pageLength : Number('${BaseForm.pageSize}'),
			paging : true,
			bSort : false,
			bLengthChange : false,
			bFilter : false,
			bInfo : true,
			autoWidth : false,
			ajax : {
				url : "${logURL}?" + $("form").serialize(),
				dataSrc : 'data'
			},
			columns : [{
				data : 'id.userId'
			}, {
				data : 'ipAddress'
			}, {
				data : 'logTimeString'
			}, {
				data : 'logType'
			}, {
				data : 'arguments'
			}]
		});
	});
</script>