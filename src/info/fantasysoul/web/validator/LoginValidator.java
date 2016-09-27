package info.fantasysoul.web.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator{

	private static final String msg = "message";
	
	@Override
	protected void validate(Controller c) {
		validateRegex("email", "\\w+@\\w+\\.\\w+", "emailMsg", "请输入正确格式的邮箱地址。");
		validateRequired("email", "emailMsg", "请输入您的邮箱。");
		validateRequired("password", "passwordMsg", "请输入您的密码。");
		validateRequired("vcode", "vcodeMsg", "请输入您的验证码。");

	}

	@Override
	protected void handleError(Controller c) {
		c.setAttr("code",0);
		c.renderJson();
	}

}
