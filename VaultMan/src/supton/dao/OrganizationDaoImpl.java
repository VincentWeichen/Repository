package supton.dao;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Organization;

/**
 	* A data access object (DAO) providing persistence and search support for Organization entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Organization
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class OrganizationDaoImpl extends BaseDao implements IOrganizationDao{
	private static final Logger log = LoggerFactory
			.getLogger(OrganizationDaoImpl.class);
	// property constants
	public static final String CODE = "code";
	public static final String PARENTCODE = "parentcode";
	public static final String TYPE = "type";
	public static final String NAME = "name";
	public static final String ALIAS = "alias";
	public static final String ADDRESS = "address";
	public static final String TELEPHONE = "telephone";
	public static final String MANAGER = "manager";
	public static final String CELLPHONE = "cellphone";
	public static final String TID = "tid";

	public void save(Organization transientInstance) {
		log.debug("saving Organization instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Organization persistentInstance) {
		log.debug("deleting Organization instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Organization findById(java.lang.Integer id) {
		log.debug("getting Organization instance with id: " + id);
		try {
			Organization instance = (Organization) getSession().get(
					"supton.Organization", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Organization> findAll() {
		log.debug("finding all Organization instances");
		try {
			String queryString = "from Organization";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
    } 
//	public List<Organization> findByExample(Organization instance) {
//		log.debug("finding Organization instance by example");
//		try {
//			List<Organization> results = (List<Organization>) getSession()
//					.createCriteria("supton.Organization")
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
	public List<Organization> findByProperty(String propertyName, Object value) {
		log.debug("finding Organization instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Organization as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Organization> findByParentcode(Object parentcode) {
		return findByProperty(PARENTCODE, parentcode);
	}

	public List<Organization> getOrganizationListByCode(String organizationCode) {
		try {
			String queryString = "from Organization where parentcode ='"
					+ organizationCode + "'";
			List<Organization> organizationList = getSession().createQuery(
					queryString).list();
			return organizationList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	// 通过code获取机构信息
	public Organization getOrganizationByCode(String code) {
		try {
			String queryString = "from Organization where code = '" + code
					+ "'";
			Query query = getSession().createQuery(queryString);
			return (Organization) query.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Organization getOrganizationById(int id) {
		try {
			String queryString = "from Organization where id = " + id;
			Query query = getSession().createQuery(queryString);
			return (Organization) query.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void SaveOrganization(Organization organization) {
		try {
			if (organization.getId() == null) {
				getSession().save(organization);
			} else {

				getSession().update(organization);
			}

		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public boolean deleteOrganization(String organizationCode) {
		boolean returnVal = true;

		try {
			Query query = getSession().createSQLQuery(
					"DELETE FROM Organization where code = '"
							+ organizationCode + "'");
			query.executeUpdate();
		} catch (RuntimeException re) {
			returnVal = false;
			// throw re;
		}

		return returnVal;
	}
	
	public List<Organization> findByTid(String tid) {
		try {
			String queryString = "from Organization where tid = " + tid;
			Query query = getSession().createQuery(queryString);
			return query.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
//
//	public List<Organization> findByCode(Object code) {
//		return findByProperty(CODE, code);
//	}
//
//	public List<Organization> findByParentcode(Object parentcode) {
//		return findByProperty(PARENTCODE, parentcode);
//	}
//
//	public List<Organization> findByType(Object type) {
//		return findByProperty(TYPE, type);
//	}
//
//	public List<Organization> findByName(Object name) {
//		return findByProperty(NAME, name);
//	}
//
//	public List<Organization> findByAlias(Object alias) {
//		return findByProperty(ALIAS, alias);
//	}
//
//	public List<Organization> findByAddress(Object address) {
//		return findByProperty(ADDRESS, address);
//	}
//
//	public List<Organization> findByTelephone(Object telephone) {
//		return findByProperty(TELEPHONE, telephone);
//	}
//
//	public List<Organization> findByManager(Object manager) {
//		return findByProperty(MANAGER, manager);
//	}
//
//	public List<Organization> findByCellphone(Object cellphone) {
//		return findByProperty(CELLPHONE, cellphone);
//	}
//
//	public List<Organization> findByTerminalcode(Object terminalcode) {
//		return findByProperty(TERMINALCODE, terminalcode);
//	}
//

//
//	public Organization merge(Organization detachedInstance) {
//		log.debug("merging Organization instance");
//		try {
//			Organization result = (Organization) getSession().merge(
//					detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}
//
//	public void attachDirty(Organization instance) {
//		log.debug("attaching dirty Organization instance");
//		try {
//			getSession().saveOrUpdate(instance);
//			log.debug("attach successful");
//		} catch (RuntimeException re) {
//			log.error("attach failed", re);
//			throw re;
//		}
//	}
//
//	public void attachClean(Organization instance) {
//		log.debug("attaching clean Organization instance");
//		try {
//			getSession().lock(instance, LockMode.NONE);
//			log.debug("attach successful");
//		} catch (RuntimeException re) {
//			log.error("attach failed", re);
//			throw re;
//		}
//	}
}