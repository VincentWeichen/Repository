package supton.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import supton.common.BaseDao;
import supton.entity.User;

@Repository
public class UserDaoImpl extends BaseDao implements IUserDao {

	/**
	 * 查询个数
	 */
	public int lookUser() {
		// Query query =
		// getCurrentSession().createSQLQuery("SELECT COUNT(*) FROM t_user");
		Query query = getSession().createQuery("FROM User");
		//Query query = getCurrentSession().createQuery("FROM t_user");
		List<?> l = query.list();
		return l.size();
	}

	/**
	 * 删除表数据
	 * 
	 * @return
	 */
	public int deleteUser(int id) {
		Query query = getSession().createSQLQuery(
				"DELETE  FROM User where id = " + id);
		return query.executeUpdate();
	}

	/**
	 * 添加数据
	 * 
	 * @param user
	 */
	public void saveUser(User user) {
		if(user.getId() == null)
		{
			getSession().save(user);
		}else
		{
			getSession().update(user);
		}	
	}
	
	public List<User> getUserLists() {
		// Query query =
		// getCurrentSession().createSQLQuery("SELECT COUNT(*) FROM t_user");
		Query query = getSession().createQuery("FROM User");
		//Query query = getCurrentSession().createQuery("FROM t_user");
		List<User> userList = query.list();
		return userList;
	}
	
	public User validateLogin(String userName, String userPassword) {
		String hql = "from User tb where code='"+userName+"' and password='"+userPassword+"'";
		Query query = getSession().createQuery(hql);
//		query.setString(0, userName);
//		query.setString(1, userPassword);
		
		return (User) query.uniqueResult();
		/*
		if (query.uniqueResult() == null)
			return "用户名或密码错误！";
		else
			return "true";
			*/
		//Query query = getCurrentSession().createSQLQuery(
				//"Select FROM User where id = " + userName);
		
		//return "";
	}
	public int deleteUser(String code) {
		try {
			Query query = getSession().createSQLQuery(
					"DELETE  FROM User where code = '" + code + "'");
			return query.executeUpdate();
        } catch (RuntimeException re) {

            throw re;
        }
	}
	public List<User> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<User> findByCode(String code) {
		return findByProperty("code", code);
	}
}