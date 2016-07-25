<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%    
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>电量报账管理</title>
<link href="<%=basePath%>plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="<%=basePath%>plugins/bootstrap/css/font-awesome.min.css?v=4.4.0"
	rel="stylesheet">
<link href="<%=basePath%>plugins/bootstrap/css/animate.min.css"
	rel="stylesheet">
<link href="<%=basePath%>plugins/bootstrap/css/style.min.css"
	rel="stylesheet">
	<link
	href="<%=basePath%>plugins/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<link href="<%=basePath%>plugins/layer2.4/layer/skin/layer.css"
	rel="stylesheet" />
<style type="text/css">
.border-bottom .navbar {
	border-bottom: 3px solid #536498;
}

.top-navigation .navbar-brand {
	background-color: #536498;
}

.top-navigation .wrapper.wrapper-content {
	padding-top: 70px;
}
dt,dd{
	    padding: 2px 0 2px 0;
}
dl{
	margin-bottom:5px;
}
</style>


</head>

<body class="gray-bg top-navigation">

	<div id="wrapper">
		<div id="page-wrapper" class="gray-bg">
			<%@include file="../header.jsp"%>
			<div class="wrapper wrapper-content">
				<div class="row">
					<div class="col-sm-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>电量报账管理</h5>
								<div class="ibox-tools"></div>
								<input type="hidden" value="" id="stationid"/>
							</div>
							<div class="ibox-content" >
								<div class="row">
									<div class="col-lg-3">
									</div>
									<div class="col-lg-6">
										<div class="input-group">
                                        <input type="text" class="form-control" id="station" placeholder="输入站点编码或者站点名称进行搜索 ,关键字可空格间隔">
                                        <div class="input-group-btn">
                                            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                               	<i class="fa fa-search"></i>搜索
                                            </button>
                                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                            </ul>
                                        </div>
                                        <!-- /btn-group -->
                                    </div>
									</div>
									<div class="col-lg-3">
									</div> 
								</div>
								<div class="row" style="padding-top:10px;" id="stationInfo">
									
								</div>
								<div class="row" id="electricRow">
										<div class="btn-group hidden-xs" id="electricToolbar"
											role="group">
											<button type="button" class="btn btn-outline btn-default" onclick="add()">
												新增 <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
											</button>
											<!-- <button type="button" class="btn btn-outline btn-default"
												onclick="importExcel()">
												导入 <i class="fa fa-file-excel-o" aria-hidden="true"></i>
											</button> -->
											<button type="button" class="btn btn-outline btn-default" onclick="deleteStationElectric()">
												删除 <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</button>
											<h3 id="title" style="margin-left:80px;display:inline;"></h3>
								</div>
								<table id="electricTable"
									data-toolbar="#electricToolbar" data-striped="true"
									data-side-pagination="server" data-toggle="table"
									data-url="<%=basePath%>admin/stationElectric/data"
									data-search-on-enter-key="true" data-height="400"
									data-pagination="true" data-icon-size="outline"
									data-show-refresh="false" data-show-columns="false"
									data-search="false"  data-query-params="queryParams">
									<thead>
										<tr>
											<th data-checkbox="true"></th>
											<th data-field="id" data-align="center" data-switchable="false" data-visible="false"></th>
											<th data-align="center" data-field="electricno" data-sortable="true"  data-searchable="true">电表</th>
											<th data-align="center" data-field="power" data-switchable="false" data-searchable="true" data-sortable="true" >用电量</th>
											<th data-align="center" data-field="price">单价</th>
											<th data-align="center" data-field="payment">总金额</th>
											<th data-align="center" data-field="paydate">到账日期</th>
											<th data-align="center" data-field="createdate">报账日期</th>
											<th data-align="center" data-field="startdate">用电开始日期</th>
											<th data-align="center" data-field="enddate">用电结束日期</th>
											<th data-align="center" data-switchable="false" data-formatter="operFormatter">操作</th>
										</tr>
									</thead>
								</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=basePath%>plugins/jquery/jQuery.js"></script>
	<script
		src="<%=basePath%>plugins/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
	<script
		src="<%=basePath%>plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script
		src="<%=basePath%>plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>plugins/layer2.4/layer/layer.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>plugins/suggest/bootstrap-suggest.min.js"></script>
	<script type="text/javascript">
	var bsSuggest = $("#station").bsSuggest({
	      	url: "<%=basePath%>admin/stationElectric/search?keyword=",
	      	idField: "code",                    //每组数据的哪个字段作为 data-id，优先级高于 indexId 设置（推荐）
	        keyField: "name",                   //每组数据的哪个字段作为输入框内容，优先级高于 indexKey 设置（推荐）
	        effectiveFields: ["code","name"],            //有效显示于列表中的字段，非有效字段都会过滤，默认全部，对自定义getData方法无效
	        effectiveFieldsAlias: {code:"站点编码",name:"站点名称"},       //有效字段的别名对象，用于 header 的显示
	      	multiWord:false,
	      	showBtn:true,allowNoKeyword:false,
	      	getDataMethod: 'url',
	      	autoSelect: false
	      	
	    });
		bsSuggest.on("onSetSelectValue",function(e,data){
			$("#station").val(data.id+data.key);
			var station = bsSuggest.data().bsSuggest.options.data.value[data.index];
			for(var i in station ){
				if(station[i] == null) station[i]="-";
			}
			$("#stationid").val(station.id);
			$('#electricTable').bootstrapTable('refresh');
			$("#stationInfo").html("<div class='col-sm-4'>"
					+"<dl class='dl-horizontal'>"
					+"<dt>站点编码：</dt>"
					+"<dd>"+station.code+"</dd>"
					+"<dt>站点编码名称：</dt>"
					+"<dd>"+station.name+"</dd>"
					+"<dt>运营商编码：</dt>"
					+"<dd>"+station.operatorcode+"</dd>"
					+"<dt>基站名称：</dt>"
					+"<dd>"+station.basename+"</dd>"
					+"<dt>电表：</dt>"
					+"<dd>"+station.electricity+"</dd>"
					+"<dt>区域：</dt>"
					+"<dd>"+station.area+"</dd>"
					+"<dt>付款类型：</dt>"
					+"<dd>"+station.paytype+"</dd>"
					+"<dt>联系人：</dt>"
					+"<dd>"+station.contactman+"</dd>"
					+"<dt>联系电话：</dt>"
					+"<dd>"+station.contacttelephone+"</dd>"
					+"</dl>"
					+"</div>"
					+"<div class='col-sm-4'>"
					+"<dl class='dl-horizontal'>"
					+"<dt>上笔支付时间：</dt>"
					+"<dd>"+station.lastpay+"</dd>"
					+"<dt>结算截止时间：</dt>"
					+"<dd>"+station.endpay+"</dd>"
					+"<dt>转账名称：</dt>"
					+"<dd>"+station.transfername+"</dd>"
					+"<dt>开户银行：</dt>"
					+"<dd>"+station.transferbank+"</dd>"
					+"<dt>账号：</dt>"
					+"<dd>"+station.transfercount+"</dd>"
					+"<dt>合同编号：</dt>"
					+"<dd>"+station.contractcode+"</dd>"
					+"<dt>合同开始日期：</dt>"
					+"<dd>"+station.contractstart+"</dd>"
					+"<dt>合同结束日期：</dt>"
					+"<dd>"+station.contractend+"</dd>"
					+"<dt>合同甲方：</dt>"
					+"<dd>"+station.contracta+"</dd>"
					+"</dl>"
					+"</div>"
					+"<div class='col-sm-4'>"
					+"<dl class='dl-horizontal'>"
					+"<dt>协议单价：</dt>"
					+"<dd>"+station.price+"</dd>"
					+"<dt>支付周期：</dt>"
					+"<dd>"+station.paycircle+"</dd>"
					+"<dt>取票人员：</dt>"
					+"<dd>"+station.ticketman+"</dd>"
					+"<dt>取票时间：</dt>"
					+"<dd>"+station.tickettime+"</dd>"
					+"<dt>是否移交：</dt>"
					+"<dd>"+station.turnover+"</dd>"
					+"<dt>是否更名：</dt>"
					+"<dd>"+station.rename+"</dd>"
					+"<dt>长摊需确认：</dt>"
					+"<dd>"+station.ctconfirm+"</dd>"
					+"<dt>续签人员：</dt>"
					+"<dd>"+station.renewman+"</dd>"
					+"<dt>备注：</dt>"
					+"<dd>"+station.remark+"</dd>"
					+"</dl>"
					+"</div>");
		});
		function add(){
			var stationid = $("#stationid").val();
			if("" == stationid){
				layer.msg("请先选择一个站点后，再进行报账。");
				return;
			}
    		layer.open({
				type: 2,
				title: '新增报账',
				shadeClose: false,
				shade: 0.8,
				btn:["保存","取消"],
				yes:function(index,layro){
					 var form = layer.getChildFrame('form',index);
					 var formData = $(form).serialize();
					 $.ajax({
			    			type: "POST",
			    			url: "<%=basePath%>admin/stationElectric/save",     
			    			dataType:"json",
			    			data: formData,
			    			success: function(res){
			    				if(res.success == true){
			    					layer.close(index);
			    					$('#electricTable').bootstrapTable('refresh');
			    				}
			    				layer.msg(res.msg);
			    			}  
			    		}); 
				},
				btn2:function(index,layro){
					layer.close(index);
				},
				area: ['800px', '75%'],
				content: '<%=basePath%>admin/stationElectric/add?stationid='+stationid 
			});
		}
		function update(id){
			var stationid = $("#stationid").val();
			if("" == stationid){
				layer.msg("请先选择一个站点后，再进行报账。");
				return;
			}
    		layer.open({
				type: 2,
				title: '新增报账',
				shadeClose: false,
				shade: 0.8,
				btn:["保存","取消"],
				yes:function(index,layro){
					 var form = layer.getChildFrame('form',index);
					 var formData = $(form).serialize();
					 $.ajax({
			    			type: "POST",
			    			url: "<%=basePath%>admin/stationElectric/save",     
			    			dataType:"json",
			    			data: formData,
			    			success: function(res){
			    				if(res.success == true){
			    					layer.close(index);
			    					$('#electricTable').bootstrapTable('refresh');
			    				}
			    				layer.msg(res.msg);
			    			}  
			    		}); 
				},
				btn2:function(index,layro){
					layer.close(index);
				},
				area: ['800px', '75%'],
				content: '<%=basePath%>admin/stationElectric/edit?stationid='+stationid+'&id='+id
			});
		}
		$(function(){
			$("#electricTable").on("load-success.bs.table",function(e,data){
				console.log(data)
				if(data.statics){
					$("#title").html("当前站点共报账"+data.statics.total+"笔"
							+",总用电量："+data.statics.power+"，总金额："+data.statics.payment+"。");
				}
			});
		});
		function queryParams(params){
			params.stationid = $("#stationid").val();
					return params
		}
		
		function operFormatter(value,row,index){
    		return "<div class='btn-group hidden-xs' id='exampleToolbar'"
			+"role='group'>"
			+"<button type='button' class='btn btn-outline btn-sm btn-default' onclick='update("+row.id+")'>"
			+"修改"
			+"</button>"
			+"</div>";
    	}
		
		function deleteStationElectric(){
    		var ckDatas = $('#electricTable').bootstrapTable('getAllSelections');
    		if(ckDatas.length == 0){
    			layer.msg("请至少选择一条记录");
    		}else{
    			var selectColNames = "";
				for ( var i = 0; i < ckDatas.length; i++) {
					if(i>0){
						selectColNames+=",";
					}
					selectColNames+=ckDatas[i].id;
				}
				$.ajax({
	    			type: "POST",
	    			url: "<%=basePath%>admin/stationElectric/delete",     
	    			dataType:"json",
	    			data: {
	    				ids:selectColNames  
	    			},
	    			success: function(res){
	    				if(res.success == true){
	    					$('#electricTable').bootstrapTable('refresh');
	    				}
	    				layer.msg(res.msg);
	    			}  
	    		}); 
				
    		}
    	}
	</script>
</body>
</html>