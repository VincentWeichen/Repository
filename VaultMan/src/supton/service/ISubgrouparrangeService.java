package supton.service;

import java.util.List;

import supton.entity.Organization;
import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
import supton.entity.Taskexport;
import supton.entity.VaultlogDecode;


public interface ISubgrouparrangeService {  

	 public void save(Subgrouparrange transientInstance);
      
	 public void delete(Subgrouparrange persistentInstance);
      
	 public Subgrouparrange findById(Integer id);
	 
	 public int deleteByOrganizationcode(String organizationcode,String type);
	 
	 public List<Subgrouparrange> findByOrganizationcode(String organizationcode);
	 
	 public Taskexport findTaskExport(String tid, Integer typeid);
	 
	 public void SetTaskExport(Taskexport taskexport);
}  