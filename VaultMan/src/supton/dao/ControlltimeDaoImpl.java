package supton.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Controlltime;
import supton.entity.PageInfo;

/**
 	* A data access object (DAO) providing persistence and search support for Controlltime entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Controlltime
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class ControlltimeDaoImpl extends BaseDao implements IControlltimeDao  {
	     private static final Logger log = LoggerFactory.getLogger(ControlltimeDaoImpl.class);
		//property constants
	public static final String VERSION = "version";
	public static final String TYPE = "type";
	public static final String SEQUENCE = "sequence";
	public static final String ORGANIZATIONCODE = "organizationcode";
	public static final String STATUS = "status";



    
    public void save(Controlltime transientInstance) {
        log.debug("saving Controlltime instance");
        try {
        	if (transientInstance.getId() == null) {
				getSession().save(transientInstance);
			} else {
				getSession().update(transientInstance);
			}
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Controlltime persistentInstance) {
        log.debug("deleting Controlltime instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Controlltime findById( java.lang.Integer id) {
        log.debug("getting Controlltime instance with id: " + id);
        try {
            Controlltime instance = (Controlltime) getSession()
                    .get("supton.Controlltime", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Controlltime> findByExample(Controlltime instance) {
        log.debug("finding Controlltime instance by example");
        try {
            List<Controlltime> results = (List<Controlltime>) getSession()
                    .createCriteria("supton.Controlltime")
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
      log.debug("finding Controlltime instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Controlltime as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Controlltime> findByVersion(Object version
	) {
		return findByProperty(VERSION, version
		);
	}
	
	public List<Controlltime> findByType(String type
	) {
		return findByProperty(TYPE, type
		);
	}
	
	public List<Controlltime> findBySequence(Object sequence
	) {
		return findByProperty(SEQUENCE, sequence
		);
	}
	
	public List<Controlltime> findByOrganizationcode(String organizationcode
	) {
		return findByProperty(ORGANIZATIONCODE, organizationcode
		);
	}
	
	public List<Controlltime> findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	

	public List findAll() {
		log.debug("finding all Controlltime instances");
		try {
			String queryString = "from Controlltime";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Controlltime merge(Controlltime detachedInstance) {
        log.debug("merging Controlltime instance");
        try {
            Controlltime result = (Controlltime) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Controlltime instance) {
        log.debug("attaching dirty Controlltime instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Controlltime instance) {
        log.debug("attaching clean Controlltime instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public PageInfo PagedQueryZhou(String organizationCode,String type, int pageNo, int pageSize) {
		try {
			String hql = "select R from Controlltime R where organizationcode ='"
					+ organizationCode + "' and  type = '"+type+"'";
			PageInfo pageInfo = pagedQuery(hql, pageNo, pageSize);
			return pageInfo;
		} catch (RuntimeException re) {
			throw re;
		}
	}
    
    public int deleteByControlltimeId(Integer controlltimeId) {
		try {
			Query query = getSession().createSQLQuery(
					"DELETE  FROM Controlltime where id = " + controlltimeId + "");
			return query.executeUpdate();
        } catch (RuntimeException re) {

            throw re;
        }
    }
}