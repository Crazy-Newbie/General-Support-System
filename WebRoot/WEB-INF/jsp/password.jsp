<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section class="section">
	<div class="row">
		<div class="col-6">
			<div class="card card-info">
				<div class="card-header">
					<div class="header-block">
						<p class="title">Change Password</p>
					</div>
				</div>
				<div class="card-body">
					<form:form autocomplete="false" method="POST"
						commandName="PasswordForm">
						<c:if test="${not isAdmin }">
							<input type="hidden" name="employeeId" id="employeeId"
								value="${AppUser.userId}" />
							<div class="form-group row">
								<form:label path="oldPass" for="oldPass"
									cssClass="col-md-3 col-form-label">Current Password : </form:label>
								<div class="col-md-9">
									<form:input path="oldPass" cssClass="form-control form-control-sm"
										required="required" placeholder="Current Password"
										type="password" />
								</div>
							</div>
						</c:if>
						<c:if test="${isAdmin }">
							<div class="form-group row">
								<form:label path="employeeId" for="employeeId"
									cssClass="col-md-3 col-form-label">Employee ID : </form:label>
								<div class="col-md-9">
									<form:input path="employeeId" cssClass="form-control form-control-sm"
										required="required" placeholder="Employee ID" maxlength="6" />
								</div>
							</div>
						</c:if>
						<div class="form-group row">
							<form:label path="newPass" for="newPass"
								cssClass="col-md-3 col-form-label">New Password : </form:label>
							<div class="col-md-9">
								<form:input path="newPass" cssClass="form-control form-control-sm"
									required="required" placeholder="New Password" type="password" />
							</div>
						</div>
						<div class="form-group row">
							<form:label path="newPassConf" for="newPassConf"
								cssClass="col-md-3 col-form-label">New Password (Confirm) : </form:label>
							<div class="col-md-9">
								<form:input path="newPassConf" cssClass="form-control form-control-sm"
									required="required" placeholder="New Password (Confirm)"
									type="password" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label"></label>
							<div class="col-md-9">
								<button type="submit" class="btn btn-primary btn-sm">CHANGE</button>
							</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
	$(document).ready(function() {
		$("form").submit(function(e) {
			var pass = $("#oldPass").val();
			var pass1 = $("#newPass").val();
			var pass2 = $("#newPassConf").val();

			if (pass1 != pass2) {
				e.preventDefault();
				Swal.fire("Confirmation Password not Match").then(function(x){
					$("#newPassConf").val("");
				});
				/*bootbox.alert("Confirmation Password not Match", function() {
					$("#newPassConf").val("");
				});*/
			} else if (pass === pass1) {
				e.preventDefault();
				Swal.fire("New Password equal to Old Password").then(function(x){
					$("#newPass").val("");
					$("#newPassConf").val("");
				});
				/*bootbox.alert("New Password equal to Old Password", function() {
					$("#newPass").val("");
					$("#newPassConf").val("");
				});*/
			}
		});
	})
</script>