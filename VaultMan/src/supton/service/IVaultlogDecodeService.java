package supton.service;

import java.util.List;

import supton.entity.Organization;
import supton.entity.VaultlogDecode;


public interface IVaultlogDecodeService {  

	 public void save(VaultlogDecode transientInstance);
      
	 public void delete(VaultlogDecode persistentInstance);
      
	 public VaultlogDecode findById(Integer id);
	 
	 public List<VaultlogDecode> findAll();
	 
	 public List<VaultlogDecode> findByUsercode(String usercode);
}  