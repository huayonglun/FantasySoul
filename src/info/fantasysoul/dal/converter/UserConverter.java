package info.fantasysoul.dal.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import info.fantasysoul.dal.modal.UserInfo;
import info.fantasysoul.domain.User;

public class UserConverter {
	
	public static User DoToDomain(UserInfo userInfo){
		if(userInfo == null){
			return null;
		}
		User user = new User();
		user.setUserId(userInfo.getInt("user_id"));
		user.setUsername(userInfo.getStr("username"));
		user.setPassword(userInfo.getStr("password"));
		user.setEmail(userInfo.getStr("email"));
		user.setCode(userInfo.getStr("code"));
		user.setState(userInfo.getInt("state") == null ? null : (userInfo.getInt("state") == 0 ? false : true));
		user.setSex(userInfo.getInt("sex") == null ? null : (userInfo.getInt("sex") == 0 ? 'F' : 'M'));
		user.setTelphone(userInfo.getStr("telphone"));
		user.setAddress(userInfo.getStr("address"));
		user.setInterest(userInfo.getStr("interest"));
		user.setGmtCreate(userInfo.getTimestamp("gmt_create"));
		user.setGmtModified(userInfo.getTimestamp("gmt_modified"));
		return user;
	}
	
	public static List<User> DoToDomains(List<UserInfo> userInfos){
		List<User> users = new ArrayList<User>();
		if(userInfos == null || userInfos.isEmpty()){
			return users;
		}
		for(UserInfo userInfo : userInfos){
			users.add(DoToDomain(userInfo));
		}
		return users;
	}
	
	public static UserInfo DomainToDo(User user){
		if(user == null){
			return null;
		}
		UserInfo userInfo = new UserInfo();
		userInfo.set("username", user.getUsername());
		userInfo.set("password", user.getPassword());
		userInfo.set("email", user.getEmail());
		userInfo.set("code", user.getCode());
		userInfo.set("state", user.getState() == null ? null : (user.getState() ? 1 : 0));
		userInfo.set("sex", user.getSex() == null ? null : (user.getSex() == 'M'? 1 : 0));
		userInfo.set("telphone", user.getTelphone());
		userInfo.set("address", user.getAddress());
		userInfo.set("password", user.getPassword());
		userInfo.set("gmt_create", new Date());
		userInfo.set("gmt_modified", new Date());
		return userInfo;
	}
	public static List<UserInfo> DomainToDo (List<User> users){
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		if(users == null || users.isEmpty()){
			return userInfos;
		}
		for(User user : users){
			userInfos.add(DomainToDo(user));
		}
		return userInfos;
	}
}
