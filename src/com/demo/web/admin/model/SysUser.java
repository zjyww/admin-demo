package com.demo.web.admin.model;

import com.jfinal.ext.anotation.ModelMapping;
import com.jfinal.plugin.activerecord.Model;

@ModelMapping(tableName="t_sysuser",pkName="user_id")
public class SysUser extends Model<SysUser>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6292943986199987052L;
	
	/**
     * DAO
     */
    public static final SysUser DAO = new SysUser();
    
    
}
