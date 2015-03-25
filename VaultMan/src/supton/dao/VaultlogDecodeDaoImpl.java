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
import supton.entity.VaultlogDecode;

/**
 	* A data access object (DAO) providing persistence and search support for VaultlogDecode entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.VaultlogDecode
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class VaultlogDecodeDaoImpl extends BaseDao implements IVaultlogDecodeDao {
	     private static final Logger log = LoggerFactory.getLogger(VaultlogDecodeDaoImpl.class);
		//property constants
	public static final String OPERATION = "operation";
	public static final String USERCODE = "usercode";



    
	public void save(VaultlogDecode transientInstance) {
		log.debug("saving VaultlogDecode instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(VaultlogDecode persistentInstance) {
		log.debug("deleting VaultlogDecode instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public List<VaultlogDecode> findByUsercode(String usercode) {
		return findByProperty(USERCODE, usercode);
	}

	public List<VaultlogDecode> findAll() {
		log.debug("finding all VaultlogDecode instances");
		try {
			String queryString = "from VaultlogDecode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<VaultlogDecode> findByProperty(String propertyName, Object value) {
		log.debug("finding VaultlogDecode instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VaultlogDecode as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
    public VaultlogDecode findById( java.lang.Integer id) {
        log.debug("getting VaultlogDecode instance with id: " + id);
        try {
            VaultlogDecode instance = (VaultlogDecode) getSession()
                    .get("supton.VaultlogDecode", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
//    public List<VaultlogDecode> findByExample(VaultlogDecode instance) {
//        log.debug("finding VaultlogDecode instance by example");
//        try {
//            List<VaultlogDecode> results = (List<VaultlogDecode>) getSession()
//                    .createCriteria("supton.VaultlogDecode")
//                    .add( create(instance) )
//            .list();
//            log.debug("find by example successful, result size: " + results.size());
//            return results;
//        } catch (RuntimeException re) {
//            log.error("find by example failed", re);
//            throw re;
//        }
//    }    
    
    

//	public List<VaultlogDecode> findByOperation(Object operation
//	) {
//		return findByProperty(OPERATION, operation
//		);
//	}
	
	
	
//    public VaultlogDecode merge(VaultlogDecode detachedInstance) {
//        log.debug("merging VaultlogDecode instance");
//        try {
//            VaultlogDecode result = (VaultlogDecode) getSession()
//                    .merge(detachedInstance);
//            log.debug("merge successful");
//            return result;
//        } catch (RuntimeException re) {
//            log.error("merge failed", re);
//            throw re;
//        }
//    }
//
//    public void attachDirty(VaultlogDecode instance) {
//        log.debug("attaching dirty VaultlogDecode instance");
//        try {
//            getSession().saveOrUpdate(instance);
//            log.debug("attach successful");
//        } catch (RuntimeException re) {
//            log.error("attach failed", re);
//            throw re;
//        }
//    }
//    
//    public void attachClean(VaultlogDecode instance) {
//        log.debug("attaching clean VaultlogDecode instance");
//        try {
//            getSession().lock(instance, LockMode.NONE);
//            log.debug("attach successful");
//        } catch (RuntimeException re) {
//            log.error("attach failed", re);
//            throw re;
//        }
//    }
}