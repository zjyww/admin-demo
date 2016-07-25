package com.demo.web.admin.model;

import com.jfinal.ext.anotation.ModelMapping;
import com.jfinal.plugin.activerecord.Model;

@ModelMapping(tableName="t_station_electric",pkName="id")
public class StationElectric extends Model<StationElectric>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6667927360272931311L;

	/**
     * DAO
     */
    public static final StationElectric DAO = new StationElectric();
}
