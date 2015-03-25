package supton.service;

import java.util.List;

import supton.entity.Messagelog;
import supton.entity.Organization;
import supton.entity.Roleright;
import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
import supton.entity.VaultlogDecode;


public interface IMessagelogService {  

	 public void save(Messagelog transientInstance);
      
	 public void delete(Messagelog persistentInstance);
      
	 public Messagelog findById(Integer id);
	 
	 
}  