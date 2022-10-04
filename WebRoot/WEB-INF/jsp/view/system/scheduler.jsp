<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/sys/scheduler/start.do" var="start" />
<spring:url value="/sys/scheduler.do" var="gotm" />
<script type="text/javascript">
	var itemtable;
	function startScheduler() {
		$.post("${start}", null).done(function(e) {
			if (e.success)
				itemtable.ajax.reload();
			else
				Swal.fire(e.desc);
		})
	}

	function disableJob(name, group) {
		/*bootbox.confirm("Disable " + name + " in " + group, function(e) {
			if (e) {
				$.post("${form}", {
					jobName : name,
					jobGroup : group,
					action : 'DISABLE'
				}).done(function(ret) {
					if (ret.success)
						itemtable.ajax.reload();
					else
						bootbox.alert(ret.desc);
				})
			}
		})*/

		Swal.fire({
			text : "Disable " + name + " in " + group,
			icon : 'question',
			showCancelButton : true
		}).then(function(e) {
			if (e.isConfirmed) {
				$.post("${form}", {
					jobName : name,
					jobGroup : group,
					action : 'DISABLE'
				}).done(function(ret) {
					if (ret.success)
						itemtable.ajax.reload();
					else
						Swal.fire(ret.desc);
				});
			}
		})
	}

	function setAction(vals) {
		$("#action").val(vals);
	}
</script>
<section class="section">
	<div class="row">
		<div class="col-12">
			<div class="card border-top-primary shadow">
				<div
					class="card-header d-flex justify-content-start align-items-center bg-primary text-white">
					<h3 class="card-title my-0 me-2 text-white">
						Scheduler Status :
						<c:out value="${SchedulerListForm.schedulerStatus }" />
					</h3>
					<c:if test="${not SchedulerListForm.isSchedulerRunning }">
						<button type="button" class="btn btn-success btn-sm"
							onclick="startScheduler()">
							<span class="fa fa-play"></span> Start
						</button>
					</c:if>
				</div>
				<div class="card-body">
					<form:form commandName="SchedulerListForm"
						cssClass="row gx-2 gy-3 align-items-center">
						<form:hidden path="action" />
						<div class="col-auto">
							<label class="col-form-label">Available Jobs : </label>
						</div>
						<div class="col-md-6">
							<div class="input-group">
								<form:select path="jobName" class="form-select">
									<form:options items="${CatalogOrdered.codes['SCHEDULED_JOB'] }"
										itemLabel="tval" itemValue="id.tcode" />
								</form:select>
								<button type="submit" class="btn btn-outline-primary"
									onclick="setAction('ENABLE')">
									<span class="fas fa-play"></span> ENABLE
								</button>
								<button type="submit" class="btn btn-outline-success"
									onclick="setAction('RUN')">
									<span class="fas fa-step-forward"></span> RUN-ONCE
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
					<table class="table table-striped table-bordered table-hover"
						id="itemtable">
						<thead>
							<tr>
								<th>Job Group</th>
								<th>Job Name</th>
								<th>Prev</th>
								<th>Next</th>
								<th class="action-row">Action</th>
							</tr>
						</thead>
						<tbody>
							<%-- <c:forEach items="${SchedulerRowList }" var="item">
								<tr>
									<td>${item.jobGroup }</td>
									<td>${item.jobName }</td>
									<td><fmt:formatDate
											value="${item.trigger.previousFireTime }"
											pattern="dd-MM-yyyy HH:mm" /></td>
									<td>${item.next }</td>
									<td><button type="button"
											class="btn btn-sm btn-outline-warning"
											onclick="disableJob('${item.jobName}','${item.jobGroup }')">DISABLE</button></td>
								</tr>
							</c:forEach> --%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>
<spring:url value="/sys/scheduler/ajax.do" var="ajax" />
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						itemtable = $("#itemtable")
								.DataTable(
										{
											responsive : true,
											autoWidth : false,
											orderable : true,
											filter : false,
											info : true,
											lengthChange : false,
											ajax : {
												url : "${ajax}",
												dataSrc : ''
											},
											columns : [
													{
														data : 'jobGroup',
														responsivePriority : 1
													},
													{
														data : 'jobName'
													},
													{
														data : 'prev'
													},
													{
														data : 'next'
													},
													{
														data : null,
														orderable : false,
														render : function(item) {
															return '<button type="button" class="btn btn-sm btn-outline-warning" onclick="disableJob(\''
																	+ item.jobName
																	+ '\',\''
																	+ item.jobGroup
																	+ '\')"><span class="fas fa-cancel"></span> DISABLE</button>';
														}
													} ]
										})

						$("form")
								.submit(
										function(e) {
											e.preventDefault();
											if ($("#action").val() === 'ENABLE') {
												/* bootbox.confirm("Run this scheduler? Make sure this scheduler not exist / active", function(e) {
													if (e) {
														$.post("${form}", {
															jobName : $("#jobName").val(),
															action : $("#action").val()
														}).done(function(ret) {
															if (ret.success)
																itemtable.ajax.reload(null, false);
															else
																bootbox.alert(ret.desc);
														})
													}
												}) */
												Swal
														.fire(
																{
																	title : 'Run this scheduler?',
																	text : 'Make sure this scheduler not exist / active',
																	showCancelButton : true,
																	icon : 'question'
																})
														.then(
																function(resp) {
																	if (resp.isConfirmed) {
																		$
																				.post(
																						"${form}",
																						{
																							jobName : $(
																									"#jobName")
																									.val(),
																							action : $(
																									"#action")
																									.val()
																						})
																				.done(
																						function(
																								ret) {
																							if (ret.success)
																								itemtable.ajax
																										.reload(
																												null,
																												false);
																							else
																								Swal
																										.fire({
																											title : 'Process Failed',
																											text : ret.desc,
																											icon : 'danger'
																										});
																						})
																	}
																})
											}

											if ($("#action").val() === 'RUN') {
												/* bootbox.confirm("Run this scheduler once?", function(e) {
													if (e) {
														$.post("${form}", {
															jobName : $("#jobName").val(),
															action : $("#action").val()
														}).done(function(ret) {
															if (ret.success)
																itemtable.ajax.reload(null, false);
															else
																bootbox.alert(ret.desc);
														})
													}
												}) */
												Swal
														.fire(
																{
																	title : 'Run this scheduler once?',
																	showCancelButton : true,
																	icon : 'question'
																})
														.then(
																function(resp) {
																	if (resp.isConfirmed) {
																		$
																				.post(
																						"${form}",
																						{
																							jobName : $(
																									"#jobName")
																									.val(),
																							action : $(
																									"#action")
																									.val()
																						})
																				.done(
																						function(
																								ret) {
																							if (ret.success)
																								itemtable.ajax
																										.reload(
																												null,
																												false);
																							else
																								Swal
																										.fire({
																											title : 'Process Failed',
																											text : ret.desc,
																											icon : 'danger'
																										});
																						})
																	}
																})
											}
										})
					});
</script>