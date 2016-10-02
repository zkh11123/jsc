<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/resources/ico/favicon.ico">
<link rel="stylesheet" type="css/text" href="/resources/css/main.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main</title>
</head>
<body>
	<div class="displayHeader">
		<h4>最新播报：</h4>
	</div>
	<c:forEach items="${uploadInfos }" var="item">
		<div class="displayContent">
			<div class="rowContent">用户
				<span class="user">${item.userName }</span>在
				<span class="time">${item.uploadTime }</span>时刻上传了
				<span class="fileNum">${item.uploadCnt }</span>
				<span class="fileType">个${item.uploadType }</span>
				<span class="detail"><a href="/menu/detail?uploadId=${item.uploadId }">点击查看详情</a></span>
			</div>
		</div>
	</c:forEach>
	<div class="displayfooter">
	
	</div>
</body>
</html>