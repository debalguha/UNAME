window.overlay_opacity=0.4;
$(document).ready(function(){
	bindAdd();
	bindEditCategory();
	bindDelete();
	function bindEditCategory(){
		$('a[name="edit"]').click(function(){
			var entityId = $(this).attr('entityId');
			var fields = ["entityName","entityWebsite","entityEmail","entityDesc"];
			var html = $('#divPopup').html();
			var title = "Edit Entity";
			$.Zebra_Dialog(html, {
		    	'overlay_opacity': overlay_opacity,
		    	'width': 540,
			    'type':     '',
			    'overlay_close' : true,
			    'title':    title,
			    'buttons':  [
			                    {caption: 'Submit', callback: function() { 
				                    	var data = getFormData();
			                    		if(data == undefined){
			                    			throw "form is not valid.";
			                    		}
			                    		if(updateEntity(entityId,data) == false){
			                    			throw "form save failed.";
			                    		}
			                    	}
			                    },
			                    {caption: 'Cancel', callback: function() { return false;}}
			                ]
		    });
			var dialog = $('.ZebraDialog_Body');
			for ( var i = 0; i < fields.length; i++) {
				var field = fields[i];
				var value = $('#' + field + "_" + entityId).text();
				dialog.find('input[name="' + field + '"]').val(value);
			}
			var categoryId = $(this).attr('categoryId');
			dialog.find('select').val(categoryId);
		});
	}
	function bindAdd(){
		$('a[name="addEntity"]').click(function(){
			var html = $('#divPopup').html();
			var title = "Create Entity";
			$.Zebra_Dialog(html, {
		    	'overlay_opacity': overlay_opacity,
		    	'width': 540,
			    'type':     '',
			    'overlay_close' : true,
			    'title':    title,
			    'buttons':  [
			                    {caption: 'Submit', callback: function() { 
			                    		var data = getFormData();
			                    		if(data == undefined){
			                    			throw "form is not valid.";
			                    		}
			                    		if(createEntity(data) == false){
			                    			throw "form save failed.";
			                    		}else{
			                    			var dialog = $('.ZebraDialog_Body');
			                    			dialog.find('input').val('');
			                    			throw "form save successfully.";
			                    		}
			                    	}
			                    },
			                    {caption: 'Cancel', callback: function() { 
				                    	window.location.reload();
				                    	return false;
			                    	}
			                    }
			                ]
		    });
			var dialog = $('.ZebraDialog_Body');
			dialog.find('input[name="categoryId"]').removeAttr('readonly');
		});
	}
	function getFormData(){
		var dialog = $('.ZebraDialog_Body');
		var categoryId = dialog.find('select[name="categoryId"]').val();
		if($.trim(categoryId) == ''){
			alert('Please Enter Category Id.');
			return undefined;
		}
		var entityName = dialog.find('input[name="entityName"]').val();
		if($.trim(entityName) == ''){
			alert('Please Enter Entity Name.');
			return Please;
		}
		var entityWebsite = dialog.find('input[name="entityWebsite"]').val();
		var entityEmail = dialog.find('input[name="entityEmail"]').val();
		var entityDesc = dialog.find('input[name="entityDesc"]').val();
		/*
		if($.trim(entityWebsite) == ''){
			alert('Please Enter Entity Website.');
			return undefined;
		}
		if($.trim(entityEmail) == ''){
			alert('Please Enter Entity Email.');
			return undefined;
		}
		if($.trim(entityDesc) == ''){
			alert('Please Enter Entity Desc.');
			return undefined;
		}
		if($.trim(entityEmail) != '' && isValidEmail(entityEmail) == false){
			alert('Please Enter Valid Email.');
			return undefined;
		}
		*/
		return {categoryId:categoryId,entityName:entityName,entityWebsite:entityWebsite,entityEmail:entityEmail,entityDesc:entityDesc};
	}
	function createEntity(data){
		var flag = false;
		$.ajax( {
			type : "post",
			url : contextPath + "/admin/entity/create.do",
			data : data,
			async : false,
			dataType : "json",
			success : function(ret) {
				if (ret) {
					if (ret.code == "ok") {
						flag = true;
						alert('Submit Successfully');
					}else if(ret.code == 'category_notexist'){
						alert('The category id is not existed');
					}else{
						alert('Submit Failed');
					}
				} else {
					alert('Submit Failed');
				}
			},
			error : function() {
				alert('Submit Failed');
			}
		});
		return flag;
	}
	function updateEntity(entityId,data){
		data.entityId = entityId;
		var flag = false;
		$.ajax( {
			type : "post",
			url : contextPath + "/admin/entity/update.do",
			data : data,
			async : true,
			dataType : "json",
			success : function(ret) {
				if (ret) {
					if (ret.code == "ok") {
						flag = true;
						alert('Submit Successfully');
						window.location.reload();
					}else{
						alert('Submit Failed');
					}
				} else {
					alert('Submit Failed');
				}
			},
			error : function() {
				alert('Submit Failed');
			}
		});
		return flag;
	}
	function bindDelete(){
		$('#entityList').find('a[name="delete"]').click(function(){
			var entityId=$(this).attr('entityId');
			if(confirm('Confirm To Delete?')){
				$.ajax( {
					type : "post",
					url : contextPath + "/admin/entity/delete.do",
					data : {
						entityId:entityId
					},
					async : true,
					dataType : "json",
					success : function(ret) {
						if (ret) {
							if (ret.code == "ok") {
								alert('Delete Successfully');
								window.location.reload();
							}else{
								alert('Delete Failed');
							}
						} else {
							alert('Delete Failed');
						}
					},
					error : function() {
						alert('Delete Failed');
					}
				});
			}
		});
	}
});