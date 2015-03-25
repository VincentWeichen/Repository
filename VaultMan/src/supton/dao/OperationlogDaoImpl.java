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
import supton.entity.Operationlog;

/**
 	* A data access object (DAO) providing persistence and search support for Operationlog entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Operationlog
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class OperationlogDaoImpl extends BaseDao implements IOperationlogDao  {
	     private static final Logger log = LoggerFactory.getLogger(OperationlogDaoImpl.class);
		//property constants
	public static final String OPERATION = "operation";
	public static final String USERCODE = "usercode";



    
    public void save(Operationlog transientInstance) {
        log.debug("saving Operationlog instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Operationlog persistentInstance) {
        log.debug("deleting Operationlog instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Operationlog findById( java.lang.Integer id) {
        log.debug("getting Operationlog instance with id: " + id);
        try {
            Operationlog instance = (Operationlog) getSession()
                    .get("supton.Operationlog", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Operationlog> findByExample(Operationlog instance) {
        log.debug("finding Operationlog instance by example");
        try {
            List<Operationlog> results = (List<Operationlog>) getSession()
                    .createCriteria("supton.Operationlog")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Operationlog instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Operationlog as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Operationlog> findByOperation(Object operation
	) {
		return findByProperty(OPERATION, operation
		);
	}
	
	public List<Operationlog> findByUsercode(Object usercode
	) {
		return findByProperty(USERCODE, usercode
		);
	}
	

	public List findAll() {
		log.debug("finding all Operationlog instances");
		try {
			String queryString = "from Operationlog";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Operationlog merge(Operationlog detachedInstance) {
        log.debug("merging Operationlog instance");
        try {
            Operationlog result = (Operationlog) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Operationlog instance) {
        log.debug("attaching dirty Operationlog instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Operationlog instance) {
        log.debug("attaching clean Operationlog instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}