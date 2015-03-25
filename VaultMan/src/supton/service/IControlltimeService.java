package supton.service;

import java.util.List;

import supton.entity.Controlltime;
import supton.entity.Controlltimedetail;
import supton.entity.Exclusionrole;
import supton.entity.Fingerprintuser;
import supton.entity.Lookupoption;
import supton.entity.Messagelog;
import supton.entity.Organization;
import supton.entity.Roleright;
import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
import supton.entity.VaultlogDecode;


public interface IControlltimeService {  

	 public void save(Controlltime transientInstance);
      
	 public void delete(Controlltime persistentInstance);
      
	 public Controlltime findById(Integer id);
	 
	 public List<Controlltime> findByType(String type);
	 
	 public List<Controlltime> findByOrganizationcode(String organizationcode);
	 
	 public int deleteByControlltimeId(Integer controlltimeId);
}  