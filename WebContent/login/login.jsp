<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%    
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>后台登录</title>
	<link href="<%=basePath%>plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>plugins/bootstrap/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="<%=basePath%>plugins/bootstrap/css/animate.min.css" rel="stylesheet">
    <link href="<%=basePath%>plugins/bootstrap/css/style.min.css" rel="stylesheet">
    <link href="<%=basePath%>plugins/bootstrap/css/login.min.css" rel="stylesheet">
    <link href="<%=basePath%>plugins/layer2.4/layer/skin/layer.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>plugins/layer2.4/layer/layer.js"></script>
    <script type="text/javascript">
    	$(function(){
    	});  
    	function submitLogin(){
    		var username = $("#username").val();
    		var password = $("#password").val();
    		if(username == ""){
    			layer.msg("用户名不能为空");
    			return;
    		}
    		if(password == ""){
    			layer.msg("密码不能为空");
    			return;
    		}
    		$.ajax({
    			type: "POST",
    			url: "<%=basePath%>login/checkLogin",     
    			data: {username:username,
    				password:password     
    			},
    			success: function(res){
    				if(res.success){
    					location.href = "<%=basePath%>"+res.url;
    				}else{
    					layer.msg(res.msg);
    				}
    			}  
    		}); 
    	}
    </script>
</head>
<body class="signin">
    <div class="signinpanel">
        <div class="row">
			<div class="middle-box">
                <form method="post" id="loginForm" action="">
                    <h2 class="no-margins">登录：</h2>
                    <input type="text" class="form-control uname" maxlength="10" name="username" id="username" 
                    onkeypress="if(event.keyCode == 13){submitLogin();}"  placeholder="用户名" />
                    <input type="password" class="form-control pword m-b" name="password" id="password"
                    onkeypress="if(event.keyCode == 13){submitLogin();}"  maxlength="8" placeholder="密码" />
                    <a onclick="submitLogin();" class="btn btn-success btn-block">登录</a>
                </form>
			</div>
        </div>
        <div class="signup-footer">
        </div>
    </div>
</body>
</html>