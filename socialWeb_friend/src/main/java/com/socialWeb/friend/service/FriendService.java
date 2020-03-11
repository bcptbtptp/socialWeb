package com.socialWeb.friend.service;

import com.socialWeb.friend.client.UserClient;
import com.socialWeb.friend.dao.FriendDao;
import com.socialWeb.friend.dao.NoFriendDao;
import com.socialWeb.friend.pojo.Friend;
import com.socialWeb.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName FriendService
 * @Description TODO
 * @Author 42
 * @Date 2020/3/11 下午 3:53
 * @Version 1.0
 */
@Service
@Transactional
public class FriendService
{
	@Autowired
	private FriendDao friendDao;

	@Autowired
	private NoFriendDao noFriendDao;

	@Autowired
	private UserClient userClient;

	public int addFriend(String userid,String friendid){
		//判断如果用户已经添加了这个好友，则不进行任何操作,返回0
		Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
		if(friend != null){
			return 0;
		}
		//向喜欢表中添加记录
		friend=new Friend();
		friend.setUserid(userid);
		friend.setFriendid(friendid);
		friend.setIslike("0");
		friendDao.save(friend);
		userClient.incFollowcount(userid,1);//增加自己的关注数
		userClient.incFanscount(friendid,1);//增加对方的粉丝数
		//判断对方是否喜欢你，如果喜欢，将islike设置为1
		if(friendDao.findByUseridAndFriendid( friendid,userid) != null){
			friendDao.updateLike(userid,friendid,"1");
			friendDao.updateLike(friendid,userid,"1");
		}
		return 1;
	}
	/**
	 * 向不喜欢列表中添加记录
	 * @param userid
	 * @param friendid
	 */
	public void addNoFriend(String userid,String friendid){
		NoFriend noFriend=new NoFriend();
		noFriend.setUserid(userid);
		noFriend.setFriendid(friendid);
		noFriendDao.save(noFriend);
	}

	public void deleteFriend(String userid,String friendid){
		friendDao.deleteFriend(userid,friendid);
		friendDao.updateLike(friendid,userid,"0");
		addNoFriend(userid,friendid);//向不喜欢表中添加记录
		userClient.incFollowcount(userid,-1);//减少自己的关注数
		userClient.incFanscount(friendid,-1);//减少对方的粉丝数
	}
}
