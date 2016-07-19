package com.demo.web.admin.controller;

import java.util.HashMap;
import java.util.Map;

import com.demo.web.admin.model.SysUser;
import com.demo.web.core.controller.BaseController;
import com.demo.web.core.util.PasswordUtil;
import com.jfinal.ext.anotation.RouteMapping;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;

@RouteMapping(controllerKey = "/admin")
public class AdminController extends BaseController{
	 /**
     * 描述：
     */
    protected Log log = Log.getLog(AdminController.class);
    
    public void index(){
    	render("/admin/index.jsp");
    }
    
    
}
