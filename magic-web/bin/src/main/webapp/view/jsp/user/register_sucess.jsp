<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href="/resources/ico/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	body {
		text-align: center;
		background: url(/resources/image/right_1600.png) right top no-repeat #f8fdff;
	}
	.bg{
		margin-top: 200px;
	}
</style>
<title>提示：注册成功。。。</title>
</head>
<body>
	<div class="bg" id="ibg">
		<div>注册成功，3秒后返回登录...</div>
		<div>如果没有反应请点击下面链接</div>
		<div>
			<a href="/user/login">返回登录</a>
		</div>
	</div>
</body>
<script type="text/javascript">
	window.onload=function(){
		setInterval(function(){
			window.location.href="/user/login";
		}, 3000);
	}
</script>
</html>