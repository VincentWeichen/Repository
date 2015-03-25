package supton.dao;


import java.util.List;

import supton.entity.Role;
import supton.entity.Subgrouparrange;
import supton.entity.Taskexport;

public interface ISubgrouparrangeDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Subgrouparrange transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Subgrouparrange persistentInstance);
      
    /** 
     * 添加数据 
     * @param Subgrouparrange 
     */  
	 public Subgrouparrange findById(Integer id);
	 
	 public int deleteByOrganizationcode(String organizationcode,String type);
	 
	 public List<Subgrouparrange> findByOrganizationcode(String organizationcode);
	 
	 public Taskexport findTaskExport(String tid, Integer typeid);
	 
	 public void SetTaskExport(Taskexport taskexport);
}
