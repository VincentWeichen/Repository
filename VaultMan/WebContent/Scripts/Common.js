$(document).ready(function(){
	/* 初始化导航菜单 */
	$(".Header-Navigation li").each(function(i, object) {
		var liItemObject = $(object);
		if (liItemObject.find("ul").length > 0)
		{
			liItemObject.find("a:first").attr("class", "haveSubItem");
			liItemObject.find("li:first").attr("class", "first");
			liItemObject.bind("mouseover", function() {
				$(this).find("ul:first").css("display", "block");
			}).bind("mouseleave", function() {   
				$(this).find("ul:first").css("display", "none");
			}); 
		}
	});
	
	/* 初始化树形菜单 */
	$(".Tree-Content li").each(function(i, object) {
		var liItemObject = $(object);
		var liItemDiv = liItemObject.find("div.TreeItem-Div:first");
		if (liItemObject.find("ul").length > 0)
		{
			liItemObject.addClass("Expand")
			//liItemDiv.addClass("Expand");
			liItemObject.find("a:first").bind("click", function() {
				$(this).closest("div.TreeItem-Div").next().slideToggle("normal");
				$(this).closest("li").toggleClass("Expand").addClass("HasSubmit");
			});
		}
		liItemDiv.append("<div style=\"clear:both\"><\/div>").bind("mouseover", function() {
				$(this).find("div.TreeItem-Option:first").css("display", "block");
			}).bind("mouseleave", function() {   
				$(this).find("div.TreeItem-Option:first").css("display", "none");
			}).bind("click", function() {
				$(".Tree-Content li.TreeItem-checked").removeClass("TreeItem-checked");
				//$(this).closest("li").addClass("TreeItem-checked");
			});         
	});
	
	var treeWidth = 0;
	//validateTree();
	//alert(thisPage)
	if (thisPage)
		thisPage == "" ? treeWidth = 0 : treeWidth = 257;
	
	$("#Content").height($(window).height() - 116 - 45).width($(window).width());
	$("#Content-Tree").height($(window).height() - 116 - 45 - 7);
	
	if ($("#contentBoxLarger")[0])
	{
		$("#contentBoxLarger").width($(window).width() - 50);
		$("#content-content").width($("#contentBoxLarger").width() - 40);
	}
	else
	{
		//$("#contentBox").width($(window).width() - 305);
		//$("#content-content").width($(window).width() - 305 - 40);
		//$("#content-content").width($("#contentBox").width() - 40);
		//$("#content-contents").width($("#contentBox").width() - 40);
		
		var contentBoxWidth = $(window).width() - treeWidth - 13;
		var contentBoxHeight = $(window).height() - 116 - 45 - 7;
		
		$("#contentBox").width(contentBoxWidth).height(contentBoxHeight);
		$("#contentIframe").width(contentBoxWidth).height(contentBoxHeight - 28);
	}
	
	if ($("#contentBoxList")[0])
	{
		$("#contentBoxList").width(735);
		$("#contentBoxList #content-content").width(735 - 40);
	}
		
	var normalTr = $(".dataList tr[class!='Info'][class!='table-title'][class!='Info-tr']");
		
		$(".dataList tr[class!='Info'][class!='table-title'][class!='Info-tr']").each(function(i, object) {
			if (i%2 == 0) {
				$(this).addClass("even");
				$(this).mouseout(function(){
					$(this).toggleClass("over");
					$(this).addClass("even");
				});
			}
			else {
				$(this).addClass("odd");
				$(this).mouseout(function(){
					$(this).toggleClass("over");
					$(this).addClass("odd");
				});
			}
			
			$(this).mouseover(function(){$(this).removeClass("even odd"); $(this).toggleClass("over");});
			
			
			/*
			$(this).click(function() {
				var checkObj = $(this).find("input:first");
				checkObj.attr("checked") ? checkObj.attr("checked","") : checkObj.attr("checked","checked");
			});
			$(this).dblclick(function() {alert("a")});
			*/
		});
		
		//$(".dataList tr[class!='Info'][class!='table-title'][class!='Info-tr']:even").addClass("even");
		//$(".dataList tr[class!='Info'][class!='table-title'][class!='Info-tr']:even").addClass("odd");
		//normalTr.mouseover(function(){$(this).removeClass("even odd"); $(this).toggleClass("over");});
		//normalTr.mouseout(function(){ $(this).toggleClass("over");$(".dataList tr[class!='Info'][class!='table-title'][class!='Info-tr']:even").addClass("even");$(".dataList tr[class!='Info'][class!='table-title'][class!='Info-tr']:odd").addClass("odd");});
		
		//$(".dataList tr:not(:has(th,div)):even").addClass("even");
		//$(".dataList tr:not(:has(th,div)):odd").addClass("odd");
		
		$(".dataList tr[class='Info']:even div").addClass("infoEven");
		////$(".dataList tr:has(div):odd").style.borderBottom = "1px #F00 solid");
		
		//$(".dataList tr:not(:has(th,div))").mouseover(function(){$(this).removeClass("even odd"); $(this).toggleClass("over");});
		//$(".dataList tr:not(:has(th,div))").mouseout(function(){ $(this).toggleClass("over");$(".dataList tr:not(:has(th,div)):even").addClass("even");$(".dataList tr:not(:has(th,div)):odd").addClass("odd");});

		//alert($(window).width());
		//alert($(window).height());
		
		$(".dataList tr[class='Info'] div td").each(function(i, object) {
			i%2 == 0 ? $(object).addClass("strong textRight") : null;
		});
		
		
		$(".hasBorder").each(function(j, tableObj) {
			var isGrey = true;
			$(tableObj).find("td").each(function(i, object) {
				if (isGrey){
						$(object).addClass("greyBg");
				}
				if ((i+1)%3 == 0)
				{
					isGrey = isGrey ? false : true;
				}
			});
		 });
	
	
	
	
	if ($("textarea[class='text-input textarea wysiwyg']")[0])
	{
		$('a[rel*=modal]').facebox(); // Applies modal window to any link with attribute rel="modal"

    	// Initialise jQuery WYSIWYG:
		
		$(".wysiwyg").wysiwyg(); // Applies WYSIWYG editor to any textarea with the class "wysiwyg"
	}
	
	$(".changeColor a.Green").bind("click", function() {
		$("link").eq(1)[0].href="Styles/GreenStyle.css";
	});
	$(".changeColor a.base").bind("click", function() {
		$("link").eq(1)[0].href="Styles/BasicStyle.css";
	});
	
	
	
	$("#contentBox #dataList .delete").each(function(i, object) {
		var wTop = ($(window).height() - 100) / 2;
		var wLeft = ($(window).width() - 300) / 2;									   
		$(object).bind("click", function() {
			
			var trs = $(this).closest("table").find("tr");
			
			var index = trs.index($(this).closest("tr"));
			
			if ($(this).closest("tr").find("td[class='TimeStart']").length > 0)
			{
				$("#MessageBox p:first").text("您确定要删除：" + $(this).closest("tr").find("td[class='TimeStart']").text() + " ~ " + $(this).closest("tr").find("td[class='TimeEnd']").text() + "  这个时段吗？")
			}
			else
			{
				$("#MessageBox p:first").text("您确定要删除：" + $(this).closest("tr").find("td[class='itenName']").text() + "  吗？");
			}
			$.blockUI({
				message: $("#MessageBox"),
				css: { border:'none', width: '300', top : wTop, left : wLeft },
				growlCSS: { width: '300px'}
			});
			
			$("#MsgOK").bind("click", function() {
				//$("#dataList tr").eq(index).fadeOut("normal", function (){$(this).remove();});
				$(object).closest("tr").fadeOut("normal", function (){$(this).remove();});
				$.unblockUI();
			});
		});
	});
	
	$("#contentBoxLarger #dataList .delete").each(function(i, object) {
		var wTop = ($(window).height() - 100) / 2;
		var wLeft = ($(window).width() - 300) / 2;									   
		$(object).bind("click", function() {
			
			var trs = $(this).closest("table").find("tr");
			
			var index = trs.index($(this).closest("tr"));
			
			if ($(this).closest("tr").find("td[class='TimeStart']").length > 0)
			{
				$("#MessageBox p:first").text("您确定要删除：" + $(this).closest("tr").find("td[class='TimeStart']").text() + " ~ " + $(this).closest("tr").find("td[class='TimeEnd']").text() + "  这个时段吗？")
			}
			else
			{
				$("#MessageBox p:first").text("您确定要删除：" + $(this).closest("tr").find("td[class='itenName']").text() + "  吗？");
			}
			$.blockUI({
				message: $("#MessageBox"),
				css: { border:'none', width: '300', top : wTop, left : wLeft },
				growlCSS: { width: '300px'}
			});
			
			$("#MsgOK").bind("click", function() {
				//$("#dataList tr").eq(index).fadeOut("normal", function (){$(this).remove();});
				$(object).closest("tr").fadeOut("normal", function (){$(this).remove();});
				$.unblockUI();
			});
		});
	});
	
	var checkBoxs;
	$(".checkBoxAll").bind("click", function() {
		checkBoxs = $(this).closest("table").find("td input[type='checkbox']");
		checkBoxs.attr("checked", $(this).attr("checked"));
	});
	
	$("table[class='dataList'] td input[type='checkbox']").bind("click", function () {
		var value = $(this).attr("checked");
		var flg = true;
		$(this).closest("table").find("td input[type='checkbox']").each(function(i, obj) {
			if ($(obj).attr("checked") != value)
			{
				$(obj).closest("table").find("th input.checkBoxAll").attr("checked", false);
				flg = false;
				return false;
			}
		});
		if (flg)
			$(".checkBoxAll").attr("checked", value);
	});
	
	$("#contentBox .content-top a.Delete").click(function() {
		var msg = "";
		//alert($("this").closest("div.content-top"))
		$(this).closest("div.content-top").next().find("table td input[type='checkbox']").each(function(i, obj) {
			if ($(obj).attr("checked"))
			{
				var value =$(obj).parents("tr:first").find("td[class='itenName']").text();
				msg == "" ? msg += "您确定要删除以下选项吗：<br \/>" + value : msg += "<br \/>" + value;
			}
		});
		
		if (msg != "")
		{
			$("#MsgOK").bind("click", function() {deleteList()});
			popupMsg(msg);
			msg = "";
		}
	});
	
	$("a[target='contentIframe']").bind("click", function() {
		validateTree(this);
	});
	
	var checkedAllObj = $(".checkBoxAll");
	$("table.newdataList tr[class!='Info'][class!='title'][class!='Info-tr']").each(function(i, object) {
		var trClass = "";
		var thisObj = $(this);
		(i + 1)%2 == 0 ? trClass = "even" : trClass = "odd";
		
		thisObj.addClass(trClass).bind("mouseout", function() {
			thisObj.toggleClass("over");
			if (thisObj.find("input:first[type='checkbox']").attr("checked") == "checked")
				thisObj.addClass("checked");
			else
				thisObj.addClass(trClass);
		}).bind("mouseover", function() {
			thisObj.removeClass("even odd checked"); $(this).toggleClass("over");
		});
		
		thisObj.find("td:first input[type='checkbox']").click(function() {
			var isChecked = false;
			if ($(this).attr("checked") == "checked") {
				thisObj.addClass("checked");
				if ($(this).closest("table").find("tr[class!='title'] input[checked!='checked']").length == 0)
					checkedAllObj.attr("checked", true);
			}
			else {
				thisObj.removeClass("checked");
				checkedAllObj.attr("checked", false);
			}
		});
	});
	
	checkedAllObj.bind("click", function() {
		var trs = $(this).closest("table").find("tr[class!='title']");
		var checkBoxs =trs.find("input[type='checkbox']");
		var isChecked = true;
		if (!$(this).attr("checked")) {
			isChecked = false;
			trs.removeClass("checked");
		}
		else {
			trs.addClass("checked");
		}
		checkBoxs.attr("checked", isChecked);
	});
	
});

