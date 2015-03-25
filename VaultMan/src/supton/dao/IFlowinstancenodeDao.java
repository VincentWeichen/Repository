package supton.dao;


import java.util.List;

import supton.entity.Flowinstancenode;
import supton.entity.PageInfo;
import supton.entity.Role;

public interface IFlowinstancenodeDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Flowinstancenode transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Flowinstancenode persistentInstance);
      
    /** 
     * 添加数据 
     * @param Role 
     */  
	 public Flowinstancenode findById(Integer id);
	 
}
