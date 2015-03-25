package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IOrganizationDao;
import supton.entity.Organization;

@Service
public class OrganiztionServiceImpl implements IOrganiztionService {

	@Autowired
	private IOrganizationDao organizationDao;

	public void save(Organization transientInstance)  {
		organizationDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(Organization persistentInstance) {
		organizationDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param Organization
	 */
	public Organization findById(java.lang.Integer id) {
		return organizationDao.findById(id);
	}
	
	public List<Organization> findAll() {
		return organizationDao.findAll();
	}
	
	public List<Organization> findByParentcode(Object parentcode)
	{
		return organizationDao.findByParentcode(parentcode);	
	}
	
	public List<Organization> getOrganizationListByCode(String organizationCode)
	{
		return organizationDao.getOrganizationListByCode(organizationCode);
	}
	
	public Organization getOrganizationByCode(String code)
	{
		return organizationDao.getOrganizationByCode(code);
	}
	
	public Organization getOrganizationById(int id)
	{
		return organizationDao.getOrganizationById(id);
	}
	
	public void SaveOrganization(Organization organization)
	{
		organizationDao.SaveOrganization(organization);
	}

	public boolean deleteOrganization(String organizationCode)
	{
		return organizationDao.deleteOrganization(organizationCode);
	}
	
	public List<Organization> findByTid(String tid)
	{
		return organizationDao.findByTid(tid);
	}
}