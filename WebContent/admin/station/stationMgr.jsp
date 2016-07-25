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
<title>站点信息管理</title>
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
								<h5>站点信息管理</h5>
								<div class="ibox-tools"></div>
							</div>
							<div class="ibox-content">
								<div class="fixed-table-toolbar">
									<div class="bars pull-left">
										<div class="btn-group hidden-xs" id="exampleToolbar"
											role="group">
											<button type="button" class="btn btn-outline btn-default" onclick="add()">
												新增 <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
											</button>
											<button type="button" class="btn btn-outline btn-default"
												onclick="importExcel()">
												导入 <i class="fa fa-file-excel-o" aria-hidden="true"></i>
											</button>
											<button type="button" class="btn btn-outline btn-default" onclick="deleteStation()">
												删除 <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
											</button>
										</div>
									</div>
								</div>
								<table id="table"
									data-toolbar="#exampleToolbar" data-striped="true"
									data-side-pagination="server" data-toggle="table"
									data-url="<%=basePath%>admin/station/data"
									data-search-on-enter-key="true" data-height="500"
									data-pagination="true" data-icon-size="outline"
									data-show-refresh="true" data-show-columns="true"
									data-search="true" data-id-field>
									<thead>
										<tr>
											<th data-checkbox="true"></th>
											<th data-field="id" data-align="center" data-switchable="false" data-visible="false"></th>
											<th data-align="center" data-field="code" data-sortable="true"  data-searchable="true">站点编码</th>
											<th data-align="center" data-field="name" data-switchable="false" data-searchable="true" data-sortable="false" >站点名称</th>
											<th data-align="center" data-field="area">区域</th>
											<th data-align="center" data-field="operatorcode">运营商编码</th>
											<th data-align="center" data-field="contractstart" data-sortable="true">合同开始时间</th>
											<th data-align="center" data-field="contractend" data-sortable="true">合同结束时间</th>
											<th data-align="center" data-field="lastpay" data-sortable="true">上笔支付时间</th>
											<th data-align="center" data-field="endpay" data-sortable="true">结算截止时间</th>
											<th data-width="130" data-align="center" data-switchable="false" data-formatter="operFormatter">操作</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="file" id="picker" style="display: none">
			<div id='showImg' class='layui-layer-wrap' style='display: none;'><img src=''></div>
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

	<script src="<%=basePath%>plugins/webuploader/webuploader.nolog.js"></script>
	<script type="text/javascript">
    var lindex = null;
    var uploader = WebUploader.create({
	    swf: "<%=basePath%>plugins/webuploader/Uploader.swf",
	    accept:{mimeTypes:"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	    	,title:"选择Excel 文件",extensions:"xls,xlsx"},
	   	auto:true,
	   	pick: '#picker',
	   	server:"<%=basePath%>admin/station/importExcel"
	});
    uploader.on( "uploadSuccess",function(file,res){
   		layer.close(lindex);
		layer.msg(res.msg);
		$('#table').bootstrapTable('refresh');
		uploader.reset();
   	});
    uploader.on( "startUpload",function(file,res){
    	lindex = layer.msg('导入中...', {shade:0.2,shadeClose:false,icon: 16,time:0});
   	});
    uploader.on( "uploadError",function(file,res){
    	layer.close(lindex);
		layer.msg(res.msg);
		uploader.reset();
   	});
    	function importExcel(){
    		$(".webuploader-element-invisible").trigger("click");
    	}
    	
    	function add(){
    		layer.open({
				type: 2,
				title: '新增站点信息',
				shadeClose: false,
				shade: 0.8,
				btn:["保存","取消"],
				yes:function(index,layro){
					 var form = layer.getChildFrame('form',index);
					 var formData = $(form).serialize();
					 $.ajax({
			    			type: "POST",
			    			url: "<%=basePath%>admin/station/save",     
			    			dataType:"json",
			    			data: formData,
			    			success: function(res){
			    				if(res.success == true){
			    					layer.close(index);
			    					$('#table').bootstrapTable('refresh');
			    				}
			    				layer.msg(res.msg);
			    				
			    			}  
			    		}); 
				},
				btn2:function(index,layro){
					layer.close(index);
				},
				area: ['1100px', '85%'],
				content: '<%=basePath%>admin/station/add' 
			});
    	}
    	function update(id){
    		layer.open({
				type: 2,
				title: '编辑站点信息',
				shadeClose: false,
				shade: 0.8,
				btn:["保存","取消"],
				yes:function(index,layro){
					 var form = layer.getChildFrame('form',index);
					 var formData = $(form).serialize();
					 $.ajax({
			    			type: "POST",
			    			url: "<%=basePath%>admin/station/save",     
			    			dataType:"json",
			    			data: formData,
			    			success: function(res){
			    				if(res.success == true){
			    					layer.close(index);
			    					$('#table').bootstrapTable('refresh');
			    				}
			    				layer.msg(res.msg);
			    				
			    			}  
			    		}); 
				},
				btn2:function(index,layro){
					layer.close(index);
				},
				area: ['1100px', '85%'],
				content: '<%=basePath%>admin/station/edit?&id='+id 
			});
    	}
    	function deleteStation(){
    		var ckDatas = $('#table').bootstrapTable('getAllSelections');
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
	    					$('#table').bootstrapTable('refresh');
	    				}
	    				layer.msg(res.msg);
	    			}  
	    		}); 
				
    		}
    	}
    	function contract(id){
    		layer.open({
				type: 2,
				title: '查看合同信息',
				shadeClose: false,
				shade: 0.8,
				btn:["添加合同","关闭"],
				yes:function(index,layro){
					 var iframeWin = window[layro.find('iframe')[0]['name']]; 
					 iframeWin.addImg();
				},
				btn2:function(index,layro){
					layer.close(index);
				},
				area: ['630px', '65%'],
				content: '<%=basePath%>admin/station/contract?&id='+id+'&type=1'
			});
    	}
    	function certificate(id){
    		layer.open({
				type: 2,
				title: '查看凭证信息',
				shadeClose: false,
				shade: 0.8,
				btn:["添加凭证","关闭"],
				yes:function(index,layro){
					 var iframeWin = window[layro.find('iframe')[0]['name']]; 
					 iframeWin.addImg();
				},
				btn2:function(index,layro){
					layer.close(index);
				},
				area: ['630px', '65%'],
				content: '<%=basePath%>admin/station/contract?&id='+id+'&type=2'
			});
    	}
    	function operFormatter(value,row,index){
    		return "<div class='btn-group hidden-xs' id='exampleToolbar'"
			+"role='group'>"
			+"<button type='button' class='btn btn-outline btn-xs btn-default' onclick='update("+row.id+")'>"
			+"修改"
			+"</button>"
			+"<button type='button' class='btn btn-xs btn-outline btn-default'"
			+"onclick='contract("+row.id+")'>"
			+"合同"
			+"</button>"
			+"<button type='button' class='btn btn-outline btn-xs btn-default' onclick='certificate("+row.id+")'>"
			+"凭证 "
			+"</button>"
			+"</div>";
    	}
    	function showImg(src,width,height){
    		$("#showImg img").attr("src",src);
    		console.log($(window).width())
    		console.log($(window).height())
    		console.log(width)
    		if(width > $(window).width()-100){
    			console.log("ss")
    			width = "95%";
    		} 
    		if(height > $(window).height()-100){
    			height = "85%";
    		}
    		layer.open({
    			  type: 1,
    			  title: false,
    			  closeBtn: 1,
    			  area: [width,height],
    			  skin: 'layui-layer-nobg', //没有背景色
    			  shadeClose: true,
    			  content: $("#showImg"),
    			  zIndex:20000000
    			  ,success: function(layero){
    			        layer.setTop(layero); //重点2
    			    }
    			});
    	}
    </script>
</body>
</html>