<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=basePath%>plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="<%=basePath%>plugins/bootstrap/css/font-awesome.min.css?v=4.4.0"
	rel="stylesheet">
<link href="<%=basePath%>plugins/bootstrap/css/animate.min.css"
	rel="stylesheet">
<link href="<%=basePath%>plugins/bootstrap/css/style.min.css"
	rel="stylesheet">
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight" id="content">
		<ul class="filelist" id="filelist">
			<c:forEach items="${imgs }" var="i"> 
				<li id="img${i.id }">
				<p class="title">${i.name }</p>
				<p class="imgWrap">
					<img src="<%=basePath %>${i.path }" onclick="showImg(this)">
				</p>
				<div class="file-panel" id="p1" style="height: 0px;">
					<span class="cancel" title="删除" onclick="deleteImg(${i.id})">删除</span>
				</div>
			</li>
			</c:forEach>
		</ul>
			<div id="noimg" class="middle-box text-center animated fadeInDown" style="margin-top:20px;<c:if test='${imgs.size() != 0 }'>display:none</c:if>">
        		<h2 class="font-bold">暂无相关文件,请上传</h2>
        		</div>
	</div>
	
	<input type="file" id="imgpick" style="display: none">
	<script type="text/javascript"
		src="<%=basePath%>plugins/jquery/jQuery.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>plugins/layer2.4/layer/layer.js"></script>
	<script src="<%=basePath%>plugins/webuploader/webuploader.nolog.js"></script>
	<script type="text/javascript">
	// 初始化Web Uploader
	var lindex;
	var imgUploader = WebUploader.create({
	    // 选完文件后，是否自动上传。
	    auto: true,
		pick: '#imgpick',
	    swf: "<%=basePath%>plugins/webuploader/Uploader.swf",
	    server:"<%=basePath%>admin/station/uploadImage",
	    // 只允许选择图片文件。
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/*'
	    }
	    ,formData:{
	    	"stationId":"${stationId}",
	    	"imgtype":"${type}"
	    }
	});
	imgUploader.on( "uploadSuccess",function(file,res){
	   		layer.close(lindex);
	   		if(res.success){
	   			$("#filelist").prepend("<li id='img"+res.id+"'>"
				+"<p class='title'>"+res.name+"</p>"
				+"<p class='imgWrap'>"
				+"<img src='<%=basePath%>"+res.path+"' onclick='showImg(this)'>"
				+"</p>"
				+"<div class='file-panel' style='height: 0px;'>"
				+"<span class='cancel' onclick='deleteImg("+res.id+")'>删除</span>"
				+"</div>"
				+"</li>")
	   		}
	   		$("#noimg").hide();
	   		$("#img"+res.id).on("mouseenter",function(){
				$("#img"+res.id+" .title").stop().animate({height:28});
				$("#img"+res.id+" .file-panel").stop().animate({height:28});
			})
			$("#img"+res.id).on("mouseleave",function(){
				$("#img"+res.id+" .title").stop().animate({height:0});
				$("#img"+res.id+" .file-panel").stop().animate({height:0});
			})
			layer.msg(res.msg);
			imgUploader.reset();
	   	});
	imgUploader.on( "startUpload",function(file,res){
	    	lindex = layer.msg('上传中...', {shade:0.2,shadeClose:false,icon: 16,time:0});
	   	});
	imgUploader.on( "uploadError",function(file,res){
	    	layer.close(lindex);
			layer.msg(res.msg);
			imgUploader.reset();
	   	});
	$(function(){
		<c:forEach items="${imgs }" var="i"> 
			$("#img${i.id}").on("mouseenter",function(){
				$("#img${i.id} .title").stop().animate({height:28});
				$("#img${i.id} .file-panel").stop().animate({height:28});
			})
			$("#img${i.id}").on("mouseleave",function(){
				$("#img${i.id} .title").stop().animate({height:0});
				$("#img${i.id} .file-panel").stop().animate({height:0});
			})
		</c:forEach>
	})
	function deleteImg(id){
		$.ajax({
			type: "POST",
			url: "<%=basePath%>admin/station/deleteImg",     
			dataType:"json",
			data: {
				id:id  
			},
			success: function(res){
				if(res.success == true){
					$("#img"+id).remove();
				}
				layer.msg(res.msg);
				if($("#filelist li").length == 0){
					$("#noimg").show();
				}
			}  
		}); 
	}
	function addImg(){
		$(".webuploader-element-invisible").trigger("click");
	}
	function showImg(obj){
		var image = new Image();
		  image.src = obj.src;
		  if (image.complete) {
			  parent.showImg(image.src,image.width,image.height)
		  } 
		
	}
	
	</script>
</body>
</html>