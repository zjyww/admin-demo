package com.demo.web.admin.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.demo.web.admin.model.Station;
import com.demo.web.admin.model.StationElectric;
import com.demo.web.core.controller.BaseController;
import com.jfinal.ext.anotation.RouteMapping;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

@RouteMapping(controllerKey = "/admin/stationElectric")
public class StationElectricController extends BaseController{
	public void index(){
		render("/admin/station/electricMgr.jsp");
	}
	

	public void add(){
		this.setAttr("station", Station.DAO.findById(getParaToInt("stationid")));
		render("/admin/station/electricEdit.jsp");
	}
	
	public void importExcel(){
		
	}
	public void delete(){
		try {
			String[] ids= this.getPara("ids").split(",");
			for (int i = 0;i<ids.length;i++) {
				Db.deleteById("t_station_electric", "id", Integer.valueOf(ids[i]));
			}
			renderJsonSuccess("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			renderJsonError("删除异常");
		}
	}
	public void edit(){
		this.setAttr("station", Station.DAO.findById(getParaToInt("stationid")));
		this.setAttr("stationElectric", StationElectric.DAO.findById(this.getParaToInt("id")));
		render("/admin/station/electricEdit.jsp");
	}
	
	public void data(){
		int start = getParaToInt("offset");
    	int pageSize = getParaToInt("limit");
    	int pageNum = (start+pageSize)/pageSize;
    	int stationid = getParaToInt("stationid",0);
    	String sql = "select * ";
    	StringBuilder sqlWhere = new StringBuilder("from t_station_electric ");
    	String orderby = "order by createdate desc";
    	sqlWhere.append("where stationid ='").append(stationid).append("'").append(orderby);
    	Page<StationElectric> se = StationElectric.DAO.paginate(pageNum, pageSize, sql, sqlWhere.toString());
    	Map<String, Object> result = new HashMap<String, Object>();
    	if(stationid != 0){
    		StationElectric statics = StationElectric.DAO.findFirst("select count(*) as total,sum(payment) as payment,sum(power) as power from t_station_electric where stationid ='"+stationid+"' group by stationid");
    		result.put("statics", statics);
    	}
    	
    	result.put("total", se.getTotalRow());
    	result.put("rows", se.getList());
    	renderJson(result);
	}
	
	public void search(){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("value",null);
		try {
			String[] keywords = new String(getPara("keyword","").getBytes("ISO8859-1"),"UTF-8").trim().split(" ");
			StringBuilder sql = new StringBuilder("select * from t_station where CONCAT(code,name) like '%");
			for (int i = 0; i < keywords.length; i++) {
				if(i == 0){
					sql.append(keywords[i]).append("%' ");
				}else{
					sql.append("and CONCAT(code,name) like '%").append(keywords[i]).append("%' ");
				}
			}
			result.put("value", Station.DAO.find(sql.toString()));
			renderJson(result);
		} catch (Exception e) {
			renderJsonError("查询出错。");
		}
	}
	
	public void save(){
		try {
			StationElectric s = getModel(StationElectric.class, "stationElectric");
			Integer id = s.getInt("id");
			if(id == null){
				s.save();
			}else{
				s.update();
			}
			renderJsonSuccess("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			renderJsonError("保存异常，请重试。");
		}
	}
}
