<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:url value="/sys/init/menu.do" var="cat" />
<spring:url value="/dashboard.do" var="db" />
<script type="text/javascript">
	function refreshMenu() {
		$.post("${cat}", null).done(function(e) {
			window.location.href = '${db}';
		}).fail(function() {
			alert("Request failed");
		})
	}
</script>
<nav class="navbar navbar-expand navbar-light navbar-bg">
	<a class="sidebar-toggle js-sidebar-toggle">
		<i class="hamburger align-self-center"></i>
	</a>
	<div class="navbar-nav d-none d-lg-flex h1">${CatalogPair.codes['APPL']['APPNAME'].tval}</div>
	<div class="navbar-nav d-show d-lg-none h1">${CatalogPair.codes['APPL']['APPNAME'].tattr1}</div>
	<div class="navbar-collapse collapse">
		<ul class="navbar-nav navbar-align">
			<li class="nav-item dropdown">
				<a class="nav-icon pe-md-0 dropdown-toggle" href="#" data-bs-toggle="dropdown">
					<!-- <img src="img/avatars/avatar.jpg" class="avatar img-fluid rounded" alt="Charles Hall"> -->
					<spring:url value="/resources/img/notfound.png" var="altimg" />
					<img class="avatar img-fluid rounded" src="${CatalogPair.codes['APPL']['PHOTOS'].tval}/${AppUser.userId}.jpg" onerror="this.onerror=null;this.src='${altimg}'"
						alt="${AppUser.name} # ${AppUser.userId}" />
					<%-- <span class="mx-2 d-none d-lg-inline text-gray-600 small">${AppUser.name} # ${AppUser.userId}</span> --%>
				</a>
				<div class="dropdown-menu dropdown-menu-end">
					<spring:url value="/switch.do" var="switchURL" />
					<spring:url value="/password.do" var="passwordURL" />
					<button class="dropdown-item" onclick="refreshMenu()">
						<i class="fas fa-sync"></i> Refresh Menu
					</button>
					<c:choose>
						<c:when test="${not empty AppUser.originalUser and AppUser.originalUser.isAllowLoginas}">
							<a href="${switchURL }" class="dropdown-item">
								<i class="fas fa-random fa-fw icon"></i> Switch User
							</a>
						</c:when>
						<c:when test="${AppUser.isAllowLoginas }">
							<a href="${switchURL }" class="dropdown-item">
								<i class="fas fa-random fa-fw icon"></i> Switch User
							</a>
						</c:when>
					</c:choose>
					<a href="${passwordURL }" class="dropdown-item">
						<i class="fas fa-key fa-fw icon"></i> Change Password
					</a>
					<div class="dropdown-divider"></div>
					<spring:url value="/logout.do" var="logoutURL" />
					<a class="dropdown-item" href="${logoutURL }">
						<i class="fas fa-sign-out-alt fa-fw text-gray-600"></i> Logout
					</a>
				</div>
			</li>
		</ul>
	</div>
</nav>