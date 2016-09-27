package info.fantasysoul.service;

import info.fantasysoul.domain.User;
import into.fantasysoul.exception.UserException;

public interface UserService {


	public void register(User registerRequest)  throws UserException;
	

	public User login(User loginRequest) throws UserException;
	
	/**
	 * 编辑资料
	 * @param user 要进行资料修改的用户
	 * @return 修改是否成功
	 */
	public boolean editProfile(User editRequest);
	
	/**
	 * 发送邮件
	 * @param email 发送的邮箱账号
	 * @param username 用户名
	 * @param code  激活码
	 */
	public void sentEmail(String email, String username, String code);
	
	/**
	 * 激活功能
	 * @param code 激活码
	 * @throws UserException
	 */
	public void active(String code) throws UserException;
}
