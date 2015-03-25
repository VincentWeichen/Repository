package supton.dao;


import supton.entity.Role;
import supton.entity.Roleright;

public interface IRoleRightDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Roleright transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Roleright persistentInstance);
      
    /** 
     * 添加数据 
     * @param Role 
     */  
	 public Roleright findById(Integer id);
}
