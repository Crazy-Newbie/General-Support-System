<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<spring:url value="/sys/scheduler/job/enable.do" var="ena" />
<spring:url value="/sys/scheduler/job/disable.do" var="dis" />
<spring:url value="/sys/scheduler/job/delete.do" var="del" />
<spring:url value="/sys/scheduler/job/add.do" var="add" />
<spring:url value="/sys/scheduler/job.do" var="form" />
<script type="text/javascript">
	var datatable;

	function disableJob(id) {
		/*bootbox.confirm("Disable this job?", function(e) {
			if (e)
				$.post("${form}", {
					id : id,
					action : 'DISABLE'
				}).done(function(e) {
					if (e.success) {
						bootbox.alert(e.desc, function() {
							datatable.ajax.reload();
						})
					} else bootbox.alert(e.desc);
				})
		});*/
		
		Swal.fire({
			text : "Disable this job?",
			icon : 'question',
			showCancelButton : true
		}).then(function(e){
			if (e.isConfirmed) {
				$.post("${form}", {
					id : id,
					action : 'DISABLE'
				}).done(function(e) {
					if (e.success) {
						datatable.ajax.reload();
					} else Swal.fire(e.desc);
				})
			}
		})
	}

	function enableJob(id) {
		/*bootbox.confirm("Enable this job?", function(e) {
			if (e)
				$.post("${form}", {
					id : id,
					action : 'ENABLE'
				}).done(function(e) {
					if (e.success) {
						bootbox.alert(e.desc, function() {
							datatable.ajax.reload();
						})
					} else bootbox.alert(e.desc);
				})
		});*/
		
		Swal.fire({
			text : "Enable this job?",
			icon : 'question',
			showCancelButton : true
		}).then(function(e){
			if (e.isConfirmed) {
				$.post("${form}", {
					id : id,
					action : 'ENABLE'
				}).done(function(e) {
					if (e.success) {
						datatable.ajax.reload();
					} else Swal.fire(e.desc);
				})
			}
		})
	}

	function deleteJob(id) {
		/*bootbox.confirm("Delete this job?", function(e) {
			if (e)
				$.post("${form}", {
					id : id,
					action : 'DELETE'
				}).done(function(e) {
					if (e.success) {
						bootbox.alert(e.desc, function() {
							datatable.ajax.reload();
						})
					} else bootbox.alert(e.desc);
				})
		});*/
		
		Swal.fire({
			text : "Delete this job?",
			icon : 'question',
			showCancelButton : true
		}).then(function(e){
			if (e.isConfirmed) {
				$.post("${form}", {
					id : id,
					action : 'DELETE'
				}).done(function(e) {
					if (e.success) {
						datatable.ajax.reload();
					} else Swal.fire(e.desc);
				})
			}
		})
	}

	function addJob(jbname, desc, syn, sch) {
		$.post("${form}", {
			jobName : jbname,
			jobDesc : desc,
			jobSyntax : syn,
			jobScheduler : sch,
			action : 'ADD'
		}).done(function(e) {
			if (e.success) {
				datatable.ajax.reload();
				$("#jobName").val("");
				$("#jobDesc").val("");
				$("#jobSyntax").val("");
				$("#jobScheduler").val("");
			} else {Swal.fire(e.desc);}
		})
	}
</script>
<div class="row">
	<div class="col-md-6">
		<div class="card border-left-primary shadow">
			<div class="card-body">
				<form:form commandName="SchedulerListForm" method="POST">
					<div class="mb-3 row">
						<form:label path="jobName" for="jobName"
							cssClass="col-md-2 col-form-label">Job Name :</form:label>
						<div class="col-md-10">
							<form:input path="jobName" cssClass="form-control"
								required="required" placeholder="Job Name" />
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="jobDesc" for="jobDesc"
							cssClass="col-md-2 col-form-label">Description :</form:label>
						<div class="col-md-10">
							<form:input path="jobDesc" cssClass="form-control"
								placeholder="Job Description" />
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="jobSyntax" for="jobSyntax"
							cssClass="col-md-2 col-form-label">SQL Syntax :</form:label>
						<div class="col-md-10">
							<form:input path="jobSyntax" cssClass="form-control"
								required="required"
								placeholder="SQL Syntax referrer to oracle package" />
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="jobScheduler" for="jobScheduler"
							cssClass="col-md-2 col-form-label">Scheduler :</form:label>
						<div class="col-md-10">
							<form:select path="jobScheduler" cssClass="form-select"
								required="required">
								<form:option value="">Select Scheduler...</form:option>
								<form:options items="${CatalogOrdered.codes['SCHEDULED_TIME'] }"
									itemValue="id.tcode" itemLabel="tval" />
							</form:select>
						</div>
					</div>
					<div class="mb-3 row">
						<div class="col-md-10 offset-md-2">
							<button type="submit" class="btn btn-sm btn-success"
								value="SUBMIT">SUBMIT</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="card border-left-success shadow">
			<div class="card-body">
				<table class="table table-striped table-bordered table-hover"
					id="datatable">
					<thead>
						<tr>
							<th>Job Name</th>
							<th>Job Desc</th>
							<th>Syntax</th>
							<th>Scheduler</th>
							<th>Active</th>
							<th>Create By</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<spring:url value="/sys/scheduler/joblist/ajax.do" var="url" />
<script type="text/javascript">
	$(document).ready(function() {
		$("form").submit(function(e) {
			e.preventDefault();
			addJob($("#jobName").val(), $("#jobDesc").val(), $("#jobSyntax").val(), $("#jobScheduler").val());
		});

		datatable = $("#datatable").DataTable(
			{
				responsive : true,
				searching : true,
				paging : true,
				bLengthChange : false,
				iDisplayLength : 5,
				bInfo : true,
				autoWidth : false,
				dom : "p",
				ajax : {
					url : "${url}",
					dataSrc : ''
				},
				columns : [ {
					data : 'jobName',
					responsivePriority : 3
				}, {
					data : 'jobDesc',
					responsivePriority : 4
				}, {
					data : 'syntax',
					responsivePriority : 1
				}, {
					data : 'scheduledTime'
				}, {
					data : 'isActive',
					class : 'action-row'
				}, {
					data : 'createBy',
					class : 'action-row'
				}, {
					data : null,
					class : 'action-row',
					render : function(data,
						type, full,
						meta) {
						var butt1;

						if (data.isActive)
							butt1 = '<button type="button" class="btn btn-secondary btn-sm" title="Disable" onclick="disableJob(\''
								+ data.jobId
								+ '\')"><span class="fa fa-ban"></span> Disable</button>';
						else
							butt1 = '<button type="button" class="btn btn-success btn-sm" title="Enable" onclick="enableJob(\''
								+ data.jobId
								+ '\')"><span class="fa fa-check"></span> Enable</button>';

						var butt2 = '<button type="button" class="btn btn-danger btn-sm" title="Delete" onclick="deleteJob(\''
							+ data.jobId
							+ '\')"><span class="fa fa-times"></span> Delete</button>';
						return butt1 + butt2;
					}
				} ]
			});
	})
</script>