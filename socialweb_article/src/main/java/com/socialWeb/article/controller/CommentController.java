package com.socialWeb.article.controller;

import com.socialWeb.article.pojo.Comment;
import com.socialWeb.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CommentController
 * @Description TODO
 * @Author 42
 * @Date 2020/3/8 下午 4:55
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController
{

	private CommentService commentService;

	@RequestMapping(method= RequestMethod.POST)
	public Result save(@RequestBody Comment comment){
		commentService.add(comment);
		return new Result(true, StatusCode.OK, "提交成功 ");
	}

	@RequestMapping(value="/article/{articleid}",method= RequestMethod.GET)
	public Result findByArticleid(@PathVariable String articleid){
		return new Result(true, StatusCode.OK, "查询成功",
				commentService.findByArticleid(articleid));
	}
}
