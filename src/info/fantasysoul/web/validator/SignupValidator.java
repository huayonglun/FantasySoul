package info.fantasysoul.web.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class SignupValidator  extends Validator{

	private static final String msg = "message";
	
	@Override
	protected void validate(Controller c) {
		validateRequired("vcode", "vcodeMsg", "请输入您的验证码。");
		validateString("username", 3, 10, "usernameMsg", "用户名长度必须在3~10之间！");
		validateRequired("username", "usernameMsg", "请输入您的昵称。");
		validateEqualField("password", "confirmPass", "confirmPassMsg", "两次密码输入不一致");
		validateRequired("confirmPass", "confirmPassMsg", "请输入您的确认密码。");
		validateString("password", 3, 10, "passwordMsg", "密码长度必须在3~10之间！");
		validateRequired("password", "passwordMsg", "请输入您的密码。");
		validateRegex("email", "\\w+@\\w+\\.\\w+", "emailMsg", "请输入正确格式的邮箱地址。");
		validateRequired("email", "emailMsg", "请输入您的邮箱。");
	}

	@Override
	protected void handleError(Controller c) {
		c.setAttr("code",0);
		c.renderJson();
	}

}
