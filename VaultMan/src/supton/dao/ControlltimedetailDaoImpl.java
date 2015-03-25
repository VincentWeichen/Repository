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
import supton.entity.Controlltimedetail;

/**
 	* A data access object (DAO) providing persistence and search support for Controlltimedetail entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Controlltimedetail
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class ControlltimedetailDaoImpl  extends BaseDao implements IControlltimedetailDao  {
	     private static final Logger log = LoggerFactory.getLogger(ControlltimedetailDaoImpl.class);
		//property constants
	public static final String CONTROLLTIMEID = "controlltimeid";
	public static final String TYPE = "type";
	public static final String SEQUENCE = "sequence";
	public static final String ORGANIZATIONCODE = "organizationcode";
	public static final String WEEKDAY = "weekday";
	public static final String STATUS = "status";



    
	public void save(Controlltimedetail transientInstance) {
		if (transientInstance.getId() == null) {
			getSession().save(transientInstance);
		} else {
			getSession().update(transientInstance);
		}
	}

	public void delete(Controlltimedetail persistentInstance) {
        log.debug("deleting Controlltimedetail instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
	
	public int delete(Integer id) {
		try {
			Query query = getSession().createSQLQuery(
					"DELETE  FROM Controlltimedetail where id = " + id + "");
			return query.executeUpdate();
        } catch (RuntimeException re) {

            throw re;
        }
    }
    
	public int deleteByControlltimeercisedateId(Integer controlltimeercisedateid) {
		try {
			Query query = getSession().createSQLQuery(
					"DELETE  FROM Controlltimedetail where controlltimeercisedateid = " + controlltimeercisedateid + "");
			return query.executeUpdate();
        } catch (RuntimeException re) {

            throw re;
        }
    }
	
    public Controlltimedetail findById( java.lang.Integer id) {
        log.debug("getting Controlltimedetail instance with id: " + id);
        try {
            Controlltimedetail instance = (Controlltimedetail) getSession()
                    .get("supton.entity.Controlltimedetail", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Controlltimedetail> findByExample(Controlltimedetail instance) {
        log.debug("finding Controlltimedetail instance by example");
        try {
            List<Controlltimedetail> results = (List<Controlltimedetail>) getSession()
                    .createCriteria("supton.Controlltimedetail")
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
      log.debug("finding Controlltimedetail instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Controlltimedetail as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Controlltimedetail> findByControlltimeid(Object controlltimeid
	) {
		return findByProperty(CONTROLLTIMEID, controlltimeid
		);
	}
	
	public List<Controlltimedetail> findByType(String type
	) {
		return findByProperty(TYPE, type
		);
	}
	
	public List<Controlltimedetail> findBySequence(Object sequence
	) {
		return findByProperty(SEQUENCE, sequence
		);
	}
	
	public List<Controlltimedetail> findByOrganizationcode(String organizationcode
	) {
		return findByProperty(ORGANIZATIONCODE, organizationcode
		);
	}
	
	public List<Controlltimedetail> findByWeekday(Object weekday
	) {
		return findByProperty(WEEKDAY, weekday
		);
	}
	
	public List<Controlltimedetail> findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	

	public List findAll() {
		log.debug("finding all Controlltimedetail instances");
		try {
			String queryString = "from Controlltimedetail";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Controlltimedetail merge(Controlltimedetail detachedInstance) {
        log.debug("merging Controlltimedetail instance");
        try {
            Controlltimedetail result = (Controlltimedetail) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Controlltimedetail instance) {
        log.debug("attaching dirty Controlltimedetail instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Controlltimedetail instance) {
        log.debug("attaching clean Controlltimedetail instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    public List<Controlltimedetail> findByOrganizationcode(String organizationcode,String type) {
    	try {
			String queryString = "from Controlltimedetail where organizationcode='"+organizationcode+"' And type='"+type+"'";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
    }
    
    public int deleteByControlltimeId(Integer controlltimeid) {
		try {
			Query query = getSession().createSQLQuery(
					"DELETE  FROM Controlltimedetail where controlltimeid = " + controlltimeid + "");
			return query.executeUpdate();
        } catch (RuntimeException re) {

            throw re;
        }
    }
}