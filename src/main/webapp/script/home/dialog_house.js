
$(function(){
	$("#houseUploadDialogLk").click(function(){
		houseUpload_dialogOpen();
	});
});

//打开户型选择窗口
var houseUpload_dialog = null;
function houseUpload_dialogOpen(){
	if(dialogObj){
		dialogObj = null;
	}
	houseUpload_dialog = $("#house-upload-dialog-listLk" ).dialog({
	      resizable: false,
	      draggable: false,
	      position: {my: "left top", at: "left top", of: "#cont"},
	      height:$(".in_top").width() - 500,
	      width:$(".in_top").width() - 200,
	      modal: true
	 });
}
//户型选择框返回的url
function houseUpload_dialogCallbak(jsonStr){
	//alert("户型选择框返回的url:" + jsonStr);
	if(houseUpload_dialog){
		houseUpload_dialog.dialog("close");
	}
	
	//LoadUnit(jsonStr);
}