package com.socialWeb.friend.dao;

import com.socialWeb.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @InterfaceName NoFriendDao
 * @Description TODO
 * @Author 42
 * @Date 2020/3/11 下午 4:33
 * @Version 1.0
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String>
{
}
