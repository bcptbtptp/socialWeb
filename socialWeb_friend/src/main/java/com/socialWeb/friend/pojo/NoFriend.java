package com.socialWeb.friend.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import org.springframework.data.annotation.Id;

/**
 * @ClassName Friend
 * @Description TODO
 * @Author 42
 * @Date 2020/3/11 上午 9:51
 * @Version 1.0
 */
@Entity
@Table(name="sc_nofriend")
@IdClass(NoFriend.class)
public class NoFriend implements Serializable
{
	@Id
	private String userid;
	@Id
	private String friendid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFriendid() {
		return friendid;
	}

	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}

}
