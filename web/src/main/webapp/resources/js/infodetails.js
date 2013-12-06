$(document).ready(function(){
	bindDelete();
	function bindDelete(){
		$('#infoList').find('a').click(function(){
			var infoId=$(this).attr('infoId');
			if(confirm('Confirm To Delete?')){
				$.ajax( {
					type : "post",
					url : contextPath + "/admin/infodetails/delete.do",
					data : {
						infoId:infoId
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