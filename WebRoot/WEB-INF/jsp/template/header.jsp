<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<spring:url value="/sys/init/menu.do" var="cat" />
<spring:url value="/dashboard.do" var="db" />
<script type="text/javascript">

	function refreshMenu() {
		$.post("${cat}", null).done(function(e) {
			/* bootbox.alert(e.desc, function() {
				window.location.reload();
			}); */
			window.location.href = '${db}';
		}).fail(function() {
			bootbox.alert("Request failed");
		})
	}
</script>
<nav class="navbar navbar-expand navbar-light bg-white topbar mb-3 static-top shadow">

	<!-- Sidebar Toggle (Topbar) -->
	<button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
		<i class="fa fa-bars"></i>
	</button>

	<h3 class="slideInDown animated faster text-truncate">${CatalogPair.codes['APPL']['APPNAME'].tval}</h3>

	<!-- Topbar Navbar -->
	<ul class="navbar-nav ml-auto">

		<div class="topbar-divider d-none d-sm-block"></div>

		<!-- Nav Item - User Information -->

		<li class="nav-item dropdown no-arrow"><a class="nav-link dropdown-toggle" href="#"
			id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				<spring:url value="/resources/img/notfound.png" var="altimg" /> <img
				class="img-profile rounded-circle"
				src="${CatalogPair.codes['APPL']['PHOTOS'].tval}/${AppUser.userId}.jpg"
				onerror="this.onerror=null;this.src='${altimg}'" /><span
				class="mx-2 d-none d-lg-inline text-gray-600 small">${AppUser.name} # ${AppUser.userId}</span>

		</a> <!-- Dropdown - User Information -->
			<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
				aria-labelledby="userDropdown">
				<spring:url value="/switch.do" var="switchURL" />
				<spring:url value="/password.do" var="passwordURL" />
				<button class="dropdown-item" onclick="refreshMenu()">
					<i class="fas fa-sync"></i> Refresh Menu
				</button>
				<c:choose>
					<c:when test="${not empty AppUser.originalUser and AppUser.originalUser.isAllowLoginas}">
						<a href="${switchURL }" class="dropdown-item"><i class="fas fa-random fa-fw icon"></i>
							Switch User</a>
					</c:when>
					<c:when test="${AppUser.isAllowLoginas }">
						<a href="${switchURL }" class="dropdown-item"><i class="fas fa-random fa-fw icon"></i>
							Switch User</a>
					</c:when>
				</c:choose>
				<a href="${passwordURL }" class="dropdown-item"><i class="fas fa-key fa-fw icon"></i> Change
					Password</a>
				<div class="dropdown-divider"></div>
				<spring:url value="/logout.do" var="logoutURL" />
				<a class="dropdown-item" href="${logoutURL }"> <i
					class="fas fa-sign-out-alt fa-fw text-gray-600"></i> Logout
				</a>
			</div></li>

	</ul>

</nav>