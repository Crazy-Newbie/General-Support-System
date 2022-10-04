<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/team/form.do?teamId=dummy" var="editForm" />
<spring:url value="/team/form.do" var="deleteform" />

<script type="text/javascript">
	var itemtable;

	function setAction(val) {
		$("#action").val(val);
	}

	function editTeam(teamId) {
		window.location.href="${editForm}".replace('dummy', teamId);
	}
	
	/*function delForm(teamId, name) {
		Swal.fire({
			text : 'Are you sure you want to delete this data?',
			icon : 'question',
			showCancelButton : true
		}).then(function(resp) {
			if (resp.isConfirmed) {
				$.post("${deleteform}", {
					action : 'DELETE',
					teamId : teamId,
					teamName : name
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
	}*/
	
	
</script>
<div class="row">
	<div class="col-12">
		<div class="card border-top-primary shadow">
			<div class="card-body">
				<form:form commandName="TeamListForm">
					<form:hidden path="action" />
					<form:hidden path="accessCode" />
					<div class="row g-1">
						<%--  <div class="col-12 col-sm-auto">
							<form:input path="reqId" cssClass="form-control"
								placeholder="Request ID..." />
						</div>--%>
						<%-- <div class="col-12 col-sm-auto">
							<form:input path="eqptId" cssClass="form-control"
								placeholder="Equipment ID..." />
						</div>
						<div class="col-12 col-sm-auto">
							<form:select path="eqptType" cssClass="form-select">
								<form:option value="">ALL TYPE</form:option>
								<form:options items="${type }" itemValue="typeId"
									itemLabel="typeDesc" />
							</form:select>
						</div>
						<div class="col-12 col-sm-auto">
							<form:input path="model" cssClass="form-control"
								placeholder="Model..." />
						</div>
						<div class="col-12 col-sm-auto">
							<form:input path="serialNo" cssClass="form-control"
								placeholder="Serial No..." />
						</div>
						<div class="col-12 col-sm-auto">
							<form:hidden path="location" />
							<div class="input-group">
								<form:input path="locationDesc" onkeydown="return false"
									placeholder="Location Group..." cssClass="form-control"
									onclick="openLookup()" autocomplete="off" />
								<form:select path="isLocExact" cssClass="form-select">
									<form:option value="N">Incl. Children</form:option>
									<form:option value="Y">Exact Location</form:option>
								</form:select>
								<button type="button" onclick="clearParent()"
									class="btn btn-outline-warning">
									<span class="fas fa-times"></span>
								</button>
							</div>
						</div> --%>
						<div class="col-12 col-sm-auto">
							<button type="submit" class="btn btn-outline-primary"
								title="SEARCH" onclick="setAction('SEARCH')">
								<span class="fas fa-search"></span> SEARCH
							</button>
							<spring:url value="/team/form.do" var="createMenu" />
							<a href="${createMenu }" class="btn btn-success"> <span
								class="fas fa-plus"></span> CREATE
							</a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div class="card border-top-success shadow">
			<div class="card-body">
				<table
					class="table table-sm table-striped table-bordered table-hover"
					id="itemtable">
					<thead>
						<tr>
							<th>No</th>
							<th>Team Id</th>
							<th>Team Name</th>
							<th>Team Member</th>
							<th class="action-row">New</th>
							<th class="action-row">Assign.</th>
							<th class="action-row">Exec.</th>
							<th class="action-row">Pend.</th>
							<th class="action-row">Compl.</th>
							<th class="action-row">Cancel.</th>
							<th class="action-row">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty listdata }">
							<tr>
								<td colspan="5" class="text-center">Data Not Found</td>
							</tr>
						</c:if>
						<c:forEach items="${listdata }" var="item" varStatus="status">
							<tr>
								<td>${status.index + 1 }</td>
								<td>${item.teamId }</td>
								<td>${item.teamName }</td>
								<spring:url value="/team/member.do?teamId=${item.teamId }"
									var="url" />
								<td class="text-center"><a href="${url }"
									class="btn btn-sm btn-outline-info">${item.teamMember }</a></td>
								<spring:url
									value="/request/list.do?teamId=${item.teamId }&status=NEW"
									var="url" />
								<td class="text-center"><a href="${url }"
									class="btn btn-sm btn-outline-info">${item.neww }</a></td>
								<spring:url
									value="/request/list.do?teamId=${item.teamId }&status=ASG"
									var="url" />
								<td class="text-center"><a href="${url }"
									class="btn btn-sm btn-outline-primary">${item.asg }</a></td>
								<spring:url
									value="/request/list.do?teamId=${item.teamId }&status=EXE"
									var="url" />
								<td class="text-center"><a href="${url }"
									class="btn btn-sm btn-outline-info">${item.exe }</a></td>
								<spring:url
									value="/request/list.do?teamId=${item.teamId }&status=PND"
									var="url" />
								<td class="text-center"><a href="${url }"
									class="btn btn-sm btn-outline-secondary">${item.pnd }</a></td>
								<spring:url
									value="/request/list.do?teamId=${item.teamId }&status=CMPL"
									var="url" />
								<td class="text-center"><a href="${url }"
									class="btn btn-sm btn-outline-success">${item.cmpl }</a></td>
								<spring:url
									value="/request/list.do?teamId=${item.teamId }&status=CANC"
									var="url" />
								<td class="text-center"><a href="${url }"
									class="btn btn-sm btn-outline-danger">${item.canc }</a></td>
								<td class="text-center">
									<div class="btn-group">
										<button type='button' class="btn btn-outline-primary btn-sm"
											onclick="editTeam('${item.teamId}')">
											<span class="fas fa-list"></span>
										</button>
										<%-- <button type='button' class="btn btn-outline-danger btn-sm"
											onclick="delForm('${item.teamId}' + '${item.teamName }')">
											<span class="fas fa-times"></span>
										</button>--%>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<spring:url value="/team/list.do" var="dataurl" />

<script type="text/javascript">
	$(document).ready(function() {
		$("#itemtable").DataTable({
			lengthChange : false,
			searching : true,
			ordering : false,
			columnDefs : [ {
				targets : -1,
				responsivePriority : 2
			} ],
		});

	});
</script>