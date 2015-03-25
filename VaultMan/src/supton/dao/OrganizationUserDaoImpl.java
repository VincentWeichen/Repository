package supton.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Fingerprintuser;
import supton.entity.Organization;
import supton.entity.OrganizationUser;
import supton.entity.PageInfo;
import supton.entity.User;
import supton.entity.pseudo.UserInfomation;

/**
 * A data access object (DAO) providing persistence and search support for
 * Organizationuser entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see supton.OrganizationUser
 * @author MyEclipse Persistence Tools
 */
@Repository
public class OrganizationUserDaoImpl extends BaseDao  implements IOrganizationUserDao {
	private static final Logger log = LoggerFactory
			.getLogger(OrganizationUserDaoImpl.class);
	// property constants
	public static final String ORGANIZATIONCODE = "organizationcode";
	public static final String USERCODE = "usercode";

	public void save(OrganizationUser transientInstance) {
		log.debug("saving OrganizationUser instance");
		try {
			if(transientInstance.getSn() == null)
			{
				getSession().save(transientInstance);
			}else
			{
				getSession().update(transientInstance);
			}
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OrganizationUser persistentInstance) {
		log.debug("deleting OrganizationUser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OrganizationUser findById(java.lang.Integer id) {
		log.debug("getting Organizationuser instance with id: " + id);
		try {
			OrganizationUser instance = (OrganizationUser) getSession().get(
					"supton.OrganizationUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public List<OrganizationUser> findAll() {
		log.debug("finding all OrganizationUser instances");
		try {
			String queryString = "from OrganizationUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public UserInfomation getUserInfomationByOrCode(String organizationCode) {
		try {
			String userqueryString = "select U from User U, OrganizationUser O where U.code = O.usercode and O.organizationcode ='" + organizationCode+"'";
			String organizationqueryString = "select O from Organization O where O.code ='" + organizationCode+"'";
			List<User> userList = getSession().createQuery(userqueryString).list();
			Organization organization = (Organization)getSession().createQuery(organizationqueryString).uniqueResult();
			UserInfomation  userInfomation = new UserInfomation();
			userInfomation.setOrganization(organization);
			userInfomation.setUserList(userList); 
			userInfomation.setCountTotal(userList.size());
			return userInfomation;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public PageInfo PagedQuery(String organizationCode, int pageNo, int pageSize) {
		try {
			String hql = "select U from User U, OrganizationUser O where U.code = O.usercode and O.organizationcode ='" + organizationCode+"'";
			List<User> userList = getSession().createQuery(hql).list();
			PageInfo pageInfo = pagedQuery(hql,pageNo,pageSize);
			return pageInfo;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	public int deleteOrganizationUser(String userCode) {
		try {
			Query query = getSession().createSQLQuery(
					"DELETE  FROM OrganizationUser where usercode = '" + userCode + "'");
			return query.executeUpdate();
        } catch (RuntimeException re) {

            throw re;
        }
	}
//	public List<Organizationuser> findByExample(Organizationuser instance) {
//		log.debug("finding Organizationuser instance by example");
//		try {
//			List<Organizationuser> results = (List<Organizationuser>) getSession()
//					.createCriteria("supton.Organizationuser")
//					.add(create(instance)).list();
//			log.debug("find by example successful, result size: "
//					+ results.size());
//			return results;
//		} catch (RuntimeException re) {
//			log.error("find by example failed", re);
//			throw re;
//		}
//	}
//
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Organizationuser instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OrganizationUser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
//
//	public List<Organizationuser> findByOrganizationcode(Object organizationcode) {
//		return findByProperty(ORGANIZATIONCODE, organizationcode);
//	}
//
	public List<OrganizationUser> findByUsercode(String usercode) {
		return findByProperty("usercode", usercode);
	}
//
//	public List findAll() {
//		log.debug("finding all Organizationuser instances");
//		try {
//			String queryString = "from Organizationuser";
//			Query queryObject = getSession().createQuery(queryString);
//			return queryObject.list();
//		} catch (RuntimeException re) {
//			log.error("find all failed", re);
//			throw re;
//		}
//	}
//
//	public Organizationuser merge(Organizationuser detachedInstance) {
//		log.debug("merging Organizationuser instance");
//		try {
//			Organizationuser result = (Organizationuser) getSession().merge(
//					detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}
//
//	public void attachDirty(Organizationuser instance) {
//		log.debug("attaching dirty Organizationuser instance");
//		try {
//			getSession().saveOrUpdate(instance);
//			log.debug("attach successful");
//		} catch (RuntimeException re) {
//			log.error("attach failed", re);
//			throw re;
//		}
//	}
//
//	public void attachClean(Organizationuser instance) {
//		log.debug("attaching clean Organizationuser instance");
//		try {
//			getSession().lock(instance, LockMode.NONE);
//			log.debug("attach successful");
//		} catch (RuntimeException re) {
//			log.error("attach failed", re);
//			throw re;
//		}
//	}
}