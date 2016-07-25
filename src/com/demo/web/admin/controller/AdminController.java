package com.demo.web.admin.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.demo.web.core.controller.BaseController;
import com.jfinal.ext.anotation.RouteMapping;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

@RouteMapping(controllerKey = "/admin")
public class AdminController extends BaseController{
	 /**
     * 描述：
     */
    protected Log log = Log.getLog(AdminController.class);
    
    public void index(){
    	render("/admin/index.jsp");
    }
    
    public void echartData() {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, -1);
        now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
        String end = DateFormatUtils.format(now, "yyyy/MM/dd");
        now.add(Calendar.MONTH,-12);
        now.set(Calendar.DAY_OF_MONTH, 1);
        String start = DateFormatUtils.format(now, "yyyy/MM/dd");
        String sql = "select count(*) as bzcount,sum(power) as power,SUM(payment) as payment, SUBSTR(createdate,1,7) as ct  " 
        			+"from t_station_electric " 
        			+"where createdate >=? and createdate <= ? GROUP BY ct ORDER BY  ct asc";
        
        List<Record> records = Db.find(sql, start ,end );
        
        Map<String,  Object> result = new HashMap<String, Object>();
        ArrayList<String> xAxis = new ArrayList<String>();
		ArrayList<Object> bzData = new ArrayList<Object>();
		ArrayList<Object> powerData = new ArrayList<Object>();
		ArrayList<Object> paymentData = new ArrayList<Object>();
        for (int i = 1; i <= 12; i++) {
        	now.add(Calendar.MONTH,1);
			String date = DateFormatUtils.format(now, "yyyy/MM");
			xAxis.add(date);
			boolean flag = false;
			for (Record r : records) {
				if(date.equals(r.getStr("ct"))){
					bzData.add(r.get("bzcount","0"));
					powerData.add(r.get("power", "0"));
					paymentData.add(r.get("payment","0"));
					flag = true;
				}
			}
			if(!flag){
				bzData.add("0");
				powerData.add("0");
				paymentData.add("0");
			}
		}
        result.put("xAxis", xAxis);
        result.put("bzData", bzData);
        result.put("powerData", powerData);
        result.put("paymentData", paymentData);
        renderJson(result);
	}
}
