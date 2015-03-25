package supton.service;

import java.util.List;

import supton.entity.PageInfo;
import supton.entity.Role;


public interface IRoleService {  
	public void save(Role transientInstance);
	public void delete(Role persistentInstance);
	public Role findById(Integer id);
	public PageInfo PagedQuery(int pageNo, int pageSize);
	public List<Role> findByCode(Object code);
	public void saveRole(Role role);
	public int deleteRole(String codes);
	public List<Role> findAll();
	public Role getRoleById(int id);
}  