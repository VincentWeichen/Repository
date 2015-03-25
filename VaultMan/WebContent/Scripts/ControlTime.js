var dropdownButton = new DropdownButton();
$(document).ready(function(){
	
	InitMultiple();
	//alert("a");
	//alert(dropdownButton);
	dropdownButton.Init();
	//alert("b");
	/*
	$("#dataList .edit").each(function(i, object) {
		var wTop = ($(window).height() - 370) / 2;
		var wLeft = ($(window).width() - 760) / 2;
		$(object).bind("click", function() {
			currentObj = $(this).parents("tr");
			var GroupCol = $(this).parents("tr").find("td");
			var GroupName = GroupCol.eq(1).text();
			$.blockUI({
				message: $("#UserInfo"),
				css: { border:'none', width: '760', top : wTop, left : wLeft },
				growlCSS: { width: '760px'}
			});
			
		});
	});
	*/
	
	
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
	$("#restId a.Add ").bind("click", function() {
		var wTop = ($(window).height() - 370) / 2;
		var wLeft = ($(window).width() - 760) / 2;
		$.blockUI({
			message: $("#UserInfo"),
			css: { border:'none', width: '760', top : wTop, left : wLeft },
			growlCSS: { width: '760px'}
		});
	});
	
	/*
	$("#zhouId a.Add ").bind("click", function() {
		var wTop = ($(window).height() - 370) / 2;
		var wLeft = ($(window).width() - 760) / 2;
		$.blockUI({
			message: $("#UserInfo"),
			css: { border:'none', width: '760', top : wTop, left : wLeft },
			growlCSS: { width: '760px'}
		});
	});
	*/
	
	$("#yearWorkId a.addYear ").bind("click", function() {
		var wTop = ($(window).height() - 370) / 2;
		var wLeft = ($(window).width() - 760) / 2;
		$.blockUI({
			message: $("#YearUserInfo"),
			css: { border:'none', width: '760', top : wTop, left : wLeft },
			growlCSS: { width: '760px'}
		});
	});
	
	$("#yearWorkId a.addYear ").bind("click", function() {
		var wTop = ($(window).height() - 370) / 2;
		var wLeft = ($(window).width() - 760) / 2;
		$.blockUI({
			message: $("#YearUserInfo"),
			css: { border:'none', width: '760', top : wTop, left : wLeft },
			growlCSS: { width: '760px'}
		});
	});
	//var checkedList = "1,2,3,4,5";
	
	(function ($) {
		$.fn.extend({
			MultDropList: function (options) {
				var op = $.extend({ wraperClass: "wraper", width: "220px", height: "170px", data: "", selected: "" }, options);
				return this.each(function () {
					var $this = $(this); //指向TextBox
					var $hf = $(this).next(); //指向隐藏控件存
					var conSelector = "#" + $this.attr("id") + ",#" + $hf.attr("id");
					var $wraper = $(conSelector).wrapAll("<div><div></div></div>").parent().parent().addClass(op.wraperClass);

					var $list = $('<div class="list"></div>').appendTo($wraper);
					$list.css({ "width": op.width, "height": op.height });
					//控制弹出页面的显示与隐藏
					$this.click(function (e) {
						$list.toggle();
						e.stopPropagation();
					});

					$(document).click(function () {
						$list.hide();
					});

					$list.filter("*").click(function (e) {
						e.stopPropagation();
					});
					//加载默认数据
					$list.append('<ul style="list-style-type:none;margin:0;"></ul>');
					var $ul = $list.find("ul");

					//加载json数据
					var listArr = op.data.split("|");
					var jsonData;
					
					//alert($this.attr("id"));
					
					var limitString = ",";
					if ($hf.attr("id") == "hfTest")
					{
						limitString += $("#hfTest1").val();
					}
					else
					{
						limitString += $("#hfTest").val();
					}
					//alert(limitString);
					
					for (var i = 0; i < listArr.length; i++) {
						jsonData = eval("(" + listArr[i] + ")");
						//alert(jsonData.k);
						if (limitString.indexOf("," + jsonData.k) != -1)
						continue;
						$ul.append('<li><input type="checkbox" value="' + jsonData.k + '" /><span>' + jsonData.v + '</span></li>');
					}

					//加载勾选项
					var seledArr;
					if (op.selected.length > 0) {
						seledArr = selected.split(",");
					}
					else {
						seledArr = $hf.val().split(",");
					}

					$.each(seledArr, function (index) {
						$("li input[value='" + seledArr[index] + "']", $ul).attr("checked", "checked");

						var vArr = new Array();
						$("input[class!='selectAll']:checked", $ul).each(function (index) {
							vArr[index] = $(this).next().text();
						});
						$this.val(vArr.join(","));
					});
					/*
					//全部选择或全不选
					$("li:first input", $ul).click(function () {
						if ($(this).attr("checked")) {
							$("li input", $ul).attr("checked", "checked");
						}
						else {
							$("li input", $ul).removeAttr("checked");
						}
					});
					*/
					//点击其它复选框时，更新隐藏控件值,文本框的值
					$("input", $ul).click(function () {
						var kArr = new Array();
						var vArr = new Array();
						$("input[class!='selectAll']:checked", $ul).each(function (index) {
							kArr[index] = $(this).val();
							vArr[index] = $(this).next().text();
						});
						$hf.val(kArr.join(","));
						$this.val(vArr.join(","));
						/*
						//alert($hf.attr("id"))
						if ($hf.attr("id") == "hfTest")
						{
							$("#txtTest1").MultDropList({ data: $("#hfddlList1").val() });
						}
						else
						{
							$("#txtTest").MultDropList({ data: $("#hfddlList").val() });
						}
						*/
					});
				});
			}
		});
	})(jQuery);
	$(document).ready(function () {
		$("#txtTest").MultDropList({ data: $("#hfddlList").val() });
		$("#txtTest1").MultDropList({ data: $("#hfddlList1").val() });
	});
	
	
	//alert("2222");
	$("#addGuanzhi").bind("click", function() {addGuanzhiTr();})
});

