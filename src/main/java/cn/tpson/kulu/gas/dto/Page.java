package cn.tpson.kulu.gas.dto;

import java.util.List;

/**
 * 分页对象
 * @author zk
 *
 * @param <E>
 */
public class Page<E> extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6562202911735793539L;
	/**
	 * 默认页码
	 */
	public static final int DEFAULT_PAGE_NO = 1;
	/**
	 * 默认页面大小
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;

	private int pageNo = DEFAULT_PAGE_NO; // 页码
	private int pageSize = DEFAULT_PAGE_SIZE; // 页面大小
	private long totalCount; // 总的记录数
	private List<E> resultList; // 返回的查询结果集
	private E condition;		//查询条件

	public Page() {
		super();
	}

	public Page(long totalCount, List<E> resultList) {
		this.totalCount = totalCount;
		this.resultList = resultList;
	}

	public Page(int pageNo, int pageSize) {
		super();
		setPageNo(pageNo);
		setPageSize(pageSize);
	}

	public Page(int pageNo, int pageSize, int pageNaviSize) {
		this(pageNo, pageSize);
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			pageNo = DEFAULT_PAGE_NO;
		}
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<E> getResultList() {
		return resultList;
	}

	public void setResultList(List<E> resultList) {
		this.resultList = resultList;
	}

	public E getCondition() {
		return condition;
	}

	public void setCondition(E condition) {
		this.condition = condition;
	}

	/**
	 * 获得总的页码数量
	 * 
	 * @return
	 */
	public long getTotalPage() {
		if (totalCount % pageSize > 0) {
			return totalCount / pageSize + 1;
		} else {
			return totalCount / pageSize;
		}
	}

	/**
	 * 获取从哪一条记录开始查询
	 * 
	 * @return
	 */
	public int getFirstIndex() {
		return (pageNo - 1) * pageSize;
	}
	
	/**
	 * 获取最后一条记录的下标数（不包含）
	 * 
	 * @return
	 */
	public int getLastIndex() {
		return getFirstIndex() + pageSize;
	}

	/**
	 * 判断是否还有下一页
	 * 
	 * @return
	 */
	public boolean isHasNextPage() {
		return (pageNo + 1) <= getTotalPage();
	}

	/**
	 * 获取下一个页码，在调用之前先调用<code>isHasNextPage()</code>方法进行判断
	 * 
	 * @return
	 */
	public int getNextPage() {
		return pageNo + 1;
	}

	/**
	 * 判断是否还有上一页
	 *
	 * @return
	 */
	public boolean isHasPrePage() {
		return (pageNo - 1) > 0;
	}

	/**
	 * 获取上一个页码，在调用之前先调用<code>isHasPrePage()</code>方法进行判断
	 * 
	 * @return
	 */
	public int getPrePage() {
		return pageNo - 1;
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalCount="
				+ totalCount + ", resultList=" + resultList + "]";
	}
}

