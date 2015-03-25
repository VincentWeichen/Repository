package supton.dao;


import java.util.List;

import supton.entity.OrganizationUser;
import supton.entity.Role;
import supton.entity.RoleUser;

public interface IRoleUserDao {
    /** 
     * 查看条数 
     * @return 
     */  
	public void save(RoleUser roleUser);
      
    /** 
     * 删除表数据 
     * @return 
     */  
	public void delete(RoleUser roleUser);
	
	public void deleteByUserCode(String userCode);
      
    /** 
     * 添加数据 
     * @param RoleUser 
     */  
	public RoleUser findById(Integer id);
	
	public List<RoleUser> findAll();
	
	public List<RoleUser> findByUsercode(String usercode);
	
	public List<Role> getRoleByUsercode(String userCode);
}
