/**
 * 
 */

$(document).ready(function(){
	
	$("#login-but").click(function(){
		$.ajax({
			  type: "POST",
			  dataType: "json",
			  url: "/user/login",
			  data: {email : $("#inputEmail").val(), password : $("#inputPassword").val() , vcode : $("#inputVCode").val()},
			  success: function(data){
				  console.log(data);
				  $("#email-msg").html("");
				  $("#password-msg").html("");
				  $("#vcode-msg").html("");
				  $("#msg").html("");
				  if(data.code == 0){
					  $("#email-msg").html(data.emailMsg);
					  $("#password-msg").html(data.passwordMsg);
					  $("#vcode-msg").html(data.vcodeMsg);
					  $("#msg").html(data.msg);
					  $(".tip-msg").css("color","red");
				  } else{
					  console.log("登录成功：" + data);
				  }
			  }
		});
	});
	
});
