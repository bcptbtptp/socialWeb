package com.socialWeb.friend.dao;

import com.socialWeb.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @InterfaceName FriendDao
 * @Description TODO
 * @Author 42
 * @Date 2020/3/11 下午 3:52
 * @Version 1.0
 */
public interface FriendDao extends JpaRepository<Friend, String>
{
	public Friend findByUseridAndFriendid(String userid, String friendid);

	@Modifying
	@Query("update Friend f set f.islike=?3 where f.userid=?1 and f.friendid=?2")
	public void updateLike(String userid,String friendid,String islike);

	@Modifying
	@Query("delete from Friend f where f.userid=?1 and f.friendid=?2")
	public void deleteFriend(String userid,String friendid);


}
