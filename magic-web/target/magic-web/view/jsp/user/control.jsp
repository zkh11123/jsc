<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/resources/css/index.css" />
<link rel="stylesheet" href="/resources/css/login.css" />
<link rel="shortcut icon" type="image/x-icon"
	href="/resources/ico/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery-1.7.2.min.js"></script>
<title>账号登录</title>
</head>
<body>
	<div class="bg" id="ibg">
		<div class="container">
			<div class="header"></div>
			<div class="content">
				<div class="left"></div>
				<div class="right">
					<form id="userLogin" action="/genarate" method="post">
						<div class="ipt_box">
							<div id="nick_bg" class="ipt_nor">
								<span class="label">寄生虫上传目录：</span> <input type="text"
									class="new_txt" id="userName" name="saveUrl" tabindex="6"
									style="z-index: 0">
									<b id="userNameError"></b>
							</div>
						</div>
						<div class="ipt_box">
							<div id="nick_bg" class="ipt_nor">
								<span class="label">页面生成的数量：</span> <input type="text"
									class="new_txt" id="password" name="createNum" tabindex="6"
									style="z-index: 0">
									<b id="passWordError"></b>
							</div>
						</div>
						<div class="ipt_box">
							<div id="nick_bg" class="ipt_nor">
								<span class="label">电话号码的信息：</span> <input type="text"
									class="new_txt" id="phoneNumber" name="phoneNumber" tabindex="6"
									style="z-index: 0">
									<b id="userNameError"></b>
							</div>
						</div>
						<div class="ipt_box">
							<div id="nick_bg" class="ipt_nor">
								<span class="label">能联系的QQ微信：</span> <input type="text"
									class="new_txt" id="tencentNumber" name="tencentNumber" tabindex="6"
									style="z-index: 0">
									<b id="userNameError"></b>
							</div>
						</div>
						<div class="ipt_box">
							<div id="nick_bg" class="ipt_nor">
								<span class="label">设置文件的前缀：</span> <input type="text"
									class="new_txt" id="filePrex" name="filePrex" tabindex="6"
									style="z-index: 0">
									<b id="userNameError"></b>
							</div>
						</div>
						<div class="submit" style="padding-top: 10px">
							<input type="submit" value="生　　成" title="登录" tabindex="23"
								id="submit" onclick="">
							<b id="loginTip"></b>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>