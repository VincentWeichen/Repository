package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.ISubgroupDao;
import supton.entity.PageInfo;
import supton.entity.Subgroup;

@Service
public class SubgroupServiceImpl implements ISubgroupService {

	@Autowired
	private ISubgroupDao subgroupDao;

	public void save(Subgroup transientInstance) {
		subgroupDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Subgroup persistentInstance) {
		subgroupDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param Subgroup
	 */
	public Subgroup findById(Integer id) {
		return subgroupDao.findById(id);
	}
	
	public PageInfo PagedQuery(String organizationCode, int pageNo, int pageSize)
	{
		return subgroupDao.PagedQuery(organizationCode, pageNo,pageSize);
	}
	 
	public int deleteGroup(String code, String corganizationCode)
	{
		return subgroupDao.deleteGroup(code,corganizationCode);
	}
	
	public List<Subgroup> findByCode(String code)
	{
		return subgroupDao.findByCode(code);
	}
	
	public int deleteByOrganizationcode(String organizationcode)
	{
		return subgroupDao.deleteByOrganizationcode(organizationcode);
	}
	
	public List<Subgroup> findByOrganizationcode(String organizationcode)
	{
		return subgroupDao.findByOrganizationcode(organizationcode);
	}
	public List<Subgroup> GetGroupListBytype(String organizationCode,String type)
	{
		return subgroupDao.GetGroupListBytype(organizationCode,type);
	}
	
	public Subgroup GetGroupByAuthorize(String organizationCode,String type,String groupauthorize)
	{
		return subgroupDao.GetGroupByAuthorize(organizationCode,type,groupauthorize);
	}
}