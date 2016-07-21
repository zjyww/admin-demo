package com.demo.web.admin.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.demo.web.admin.model.Station;
import com.demo.web.admin.model.StationImg;
import com.demo.web.admin.model.SysUser;
import com.demo.web.core.controller.BaseController;
import com.demo.web.core.util.StringUtil;
import com.jfinal.core.JFinalFilter;
import com.jfinal.ext.anotation.RouteMapping;
import com.jfinal.ext.kit.JaxbKit;
import com.jfinal.ext.kit.excel.PoiImporter;
import com.jfinal.ext.kit.excel.Rule;
import com.jfinal.kit.PathKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

@RouteMapping(controllerKey = "/admin/station")
public class StationController extends BaseController{
	 /**
     * 描述：
     */
    protected Log log = Log.getLog(AdminController.class);
    
    public void index(){
    	
    	render("/admin/station/stationMgr.jsp");
    }
    
    public void data() throws Exception{
    	int start = getParaToInt("offset");
    	int pageSize = getParaToInt("limit");
    	String search = new String(getPara("search","").getBytes("ISO8859-1"),"UTF-8");
    	int pageNum = (start+pageSize)/pageSize;
    	String sql = "select * ";
    	StringBuilder sqlWhere = new StringBuilder(" where ");
    	sqlWhere.append("code like '%").append(search).append("%' ");
    	sqlWhere.append("or name like '%").append(search).append("%' ");
    	
    	String sort = getPara("sort", "code");
    	String order = getPara("order","asc");
    	String orderBy = " order by "+sort + " "+order;
    	Page<Station> station = Station.DAO.paginate(pageNum, pageSize, sql, "from t_station "+sqlWhere +orderBy);
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("total", station.getTotalRow());
    	result.put("rows", station.getList());
    	renderJson(result);
    }
    
    public void importExcel(){
    	try {
    	File excel = this.getFile("file").getFile();
    	File xmlFile = new File(PathKit.getRootClassPath()+"\\rule.xml");
        Rule rule = JaxbKit.unmarshal(xmlFile , Rule.class);
    	List<Model<?>> result = PoiImporter.processSheet(excel, rule,Station.class); 
    	File xmlFile2 = new File(PathKit.getRootClassPath()+"\\rule2.xml");
        Rule rule2 = JaxbKit.unmarshal(xmlFile2 , Rule.class);
        List<Model<?>> result2 = PoiImporter.processSheet(excel, rule2,Station.class);
        File xmlFile3 = new File(PathKit.getRootClassPath()+"\\rule3.xml");
        Rule rule3 = JaxbKit.unmarshal(xmlFile3 , Rule.class);
        List<Model<?>> result3 = PoiImporter.processSheet(excel, rule3,Station.class);
        File xmlFile4 = new File(PathKit.getRootClassPath()+"\\rule4.xml");
        Rule rule4 = JaxbKit.unmarshal(xmlFile4 , Rule.class);
        List<Model<?>> result4 = PoiImporter.processSheet(excel, rule4,Station.class);
        
        mergerAttrs(result, result2);
        mergerAttrs(result, result3);
        mergerAttrs(result, result4);
        
        Db.batchSave(result, result.size());
        
        renderJsonSuccess("导入成功");
    	} catch (Exception e) {
    		e.printStackTrace();
    		renderJsonError("导入失败，请检查Excel文件格式！");
		}
    }

	private void mergerAttrs(List<Model<?>> result, List<Model<?>> other) {
		for (Model<?> model : result) {
			for (Model<?> m2 : other) {
				String code = model.getStr("code");
				if( code != null && code.equals(m2.getStr("code"))){
					model.put(m2);
					break;
				}
			}
		}
	}
	
	public void add(){
		render("/admin/station/edit.jsp");
	}
	
	public void edit(){
		this.setAttr("station", Station.DAO.findById(this.getParaToInt("id")));
		render("/admin/station/edit.jsp");
	}
	
	public void save(){
		try {
			Station s  =this.getModel(Station.class);
			Integer id = s.getId();
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
	
	public void delete(){
		try {
			String[] ids= this.getPara("ids").split(",");
			for (int i = 0;i<ids.length;i++) {
				Db.deleteById("t_station", "id", Integer.valueOf(ids[i]));
			}
			renderJsonSuccess("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			renderJsonError("删除异常");
		}
	}
	
	public void contract(){
		this.setAttr("imgs", StationImg.DAO.find("select * from t_station_img where type='1' and stationId = "+this.getPara("id")));
		render("/admin/station/contract.jsp");
	}
	
	public void certificate(){
		this.setAttr("imgs", StationImg.DAO.find("select * from t_station_img where type='2' and stationId = "+this.getPara("id")));
		render("/admin/station/contract.jsp");
	}
	
	public void uploadImage(){
		this.getFiles("file").get(0);
	}
}
