package com.demo.job;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.demo.web.admin.model.Station;
import com.demo.web.admin.model.SysMsg;
import com.demo.web.core.util.SendMailUtil;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;

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
		String contractStart = DateFormatUtils.format(now, "yyyy/MM/dd");
		String endpayStart = contractStart;
		now.add(Calendar.DAY_OF_MONTH, beforeContract);
		String contractEnd = DateFormatUtils.format(now , "yyyy/MM/dd");
		List<Station> cons = Station.DAO.find("select contractEnd,code,name from t_station where contractEnd > ? and contractEnd < ?",contractStart,contractEnd);
		List<SysMsg> msgs = new ArrayList<SysMsg>();
		for (Station station : cons) {
			String msg = MessageFormat.format("站点：{0}（{1}），合同将于{2}到期，请处理。", station.get("name"),station.get("code"),station.get("contractEnd"));
			SysMsg m = new SysMsg();
			m.set("content", msg);
			m.set("createtime", DateFormatUtils.format(new Date(), "yyyy/MM/dd hh:mm:ss"));
			m.set("touser", 1);
			m.set("state","1");
			msgs.add(m);
		}
		
		int beforeEndpay = PropKit.use("webconfig.properties").getInt("beforeEndpay", 5); //
		now.add(Calendar.DAY_OF_MONTH, beforeEndpay-beforeContract);
		String endpayEnd = DateFormatUtils.format(now , "yyyy/MM/dd");
		List<Station> pays = Station.DAO.find("select endPay,code,name from t_station where endPay >= ? and endPay < ?",endpayStart,endpayEnd);

		for (Station station : pays) {
			String msg = MessageFormat.format("站点：{0}（{1}），电费结算截至日期为{2}，请处理。", station.get("name"),station.get("code"),station.get("endPay"));
			SysMsg m = new SysMsg();
			m.set("content", msg);
			m.set("createtime", DateFormatUtils.format(new Date(), "yyyy/MM/dd hh:mm:ss"));
			m.set("touser", 1);
			m.set("state","1");
			msgs.add(m);
		}
		
		Db.batchSave(msgs, msgs.size());
		
		try {
			//SendMailUtil.sendMail("653828626@qq.com", "最新消息", msgs.get(0).getStr("content"), false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
