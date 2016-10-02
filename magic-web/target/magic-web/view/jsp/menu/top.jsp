<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href="/resources/ico/favicon.ico">
<link rel="stylesheet" href="/resources/css/top.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>top</title>
</head>
<body>
	<div class="top_content">
		<a href="/" target="_top">
		<img alt="返回首页" src="/resources/image/qqlogo_1x.png" style="float: left;">
		</a>
		<h1>后台管理系统</h1>
		<div class="top_user">
			<div>当前用户：${MagicUser }</div>
			<div><a href="/user/logout" target="_top">安全退出登录</a></div>
		</div>
	</div>
</body>
</html>