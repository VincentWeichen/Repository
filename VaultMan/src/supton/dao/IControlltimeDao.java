package supton.dao;


import java.util.List;

import supton.entity.Controlltime;
import supton.entity.Role;

public interface IControlltimeDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Controlltime transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Controlltime persistentInstance);
      
    /** 
     * 添加数据 
     * @param Controlltime 
     */  
	 public Controlltime findById(Integer id);
	 
	 public List<Controlltime> findByType(String type);
	 
	 public List<Controlltime> findByOrganizationcode(String organizationcode);
	 
	 public int deleteByControlltimeId(Integer controlltimeId);
}
