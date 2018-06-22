package cn.tpson.kulu.gas.dto;

import com.alibaba.fastjson.JSON;

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

	private int pageNumber = DEFAULT_PAGE_NO; // 页码
	private int pageSize = DEFAULT_PAGE_SIZE; // 页面大小
	private int totalRow; // 总的记录数
	private List<E> list; // 返回的查询结果集
	private E condition;		//查询条件

	public Page() {
		super();
	}

	public Page(int total, List<E> resultList) {
		this.totalRow = total;
		this.list = resultList;
	}

	public Page(Integer offset, Integer limit) {
		if (offset == null || offset <= 0) {
			pageNumber = DEFAULT_PAGE_NO;
		} else {
            pageNumber = offset / limit + 1;
		}

		if (limit == null || limit <= 0) {
			pageSize = DEFAULT_PAGE_SIZE;
		} else {
			pageSize = limit;
		}
	}

	public Page(int pageNo, int pageSize, int pageNaviSize) {
		this(pageNo, pageSize);
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		if (pageNumber < 1) {
            pageNumber = DEFAULT_PAGE_NO;
		}
		this.pageNumber = pageNumber;
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

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
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
		if (totalRow % pageSize > 0) {
			return totalRow / pageSize + 1;
		} else {
			return totalRow / pageSize;
		}
	}

	/**
	 * 获取从哪一条记录开始查询
	 * 
	 * @return
	 */
	public int getFirstIndex() {
		return (pageNumber - 1) * pageSize;
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
		return (pageNumber + 1) <= getTotalPage();
	}

	/**
	 * 获取下一个页码，在调用之前先调用<code>isHasNextPage()</code>方法进行判断
	 * 
	 * @return
	 */
	public int getNextPage() {
		return pageNumber + 1;
	}

	/**
	 * 判断是否还有上一页
	 *
	 * @return
	 */
	public boolean isHasPrePage() {
		return (pageNumber - 1) > 0;
	}

	/**
	 * 获取上一个页码，在调用之前先调用<code>isHasPrePage()</code>方法进行判断
	 * 
	 * @return
	 */
	public int getPrePage() {
		return pageNumber - 1;
	}

	@Override
	public String toString() {
		return "Page [pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", totalRow="
				+ totalRow + ", list=" + list + "]";
	}

	/*public static void main(String[] args) {
		System.out.println(JSON.toJSONString(new Page()));
	}*/
}

