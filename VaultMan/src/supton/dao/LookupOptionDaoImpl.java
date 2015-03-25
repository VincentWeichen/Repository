package supton.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Lookupoption;

/**
 	* A data access object (DAO) providing persistence and search support for Lookupoption entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Lookupoption
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class LookupOptionDaoImpl extends BaseDao implements ILookupOptionDao {
	     private static final Logger log = LoggerFactory.getLogger(LookupOptionDaoImpl.class);
		//property constants
	public static final String CLASS_ = "class_";
	public static final String SN = "sn";
	public static final String OPTION = "option";
	public static final String OPTIONVALUE = "optionvalue";



    
    public void save(Lookupoption transientInstance) {
        log.debug("saving Lookupoption instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Lookupoption persistentInstance) {
        log.debug("deleting Lookupoption instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Lookupoption findById( java.lang.Integer id) {
        log.debug("getting Lookupoption instance with id: " + id);
        try {
            Lookupoption instance = (Lookupoption) getSession()
                    .get("supton.Lookupoption", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Lookupoption> findByExample(Lookupoption instance) {
        log.debug("finding Lookupoption instance by example");
        try {
            List<Lookupoption> results = (List<Lookupoption>) getSession()
                    .createCriteria("supton.Lookupoption")
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
      log.debug("finding Lookupoption instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Lookupoption as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Lookupoption> findByClass_(int class_) {
		return findByProperty(CLASS_, class_
		);
	}
	
	public List<Lookupoption> findBySn(Object sn
	) {
		return findByProperty(SN, sn
		);
	}
	
	public List<Lookupoption> findByOption(Object option
	) {
		return findByProperty(OPTION, option
		);
	}
	
	public List<Lookupoption> findByOptionvalue(Object optionvalue
	) {
		return findByProperty(OPTIONVALUE, optionvalue
		);
	}
	

	public List findAll() {
		log.debug("finding all Lookupoption instances");
		try {
			String queryString = "from Lookupoption";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Lookupoption merge(Lookupoption detachedInstance) {
        log.debug("merging Lookupoption instance");
        try {
            Lookupoption result = (Lookupoption) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Lookupoption instance) {
        log.debug("attaching dirty Lookupoption instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Lookupoption instance) {
        log.debug("attaching clean Lookupoption instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}