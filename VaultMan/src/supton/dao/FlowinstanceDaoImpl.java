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
import supton.entity.Flowinstance;

/**
 	* A data access object (DAO) providing persistence and search support for Flowinstance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Flowinstance
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class FlowinstanceDaoImpl  extends BaseDao implements IFlowinstanceDao {
	     private static final Logger log = LoggerFactory.getLogger(FlowinstanceDaoImpl.class);
		//property constants
	public static final String TEMPLATE_ID = "templateId";
	public static final String TEMPLATEXMLSTRING = "templatexmlstring";
	public static final String CREATEUSER = "createuser";
	public static final String STATE = "state";



    
    public void save(Flowinstance transientInstance) {
        log.debug("saving Flowinstance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Flowinstance persistentInstance) {
        log.debug("deleting Flowinstance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Flowinstance findById( java.lang.Integer id) {
        log.debug("getting Flowinstance instance with id: " + id);
        try {
            Flowinstance instance = (Flowinstance) getSession()
                    .get("supton.Flowinstance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Flowinstance> findByExample(Flowinstance instance) {
        log.debug("finding Flowinstance instance by example");
        try {
            List<Flowinstance> results = (List<Flowinstance>) getSession()
                    .createCriteria("supton.Flowinstance")
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
      log.debug("finding Flowinstance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Flowinstance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Flowinstance> findByTemplateId(Object templateId
	) {
		return findByProperty(TEMPLATE_ID, templateId
		);
	}
	
	public List<Flowinstance> findByTemplatexmlstring(Object templatexmlstring
	) {
		return findByProperty(TEMPLATEXMLSTRING, templatexmlstring
		);
	}
	
	public List<Flowinstance> findByCreateuser(Object createuser
	) {
		return findByProperty(CREATEUSER, createuser
		);
	}
	
	public List<Flowinstance> findByState(Object state
	) {
		return findByProperty(STATE, state
		);
	}
	

	public List findAll() {
		log.debug("finding all Flowinstance instances");
		try {
			String queryString = "from Flowinstance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Flowinstance merge(Flowinstance detachedInstance) {
        log.debug("merging Flowinstance instance");
        try {
            Flowinstance result = (Flowinstance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Flowinstance instance) {
        log.debug("attaching dirty Flowinstance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Flowinstance instance) {
        log.debug("attaching clean Flowinstance instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}