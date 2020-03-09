package com.socialweb.search.service;

import com.socialweb.search.dao.ArticleDao;
import com.socialweb.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * @ClassName ArticleService
 * @Description TODO
 * @Author 42
 * @Date 2020/3/8 下午 7:05
 * @Version 1.0
 */
@Service
public class ArticleService
{

	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private IdWorker idWorker;

	public void save(Article article){
//		article.setId(idWorker.nextId() + "");
		articleDao.save(article);
	}

	public Page<Article> findByKey(String key, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return articleDao.findByTitleOrContentLike(key, key, pageable);
	}
}
