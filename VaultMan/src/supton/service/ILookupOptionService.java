package supton.service;

import java.util.List;

import supton.entity.Lookupoption;
import supton.entity.Messagelog;
import supton.entity.Organization;
import supton.entity.Roleright;
import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
import supton.entity.VaultlogDecode;


public interface ILookupOptionService {  

	 public void save(Lookupoption transientInstance);
      
	 public void delete(Lookupoption persistentInstance);
      
	 public Lookupoption findById(Integer id);
	 
	 public List<Lookupoption> findByClass_(int class_);
	 
}  