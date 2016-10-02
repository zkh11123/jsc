$(document).ready(function(){
	$("#submit").click(function(){
		var tag = true;
		if(!userName()) tag = false;
		if(!password()) tag = false;
		if(tag){
			var params=$("#userLogin").serialize();
			$.ajax({
  				type : "POST",
  				url : "/user/login",
  				data : params,
                dataType: "text",
  				success : function(data) {
  				   if (isNaN(data)) {              
  						var objs =  $.parseJSON(data);
  						if (objs != null) {
  							if(objs.scode == "0")
  							{
  							   window.location.href="http://www.xiu.com/";
  							}else{
  								$("#loginTip").text(objs.data);
  			            	}
  						}else {
  							$("#loginTip").text("登录异常！");
						}
  					}
  				},
  				error : function(data) {
  					var objs =  $.parseJSON(data);
  					if(objs != null){
  						$("#loginTip").text(objs.data);
  					}else{
  						$("#loginTip").text("登录异常！");
  					}
  				}
			});
		}
	});
	
	$("#userName").blur(function(){
		var tag = true;
		if(!userName()) 
			tag = false;
		return tag;
	});
	
	//密码输入框鼠标离开事件验证
	$("#password").blur(function(){
		var tag = true;
		if(!password()) tag = false;
		return tag;
	});
});

//用户名验证
function userName(){
	$("#userNameError").removeClass();
	var userName =$.trim($("#userName").val());
	if(userName==""){
		$("#userNameError").addClass("error_prompt").html("请输入用户名");
		return false;
	}
	$("#userNameError").addClass("ok_prompt").html("");
	return true;
}

//用户密码验证
function password(){
	$("#passWordError").removeClass();
	var password =$.trim($("#password").val());
	if(password==""){
		$("#passWordError").addClass("error_prompt").html("请输入密码");
		return false;
	}
	$("#passWordError").addClass("ok_prompt").html("");
	return true;
}