package com.demo.web.admin.model;

import com.jfinal.ext.anotation.ModelMapping;
import com.jfinal.plugin.activerecord.Model;

@ModelMapping(tableName="t_station",pkName="id")
public class Station extends Model<Station>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * DAO
     */
    public static final SysUser DAO = new SysUser();
}
