package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IRoleDao;
import supton.entity.PageInfo;
import supton.entity.Role;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roledao;

	public void save(Role transientInstance) {
		roledao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Role persistentInstance) {
		roledao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param user
	 */
	public Role findById(Integer id) {
		return roledao.findById(id);
	}
	
	public PageInfo PagedQuery(int pageNo, int pageSize)
	{
		return roledao.PagedQuery(pageNo,pageSize);
	}
	
	public List<Role> findByCode(Object code)
	{
		return roledao.findByCode(code);
	}
	
	public void saveRole(Role role)
	{
		roledao.saveRole(role);
	}
	
	public int deleteRole(String codes)
	{
		return roledao.deleteRole(codes);
	}
	
	public List<Role> findAll()
	{
		return roledao.findAll();
	}
	
	public Role getRoleById(int id)
	{
		return roledao.getRoleById(id);
	}
}