<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="row vh-100">
	<div class="col-sm-10 col-md-7 col-lg-5 mx-auto d-table h-100">
		<div class="d-table-cell align-middle">
			<div class="card">
				<div class="card-body">
					<div class="m-sm-4">
						<div class="text-center mb-3">
							<spring:url value="/resources/img/ptb_logo.png" var="str" />
							<img alt="" src="${str }" class="img-responsive animate__animated animate__fadeIn" style="max-height: 70%">
						</div>
						<hr />
						<div class="text-center">
							<span class="h2 font-weight-bold animate__animated animate__fadeInDown">${CatalogPair.codes['APPL']['APPNAME'].tval}</span>
						</div>
						<hr />
						<form:form commandName="LoginForm" method="POST">
							<div class="mb-3">
								<!-- <label class="form-label">Email</label> <input class="form-control form-control-lg" type="email" name="email" placeholder="Enter your email">
							 -->
								<form:label path="username" for="username" cssClass="form-label">Username</form:label>
								<form:input path="username" cssClass="form-control form-control-lg" placeholder="Username..." required="required" />
							</div>
							<div class="mb-3">
								<!-- <label class="form-label">Password</label> <input class="form-control form-control-lg" type="password" name="password" placeholder="Enter your password"> -->
								<form:label path="password" for="password" cssClass="form-label">Password</form:label>
								<form:input path="password" cssClass="form-control form-control-lg" placeholder="Password..." required="required" type="password" />
							</div>
							<div class="text-center mt-3">
								<button type="submit" class="btn btn-lg btn-primary">SIGN IN</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>