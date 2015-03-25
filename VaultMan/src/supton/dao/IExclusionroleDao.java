package supton.dao;


import java.util.List;

import supton.entity.Exclusionrole;
import supton.entity.PageInfo;
import supton.entity.Role;

public interface IExclusionroleDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Exclusionrole transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Exclusionrole persistentInstance);
      
    /** 
     * 添加数据 
     * @param Role 
     */  
	 public Exclusionrole findById(Integer id);
	 
	 public int deleteExclusionrole(int sn);
	 
	 public List<Exclusionrole> findBySn(int sn);
	 
	 public void saveExclusionrole(Exclusionrole exclusionrole);
	 
	 public PageInfo PagedQuery(int pageNo, int pageSize);
}