$(window).resize(function() {
	
	var treeWidth = 0;
	if (thisPage)
		thisPage == "" ? treeWidth = 0 : treeWidth = 257;
	
	//alert(treeWidth);
	
	setTimeout(function() {
		$("#Content").height($(window).height() - 116 - 45);
		$("#Content-Tree").height($(window).height() - 116 - 45 - 7);
		if ($("#contentBoxLarger")[0])
		{
			//alert("a");
			$("#contentBoxLarger").width($(window).width() - 50);
			$("#content-content").width($("#contentBoxLarger").width() - 40);
		}
		else
		{
			//alert("b");
			var contentBoxWidth = $(window).width() - treeWidth - 13;
			var contentBoxHeight = $(window).height() - 116 - 45 - 7;
			
			$("#contentBox").width(contentBoxWidth).height(contentBoxHeight);
			$("#contentIframe").width(contentBoxWidth).height(contentBoxHeight - 28);
			
			//$("#contentBox").width($(window).width() - 305 - 20);
			//$("#content-content").width($(window).width() - 305 - 40);
			//$("#content-content").width($("#contentBox").width() - 40);
			//$("#contentBox").width($(window).width() - treeWidth - 13).height($(window).height() - 116 - 45 - 7);
		}
		$("#Content").width($(window).width());
	}, 100);
});

