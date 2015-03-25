package supton.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Terminal;

/**
 	* A data access object (DAO) providing persistence and search support for Terminal entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Terminal
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class TerminalDaoImpl extends BaseDao implements ITerminalDao  {
	     private static final Logger log = LoggerFactory.getLogger(TerminalDaoImpl.class);
		//property constants
	public static final String STATUS = "status";
	public static final String IPADDRESS = "ipaddress";
	public static final String MACADDRESS = "macaddress";
	public static final String SERIALNO = "serialno";
	public static final String MODELNAME = "modelname";
	public static final String FIRMWAREVERSION = "firmwareversion";



    
    public void save(Terminal transientInstance) {
        log.debug("saving Terminal instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Terminal persistentInstance) {
        log.debug("deleting Terminal instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Terminal findById( java.lang.String id) {
        log.debug("getting Terminal instance with id: " + id);
        try {
            Terminal instance = (Terminal) getSession()
                    .get("supton.entity.Terminal", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Terminal> findByExample(Terminal instance) {
        log.debug("finding Terminal instance by example");
        try {
            List<Terminal> results = (List<Terminal>) getSession()
                    .createCriteria("supton.entity.Terminal")
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
      log.debug("finding Terminal instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Terminal as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Terminal> findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	
	public List<Terminal> findByIpaddress(Object ipaddress
	) {
		return findByProperty(IPADDRESS, ipaddress
		);
	}
	
	public List<Terminal> findByMacaddress(Object macaddress
	) {
		return findByProperty(MACADDRESS, macaddress
		);
	}
	
	public List<Terminal> findBySerialno(Object serialno
	) {
		return findByProperty(SERIALNO, serialno
		);
	}
	
	public List<Terminal> findByModelname(Object modelname
	) {
		return findByProperty(MODELNAME, modelname
		);
	}
	
	public List<Terminal> findByFirmwareversion(Object firmwareversion
	) {
		return findByProperty(FIRMWAREVERSION, firmwareversion
		);
	}
	

	public List findAll() {
		log.debug("finding all Terminal instances");
		try {
			String queryString = "from Terminal";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Terminal merge(Terminal detachedInstance) {
        log.debug("merging Terminal instance");
        try {
            Terminal result = (Terminal) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Terminal instance) {
        log.debug("attaching dirty Terminal instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Terminal instance) {
        log.debug("attaching clean Terminal instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}