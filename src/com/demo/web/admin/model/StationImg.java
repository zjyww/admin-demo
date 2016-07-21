package com.demo.web.admin.model;

import com.jfinal.ext.anotation.ModelMapping;
import com.jfinal.plugin.activerecord.Model;

@ModelMapping(tableName="t_station_img",pkName="id")
public class StationImg extends Model<StationImg>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2835270171584612003L;

	/**
     * DAO
     */
    public static final StationImg DAO = new StationImg();
}
