package supton.dao;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Rights;

/**
 * A data access object (DAO) providing persistence and search support for
 * Rights entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see supton.Rights
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RightsDaoImpl extends BaseDao implements IRightsDao {
	private static final Logger log = LoggerFactory.getLogger(RightsDaoImpl.class);
	// property constants
	public static final String OPERATION = "operation";
	public static final String OPERATIONURL = "operationurl";

	public void save(Rights transientInstance) {
		log.debug("saving Rights instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Rights persistentInstance) {
		log.debug("deleting Rights instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Rights findById(java.lang.Integer id) {
		log.debug("getting Rights instance with id: " + id);
		try {
			Rights instance = (Rights) getSession().get("supton.Rights", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Rights> findAll() {
		try {
			String queryString = "from Rights";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
}

//	public List<Rights> findByExample(Rights instance) {
//		log.debug("finding Rights instance by example");
//		try {
//			List<Rights> results = (List<Rights>) getSession()
//					.createCriteria("supton.Rights").add(create(instance))
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
//	public List findByProperty(String propertyName, Object value) {
//		log.debug("finding Rights instance with property: " + propertyName
//				+ ", value: " + value);
//		try {
//			String queryString = "from Rights as model where model."
//					+ propertyName + "= ?";
//			Query queryObject = getSession().createQuery(queryString);
//			queryObject.setParameter(0, value);
//			return queryObject.list();
//		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
//			throw re;
//		}
//	}
//
//	public List<Rights> findByOperation(Object operation) {
//		return findByProperty(OPERATION, operation);
//	}
//
//	public List<Rights> findByOperationurl(Object operationurl) {
//		return findByProperty(OPERATIONURL, operationurl);
//	}
//
//	public List findAll() {
//		log.debug("finding all Rights instances");
//		try {
//			String queryString = "from Rights";
//			Query queryObject = getSession().createQuery(queryString);
//			return queryObject.list();
//		} catch (RuntimeException re) {
//			log.error("find all failed", re);
//			throw re;
//		}
//	}
//
//	public Rights merge(Rights detachedInstance) {
//		log.debug("merging Rights instance");
//		try {
//			Rights result = (Rights) getSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}
//
//	public void attachDirty(Rights instance) {
//		log.debug("attaching dirty Rights instance");
//		try {
//			getSession().saveOrUpdate(instance);
//			log.debug("attach successful");
//		} catch (RuntimeException re) {
//			log.error("attach failed", re);
//			throw re;
//		}
//	}
//
//	public void attachClean(Rights instance) {
//		log.debug("attaching clean Rights instance");
//		try {
//			getSession().lock(instance, LockMode.NONE);
//			log.debug("attach successful");
//		} catch (RuntimeException re) {
//			log.error("attach failed", re);
//			throw re;
//		}
//	}
}