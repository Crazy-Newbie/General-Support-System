<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/lookup/menu.do" var="menuURL" />
<spring:url value="/lookup/form.do" var="formURL" />
<spring:url value="/sys/role/addmenu.do" var="addURL" />
<spring:url value="/sys/role/deletemenu.do" var="delURL" />
<div class="row">
	<div class="col-12">
		<div class="card border-top-primary shadow">
			<div class="card-body">
				<form:form commandName="RoleForm" method="POST" action="menu.do">
					<div class="col-12 mb-3">
						<form:hidden path="action" />
						<form:hidden path="roleId" />
						<div class="row gx-3 gy-2 align-items-center">
							<div class="col-auto">
								<form:label path="roleName" for="roleName" cssClass="form-label">Role : </form:label>
							</div>
							<div class="col-auto">
								<div class="input-group">
									<form:input path="roleName" readonly="true" cssClass="form-control" />
									<button type="submit" class="btn btn-success">UPDATE</button>
								</div>
							</div>
							<div class="col-auto">
								<spring:url value="/sys/role.do" var="rolelist" />
								<a href="${rolelist }" class="btn btn-outline-secondary"><span class="fas fa-chevron-left"></span> BACK</a>
							</div>
						</div>
					</div>
					<div class="col-12">
						<table class="table table-striped table-bordered table-hover" id="menutable">
							<thead>
								<tr>
									<th class="action-row">Menu ID</th>
									<th>Menu Name</th>
									<th>Form Id</th>
									<th class="action-row">Show</th>
									<th class="action-row">Access Code</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${AppRoleMenuViewList }" var="item" varStatus="status">
									<tr>
										<form:hidden path="appRoleMenu[${status.index }].id.roleId" />
										<form:hidden path="appRoleMenu[${status.index }].id.menuId" />
										<td class="action-row">${item.id.menuId }</td>
										<td>${item.menuNamePadding }</td>
										<td>${item.formId }</td>
										<td class="action-row"><c:if test="${item.isMenu }">
												<div class="form-check form-switch">
													<form:checkbox path="appRoleMenu[${status.index}].isShowed" cssClass="form-check-input" />
												</div>
											</c:if> <c:if test="${not item.isMenu }">
												<form:hidden path="appRoleMenu[${status.index }].isShowed" />
											</c:if></td>
										<td><c:if test="${not empty item.formId }">
												<form:select path="appRoleMenu[${status.index}].accessCode" cssClass="form-select">
													<form:options items="${CatalogOrdered.codes['MOD'] }" itemLabel="tval" itemValue="id.tcode" />
												</form:select>
											</c:if> <c:if test="${empty item.formId }">
												<form:hidden path="appRoleMenu[${status.index }].accessCode" />
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		menutable = $("#menutable").DataTable({
			responsive : true,
			searching : false,
			paging : false,
			bSort : false,
			bLengthChange : false,
			bFilter : false,
			bInfo : true,
			autoWidth : false,
			columnDef : [{
				targets : [2, 3, 4],
				responsivePriority : 1
			}]
		})
	})
</script>