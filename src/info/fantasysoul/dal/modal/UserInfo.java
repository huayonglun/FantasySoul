package info.fantasysoul.dal.modal;

import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Model;

import info.fantasysoul.dal.converter.UserConverter;
import info.fantasysoul.domain.User;
import info.fantasysoul.service.UserServiceImpl;


/**
 * 
 * 用户的数据模型及操作
 * @author Liu Yong
 *
 */
public class UserInfo extends Model<UserInfo> {
	
	static Logger logger = Logger.getLogger (UserServiceImpl.class.getName () ) ;
	
	public static final UserInfo dao = new UserInfo();
	
	/**
	 * 根据邮箱查找用户
	 * @param username 
	 * @return
	 */
	public User findByEmail(String email){
		UserInfo userInfo = dao.findFirst("select * from user_info where email=?", email);
		User user = UserConverter.DoToDomain(userInfo);
		logger.info("模型转换后用户信息为为：" + user);
		return user;
	}
	
	/**
	 * 根据 code 查找用户
	 * @param code
	 * @return
	 */
	public User findByCode(String code){
		UserInfo userInfo = dao.findFirst("select * from user_info where code=?", code);
		User user = UserConverter.DoToDomain(userInfo);
		logger.info("模型转换后用户信息为为：" + user);
		return user;
	}
	
	/**
	 * 根据 user_id 更改用户状态
	 * @param userId 
	 * @param state
	 * @return
	 */
	public boolean updateState(int userId, int state){
		UserInfo userInfo = dao.findById(userId).set("state", state);
		boolean isSucc = userInfo.update();
		return isSucc;
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
