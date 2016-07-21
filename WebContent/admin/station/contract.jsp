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
		<ul class="filelist">
			<li id="file1">
				<p class="title" id="t1">截图2-超sadadw edwdfsdsasa时.png</p>
				<p class="imgWrap">
					<img src="img/p_big1.jpg">
				</p>
				<div class="file-panel" id="p1" style="height: 0px;">
					<span class="cancel" title="删除">删除</span> <span class="rotateRight">向右旋转</span>
					<span class="rotateLeft">向左旋转</span>
				</div>
			</li>
			<li id="">
				<p class="title">截图2-超时.png</p>
				<p class="imgWrap">
					<img src="img/p_big1.jpg">
				</p>
				<div class="file-panel" style="height: 0px;">
					<span class="cancel" title="删除">删除</span> <span class="rotateRight">向右旋转</span>
					<span class="rotateLeft">向左旋转</span>
				</div>
			</li>
			<li id="">
				<p class="title">截图2-超时.png</p>
				<p class="imgWrap">
					<img src="img/p_big1.jpg">
				</p>
				<div class="file-panel" style="height: 0px;">
					<span class="cancel">删除</span> <span class="rotateRight">向右旋转</span>
					<span class="rotateLeft">向左旋转</span>
				</div>
			</li>
			<li id="">
				<p class="title">截图2-超时.png</p>
				<p class="imgWrap">
					<img src="img/p_big1.jpg">
				</p>
				<div class="file-panel" style="height: 0px;">
					<span class="cancel">删除</span> <span class="rotateRight">向右旋转</span>
					<span class="rotateLeft">向左旋转</span>
				</div>
			</li>
			<li id="">
				<p class="title">截图2-超时.png</p>
				<p class="imgWrap">
					<img src="img/p_big1.jpg">
				</p>
				<div class="file-panel" style="height: 0px;">
					<span class="cancel" title="删除">删除</span> <span class="rotateRight">向右旋转</span>
					<span class="rotateLeft">向左旋转</span>
				</div>
			</li>
		</ul>
	</div>
	<input type="file" id="picker" style="display: none">
	<script type="text/javascript"
		src="<%=basePath%>plugins/jquery/jQuery.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>plugins/layer2.4/layer/layer.js"></script>
	<script src="<%=basePath%>plugins/webuploader/webuploader.nolog.js"></script>
	<script type="text/javascript">
	// 初始化Web Uploader
	var uploader = WebUploader.create({
	    // 选完文件后，是否自动上传。
	    auto: true,
		pick: '#picker',
	    swf: "<%=basePath%>plugins/webuploader/Uploader.swf",
	    server:"<%=basePath%>admin/station/importExcel",
	    // 只允许选择图片文件。
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/*'
	    },
	    thumb:{
	    	width: 180,
	        height: 120,
	        // 图片质量，只有type为`image/jpeg`的时候才有效。
	        quality: 80,
	        // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
	        allowMagnify: false,
	        // 是否允许裁剪。
	        crop: true
	    }
	});
	$(function(){
		$("#file1").on("mouseenter",function(){
		$("#t1").stop().animate({height:28});
			$("#p1").stop().animate({height:28});
		})
		$("#file1").on("mouseleave",function(){
		$("#t1").stop().animate({height:0});
			$("#p1").stop().animate({height:0});
		})
	})
	function addImg(){
		$("#picker").trigger("click");
	}
	
	</script>
</body>
</html>