package supton.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Bulletinfile;

/**
 	* A data access object (DAO) providing persistence and search support for Bulletinfile entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Bulletinfile
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class BulletinfileDaoImpl extends BaseDao implements IBulletinfileDao  {
	     private static final Logger log = LoggerFactory.getLogger(BulletinfileDaoImpl.class);
		//property constants
	public static final String BULLETINSN = "bulletinsn";
	public static final String FILEPATH = "filepath";



    
    public void save(Bulletinfile transientInstance) {
        log.debug("saving Bulletinfile instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Bulletinfile persistentInstance) {
        log.debug("deleting Bulletinfile instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Bulletinfile findById( java.lang.Integer id) {
        log.debug("getting Bulletinfile instance with id: " + id);
        try {
            Bulletinfile instance = (Bulletinfile) getSession()
                    .get("supton.entity.Bulletinfile", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Bulletinfile> findByExample(Bulletinfile instance) {
        log.debug("finding Bulletinfile instance by example");
        try {
            List<Bulletinfile> results = (List<Bulletinfile>) getSession()
                    .createCriteria("supton.Bulletinfile")
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
      log.debug("finding Bulletinfile instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Bulletinfile as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Bulletinfile> findByBulletinsn(Object bulletinsn
	) {
		return findByProperty(BULLETINSN, bulletinsn
		);
	}
	
	public List<Bulletinfile> findByFilepath(Object filepath
	) {
		return findByProperty(FILEPATH, filepath
		);
	}
	

	public List<Bulletinfile> findAll() {
		log.debug("finding all Bulletinfile instances");
		try {
			String queryString = "from Bulletinfile";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Bulletinfile merge(Bulletinfile detachedInstance) {
        log.debug("merging Bulletinfile instance");
        try {
            Bulletinfile result = (Bulletinfile) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Bulletinfile instance) {
        log.debug("attaching dirty Bulletinfile instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Bulletinfile instance) {
        log.debug("attaching clean Bulletinfile instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}