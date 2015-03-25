package supton.service;

import java.util.List;

import supton.entity.Lookupoption;
import supton.entity.Messagelog;
import supton.entity.Organization;
import supton.entity.Roleright;
import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
import supton.entity.Vaultlog;
import supton.entity.VaultlogDecode;


public interface IVaultlogService {  

	 public void save(Vaultlog transientInstance);
      
	 public void delete(Vaultlog persistentInstance);
      
	 public Vaultlog findById(Integer id);
	 
	 public List<Vaultlog> findByTid(String tid);
	 
	 public List<Vaultlog> findByTidDate(String tid,String nowDate);
	 
	 public List<Vaultlog> findByTidNowDate(String tid,String nowDate);
	 
	 public List<Vaultlog> findByTidLogIndex(String tid,String logIndex,String nowDate);
}  