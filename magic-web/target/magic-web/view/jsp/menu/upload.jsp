<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery-1.7.2.min.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="/resources/ico/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/upload.css">
<link rel="stylesheet" type="text/css" href="/resources/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css">  
<link rel="stylesheet" type="text/css" href="/resources/plupload/js/jquery.ui.plupload/css/jquery.ui.plupload.css">  
<link rel="stylesheet" type="text/css" href="/resources/jquery-ui/jquery-ui.min.css"/>
<script type="text/javascript" src="/resources/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="/resources/plupload/js/plupload.full.min.js"></script>
<script type="text/javascript" src="/resources/plupload/js/jquery.ui.plupload/jquery.ui.plupload.js"></script>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<title>上传</title>
</head>
<body>
	<div class="upload_header">
		<div class="header_title">
			<h1>文件上传</h1>
		</div>
	</div>
	<div class="upload_container">
		<div class="upload_tabs">
			<div class="upload_tab upload_vedio"><a id="a_vedio">　视频　</a></div>
			<div class="upload_tab upload_image"><a id="a_image">　图片　</a></div>
			<div class="upload_tab upload_text"><a id="a_text">　文本　</a></div>
			
		</div>
		<div class="upload_content">
			<form action="" id="upload_form" >
				<div class="upload_input">
					<label id="lab_title">视频标题：</label>
					<input type="text" name="uploadTitle" id="upload_title">
					<b id="titleErrorTip"></b>
				</div>
				<div class="upload_input">
					<label id="lab_desc">视频描述：</label>
					<input type="text" name="uploadDesc" id="upload_desc">
					<b id="descErrorTip"></b>
				</div>
				<div style="width:860px;height: 320px;">
					<div style="width:750px;margin: 10px 0 0 10px;">
						<div id="uploader" >
							<p>您的浏览器未安装 Flash, Silverlight, Gears, BrowserPlus 或者支持 HTML5 .</p>  
							<input value="继续上传" id="Reload" type="button">  
						</div>
					</div>
					<b id="fileErrorTip"></b>
				</div>
				<div class="upload_button" >
					<input type="hidden" id="upload_files" value="" name="files">
					<input type="hidden" id="upload_type" name="uploadType" value="${uploadType }">
					<input type="button" value="确认上传" id="upload_submit">
				</div>
				<div><p id="uploadTip"></p></div>
			</form>
		</div>
	</div>
</body>
</html>