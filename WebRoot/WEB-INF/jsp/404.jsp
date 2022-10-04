<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
.error {
	color: #5a5c69;
	font-size: 7rem;
	position: relative;
	line-height: 1;
	width: 12.5rem;
}

.error:before {
	content: attr(data-text);
	position: absolute;
	left: -2px;
	text-shadow: 1px 0 #4e73df;
	top: 0;
	color: #5a5c69;
	background: #f8f9fc;
	overflow: hidden;
	clip: rect(0, 900px, 0, 0);
	animation: noise-anim-2 3s infinite linear alternate-reverse;
}

.error:after {
	content: attr(data-text);
	position: absolute;
	left: 2px;
	text-shadow: -1px 0 #e74a3b;
	top: 0;
	color: #5a5c69;
	background: #f8f9fc;
	overflow: hidden;
	clip: rect(0, 900px, 0, 0);
	animation: noise-anim 2s infinite linear alternate-reverse;
}

@-webkit-keyframes noise-anim{0%{clip:rect(32px,9999px,16px,0)}5%{clip:rect(5px,9999px,24px,0)}10%{clip:rect(77px,9999px,87px,0)}15%{clip:rect(91px,9999px,95px,0)}20%{clip:rect(74px,9999px,9px,0)}25%{clip:rect(37px,9999px,32px,0)}30%{clip:rect(56px,9999px,27px,0)}35%{clip:rect(35px,9999px,33px,0)}40%{clip:rect(89px,9999px,6px,0)}45%{clip:rect(81px,9999px,77px,0)}50%{clip:rect(64px,9999px,69px,0)}55%{clip:rect(12px,9999px,11px,0)}60%{clip:rect(59px,9999px,11px,0)}65%{clip:rect(69px,9999px,59px,0)}70%{clip:rect(74px,9999px,65px,0)}75%{clip:rect(56px,9999px,79px,0)}80%{clip:rect(80px,9999px,64px,0)}85%{clip:rect(87px,9999px,29px,0)}90%{clip:rect(16px,9999px,21px,0)}95%{clip:rect(69px,9999px,43px,0)}100%{clip:rect(75px,9999px,63px,0)}}@keyframes noise-anim{0%{clip:rect(32px,9999px,16px,0)}5%{clip:rect(5px,9999px,24px,0)}10%{clip:rect(77px,9999px,87px,0)}15%{clip:rect(91px,9999px,95px,0)}20%{clip:rect(74px,9999px,9px,0)}25%{clip:rect(37px,9999px,32px,0)}30%{clip:rect(56px,9999px,27px,0)}35%{clip:rect(35px,9999px,33px,0)}40%{clip:rect(89px,9999px,6px,0)}45%{clip:rect(81px,9999px,77px,0)}50%{clip:rect(64px,9999px,69px,0)}55%{clip:rect(12px,9999px,11px,0)}60%{clip:rect(59px,9999px,11px,0)}65%{clip:rect(69px,9999px,59px,0)}70%{clip:rect(74px,9999px,65px,0)}75%{clip:rect(56px,9999px,79px,0)}80%{clip:rect(80px,9999px,64px,0)}85%{clip:rect(87px,9999px,29px,0)}90%{clip:rect(16px,9999px,21px,0)}95%{clip:rect(69px,9999px,43px,0)}100%{clip:rect(75px,9999px,63px,0)}}
</style>
<div class="text-center">
	<div
		class="error mx-auto animate__animated animate__pulse animate__infinite animate__slow"
		data-text="404">404</div>
	<p class="lead text-gray-800 mb-5">Page Not Found</p>
	<p class="text-gray-500 mb-0">. . . or this link (and all of its
		feature) is not yet ready</p>
	<spring:url value="/dashboard.do" var="url" />
	<a href="${url }"><span class="fas fa-chevron-left"></span> Back to
		Dashboard</a>
</div>