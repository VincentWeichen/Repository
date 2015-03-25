package supton.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Fingerprintuser;
import supton.entity.User;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fingerprintuser entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see supton.Fingerprintuser
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FingerprintUserDaoImpl extends BaseDao implements
		IFingerprintUserDao {
	private static final Logger log = LoggerFactory
			.getLogger(FingerprintUserDaoImpl.class);
	// property constants
	public static final String TYPE = "userType";
	public static final String ENCRYKEYAMOUNT = "fingerAmount";
	public static final String USERCODESYS = "usercodesys";
	public static final String TID = "tid";

	public void save(Fingerprintuser transientInstance) {
		try {
			if(transientInstance.getId() == null)
    		{
    			getSession().save(transientInstance);
    		}else
    		{
    			getSession().update(transientInstance);
    		}
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Fingerprintuser persistentInstance) {
		log.debug("deleting Fingerprintuser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Fingerprintuser findById(java.lang.Integer id) {
		log.debug("getting Fingerprintuser instance with id: " + id);
		try {
			Fingerprintuser instance = (Fingerprintuser) getSession().get(
					"supton.Fingerprintuser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Fingerprintuser> findByExample(Fingerprintuser instance) {
		log.debug("finding Fingerprintuser instance by example");
		try {
			List<Fingerprintuser> results = (List<Fingerprintuser>) getSession()
					.createCriteria("supton.Fingerprintuser")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public Fingerprintuser getFingerprintuserByTUserCode(String tusercode,String tid) {
		try {
			String queryString = "from Fingerprintuser where tusercode ='" + tusercode +"' and tid = '"+tid+"'";
			Query fingerprintuser = getSession().createQuery(queryString);
			return (Fingerprintuser) fingerprintuser.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Fingerprintuser instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Fingerprintuser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Fingerprintuser> findByTID(String tid) {
		
		String organizationqueryString = "from Fingerprintuser where tid ='" + tid+"'";
		List<Fingerprintuser> fingerprintuserList = getSession().createQuery(organizationqueryString).list();
		return fingerprintuserList;
	}
	
	public List<Fingerprintuser> findAllByTId(String tid) {
		try {
			String queryString = "from Fingerprintuser where tid ='" + tid +"'";
			List<Fingerprintuser> fingerprintuserList = getSession().createQuery(queryString).list();
			return fingerprintuserList;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Fingerprintuser> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<Fingerprintuser> findByEncrykeyamount(Object encrykeyamount) {
		return findByProperty(ENCRYKEYAMOUNT, encrykeyamount);
	}

	public List<Fingerprintuser> findByUsercodesys(Object usercodesys) {
		return findByProperty(USERCODESYS, usercodesys);
	}

	public List findAll() {
		log.debug("finding all Fingerprintuser instances");
		try {
			String queryString = "from Fingerprintuser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Fingerprintuser merge(Fingerprintuser detachedInstance) {
		log.debug("merging Fingerprintuser instance");
		try {
			Fingerprintuser result = (Fingerprintuser) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Fingerprintuser instance) {
		log.debug("attaching dirty Fingerprintuser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Fingerprintuser instance) {
		log.debug("attaching clean Fingerprintuser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}