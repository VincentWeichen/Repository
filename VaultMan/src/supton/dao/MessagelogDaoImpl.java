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
import supton.entity.Messagelog;

/**
 	* A data access object (DAO) providing persistence and search support for Messagelog entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Messagelog
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class MessagelogDaoImpl extends BaseDao implements IMessagelogDao  {
	     private static final Logger log = LoggerFactory.getLogger(MessagelogDaoImpl.class);
		//property constants
	public static final String MESSAGE = "message";
	public static final String CELLPHONE = "cellphone";
	public static final String STATUS = "status";
	public static final String ERRORMSG = "errormsg";



    
    public void save(Messagelog transientInstance) {
        log.debug("saving Messagelog instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Messagelog persistentInstance) {
        log.debug("deleting Messagelog instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Messagelog findById( java.lang.Integer id) {
        log.debug("getting Messagelog instance with id: " + id);
        try {
            Messagelog instance = (Messagelog) getSession()
                    .get("supton.Messagelog", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Messagelog> findByExample(Messagelog instance) {
        log.debug("finding Messagelog instance by example");
        try {
            List<Messagelog> results = (List<Messagelog>) getSession()
                    .createCriteria("supton.Messagelog")
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
      log.debug("finding Messagelog instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Messagelog as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Messagelog> findByMessage(Object message
	) {
		return findByProperty(MESSAGE, message
		);
	}
	
	public List<Messagelog> findByCellphone(Object cellphone
	) {
		return findByProperty(CELLPHONE, cellphone
		);
	}
	
	public List<Messagelog> findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	
	public List<Messagelog> findByErrormsg(Object errormsg
	) {
		return findByProperty(ERRORMSG, errormsg
		);
	}
	

	public List findAll() {
		log.debug("finding all Messagelog instances");
		try {
			String queryString = "from Messagelog";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Messagelog merge(Messagelog detachedInstance) {
        log.debug("merging Messagelog instance");
        try {
            Messagelog result = (Messagelog) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Messagelog instance) {
        log.debug("attaching dirty Messagelog instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Messagelog instance) {
        log.debug("attaching clean Messagelog instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}