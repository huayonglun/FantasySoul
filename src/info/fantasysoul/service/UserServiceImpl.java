package info.fantasysoul.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.Session;

import org.apache.log4j.Logger;

import info.fantasysoul.commons.mail.Mail;
import info.fantasysoul.commons.mail.MailUtils;
import info.fantasysoul.commons.utils.CommonUtils;
import info.fantasysoul.dal.converter.UserConverter;
import info.fantasysoul.dal.modal.UserInfo;
import info.fantasysoul.domain.User;
import into.fantasysoul.exception.UserException;

public class UserServiceImpl implements UserService {

	static Logger logger = Logger.getLogger (UserServiceImpl.class.getName () ) ;
	
	@Override
	public void register(User registerRequest)  throws UserException {
		//校验邮箱
		User user = UserInfo.dao.findByEmail(registerRequest.getEmail());
		if(user != null){
			throw new UserException(" 当前邮箱已被使用，请您尝试其他邮箱。");
		}
		registerRequest.setCode(CommonUtils.uuid() + CommonUtils.uuid());
		registerRequest.setState(false);
		UserInfo userInfo = UserConverter.DomainToDo(registerRequest);
		userInfo.save();
	}

	@Override
	public User login(User loginRequest)  throws UserException {
		/**
		 * 1. 使用 email 查询，得到user
		 * 2. 如果 user 为null，抛出异常（该邮箱并未注册）
		 * 3. 比较请求和user的密码，若不同抛出异常（密码错误）
		 * 4. 查看用户的状态，若为 false，抛出异常（尚未激活）
		 * 5. 返回 user
		 * 
		 */
		logger.info("进入邮箱验证");
		User user = UserInfo.dao.findByEmail(loginRequest.getEmail());
		logger.info("得到user" + user);
		if(user == null){
			throw new UserException("当前邮箱地址无效，请重新输入。");
		}
		logger.info("邮箱验证已完成！");
		if(!user.getPassword().equals(loginRequest.getPassword())){
			throw new UserException("您输入的密码错误，请重新输入。");
		}
		logger.info("密码验证已完成！");
		if(!user.getState()){
			
			try {
				String hrefStr = "/user/sentEmail?email=" + URLEncoder.encode(user.getEmail(), "utf-8") + "&username=" + user.getUsername() + "&code=" + user.getCode();
				String tipMsg = "onclick=\"alert('邮件已发送，请查看邮箱，完成激活后登录。')\"";
				throw new UserException("用户尚未激活，请查收激活邮件。邮件未收到？请点击：<a href='" + hrefStr + "' " + tipMsg + ">请求发送激活邮件</a>");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		logger.info("激活验证已完成！");
		return user;
	}

	@Override
	public boolean editProfile(User user) {
		return false;
	}
	
	@Override
	public void sentEmail(String email, String username, String code){
		
		// 获取配置文件内容
		Properties props = new Properties();
		try {
			props.load(this.getClass().getClassLoader()
					.getResourceAsStream("email_template.properties"));
		} catch (IOException e1) {
			logger.info("邮件配置文件加载出错");
		}
		String host = props.getProperty("host");//获取服务器主机
		String uname = props.getProperty("uname");//获取用户名
		String pwd = props.getProperty("pwd");//获取密码
		String from = props.getProperty("from");//获取发件人
		String to = email;//获取收件人
		String subject = props.getProperty("subject");//获取主题
		String content = props.getProperty("content");//获取邮件内容
		content = MessageFormat.format(content, username, code);//替换{0},{1}
		
		Session session = MailUtils.createSession(host, uname, pwd);//得到session
		Mail mail = new Mail(from, to, subject, content);//创建邮件对象
		try {
			MailUtils.send(session, mail);//发邮件！
			logger.info("邮箱发送成功！Email=" + email);
		} catch (Exception e) {
			logger.info("邮箱发送异常！Email=" + email);
			logger.info(e.getMessage());
		}		
	}

	public void active(String code) throws UserException{
		User user = UserInfo.dao.findByCode(code);
		if(user == null){
			throw new UserException("激活码无效。");
		}
		if(user.getState()){
			throw new UserException("您的账号已激活，不用重复激活。请前往主页登录。");
		}
		UserInfo.dao.updateState(user.getUserId(), 1);
	}
}
