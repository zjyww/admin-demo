package com.demo.job;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.demo.web.admin.model.Station;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;

public class MsgJob implements Job{
	 /**
     * 描述：
     */
    protected Log log = Log.getLog(MsgJob.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//log.info("开始执行站点检查，判断是否合同快到期，提醒结算截止");
		int beforeContract = PropKit.use("webconfig.properties").getInt("beforeContract", 10); //
		Calendar now = Calendar.getInstance();
		String contractEnd = DateFormatUtils.format(now, "yyyy/MM/dd");
		String endpayEnd = contractEnd;
		now.add(Calendar.DAY_OF_MONTH, -beforeContract);
		String contractStart = DateFormatUtils.format(now , "yyyy/MM/dd");
		List<Station> cons = Station.DAO.find("select contractEnd,code,name from t_station where contractEnd >= ? and contractEnd < ?",contractStart,contractEnd);
		
		int beforeEndpay = PropKit.use("webconfig.properties").getInt("beforeEndpay", 5); //
		now.add(Calendar.DAY_OF_MONTH, -beforeEndpay+beforeContract);
		String endpayStart = DateFormatUtils.format(now , "yyyy/MM/dd");
		List<Station> pays = Station.DAO.find("select endPay,code,name from t_station where endPay >= ? and endPay < ?",endpayStart,endpayEnd);
		
		
		
	}

}
