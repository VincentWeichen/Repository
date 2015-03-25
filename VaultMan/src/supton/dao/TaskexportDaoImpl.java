package supton.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Taskexport;

/**
 	* A data access object (DAO) providing persistence and search support for Taskexport entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Taskexport
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class TaskexportDaoImpl extends BaseDao implements ITaskexportDao   {
	     private static final Logger log = LoggerFactory.getLogger(TaskexportDaoImpl.class);
		//property constants
	public static final String VERSION = "version";
	public static final String TID = "tid";
	public static final String TYPE = "type";
	public static final String STATUS = "status";
	public static final String TIMES = "times";



    
    public void save(Taskexport transientInstance) {
        log.debug("saving Taskexport instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Taskexport persistentInstance) {
        log.debug("deleting Taskexport instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Taskexport findById( java.lang.Integer id) {
        log.debug("getting Taskexport instance with id: " + id);
        try {
            Taskexport instance = (Taskexport) getSession()
                    .get("supton.Taskexport", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Taskexport> findByExample(Taskexport instance) {
        log.debug("finding Taskexport instance by example");
        try {
            List<Taskexport> results = (List<Taskexport>) getSession()
                    .createCriteria("supton.Taskexport")
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
      log.debug("finding Taskexport instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Taskexport as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Taskexport> findByVersion(Object version
	) {
		return findByProperty(VERSION, version
		);
	}
	
	public List<Taskexport> findByTid(Object tid
	) {
		return findByProperty(TID, tid
		);
	}
	
	public List<Taskexport> findByType(Object type
	) {
		return findByProperty(TYPE, type
		);
	}
	
	public List<Taskexport> findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	
	public List<Taskexport> findByTimes(Object times
	) {
		return findByProperty(TIMES, times
		);
	}
	

	public List findAll() {
		log.debug("finding all Taskexport instances");
		try {
			String queryString = "from Taskexport";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Taskexport merge(Taskexport detachedInstance) {
        log.debug("merging Taskexport instance");
        try {
            Taskexport result = (Taskexport) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Taskexport instance) {
        log.debug("attaching dirty Taskexport instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Taskexport instance) {
        log.debug("attaching clean Taskexport instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}