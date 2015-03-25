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


public interface IControlltimedetailService {  

	 public void save(Controlltimedetail transientInstance);
      
	 public void delete(Controlltimedetail persistentInstance);
      
	 public Controlltimedetail findById(Integer id);
	 
     public List<Controlltimedetail> findByOrganizationcode(String organizationcode);
	 
	 public List<Controlltimedetail> findByType(String type);
	 
	 public int delete(Integer id);
	 
	 public List<Controlltimedetail> findByOrganizationcode(String organizationcode,String type);
	 
	 public int deleteByControlltimeercisedateId(Integer controlltimeercisedateid);
	 
	 public int deleteByControlltimeId(Integer controlltimeid);
}  