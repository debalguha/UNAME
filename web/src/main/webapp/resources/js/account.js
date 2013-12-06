$(document).ready(function() {
	bindLogin();
	function bindLogin() {
		$('.login-form').submit(function() {
			var username = $('.login-form').find('input[name="username"]');
			var password = $('.login-form').find('input[name="password"]');
			if ($.trim(username.val()) == '') {
				username.focus();
				alert('Please Enter Username');
				return;
			}
			if ($.trim(password.val()) == '') {
				password.focus();
				alert('Please Enter Password');
				return;
			}
			$.ajax({
				type : "post",
				url : appContextPath + "/system/checklogin.do",
				data : {
					type : 'creator',
					username : username.val(),
					password : password.val(),
				},
				async : true,
				dataType : "json",
				success : function(ret) {
					if (ret) {
						if (ret.code == "ok") {
							window.location.href = appContextPath + '/user/list.do';
						} else if (ret.code == 'unvalid_user') {
							alert('Invalid Username or Password');
							username.focus();
						} else if (ret.code == 'inactive') {
							alert('You are inactive,please contact adminstrator');
						}
					} else {
						alert('Login Failed');
					}
				},
				error : function() {
					alert('Login Failed');
				}
			});
			return false;
		});
	}
});