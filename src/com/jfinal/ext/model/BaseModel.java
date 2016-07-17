package com.jfinal.ext.model;

import java.io.Serializable;
import java.util.List;

import com.jfinal.ext.anotation.ModelMapping;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

/**
 * 
 * @param <M>
 */
public abstract class BaseModel<M extends Model<M>> extends Model<M> implements Serializable {

    /**
     * 描述：
     */
    private static final long serialVersionUID = 9147595350274395513L;
    /**
     * 描述：
     */
    private static Log log = Log.getLog(BaseModel.class);

    /**
     * @return 表名
     */
    public String tableName;
    /**
     * @return 主键名
     */
    public String pkName;

    /***
     * 反射获取 注解获得 tablename
     */
    public BaseModel() {
        super();
        ModelMapping table = this.getClass().getAnnotation(ModelMapping.class);
        if (table != null) {
            tableName = table.tableName();
            pkName = table.pkName();
        }
    }

    /***
     * 删除自己的同时 删除 所有 子节点 属性名 必需为pid
     * 
     * @param para
     * @return
     */
    public boolean deleteByIdAndPid(Integer id) {
        boolean result = deleteById(id);
        List<Record> list = (List<Record>) Db.find("select * from " + tableName + " where pid=?", id);
        for (Record r : list) {
            deleteByIdAndPid(r.getInt(pkName));
            Db.update("delete from " + tableName + " where pid=? ", id);
        }
        return result;
    }

    /***
     * ids 必需为 连续的 1，2，3 这样子
     * 
     * @param ids
     */
    public boolean batchDelete(String ids) {
        if (StrKit.isBlank(ids)) {
            return false;
        }
        return Db.update("delete from " + tableName + " where " + pkName + " in (" + ids + ")") > 0;
    }

}
