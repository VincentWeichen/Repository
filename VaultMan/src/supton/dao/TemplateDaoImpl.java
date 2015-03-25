package supton.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.PageInfo;
import supton.entity.Template;

/**
 	* A data access object (DAO) providing persistence and search support for Template entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Template
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class TemplateDaoImpl extends BaseDao implements ITemplateDao   {
	     private static final Logger log = LoggerFactory.getLogger(TemplateDaoImpl.class);
		//property constants
	public static final String TEMPLATE_ID = "templateId";
	public static final String TEMPLATEXMLSTRING = "templatexmlstring";
	public static final String CREATEUSER = "createuser";
	public static final String CREATETIME = "createtime";



    
    public void save(Template transientInstance) {
        log.debug("saving Template instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Template persistentInstance) {
        log.debug("deleting Template instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Template findById( java.lang.Integer id) {
        log.debug("getting Template instance with id: " + id);
        try {
            Template instance = (Template) getSession()
                    .get("supton.Template", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public PageInfo PagedQuery(int pageNo, int pageSize) {
		try {
			String hql = "select R from Template R";
			PageInfo pageInfo = pagedQuery(hql,pageNo,pageSize);
			return pageInfo;
		} catch (RuntimeException re) {
			throw re;
		}
	}
    
    
    public List<Template> findByExample(Template instance) {
        log.debug("finding Template instance by example");
        try {
            List<Template> results = (List<Template>) getSession()
                    .createCriteria("supton.Template")
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
      log.debug("finding Template instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Template as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Template> findByTemplateId(Object templateId
	) {
		return findByProperty(TEMPLATE_ID, templateId
		);
	}
	
	public List<Template> findByTemplatexmlstring(Object templatexmlstring
	) {
		return findByProperty(TEMPLATEXMLSTRING, templatexmlstring
		);
	}
	
	public List<Template> findByCreateuser(Object createuser
	) {
		return findByProperty(CREATEUSER, createuser
		);
	}
	
	public List<Template> findByCreatetime(Object createtime
	) {
		return findByProperty(CREATETIME, createtime
		);
	}
	

	public List findAll() {
		log.debug("finding all Template instances");
		try {
			String queryString = "from Template";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Template merge(Template detachedInstance) {
        log.debug("merging Template instance");
        try {
            Template result = (Template) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Template instance) {
        log.debug("attaching dirty Template instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Template instance) {
        log.debug("attaching clean Template instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}