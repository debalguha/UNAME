window.overlay_opacity=0.4;
$(document).ready(function(){
	bindAddCategory();
	bindEditCategory();
	bindDeleteCategory();
	function bindEditCategory(){
		$('a[name="edit"]').click(function(){
			var categoryId = $(this).attr('categoryId');
			var categoryName = $('#td' + categoryId).text();
			var html = $('#divPopup').html();
			var title = "Edit Category";
			$.Zebra_Dialog(html, {
		    	'overlay_opacity': overlay_opacity,
		    	'width': 540,
			    'type':     '',
			    'overlay_close' : true,
			    'title':    title,
			    'buttons':  [
			                    {caption: 'Submit', callback: function() { 
			                    		var dialog = $('.ZebraDialog_Body');
			                    		var categoryName = dialog.find('input').val();
			                    		if($.trim(categoryName) == ''){
			                    			alert('Please Enter Category Name.');
			                    			throw "form is not valid.";
			                    		}
			                    		updateCategory(categoryId,categoryName);
			                    	}
			                    },
			                    {caption: 'Cancel', callback: function() { return false;}}
			                ]
		    });
			var dialog = $('.ZebraDialog_Body');
			dialog.find('input').val(categoryName);
		});
	}
	function bindAddCategory(){
		$('a[name="addCategory"]').click(function(){
			var html = $('#divPopup').html();
			var title = "Create Category";
			$.Zebra_Dialog(html, {
		    	'overlay_opacity': overlay_opacity,
		    	'width': 540,
			    'type':     '',
			    'overlay_close' : true,
			    'title':    title,
			    'buttons':  [
			                    {caption: 'Submit', callback: function() { 
			                    		var dialog = $('.ZebraDialog_Body');
			                    		var categoryName = dialog.find('input').val();
			                    		if($.trim(categoryName) == ''){
			                    			alert('Please Enter Category Name.');
			                    			throw "form is not valid.";
			                    		}
			                    		createCategory(categoryName);
			                    	}
			                    },
			                    {caption: 'Cancel', callback: function() { return false;}}
			                ]
		    });
		});
	}
	function createCategory(categoryName){
		$.ajax( {
			type : "post",
			url : contextPath + "/admin/category/create.do",
			data : {
				categoryName:categoryName
			},
			async : true,
			dataType : "json",
			success : function(ret) {
				if (ret) {
					if (ret.code == "ok") {
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
	}
	function updateCategory(categoryId,categoryName){
		$.ajax( {
			type : "post",
			url : contextPath + "/admin/category/update.do",
			data : {
				categoryId:categoryId,
				categoryName:categoryName
			},
			async : true,
			dataType : "json",
			success : function(ret) {
				if (ret) {
					if (ret.code == "ok") {
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
	}
	function bindDeleteCategory(){
		$('#categoryList').find('a[name="delete"]').click(function(){
			var categoryId=$(this).attr('categoryId');
			if(confirm('Confirm To Delete?')){
				$.ajax( {
					type : "post",
					url : contextPath + "/admin/category/delete.do",
					data : {
						categoryId:categoryId
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