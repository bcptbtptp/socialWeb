package com.socialWeb.article.service;

import com.socialWeb.article.dao.CommentDao;
import com.socialWeb.article.pojo.Comment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import util.IdWorker;

/**
 * @ClassName CommentService
 * @Description TODO
 * @Author 42
 * @Date 2020/3/8 下午 4:53
 * @Version 1.0
 */
public class CommentService
{

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private IdWorker idWorker;

	public void add(Comment comment){
		comment.set_id( idWorker.nextId()+"" );
		commentDao.save(comment);
	}

	public List<Comment> findByArticleid(String articleid){
		return commentDao.findByArticleid(articleid);
	}
}
