package supton.common;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import supton.entity.PageInfo;
import supton.entity.User;

public class BaseDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Boolean isCache=true;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	/**
	 * 分页查询函数，使用hql.
	 * 
	 * @param pageNo
	 *            页号,从1开始.
	 */
	public PageInfo pagedQuery(String hql, int pageNo, int pageSize) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count (*) "
				+ removeSelect(removeOrders(hql));
		List countlist =  getSession().createQuery(countQueryString).list();;
		long totalCount = (Long) countlist.get(0);

		if (totalCount < 1)
			return new PageInfo();
		// 实际查询返回分页对象
		int startIndex = PageInfo.getStartOfPage(pageNo, pageSize);
		Query query = getSession().createQuery(hql);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize)
				.list();

		return new PageInfo(startIndex, totalCount, pageSize, pageNo, list);
	}
	
	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql
				+ " must has a keyword 'from'");
		return hql.substring(beginPos);
	}
	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
//	public Query createQuery(String hql, Object... values) {
//		Assert.hasText(hql);
//		Query query = getSession().createQuery(hql);
//		for (int i = 0; i < values.length; i++) {
//			query.setParameter(i, values[i]);
//		}
//		return query.setCacheable(isCache);
//	}
}
