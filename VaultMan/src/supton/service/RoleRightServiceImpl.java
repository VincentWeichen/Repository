package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IRoleRightDao;
import supton.entity.Roleright;

@Service
public class RoleRightServiceImpl implements IRoleRightService {

	@Autowired
	private IRoleRightDao roleRightDao;

	public void save(Roleright transientInstance) {
		roleRightDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Roleright persistentInstance) {
		roleRightDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param Roleright
	 */
	public Roleright findById(Integer id) {
		return roleRightDao.findById(id);
	}
	
	 
}