<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript">
	function good() {
		if ($("#score").val() != 5)
			$("#score").val($("#score").val() + 1);
	}

	function bad() {
		if ($("#score").val() != -5)
			$("#score").val($("#score").val() - 1);
	}
</script>
<section class="section">
	<div class="row">
		<div class="col-md-6">
			<div class="card card-block">
				<div class="card-body">
					<form:form action="rate.do" autocomplete="false" method="POST"
						commandName="RatingForm">
						<form:hidden path="userId" />
						<form:hidden path="action" />
						<div class="form-group row">
							<%-- <form:label path="score" for="score"
								cssClass="col-md-2 col-form-label">Rate</form:label> --%>
							<div class="col-md-12">
								<div class="input-group input-group-sm">
									<div class="input-group-prepend">
										<button type="button" class="btn btn-danger">
											<span class="fa fa-arrow-left"></span> Buruk
										</button>
									</div>
									<form:input path="score" type="range" min="-5" max="5"
										cssClass="form-control" />
									<div class="input-group-append">
										<button type="button" class="btn btn-success" >
											Baik <span class="fa fa-arrow-right"></span>
										</button>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group row">
							<%-- <form:label path="message" for="message"
								cssClass="col-md-2 col-form-label">Comment</form:label> --%>
							<div class="col-md-12">
								<form:textarea path="message" rows="4" maxlength="512"
									cssStyle="resize: none;"
									cssClass="form-control form-control-sm"
									placeholder="Please write your comment about this application." />
							</div>
						</div>
						<div class="form-group row">
							<%-- <form:label path="message" for="message"
								cssClass="col-md-2 col-form-label">Comment</form:label> --%>
							<div class="col-md-12">
								<form:textarea path="complain" rows="4" maxlength="512"
									cssStyle="resize: none;"
									cssClass="form-control form-control-sm"
									placeholder="If you have any complain with this application (which i hope not), please write here" />
							</div>
						</div>
						<div class="form-group row">
							<%-- <form:label path="message" for="message"
								cssClass="col-md-2 col-form-label">Comment</form:label> --%>
							<div class="col-md-12">
								<form:textarea path="suggestion" rows="4" maxlength="512"
									cssStyle="resize: none;"
									cssClass="form-control form-control-sm"
									placeholder="Please write your suggestion about this application." />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-12">
								<button type="submit" class="btn btn-outline-primary btn-sm"
									title="SUBMIT">SUBMIT</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>