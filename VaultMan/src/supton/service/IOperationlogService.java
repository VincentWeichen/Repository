package supton.service;

import java.util.List;

import supton.entity.Operationlog;
import supton.entity.Organization;
import supton.entity.Roleright;
import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
import supton.entity.VaultlogDecode;


public interface IOperationlogService {  

	 public void save(Operationlog transientInstance);
      
	 public void delete(Operationlog persistentInstance);
      
	 public Operationlog findById(Integer id);
	 
	 
}  