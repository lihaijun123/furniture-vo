
						$(function(){
							var initJson1 = {};
							initJson1.fileExt = "*.jpg;*.png;*.jpeg";
							initJson1.fileDesc = "*.jpg;*.png;*.jpeg";
							initJson1.onComplete = picUploadOnComplete;
							initJson1.buttonImg = "/script/uploadFile/btn03.png";
							veUploadify(initJson1, "file_upload1");
						})
						function getfile_upload1Id(){
							return "picFileSn";
						}
						//文件上传成功后默认的回调方法
						function picUploadOnComplete(event, ID, fileObj, response, data){
							//文件上传空间对象的id
							var uploadFileId = event.currentTarget.id;
							//文件文本域id一般对应model中的一个属性
							var filedId = eval("get" + uploadFileId + "Id()");
							//返回文件id,url
							var returnJsonAry = eval("(" + response + ")");
							var rv = returnJsonAry[0];
							//新保存的id
							var newFileId = rv.fileId;
							var oldFileId = $("#" + filedId).val();
							$("#" + filedId).val(newFileId);
							//如果有图片
							$("#uploadMsg").find("img").attr({ "src": rv.fileUrl, "bigUrl": rv.fileUrl });
						}
