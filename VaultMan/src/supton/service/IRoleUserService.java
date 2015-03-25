package supton.service;

import java.util.List;

import supton.entity.Role;
import supton.entity.RoleUser;


public interface IRoleUserService {  
	public void save(RoleUser transientInstance);
	public void delete(RoleUser persistentInstance);
	public RoleUser findById(Integer id);
	public List<RoleUser> findAll();
	public List<RoleUser> findByUsercode(String usercode);
	public List<Role> getRoleByUsercode(String userCode);
	
	public void deleteByUserCode(String userCode);
}  