package supton.service;

import java.util.List;

import supton.entity.Organization;
import supton.entity.Roleright;
import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
import supton.entity.VaultlogDecode;


public interface IRoleRightService {  

	 public void save(Roleright transientInstance);
      
	 public void delete(Roleright persistentInstance);
      
	 public Roleright findById(Integer id);
	 
	 
}  