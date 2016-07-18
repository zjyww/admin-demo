package com.demo.web.admin.controller;

import java.util.HashMap;
import java.util.Map;

import com.demo.web.admin.model.SysUser;
import com.demo.web.core.controller.BaseController;
import com.jfinal.ext.anotation.RouteMapping;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Page;

@RouteMapping(controllerKey = "/admin/station")
public class StationController extends BaseController{
	 /**
     * 描述：
     */
    protected Log log = Log.getLog(AdminController.class);
    
    public void index(){
    	
    	render("/admin/station/stationMgr.jsp");
    }
    
    public void data(){
    	int start = getParaToInt("offset");
    	int pageSize = getParaToInt("limit");
    	int pageNum = (start+pageSize)/pageSize;
    	String sql = "select * from t_sysuser";
    	Page<SysUser> users = SysUser.DAO.paginate(pageNum, pageSize, sql, "user_id");
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("total", users.getTotalRow());
    	result.put("rows", users.getList());
    	renderJson(result);
    }
}
