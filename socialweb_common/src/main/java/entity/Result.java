package entity;

/**
 * @ClassName Result
 * @Description TODO
 * @Author 42
 * @Date 2020/3/6 下午 2:36
 * @Version 1.0
 */
public class Result
{
	//是否成功
	private boolean flag;
    //返回码
	private Integer code;
	//返回信息
	private String message;
	//返回数据
	private Object data;

	public Result() {
	}

	public Result(boolean flag, Integer code, String message) {
		this.flag = flag;
		this.code = code;
		this.message = message;
	}

	public Result(boolean flag, Integer code, String message, Object data) {
		this.flag = flag;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
