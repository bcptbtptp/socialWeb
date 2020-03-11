package com.socialWeb.friend.service;

import com.socialWeb.friend.dao.FriendDao;
import com.socialWeb.friend.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName FriendService
 * @Description TODO
 * @Author 42
 * @Date 2020/3/11 上午 10:01
 * @Version 1.0
 */
@Service
@Transactional
public class FriendService
{
	@Autowired
	private FriendDao friendDao;

	public int addFriend(String userid, String friendid){
		//判断如果用户已经添加了这个好友，则不进行任何操作,返回0
		Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
		if(friend != null){
			return 0;
		}
		//向喜欢表中添加记录
		friend = new Friend();
		friend.setUserid(userid);
		friend.setFriendid(friendid);
		friend.setIsLike("0");
		friendDao.save(friend);
		//判断对方是否喜欢你，如果喜欢，将islike设置为1
		if(friendDao.findByUseridAndFriendid(friendid,userid) != null){
			friendDao.updateLike(userid,friendid,"1");
			friendDao.updateLike(friendid,userid,"1");
		}
		return 1;
	}
}
