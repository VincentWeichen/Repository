package supton.service;

import java.util.List;

import supton.entity.Exclusionrole;
import supton.entity.Fingerprintuser;
import supton.entity.Lookupoption;
import supton.entity.Messagelog;
import supton.entity.Organization;
import supton.entity.PageInfo;
import supton.entity.Roleright;
import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
import supton.entity.VaultlogDecode;


public interface IExclusionroleService {  

	 public void save(Exclusionrole transientInstance);
      
	 public void delete(Exclusionrole persistentInstance);
      
	 public Exclusionrole findById(Integer id);
	 
     public int deleteExclusionrole(int sn);
	 
	 public List<Exclusionrole> findBySn(int sn);
	 
	 public void saveExclusionrole(Exclusionrole exclusionrole);
	 
	 public PageInfo PagedQuery(int pageNo, int pageSize);
}  