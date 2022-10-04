<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/lookup/form.do" var="formURL" />
<spring:url value="/sys/role/addform.do" var="addUrl" />
<spring:url value="/sys/role/deleteform.do" var="deleteUrl" />
<script type="text/javascript">
	var formtable;

	function openFormLookup() {
		windowLookup("${formURL}", 640, 480);
	}

	function deleteForm(roleId, formId) {
		/*bootbox.confirm("Are you sure to revoke access of " + formId + " from " + roleId, function(result) {
			if (result) {
				$.post("${deleteUrl}", {
					roleId : roleId,
					formId : formId
				}).done(function(ret) {
					if (ret.success)
						bootbox.alert(ret.desc, function() {
							formtable.ajax.reload();
						})
					else
						bootbox.alert(ret.desc);
				});
			}
		})*/
		
		Swal.fire({
			text : "Are you sure to revoke access of " + formId + " from " + roleId,
			icon : 'question',
			showCancelButton : true
		}).then(function(result){
			if (result.isConfirmed) {
				$.post("${deleteUrl}", {
					roleId : roleId,
					formId : formId
				}).done(function(ret) {
					if (ret.success)
						formtable.ajax.reload();
					else
						Swal.fire(ret.desc);
				});
			}
		})
		
	}


	function receiveCallback(obj) {
		$("#formNo").val(obj.formNo);
		$("#formDesc").val(obj.formDesc);
	}
</script>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Form Detail</div>
			<div class="panel-body">
				<div class="row">
					<form:form commandName="RoleForm">
						<div class="col-lg-6 col-md-6">

							<form:hidden path="roleId" />
							<div class="form-group">
								<form:label path="roleName" for="roleName">Role : </form:label>
								<form:input path="roleName" readonly="true"
									cssClass="form-control" />
							</div>
							<div class="form-group">
								<form:hidden path="formNo" />
								<form:label path="formDesc" for="formDesc">Form : </form:label>
								<div class="input-group" onclick="openFormLookup()">
									<span class="input-group-addon"><i class="fa fa-search"></i></span>
									<form:input path="formDesc" cssClass="form-control"
										placeholder="Assign Form" required="required"
										onkeydown="return false" />
								</div>
							</div>
							<div class="form-group">
								<form:label path="accessRole" for="accessRole">Access : </form:label>
								<select id="accessRole" class="form-control"><c:forEach
										var="item" items="${CatalogOrdered.codes['MOD']}">
										<option value='<c:out value="${item.id.tcode }"/>'><c:out
												value="${item.tval }" /></option>
									</c:forEach></select>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-success">SET</button>
							</div>

						</div>
					</form:form>
					<div class="col-lg-12">
						<table class="table table-striped table-bordered table-hover"
							id="formtable">
							<thead>
								<tr>
									<th>Form No</th>
									<th>Form Name</th>
									<th>Access Flag</th>
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
	</div>
</div>
<spring:url value="/helper/roleform.do" var="url">
	<spring:param name="roleId">${RoleForm.roleId }</spring:param>
</spring:url>
<script type="text/javascript">
	$(document).ready(function() {
		formtable = $("#formtable")
			.DataTable(
				{
					responsive : true,
					searching : true,
					paging : true,
					bSort : false,
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
						data : 'formDesc'
					}, {
						data : 'accessCode'
					} ],
					columnDefs : [ {
						targets : 3,
						data : null,
						class : 'action-row',
						render : function(data, type,
							full, meta) {
							var butt3 = '<button type="button" class="btn btn-danger" onclick="deleteForm(\''
								+ data.roleId + '\',\''
								+ data.formNo
								+ '\')"><span class="fa fa-trash"></span></button>';

							return butt3;
						}
					} ]
				})

		$("form").submit(function(e) {
			e.preventDefault();
			$.post("${addUrl}", {
				roleId : $("#roleId").val(),
				formNo : $("#formNo").val(),
				accessCode : $("#accessRole").val()
			}).done(function(data) {
				if (data.success) {
					formtable.ajax.reload();
					$("#formNo").val("");
					$("#formDesc").val("");
					$("#accessRole").val(0);
				} else {
					Swal.fire(data.desc);
				}
			})
		})
	})
</script>