package supton.entity;

import java.util.ArrayList;

/**
 * 分页对象. 包含当前页数据及分页信息如总记录数.
 * 
 * @author ajax
 * @author calvin
 */
public class PageInfo implements java.io.Serializable {

	private static int DEFAULT_PAGE_SIZE = 20;

	private int pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数

	private long start; // 当前页第一条数据在List中的位置,从0开始

	private Object data; // 当前页中存放的记录,类型一般为List

	private long totalCount; // 总记录数
	
	private long pageCount; // 总记录数
	
	private int pageNo;

	/**
	 * 构造方法，只构造空页.
	 */
	public PageInfo() {
		this(0, 0, DEFAULT_PAGE_SIZE, 0,new ArrayList());
	}

	/**
	 * 默认构造方法.
	 * 
	 * @param start
	 *            本页数据在数据库中的起始位置
	 * @param totalSize
	 *            数据库中总记录条数
	 * @param pageSize
	 *            本页容量
	 * @param data
	 *            本页包含的数据
	 */
	public PageInfo(long start, long totalSize, int pageSize,int pageNo, Object data) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.pageNo = pageNo;
		this.data = data;
		this.pageCount = getTotalPageCount();
	}

	/**
	 * 取总记录数.
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 取总页数.
	 */
	public long getTotalPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	/**
	 * 取每页数据容量.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 取当前页中的记录.
	 */
	public Object getResult() {
		return data;
	}

	/**
	 * 取该页当前页码,页码从1开始.
	 */
	public long getCurrentPageNo() {
		return start / pageSize + 1;
	}

	/**
	 * 该页是否有下一页.
	 */
	public boolean hasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount() - 1;
	}

	/**
	 * 该页是否有上一页.
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}

	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
	 * 
	 * @see #getStartOfPage(int,int)
	 */
	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第一条数据在数据集的位置.
	 * 
	 * @param pageNo
	 *            从1开始的页号
	 * @param pageSize
	 *            每页记录条数
	 * @return 该页第一条数据
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}

	public synchronized final Object getData() {
		return data;
	}

	public synchronized final void setData(Object data) {
		this.data = data;
	}

	public synchronized final long getPageCount() {
		return pageCount;
	}

	public synchronized final void setPageCount(long pageCount) {
		this.pageCount = getTotalPageCount();
	}

	public synchronized final int getPageNo() {
		return pageNo;
	}

	public synchronized final void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
}