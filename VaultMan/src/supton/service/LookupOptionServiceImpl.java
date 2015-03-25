package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.ILookupOptionDao;
import supton.entity.Lookupoption;

@Service
public class LookupOptionServiceImpl implements ILookupOptionService {

	@Autowired
	private ILookupOptionDao lookupOptionDao;

	public void save(Lookupoption transientInstance) {
		lookupOptionDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Lookupoption persistentInstance) {
		lookupOptionDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param Lookupoption
	 */
	public Lookupoption findById(Integer id) {
		return lookupOptionDao.findById(id);
	}
	
	public List<Lookupoption> findByClass_(int class_) {
		return lookupOptionDao.findByClass_(class_);
	}
}