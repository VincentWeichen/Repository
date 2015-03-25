package supton.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Exclusionrole;
import supton.entity.PageInfo;
import supton.entity.Role;

/**
 	* A data access object (DAO) providing persistence and search support for Exclusionrole entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Exclusionrole
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class ExclusionroleDaoImpl extends BaseDao implements IExclusionroleDao  {
	     private static final Logger log = LoggerFactory.getLogger(ExclusionroleDaoImpl.class);
		//property constants
	public static final String EXCLUSIONROLE = "exclusionrole";



    
    public void save(Exclusionrole transientInstance) {
        log.debug("saving Exclusionrole instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Exclusionrole persistentInstance) {
        log.debug("deleting Exclusionrole instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Exclusionrole findById( java.lang.Integer id) {
        log.debug("getting Exclusionrole instance with id: " + id);
        try {
            Exclusionrole instance = (Exclusionrole) getSession()
                    .get("supton.Exclusionrole", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List<Exclusionrole> findBySn(int sn) {
		return findByProperty("sn", sn);
	}
	
	public int deleteExclusionrole(int sn) {
		try {
			Query query = getSession().createSQLQuery(
					"DELETE  FROM Exclusionrole where sn = " + sn);
			return query.executeUpdate();
        } catch (RuntimeException re) {

            throw re;
        }
	}
	
    /**
	 * 添加数据
	 * 
	 * @param exclusionrole
	 */
	public void saveExclusionrole(Exclusionrole exclusionrole) {
		if(exclusionrole.getSn() == null)
		{
			getSession().save(exclusionrole);
		}else
		{
			getSession().update(exclusionrole);
		}	
	}
	
    public PageInfo PagedQuery(int pageNo, int pageSize) {
		try {
			String hql = "select R from Exclusionrole R";
			PageInfo pageInfo = pagedQuery(hql,pageNo,pageSize);
			return pageInfo;
		} catch (RuntimeException re) {
			throw re;
		}
	}
    
    public List<Exclusionrole> findByExample(Exclusionrole instance) {
        log.debug("finding Exclusionrole instance by example");
        try {
            List<Exclusionrole> results = (List<Exclusionrole>) getSession()
                    .createCriteria("supton.Exclusionrole")
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
      log.debug("finding Exclusionrole instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Exclusionrole as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Exclusionrole> findByExclusionrole(Object exclusionrole
	) {
		return findByProperty(EXCLUSIONROLE, exclusionrole
		);
	}
	

	public List<Exclusionrole> findAll() {
		log.debug("finding all Exclusionrole instances");
		try {
			String queryString = "from Exclusionrole";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Exclusionrole merge(Exclusionrole detachedInstance) {
        log.debug("merging Exclusionrole instance");
        try {
            Exclusionrole result = (Exclusionrole) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Exclusionrole instance) {
        log.debug("attaching dirty Exclusionrole instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Exclusionrole instance) {
        log.debug("attaching clean Exclusionrole instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}