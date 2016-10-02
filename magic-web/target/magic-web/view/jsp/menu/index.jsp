<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href="/resources/ico/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
<style type="text/css">
	#leftFrame {
		border-right: 1px solid silver;
	}
</style>
</head>
<frameset rows="60,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="/menu/top" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame"/>
	<frameset cols="230,*" frameborder="no" border="0" framespacing="0">
		<frame src="/menu/left" name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame" title="leftFrame" />
		<frameset cols="10,*" frameborder="no" border="0" framespacing="0">
			<frame src="/menu/center" name="centerFrame" scrolling="no" id="centerFrame" title="centerFrame" />
			<frameset rows="*,26" cols="*" frameborder="no" border="0" framespacing="0">
				<frame src="/menu/main" name="mainFrame" id="mainFrame" title="mainFrame"/>
				<frame src="/menu/bottom" name="bottomFrame" scrolling="no" id="bottomFrame" title="bottomFrame" />
			</frameset>
		</frameset>
	</frameset>
</frameset>
<body>
</body>
</html>