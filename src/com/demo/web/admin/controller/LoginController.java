package com.demo.web.admin.controller;

import java.util.HashMap;
import java.util.Map;

import com.demo.web.admin.model.SysUser;
import com.demo.web.core.controller.BaseController;
import com.demo.web.core.util.PasswordUtil;
import com.jfinal.ext.anotation.RouteMapping;

@RouteMapping(controllerKey = "/login")
public class LoginController extends BaseController{
	
	public void index() {
    	render("/login/login.jsp");
    }
    public void logout(){
    	this.getSession().setAttribute("loginUser", null);
    	redirect("/login/index");
    }
    
    public void checkLogin(){
    	SysUser sysUser = (SysUser) this.getSession().getAttribute("loginUser");
    	Map<String, Object> result = new HashMap<String, Object>();
    	if(sysUser != null){
    		result.put("success",true);
    		result.put("url", "admin/index");
    	}else{
    		String username = this.getPara("username");
    		String password = this.getPara("password");
    		String msg = null;
    		sysUser =  SysUser.DAO.findFirst("select * from t_sysuser u where u.user_name = ?",username);
    		if(sysUser != null){
    			try {
    				boolean isvalid = PasswordUtil.authenticate(password,
    						PasswordUtil.decode(sysUser.getStr("password")), 
    						PasswordUtil.decode(sysUser.getStr("salt")));
    				if(isvalid){
    					result.put("success",true);
    					result.put("url", "admin/index");
    					this.getSession().setAttribute("loginUser", sysUser);
    				}else{
    					msg = "用户名或密码错误";
    					result.put("success",false);
    					result.put("msg", msg);
    				}
    				
				} catch (Exception e) {
					msg = "用户名或密码错误";
					result.put("success",false);
					result.put("msg", msg);
				} 
    		}else{
    			msg = "用户名或密码错误";
    			result.put("success",false);
				result.put("msg", msg);
    		}
    	}
    	renderJson(result);
    }
    
}
