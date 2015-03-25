package supton.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Role;
import supton.entity.RoleUser;
import supton.entity.User;

@Repository
public class RoleUserDaoImpl extends BaseDao  implements IRoleUserDao {
	private static final Logger log = LoggerFactory
			.getLogger(RoleUserDaoImpl.class);
	// property constants
	public static final String ROLECODE = "rolecode";
	public static final String USERCODE = "usercode";

	public void save(RoleUser transientInstance) {
		log.debug("saving RoleUser instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RoleUser persistentInstance) {
		log.debug("deleting RoleUser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void deleteByUserCode(String userCode) {
		log.debug("deleting RoleUser By usercode");
		try {
			Query query = getSession().createSQLQuery(
					"DELETE FROM roleuser where usercode = '" + userCode + "'");
			query.executeUpdate();
			log.debug("delete successful");
        } catch (RuntimeException re) {
        	log.error("delete failed", re);
            throw re;
        }
	}

	public RoleUser findById(java.lang.Integer id) {
		log.debug("getting RoleUser instance with id: " + id);
		try {
			RoleUser instance = (RoleUser) getSession().get("supton.Roleuser",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
//	public List findByProperty(String propertyName, Object value) {
//		log.debug("finding Roleuser instance with property: " + propertyName
//				+ ", value: " + value);
//		try {
//			String queryString = "from Roleuser as model where model."
//					+ propertyName + "= ?";
//			Query queryObject = getSession().createQuery(queryString);
//			queryObject.setParameter(0, value);
//			return queryObject.list();
//		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
//			throw re;
//		}
//	}

	public List<RoleUser> findAll() {
		log.debug("finding all RoleUser instances");
		try {
			String queryString = "from RoleUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<RoleUser> findByUsercode(String usercode) {
	return findByProperty(USERCODE, usercode);
}
	public List<Role> getRoleByUsercode(String userCode) {
		String rolequeryString = "select O from RoleUser R, Role O where R.rolecode = O.code and R.usercode ='" + userCode+"'";
		List<Role> roleList = getSession().createQuery(rolequeryString).list();
		return roleList;
	}
//	public List<RoleUser> findByExample(RoleUser instance) {
//		log.debug("finding Roleuser instance by example");
//		try {
//			List<RoleUser> results = (List<RoleUser>) getSession()
//					.createCriteria("supton.Roleuser").add(create(instance))
//					.list();
//			log.debug("find by example successful, result size: "
//					+ results.size());
//			return results;
//		} catch (RuntimeException re) {
//			log.error("find by example failed", re);
//			throw re;
//		}
//	}
//
	public List<RoleUser> findByProperty(String propertyName, Object value) {
		log.debug("finding Roleuser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RoleUser as model where model."
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
//	public List<RoleUser> findByRolecode(Object rolecode) {
//		return findByProperty(ROLECODE, rolecode);
//	}
//

//

//
//	public RoleUser merge(RoleUser detachedInstance) {
//		log.debug("merging Roleuser instance");
//		try {
//			RoleUser result = (RoleUser) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}
//
//	public void attachDirty(RoleUser instance) {
//		log.debug("attaching dirty Roleuser instance");
//		try {
//			getSession().saveOrUpdate(instance);
//			log.debug("attach successful");
//		} catch (RuntimeException re) {
//			log.error("attach failed", re);
//			throw re;
//		}
//	}
//
//	public void attachClean(RoleUser instance) {
//		log.debug("attaching clean Roleuser instance");
//		try {
//			getSession().lock(instance, LockMode.NONE);
//			log.debug("attach successful");
//		} catch (RuntimeException re) {
//			log.error("attach failed", re);
//			throw re;
//		}
//	}
}