<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/resources/css/index.css" />
<link rel="shortcut icon" type="image/x-icon"
	href="/resources/ico/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/resources/js/register.js"></script>
<title>账号注册</title>
</head>
<body>
	<div class="bg" id="ibg">
		<div class="container">
			<div class="header"></div>
			<div class="content">
				<div class="left">
					<div id="nav_1" class="nav_box cur">
						<span class="dt nav_1">帐号注册</span> <span class="dd">字母数字特殊字符组成</span>
					</div>
				</div>
				<div class="right">
					<form id="userRegister" action="" method="post">
						<div class="ipt_box">
							<div id="nick_bg" class="ipt_nor">
								<span class="label">用户账号：</span> <input type="text"
									class="new_txt" id="userName" name="userName" tabindex="6"
									style="z-index: 0" maxlength="24">
									<b id="userNameError"></b>
							</div>
						</div>
						<div class="ipt_box">
							<div id="nick_bg" class="ipt_nor">
								<span class="label">密　　码：</span> <input type="password"
									class="new_txt" id="password" name="password" tabindex="6"
									style="z-index: 0" maxlength="24">
									<b id="passWordError"></b>
							</div>
						</div>
						<div class="ipt_box">
							<div id="nick_bg" class="ipt_nor">
								<span class="label">确认密码：</span> <input type="password"
									class="new_txt" id="repeatPassword" name="repeatPassword" tabindex="6"
									style="z-index: 0" maxlength="24">
									<b id="repeatPasswordError"></b>
							</div>
						</div>
						<div class="ipt_box">
							<div id="nick_bg" class="ipt_nor">
								<span class="label">联系方式：</span> <input type="text"
									class="new_txt" id="phoneNumber" name="phoneNumber" tabindex="6"
									style="z-index: 0" maxlength="24">
									<b id="phoneNumberError"></b>
							</div>
						</div>
						<div class="ipt_box">
							<div id="nick_bg" class="ipt_nor">
								<span class="label">邮箱地址：</span> <input type="email"
									class="new_txt" id="email" name="email" tabindex="6"
									style="z-index: 0" maxlength="24">
									<b id="emailError"></b>
							</div>
						</div>
						<div class="ipt_box">
							<div id="nick_bg" class="ipt_nor" style="height: 45px;">
								<div style="float: left;">
								<span class="label">　验证码：</span> <input type="text"
									class="new_txt" id="validimage" name="validimage" tabindex="6"
									style="z-index: 0;width: 160px;" maxlength="24">
								</div>
								<div style="float: left; margin-left: 10px;">
									<img src="/captcha/validimg" id="validImage"
										onclick="this.src='/captcha/validimg?d='+new Date().getTime()"/>
								</div>
								<b id="validImageError"></b>
							</div>
						</div>
						<div class="submit" style="padding-top: 10px">
							<input type="button" value="立即注册" title="立即注册" tabindex="23"
								id="submit" onclick="">
							<b id="registerTip"></b>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>