package supton.dao;


import java.util.List;

import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.Subgroup;

public interface ISubgroupDao {
    /** 
     * 查看条数 
     * @return 
     */  
	 public void save(Subgroup transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	 public void delete(Subgroup persistentInstance);
      
    /** 
     * 添加数据 
     * @param Role 
     */  
	 public Subgroup findById(Integer id);
	 
	 public PageInfo PagedQuery(String organizationCode, int pageNo, int pageSize);
	 
	 public int deleteGroup(String code, String corganizationCode);
	 
	 public List<Subgroup> findByCode(String code);
	 
	 public int deleteByOrganizationcode(String organizationcode);
	 
	 public List<Subgroup> findByOrganizationcode(String organizationcode);
	 
	 public List<Subgroup> GetGroupListBytype(String organizationCode,String type);
	 
	 public Subgroup GetGroupByAuthorize(String organizationCode,String type,String groupauthorize);
}