function addGuanzhiTr(){
	alert("111");
	var tableObj = $("#controlltimeHoliday");
	if (isCreate(tableObj)) {
		var trHtml = "<td width=\"30%\"><input type=\"text\" id=\"begindate\" name=\"begindate\"  onFocus=\"WdatePicker({begindate:'%y-%M-01 00:00',dateFmt:'HH:mm',alwaysUseStartDate:true})\" /></td>";
		trHtml += "<td width=\"30%\"><input type=\"text\" id=\"enddate\" name=\"enddate\"  onFocus=\"WdatePicker({enddate:'%y-%M-01 00:00',dateFmt:'HH:mm',alwaysUseEndDate:true})\" /></td>";
		trHtml += "</tr>"
		tableObj.append(trHtml);
	}
	else{
		$(window.parent.showMessage("执行循环有空白信息，请全部制定后再添加！"));
	}
		
}

function DropdownButton()
{
	this.thisObj;
	DropdownButton.prototype.Init = function() {
		var multiples = $("input.Multiple");
		//alert(multiples.length);
		multiples.bind("click", function() {
			//alert("aa")	
			dropdownButton.EXShow($(this).parent());
		});
		
		/*
		dropdownBtn.each(function(i, object) {
			$(object).find("a:first").bind("click", function() {
				var windowW = $(window).width();
				dropdownButton.EXShow(this);
				var btnBorder = $(this).next();
				//btnBorder.height(0);
				//alert("a");
				//btnBorder.css("display", "block");
				
				var offsetW = $(this).offset().left + btnBorder.width();
				//alert(offsetW + "，" + windowW);
				if (offsetW >= windowW)
				{
					var left = btnBorder.outerWidth(true) - $(this).outerWidth(true);
					//alert(btnBorder.outerWidth() + "，" + $(this).outerWidth(true));
					btnBorder.css("margin-left", (0 - (btnBorder.outerWidth() - $(this).outerWidth(true))) + "px");
				}
				btnBorder.height("auto");
			});
		});
		*/
	}
	
	
	var EX = {
		addEvent:function(k,v){
			var me = this;
			if (me.addEventListener)
				me.addEventListener(k, v, false);
			else if(me.attachEvent)
				me.attachEvent("on" + k, v);
			else
				me["on" + k] = v;
	  	},
		removeEvent:function(k,v){
			var me = this;
			if (me.removeEventListener)
				me.removeEventListener(k, v, false);
			else if (me.detachEvent)
				me.detachEvent("on" + k, v);
			else
				me["on" + k] = null;
		},
		stop:function(evt){
			evt = evt || window.event;
			evt.stopPropagation?evt.stopPropagation():evt.cancelBubble=true;
		}
	};
	
	DropdownButton.prototype.EXShow = function(obj) {
		//alert($(obj).next().next().next().html());
		dropdownButton.thisObj = $(obj);
		
		setTimeout(function(){$(obj).find("ul:first").css("display", "block");},1);
		setTimeout(function(){EX.addEvent.call(document,'click',EXHide);/*EX.addEvent.call(obj,'click',EXHide);*/});
		//setTimeout(function(){EX.addEvent.call(document,'click',dropdownButton.EXHide());/*EX.addEvent.call(obj,'click',EXHide);*/});
	}
	
	function EXHide(ev){
		ev = ev || window.event;
		target = ev.srcElement || ev.target;
		var tagNameString = target.tagName.toUpperCase();
		//alert(tagNameString);
		if (tagNameString != "LABEL" && tagNameString != "INPUT" && tagNameString != "LI" && tagNameString != "UL") {
			EX.removeEvent.call(document,'click',EXHide);
			$(".Multiple").each(function(i, obj) {$(obj).parent().find("ul:first").css("display", "none");/*EX.removeEvent.call(obj,'click',EXHide);*/});
			
			//alert(dropdownButton.thisObj.find("ul:first input[checked='checked']").length)
			//设置当前多选控件的值
			var hfValueStr = "";
			var multipleStr = "";
			dropdownButton.thisObj.find("ul:first input[type='checkbox']").each(function(i, item) {
				var obj = $(item)
				if (obj.attr("checked")) {
					multipleStr == "" ? multipleStr = obj.next().html() : multipleStr += "," + obj.next().html();
					hfValueStr == "" ? hfValueStr = obj.val() : hfValueStr += "," + obj.val();
				}
			});
			var multipleInput = dropdownButton.thisObj.find("input.Multiple");
			multipleInput.val(multipleStr);
			dropdownButton.thisObj.find("input[name='hfTest']").val(hfValueStr);
			//设置多选互斥
			setMultiple();
		}
		//EX.removeEvent.call(document,'click',EXHide);
		//$(".Multiple").each(function(i, obj) {$(obj).parent().find("ul:first").css("display", "none");EX.removeEvent.call(obj,'click',EXHide);});
	}
}

