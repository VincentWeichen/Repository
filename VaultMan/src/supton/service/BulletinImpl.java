package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IBulletinDao;
import supton.entity.Bulletin;

@Service
public class BulletinImpl implements IBulletinService {

	@Autowired
	private IBulletinDao bulletinDao;

	public void save(Bulletin transientInstance) {
		bulletinDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Bulletin persistentInstance) {
		bulletinDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param Bulletin
	 */
	public Bulletin findById(Integer id) {
		return bulletinDao.findById(id);
	}
	
	 
}