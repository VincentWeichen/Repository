package supton.service;

import java.util.List;

import supton.entity.Lookupoption;
import supton.entity.Messagelog;
import supton.entity.Organization;
import supton.entity.Roleright;
import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
import supton.entity.Terminal;
import supton.entity.Vaultlog;
import supton.entity.VaultlogDecode;


public interface ITerminalService {  

	 public void save(Terminal transientInstance);
      
	 public void delete(Terminal persistentInstance);
      
	 public Terminal findById(String id);
	 
	 
}  