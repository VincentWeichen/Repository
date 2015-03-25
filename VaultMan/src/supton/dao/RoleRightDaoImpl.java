package supton.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Roleright;

/**
 	* A data access object (DAO) providing persistence and search support for Roleright entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Roleright
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class RoleRightDaoImpl extends BaseDao  implements IRoleRightDao  {
	     private static final Logger log = LoggerFactory.getLogger(RoleRightDaoImpl.class);
		//property constants
	public static final String ROLECODE = "rolecode";
	public static final String OPERATION = "operation";
	public static final String OPERATIONURL = "operationurl";



    
    public void save(Roleright transientInstance) {
        log.debug("saving Roleright instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Roleright persistentInstance) {
        log.debug("deleting Roleright instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Roleright findById( java.lang.Integer id) {
        log.debug("getting Roleright instance with id: " + id);
        try {
            Roleright instance = (Roleright) getSession()
                    .get("supton.Roleright", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Roleright> findByExample(Roleright instance) {
        log.debug("finding Roleright instance by example");
        try {
            List<Roleright> results = (List<Roleright>) getSession()
                    .createCriteria("supton.Roleright")
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
      log.debug("finding Roleright instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Roleright as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Roleright> findByRolecode(Object rolecode
	) {
		return findByProperty(ROLECODE, rolecode
		);
	}
	
	public List<Roleright> findByOperation(Object operation
	) {
		return findByProperty(OPERATION, operation
		);
	}
	
	public List<Roleright> findByOperationurl(Object operationurl
	) {
		return findByProperty(OPERATIONURL, operationurl
		);
	}
	

	public List findAll() {
		log.debug("finding all Roleright instances");
		try {
			String queryString = "from Roleright";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Roleright merge(Roleright detachedInstance) {
        log.debug("merging Roleright instance");
        try {
            Roleright result = (Roleright) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Roleright instance) {
        log.debug("attaching dirty Roleright instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Roleright instance) {
        log.debug("attaching clean Roleright instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}