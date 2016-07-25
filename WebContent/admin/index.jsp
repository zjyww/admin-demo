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
<link href="<%=basePath%>plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="<%=basePath%>plugins/bootstrap/css/font-awesome.min.css?v=4.4.0"
	rel="stylesheet">
<link href="<%=basePath%>plugins/bootstrap/css/animate.min.css"
	rel="stylesheet">
<link href="<%=basePath%>plugins/bootstrap/css/style.min.css"
	rel="stylesheet">
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
			<%@include file="header.jsp"%>
			<div class="wrapper wrapper-content">
				<div class="container">
					<div class="row">
						<div class="col-lg-9">
							<div class="ibox-title">
								<h5>报账统计</h5>
								<div class="ibox-tools"></div>
							</div>
							<div class="ibox float-e-margins">
								<div class="ibox-content">
									<div id="main" style="width: 100%; height: 500px;"></div>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="ibox float-e-margins">
								<div class="ibox-title">
									<h5>最新消息</h5>
									<div class="ibox-tools">
										<span class="label label-warning-light">10条未读</span>
									</div>
								</div>
								<div class="ibox-content">
									<div class="feed-activity-list">
										<div class="feed-element">
											<div>
												<small class="pull-right label label-warning">未读</small>
												<p>九部令人拍案叫绝的惊悚悬疑剧情佳作】如果你喜欢《迷雾》《致命ID》《电锯惊魂》《孤儿》《恐怖游轮》这些好片，那么接下来
												</p>
												<small class="text-muted "><i class="fa fa-clock-o"></i>2014.11.8
													12:22</small> <a class="pull-right btn btn-xs btn-white"><i
													class="fa fa-eye"></i>朕已阅 </a>
											</div>
										</div>
										<div class="feed-element">
											<div>
												<small class="pull-right label label-primary">已读</small>
												<p>九部令人拍案叫绝的惊悚悬疑剧情佳作】如果你喜欢《迷雾》《致命ID》《电锯惊魂》《孤儿》《恐怖游轮》这些好片，那么接下来
												</p>
												<small class="text-muted "><i class="fa fa-clock-o"></i>2014.11.8
													12:22</small>
											</div>
										</div>
										<div class="feed-element">
											<div>
												<small class="pull-right label label-primary">已读</small>
												<p>九部令人拍案叫绝的惊悚悬疑剧情佳作】如果你喜欢《迷雾》《致命ID》《电锯惊魂》《孤儿》《恐怖游轮》这些好片，那么接下来
												</p>
												<small class="text-muted "><i class="fa fa-clock-o"></i>2014.11.8
													12:22</small>
											</div>
										</div>
										<div class="feed-element">
											<div>
												<small class="pull-right label label-warning">已读</small>
												<p>九部令人拍案叫绝的惊悚悬疑剧情佳作】如果你喜欢《迷雾》《致命ID》《电锯惊魂》《孤儿》《恐怖游轮》这些好片，那么接下来
												</p>
												<small class="text-muted "><i class="fa fa-clock-o"></i>2014.11.8
													12:22</small> <a class="pull-right btn btn-xs btn-white"><i
													class="fa fa-eye"></i>朕已阅 </a>
											</div>
										</div>
										<div class="feed-element">
											<div>
												<small class="pull-right label label-warning">已读</small>
												<p>九部令人拍案叫绝的惊悚悬疑剧情佳作】如果你喜欢《迷雾》《致命ID》《电锯惊魂》《孤儿》《恐怖游轮》这些好片，那么接下来
												</p>
												<small class="text-muted "><i class="fa fa-clock-o"></i>2014.11.8
													12:22</small> <a class="pull-right btn btn-xs btn-white"><i
													class="fa fa-eye"></i>朕已阅 </a>
											</div>
										</div>
										<div class="feed-element">
											<div>
												<small class="pull-right label label-warning">已读</small>
												<p>九部令人拍案叫绝的惊悚悬疑剧情佳作】如果你喜欢《迷雾》《致命ID》《电锯惊魂》《孤儿》《恐怖游轮》这些好片，那么接下来
												</p>
												<small class="text-muted "><i class="fa fa-clock-o"></i>2014.11.8
													12:22</small>
											</div>
										</div>

									</div>
									<button class="btn btn-primary btn-block m-t">
										<i class="fa fa-arrow-down"></i> 加载更多
									</button>
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

	<script src="<%=basePath%>plugins/jquery/jQuery.js"></script>
	<script
		src="<%=basePath%>plugins/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="<%=basePath%>plugins/echarts/echarts.common.min.js"></script>
	<script>
		$(function(){
			$.get("<%=basePath%>admin/echartData").done(function (data) {
				if(data.xAxis){
					// 基于准备好的dom，初始化echarts实例
			        var myChart = echarts.init(document.getElementById('main'));

			        // 指定图表的配置项和数据
			        var option = {
			            title: {
			                text: ''
			            },
			            tooltip : {
			                trigger: 'axis',
			                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			                }
			            },
			            toolbox: {
			                show : true,
			                feature : {
			                    magicType : {show: true, type: ['line', 'bar']},
			                    restore : {show: true},
			                    saveAsImage : {show: true}
			                }
			            },
			            legend: {
			            	type : 'category',
			                data:['报账笔数',"总用电量","总金额"]
			            },
			            xAxis : [
			                     {
			                         type : 'category',
			                         data : data.xAxis
			                     }
			                 ],
			                 yAxis : [
			                          {
			                              type : 'value'
			                          }
			                      ],
			            series: [{
			                name: '报账笔数',
			                type: 'bar',
			                data: data.bzData,
			                markPoint : {
			                    data : [
			                        {type : 'max', name: '最大值'},
			                        {type : 'min', name: '最小值'}
			                    ]
			                },
			                markLine : {
			                    data : [
			                        {type : 'average', name: '平均值'}
			                    ]
			                }
			            },{
			                name: '总用电量',
			                type: 'bar',
			                data: data.powerData,
			                markPoint : {
			                    data : [
			                        {type : 'max', name: '最大值'},
			                        {type : 'min', name: '最小值'}
			                    ]
			                },
			                markLine : {
			                    data : [
			                        {type : 'average', name: '平均值'}
			                    ]
			                }
			            },{
			                name: '总金额',
			                type: 'bar',
			                data: data.paymentData,
			                markPoint : {
			                    data : [
			                        {type : 'max', name: '最大值'},
			                        {type : 'min', name: '最小值'}
			                    ]
			                },
			                markLine : {
			                    data : [
			                        {type : 'average', name: '平均值'}
			                    ]
			                }
			            }]
			        };

			        // 使用刚指定的配置项和数据显示图表。
			        myChart.setOption(option);
				}
			});
	        
		});
    </script>
</body>

</html>