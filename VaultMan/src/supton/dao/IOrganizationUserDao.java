package supton.dao;


import java.util.List;

import supton.entity.OrganizationUser;
import supton.entity.PageInfo;
import supton.entity.pseudo.UserInfomation;

public interface IOrganizationUserDao {
    /** 
     * 查看条数 
     * @return 
     */  
	public void save(OrganizationUser transientInstance);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	public void delete(OrganizationUser persistentInstance);
      
    /** 
     * 添加数据 
     * @param OrganizationUser 
     */  
	public OrganizationUser findById(Integer id);
	
	public List<OrganizationUser> findAll();
	
	public UserInfomation getUserInfomationByOrCode(String organizationCode);
	
	public int deleteOrganizationUser(String userCode);
	
	public List<OrganizationUser> findByUsercode(String usercode);
	
	public PageInfo PagedQuery(String organizationCode, int pageNo, int pageSize);
}
