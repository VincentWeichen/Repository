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
import supton.entity.Vaultlog;

/**
 	* A data access object (DAO) providing persistence and search support for Vaultlog entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see supton.Vaultlog
  * @author MyEclipse Persistence Tools 
 */
@Repository
public class VaultlogDaoImpl extends BaseDao implements IVaultlogDao   {
	     private static final Logger log = LoggerFactory.getLogger(VaultlogDaoImpl.class);
		//property constants
	public static final String TID = "tid";
	public static final String LOG_INDEX = "logIndex";
	public static final String IN_OUT_INDICATION = "inOutIndication";
	public static final String VERIFICATION_SOURCE = "verificationSource";
	public static final String EVENT_ALARM_CODE = "eventAlarmCode";
	public static final String DOOR_NO = "doorNo";
	public static final String USER_ID = "userId";
	public static final String CARD_ID = "cardId";
	public static final String FUNCTION_KEY_INDEX = "functionKeyIndex";
	public static final String GROUP_ID = "groupId";
	public static final String EVENT_ALARM_CODE_DECODE = "eventAlarmCodeDecode";



    
    public void save(Vaultlog transientInstance) {
        log.debug("saving Vaultlog instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Vaultlog persistentInstance) {
        log.debug("deleting Vaultlog instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Vaultlog findById( java.lang.Integer id) {
        log.debug("getting Vaultlog instance with id: " + id);
        try {
            Vaultlog instance = (Vaultlog) getSession()
                    .get("supton.Vaultlog", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Vaultlog> findByExample(Vaultlog instance) {
        log.debug("finding Vaultlog instance by example");
        try {
            List<Vaultlog> results = (List<Vaultlog>) getSession()
                    .createCriteria("supton.Vaultlog")
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
      log.debug("finding Vaultlog instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Vaultlog as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Vaultlog> findByTid(String tid) {
		return findByProperty(TID, tid);
	}
	
	public List<Vaultlog> findByLogIndex(Object logIndex
	) {
		return findByProperty(LOG_INDEX, logIndex
		);
	}
	
	public List<Vaultlog> findByInOutIndication(Object inOutIndication
	) {
		return findByProperty(IN_OUT_INDICATION, inOutIndication
		);
	}
	
	public List<Vaultlog> findByVerificationSource(Object verificationSource
	) {
		return findByProperty(VERIFICATION_SOURCE, verificationSource
		);
	}
	
	public List<Vaultlog> findByEventAlarmCode(Object eventAlarmCode
	) {
		return findByProperty(EVENT_ALARM_CODE, eventAlarmCode
		);
	}
	
	public List<Vaultlog> findByDoorNo(Object doorNo
	) {
		return findByProperty(DOOR_NO, doorNo
		);
	}
	
	public List<Vaultlog> findByUserId(Object userId
	) {
		return findByProperty(USER_ID, userId
		);
	}
	
	public List<Vaultlog> findByCardId(Object cardId
	) {
		return findByProperty(CARD_ID, cardId
		);
	}
	
	public List<Vaultlog> findByFunctionKeyIndex(Object functionKeyIndex
	) {
		return findByProperty(FUNCTION_KEY_INDEX, functionKeyIndex
		);
	}
	
	public List<Vaultlog> findByGroupId(Object groupId
	) {
		return findByProperty(GROUP_ID, groupId
		);
	}
	
	public List<Vaultlog> findByEventAlarmCodeDecode(Object eventAlarmCodeDecode
	) {
		return findByProperty(EVENT_ALARM_CODE_DECODE, eventAlarmCodeDecode
		);
	}
	

	public List findAll() {
		log.debug("finding all Vaultlog instances");
		try {
			String queryString = "from Vaultlog";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Vaultlog merge(Vaultlog detachedInstance) {
        log.debug("merging Vaultlog instance");
        try {
            Vaultlog result = (Vaultlog) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Vaultlog instance) {
        log.debug("attaching dirty Vaultlog instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Vaultlog instance) {
        log.debug("attaching clean Vaultlog instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public List<Vaultlog> findByTidDate(String tid,String nowDate) {
    	try {
			String hql = "from Vaultlog R where tid ='"
					+ tid + "' and str_to_date(createTime,'%Y-%m-%d') = '"+nowDate+"' order by createTime";
			Query query = getSession().createQuery(hql);
			return query.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
    
    public List<Vaultlog> findByTidNowDate(String tid,String nowDate) {
    	try {
			String hql = "from Vaultlog R where tid ='"
					+ tid + "' and str_to_date(createTime,'%Y-%m-%d') = '"+nowDate+"' order by logIndex";
			Query query = getSession().createQuery(hql);
			return query.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
    
    public List<Vaultlog> findByTidLogIndex(String tid,String logIndex,String nowDate) {
    	try {
			String hql = "from Vaultlog R where tid ='"+ tid + "' and str_to_date(createTime,'%Y-%m-%d') = '"+nowDate+"'";
			if(logIndex != null && !logIndex.isEmpty())
			{
				hql+=" and logIndex > "+logIndex;
			}
			hql+="  order by logIndex";
			Query query = getSession().createQuery(hql);
			return query.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
}