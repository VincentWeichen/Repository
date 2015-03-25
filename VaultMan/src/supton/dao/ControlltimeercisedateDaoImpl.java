package supton.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.Controlltimeercisedate;

/**
 * A data access object (DAO) providing persistence and search support for
 * Controlltimeercisedate entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see supton.Controlltimeercisedate
 * @author MyEclipse Persistence Tools
 */
@Repository
public class ControlltimeercisedateDaoImpl extends BaseDao implements
		IControlltimeercisedateDao {
	private static final Logger log = LoggerFactory
			.getLogger(ControlltimeercisedateDaoImpl.class);
	// property constants
	public static final String CONTROLLTIMEDETAILID = "controlltimedetailid";
	public static final String SEQUENCE = "sequence";
	public static final String ORGANIZATIONCODE = "organizationcode";
	public static final String WEEKDAY = "weekday";
	public static final String BEGINDATE = "begindate";
	public static final String ENDDATE = "enddate";

	public void save(Controlltimeercisedate transientInstance) {
		log.debug("saving Controlltimeercisedate instance");
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

	public void delete(Controlltimeercisedate persistentInstance) {
		log.debug("deleting Controlltimeercisedate instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Controlltimeercisedate findById(java.lang.Integer id) {
		log.debug("getting Controlltimeercisedate instance with id: " + id);
		try {
			Controlltimeercisedate instance = (Controlltimeercisedate) getSession()
					.get("supton.entity.Controlltimeercisedate", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Controlltimeercisedate> findByExample(
			Controlltimeercisedate instance) {
		log.debug("finding Controlltimeercisedate instance by example");
		try {
			List<Controlltimeercisedate> results = (List<Controlltimeercisedate>) getSession()
					.createCriteria("supton.Controlltimeercisedate")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Controlltimeercisedate instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Controlltimeercisedate as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Controlltimeercisedate> findByControlltimedetailid(
			Integer controlltimedetailid) {
		return findByProperty(CONTROLLTIMEDETAILID, controlltimedetailid);
	}

	public List<Controlltimeercisedate> findBySequence(Object sequence) {
		return findByProperty(SEQUENCE, sequence);
	}

	public List<Controlltimeercisedate> findByOrganizationcode(
			String organizationcode) {
		return findByProperty(ORGANIZATIONCODE, organizationcode);
	}

	public List<Controlltimeercisedate> findByWeekday(Object weekday) {
		return findByProperty(WEEKDAY, weekday);
	}

	public List<Controlltimeercisedate> findByBegindate(Object begindate) {
		return findByProperty(BEGINDATE, begindate);
	}

	public List<Controlltimeercisedate> findByEnddate(Object enddate) {
		return findByProperty(ENDDATE, enddate);
	}

	public List findAll() {
		log.debug("finding all Controlltimeercisedate instances");
		try {
			String queryString = "from Controlltimeercisedate";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Controlltimeercisedate merge(Controlltimeercisedate detachedInstance) {
		log.debug("merging Controlltimeercisedate instance");
		try {
			Controlltimeercisedate result = (Controlltimeercisedate) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Controlltimeercisedate instance) {
		log.debug("attaching dirty Controlltimeercisedate instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Controlltimeercisedate instance) {
		log.debug("attaching clean Controlltimeercisedate instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public int deleteByOrganizationcode(String organizationcode,String type,Integer controlltimeid) {
		try {
			Query query = getSession().createSQLQuery(
					"DELETE  FROM Controlltimeercisedate where organizationcode = '" + organizationcode + "' and type = '"+type+"' and controlltimeid ="+controlltimeid+"");
			return query.executeUpdate();
        } catch (RuntimeException re) {

            throw re;
        }
	}
	public int delete(Integer id) {
		try {
			Query query = getSession().createSQLQuery(
					"DELETE  FROM Controlltimeercisedate where id = " + id + "");
			return query.executeUpdate();
        } catch (RuntimeException re) {

            throw re;
        }
    }
	
	public int deleteByControlltimeId(Integer controlltimeid) {
		try {
			Query query = getSession().createSQLQuery(
					"DELETE  FROM Controlltimeercisedate where controlltimeid = " + controlltimeid + "");
			return query.executeUpdate();
        } catch (RuntimeException re) {

            throw re;
        }
    }
}