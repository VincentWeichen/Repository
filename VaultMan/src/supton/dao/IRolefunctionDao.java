package supton.dao;


import java.util.List;

import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.Rolefunction;

public interface IRolefunctionDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Rolefunction transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Rolefunction persistentInstance);
      
    /** 
     * 添加数据 
     * @param Role 
     */  
	 public Rolefunction findById(Integer id);
	 
	 public List<Rolefunction> findByRoles(String roles);
	 
	 public List<Rolefunction> findByRolecode(Object rolecode);
	 
	 public int deleteByRolecode(String rolecode);
	 
	 public List<String> findCodeByRoles(String roles);
	 

}
