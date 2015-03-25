$(document).ready(function(){
	$("#dataList .edit").each(function(i, object) {
		var wTop = ($(window).height() - 370) / 2;
		var wLeft = ($(window).width() - 760) / 2;
		$(object).bind("click", function() {
			currentObj = $(this).parents("tr");
			var GroupCol = $(this).parents("tr").find("td");
			var GroupName = GroupCol.eq(2).text();
			$("#UserInfo input[id='GroupName']").val(GroupName);
			$("#UserInfo input[id='datetimepicker']").val(GroupCol.eq(4).text());
			$("#UserInfo select[id='Abidance']").val(GroupCol.eq(5).text());
			//var accreditObj = $("#UserInfo input[id='Accredit']");
			var roleRightList = GroupCol.eq(3).text().split(',');
			
			var tableObj = $("#userList");
			
			$("#userList tr[class!='Info'][class!='table-title'][class!='Info-tr']").remove();
			$.each(roleRightList, function(key,val) {
				var trHtml = "<tr><td>" + (key + 1) + "<\/td><td>" + val + "<\/td><\/tr>"
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
	
	/*
	var navLink = $("#content-nav a");
	if (navLink.length > 1)
	{
		var navContentItem = $(".contentItem");
		navLink.each(function(i, object) {
			$(object).bind("click", function() {
				var index = navLink.index($(this));
				navLink.parent("li").attr("class", "");
				$(this).parent().attr("class", "current");
				navContentItem.css("display", "none");
				navContentItem.eq(index).css("display", "block");
			});
		});
	}
	
	*/
	
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