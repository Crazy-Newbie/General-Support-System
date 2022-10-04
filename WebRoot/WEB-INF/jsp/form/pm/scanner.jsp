<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<spring:url value="/resources/js2/html5-qrcode.min.js" var="url" />
<script src="${url }"></script>
<spring:url value="/pm/inspect/${AppUser.userId }/id.do" var="equipment" />
<script type="text/javascript">
	var isAuto = true;
	var isScannerOn = false;

	function setStatus(stat) {
		$("#status").val(stat);
	}

	function startScanning() {
		//scanner.stop();
		scanner.start({facingMode: "environment"},scannerConfig,qrCodeSuccessCallback);
		$("#stop-btn").show();
		$("#start-btn").hide();
		isScannerOn = true;
	}
	
	function pauseScanning(){
		scanner.stop().then((ignore) => {
		}).catch((err) => {
			alert(err);
		});
		$("#stop-btn").hide();
		$("#start-btn").show();
		isScannerOn = false;
	}
	
	/* function toggleInput(){
		if(isAuto){
			if(isScannerOn)
				pauseScanning();
			$("#auto").slideUp();
			$("#manual").slideDown();
			isAuto = false;
			$("#toggle-btn").html("SCANNER CHECK")
		}else{
			if(!isScannerOn)
				startScanning();
			$("#auto").slideDown();
			$("#manual").slideUp();
			isAuto = true;
			$("#toggle-btn").html("MANUAL INPUT")
		}
	} */
	
	/* function check(){
		$.post("${find}",{
			prefix : $("#start").val(),
			postfix : $("#end").val()
		}).done(function(res){
			if(res.success) 
				window.location.href = "${process}".replace("trxid", res.message) + "&fromUrl=" + fromUrl;
			else
				bootbox.alert(res.message);
		}) 
	} */
</script>
<div class="row justify-content-start my-5 mx-lg-5 mx-md-1">
	<div class="col-12 mb-4">
		<div class="card border-top-primary shadow">
			<div class="card-body text-center">
				<div class="d-flex flex-column align-items-center">
					<spring:url value="/resources/img/ptb_logo.png" var="img" />
					<img src="${img }" alt="..." class="mb-2">
					<h3 class="title h2 mb-3">Equipment Check</h3>
					<div id="auto" class="w-100">
						<div style="width: auto; max-width: 600px;" class="mx-auto mb-3"
							id="reader"></div>
						<button id="start-btn" class="btn btn-block btn-primary"
							title="START" onclick="startScanning()">
							<span class="fas fa-fw fa-qrcode"></span> START
						</button>
						<button id="stop-btn" class="btn btn-block btn-outline-warning"
							title="PAUSE" onclick="pauseScanning()" style="display: none;">
							<span class="fas fa-fw fa-pause"></span> PAUSE
						</button>
						<spring:url value="/logout.do" var="logout" />
						<a href="${logout }" class="btn btn-block btn-outline-info"
							title="LOGOUT"> <span class="fas fa-fw fa-sign-out-alt"></span>
							LOGOUT
						</a>
					</div>
					<!-- <div class="form-inline w-100" id="manual" style="display: none;">
						<input type="text" id="start" name="start"
							placeholder="6 Digit Awal"
							class="form-control form-control-sm mb-2 mr-sm-2" maxlength="6" />
						<input type="text" id="end" name="end" placeholder="4 Digit Akhir"
							class="form-control form-control-sm mb-2 mr-sm-2" maxlength="4" />
						<button type="button" class="btn btn-sm btn-success mb-2 mr-sm-2"
							title="CHECK" onclick="check()">
							<span class="fas fa-fw fa-search"></span> CHECK
						</button>
					</div>
					<hr class="w-100" />
					<button onclick="toggleInput()" class="btn btn-block btn-link"
						title="PAUSE" id="toggle-btn">MANUAL INPUT</button> -->
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	const scanner = new Html5Qrcode("reader");
	const scannerConfig = {
		fps : 30,
		formatsToSupport : [Html5QrcodeSupportedFormats.QR_CODE]
	};
	const qrCodeSuccessCallback = (decodedText, decodedResult) => {
		scanner.stop();
		var url = decodedText;
		var temp = decodedText.split('/');
		
		/* window.location.href = "${process}".replace("trxid", decodedText) + "&fromUrl=" + fromUrl; */
		
		window.location.href= "${equipment}".replace("id",temp[temp.length-1].replace('.do',''));
	};
	
</script>