<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/resources/ico/favicon.ico">
<link rel="stylesheet" href="/resources/css/detail.css" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery-1.7.2.min.js"></script>

<title>main</title>
</head>
<body>
	<div class="displayHeader">
		<h1>标题：${uploadInfo.uploadTitle }</h1>
		<label>描述：${uploadInfo.fileDesc }</label>
	</div>
	<hr>
	<div class="displayContent">
		<c:forEach items="${uploadInfoDetail }" var="detail">
			<c:if test="${uploadInfo.uploadType=='vedio' }">
				<div class="vedio">
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" >
						<param name="quality" value="high">
						<param name="allowFullScreen" value="true" />
						<embed
							src="/resources/vedio/player.swf?vcastr_file=${detail.fileAccessPath }"
							quality="high"
							type="application/x-shockwave-flash" width="800" height="600">
						</embed>
					</object>
				</div>
			</c:if>
			<c:if test="${uploadInfo.uploadType=='image' }">
				<div class="image">
					<img width="800" height="600" src="${detail.fileAccessPath }"></img>
				</div>
			</c:if>
			<c:if test="${uploadInfo.uploadType=='text' }">
				<div class="text" id="${detail.id }">
				${detail.textContent } 
				</div>
			</c:if>
		</c:forEach>
	</div>
	<div class="displayfooter">
		<div class=""></div>
	</div>
</body>
</html>