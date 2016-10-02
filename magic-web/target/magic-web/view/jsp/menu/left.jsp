<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href="/resources/ico/favicon.ico">
<link rel="stylesheet" href="/resources/css/left.css" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>left</title>
</head>
<body>
	<div class="left_content">
		<h2 class="left_menu"><a href="/menu/main" target="mainFrame">用户最新发布</a></h2>
		<hr>
		<ul class="left_list">
			<li class="left_upload">
				<a href="/menu/upload?type=vedio" target="mainFrame">
				<button class="left_click">上传视频</button></a></li>
			<li class="left_myfile">
				<a href="/menu/mylist?fileType=vedio" target="mainFrame">
				<button class="left_click">我上传的视频</button></a></li>
		</ul>
		<hr>
		<ul class="left_list">
			<li class="left_upload"><a href="/menu/upload?type=image" target="mainFrame">
				<button class="left_click">上传图片</button></a></li>
			<li class="left_myfile"><a href="/menu/mylist?fileType=image" target="mainFrame">
				<button class="left_click">我上传的图片</button></a></li>
		</ul>
		<hr>
		<ul class="left_list">
			<li class="left_upload"><a href="/menu/upload?type=text" target="mainFrame">
				<button class="left_click">上传文本文件</button></a></li>
			<li class="left_myfile"><a href="/menu/mylist?fileType=text" target="mainFrame">
				<button class="left_click">我上传的文件</button></a></li>
		</ul>
	</div>
</body>
</html>