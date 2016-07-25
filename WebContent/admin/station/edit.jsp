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
		 <form method="get" class="form-horizontal" id="formStation">
             <div class="form-group">
				<label class="col-sm-1 control-label">站点编码:</label>
					<div class="col-sm-3">
						<input type="hidden" name="station.id" value="${station.id }"/>
						<input type="text" class="form-control" name="station.code" value="${station.code }">
                    </div>
             
				<label class="col-sm-1 control-label">站点名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.name" value="${station.name }">
                    </div>
            
				<label class="col-sm-1 control-label">运营商编码:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.operatorCode" value="${station.operatorCode }">
                    </div>
             </div>
			 <div class="form-group">
				<label class="col-sm-1 control-label">基站名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.baseName" value="${station.baseName }">
                    </div>
             
				<label class="col-sm-1 control-label">电表:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.electricity" value="${station.electricity }">
                    </div>
            
				<label class="col-sm-1 control-label">区域:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.area" value="${station.area }">
                    </div>
             </div>
             <div class="form-group">
				<label class="col-sm-1 control-label">付款类型:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.payType" value="${station.payType }">
                    </div>
             
				<label class="col-sm-1 control-label">上笔支付时间:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control layer-date laydate-icon" id="lastPay" name="station.lastPay" value="${station.lastPay }">
                    </div>
            
				<label class="col-sm-1 control-label">结算截止时间:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control layer-date laydate-icon" id="endPay" name="station.endPay" value="${station.endPay }">
                    </div>
             </div>
             <div class="form-group">
				<label class="col-sm-1 control-label">转账名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.transferName" value="${station.transferName }">
                    </div>
             
				<label class="col-sm-1 control-label">开户银行:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.transferBank" value="${station.transferBank }">
                    </div>
            
				<label class="col-sm-1 control-label">账号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.transferCount" value="${station.transferCount }">
                    </div>
             </div>
             <div class="form-group">
				<label class="col-sm-1 control-label">合同编号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.contractCode" value="${station.contractCode }">
                    </div>
             
				<label class="col-sm-1 control-label">合同开始时间:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control layer-date laydate-icon" id="contractStart" name="station.contractStart" value="${station.contractStart }">
                    </div>
            
				<label class="col-sm-1 control-label">合同结束时间:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control layer-date laydate-icon" id="contractEnd" name="station.contractEnd" value="${station.contractEnd }">
                    </div>
             </div>
             <div class="form-group">
				<label class="col-sm-1 control-label">合同甲方:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.contractA" value="${station.contractA }">
                    </div>
             
				<label class="col-sm-1 control-label">联系人:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control"  name="station.contactMan" value="${station.contactMan }">
                    </div>
            
				<label class="col-sm-1 control-label">联系电话:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.contactTelephone" value="${station.contactTelephone }">
                    </div>
             </div>
             <div class="form-group">
				<label class="col-sm-1 control-label">协议单价:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.price" value="${station.price }">
                    </div>
             
				<label class="col-sm-1 control-label">支付周期:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.payCircle" value="${station.payCircle }">
                    </div>
            
				<label class="col-sm-1 control-label">续签人员:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.renewMan" value="${station.renewMan }">
                    </div>
             </div>
             <div class="form-group">
				<label class="col-sm-1 control-label">取票人员:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.ticketMan" value="${station.ticketMan }">
                    </div>
             
				<label class="col-sm-1 control-label">收票时间:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control layer-date laydate-icon" id="ticketTime" name="station.ticketTime" value="${station.ticketTime }">
                    </div>
            
				<label class="col-sm-1 control-label">备注:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.remark" value="${station.remark }">
                    </div>
             </div>
               <div class="form-group">
				<label class="col-sm-1 control-label">是否移交:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.turnover" value="${station.turnover }">
                    </div>
             
				<label class="col-sm-1 control-label">是否更名:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.rename" value="${station.rename }">
                    </div>
            
				<label class="col-sm-1 control-label">长摊需确认:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="station.ctconfirm" value="${station.ctconfirm }">
                    </div>
             </div>
		</form>
	</div>
	<script type="text/javascript"
		src="<%=basePath%>plugins/jquery/jQuery.js"></script>
	<script
		src="<%=basePath%>plugins/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="<%=basePath%>plugins/layer2.4/laydate/laydate.js"></script>
	<script>
	var start = {
		    elem: '#contractStart',
		    format: 'YYYY/MM/DD',
		    max: '2099-06-16 23:59:59', //最大日期
		    istime: false,
		    istoday: false,
		    choose: function(datas){
		         end.min = datas; //开始日选好后，重置结束日的最小日期
		         end.start = datas //将结束日的初始值设定为开始日
		    }
		};
		var end = {
		    elem: '#contractEnd',
		    format: 'YYYY/MM/DD',
		    max: '2099-06-16 23:59:59',
		    istime: false,
		    istoday: false,
		    choose: function(datas){
		        start.max = datas; //结束日选好后，重置开始日的最大日期
		    }
		};
		var ticketTime = {
			    elem: '#ticketTime',
			    format: 'YYYY/MM/DD',
			    max: '2099-06-16 23:59:59',
			    istime: false,
			    istoday: false,
			    choose: function(datas){
			    }
			};
		var lastPay = {
			    elem: '#lastPay',
			    format: 'YYYY/MM/DD',
			    max: '2099-06-16 23:59:59',
			    istime: false,
			    istoday: false,
			    choose: function(datas){
			    }
			};
		var endPay = {
			    elem: '#endPay',
			    format: 'YYYY/MM/DD',
			    max: '2099-06-16 23:59:59',
			    istime: false,
			    istoday: false,
			    choose: function(datas){
			    }
			};
		laydate(start);
		laydate(end);
		laydate(ticketTime);
		laydate(lastPay);
		laydate(endPay);
	</script>
</body>
</html>