package supton.service;

import java.util.List;

import supton.entity.Bulletinfile;
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


public interface IBulletinfileService {  

	 public void save(Bulletinfile transientInstance);
      
	 public void delete(Bulletinfile persistentInstance);
      
	 public Bulletinfile findById(Integer id);
	 
	 public List<Bulletinfile> findAll();
}  