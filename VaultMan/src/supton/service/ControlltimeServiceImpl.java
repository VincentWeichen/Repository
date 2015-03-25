package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IControlltimeDao;
import supton.entity.Controlltime;

@Service
public class ControlltimeServiceImpl implements IControlltimeService {

	@Autowired
	private IControlltimeDao controlltimeDao;

	public void save(Controlltime transientInstance) {
		controlltimeDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Controlltime persistentInstance) {
		controlltimeDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param Controlltime
	 */
	public Controlltime findById(Integer id) {
		return controlltimeDao.findById(id);
	}
	
	public List<Controlltime> findByType(String type)
	{
		return controlltimeDao.findByType(type);
	}
	
	public List<Controlltime> findByOrganizationcode(String organizationcode)
	{
		return controlltimeDao.findByOrganizationcode(organizationcode);
	}
	
	public int deleteByControlltimeId(Integer controlltimeId)
	{
		return controlltimeDao.deleteByControlltimeId(controlltimeId);
	}
}