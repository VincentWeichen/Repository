package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IFingerprintUserDao;
import supton.entity.Fingerprintuser;

@Service
public class FingerprintUserServiceImpl implements IFingerprintUserService {

	@Autowired
	private IFingerprintUserDao fingerprintUserDao;

	public void save(Fingerprintuser transientInstance) {
		fingerprintUserDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Fingerprintuser persistentInstance) {
		fingerprintUserDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param Fingerprintuser
	 */
	public Fingerprintuser findById(Integer id) {
		return fingerprintUserDao.findById(id);
	}
	
	public List<Fingerprintuser> findByTID(String tid)
	{
		return fingerprintUserDao.findByTID(tid);
	}
	
	public List<Fingerprintuser> getFingerprintuserListByOrgCode(String tId) {
		return fingerprintUserDao.findByTID(tId);
	}
	
	public List<Fingerprintuser> findAllByTId(String tid) {
		return fingerprintUserDao.findAllByTId(tid);
	}
	
	public Fingerprintuser getFingerprintuserByTUserCode(String tusercode,String tid){
		return fingerprintUserDao.getFingerprintuserByTUserCode(tusercode,tid);
	}
}