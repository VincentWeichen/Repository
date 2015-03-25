package supton.service;

import java.util.List;

import supton.entity.Organization;


public interface IOrganiztionService {  
	public void save(Organization transientInstance);
	public void delete(Organization persistentInstance);
	public Organization findById(java.lang.Integer id);
	public List<Organization> findAll();
	public List<Organization> findByParentcode(Object parentcode);
	public List<Organization> getOrganizationListByCode(String organizationCode);
	public Organization getOrganizationByCode(String code);
	public Organization getOrganizationById(int id);
	public void SaveOrganization(Organization organization);
	public boolean deleteOrganization(String organizationCode);
	public List<Organization> findByTid(String tid);
}  