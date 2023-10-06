<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="login-box">
	<div class="login-logo">
		<a href="../../index2.html"><b>DDIT</b>BOARD</a>
	</div>
	<div class="card">
		<div class="card-body login-card-body">
			<p class="login-box-msg">OTP 인증</p>

			<form action="/notice/otpCheck.do" method="post" onsubmit="return frmCheck();">
				<div class="input-group mb-3">
					<p class="mb-0">키 인증 번호 :</p><br/>
					<input type="text" class="form-control" name="encodedKey" value="${encodedKey }" readonly="readonly"/>
				</div>
				<div class="input-group mb-3">
					<p class="mb-0">바코드 :</p><br/>
					<img src="${qrUrl}"/>
				</div>
				<div class="input-group mb-3">
					<p class="mb-0">코드 입력창 :</p><br/>
					<input type="text" class="form-control" id="code" name="code" placeholder="코드를 입력해주세요" />
				</div>
				<div class="row">
					<div class="col-8">
						<div class="icheck-primary"></div>
					</div>
					<div class="col-4">
						<input type="submit" class="btn btn-primary btn-block" value="전송">
					</div>
				</div>
			</form>
		</div>
	</div>
</div> 
<script type="text/javascript">
$(function(){
	var errorMsg = "${errorMsg}";
	if(errorMsg != ""){
		alert(errorMsg); 
	}
});
 
function frmCheck() {
	if ($("#code").val() == "") {
		alert("코드를 입력해주세요.");
		$("#code").focus();
		return false;
	}
}
</script>