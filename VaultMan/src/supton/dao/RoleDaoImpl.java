package supton.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Organization;
import supton.entity.PageInfo;
import supton.entity.Role;
import supton.entity.User;

/**
 	* A data access object (DAO) providing persistence and search support for Role entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Role
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class RoleDaoImpl extends BaseDao implements IRoleDao {
		//property constants
	public static final String CODE = "code";
	public static final String TYPE = "type";
	public static final String NAME = "name";
	public static final String ALIAS = "alias";



    
    public void save(Role transientInstance) {
        try {
            getSession().save(transientInstance);

        } catch (RuntimeException re) {
            throw re;
        }
    }
    
	public void delete(Role persistentInstance) {

        try {
            getSession().delete(persistentInstance);

        } catch (RuntimeException re) {

            throw re;
        }
    }
    
    public Role findById(java.lang.Integer id) {

        try {
            Role instance = (Role) getSession()
                    .get("supton.Role", id);
            return instance;
        } catch (RuntimeException re) {

            throw re;
        }
    }
    
    public PageInfo PagedQuery(int pageNo, int pageSize) {
		try {
			String hql = "select R from Role R";
			PageInfo pageInfo = pagedQuery(hql,pageNo,pageSize);
			return pageInfo;
		} catch (RuntimeException re) {
			throw re;
		}
	}
    
    /**
	 * 添加数据
	 * 
	 * @param Role
	 */
	public void saveRole(Role role) {
		if(role.getId() == null)
		{
			getSession().save(role);
		}else
		{
			getSession().update(role);
		}	
	}
    
//	public List findAll() {
//	log.debug("finding all Role instances");
//	try {
//		String queryString = "from Role";
//         Query queryObject = getSession().createQuery(queryString);
//		 return queryObject.list();
//	} catch (RuntimeException re) {
//		log.error("find all failed", re);
//		throw re;
//	}
//}
    
    
//    public List<Role> findByExample(Role instance) {
//        log.debug("finding Role instance by example");
//        try {
//            List<Role> results = (List<Role>) getSession()
//                    .createCriteria("supton.Role")
//                    .add( create(instance) )
//            .list();
//            log.debug("find by example successful, result size: " + results.size());
//            return results;
//        } catch (RuntimeException re) {
//            log.error("find by example failed", re);
//            throw re;
//        }
//    }    
//    
    public List findByProperty(String propertyName, Object value) {
      try {
         String queryString = "from Role as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         throw re;
      }
	}

	public List<Role> findByCode(Object code) {
		return findByProperty(CODE, code
		);
	}
	
	public int deleteRole(String code) {
		try {
			Query query = getSession().createSQLQuery(
					"DELETE  FROM Role where code = '" + code + "'");
			return query.executeUpdate();
        } catch (RuntimeException re) {

            throw re;
        }
	}
	
	public Role getRoleById(int id) {
		try {
			String queryString = "from Role where id = " + id;
			Query query = getSession().createQuery(queryString);
			return (Role) query.uniqueResult();
		} catch (RuntimeException re) {
			//log.error("find all failed", re);
			throw re;
		}
	}
//	public List<Role> findByType(Object type
//	) {
//		return findByProperty(TYPE, type
//		);
//	}
//	
//	public List<Role> findByName(Object name
//	) {
//		return findByProperty(NAME, name
//		);
//	}
//	
//	public List<Role> findByAlias(Object alias
//	) {
//		return findByProperty(ALIAS, alias
//		);
//	}
//	
//
	public List<Role> findAll() {
		try {
			String queryString = "from Role";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
//	
//    public Role merge(Role detachedInstance) {
//        log.debug("merging Role instance");
//        try {
//            Role result = (Role) getSession()
//                    .merge(detachedInstance);
//            log.debug("merge successful");
//            return result;
//        } catch (RuntimeException re) {
//            log.error("merge failed", re);
//            throw re;
//        }
//    }
//
//    public void attachDirty(Role instance) {
//        log.debug("attaching dirty Role instance");
//        try {
//            getSession().saveOrUpdate(instance);
//            log.debug("attach successful");
//        } catch (RuntimeException re) {
//            log.error("attach failed", re);
//            throw re;
//        }
//    }
//    
//    public void attachClean(Role instance) {
//        log.debug("attaching clean Role instance");
//        try {
//            getSession().lock(instance, LockMode.NONE);
//            log.debug("attach successful");
//        } catch (RuntimeException re) {
//            log.error("attach failed", re);
//            throw re;
//        }
//    }
}