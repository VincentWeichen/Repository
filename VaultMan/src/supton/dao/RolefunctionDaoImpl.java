package supton.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Role;
import supton.entity.Rolefunction;

/**
 	* A data access object (DAO) providing persistence and search support for Rolefunction entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Rolefunction
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class RolefunctionDaoImpl extends BaseDao  implements IRolefunctionDao  {
	     private static final Logger log = LoggerFactory.getLogger(RolefunctionDaoImpl.class);
		//property constants
	public static final String FUNCTIONCODE = "functioncode";
	public static final String FUNCTIONNAME = "functionname";
	public static final String ROLECODE = "rolecode";
	public static final String WRITE = "funwrite";
	public static final String READ = "funread";



    public void save(Rolefunction transientInstance) {
    	if(transientInstance.getId() == null)
		{
			getSession().save(transientInstance);
		}else
		{
			getSession().update(transientInstance);
		}
    }
    
	public void delete(Rolefunction persistentInstance) {
        log.debug("deleting Rolefunction 2");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Rolefunction findById( java.lang.Integer id) {
        log.debug("getting Rolefunction instance with id: " + id);
        try {
            Rolefunction instance = (Rolefunction) getSession()
                    .get("supton.Rolefunction", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Rolefunction> findByExample(Rolefunction instance) {
        log.debug("finding Rolefunction instance by example");
        try {
            List<Rolefunction> results = (List<Rolefunction>) getSession()
                    .createCriteria("supton.Rolefunction")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List<Rolefunction> findByProperty(String propertyName, Object value) {
      log.debug("finding Rolefunction instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Rolefunction as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Rolefunction> findByFunctioncode(Object functioncode
	) {
		return findByProperty(FUNCTIONCODE, functioncode
		);
	}
	
	public List<Rolefunction> findByFunctionname(Object functionname
	) {
		return findByProperty(FUNCTIONNAME, functionname
		);
	}
	
	public List<Rolefunction> findByRolecode(Object rolecode
	) {
		return findByProperty(ROLECODE, rolecode
		);
	}
	
	public List<Rolefunction> findByWrite(Object write
	) {
		return findByProperty(WRITE, write
		);
	}
	
	public List<Rolefunction> findByRead(Object read
	) {
		return findByProperty(READ, read
		);
	}
	

	public List<Rolefunction> findAll() {
		log.debug("finding all Rolefunction instances");
		try {
			String queryString = "from Rolefunction";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Rolefunction merge(Rolefunction detachedInstance) {
        log.debug("merging Rolefunction instance");
        try {
            Rolefunction result = (Rolefunction) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Rolefunction instance) {
        log.debug("attaching dirty Rolefunction instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Rolefunction instance) {
        log.debug("attaching clean Rolefunction instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public List<Rolefunction> findByRoles(String roles) {
		try {
			String queryString = "from Rolefunction where rolecode in ("+roles+")";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
    public List<String> findCodeByRoles(String roles) {
		try {
			String queryString = "select distinct(functioncode) from Rolefunction where rolecode in ("+roles+")";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
    public int deleteByRolecode(String rolecode) {
		try {
			Query query = getSession().createSQLQuery(
					"DELETE  FROM Rolefunction where rolecode = '" + rolecode + "'");
			return query.executeUpdate();
        } catch (RuntimeException re) {

            throw re;
        }
	}
}