//初始化多选控件
function InitMultiple() {
	var parent = $(".Multiple").parent();
	parent.each(function(i, item) {
		var obj = $(item);
		var uls = obj.find("ul");
		//alert(inputs.length)
		var defaultVal = obj.find("input[name='hfTest']").val().split(',');
		//alert(defaultVal)
		var multipleVals = "";
		$.each(defaultVal, function(j, val) {
			//alert(val);
			var input = uls.find("input[value='" + val + "']");
			//alert(input.length);
			input.attr("checked", true);
			multipleVals == "" ? multipleVals = input.next().html() : multipleVals += "," + input.next().html();
		})
		obj.find("input.Multiple").val(multipleVals);
		//obj.parent().prev().html(multipleVals);
	});
	//设置多选互斥
	setMultiple();
}

//设置多选互斥
function setMultiple() {
	var uls = $(".Multiple").parent();
	//alert(uls.length)
	$(".Multiple").each(function(i, item) {
		//var ul = $(item).parent().find("ul:first");
		var flg = $(item).attr("name");
		//alert(flg);
		//var values = "," + $(item).next().val() + ",";
		//alert(values);
		var otherUls = uls.find("ul[id!='list" + flg + "']");
		//otherUls.find("")
		//alert(otherUls.length)
		var otherValues = "";
		otherUls.parent().find("input[id^='hfTest'][id!='hfTest" + flg + "']").each(function(j, input) {
			//alert($(input).val());
			otherValues += "," + $(input).val();
		});
		otherValues += ",";
		
		//alert(otherValues)
		
		$("ul#list" + flg + " input").each(function(j, input) {
			var inputVal = $(input).val();
			if (otherValues.indexOf("," + inputVal + ",") != -1) {
				 $(input).parent().hide();
			}
			else {
				$(input).parent().show();
			}
		});
		
		$(item).parent().parent().prev().html($(item).val());
	});
}

/* 新增周循环管制组 */
function AddGroup() {
	//alert($("ul#list0").length);
	
	if (AddGroupValidate()) {
		var titleString = "";
		titleString += "<tr class=\"table-title-type\"><th colspan=\"3\">星期一<\/th>";
		titleString += "<th class=\"btnOption\"><div class=\"newdataTitleOption\">执行日：<input type=\"text\" class=\"Multiple\" name=\"0\" style=\"width: 350px;\" value=\"\" \/>";
		titleString += "<input id=\"hfTest0\" type=\"hidden\" name=\"hfTest\" value=\"\" /><a href=\"#\" class=\"delete\" onclick=\"DeleteGroup(0);\">删除改该组<\/a><a href=\"#\" class=\"add\" onclick=\"AddType(0);\">新增管制时段<\/a>";
		titleString +="<ul id=\"list0\">";
		titleString += "<li><input type=\"checkbox\" id=\"CKB01\" value=\"1\" /><label for=\"CKB01\">星期一<\/label><\/li>";
		titleString += "<li><input type=\"checkbox\" id=\"CKB02\" value=\"2\" /><label for=\"CKB02\">星期二<\/label><\/li>";
		titleString += "<li><input type=\"checkbox\" id=\"CKB03\" value=\"3\" /><label for=\"CKB03\">星期三<\/label><\/li>";
		titleString += "<li><input type=\"checkbox\" id=\"CKB04\" value=\"4\" /><label for=\"CKB04\">星期四<\/label><\/li>";
		titleString += "<li><input type=\"checkbox\" id=\"CKB05\" value=\"5\" /><label for=\"CKB05\">星期五<\/label><\/li>";
		titleString += "<li><input type=\"checkbox\" id=\"CKB06\" value=\"6\" /><label for=\"CKB06\">星期六<\/label><\/li>";
		titleString += "<li><input type=\"checkbox\" id=\"CKB07\" value=\"7\" /><label for=\"CKB07\">星期日<\/label><\/li>";
		titleString += "<\/ul><\/div><\/th><\/tr>";
		
		$(titleString).appendTo($("#dataList"));
		
		setMultiple();
		dropdownButton.Init();
	}
}

