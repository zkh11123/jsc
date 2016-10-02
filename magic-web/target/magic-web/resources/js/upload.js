$(document).ready(function(){
	
	var type = $("#upload_type").val();
	
	if(type == "vedio"){
		$(".upload_vedio").css("border-top-color","#3E96D6");
		$(".upload_vedio").css("font-weight","bold");
		$(".upload_vedio").css("border-top-width","3px");
		$(".upload_image").css("border-top-color","#C8DCF2");
		$(".upload_image").css("font-weight","normal");
		$(".upload_image").css("border-top-width","1px");
		$(".upload_text").css("border-top-color","#C8DCF2");
		$(".upload_text").css("font-weight","normal");
		$(".upload_text").css("border-top-width","1px");
		$("#lab_title").text("视频标题：");
		$("#lab_file").text("选择视频：");
		$("#lab_desc").text("视频描述：");
		
		//plupload
		$("#uploader").plupload({
			// General settings
			runtimes : 'html5,flash,silverlight,html4',
			url : '/menu/plupload',
			max_file_count: 20,
			chunk_size: '1mb',
			resize : {
				width : 200, 
				height : 200, 
				quality : 90,
				crop: true // crop to exact dimensions
			},
			filters : {
				// Maximum file size
				max_file_size : '1000mb',
				// Specify what files to browse for
	            mime_types : [ //只允许上传图片和zip文件
	                        { title : "媒体文件", extensions : "asx,asf,mpg,wmv,3gp,mp4,mov,avi,flv,wmv9,rm,rmvb" }
	                      ],
	            prevent_duplicates : true //不允许选取重复文件
			},
			// Rename files by clicking on their titles
			rename: true,
			// Sort files
			sortable: true,

			// Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
			dragdrop: true,
			// Views to activate
			views: {
				list: true,
				thumbs: false, // Show thumbs
				active: 'list'
			},

			// Flash settings
			flash_swf_url : '/resources/plupload/js/Moxie.swf',

			// Silverlight settings
			silverlight_xap_url : '/resources/plupload/js/Moxie.xap',
			
			unique_names : true
		});
	}else if (type == "image") {
		$(".upload_image").css("border-top-color","#3E96D6");
		$(".upload_image").css("font-weight","bold");
		$(".upload_image").css("border-top-width","3px");
		$(".upload_vedio").css("border-top-color","#C8DCF2");
		$(".upload_vedio").css("font-weight","normal");
		$(".upload_vedio").css("border-top-width","1px");
		$(".upload_text").css("border-top-color","#C8DCF2");
		$(".upload_text").css("font-weight","normal");
		$(".upload_text").css("border-top-width","1px");
		$("#lab_title").text("图片标题：");
		$("#lab_file").text("选择图片：");
		$("#lab_desc").text("图片描述：");
		//plupload
		$("#uploader").plupload({
			// General settings
			runtimes : 'html5,flash,silverlight,html4',
			url : '/menu/plupload',
			max_file_count: 20,
			chunk_size: '1mb',
			resize : {
				width : 200, 
				height : 200, 
				quality : 90,
				crop: true // crop to exact dimensions
			},
			filters : {
				// Maximum file size
				max_file_size : '1000mb',
				// Specify what files to browse for
	            mime_types : [ //只允许上传图片和zip文件
	                        { title : "图片文件", extensions : "jpg,gif,png" }
	                      ],
	            prevent_duplicates : true //不允许选取重复文件
			},
			// Rename files by clicking on their titles
			rename: true,
			// Sort files
			sortable: true,

			// Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
			dragdrop: true,
			// Views to activate
			views: {
				list: true,
				thumbs: false, // Show thumbs
				active: 'list'
			},

			// Flash settings
			flash_swf_url : '/resources/plupload/js/Moxie.swf',

			// Silverlight settings
			silverlight_xap_url : '/resources/plupload/js/Moxie.xap',
			
			unique_names : true
		});
	}else if (type == "text") {
		$(".upload_text").css("border-top-color","#3E96D6");
		$(".upload_text").css("font-weight","bold");
		$(".upload_text").css("border-top-width","3px");
		$(".upload_image").css("border-top-color","#C8DCF2");
		$(".upload_image").css("font-weight","normal");
		$(".upload_image").css("border-top-width","1px");
		$(".upload_vedio").css("border-top-color","#C8DCF2");
		$(".upload_vedio").css("font-weight","normal");
		$(".upload_vedio").css("border-top-width","1px");
		$("#lab_title").text("文本标题：");
		$("#lab_file").text("选择文本：");
		$("#lab_desc").text("文本描述：");
		//plupload
		$("#uploader").plupload({
			// General settings
			runtimes : 'html5,flash,silverlight,html4',
			url : '/menu/plupload',
			max_file_count: 20,
			chunk_size: '1mb',
			resize : {
				width : 200, 
				height : 200, 
				quality : 90,
				crop: true // crop to exact dimensions
			},
			filters : {
				// Maximum file size
				max_file_size : '1000mb',
				// Specify what files to browse for
	            mime_types : [ //只允许上传图片和zip文件
	                        { title : "文本文件", extensions : "txt" }
	                      ],
	            prevent_duplicates : true //不允许选取重复文件
			},
			// Rename files by clicking on their titles
			rename: true,
			// Sort files
			sortable: true,

			// Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
			dragdrop: true,
			// Views to activate
			views: {
				list: true,
				thumbs: false, // Show thumbs
				active: 'list'
			},

			// Flash settings
			flash_swf_url : '/resources/plupload/js/Moxie.swf',

			// Silverlight settings
			silverlight_xap_url : '/resources/plupload/js/Moxie.xap',
			
			unique_names : true
		});
	}
	
	$('#uploader').on('complete', function(uploader,data) {
		var files = data.files;
		var fileLists="";
		for (var i = 0 ; i < files.length ; i++) {
			var fileName = files[i].target_name;
			var fileOriName = files[i].name;
			fileLists += fileName+"|"+fileOriName+",";
		}
		$("#upload_files").val(fileLists);
		$("#upload_files").blur();
	});
	
	$("#upload_submit").click(function(){
		  var tag = true;
		  if(!validTitle()) tag = false;
		  if(!validDesc()) tag = false;
		  if(!validFile()) tag = false;
		  
		  var upload_type = $("#upload_type").val();
		  
		  if(tag){
			  var params=$("#upload_form").serialize();
			  $.ajax({
	  				type : "POST",
	  				url : "/menu/uploadfile",
	  				data : params,
	                dataType: "text",
	  				success : function(data) {
	  				   if (isNaN(data)) {              
	  						var objs =  $.parseJSON(data);
	  						if (objs != null) {
	  							if(objs.scode == "0")
	  							{
	  							   window.location.href="/menu/mylist?fileType="+upload_type;
	  							}else{
	  								$("#uploadTip").text("请检查数据是否完整，文件是否已经上传！");
	  			            	}
	  						}else {
	  							$("#uploadTip").text("上传异常！");
							}
	  					}
	  				},
	  				error : function(data) {
	  					$("#uploadTip").text("上传异常！");
	  				}
				});
		  }
	});
	
	$("#upload_title").blur(function() {
		var tag = true;
		if(!validTitle()) 
			tag = false;
		return tag;
	});
	
	$("#upload_desc").blur(function() {
		var tag = true;
		if(!validDesc()) 
			tag = false;
		return tag;
	});
	
	$("#upload_files").blur(function() {
		var tag = true;
		if(!validFile()) 
			tag = false;
		return tag;
	});
	
});

function validTitle() {
	$("#titleErrorTip").removeClass();
	var userName =$.trim($("#upload_title").val());
	if(userName==""){
		$("#titleErrorTip").addClass("error_prompt").html("请输入标题");
		return false;
	}
	$("#titleErrorTip").addClass("ok_prompt").html("");
	return true;
}

function validDesc(){
	$("#descErrorTip").removeClass();
	var userName =$.trim($("#upload_desc").val());
	if(userName==""){
		$("#descErrorTip").addClass("error_prompt").html("请输入描述");
		return false;
	}
	$("#descErrorTip").addClass("ok_prompt").html("");
	return true;
}
	
function validFile(){
	$("#fileErrorTip").removeClass();
	var userName =$.trim($("#upload_files").val());
	if(userName==""){
		$("#fileErrorTip").addClass("error_prompt").html("请上传文件");
		return false;
	}
	$("#fileErrorTip").addClass("ok_prompt").html("");
	return true;
}