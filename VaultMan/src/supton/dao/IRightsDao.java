package supton.dao;

import java.util.List;

import supton.entity.Rights;

public interface IRightsDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Rights transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Rights persistentInstance);
      
    /** 
     * 添加数据 
     * @param Role 
     */  
	 public Rights findById(Integer id);
	 
	 public List<Rights> findAll();
}
