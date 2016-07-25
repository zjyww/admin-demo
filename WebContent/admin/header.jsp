<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>

<div class="row border-bottom white-bg">
                <nav class="navbar navbar-fixed-top" role="navigation">
                    <div class="navbar-header">
                        <button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed" type="button">
                            <i class="fa fa-reorder"></i>
                        </button>
                        <span class="navbar-brand">报账管理系统</span>
                    </div>
                    <div class="navbar-collapse collapse" id="navbar">
                        <ul class="nav navbar-nav">
                            <li class="nav navbar-top-links">
                                <a aria-expanded="false" role="button" href="<%=basePath%>admin/index">主页</a>
                            </li>
                           <li class="nav navbar-top-links">
                                <a href="<%=basePath%>admin/station/index">站点信息管理</a>
                            </li>
                            <li class="nav J_menuTab navbar-top-links">
                                <a href="<%=basePath%>admin/stationElectric/index">电量报账管理</a>
                            </li>

                        </ul>
                        <ul class="nav navbar-top-links navbar-right">
                            <li>
                                <a href="<%=basePath%>login/logout">
                                    <i class="fa fa-sign-out"></i> 退出
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>