package com.demo.web.admin.model;

import com.jfinal.ext.anotation.ModelMapping;
import com.jfinal.plugin.activerecord.Model;

@ModelMapping(tableName="t_sysmsg",pkName="id")
public class SysMsg extends Model<SysMsg>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5945227054773520090L;

	/**
    * DAO
    */
   public static final SysMsg DAO = new SysMsg();
}
