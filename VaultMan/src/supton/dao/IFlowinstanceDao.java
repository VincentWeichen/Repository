package supton.dao;


import java.util.List;

import supton.entity.Flowinstance;
import supton.entity.PageInfo;
import supton.entity.Role;

public interface IFlowinstanceDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Flowinstance transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Flowinstance persistentInstance);
      
    /** 
     * 添加数据 
     * @param Role 
     */  
	 public Flowinstance findById(Integer id);
	 
}
