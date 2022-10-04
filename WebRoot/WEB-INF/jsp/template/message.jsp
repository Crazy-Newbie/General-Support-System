<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${!empty BaseForm.messages}">
	<div class="col-12">
		<c:forEach var="msg" items="${BaseForm.messages}">
			<div class="alert alert-outline-coloured ${msg.type } ${msg.dismissible ? 'alert-dismissible' : ''} fade show animate__animated animate__flash" role="alert">
				<c:if test="${msg.dismissible }">
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</c:if>
				<div class="alert-icon">
					<span class="${msg.messageType.CSSAlertIcon}  animate__animated animate__flash animate__delay-1s animate__repeat-3 animate__slower"></span>
				</div>
				<div class="alert-message">
					<strong class="animate__fadeInRight animate__animated ${msg.messageType.CSSTextStyle}">${msg.messageDescription }</strong>
				</div>
			</div>
		</c:forEach>
	</div>
</c:if>