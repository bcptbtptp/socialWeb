package com.socialweb.search.controller;

import com.socialweb.search.pojo.Article;
import com.socialweb.search.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ArticleController
 * @Description TODO
 * @Author 42
 * @Date 2020/3/8 下午 7:10
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController
{

	@Autowired
	private ArticleService articleService;

	@RequestMapping(method= RequestMethod.POST)
	public Result save(@RequestBody Article article){
		articleService.save(article);
		return new Result(true, StatusCode.OK, "操作成功");
	}

	@RequestMapping(value = "/{key}/{page}/{size}", method = RequestMethod.GET)
	public Result findByKey(@PathVariable String key, @PathVariable int page, @PathVariable int size){
		Page<Article> pageData = articleService.findByKey(key, page, size);
		return new Result(true, StatusCode.OK, "查询成功", new PageResult<Article>(pageData.getTotalElements(), pageData.getContent()));
	}
}
