package supton.service;

import java.util.List;

import supton.entity.OrganizationUser;
import supton.entity.PageInfo;
import supton.entity.pseudo.UserInfomation;


public interface IOrganiztionUserService {  
	public void save(OrganizationUser transientInstance);
	public void delete(OrganizationUser persistentInstance);
	public OrganizationUser findById(java.lang.Integer id);
	public List<OrganizationUser> findAll();
	public UserInfomation getUserInfomationByOrCode(String organizationCode);
	public int deleteOrganizationUser(String userCode);
	public List<OrganizationUser> findByUsercode(String usercode);
	public PageInfo PagedQuery(String organizationCode, int pageNo, int pageSize);
}  