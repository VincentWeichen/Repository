$(document).ready(function(){
					
	//alert("b");
	SetSuperviseLayout();
	
	var layout = $("#SuperviseList");
	var itemSrc = "<div class=\"SuperviseItem\"><a href=\"#\"><img src=\"..\/Images\/Basic\/Icon_storehouse_Green.png\" \/><span><\/span><\/a><\/div>";
	
	var index = layout.find(".SuperviseItem").length + 1;
	
	$(".addNormal").bind("click", function() {
		layout.append("<div class=\"SuperviseItem\"><a href=\"#\"><img class=\"normal\" src=\"..\/Images\/Basic\/Icon_storehouse_Green.png\" \/><span>金库门号：" + index + "<\/span><\/a><\/div>");
		index++;
		SetSuperviseLayout();
	});
	$(".delNormal").bind("click", function() {
		layout.find(".normal:last").parents("div:first").remove();
		SetSuperviseLayout();
	});
	
	$(".addOpening").bind("click", function() {
		layout.append("<div class=\"SuperviseItem\"><a href=\"#\"><img class=\"opening\" src=\"..\/Images\/Basic\/Icon_storehouse_Yellow.png\" \/><span>金库门号：" + index + "<\/span><\/a><\/div>");
		index++;
		SetSuperviseLayout();
	});
	$(".delOpening").bind("click", function() {
		layout.find(".opening:last").parents("div:first").remove();
		SetSuperviseLayout();
	});
	
	$(".addError").bind("click", function() {
		layout.append("<div class=\"SuperviseItem\"><a href=\"#\"><img class=\"error\" src=\"..\/Images\/Basic\/Icon_storehouse_Red.png\" \/><span>金库门号：" + index + "<\/span><\/a><\/div>");
		index++;
		SetSuperviseLayout();
	});
	$(".delError").bind("click", function() {
		layout.find(".error:last").parents("div:first").remove();
		SetSuperviseLayout();
	});
	
	$(".addLose").bind("click", function() {
		layout.append("<div class=\"SuperviseItem\"><a href=\"#\"><img class=\"lose\" src=\"..\/Images\/Basic\/Icon_storehouse_Grey.png\" \/><span>金库门号：" + index + "<\/span><\/a><\/div>");
		index++;
		SetSuperviseLayout();
	});
	$(".delLose").bind("click", function() {
		layout.find(".lose:last").parents("div:first").remove();
		SetSuperviseLayout();
	});
	
	
});

function SetSuperviseLayout ()
{
	var abledW = $(window).width() - 10;	//页面实际可用宽度
	var itemMinW = 130;	//最小显示宽度
	var itemMaxW = 300;	//最大显示宽度
	
	var maxItemCount = parseInt(abledW / itemMinW);	//获取当前宽度下，一行可以显示的最大图标数
	
	var actualCount = $("#SuperviseList .SuperviseItem").length;	//实际图标数量
	
	var itemActualW = 0;
	
	if (actualCount > maxItemCount)
	{
		//直接设置最小宽显示
		itemActualW = itemMinW;
	}
	else if (actualCount * itemMaxW < abledW)
	{
		itemActualW = itemMaxW;
	}
	else
	{
		//从中间（200）起计算，如果中间值宽度总和小于实际可用宽度，则从中间值向上匹配；反之则从中加你值向下匹配
		actualCount * 200 < abledW ? flg = 1 : flg = 0;
		itemActualW = MatchActualWidth(200, actualCount, abledW, flg);
	}
	
	$("#SuperviseList .SuperviseItem").width(itemActualW - 30).height(itemActualW - 10);
	$("#SuperviseList .SuperviseItem img").width(itemActualW - 30);
	
	//var rowCount = actualCount / maxItemCount;
	
	//rowCount > parseInt(rowCount) ? rowCount = parseInt(rowCount) + 1 : rowCount = parseInt(rowCount);
//	$("#SuperviseList .SuperviseItem a").each(function(i, object) {
//		$(object).bind("click", function() {
//			var confirmObj = new Object();
//			confirmObj.func = "$('#contentIframe')[0].contentWindow.onsubmits()";
//			confirmObj.isDisplay = true;
//			
//			var closeObj = new Object();
//			closeObj.isDisplay = false;
//			
//			var dialogObj = new Object();
//			dialogObj.funcName = "Users";
//			dialogObj.titleText = "实时监控";
//			dialogObj.message = $("#UserInfo").html();
//			dialogObj.confirm = confirmObj;
//			dialogObj.close = closeObj;
//			
//			$(window.parent.showUDefinedDialog(dialogObj));
//		});
//		
//	});
}

function MatchActualWidth(startValue, actualCount, abledW, flg)
{
	if (flg)
	{
		if (actualCount * (startValue + 20) < abledW)
		{
			return MatchActualWidth(startValue + 20, actualCount, abledW, flg);
		}
		else
		{
			return startValue;
		}
	}
	else
	{
		if (actualCount * (startValue - 20) < abledW)
		{
			return MatchActualWidth(startValue - 20, actualCount, abledW, flg);
		}
		else
		{
			return startValue;
		}
	}
}


