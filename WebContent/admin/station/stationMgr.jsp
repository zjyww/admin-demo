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
    <title>基站信息管理</title>
	<link href="<%=basePath%>plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>plugins/bootstrap/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="<%=basePath%>plugins/bootstrap/css/animate.min.css" rel="stylesheet">
    <link href="<%=basePath%>plugins/bootstrap/css/style.min.css" rel="stylesheet">
    <link href="<%=basePath%>plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<style type="text/css">
		.border-bottom .navbar{
			border-bottom:3px solid #536498;
		}
		.top-navigation .navbar-brand{
			background-color:#536498;
		}
		.top-navigation .wrapper.wrapper-content{
			padding-top:70px;
		}
	</style>
	
	
</head>

<body class="gray-bg top-navigation">

    <div id="wrapper">
        <div id="page-wrapper" class="gray-bg">
            <%@include file="../header.jsp" %>
            <div class="wrapper wrapper-content">
            	<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>基站信息管理 </h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="fixed-table-toolbar"><div class="bars pull-left"><div class="btn-group hidden-xs" id="exampleToolbar" role="group">
                                    <button type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                    </button>
                                    <button type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-heart" aria-hidden="true"></i>
                                    </button>
                                    <button type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
                                    </button>
                                </div>
                                </div>
                                </div>
                        <table id="exampleTablePagination" 
                        	data-striped="true" 
                        	data-side-pagination="server"
                        	data-toggle="table" 
                        	data-url="<%=basePath%>admin/station/data" 
                        	data-search-on-enter-key="true" 
                        	data-height="500" 
                        	data-pagination="true" 
                        	data-icon-size="outline" 
                        	data-show-refresh="true"
                        	data-search="true"
                        		data-show-columns="true">
                                    <thead>
                                        <tr>
                                            <th data-checkbox="true"></th>
                                            <th data-align="center" data-field="user_id">ID</th>
                                            <th data-align="center" data-field="user_name">用户名</th>
                                            <th data-align="center" data-field="password">价格</th>
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
    <script type="text/javascript" src="<%=basePath%>plugins/jquery/jquery.min.js"></script>
	<script src="<%=basePath%>plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="<%=basePath%>plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
</body>
</html>