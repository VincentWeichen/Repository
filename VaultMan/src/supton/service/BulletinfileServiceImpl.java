package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IBulletinfileDao;
import supton.dao.IControlltimedetailDao;
import supton.dao.IExclusionroleDao;
import supton.dao.IFingerprintUserDao;
import supton.dao.ILookupOptionDao;
import supton.dao.IMessagelogDao;
import supton.dao.IRoleDao;
import supton.dao.IRoleRightDao;
import supton.dao.ISubgroupDao;
import supton.dao.ISubgrouparrangeDao;
import supton.dao.IVaultlogDecodeDao;
import supton.entity.Bulletinfile;
import supton.entity.Controlltime;
import supton.entity.Controlltimedetail;
import supton.entity.Exclusionrole;
import supton.entity.Fingerprintuser;
import supton.entity.Lookupoption;
import supton.entity.Messagelog;
import supton.entity.Role;
import supton.entity.Roleright;
import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
import supton.entity.VaultlogDecode;

@Service
public class BulletinfileServiceImpl implements IBulletinfileService {

	@Autowired
	private IBulletinfileDao bulletinfileDao;

	public void save(Bulletinfile transientInstance) {
		bulletinfileDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Bulletinfile persistentInstance) {
		bulletinfileDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param Bulletinfile
	 */
	public Bulletinfile findById(Integer id) {
		return bulletinfileDao.findById(id);
	}
	
	public List<Bulletinfile> findAll()
	{
		return bulletinfileDao.findAll();
	}
}