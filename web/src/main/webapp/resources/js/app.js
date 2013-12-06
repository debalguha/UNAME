$(document).ready(function() {
	handleAccess();
	handleTeam();
	handleAppAccess();
	function handleAppAccess() {
		$('#btnCreateAppAccess').click(function() {
			bindPopup($('#createAppAccessPopup').html(), {
				title : 'Create Application & Access'
			}, function() {
				var appName = $('.ZebraDialog_Body select[name="application"]').val();
				var accessId = $('.ZebraDialog_Body select[name="access"]').val();
				savePopupData(appContextPath + "/app-access/save.do", {
					appName : appName,
					accessId : accessId
				});
			});
		});
		$('#btnEditAppAccess').click(function() {
			var id = $('input[name="app_access"]:checked').val();
			if (!id) {
				alert('Please select an record to edit');
				return;
			}
			var tds = $('input[name="app_access"]:checked').parent().siblings();
			var appName = tds.eq(0).text();
			var access_id = $('input[name="app_access"]:checked').attr('access_id');
			bindPopup($('#editAppAccessPopup').html(), {
				title : 'Edit Application & Access'
			}, function() {
				var appName = $('.ZebraDialog_Body select[name="application"]').val();
				var accessId = $('.ZebraDialog_Body select[name="access"]').val();
				savePopupData(appContextPath + "/app-access/update.do", {
					id : id,
					appName : appName,
					accessId : accessId
				});
			});
			var dialog = $('.ZebraDialog_Body');
			dialog.find('input[name="id"]').val(id);
			dialog.find('select[name="application"]').val(appName);
			dialog.find('select[name="access"]').val(access_id);
		});
		$('#btnDeleteAppAccess').click(function() {
			var id = $('input[name="app_access"]:checked').val();
			if (!id) {
				alert('Please select an Application & Access to delete');
				return;
			}
			if (!confirm('Are you sure to delete it?')) {
				return;
			}
			deleteItem(appContextPath + "/app-access/delete.do", {
				id : id
			});
		});
	}
	function handleAccess() {
		$('#btnCreateAccess').click(function() {
			bindPopup($('#createAccessPopup').html(), {
				title : 'Create Access'
			}, function() {
				var name = $('.ZebraDialog_Body input[name="name"]').val();
				var code = $('.ZebraDialog_Body input[name="code"]').val();
				savePopupData(appContextPath + "/access/save.do", {
					name : name,
					code : code
				});
			});
		});
		$('#btnEditAccess').click(function() {
			var accessId = $('input[name="access"]:checked').val();
			if (!accessId) {
				alert('Please select an Access to edit');
				return;
			}
			var tds = $('input[name="access"]:checked').parent().siblings();
			var name = tds.eq(0).text();
			var code = tds.eq(1).text();
			bindPopup($('#editAccessPopup').html(), {
				title : 'Edit Access'
			}, function() {
				var accessId = $('.ZebraDialog_Body input[name="accessId"]').val();
				var name = $('.ZebraDialog_Body input[name="name"]').val();
				var code = $('.ZebraDialog_Body input[name="code"]').val();
				savePopupData(appContextPath + "/access/update.do", {
					accessId : accessId,
					name : name,
					code : code
				});
			});
			var dialog = $('.ZebraDialog_Body');
			dialog.find('input[name="accessId"]').val(accessId);
			dialog.find('input[name="name"]').val(name);
			dialog.find('input[name="code"]').val(code);
		});
		$('#btnDeleteAccess').click(function() {
			var accessId = $('input[name="access"]:checked').val();
			if (!accessId) {
				alert('Please select an Access to delete');
				return;
			}
			if (!confirm('Are you sure to delete it?')) {
				return;
			}
			deleteItem(appContextPath + "/access/delete.do", {
				accessId : accessId
			});
		});
	}
	function handleTeam() {
		$('#btnCreateTeam').click(function() {
			bindPopup($('#createTeamPopup').html(), {
				title : 'Create Team'
			}, function() {
				var name = $('.ZebraDialog_Body input[name="name"]').val();
				var code = $('.ZebraDialog_Body input[name="code"]').val();
				savePopupData(appContextPath + "/team/save.do", {
					name : name,
					code : code
				});
			});
		});
		$('#btnEditTeam').click(function() {
			var teamId = $('input[name="team"]:checked').val();
			if (!teamId) {
				alert('Please select a team to edit');
				return;
			}
			var tds = $('input[name="team"]:checked').parent().siblings();
			var name = tds.eq(0).text();
			var code = tds.eq(1).text();
			bindPopup($('#editTeamPopup').html(), {
				title : 'Edit Team'
			}, function() {
				var teamId = $('.ZebraDialog_Body input[name="teamId"]').val();
				var name = $('.ZebraDialog_Body input[name="name"]').val();
				var code = $('.ZebraDialog_Body input[name="code"]').val();
				savePopupData(appContextPath + "/team/update.do", {
					teamId : teamId,
					name : name,
					code : code
				});
			});
			var dialog = $('.ZebraDialog_Body');
			dialog.find('input[name="teamId"]').val(teamId);
			dialog.find('input[name="name"]').val(name);
			dialog.find('input[name="code"]').val(code);
		});
	}
	function deleteItem(url, postData) {
		$.ajax({
			type : "post",
			url : url,
			data : postData,
			async : true,
			dataType : "json",
			success : function(ret) {
				if (ret.code == "ok") {
					alert('Delete successfully');
					window.location.reload();
				} else if (ret.code == 'used') {
					alert('This record is used,can not delete it');
				} else if (ret.code == 'fail') {
					alert('Delete Failed');
				}
			},
			error : function() {
				alert('Delete Failed');
			}
		});
	}
});