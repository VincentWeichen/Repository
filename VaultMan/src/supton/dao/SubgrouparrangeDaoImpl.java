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
import supton.entity.Organization;
import supton.entity.Subgroup;
import supton.entity.Subgrouparrange;
import supton.entity.Taskexport;

/**
 	* A data access object (DAO) providing persistence and search support for Subgrouparrange entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Subgrouparrange
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class SubgrouparrangeDaoImpl extends BaseDao implements ISubgrouparrangeDao  {
	     private static final Logger log = LoggerFactory.getLogger(SubgrouparrangeDaoImpl.class);
		//property constants
	public static final String VERSION = "version";
	public static final String SUBGROUPCODE = "subgroupcode";
	public static final String SEQUENCE = "sequence";
	public static final String TYPE = "type";
	public static final String TIMEDURATION = "timeduration";
	public static final String STATUS = "status";
	public static final String ORGANIZATIONCODE = "organizationcode";



    
    public void save(Subgrouparrange transientInstance) {
        log.debug("saving Subgrouparrange instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Subgrouparrange persistentInstance) {
        log.debug("deleting Subgrouparrange instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Subgrouparrange findById( java.lang.Integer id) {
        log.debug("getting Subgrouparrange instance with id: " + id);
        try {
            Subgrouparrange instance = (Subgrouparrange) getSession()
                    .get("supton.Subgrouparrange", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Subgrouparrange> findByExample(Subgrouparrange instance) {
        log.debug("finding Subgrouparrange instance by example");
        try {
            List<Subgrouparrange> results = (List<Subgrouparrange>) getSession()
                    .createCriteria("supton.Subgrouparrange")
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
      log.debug("finding Subgrouparrange instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Subgrouparrange as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Subgrouparrange> findByVersion(Object version
	) {
		return findByProperty(VERSION, version
		);
	}
	
	public List<Subgrouparrange> findBySubgroupcode(Object subgroupcode
	) {
		return findByProperty(SUBGROUPCODE, subgroupcode
		);
	}
	
	public List<Subgrouparrange> findBySequence(Object sequence
	) {
		return findByProperty(SEQUENCE, sequence
		);
	}
	
	public List<Subgrouparrange> findByType(Object type
	) {
		return findByProperty(TYPE, type
		);
	}
	
	public List<Subgrouparrange> findByTimeduration(Object timeduration
	) {
		return findByProperty(TIMEDURATION, timeduration
		);
	}
	
	public List<Subgrouparrange> findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	

	public List findAll() {
		log.debug("finding all Subgrouparrange instances");
		try {
			String queryString = "from Subgrouparrange";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Subgrouparrange merge(Subgrouparrange detachedInstance) {
        log.debug("merging Subgrouparrange instance");
        try {
            Subgrouparrange result = (Subgrouparrange) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Subgrouparrange instance) {
        log.debug("attaching dirty Subgrouparrange instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Subgrouparrange instance) {
        log.debug("attaching clean Subgrouparrange instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public int deleteByOrganizationcode(String organizationcode,String type) {
		try {
			Query query = getSession().createSQLQuery(
					"DELETE  FROM Subgrouparrange where organizationcode = '" + organizationcode + "' and type = '" + type + "'");
			return query.executeUpdate();
        } catch (RuntimeException re) {

            throw re;
        }
	}
    
    public List<Subgrouparrange> findByOrganizationcode(String organizationcode) {
		return findByProperty(ORGANIZATIONCODE, organizationcode);
	}
    
    public Taskexport findTaskExport(String tid, Integer typeid) {
    	try {
			String queryString = "from Taskexport where tid ='"
					+ tid + "' and type = " + typeid;
			Query query = getSession().createQuery(queryString);
			return (Taskexport) query.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
    }
    
    public void SetTaskExport(Taskexport taskexport) {
    	try {
			if (taskexport.getTaskid() == null) {
				getSession().save(taskexport);
			} else {
				getSession().update(taskexport);
			}

		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
    }
}