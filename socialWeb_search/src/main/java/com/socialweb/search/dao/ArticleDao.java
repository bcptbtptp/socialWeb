package com.socialweb.search.dao;

import com.socialweb.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @InterfaceName ArticleDao
 * @Description TODO
 * @Author 42
 * @Date 2020/3/8 下午 7:05
 * @Version 1.0
 */
public interface ArticleDao extends ElasticsearchRepository<Article,String>
{

	/**
	 * 检索
	 * @param
	 * @return
	 */
	public Page<Article> findByTitleOrContentLike(String title, String
			content, Pageable pageable);
}
