package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IExclusionroleDao;
import supton.entity.Exclusionrole;
import supton.entity.PageInfo;

@Service
public class ExclusionroleServiceImpl implements IExclusionroleService {

	@Autowired
	private IExclusionroleDao exclusionroleDao;

	public void save(Exclusionrole transientInstance) {
		exclusionroleDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Exclusionrole persistentInstance) {
		exclusionroleDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param Exclusionrole
	 */
	public Exclusionrole findById(Integer id) {
		return exclusionroleDao.findById(id);
	}
	
	public int deleteExclusionrole(int sn){
		return exclusionroleDao.deleteExclusionrole(sn);
	}
	
	public List<Exclusionrole> findBySn(int sn)
	{
		return exclusionroleDao.findBySn(sn);
	}
	
	public void saveExclusionrole(Exclusionrole exclusionrole)
	{
		exclusionroleDao.saveExclusionrole(exclusionrole);
	}
	
	public PageInfo PagedQuery(int pageNo, int pageSize)
	{
		return exclusionroleDao.PagedQuery(pageNo,pageSize);
	}
}