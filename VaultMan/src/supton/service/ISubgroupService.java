package supton.service;

import java.util.List;

import supton.entity.Organization;
import supton.entity.PageInfo;
import supton.entity.Subgroup;
import supton.entity.VaultlogDecode;


public interface ISubgroupService {  

	 public void save(Subgroup transientInstance);
      
	 public void delete(Subgroup persistentInstance);
      
	 public Subgroup findById(Integer id);
	 
	 public PageInfo PagedQuery(String organizationCode, int pageNo, int pageSize);
	 
	 public int deleteGroup(String code, String corganizationCode);
	 
	 public List<Subgroup> findByCode(String code);
	 
	 public int deleteByOrganizationcode(String organizationcode);
	 
	 public List<Subgroup> findByOrganizationcode(String organizationcode);
	 
	 public List<Subgroup> GetGroupListBytype(String organizationCode,String type);
	 
	 public Subgroup GetGroupByAuthorize(String organizationCode,String type,String groupauthorize);
}  