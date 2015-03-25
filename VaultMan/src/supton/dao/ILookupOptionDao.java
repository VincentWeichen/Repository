package supton.dao;


import java.util.List;

import supton.entity.Lookupoption;
import supton.entity.Role;

public interface ILookupOptionDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Lookupoption transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Lookupoption persistentInstance);
      
    /** 
     * 添加数据 
     * @param Lookupoption 
     */  
	 public Lookupoption findById(Integer id);
	 
	 public List<Lookupoption> findByClass_(int class_);
}
