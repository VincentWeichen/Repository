package supton.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IRoleUserDao;
import supton.entity.Role;
import supton.entity.RoleUser;

@Service
public class RoleUserServiceImpl  implements IRoleUserService{
	
	@Autowired
	private IRoleUserDao roleUserdao;

	public void save(RoleUser transientInstance) {
		roleUserdao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(RoleUser persistentInstance) {
		roleUserdao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param user
	 */
	public RoleUser findById(Integer id) {
		return roleUserdao.findById(id);
	}
	
	public List<RoleUser> findAll()
	{
		return roleUserdao.findAll();
	}
	
	public List<RoleUser> findByUsercode(String usercode)
	{
		return roleUserdao.findByUsercode(usercode);
	}
	
	public List<Role> getRoleByUsercode(String userCode)
	{
		return roleUserdao.getRoleByUsercode(userCode);
	}
	
	public void deleteByUserCode(String userCode) {
		roleUserdao.deleteByUserCode(userCode);
	}
}