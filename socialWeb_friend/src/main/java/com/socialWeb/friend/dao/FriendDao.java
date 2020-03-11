package com.socialWeb.friend.dao;

import com.socialWeb.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @InterfaceName FriendDao
 * @Description TODO
 * @Author 42
 * @Date 2020/3/11 上午 10:04
 * @Version 1.0
 */
public interface FriendDao extends JpaRepository<Friend, String>
{
	/**
	 * 根据用户ID与被关注用户ID查询记录个数
	 * @param userid
	 * @param friendid
	 * @return
	 */
	public Friend findByUseridAndFriendid(String userid,String friendid);
	/**
 	* 更新为互相喜欢
 	* @param userid
 	* @param friendid
 	*/
	@Modifying
	@Query("update Friend f set f.islike=?3 where f.userid=?1 and f.friendid=?2")
	public void updateLike(String userid,String friendid,String islike);
}
