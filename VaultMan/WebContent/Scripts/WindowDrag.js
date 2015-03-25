var isIE = (document.all) ? true : false;

var BindAsEventListener = function(object, fun) {
    return function(event) {
        return fun.call(object, (event || window.event));
    }
}

var Bind = function(object, fun) {
    return function() {
        return fun.apply(object, arguments);
    }
}

var CurrentStyle = function(element) {
    return element.currentStyle || document.defaultView.getComputedStyle(element, null);
}

function addEventHandler(oTarget, sEventType, fnHandler) {
	//oTarget = oTarget[0];
    if (oTarget.addEventListener) {
        oTarget.addEventListener(sEventType, fnHandler, false);
    } else if (oTarget.attachEvent) {
        oTarget.attachEvent("on" + sEventType, fnHandler);
    } else {
        oTarget["on" + sEventType] = fnHandler;
    }
};

function removeEventHandler(oTarget, sEventType, fnHandler) {
	//oTarget = oTarget[0];
    if (oTarget.removeEventListener) {
        oTarget.removeEventListener(sEventType, fnHandler, false);
    } else if (oTarget.detachEvent) {
        oTarget.detachEvent("on" + sEventType, fnHandler);
    } else {
        oTarget["on" + sEventType] = null;
    }
};

(function ($) {
	$.fn.extend({
		Drag: function (options) {
			var op = $.extend({ Handle: "", Limit: false, Resize: false, mxContainer: "", mxLeft: 0, mxTop: 0, mxRight: 9999, mxBottom: 9999, onStart: function() { }, onMove: function() { }, onStop: function() { }, Lock: false }, options);
			/*	属性说明
				*	Handle：设置触发对象（不设置则使用拖放对象）
				*	Limit：是否设置范围限制(为true时下面参数有用,可以是负数)
				*	Resize：是否可调整大小
				*	mxContainer：指定限制在容器内
				*	mxLeft：左边界
				*	mxTop：上边界
				*	mxRight：右边界
				*	mxBottom：下边界
				*	onStart：开始移动时执行
				*	onMove：移动时执行
				*	onStop：结束移动时执行
				*	Lock：是否锁定
			*/
			this.$this = $(this);	//指向WindowItem
			//this.SetOptions(options);
			this._Handle = op.Handle || $this;	//设置触发对象（不设置则使用拖放对象）
			this.Limit = !!op.Limit;
			this.mxLeft = parseInt(op.mxLeft);
        	this.mxRight = parseInt(op.mxRight);
        	this.mxTop = parseInt(op.mxTop);
        	this.mxBottom = parseInt(op.mxBottom);
			
			this.dragging = false;
			
			this.onStart = op.onStart;
			this.onMove = op.onMove;
			this.onStop = op.onStop;
			this.Lock = op.Lock;
			
			this._fM = BindAsEventListener(this, this.Move);
        	this._fS = Bind(this, this.Stop);
			
			//alert(this._Handle.html());
			addEventHandler(this._Handle[0], "mousedown", BindAsEventListener(this, this.Start));
		},
		Start: function(oEvent)
		{
			if (this.Lock) { return; }
			if (this.Limit)
			{
				this.mxLeft = $("#SideBar").outerWidth() + 2;	//windowItem left:2
				this.mxTop = $("#Header").outerHeight() + 10 + 1;	//margin-top:10，windowItem top:1
				this.mxRight = Math.max(this.mxRight, this.mxLeft + this.$this.outerWidth());
            	this.mxBottom = Math.max(this.mxBottom, this.mxTop + this.$this.outerHeight());
				//alert("有范围");
			}
			else
			{
				//alert("无范围");
			}
			//alert(this.mxLeft + "，" + this.mxTop + "，" + this.mxRight + "，" + this.mxBottom);
			//alert(this._Handle.html());
			//alert(this.$this.attr("_startTop"));
			//alert("MouseClickX：" + oEvent.clientX + "，MouseClickY：" + oEvent.clientY + "\r\n" + "DragX：" + this.$this.offset().left + "，DragY：" + this.$this.offset().top)
			
			this._Handle.css("cursor", "move");
			
			//记录鼠标相对拖放对象的位置
			this._x = oEvent.clientX - this.$this[0].offsetLeft;	//this.$this.offset().left;
			this._y = oEvent.clientY - this.$this[0].offsetTop;	//this.$this.offset().top;
			/*
			alert(oEvent.clientX + "，" + oEvent.clientY)
			alert(this.$this.offset().left + "，" + this.$this.offset().top)
			alert(this._x + "，" + this._y);
			*/
			//this.$this.css({"left":(0 - this._x) + "px", "top":(0 - this._y) + "px"});
			//alert(document === $(document)[0])
			
			//mousemove时移动 mouseup时停止
			addEventHandler(document, "mousemove", this._fM);
			addEventHandler(document, "mouseup", this._fS);
			
			if (isIE) {
				//焦点丢失
				addEventHandler(this._Handle, "losecapture", this._fS);
				//设置鼠标捕获
				this._Handle[0].setCapture();
			} else {
				//焦点丢失
				addEventHandler(window, "blur", this._fS);
				//阻止默认动作
				oEvent.preventDefault();
			};
			this.dragging = true;
			/*
			iX = e.clientX - this.offsetLeft;
			iY = e.clientY - this.offsetTop
			*/
			this.$this.setCapture && this.$this.setCapture();
			//附加程序
	        this.onStart();
			return false;
		},
		//拖动
    	Move: function(oEvent) {
			if (this.Lock) { this.Stop(); return; };
			//清除选择
        	window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
			//alert(this.dragging);
			if (this.dragging) {
				/*
				var e = e || window.event;
				var oX = e.clientX - iX;
				var oY = e.clientY - iY;
				*/
				//设置移动参数
       			var iLeft = oEvent.clientX - this._x, iTop = oEvent.clientY - this._y;
				this.$this.css({"left":iLeft + "px", "top":iTop + "px"});
				//附加程序
        		this.onMove();
				return false;
			}
			
		},
		//停止拖动
		Stop: function() {
			this.dragging = false;
			this._Handle.css("cursor", "default");
			//移除事件
			removeEventHandler(document, "mousemove", this._fM);
			removeEventHandler(document, "mouseup", this._fS);
			if (isIE) {
				removeEventHandler(this._Handle[0], "losecapture", this._fS);
				this._Handle[0].releaseCapture();
			} else {
				removeEventHandler(window, "blur", this._fS);
			};
			//附加程序
        	this.onStop();
		},
		//调整大小
		Resize: function() {
			
		}
	});
})(jQuery);