package cn.tpson.kulu.gas.dto;

import java.util.List;

public class TableDTO<T> extends BaseDTO {
	private Integer total;
	private List<T> rows;

	public TableDTO(Integer total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
