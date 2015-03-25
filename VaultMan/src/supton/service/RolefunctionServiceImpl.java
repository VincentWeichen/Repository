package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IRoleDao;
import supton.dao.IRolefunctionDao;
import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.Rolefunction;

@Service
public class RolefunctionServiceImpl implements IRolefunctionService {

	@Autowired
	private IRolefunctionDao rolefunctiondao;

	public void save(Rolefunction transientInstance) {
		rolefunctiondao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Rolefunction persistentInstance) {
		rolefunctiondao.delete(persistentInstance);
	}
	
    public List<Rolefunction> findByRoles(String roles){
    	return rolefunctiondao.findByRoles(roles);
    }
    
    public List<Rolefunction> findByRolecode(Object rolecode)
    {
    	return rolefunctiondao.findByRolecode(rolecode);
    }

    public int deleteByRolecode(String rolecode)
    {
    	return rolefunctiondao.deleteByRolecode(rolecode);
    }
    
    public List<String> findCodeByRoles(String roles)
    {
    	return rolefunctiondao.findCodeByRoles(roles);
    }
}