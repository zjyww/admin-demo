package com.demo.web.admin.controller;

import java.util.HashMap;
import java.util.Map;

import com.demo.web.admin.model.SysMsg;
import com.demo.web.core.controller.BaseController;
import com.jfinal.ext.anotation.RouteMapping;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

@RouteMapping(controllerKey = "/admin/sysMsg")
public class SysMsgController extends BaseController{
	
	  public void getMsg()throws Exception{
	    	int start = getParaToInt("offset");
	    	int pageSize = getParaToInt("limit");
	    	String search = new String(getPara("search","").getBytes("ISO8859-1"),"UTF-8");
	    	int pageNum = (start+pageSize)/pageSize;
	    	String sql = "select * ";
	    	StringBuilder sqlWhere = new StringBuilder(" where ");
	    	sqlWhere.append("content like '%").append(search).append("%' ");
	    	
	    	Page<SysMsg> msgs = SysMsg.DAO.paginate(pageNum, pageSize, sql, "from t_sysmsg "+sqlWhere +"order by state,createtime desc");
	    	Map<String, Object> result = new HashMap<String, Object>();
	    	SysMsg msg = SysMsg.DAO.findFirst("select count(*) as total from t_sysmsg where state = '1'");
	    	result.put("total", msgs.getTotalRow());
	    	result.put("rows", msgs.getList());
	    	result.put("unread", msg.get("total"));
	    	renderJson(result);
	    }
	    
	    public void index(){
	    	render("/admin/msg/index.jsp");
	    }
	    
	    public void delete(){
	    	try {
				String[] ids= this.getPara("ids").split(",");
				for (int i = 0;i<ids.length;i++) {
					Db.deleteById("t_sysmsg", "id", Integer.valueOf(ids[i]));
				}
				renderJsonSuccess("删除成功");
			} catch (Exception e) {
				e.printStackTrace();
				renderJsonError("删除异常");
			}
	    }
	    
	    public void toRead(){
	    	try {
				String[] ids= this.getPara("ids","").split(",");
				for (int i = 0;i<ids.length;i++) {
					Db.update("update t_sysmsg set state ='2' where id = ?",Integer.valueOf(ids[i]));
				}
				renderJsonSuccess("修改成功");
			} catch (Exception e) {
				e.printStackTrace();
				renderJsonError("修改异常");
			}
	    }
}
