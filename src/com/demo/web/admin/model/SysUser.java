package com.demo.web.admin.model;

import com.demo.web.admin.model.basemodel.BaseSysuser;
import com.jfinal.ext.anotation.ModelMapping;

@ModelMapping(tableName="t_sysuser",pkName="user_id")
public class SysUser extends BaseSysuser<SysUser>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6292943986199987052L;
	
	/**
     * DAO
     */
    public static final SysUser DAO = new SysUser();
    
    
}
