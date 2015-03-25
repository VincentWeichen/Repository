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
import supton.entity.Role;
import supton.entity.Subgroup;
import supton.entity.User;

/**
 * A data access object (DAO) providing persistence and search support for
 * Subgroup entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see supton.Subgroup
 * @author MyEclipse Persistence Tools
 */
@Repository
public class SubgroupDaoImpl extends BaseDao implements ISubgroupDao {
	private static final Logger log = LoggerFactory
			.getLogger(SubgroupDaoImpl.class);
	// property constants
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String ORGANIZATIONCODE = "organizationcode";

	public void save(Subgroup transientInstance) {
		try {
			if (transientInstance.getId() == null) {
				getSession().save(transientInstance);
			} else {
				getSession().update(transientInstance);
			}
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Subgroup persistentInstance) {
		log.debug("deleting Subgroup instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	
	public int deleteByOrganizationcode(String organizationcode) {
		try {
			Query query = getSession().createSQLQuery(
					"DELETE  FROM Subgroup where organizationcode = '" + organizationcode + "'");
			return query.executeUpdate();
        } catch (RuntimeException re) {

            throw re;
        }
	}
	public Subgroup findById(java.lang.Integer id) {
		log.debug("getting Subgroup instance with id: " + id);
		try {
			Subgroup instance = (Subgroup) getSession().get("supton.entity.Subgroup",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Subgroup> findByExample(Subgroup instance) {
		log.debug("finding Subgroup instance by example");
		try {
			List<Subgroup> results = (List<Subgroup>) getSession()
					.createCriteria("supton.entity.Subgroup").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Subgroup instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Subgroup as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Subgroup> findByCode(String code) {
		return findByProperty(CODE, code);
	}

	public List<Subgroup> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Subgroup> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<Subgroup> findByOrganizationcode(String organizationcode) {
		return findByProperty(ORGANIZATIONCODE, organizationcode);
	}

	public List findAll() {
		log.debug("finding all Subgroup instances");
		try {
			String queryString = "from Subgroup";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Subgroup merge(Subgroup detachedInstance) {
		log.debug("merging Subgroup instance");
		try {
			Subgroup result = (Subgroup) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Subgroup instance) {
		log.debug("attaching dirty Subgroup instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Subgroup instance) {
		log.debug("attaching clean Subgroup instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List<Subgroup> GetGroupListBytype(String organizationCode,String type) {
		try {
			String hql = "select R from Subgroup R where organizationcode ='"
					+ organizationCode + "' and type = '"+type+"'";
			Query query = getSession().createQuery(hql);
			return query.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public PageInfo PagedQuery(String organizationCode, int pageNo, int pageSize) {
		try {
			String hql = "select R from Subgroup R where organizationcode ='"
					+ organizationCode + "'";
			PageInfo pageInfo = pagedQuery(hql, pageNo, pageSize);
			return pageInfo;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public int deleteGroup(String code, String corganizationCode) {
		try {
			Query query = getSession().createSQLQuery(
					"DELETE  FROM Subgroup where code = '" + code + "' and organizationcode ='"+ corganizationCode +"'");
			return query.executeUpdate();
		} catch (RuntimeException re) {

			throw re;
		}
	}
	public Subgroup GetGroupByAuthorize(String organizationCode,String type,String groupauthorize) {
		try {
			String hql = "select R from Subgroup R where organizationcode ='"
					+ organizationCode + "' and type = '"+type+"' and groupauthorize='"
							+ groupauthorize + "'";
			Query query = getSession().createQuery(hql);
			return (Subgroup) query.uniqueResult();
		} catch (RuntimeException re) {
			throw re;
		}
	}
}