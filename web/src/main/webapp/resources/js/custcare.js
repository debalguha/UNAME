window.overlay_opacity=0.4;
$(document).ready(function(){
	bindAdd();
	bindEdit();
	bindDelete();
	function bindCategoryChange(){
		var dialog = $('.ZebraDialog_Body');
		dialog.find('select[name="categoryId"]').change(function(){
			showEntityList();
		});
	}
	function showEntityList(entityId){
		var dialog = $('.ZebraDialog_Body');
		var categoryId = dialog.find('select[name="categoryId"]').val();
		$.ajax( {
			type : "post",
			url : contextPath + "/admin/util/list/entity_bycategory.do",
			data : {categoryId:categoryId},
			async : false,
			dataType : "json",
			success : function(ret) {
				if (ret) {
					if (ret.code == "ok") {
						var html="";
						for(var i=0;i<ret.data.length;i++){
							if(entityId && ret.data[i].entityId == entityId){
								html += '<option value="' + ret.data[i].entityId + '" selected="selected">' + ret.data[i].entityName + '</option>'
							}else{
								html += '<option value="' + ret.data[i].entityId + '">' + ret.data[i].entityName + '</option>'
							}
						}
						dialog.find('select[name="entityId"]').html(html);
					}else{
						alert('Query Failed');
					}
				} else {
					alert('Query Failed');
				}
			},
			error : function() {
				alert('Query Failed');
			}
		});
	}
	function bindEdit(){
		$('a[name="edit"]').click(function(){
			var custCareId = $(this).attr('custCareId');
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
				                    	var data = getFormData('edit');
			                    		if(data == undefined){
			                    			throw "form is not valid.";
			                    		}
			                    		data.custCareId = custCareId;
			                    		if(updateCustcare(custCareId,data) == false){
			                    			throw "form save failed.";
			                    		}
			                    	}
			                    },
			                    {caption: 'Cancel', callback: function() { return false;}}
			                ]
		    });
			var dialog = $('.ZebraDialog_Body');
			dialog.find('#addType').remove();
			var fields = ["type","entityId","custCareNumDesc","custCareNum"];
			for ( var i = 0; i < fields.length; i++) {
				var field = fields[i];
				var value = $('#' + field + "_" + custCareId).text();
				dialog.find('input[name="' + field + '"]').val(value);
			}
			var entityId = $(this).attr('entityId');
			var categoryId = $(this).attr('categoryId');
			dialog.find('select[name="categoryId"]').val(categoryId);
			showEntityList(entityId);
			bindCategoryChange();
		});
	}
	function bindAdd(){
		$('a[name="addCustcare"]').click(function(){
			var html = $('#divPopup').html();
			var title = "Create Customer Care Info";
			$.Zebra_Dialog(html, {
		    	'overlay_opacity': overlay_opacity,
		    	'width': 540,
			    'type':     '',
			    'overlay_close' : false,
			    'title':    title,
			    'buttons':  [
			                    {caption: 'Submit', callback: function() { 
			                    		var data = getFormData('add');
			                    		if(data == undefined){
			                    			throw "form is not valid.";
			                    		}
			                    		if(createCustcare(data) == false){
			                    			throw "form save failed.";
			                    		}else{
			                    			var dialog = $('.ZebraDialog_Body');
			                    			dialog.find('input[name="custCareNumDesc"]').focus();
			                    			dialog.find('input[name="custCareNumDesc"]').val('');
			                    			dialog.find('input[name="custCareNum"]').val('');
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
			dialog.find('#updateType').remove();
			dialog.find('input[name="entityId"]').removeAttr('readonly');
			showEntityList();
			bindCategoryChange();
		});
	}
	function getFormData(actionType){
		var dialog = $('.ZebraDialog_Body');
		var type = '';
		if(actionType == 'add'){
			type = dialog.find('select[name="type"]').val();
		}else{
			type = dialog.find('input[name="type"]').val();
		}
		var entityId = dialog.find('select[name="entityId"]').val();
		var custCareNumDesc = dialog.find('input[name="custCareNumDesc"]').val();
		var custCareNum = dialog.find('input[name="custCareNum"]').val();
		if($.trim(entityId) == ''){
			alert('Please Select Entity Name.');
			return Please;
		}
		return {type:type,entityId:entityId,custCareNumDesc:custCareNumDesc,custCareNum:custCareNum};
	}
	function createCustcare(data){
		var flag = false;
		$.ajax( {
			type : "post",
			url : contextPath + "/admin/custcare/create.do",
			data : data,
			async : false,
			dataType : "json",
			success : function(ret) {
				if (ret) {
					if (ret.code == "ok") {
						flag = true;
						alert('Submit Successfully');
					}else if(ret.code == 'entity_notexist'){
						alert('The Entity Id is not existed');
					}else if(ret.code == 'main_exist'){
						alert('Main Type already exists for this entity, cannot add another');
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
	function updateCustcare(custCareId,data){
		data.custCareId = custCareId;
		var flag = false;
		$.ajax( {
			type : "post",
			url : contextPath + "/admin/custcare/update.do",
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
		$('#custcareList').find('a[name="delete"]').click(function(){
			var custCareId=$(this).attr('custCareId');
			if(confirm('Confirm To Delete?')){
				$.ajax( {
					type : "post",
					url : contextPath + "/admin/custcare/delete.do",
					data : {
						custCareId:custCareId
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