function validateTree() {
	//alert(thisPage)
	if (thisPage == "")
		setFullScreen();
	else
		setHasTree();
}

var IsInIframe = window.parent ? true : false;

function setHasTree() {
	var iframeObj, contentBoxObj, treeObj;
	
	//alert(IsInIframe)
	
	if (IsInIframe) {
		iframeObj = $('#contentIframe', parent.document);
		contentBoxObj = $('#contentBox', parent.document);
		treeObj = $('#Content-Tree', parent.document);
	}
	else {
		iframeObj = $('#contentIframe');
		contentBoxObj = $('#contentBox');
		treeObj = $('#Content-Tree');
	}
	
	if (treeObj.css("display") != "none")
		return false;
	//$("#Content-Tree").css("display", "block");
	
	var contentBoxWidth = $(window).width() - 257 - 13;
	
	iframeObj.width(contentBoxWidth);
	contentBoxObj.width(contentBoxWidth);
	
	treeObj.css("display", "block");
}

function setFullScreen() {
	var iframeObj, contentBoxObj, treeObj;
	
	if (IsInIframe) {
		iframeObj = $('#contentIframe', parent.document);
		contentBoxObj = $('#contentBox', parent.document);
		treeObj = $('#Content-Tree', parent.document);
	}
	else {
		iframeObj = $('#contentIframe');
		contentBoxObj = $('#contentBox');
		treeObj = $('#Content-Tree');
	}
	
	if (treeObj.css("display") == "none")
		return false;
	
	treeObj.css("display", "none");
	var contentBoxWidth = $(window).width() - 0 - 13;
	//alert("full：" + contentBoxWidth)
	contentBoxObj.width(contentBoxWidth);
	iframeObj.width(contentBoxWidth);
	//$("#Content").width($(window).width());
}

