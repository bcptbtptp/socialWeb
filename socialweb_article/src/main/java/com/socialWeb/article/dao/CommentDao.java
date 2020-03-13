package com.socialWeb.article.dao;

import com.socialWeb.article.pojo.Comment;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @InterfaceName CommentDao
 * @Description TODO
 * @Author 42
 * @Date 2020/3/8 下午 4:53
 * @Version 1.0
 */
public interface CommentDao extends MongoRepository<Comment, String>
{

	/**
	 * 根据文章ID查询评论列表
	 * @param articleid
	 * @return
	 */
	public List<Comment> findByArticleid(String articleid);
}
