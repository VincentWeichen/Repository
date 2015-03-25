package supton.service;

import java.util.List;

import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.Rolefunction;


public interface IRolefunctionService {  
	public void save(Rolefunction transientInstance);
	public void delete(Rolefunction persistentInstance);
    public List<Rolefunction> findByRoles(String roles);
    public List<Rolefunction> findByRolecode(Object rolecode);
    public int deleteByRolecode(String rolecode);
    public List<String> findCodeByRoles(String roles);
}  