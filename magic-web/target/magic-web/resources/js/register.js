$(document).ready(function() {
	//点击注册
	//用户名鼠标离开事件验证
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
	
	//确认密码输入框鼠标离开事件验证
	$("#repeatPassword").blur(function(){
		var tag = true;
		if(!repeatPassword()) tag = false;
		return tag;
	});
	
	//邮箱输入框鼠标离开事件验证
	$("#email").blur(function(){
		var tag = true;
		if(!email()) tag = false;
		return tag;
	});
	//电话号码输入框鼠标离开事件验证
	$("#phoneNumber").blur(function(){
		var tag = true;
		if(!phoneNumber()) tag = false;
		return tag;
	});
	
	$("#validimage").blur(function(){
		var tag = true;
		if(!validCode()) tag = false;
		return tag;
	});
	
	$("#submit").click(function() {
		var tag = true;
		if(!email()) tag = false;
		if(!userName()) tag = false;
		if(!password()) tag = false;
		if(!phoneNumber()) tag = false;
		if(!repeatPassword()) tag = false;
		
		if(tag){
			var params=$("#userRegister").serialize();
			$.ajax({
  				type : "POST",
  				url : "/user/register",
  				data : params,
                dataType: "text",
  				success : function(data) {
  				   if (isNaN(data)) {              
  						var objs =  $.parseJSON(data);
  						if (objs != null) {
  							if(objs.scode == "0")
  							{
  							   window.location.href="/user/regsuccess";
  							}else{
  							   $("#registerTip").text(objs.data);
  			            	}
  						}else {
  							$("#registerTip").text("注册异常！");
						}
  					}
  				},
  				error : function(data) {
  					var objs =  $.parseJSON(data);
  					if(objs != null){
  						$("#registerTip").text(objs.data);
  					}else{
  						$("#registerTip").text("注册异常！");
  					}
  				}
  		});
			
		}
	});
});

//用户名验证
function userName(){
	var patt1=new RegExp("\\s");
	$("#userNameError").removeClass();
	var userName =$.trim($("#userName").val());
	if(userName==""){
		$("#userNameError").addClass("error_prompt").html("请输入用户名");
		return false;
	}
	if(patt1.test(userName)){
    	$("#userNameError").addClass("error_prompt").html("用户名中不能包含空格");
    	return false;
    }
	
	$("#userNameError").addClass("ok_prompt").html("");
	return true;
}

//用户密码验证
function password(){
	var patt1=new RegExp("\\s");
	var patt2=new RegExp("[a-z]");
	var patt3=new RegExp("[A-Z]");
	var patt4=new RegExp("\\d");
	$("#passWordError").removeClass();
	var password =$.trim($("#password").val());
	if(password==""){
		$("#passWordError").addClass("error_prompt").html("请输入密码");
		return false;
	}
	if(patt1.test(password)){
    	$("#passWordError").addClass("error_prompt").html("密码中不能包含空格");
    	return false;
    }
	
	if(!patt2.test(password)||!patt3.test(password)||!patt4.test(password)){
		$("#passWordError").addClass("error_prompt").html("密码中必须包含数字大小写！");
    	return false;
	}
	
	$("#passWordError").addClass("ok_prompt").html("");
	return true;
}

//用户密码确认验证
function repeatPassword(){
	$("#repeatPasswordError").removeClass();
	var password =$.trim($("#password").val());
	var repeatPassword =$.trim($("#repeatPassword").val());
	if(repeatPassword==""){
		$("#repeatPasswordError").addClass("error_prompt").html("请输入密码");
		return false;
	}
	if(password != repeatPassword){
		$("#repeatPasswordError").addClass("error_prompt").html("确认密码与输入密码不一致");
		return false;
	}
	$("#repeatPasswordError").addClass("ok_prompt").html("");
	return true;
}



//用户邮箱验证
function email(){
	var tag = /^\w+[\w|.]+@xiu\.com$/;
	$("#emailError").removeClass().html("");;
	var email =$.trim($("#email").val());
	if(email==""){
		$("#emailError").addClass("error_prompt").html("请输入email");
		return false;
	}
	if(!tag.test(email)){
		$("#emailError").addClass("error_prompt").html("email格式不正确,或者不是@xiu.com邮箱");
		return false;	
	}
	$("#emailError").addClass("ok_prompt").html("");
	return true;
}

//用户电话号码验证
function phoneNumber(){
	$("#phoneNumberError").removeClass();
	var tag = /^\d+$/;
	var patt1=new RegExp("\\s");
	var phoneNumber =$.trim($("#phoneNumber").val());
	if(phoneNumber==""){
		$("#phoneNumberError").addClass("error_prompt").html("请输入电话号码");
		return false;
	}
	if(patt1.test(phoneNumber)){
    	$("#phoneNumberError").addClass("error_prompt").html("电话号码中不能包含空格");
    	return false;
    }
	if(!tag.test(phoneNumber)){
		$("#phoneNumberError").addClass("error_prompt").html("电话号码只能用数字");
		return false;	
	}
	
	if(phoneNumber.length != 11){
		$("#phoneNumberError").addClass("error_prompt").html("电话号码职能是11位手机号码");
		return false;	
	}
	$("#phoneNumberError").addClass("ok_prompt").html("");
	return true;
}

//用户密码验证
function validCode(){
	$("#validImageError").removeClass();
	var validimageCode =$.trim($("#validimage").val());
	if(validimageCode==""){
		$("#validImageError").addClass("error_prompt").html("请输入验证码");
		return false;
	}
	$("#validImageError").addClass("ok_prompt").html("");
	return true;
}