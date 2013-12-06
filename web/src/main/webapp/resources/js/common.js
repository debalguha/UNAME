function isValidEmail(str) {
	var re = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	return re.test(str);
}
function bindPopup(html, opts, submitCallback) {
	var overlay_opacity = 0.6;
	var default_opts = {
		'overlay_opacity' : overlay_opacity,
		'width' : 350,
		'type' : '',
		'overlay_close' : false,
		'title' : '',
		'buttons' : [ {
			caption : 'Submit',
			callback : function() {
				submitCallback();
				return false;
			}
		}, {
			caption : 'Cancel',
			callback : function() {
				return false;
			}
		} ]
	}
	var opts = $.extend(default_opts, opts);
	$.Zebra_Dialog(html, opts);
	$('.ZebraDialog_Body').find('input[type="text"]').eq(0).focus();
}
function savePopupData(url, postData, opts) {
	$.ajax({
		type : "post",
		url : url,
		data : postData,
		async : true,
		dataType : "json",
		success : function(ret) {
			if (ret.code == "ok") {
				alert('Submit successfully');
				window.location.reload();
			} else if (ret.code == 'fail') {
				alert('Submit Failed');
			}
			if (opts && opts.success) {
				opts.success(ret);
			}
		},
		error : function() {
			alert('Submit Failed');
			if (opts && opts.error) {
				opts.error(ret);
			}
		},
		complete : function(ret) {
			if (opts && opts.complete) {
				opts.complete(ret);
			}
		}
	});
}