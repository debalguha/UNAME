$(document).ready(function() {
	var overlay_opacity = 0.6;
	$('#btnCreateUserPage').click(function() {
		window.location.href = appContextPath + '/user/add.do';
	});
	function isValidEmail(str) {
		var re = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		return re.test(str);
	}
	function checkForm(formId, pageType) {
		var formObj = $('#' + formId);
		if (!requiredField(formId, 'username', 'Please enter username')) {
			return false;
		}
		if (pageType == 'addUser') {
			if (!requiredField(formId, 'password', 'Please enter password')) {
				return false;
			}
		}
		if (!requiredField(formId, 'email', 'Please enter email')) {
			return false;
		}
		if (!isValidEmail(formObj.find('input[name="email"]').val())) {
			alert('Please enter valid email');
			formObj.find('input[name="email"]').focus();
			return false;
		}
		if (!requiredField(formId, 'firstName', 'Please enter first name')) {
			return false;
		}
		if (!requiredField(formId, 'lastName', 'Please enter last name')) {
			return false;
		}
		if (!requiredField(formId, 'phone', 'Please enter phone')) {
			return false;
		}
		return true;
	}
	function collectFormData(formId) {
		var formObj = $('#' + formId);
		var fields = $('#' + formId + ' input,#' + formId + ' select,#' + formId + ' textarea');
		var data = {};
		fields.each(function(i) {
			data[$(this).attr('name')] = $(this).val();
		});
		return data;
	}
	function requiredField(formId, fieldName, tips) {
		var obj = $('#' + formId + ' input[name="' + fieldName + '"]');
		if (obj.val().trim() == '') {
			alert(tips);
			obj.focus();
			return false;
		}
		return true;
	}
	var sendingFlag = false;
	function postData(url, data, opts) {
		$.ajax({
			type : "post",
			url : url,
			data : data,
			async : true,
			dataType : "json",
			success : function(ret) {
				if (ret.code == "ok") {
					alert('Submit successfully');
					if (opts && opts.success) {
						opts.success(ret);
					}
				} else {
					alert('Submit Failed');
				}
			},
			error : function() {
				alert('Submit Failed');
				if (opts && opts.error) {
					opts.error();
				}
			},
			complete : function(ret) {
				if (opts && opts.complete) {
					opts.complete(ret);
				}
			}
		});
	}
	function bindAddFormEvent() {
		$('#addUserForm').submit(function() {
			function saveUser() {
				if (sendingFlag == true) {
					console.log('sumbit locked');
					return;
				}
				if (!checkForm('addUserForm', 'addUser')) {
					return;
				}
				var data = collectFormData('addUserForm');
				sendingFlag = true;
				postData(appContextPath + "/user/save.do", data, {
					success : function() {
						window.location.href = appContextPath + "/user/list.do?r=" + (+new Date());
					},
					complete : function() {
						sendingFlag = false;
					}
				});
			}
			try {
				saveUser();
			} catch (e) {
				alert('system error');
			}
			return false;
		});
	}
	function bindEditFormEvent() {
		$('#editUserForm').submit(function() {
			function updateUser() {
				if (sendingFlag == true) {
					console.log('sumbit locked');
					return;
				}
				if (!checkForm('editUserForm')) {
					return;
				}
				var data = collectFormData('editUserForm');
				sendingFlag = true;
				postData(appContextPath + "/user/update.do", data, {
					success : function() {
						window.location.href = appContextPath + "/user/list.do?r=" + (+new Date());
					},
					complete : function() {
						sendingFlag = false;
					}
				});
			}
			try {
				updateUser();
			} catch (e) {
				alert('system error');
			}
			return false;
		});
	}
	(function() {
		$('#btnEditUserPage').click(function() {
			var obj = $('input[name="userId"]:checked');
			if (!obj || obj.length == 0) {
				alert('Please select a user to edit');
				return;
			}
			var userId = obj.val();
			window.location.href = appContextPath + '/user/edit.do?id=' + userId;
		});
	})();
	(function() {
		$('#btnChangePassword').click(function() {
			var obj = $('input[name="userId"]:checked');
			if (!obj || obj.length == 0) {
				alert('Please select a user to change password');
				return;
			}
			var tds = obj.parent().siblings();
			var username = tds.eq(0).text();
			var userId = obj.val();
			bindPopup($('#changePwdPopup').html(), {
				title : 'Change Password'
			}, function() {
				var pwd = $('.ZebraDialog_Body input[name="password"]').val();
				var userId = $('.ZebraDialog_Body input[name="userId"]').val();
				savePopupData(appContextPath + "/user/change-password.do", {
					userId : userId,
					password : pwd
				});
			});
			var dialog = $('.ZebraDialog_Body');
			dialog.find('input[name="userId"]').val(userId);
			dialog.find('input[name="username"]').val(username);
		});
	})();
	bindAddFormEvent();
	bindEditFormEvent()
});