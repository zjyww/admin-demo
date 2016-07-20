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
    <title>首页</title>
	<link href="<%=basePath%>plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>plugins/bootstrap/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="<%=basePath%>plugins/bootstrap/css/animate.min.css" rel="stylesheet">
    <link href="<%=basePath%>plugins/bootstrap/css/style.min.css" rel="stylesheet">
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
            <%@include file="header.jsp" %>
            <div class="wrapper wrapper-content">
                <div class="container">
                    <div class="row">
                        <div class="col-md-2">
                            <div class="ibox float-e-margins">
                                <div class="ibox-title">
                                    <span class="label label-success pull-right">月</span>
                                    <h5>浏览量</h5>
                                </div>
                                <div class="ibox-content">
                                    <h1 class="no-margins">386,200</h1>
                                    <div class="stat-percent font-bold text-success">98% <i class="fa fa-bolt"></i>
                                    </div>
                                    <small>总计浏览量</small>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="ibox float-e-margins">
                                <div class="ibox-title">
                                    <span class="label label-info pull-right">年</span>
                                    <h5>订单</h5>
                                </div>
                                <div class="ibox-content">
                                    <h1 class="no-margins">80,800</h1>
                                    <div class="stat-percent font-bold text-info">20% <i class="fa fa-level-up"></i>
                                    </div>
                                    <small>新订单</small>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="ibox float-e-margins">
                                <div class="ibox-title">
                                    <span class="label label-primary pull-right">今天</span>
                                    <h5>访问人次</h5>
                                </div>
                                <div class="ibox-content">

                                    <div class="row">
                                        <div class="col-md-6">
                                            <h1 class="no-margins">&yen; 406,420</h1>
                                            <div class="font-bold text-navy">44% <i class="fa fa-level-up"></i> <small>增长较快</small>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <h1 class="no-margins">206,120</h1>
                                            <div class="font-bold text-navy">22% <i class="fa fa-level-up"></i> <small>增长较慢</small>
                                            </div>
                                        </div>
                                    </div>


                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="ibox float-e-margins">
                                <div class="ibox-title">
                                    <h5>月收入</h5>
                                    <div class="ibox-tools">
                                        <span class="label label-primary">2015.02 更新</span>
                                    </div>
                                </div>
                                <div class="ibox-content no-padding">
                                    <div class="flot-chart m-t-lg" style="height: 55px;">
                                        <div class="flot-chart-content" id="flot-chart1"></div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="ibox float-e-margins">
                                <div class="ibox-content">
                                    <div>
                                        <span class="pull-right text-right">
                                        <small>在过去的一个月销售的平均值：<strong>山东</strong></small>
                                            <br/>
                                            所有销售： 162,862
                                        </span>
                                        <h3 class="font-bold no-margins">
                                        半年收入利润率
                                    </h3>
                                        <small>市场部</small>
                                    </div>

                                    <div class="m-t-sm">

                                        <div class="row">
                                            <div class="col-md-8">
                                                <div>
                                                    <canvas id="lineChart" height="114"></canvas>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <ul class="stat-list m-t-lg">
                                                    <li>
                                                        <h2 class="no-margins">2,346</h2>
                                                        <small>总订单</small>
                                                        <div class="progress progress-mini">
                                                            <div class="progress-bar" style="width: 48%;"></div>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <h2 class="no-margins ">4,422</h2>
                                                        <small>最近一个月订单</small>
                                                        <div class="progress progress-mini">
                                                            <div class="progress-bar" style="width: 60%;"></div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="m-t-md">
                                        <small class="pull-right">
                                        <i class="fa fa-clock-o"> </i>
                                        2015.02.30更新
                                    </small>
                                        <small>
                                        <strong>说明：</strong> 本期销售额比上期增长了23%。
                                    </small>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="ibox float-e-margins">
                                <div class="ibox-title">
                                    <h5>用户行为统计</h5>
                                </div>
                                <div class="ibox-content">
                                    <div class="row">
                                        <div class="col-xs-4">
                                            <small class="stats-label">访问页面 / 浏览量</small>
                                            <h4>236 321.80</h4>
                                        </div>

                                        <div class="col-xs-4">
                                            <small class="stats-label">% 新访客</small>
                                            <h4>46.11%</h4>
                                        </div>
                                        <div class="col-xs-4">
                                            <small class="stats-label">最后一周</small>
                                            <h4>432.021</h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="ibox-content">
                                    <div class="row">
                                        <div class="col-xs-4">
                                            <small class="stats-label">访问页面 / 浏览量</small>
                                            <h4>643 321.10</h4>
                                        </div>

                                        <div class="col-xs-4">
                                            <small class="stats-label">% 新访客</small>
                                            <h4>92.43%</h4>
                                        </div>
                                        <div class="col-xs-4">
                                            <small class="stats-label">最后一周</small>
                                            <h4>564.554</h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="ibox-content">
                                    <div class="row">
                                        <div class="col-xs-4">
                                            <small class="stats-label">访问页面 / 浏览量</small>
                                            <h4>436 547.20</h4>
                                        </div>

                                        <div class="col-xs-4">
                                            <small class="stats-label">% 新访客</small>
                                            <h4>150.23%</h4>
                                        </div>
                                        <div class="col-xs-4">
                                            <small class="stats-label">最后一周</small>
                                            <h4>124.990</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

   	</div>
	
            </div>
   <!--          <div class="footer">

            </div> -->
        </div>
    </div>

    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/flot/jquery.flot.js"></script>
    <script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="js/plugins/chartJs/Chart.min.js"></script>
    <script src="js/plugins/peity/jquery.peity.min.js"></script>
    <script src="js/demo/peity-demo.min.js"></script>
    <script>
        $(document).ready(function(){var d1=[[1262304000000,6],[1264982400000,3057],[1267401600000,20434],[1270080000000,31982],[1272672000000,26602],[1275350400000,27826],[1277942400000,24302],[1280620800000,24237],[1283299200000,21004],[1285891200000,12144],[1288569600000,10577],[1291161600000,10295]];var d2=[[1262304000000,5],[1264982400000,200],[1267401600000,1605],[1270080000000,6129],[1272672000000,11643],[1275350400000,19055],[1277942400000,30062],[1280620800000,39197],[1283299200000,37000],[1285891200000,27000],[1288569600000,21000],[1291161600000,17000]];var data1=[{label:"数据1",data:d1,color:"#17a084"},{label:"数据2",data:d2,color:"#127e68"}];$.plot($("#flot-chart1"),data1,{xaxis:{tickDecimals:0},series:{lines:{show:true,fill:true,fillColor:{colors:[{opacity:1},{opacity:1}]},},points:{width:0.1,show:false},},grid:{show:false,borderWidth:0},legend:{show:false,}});var lineData={labels:["一月","二月","三月","四月","五月","六月","七月"],datasets:[{label:"示例数据",fillColor:"rgba(220,220,220,0.5)",strokeColor:"rgba(220,220,220,1)",pointColor:"rgba(220,220,220,1)",pointStrokeColor:"#fff",pointHighlightFill:"#fff",pointHighlightStroke:"rgba(220,220,220,1)",data:[65,59,40,51,36,25,40]},{label:"示例数据",fillColor:"rgba(26,179,148,0.5)",strokeColor:"rgba(26,179,148,0.7)",pointColor:"rgba(26,179,148,1)",pointStrokeColor:"#fff",pointHighlightFill:"#fff",pointHighlightStroke:"rgba(26,179,148,1)",data:[48,48,60,39,56,37,30]}]};var lineOptions={scaleShowGridLines:true,scaleGridLineColor:"rgba(0,0,0,.05)",scaleGridLineWidth:1,bezierCurve:true,bezierCurveTension:0.4,pointDot:true,pointDotRadius:4,pointDotStrokeWidth:1,pointHitDetectionRadius:20,datasetStroke:true,datasetStrokeWidth:2,datasetFill:true,responsive:true,};var ctx=document.getElementById("lineChart").getContext("2d");var myNewChart=new Chart(ctx).Line(lineData,lineOptions)});
    </script>
</body>

</html>