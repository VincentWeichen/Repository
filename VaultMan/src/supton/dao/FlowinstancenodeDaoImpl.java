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
import supton.entity.Flowinstancenode;

/**
 	* A data access object (DAO) providing persistence and search support for Flowinstancenode entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Flowinstancenode
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class FlowinstancenodeDaoImpl  extends BaseDao implements IFlowinstancenodeDao {
	     private static final Logger log = LoggerFactory.getLogger(FlowinstancenodeDaoImpl.class);
		//property constants
	public static final String PARENTSTATE = "parentstate";
	public static final String INSTANCEID = "instanceid";
	public static final String STATEID = "stateid";
	public static final String TRANSACTUSER = "transactuser";
	public static final String STATE = "state";
	public static final String SUBMITUSER = "submituser";
	public static final String SUBMITOPTION = "submitoption";
	public static final String SUBMITRESULT = "submitresult";
	public static final String NEXTUSER = "nextuser";



    
    public void save(Flowinstancenode transientInstance) {
        log.debug("saving Flowinstancenode instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Flowinstancenode persistentInstance) {
        log.debug("deleting Flowinstancenode instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Flowinstancenode findById( java.lang.Integer id) {
        log.debug("getting Flowinstancenode instance with id: " + id);
        try {
            Flowinstancenode instance = (Flowinstancenode) getSession()
                    .get("supton.Flowinstancenode", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Flowinstancenode> findByExample(Flowinstancenode instance) {
        log.debug("finding Flowinstancenode instance by example");
        try {
            List<Flowinstancenode> results = (List<Flowinstancenode>) getSession()
                    .createCriteria("supton.Flowinstancenode")
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
      log.debug("finding Flowinstancenode instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Flowinstancenode as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Flowinstancenode> findByParentstate(Object parentstate
	) {
		return findByProperty(PARENTSTATE, parentstate
		);
	}
	
	public List<Flowinstancenode> findByInstanceid(Object instanceid
	) {
		return findByProperty(INSTANCEID, instanceid
		);
	}
	
	public List<Flowinstancenode> findByStateid(Object stateid
	) {
		return findByProperty(STATEID, stateid
		);
	}
	
	public List<Flowinstancenode> findByTransactuser(Object transactuser
	) {
		return findByProperty(TRANSACTUSER, transactuser
		);
	}
	
	public List<Flowinstancenode> findByState(Object state
	) {
		return findByProperty(STATE, state
		);
	}
	
	public List<Flowinstancenode> findBySubmituser(Object submituser
	) {
		return findByProperty(SUBMITUSER, submituser
		);
	}
	
	public List<Flowinstancenode> findBySubmitoption(Object submitoption
	) {
		return findByProperty(SUBMITOPTION, submitoption
		);
	}
	
	public List<Flowinstancenode> findBySubmitresult(Object submitresult
	) {
		return findByProperty(SUBMITRESULT, submitresult
		);
	}
	
	public List<Flowinstancenode> findByNextuser(Object nextuser
	) {
		return findByProperty(NEXTUSER, nextuser
		);
	}
	

	public List findAll() {
		log.debug("finding all Flowinstancenode instances");
		try {
			String queryString = "from Flowinstancenode";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Flowinstancenode merge(Flowinstancenode detachedInstance) {
        log.debug("merging Flowinstancenode instance");
        try {
            Flowinstancenode result = (Flowinstancenode) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Flowinstancenode instance) {
        log.debug("attaching dirty Flowinstancenode instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Flowinstancenode instance) {
        log.debug("attaching clean Flowinstancenode instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}