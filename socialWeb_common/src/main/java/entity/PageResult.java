package entity;
import	java.util.List;

/**
 * @ClassName PageResult
 * @Description 分类结果
 * @Author 42
 * @Date 2020/3/6 下午 2:43
 * @Version 1.0
 */
public class PageResult <T>
{
	private long total;
	private List<T> rows;

	public PageResult(long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
