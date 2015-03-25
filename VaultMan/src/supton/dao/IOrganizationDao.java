package supton.dao;


import java.util.List;

import supton.entity.Organization;

public interface IOrganizationDao {
    /** 
     * 查看条数 
     * @return 
     */  
	public void save(Organization transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	public void delete(Organization persistentInstance);
      
    /** 
     * 添加数据 
     * @param Organization 
     */  
	public Organization findById(Integer id);
	
	public List<Organization> findAll();
	
	public List<Organization> findByParentcode(Object parentcode);
	
	public List<Organization> getOrganizationListByCode(String organizationCode);
	
	public Organization getOrganizationByCode(String code);
	
	public Organization getOrganizationById(int id);
	
	public void SaveOrganization(Organization organization);

	public boolean deleteOrganization(String organizationCode);
	
	public List<Organization> findByTid(String tid);
}
