<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE HTML>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href="/resources/ico/favicon.ico">
<link type="text/css" rel="stylesheet" href="/resources/css/myupload.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function pageTurn(data){
		var currentPage = $("#currentPage").val();
		var pageTotal = $("#pageTotal").val();
		var afterPage = parseInt(currentPage)+parseInt(data);
		if(afterPage > 0 && afterPage <= pageTotal){
			$("#currentPage").val(afterPage);
			$("#mainForm").submit();
		}
	}
	
	function deleteMyInfos(id){
		if(confirm("确定要删除发布的信息吗？")){
			$.ajax({
				url:"/menu/deleteUploadInfos",
				type : "post",
				data : {"uploadId":id},
				dataType:"json",
				success:function(result){
					if(result.scode == '0'){
						location.reload(true);
					}else{
						alert(result.data);
					}
				},
				error : function(result){
					alert('删除失败');
				}
			});
		}else{
			return false;
		}
	}
</script>
<title>center</title>
</head>
<body>
<!-- 查询条件框 -->
	<form id="mainForm" name="mainForm" action="/menu/mylist" method="POST" >
		<div class="adminUp_wrap">
			<!-- 查询条件 -->
			<fieldset class="clearfix adminSearch">
				<label>上传标题(模糊)：</label>
				<input type="text" name="fileTitle" value="${pageInfos.fileTitle }">
				<label>时间段：</label>
				<select name="timeRange">
					<option value="0" <c:if test="${pageInfos.timeRange==0 }">selected="selected"</c:if>>全部</option>
					<option value="1" <c:if test="${pageInfos.timeRange==1 }">selected="selected"</c:if>>一个月内</option>
					<option value="3" <c:if test="${pageInfos.timeRange==3 }">selected="selected"</c:if>>一个月到三个月</option>
					<option value="6" <c:if test="${pageInfos.timeRange==6 }">selected="selected"</c:if>>三个月到六个月</option>
					<option value="9" <c:if test="${pageInfos.timeRange==9 }">selected="selected"</c:if>>六个月之前</option>
				</select>
				<input type="submit" value="查询" class="buttonfix">
				<input type="hidden" name="fileType" value="${pageInfos.fileType }">
			</fieldset>
			<!-- 查询条件 end -->
		</div>
		<!-- 查询条件框 end -->
	
		<!-- 列表分页 -->
		<div class="adminContent clearfix">
			<table width="100%" class="table_bg01 table_hg01" border=2 cellspacing=0 cellpadding=0 >
				<thead>
					<tr>
						<th width="10%">上传类型</th>
						<th width="20%">上传时间</th>
						<th width="20%">文件标题</th>
						<th width="25%">文件描述</th>
						<th width="25%">用户操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="file" items="${uploadFiles }">
						<tr>
							<td>${file.uploadType=='vedio'?'视频':file.uploadType=='image'?'图片':file.uploadType=='text'?'文本':'' }</td>
							<td><fmt:formatDate value="${file.uploadTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
							<td>${file.uploadTitle }</td>
							<td>${file.fileDesc }</td>
							<td><a href="javascript:void(0);" onclick="return deleteMyInfos('${file.id }')">删除</a></td>
						</tr>
					</c:forEach>
					<!-- <tr>
						<td colspan="5"><font color="red">暂时没有查询到记录</font></td>
					</tr> -->
				</tbody>
			</table>
			<div class="page_foot">
				<label>每页数</label>
				<input type="text" name="pageSize" value="${pageInfos.pageSize }">
				<span style="float: right;">
					<label style="margin-left: 1px;">当前页</label>
					<input class="page" type="text" name="currentPage" id="currentPage" value="${pageInfos.currentPage }">
					<label style="margin-left: 1px;">总页数</label>
					<input class="page" type="text" name="pageTotal" id="pageTotal" value="${pageInfos.pageTotal }">
					<input type="button" name="fontPage" value="上一页" onclick="pageTurn(-1)">
					<input type="button" name="nextPage" value="下一页" onclick="pageTurn(1)">
				</span>
			</div>
		</div>
	</form>
</body>
</html>