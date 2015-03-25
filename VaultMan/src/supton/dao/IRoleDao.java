package supton.dao;


import java.util.List;

import supton.entity.PageInfo;
import supton.entity.Role;

public interface IRoleDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Role transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Role persistentInstance);
      
    /** 
     * 添加数据 
     * @param Role 
     */  
	 public Role findById(Integer id);
	 
	 /** 
	  * 分页查询
	  * @param List<Role> 
	  */
	 public PageInfo PagedQuery(int pageNo, int pageSize);
	 
	 public List<Role> findByCode(Object code);
	 
	 public void saveRole(Role role);
	 
	 public int deleteRole(String code);
	 
	 public List<Role> findAll();
	 
	 public Role getRoleById(int id);
}
