package com.socialweb.search.pojo;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @ClassName Article
 * @Description TODO
 * @Author 42
 * @Date 2020/3/8 下午 6:44
 * @Version 1.0
 */
@Document(indexName = "socialweb_article", type = "article")
public class Article implements Serializable
{
	@Id
	private String id;

	@Field(index= true
			,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String title;

	@Field(index= true
			,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String content;

	private String state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
