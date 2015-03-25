package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IRightsDao;
import supton.entity.Rights;

@Service
public class RightsServiceImpl implements IRightsService {

	@Autowired
	private IRightsDao rightsDao;

	public void save(Rights transientInstance) {
		rightsDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Rights persistentInstance) {
		rightsDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param user
	 */
	public Rights findById(Integer id) {
		return rightsDao.findById(id);
	}
	
	public List<Rights> findAll()
	{
		return rightsDao.findAll();
	}
}