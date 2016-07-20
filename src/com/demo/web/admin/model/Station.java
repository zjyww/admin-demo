package com.demo.web.admin.model;

import com.demo.web.admin.model.basemodel.BaseStation;
import com.jfinal.ext.anotation.ModelMapping;

@ModelMapping(tableName="t_station",pkName="id")
public class Station extends BaseStation<Station>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * DAO
     */
    public static final Station DAO = new Station();
}
