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
			height:28px;
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
			padding-top:5px;
		}
	</style>
	</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		 <form method="get" class="form-horizontal">
             <div class="form-group">
				<label class="col-sm-1 control-label">站点编码:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             
				<label class="col-sm-1 control-label">站点名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
            
				<label class="col-sm-1 control-label">运营商编码:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             </div>
			 <div class="form-group">
				<label class="col-sm-1 control-label">基站名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             
				<label class="col-sm-1 control-label">电表:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
            
				<label class="col-sm-1 control-label">区域:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             </div>
             <div class="form-group">
				<label class="col-sm-1 control-label">付款类型:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             
				<label class="col-sm-1 control-label">上笔支付之间:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control layer-date laydate-icon" id="lastPay">
                    </div>
            
				<label class="col-sm-1 control-label">结算截止时间:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control layer-date laydate-icon" id="endPay">
                    </div>
             </div>
             <div class="form-group">
				<label class="col-sm-1 control-label">转账名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             
				<label class="col-sm-1 control-label">开户银行:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
            
				<label class="col-sm-1 control-label">账号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             </div>
             <div class="form-group">
				<label class="col-sm-1 control-label">合同编号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             
				<label class="col-sm-1 control-label">合同开始时间:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control layer-date laydate-icon" id="start">
                    </div>
            
				<label class="col-sm-1 control-label">合同结束时间:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control layer-date laydate-icon" id="end">
                    </div>
             </div>
             <div class="form-group">
				<label class="col-sm-1 control-label">合同甲方:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             
				<label class="col-sm-1 control-label">联系人:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
            
				<label class="col-sm-1 control-label">联系电话:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             </div>
             <div class="form-group">
				<label class="col-sm-1 control-label">协议单价:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             
				<label class="col-sm-1 control-label">支付周期:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
            
				<label class="col-sm-1 control-label">续签人员:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             </div>
             <div class="form-group">
				<label class="col-sm-1 control-label">取票人员:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             
				<label class="col-sm-1 control-label">收票时间:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control layer-date laydate-icon" id="ticketTime">
                    </div>
            
				<label class="col-sm-1 control-label">备注:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             </div>
               <div class="form-group">
				<label class="col-sm-1 control-label">是否移交:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             
				<label class="col-sm-1 control-label">是否移交:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
            
				<label class="col-sm-1 control-label">长摊需确认:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control">
                    </div>
             </div>
		</form>
	<div>
	<script type="text/javascript"
		src="<%=basePath%>plugins/jquery/jQuery.js"></script>
	<script
		src="<%=basePath%>plugins/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="<%=basePath%>plugins/layer2.4/laydate/laydate.js"></script>
	<script>
		
	</script>
</body>
</html>