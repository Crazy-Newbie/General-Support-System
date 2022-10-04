<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/dashboard.do" var="home" />
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="metismenu">
	<a class="sidebar-brand d-flex align-items-center justify-content-center" href="${home }">
		<div class="sidebar-brand-icon ">
			<spring:url value="/resources/img/ptb_logo_icon_gray.png" var="url" />
			<img alt="" src="${url }" style="max-height: 85%; max-width: 44px">
			<!-- <i class="fas fa-laugh-wink"></i>rotate-n-15 -->
		</div>
		<div class="sidebar-brand-text mx-3 text-nowrap">
			<%-- ${CatalogPair.codes['APPL']['APPNAME'].tattr1} --%>
			Badak LNG
		</div>
	</a>
	<hr class="sidebar-divider my-0" />
	<c:out value="${AppMenu }" escapeXml="false" />
	<hr class="sidebar-divider d-none d-md-block" />
	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>
</ul>
<script type="text/javascript">
	var selected = $('.sidebar-link a[href="' + window.location.pathname + '"]');
	selected.parents('.sidebar-item').addClass('active');
	/* selected.addClass('sidebar-menu-selected');
	selected.parents('ul .sidebar-nav.collapse').addClass('in');
	selected.parents('li').addClass('open'); */

	

	/* $(document).ready(function() {
	 $('[data-toggle="tooltip"]').tooltip();
	 }); */
</script>