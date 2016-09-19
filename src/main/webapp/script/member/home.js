$(function(){
	var menuList = ['voexhibitions','vofunction_room','voexhibition_order','vohelp'];
	for(var i = 0;i<menuList.length;i++){
		(function(el){
			var func = null;

			$('#'+el).hover(function(){
				//高亮
				var _this = $(this);
				func = window.setTimeout(function(){
					for(var i = 0;i<menuList.length;i++){
						$('#'+menuList[i]+'_div').hide();//关闭所有菜单
					}
					$('#'+_this[0].id+'_div').show();//显示相应菜单
				},100);
			},function(){
				var _this = $(this);
				for(var i = 0;i<menuList.length;i++){
					if(func){
						window.clearTimeout(func);
					}
					$('#'+_this[0].id+'_div').hide();
				}
			});
		})(menuList[i]);
	}


			//ajax请求菜单树
			$.ajax({
   				type: "GET",
   				url: "/menutree/data/" + new Date().getTime(),
				async: false,
   				success: menuTreeAjaxCallbak
			});
				/**实名验证*/
//			$("a[name='realname']").bind("click",function(){
//				$.ajax({
//					type: "POST",
//					url: "/realNameAuth/toRealNameAuth",
//					data: "",
//					success: function(msg){
//						alert( "Data Saved: " + msg );
//					}
//					});
//				});
            });
			//生成菜单树结构
			function menuTreeAjaxCallbak(responseText, textStatus, XMLHttpRequest){
				var treeJson = eval("(" + responseText + ")");
				var treeHtmlStr = "";
				//计算父节点个数
				var count_1 = 0;
				for (var parentTree in treeJson) {
					count_1 ++;
				}
				var count_2 = 0;
				var menuClass = "menu openmenu";
				for (var parentTree in treeJson) {
					var parentTreeName = parentTree;
					//改变最后一个父节点样式
					if(count_2 === count_1 - 1){
						menuClass = "menu openmenu last";
					}
					treeHtmlStr += "<div class='" + menuClass + "' id='menu"+(count_2+1)+"' url='" + parentTreeName.split("_")[1] + "'>" + parentTreeName.split("_")[0] + "</div>";
					treeHtmlStr += "	<div class='childmenu'>";
					treeHtmlStr += "		<ul>";
					var childsTreeAry = treeJson[parentTreeName];
					for (var childTreeIndex = 0; childTreeIndex < childsTreeAry.length; childTreeIndex ++) {
						var childTreeJson = childsTreeAry[childTreeIndex];
						var childTreeNodeName = childTreeJson["RESOURCE_DISPLAY_NAME"];
						var childNodeLinkId = childTreeJson["RESOURCE_LINK_ID"];
						var childNOteLinkUrl = childTreeJson["RESOURCE_URL"];
              			treeHtmlStr += "		<li><a href='" + childNOteLinkUrl + "' id='" + childNodeLinkId + "'>" + childTreeNodeName + "</a></li>";
					}
					treeHtmlStr += "		</ul>";
					treeHtmlStr += "	</div>";
					count_2 ++;
				}
				$("#treel").html(treeHtmlStr);
                //控制菜单动作
				//页面初始状态：菜单全部收缩
				$(".menu").each(function(){
					controlMenuDisplay($(this), null);
				});
				$(".menu").mouseover(function(){
					controlMenuShow($(this), "addClass", "menuHover");
				});
				$(".menu").mouseout (function(){
					controlMenuShow($(this), "removeClass", "menuHover");
				});
				$(".menu").click(function(){
					var selfMenuObj = $(this);
					controlMenuShow(selfMenuObj, "removeClass", "menuHover");
	            	controlMenuDisplay(selfMenuObj, null);
					//关闭除了当前的其他展开的菜单
					$(".menu").each(function(){
						controlMenuDisplay($(this), selfMenuObj);
					});
					var url = selfMenuObj.attr("url");
					if(url !== "-1"){
						window.location.href = url;
					}
	            });
			}

            function menu_show(id){
				$("a[id^='menu']").removeClass("now");
                $("#menu" + id).addClass("now");
				$("#menu" + id).parent().parent().parent().css("display", "block");
				$("#menu" + id).parent().parent().parent().prev().addClass("openmenu");
            }
			//控制菜单鼠标移动上去外观发生的改变
			function controlMenuShow(curMenuObj, action, cssName){
				var val = curMenuObj.next(".childmenu").css("display");
				if (val == "none") {
					if(action === "addClass"){
						curMenuObj.addClass(cssName);
					}
					else {
						curMenuObj.removeClass(cssName);
					}
	            }
			}
			//控制菜单关闭和展开
			//curMenuObj：当前遍列到的菜单
			//selfMenuObj：当前点击的菜单
			function controlMenuDisplay(curMenuObj, selfMenuObj){
				if(null == selfMenuObj){
					var val = curMenuObj.next(".childmenu").css("display");
	                if (val == "none") {
	                	curMenuObj.next(".childmenu").css("display", "block");
	                	curMenuObj.addClass("openmenu");
	                }
	                else {
	                	curMenuObj.next(".childmenu").css("display", "none");
	                    curMenuObj.removeClass("openmenu");
	                }
				}
				else
				{
					//关闭除了当前的其他展开的菜单
					var val = curMenuObj.next(".childmenu").css("display");
	                if(val === "block" && curMenuObj.html() != selfMenuObj.html()){
	                	curMenuObj.next(".childmenu").css("display", "none");
	                	curMenuObj.removeClass("openmenu");
	                }
				}
			}