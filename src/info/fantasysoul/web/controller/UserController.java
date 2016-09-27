package info.fantasysoul.web.controller;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import info.fantasysoul.commons.render.MyCaptchaRender;
import info.fantasysoul.commons.utils.CommonUtils;
import info.fantasysoul.commons.vcode.utils.VerifyCode;
import info.fantasysoul.domain.User;
import info.fantasysoul.service.UserService;
import info.fantasysoul.service.UserServiceImpl;
import info.fantasysoul.web.validator.LoginValidator;
import info.fantasysoul.web.validator.SignupValidator;
import into.fantasysoul.exception.UserException;

public class UserController extends Controller {
	static Logger logger = Logger.getLogger (UserController.class.getName () ) ;
	UserService userService = new UserServiceImpl();
	
	public void index() {
		renderText("主页");
	}
	
	@Before(LoginValidator.class)
	public void login(){
//		if(getPara("email") == null || getPara("email").trim().isEmpty()){
//			logger.info("邮箱为空。");
//			setAttr("code",0);
//			setAttr("msg", "邮箱不能为空。");
//			renderJson();
//			return;
//		}
//		
//		if(!getPara("email").matches("\\w+@\\w+\\.\\w+")) {
//			setAttr("code",0);
//			setAttr("msg", "请输入正确格式的邮箱地址。");
//			renderJson();
//			return;
//		}
		
//		if(getPara("password") == null || getPara("password").trim().isEmpty()){
//			logger.info("密码为空。");
//			setAttr("code",0);
//			setAttr("msg", "请输入您的密码。");
//			renderJson();
//			return;
//		}
		
//		if(inputRandomCode == null || inputRandomCode.trim().isEmpty()){
//			logger.info("验证码为空。");
//			setAttr("code",0);
//			setAttr("msg", "请输入您的验证码。！");
//			renderJson();
//			return;
//		}
		String inputRandomCode = getPara("vcode");
		boolean validate = MyCaptchaRender.validate(this, inputRandomCode);
		if(!validate){
			logger.info("验证码错误。inputRandomCode=" + inputRandomCode);
			setAttr("code",0);
			setAttr("msg", "验证码错误，请重新输入。");
			renderJson();
			return;
		}		
		logger.info("CaptchaRender.validate=" + validate + ";inputRandomCode=" + inputRandomCode);
		
		User loginRequest = new User();
		loginRequest.setEmail(getPara("email"));
		loginRequest.setPassword(getPara("password"));

		User user = null;
		try{
			user = userService.login(loginRequest);
			logger.info("获取到用户信息：" + user);
			setSessionAttr("session_user", user);
			setAttr("code",1);
			renderJson();
		} catch(UserException userExe){
			logger.info("用户登录异常："+ userExe.getMessage());
			setAttr("msg", userExe.getMessage());
			setAttr("code",0);
			renderJson();
		} catch(Exception e){
			logger.info(e.getMessage());
		}
		
			
	}
	
	@Before(SignupValidator.class)
	public void signup() throws Exception{

		//封装表单
		
		User registerRequest = CommonUtils.toBean(getParaMap(), User.class);
		
		//验证码校验
		String inputRandomCode = getPara("vcode");
		boolean validate = MyCaptchaRender.validate(this, inputRandomCode);
		if(!validate){
			logger.info("验证码错误。inputRandomCode=" + inputRandomCode);
			setAttr("code",0);
			setAttr("msg", "验证码错误，请重新输入。");
			renderJson();
			return;
		}		
		logger.info("CaptchaRender.validate=" + validate + ";inputRandomCode=" + inputRandomCode);
		

		
		/*
		 * 调用service的register方法
		 * 
		 */
		try{
			userService.register(registerRequest);
		} catch (UserException e){
			setAttr("code",0);
			setAttr("msg", e.getMessage());
			renderJson();
			return;
		}
		/*
		 * 发邮件
		 */

		userService.sentEmail(registerRequest.getEmail(), registerRequest.getUsername(), registerRequest.getCode());
		setAttr("code",1);
		setAttr("msg", "恭喜，注册成功！请马上到邮箱激活");
		renderJson();
		
	}
	
	public void sentEmail(){
		userService.sentEmail(getPara("email"), getPara("username"), getPara("code"));
		redirect("/login.html");
	}
	
	  /**
	   * 验证码
	   */
	public void captcha(){
		render(new MyCaptchaRender(90,30,4,true));
	}
	
	/**
	 * 激活
	 */
	public void active(){
		/*
		 * 1. 获取参数激活码
		 * 2. 调用 service 方法完成激活
		 * 3. 保存异常信息或保存成功信息返回JSON
		 */
		try{
			userService.active(getPara("code"));
			setAttr("code",1);
			setAttr("msg", "恭喜，激活成功！请前往主页登录。");
		} catch (UserException e) {
			setAttr("code",0);
			setAttr("msg", e.getMessage());
			
		}
		renderJson();
	}
}
