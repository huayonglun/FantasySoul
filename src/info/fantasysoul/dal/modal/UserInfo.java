package info.fantasysoul.dal.modal;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

import info.fantasysoul.dal.converter.UserConverter;
import info.fantasysoul.domain.User;



public class UserInfo extends Model<UserInfo> {
	public static final UserInfo dao = new UserInfo();
	
	/**
	 * 根据邮箱查找用户
	 * @param username 
	 * @return
	 */
	public User findByEmail(String email){
		UserInfo userInfo = dao.findFirst("select * from user_info where email=?", email);
		User user = UserConverter.DoToDomain(userInfo);
		return user;
	}
	
	public List<UserInfo> findAllUser(){
		
		List<UserInfo> users = dao.find("select * from user_info");
		StringBuilder sb = new StringBuilder();
		for(UserInfo user : users){
			System.out.println(user.getStr("username"));
			sb.append(user.getStr("username") + "----" + user.getStr("password") +"\n");
		}	
		return users;
	}	
}