function popupMsg(msg)
{
	$("#MessageBox p:first").html(msg);
	var wTop = ($(window).height() - 100) / 2;
	var wLeft = ($(window).width() - 300) / 2;
	
	$.blockUI({
		message: $("#MessageBox"),
		css: { border:'none', width: '300', top : wTop, left : wLeft },
		growlCSS: { width: '300px'}
	});
	
}

function deleteList()
{
	$("table[class='dataList'] td input[type='checkbox']").each(function(i, obj) {
		if ($(obj).attr("checked"))
		{
			$(obj).parents("tr:first").remove();
		}
	});
	$("#MessageBox p:first").html("删除成功！");
	$("#MsgOK").hide();
	setTimeout(function() {$.unblockUI();$("#MsgOK").show()}, 2000);
}

function bindDataListMouseArgs(tableObj) {
	var checkedAllObj = $(".checkBoxAll");
	tableObj.find("tr[class!='Info'][class!='title'][class!='Info-tr']").each(function(i, object) {
		var trClass = "";
		var thisObj = $(this);
		(i + 1)%2 == 0 ? trClass = "even" : trClass = "odd";
		
		thisObj.addClass(trClass).bind("mouseout", function() {
			thisObj.toggleClass("over");
			if (thisObj.find("input:first[type='checkbox']").attr("checked") == "checked")
				thisObj.addClass("checked");
			else
				thisObj.addClass(trClass);
		}).bind("mouseover", function() {
			thisObj.removeClass("even odd checked"); $(this).toggleClass("over");
		});
		
		thisObj.find("td:first input[type='checkbox']").click(function() {
			var isChecked = false;
			if ($(this).attr("checked") == "checked") {
				thisObj.addClass("checked");
				if ($(this).closest("table").find("tr[class!='title'] input[checked!='checked']").length == 0)
					checkedAllObj.attr("checked", true);
			}
			else {
				thisObj.removeClass("checked");
				checkedAllObj.attr("checked", false);
			}
		});
	});
	
	checkedAllObj.bind("click", function() {
		var trs = $(this).closest("table").find("tr[class!='title']");
		var checkBoxs =trs.find("input[type='checkbox']");
		var isChecked = true;
		if (!$(this).attr("checked")) {
			isChecked = false;
			trs.removeClass("checked");
		}
		else {
			trs.addClass("checked");
		}
		checkBoxs.attr("checked", isChecked);
	});
}

