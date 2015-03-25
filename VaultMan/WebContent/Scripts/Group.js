$(document).ready(function(){
	$("#contentBox .Add").bind("click", function() {
		var wTop = ($(window).height() - 370) / 2;
		var wLeft = ($(window).width() - 760) / 2;
		currentObj = $(this).parents("tr");
		var GroupCol = $(this).parents("tr").find("td");
		var GroupName = GroupCol.eq(1).text();
		$(".PopTitle").text(GroupName + "信息编辑");
		$("#UserInfo input[id='GroupNo']").val(GroupCol.eq(2).text());
		$("#UserInfo input[id='GroupName']").val(GroupName);
		$("#UserInfo select[id='GroupType']").val(GroupCol.eq(3).text());
		var accreditObj = $("#UserInfo input[id='Accredit']");
		GroupCol.eq(5).text() == "是" ? accreditObj.attr("checked", true) : accreditObj.attr("checked", false);
		var roleRightList = GroupCol.eq(4).text().split(',');
		
		var tableObj = $("#GroupList");
		
		$("#GroupList tr[class!='Info'][class!='table-title'][class!='Info-tr']").remove();
		$.each(roleRightList, function(key,val) {
			var trHtml = "<tr><td class=\"table-center\"><input type=\"checkbox\" \/><\/td><td>" + (key + 1) + "<\/td><td>" + val + "<\/td><td>男<\/td><td>13012345678<\/td><td>员工<\/td><td>软件操作员<\/td><td class=\"optionButtons table-center\"><a href=\"#\" class=\"delete\">删除<\/a><\/td><\/tr>"
			tableObj.append(trHtml);
		});
		
		$("#roleList tr[class!='Info'][class!='table-title'][class!='Info-tr']").each(function(i, object) {
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
		});
		$.blockUI({
			message: $("#UserInfo"),
			css: { border:'none', width: '900', top : wTop, left : wLeft },
			growlCSS: { width: '900px'}
		});
	});
	$("#dataList .edit").each(function(i, object) {
		var wTop = ($(window).height() - 370) / 2;
		var wLeft = ($(window).width() - 760) / 2;
		$(object).bind("click", function() {
			currentObj = $(this).parents("tr");
			var GroupCol = $(this).parents("tr").find("td");
			var GroupName = GroupCol.eq(1).text();
			$(".PopTitle").text(GroupName + "信息编辑");
			$("#UserInfo input[id='GroupNo']").val(GroupCol.eq(2).text());
			$("#UserInfo input[id='GroupName']").val(GroupName);
			$("#UserInfo select[id='GroupType']").val(GroupCol.eq(3).text());
			var accreditObj = $("#UserInfo input[id='Accredit']");
			GroupCol.eq(5).text() == "是" ? accreditObj.attr("checked", true) : accreditObj.attr("checked", false);
			var roleRightList = GroupCol.eq(4).text().split(',');
			
			var tableObj = $("#GroupList");
			
			$("#GroupList tr[class!='Info'][class!='table-title'][class!='Info-tr']").remove();
			$.each(roleRightList, function(key,val) {
				var trHtml = "<tr><td class=\"table-center\"><input type=\"checkbox\" \/><\/td><td>" + (key + 1) + "<\/td><td>" + val + "<\/td><td>男<\/td><td>13012345678<\/td><td>员工<\/td><td>软件操作员<\/td><td class=\"optionButtons table-center\"><a href=\"#\" class=\"delete\">删除<\/a><\/td><\/tr>"
				tableObj.append(trHtml);
			});
			
			$("#roleList tr[class!='Info'][class!='table-title'][class!='Info-tr']").each(function(i, object) {
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
			});

			$.blockUI({
				message: $("#UserInfo"),
				css: { border:'none', width: '760', top : wTop, left : wLeft },
				growlCSS: { width: '760px'}
			});
			
			
		});
		
	});
	
	
	
	
	$("#UserInfo .content-top a.Delete").click(function() {
		var wTop = ($(window).height() - 100) / 2;
		var wLeft = ($(window).width() - 300) / 2;
		var msg = "";
		var delItems = "";
		$(this).closest("div.content-top").next().find("table td input[type='checkbox']").each(function(i, obj) {
			if ($(obj).attr("checked"))
			{
				var value =$(obj).parents("tr:first").find("td").eq(2).text();
				msg == "" ? msg += "您确定要删除以下选项吗：<br \/>" + value : msg += "<br \/>" + value;
				delItems == "" ? delItems += "," + value : delItems += "▲," + value;
			}
		});
		
		if (msg != "")
		{
			$("#MsgOK").bind("click", function() {msgDeleteList(delItems)});
			popupMsg(msg);
			msg = "";
		}
	});
	
	$("#content-content .dataList tr[class!='Info'][class!='table-title'][class!='Info-tr']").each(function(i, object) {
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



var currentObj;
function msgDeleteList(delItems)
{
	//alert(delItems);
	var roleInfo = "," + currentObj.find("td").eq(4).text();
	$.each(delItems.split('▲'), function(key,val) {
		roleInfo = roleInfo.replace(val, "");
	});
	currentObj.find("td").eq(4).text(roleInfo.substring(1));
	
	$("#MessageBox p:first").html("删除成功！");
	$("#MsgOK").hide();
	setTimeout(function() {$.unblockUI();$("#MsgOK").show()}, 2000);
}
