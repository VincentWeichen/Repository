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
import supton.entity.Bulletin;

/**
 	* A data access object (DAO) providing persistence and search support for Bulletin entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Bulletin
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class BulletinDaoImpl extends BaseDao implements IBulletinDao {
	     private static final Logger log = LoggerFactory.getLogger(BulletinDaoImpl.class);
		//property constants
	public static final String TITLE = "title";
	public static final String CONTENT = "content";



    
    public void save(Bulletin transientInstance) {
    	if(transientInstance.getSn() == null)
		{
			getSession().save(transientInstance);
		}else
		{
			getSession().update(transientInstance);
		}	
    }
    
	public void delete(Bulletin persistentInstance) {
        log.debug("deleting Bulletin instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Bulletin findById( java.lang.Integer id) {
        log.debug("getting Bulletin instance with id: " + id);
        try {
            Bulletin instance = (Bulletin) getSession()
                    .get("supton.Bulletin", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Bulletin> findByExample(Bulletin instance) {
        log.debug("finding Bulletin instance by example");
        try {
            List<Bulletin> results = (List<Bulletin>) getSession()
                    .createCriteria("supton.Bulletin")
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
      log.debug("finding Bulletin instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Bulletin as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Bulletin> findByTitle(Object title
	) {
		return findByProperty(TITLE, title
		);
	}
	
	public List<Bulletin> findByContent(Object content
	) {
		return findByProperty(CONTENT, content
		);
	}
	

	public List findAll() {
		log.debug("finding all Bulletin instances");
		try {
			String queryString = "from Bulletin";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Bulletin merge(Bulletin detachedInstance) {
        log.debug("merging Bulletin instance");
        try {
            Bulletin result = (Bulletin) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Bulletin instance) {
        log.debug("attaching dirty Bulletin instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Bulletin instance) {
        log.debug("attaching clean Bulletin instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}