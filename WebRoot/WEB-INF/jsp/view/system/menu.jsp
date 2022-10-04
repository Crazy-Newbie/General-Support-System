<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/sys/menu/uporder.do" var="up" />
<spring:url value="/sys/menu/downorder.do" var="down" />
<spring:url value="/sys/menu/form.do" var="edit" />
<spring:url value="/sys/init/form.do" var="init" />
<spring:url value="/sys/init/catalog.do" var="cat" />
<script type="text/javascript">
	var menutable;

	function upOrder(menuId) {
		//pleaseWait.show();
		$.post("${up}", {
			menuId : menuId,
			action : 'UPDATE'
		}).done(function(data) {
			if (data.success)
				menutable.ajax.reload(null, false);
			else
				Swal.fire({title:'Process Failed',text:data.desc,icon:'error'});
		//pleaseWait.hide();
		});

	}

	function downOrder(menuId) {
		//pleaseWait.show();
		$.post("${down}", {
			menuId : menuId,
			action : 'UPDATE'
		}).done(function(data) {
			if (data.success)
				menutable.ajax.reload(null, false);
			else Swal.fire({title:'Process Failed',text:data.desc,icon:'error'});
		//pleaseWait.hide();
		});
	}

	function editMenu(menuId) {
		var editurl = "${edit}?menuId=" + menuId;
		window.location.href = editurl;
	}

	function deleteMenu(menuId) {
		Swal.fire({
			title : "Are you sure you want to delete this Menu?",
			text : "Please select action for this menu sub-menu",
			input : 'select',
			icon : 'warning',
			inputPlaceholder : 'Select action...',
			showCancelButton : true,
			inputOptions:{
				1 : 'Delete sub-menu as well',
				0 : 'Move sub-menu to this menu parent'
			},
			inputValidator : function(val){
				return new Promise((resolve)=>{
					if(!val)
						resolve('Please select one of the option above!!!');
					else
						resolve();
				});
			}
		}).then(function(resp){
			if(resp.isConfirmed){
				$.post("${edit}", {
					menuId : menuId,
					withChild : resp.value,
					action : 'DELETE'
				}, function(data, status) {
					if (data.success)
						menutable.ajax.reload(null, false);
					else
						Swal.fire({title:'Process Failed',text:data.desc,icon:'error'});
				});
			}
		});
		/* bootbox.prompt({
			title : "Are you sure you want to delete this Menu?",
			inputType : 'select',
			inputOptions : [
				{
					text : 'Yes! Delete with sub-menu as well',
					value : 1,
				},
				{
					text : 'Yes! But move sub-menu to this parent menu',
					value : 0,
				},
				{
					text : 'No',
					value : 'X',
				}
			],
			callback : function(result) {
				if (result && result !== 'X') {
					$.post("${edit}", {
						menuId : menuId,
						withChild : result,
						action : 'DELETE'
					}, function(data, status) {
						if (data.success)
							menutable.ajax.reload(null, false);
						else
							bootbox.alert(data.desc);
					});
				}
			}
		}); */
	}

	function reinit() {
		/* bootbox.confirm("Re-Initialize Form Name?", function(e) {
			if (e) {
				$.post("${init}", null).done(function(e) {
					bootbox.alert(e.desc);
				}).fail(function() {
					bootbox.alert("Request failed");
				})
			}
		}) */
		Swal.fire({
			title:'Re-Initialize Form Name?',
					showCancelButton : true,
					icon:'question'
		}).then(function(resp){
			if(resp.isConfirmed){
				$.post("${init}", null).done(function(e) {
					Swal.fire(e.desc);
				}).fail(function() {
					Swal.fire("Request failed");
				})
			}
		})
	}
</script>
<div class="row">
	<div class="col-12">
		<div class="card border-top-primary shadow">
			<div class="card-body">
				<spring:url value="/sys/menu/form.do" var="createMenu" />
				<a href="${createMenu }" class="btn btn-primary">
					<span class="fas fa-plus"></span> CREATE
				</a>
				<button type="button" class="btn btn-secondary" onclick="reinit()">
					<span class="fas fa-sync"></span> RE-INIT FORM
				</button>
			</div>
		</div>
		<div class="card border-top-success shadow">
			<div class="card-body">
				<table class="table table-striped table-bordered table-hover" id="menutable">
					<thead>
						<tr>
							<th>Menu ID</th>
							<th>Menu Name</th>
							<th>URL</th>
							<th>Form Code</th>
							<th>Level</th>
							<th>Parent</th>
							<th>Action</th>
							<th>Order</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<%-- <div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Menu List</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<spring:url value="/sys/menu/form.do" var="createMenu" />
						<a href="${createMenu }" class="btn btn-primary btn-sm">CREATE</a>
						<button type="button" class="btn btn-secondary btn-sm"
							onclick="reinit()">RE-INIT FORM</button>
					</div>
					<div class="col-lg-12">
						<table class="table table-striped table-bordered table-hover"
							id="menutable">
							<thead>
								<tr>
									<th>Menu ID</th>
									<th>Menu Name</th>
									<th>URL</th>
									<th>Form Code</th>
									<th>Level</th>
									<th>Parent</th>
									<th>Action</th>
									<th>Order</th>
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
</div> --%>
<spring:url value="/helper/menu.do" var="menuURL" />
<script type="text/javascript">
	$(document)
		.ready(
			function() {
				menutable = $("#menutable")
					.DataTable(
						{
							responsive : true,
							searching : true,
							paging : false,
							bSort : false,
							bLengthChange : false,
							bFilter : false,
							bInfo : true,
							autoWidth : false,
							ajax : {
								url : "${menuURL}",
								dataSrc : ''
							},
							columns : [ {
								data : 'menuId',
								class : 'action-row'
							}, {
								data : 'menuNamePadding'
							}, {
								data : 'menuUrl'
							}, {
								data : 'formId'
							}, {
								data : 'menuLvl',
								class : 'action-row'
							}, {
								data : null,
								class : 'action-row',
								render : function(data, type, full, meta) {
									if (data.appMenu != null)
										return data.appMenu.menuName;
									else
										return "[TOP]";
								}
							}, {
								data : null,
								class : 'action-row',
								render : function(data,
									type, full,
									meta) {
									var editbutt = '<button type="button" class="btn btn-outline-secondary btn-sm" onclick="editMenu(\''
										+ data.menuId
										+ '\')"><span class="fa fa-edit"></span></button>';
									var delbutt = '<button type="button" class="btn btn-outline-danger btn-sm" onclick="deleteMenu(\''
										+ data.menuId
										+ '\')"><span class="fa fa-trash"></span></button>';

									return editbutt + delbutt;
								}
							}, {
								data : null,
								class : 'action-row',
								render : function(data,
									type, full,
									meta) {
									var butt1 = '<button type="button" class="btn btn-outline-secondary btn-sm" onclick="upOrder(\''
										+ data.menuId
										+ '\')"><span class="fa fa-angle-up"></span></button>';
									var butt2 = '<button type="button" class="btn btn-outline-secondary btn-sm" onclick="downOrder(\''
										+ data.menuId
										+ '\')"><span class="fa fa-angle-down"></span></button>';
									return butt1
										+ butt2;
								}
							}
							]
						})
			})
</script>