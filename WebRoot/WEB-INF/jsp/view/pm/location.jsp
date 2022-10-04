<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/pm/location/form.do" var="form" />
<spring:url value="/sys/menu/uporder.do" var="up" />
<spring:url value="/sys/menu/downorder.do" var="down" />
<spring:url value="/pm/location/form.do" var="edit" />
<spring:url value="/sys/init/form.do" var="init" />
<spring:url value="/sys/init/catalog.do" var="cat" />
<script type="text/javascript">
	var menutable;

	function upOrder(locationId) {
		//pleaseWait.show();
		$.post("${up}", {
			locationId : locationId,
			action : 'UPDATE'
		}).done(function(data) {
			if (data.success)
				menutable.ajax.reload(null, false);
			else
				Swal.fire({title:'Process Failed',text:data.desc,icon:'error'});
		//pleaseWait.hide();
		});

	}

	function downOrder(locationId) {
		//pleaseWait.show();
		$.post("${down}", {
			locationId : locationId,
			action : 'UPDATE'
		}).done(function(data) {
			if (data.success)
				menutable.ajax.reload(null, false);
			else Swal.fire({title:'Process Failed',text:data.desc,icon:'error'});
		//pleaseWait.hide();
		});
	}

	function editMenu(locationId) {
		var editurl = "${edit}?locationId=" + locationId;
		window.location.href = editurl;
	}

	function deleteMenu(locationId) {
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
					locationId : locationId,
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

	
</script>

<div class="row">
	<div class="col-12">
		<div class="card border-top-primary shadow">
			<div class="card-body">
				<spring:url value="/pm/location/form.do" var="createMenu" />
				<a href="${createMenu }" class="btn btn-primary"> <span
					class="fas fa-plus"></span> CREATE
				</a>

			</div>
		</div>
		<div class="card border-top-success shadow">
			<div class="card-body">
				<table class="table table-striped table-bordered table-hover"
					id="menutable">
					<thead>
						<tr>
							<th>Location ID</th>
							<th>Location Description</th>
							<th>Location Path</th>
							<th>Equipments</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<spring:url value="/pm/location/list/data.do" var="menuURL" />
<script type="text/javascript">
	$(document).ready(function() {menutable = 
		$("#menutable").DataTable({
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
								data : 'locationId',
								className : 'action-row'
							}, {
								data : 'locationDescPadding'
							}, {
								data : 'path'
							}, {
								className : 'action-row',
								data : 'eqptCounter'
							},{
								data : null,
								className : 'action-row',
								render : function(data) {
									var editbutt = '<button type="button" class="btn btn-outline-secondary btn-sm" onclick="editMenu(\''
										+ data.locationId
										+ '\')"><span class="fa fa-edit"></span></button>';
									var delbutt = '<button type="button" class="btn btn-outline-danger btn-sm" onclick="deleteMenu(\''
										+ data.locationId
										+ '\')"><span class="fa fa-trash"></span></button>';

									return editbutt + delbutt;
								}
							}, 
							]
						})
			})
</script>