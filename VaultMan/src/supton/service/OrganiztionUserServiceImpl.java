package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.IOrganizationUserDao;
import supton.entity.OrganizationUser;
import supton.entity.PageInfo;
import supton.entity.pseudo.UserInfomation;

@Service
public class OrganiztionUserServiceImpl implements IOrganiztionUserService {

	@Autowired
	private IOrganizationUserDao organizationUserDao;

	public void save(OrganizationUser transientInstance)  {
		organizationUserDao.save(transientInstance);
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public void delete(OrganizationUser persistentInstance) {
		organizationUserDao.delete(persistentInstance);
	}

	/**
	 * 添加数据
	 * 
	 * @param OrganizationUser
	 */
	public OrganizationUser findById(java.lang.Integer id) {
		return organizationUserDao.findById(id);
	}
	
	public List<OrganizationUser> findAll() {
		return organizationUserDao.findAll();
	}

	public UserInfomation getUserInfomationByOrCode(String organizationCode)
	{
		return organizationUserDao.getUserInfomationByOrCode(organizationCode);
	}
	
	public int deleteOrganizationUser(String userCode)
	{
		return organizationUserDao.deleteOrganizationUser(userCode);
	}
	
	public List<OrganizationUser> findByUsercode(String userCode)
	{
		return organizationUserDao.findByUsercode(userCode);
	}
	
	public PageInfo PagedQuery(String organizationCode, int pageNo, int pageSize)
	{
		return organizationUserDao.PagedQuery(organizationCode,pageNo,pageSize);
	}
}