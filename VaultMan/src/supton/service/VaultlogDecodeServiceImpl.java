package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IRoleDao;
import supton.dao.IVaultlogDecodeDao;
import supton.entity.Role;
import supton.entity.VaultlogDecode;

@Service
public class VaultlogDecodeServiceImpl implements IVaultlogDecodeService {

	@Autowired
	private IVaultlogDecodeDao vaultlogDecodeDao;

	public void save(VaultlogDecode transientInstance) {
		vaultlogDecodeDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(VaultlogDecode persistentInstance) {
		vaultlogDecodeDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param user
	 */
	public VaultlogDecode findById(Integer id) {
		return vaultlogDecodeDao.findById(id);
	}
	
	 public List<VaultlogDecode> findAll()
	 {
		 return vaultlogDecodeDao.findAll();
	 }
	 
	 public List<VaultlogDecode> findByUsercode(String usercode)
	 {
		 return vaultlogDecodeDao.findByUsercode(usercode);
	 }
}