function AddGroupValidate() {
	if ($("ul#list0").length > 0) {
		$(window.parent.showMessage("请先为新增的周循环分组设置管制时段！"));
		return false;
	}
	
	var values = "";
	$(".Multiple").each(function(i, item) {
		var obj = $(item);
		values == "" ? values = obj.val() : values += "," + obj.val();
	});
	
	if (values.split(",").length >= 7) {
		$(window.parent.showMessage("周循环管制组已满！"));
		return false;
	}
	
	return true;
}
/* 新增周循环管制组 End */

/* 新增年循环管制组 */
function AddGroupYear() {
	if (AddGroupYearValidate()) {
		var titleString = "";
		titleString += "<div class=\"newdataTitle\"><h1>新增年循环管制组<\/h1><div class=\"newdataTitleOption\" style=\"margin-top:10px\"><a href=\"#\" class=\"delete\" onclick=\"DeleteGroupYear(0);\">删除管制组<\/a><a href=\"#\" class=\"add\" onclick=\"AddGroupYear();\">新增管制组<\/a><\/div><\/div>";
		
		tableString = "";
		tableString += "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"dataList0\" class=\"newdataList\" style=\"margin-bottom:15px\" width=\"100%\">";
		tableString += "<tr class=\"table-title\"><th>序号<\/th><th>开始时间<\/th><th>结束时间<\/th><th class=\"table-center\">操作<\/th><\/tr>";
		tableString += "<tr class=\"table-title-type\"><th colspan=\"3\">执行日<\/th><th class=\"btnOption\"><a href=\"#\" class=\"add\" onclick=\"AddYearZhixingWindow(1, 0);\">新增执行日<\/a><\/th><\/tr>";
		tableString += "<tr class=\"table-title-type\"><th colspan=\"3\">管制时段<\/th><th class=\"btnOption\"><a href=\"#\" class=\"add\" onclick=\"AddYearGuanzhiWindow(1, 0);\">新增时段<\/a><\/th><\/tr>";
		tableString += "<\/table>";
		
		$(titleString + tableString).appendTo($(".contentItem:first"));
	}
}

function AddGroupYearValidate() {
	if ($("#dataList0").length > 0) {
		$(window.parent.showMessage("请先为新增的年循环分组设置管制时段！"));
		return false;
	}
	
	return true;
}
/* 新增年循环管制组 End */

/* 新增假期和调班循环管制组 */
function AddGroupHoliday() {
	//alert("a")
	if (AddGroupHolidayValidate()) {
		var titleString = "";
		titleString += "<tr class=\"table-title-type\"><th colspan=\"3\"><\/th><th class=\"btnOption\">";
		
		titleString += "<div class=\"newdataTitleOption\"><div style=\"float:right;\">";
		titleString += "开始时间：<input type=\"text\" style=\"width:100px\" id=\"begindateHoliday0\" name=\"begindateHoliday0\" value=\"\" onFocus=\"WdatePicker({begindate:'%y-%M-01 00:00',dateFmt:'yyyy年MM月dd',alwaysUseStartDate:true})\" \/>";
		titleString += "开始时间：<input type=\"text\" style=\"width:100px\" id=\"enddateHoliday0\" name=\"enddateHoliday0\" value=\"\" onFocus=\"WdatePicker({begindate:'%y-%M-01 00:00',dateFmt:'yyyy年MM月dd',alwaysUseStartDate:true})\" \/>";
		titleString += "<a href=\"#\" class=\"delete\" onclick=\"DeleteGroup(0);\">删除该组<\/a><a href=\"#\" class=\"add\" onclick=\"AddType(0);\">新增管制时段<\/a><\/div>";
		titleString += "<div style=\"float:right;margin-top:5px;padding-right:8px\">管制原因：<input id=\"description0\" name=\"description0\" style=\"width:450px\" value=\"\" \/><\/div>";
		
		titleString += "<\/div><\/th><\/tr>";
		
		$(titleString).appendTo($("#dataList"));
	}
}

function AddGroupHolidayValidate() {
	if ($("#begindateHoliday0").length > 0) {
		$(window.parent.showMessage("请先为新增的假期调班分组设置管制时段！"));
		return false;
	}
	return true;
}
/* 新增假期和调班循环管制组 End */

