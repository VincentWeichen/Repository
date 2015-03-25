package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IVaultlogDao;
import supton.entity.Vaultlog;


@Service
public class VaultlogServiceImpl implements IVaultlogService {

	@Autowired
	private IVaultlogDao vaultlogDao;

	public void save(Vaultlog transientInstance) {
		vaultlogDao.save(transientInstance);
	}

	public void delete(Vaultlog persistentInstance) {
		vaultlogDao.delete(persistentInstance);
	}

	public Vaultlog findById(Integer id) {
		return vaultlogDao.findById(id);
	}
	
	public List<Vaultlog> findByTid(String tid)
	{
		return vaultlogDao.findByTid(tid);
	}
	
	public List<Vaultlog> findByTidDate(String tid,String nowDate)
	{
		return vaultlogDao.findByTidDate(tid, nowDate);
	}
	
	public List<Vaultlog> findByTidNowDate(String tid,String nowDate)
	{
		return vaultlogDao.findByTidNowDate(tid, nowDate);
	}
	
	public List<Vaultlog> findByTidLogIndex(String tid,String logIndex,String nowDate)
	{
		return vaultlogDao.findByTidLogIndex(tid, logIndex, nowDate);
	}
}