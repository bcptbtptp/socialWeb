package com.socialweb.spit.dao;

import com.socialweb.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @InterfaceName SpitDao
 * @Description TODO
 * @Author 42
 * @Date 2020/3/8 下午 1:53
 * @Version 1.0
 */
public interface SpitDao extends MongoRepository<Spit, String>
{
	public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
