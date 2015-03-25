(function ($) {
    $.fn.extend({
		Tree: function (options) {
            var op = $.extend({ expand: true, expandAmount: 0, isOperate: true, treeItemParent: $("#treeDemo"), currentNum: 0, isParent: true, data: [] }, options);
            var $this = this;
			this.data = op.data;
			this.expand = op.expand;	//是否展开
			this.expandAmount = op.expandAmount;	//展开层数
			this.treeItemParent = op.treeItemParent;
			this.treeCollection = [];
			this.isParent = op.isParent;
			this.isOperate = op.isOperate;
			this.currentNum = op.currentNum;
			
			$this.AppendClildrenNodes($this, this.data, this.data.length, this.treeItemParent, this.currentNum, event || window.event);
        },
		AppendClildrenNodes: function($this, tree, treeNodeCount, parent, num, oEvent) {
			//alert(parent.html())
			var newUl;
			var hasChildNodes = false;
			var child;
			$.each(tree, function(i, treeItem) {
				hasChildNodes = false;
				//alert($("#treeDemo").html())
				treeItem["orderNum"] = num;
				treeItem["maxNum"] = treeNodeCount;
				//if ($this.isInit)
				treeItem["parent"] = parent;
				//treeItem["isParent"] = true;
				
				//alert(num)
				
				//alert(treeItem.childrenTreeList.length)
				//alert(treeNodeCount)
				var nodeObj = $this.AppendNodes($this, treeItem, event || window.event);
				if (treeItem.childrenTreeList.length > 1 && treeNodeCount > 1)
					newUl = $("<ul class=\"hasLine\"><\/ul>");
				else
					newUl = $("<ul><\/ul>");
				newUl.appendTo(nodeObj);
				if ($this.isParent) $this.isParent = false;
				//如果当前节点下有子节点，则跳出当前循环，进入子节点
				if (treeItem.childrenTreeList.length > 0)
				{
					hasChildNodes = true;
					child = treeItem.childrenTreeList;
					
					return false;
				}
				
				num++;
				
			});
			
			if (hasChildNodes)
			{
				//alert(newUl[0])
				$this.treeCollection.unshift({ treeParent: parent, data: tree, treeNodeNum: ++num, treeNodeCount: treeNodeCount });
				$this.AppendClildrenNodes($this, child, child.length, newUl, 0, event || window.event);
			}
			else
			{
				if ($this.treeCollection.length > 0)
				{
					var tempData = $this.treeCollection[0].data;
					var tempParent = $this.treeCollection[0].treeParent;
					var tempNum = $this.treeCollection[0].treeNodeNum;
					var tempNodeCount = $this.treeCollection[0].treeNodeCount;
					//alert(tempParent.html())
					tempData.shift();
					//$this.treeItemParent = $("root" + tempData.treeParent);
					$this.treeCollection.shift();
					$this.AppendClildrenNodes($this, tempData, tempNodeCount, tempParent, tempNum, event || window.event);
				}
			}
		},
		AppendNodes: function ($this, treeItem, oEvent) {
			
			//节点在该分支中的位置
			var position;
			if (treeItem.orderNum == 0 && $this.isParent)
				position = "Top";
			else if (treeItem.orderNum == treeItem.maxNum - 1 || treeItem.orderNum > treeItem.maxNum - 1)
				position = "Bottom";
			else
				position = "Middle";
			
			var hasChildren;	//该节点是否有子节点
			treeItem.childrenTreeList.length > 0 ? hasChildren = true : hasChildren = false;
			
			var expendClass = "btn ";
			var lastNodeClass = "";
			if (hasChildren)
			{
				if (!$this.expand)
					expendClass += "Close" + position;
				else
					expendClass += "Open" + position;
			}
			else
			{
				expendClass += position;
				//lastNodeClass = " class=\"Warehouse\"";
			}
			
			if (treeItem.nodeOrgType != "1")
				lastNodeClass = " class=\"Warehouse\"";
				
			//alert(treeItem.nodeId + "，" + treeItem.nodeName + "，" + treeItem.isParent + "，" + treeItem.parent[0] + "，" + treeItem.childrenTreeList.length);
			
			var operateBtnHtml = "";
			if ($this.isOperate)
				operateBtnHtml = "<span><img src=\"/VaultMan/Images/Basic/Icon_Add.gif\" alt=\"treeAdd\" \/><\/span><span><img src=\"/VaultMan/Images/Basic/Icon_Delete.gif\" alt=\"treeDel\" \/><\/span>";
			var nodeObj = $("<li" + lastNodeClass + " tid=\"" + treeItem.nodeTId + "\" typeId=\"" + treeItem.nodeOrgType + "\" id=\"tree" + treeItem.nodeId + "\"><span class=\"" + expendClass + "\"></span><a href=\"#\">" + treeItem.nodeName + "<\/a><\/li>");	//operateBtnHtml + 
			nodeObj.appendTo(treeItem.parent);
			$this.BindEvent($this, nodeObj, treeItem, event || window.event);
			
			return nodeObj;
		},
		BindEvent: function ($this, nodeObj, treeItem, oEvent) {
			//绑定“显示/隐藏”按钮事件
			if (nodeObj.attr("class") != "Warehouse")
			{
				var spanBtn = nodeObj.find("span:first");
				spanBtn.bind("click", function() {
					var ulObj = nodeObj.find("ul:first");
					var thisClass = $(this).attr("class");
					if (ulObj.is(":hidden"))
					{
						ulObj.slideDown(100);
						spanBtn.attr("class", thisClass.replace("Close", "Open"));
					}
					else
					{
						ulObj.slideUp(100);
						spanBtn.attr("class", thisClass.replace("Open", "Close"));
					}
					/*
					ulObj.toggle("fast",
						function() {
							if ($(this).is(":hidden"))
								spanBtn.attr("class", thisClass.replace("Open", "Close"));
							else
								spanBtn.attr("class", thisClass.replace("Close", "Open"));
						}
					);
					*/
				});
			}
			
			//绑定点击菜单项事件
			nodeObj.find("a:first").bind("click", function() {
				if ($(this).attr("class") == "TreeCurSelected" || $(this).attr("class") == "TreeNewSelected")
					return false;
				//alert(nodeObj.find("a.TreeCurSelected").length)
				var oldAction = $("#treeDemo a.TreeCurSelected");
				oldAction.removeClass("TreeCurSelected");
				
				oldAction = $("#treeDemo a.TreeNewSelected");
				oldAction.removeClass("TreeNewSelected");
				
				$(this).addClass("TreeCurSelected");
				
				onClick($(this).parent());
				//return false;
			});
			
			//绑定操作按钮事件
			if ($this.isOperate)
			{
				var imgButtons = nodeObj.find("img");
				imgButtons.bind("click", function(event) {
					event.stopPropagation();
					if ($(this).attr("alt") == "treeAdd")
					{
						$this.AddNode($this, nodeObj, treeItem, event || window.event);
						btnAddClick(treeItem);
						return false;
					}
					else
					{
						$.fn.Dialog({ funcName: "Confirm", message: "点击“确定”后该机构以下的其它机构则会被全部删除，确定要删除该机构吗？", confirm: { func: "btnDelClick('" + treeItem.nodeId + "')", isDisplay: true }, close: { isDisplay: false } });
						//$this.RemoveNode(nodeObj, treeItem);
						//btnDelClick(treeItem, function() {alert("a")});
						return false;
					}
				});
			}
		},
		AddNode: function ($this, nodeObj, treeItem, oEvent) {
			//nodeObj.attr("class", "");
			nodeObj.removeAttr("class");
			
			if (nodeObj.find("ul:first > li").length > 0)
			{
				var lastItemSpan = $("#" + nodeObj.find("ul:first > li:last").attr("id")).find("span:first");
				var lastItemSpanClass = lastItemSpan.attr("class");
				lastItemSpan.attr("class", lastItemSpanClass.replace("Bottom", "Middle"));
			}
			else
			{
				var nodeObjSpan = nodeObj.find("span:first");
				nodeObjSpan.attr("class", nodeObjSpan.attr("class").replace("btn ", "btn Open"));
				nodeObjSpan.bind("click", function() {
					var ulObj = nodeObj.find("ul:first");
					var thisClass = $(this).attr("class");
					if (ulObj.is(":hidden"))
					{
						ulObj.slideDown(100);
						spanBtn.attr("class", thisClass.replace("Close", "Open"));
					}
					else
					{
						ulObj.slideUp(100);
						spanBtn.attr("class", thisClass.replace("Open", "Close"));
					}
					/*
					ulObj.toggle("fast",
						function() {
							if ($(this).is(":hidden"))
								nodeObjSpan.attr("class", thisClass.replace("Open", "Close"));
							else
								nodeObjSpan.attr("class", thisClass.replace("Close", "Open"));
						}
					);
					*/
				});
			}
		},
		RemoveNode: function (nodeObj) {
			//alert(nodeObj[0] + "，" + treeItem.nodeId)
			//nodeObj.remove();
			
			var thisLevels = nodeObj.parent().find(" > li");
			if (thisLevels.length > 1) {
				var index = thisLevels.index(nodeObj);
				if (index == thisLevels.length - 1) {
					var span = nodeObj.prev().find("span:first");
					span.attr("class", nodeObj.find("span:first").attr("class"));
				}
			}
			else {
				var parentNode = nodeObj.parent().parent();
				parentNode.addClass("Warehouse");
				var span = parentNode.find("span:first");
				span.attr("class", span.attr("class").replace("btn Open", "btn "));
			}
			nodeObj.remove();
			
			$.fn.Close(null, event);
		}
	});
})(jQuery);

