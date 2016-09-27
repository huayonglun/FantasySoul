/**
 * 
 */

$(document).ready(function(){
	
	$("#login-but").click(function(){
		$.ajax({
			  type: "POST",
			  dataType: "json",
			  url: "/user/signup",
			  data: {email : $("#inputEmail").val(), password : $("#inputPassword").val() , confirmPass : $("#inputConfPassword").val(), username : $("#username").val(), vcode : $("#inputVCode").val()},
			  success: function(data){
				  console.log(data);
				  $("#email-msg").html("");
				  $("#password-msg").html("");
				  $("#confirmPass-msg").html("");
				  $("#username-msg").html("");
				  $("#vcode-msg").html("");
				  $("#msg").html("");
				  if(data.code == 0){
					  $("#email-msg").html(data.emailMsg);
					  $("#password-msg").html(data.passwordMsg);
					  $("#confirmPass-msg").html(data.confirmPassMsg);
					  $("#username-msg").html(data.usernameMsg);
					  $("#vcode-msg").html(data.vcodeMsg);
					  $("#msg").html(data.msg);
					  $(".tip-msg").css("color","red");
				  } else{
					  $("#msg").html(data.msg);
					  $(".tip-msg").css("color","blue");
					  console.log("登录成功：" + data);
				  }
			  }
		});
	});
	
});
