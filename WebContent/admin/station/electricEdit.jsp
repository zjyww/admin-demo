<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>

 <%    
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="<%=basePath%>plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>plugins/bootstrap/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="<%=basePath%>plugins/bootstrap/css/animate.min.css" rel="stylesheet">
    <link href="<%=basePath%>plugins/bootstrap/css/style.min.css" rel="stylesheet">
    <style>
		.form-group{
			margin-bottom:10px;
		}
		.form-control{
			padding:2px 4px;
			line-height:24px;
			height:34px;
		}
		.col-sm-1{
			width:10%
		}
		.col-sm-3{
			width:23.3333333%
		}
		.form-group .col-sm-1,.form-froup .col-sm-3{
			padding-right:2px;
			padding-left:2px;
			padding-top:8px;
		}
	</style>
	</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		 <form method="get" class="form-horizontal" >
		 	<div class="form-group">
				<label class="col-sm-2 control-label">站点名称:</label>
					<div class="col-sm-4">
					<input type="hidden" value="${stationElectric.id }" name="stationElectric.id"/>
					<input type="hidden" value="${station.id }" name="stationElectric.stationid"/>
						<input type="text" class="form-control " readonly name="station.name" value="${station.name }">
                    </div>
                    <label class="col-sm-2 control-label">站点编码:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" readonly name="station.code" value="${station.code}">
                    </div>
             </div>
             <div class="form-group">
				<label class="col-sm-2 control-label">电表:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="stationElectric.electricno" value="${stationElectric == null ? station.electricity:stationElectric.electricno}">
                    </div>
                    <label class="col-sm-2 control-label">单价:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="stationElectric.price" value="${stationElectric.price == null?station.price:stationElectric.price }">
                    </div>
             </div>
                          <div class="form-group">
				<label class="col-sm-2 control-label">用电量:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="stationElectric.power" value="${stationElectric.power }">
                    </div>
                    <label class="col-sm-2 control-label">总金额:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="stationElectric.payment" value="${stationElectric.payment }">
                    </div>
             </div>
             <div class="form-group">
				<label class="col-sm-2 control-label">到账日期:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control layer-date laydate-icon" name="stationElectric.paydate" id="paydate" value="${stationElectric.paydate }">
                    </div>
                    <label class="col-sm-2 control-label">报账日期:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control layer-date laydate-icon" name="stationElectric.createdate" id="createdate" value="${stationElectric.paydate }" >
                    </div>
             </div>
             <div class="form-group">
				<label class="col-sm-2 control-label">用电开始日期:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control layer-date laydate-icon" name="stationElectric.startdate" id="startdate" value="${stationElectric.startdate }">
                    </div>
                    <label class="col-sm-2 control-label">用电结束日期:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control layer-date laydate-icon" name="stationElectric.enddate" id="enddate" value="${stationElectric.enddate }">
                    </div>
             </div>
		 </form>
		 </div>
		 <script type="text/javascript"
		src="<%=basePath%>plugins/jquery/jQuery.js"></script>
	<script
		src="<%=basePath%>plugins/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="<%=basePath%>plugins/layer2.4/laydate/laydate.js"></script>
   <script type="text/javascript">
   var startdate = {
		    elem: '#startdate',
		    format: 'YYYY/MM/DD',
		    max: '2099-06-16 23:59:59', //最大日期
		    istime: false,
		    istoday: false,
		    choose: function(datas){
		    	enddate.min = datas; //开始日选好后，重置结束日的最小日期
		         enddate.start = datas //将结束日的初始值设定为开始日
		    }
		};
		var enddate = {
		    elem: '#enddate',
		    format: 'YYYY/MM/DD',
		    max: '2099-06-16 23:59:59',
		    istime: false,
		    istoday: false,
		    choose: function(datas){
		    	startdate.max = datas; //结束日选好后，重置开始日的最大日期
		    }
		};
		var createdate = {
			    elem: '#createdate',
			    format: 'YYYY/MM/DD',
			    max: '2099-06-16 23:59:59',
			    istime: false,
			    istoday: true,
			    choose: function(datas){
			    }
			};
		var paydate = {
			    elem: '#paydate',
			    format: 'YYYY/MM/DD',
			    max: '2099-06-16',
			    istime: false,
			    istoday: false,
			    choose: function(datas){
			    }
			};
		laydate(startdate);
		laydate(enddate);
		laydate(enddate);
		laydate(createdate);
		laydate(paydate);
		$(function(){
			if($("#createdate").val()==""){
				$("#createdate").val(laydate.now())
			}
		});
   </script>
		 </body>
		 </html>