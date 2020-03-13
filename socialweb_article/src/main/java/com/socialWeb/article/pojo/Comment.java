package com.socialWeb.article.pojo;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;

/**
 * @ClassName Comment
 * @Description TODO
 * @Author 42
 * @Date 2020/3/8 下午 4:51
 * @Version 1.0
 */
public class Comment implements Serializable
{
	@Id
	private String _id;
	private String articleid;
	private String content;
	private String userid;
	private String parentid;
	private Date publishdate;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getArticleid() {
		return articleid;
	}

	public void setArticleid(String articleid) {
		this.articleid = articleid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public Date getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(Date publishdate) {
		this.publishdate = publishdate;
	}
}
