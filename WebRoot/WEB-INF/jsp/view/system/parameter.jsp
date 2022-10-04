<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<spring:url value="/sys/parameter/setvalue.do" var="setvalueURL" />
<script type="text/javascript">
	var paramtable;

	function setParam(paramKey, paramDesc, paramVal) {
		Swal.fire({
			title : paramKey,
			text : !paramDesc ? paramDesc : '',
			input : 'text',
			inputValue : paramVal,
			inputPlaceholder : 'Parameter Value...',
			showCancelButton : true,
			inputValidator: (value) => {
				if (!value) {
			      return 'Value cannot empty!'
			    }
			  }
		}).then(function(resp){
			if(resp.isConfirmed){
				$.post("${setvalueURL}", {
					paramKey : paramKey,
					value : resp.value
				}, function(data, status) {
					if (data.success)
						paramtable.ajax.reload(null, false);
					else
						Swal.fire({icon:'danger',title:'Process Failed',text:data.desc});
				});
			}
		})
		/* bootbox.prompt({
			title : "[" + paramKey + "] " + paramDesc,
			value : paramVal,
			callback : function(result) {
				if (result === null) {
					//do nothing here as user does'nt want to do anything
				} else if (isEmpty(result.trim())) {
					bootbox.alert("Value cannot empty");
				} else {
					$.post("${setvalueURL}", {
						paramKey : paramKey,
						value : result
					}, function(data, status) {
						if (data.success)
							paramtable.ajax.reload(null, false);
					});
				}
			}
		}) */
	}
</script>
<div class="row">
	<div class="col-12">
		<div class="card card-block">
			<div class="card-body">
				<table class="table table-striped table-bordered table-hover" id="paramtable">
					<thead>
						<tr>
							<th>Param Key</th>
							<th>Description</th>
							<th>Value</th>
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
<spring:url value="/sys/parameter/ajax.do" var="paramURL" />
<script type="text/javascript">
	$(document).ready(function() {
		paramtable = $("#paramtable").DataTable(
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
					url : "${paramURL}",
					dataSrc : ''
				},
				columns : [ {
					data : 'paramKey',
					responsivePriority : 1
				}, {
					data : 'paramDesc',
					responsivePriority: 3
				}, {
					data : 'paramValue'
				} ],
				columnDefs : [
					{
						targets : 3,
						responsivePriority : 2,
						data : null,
						className : 'action-row',
						render : function(data, type, full, meta) {
							var butt1 = '<button type="button" class="btn btn-sm btn-outline-primary" title="SET" onclick="setParam(\''
								+ data.paramKey
								+ '\',\'' + data.paramDesc + '\',\'' + data.paramValue + '\')"><span class="fas fa-edit"></span></button>';
							return butt1;
						}
					} ]
			})
	})
</script>