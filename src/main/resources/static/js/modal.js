var Modal = {
	showMessage : function(title, content, config) {
		$('#modalOKButton').hide();
		$('#modalCancelButton').hide();
		$('#modalCloseButton').show();
		return this.show(title, content, false, config);
	},
	showModal : function (title, content, config) {
		$('#modalOKButton').show();
		$('#modalCancelButton').hide();
		$('#modalCloseButton').hide();
		return this.show(title, content, false, config);
	},
	showConfirm : function(title, content, config){
		$('#modalOKButton').show();
		$('#modalCancelButton').show();
		$('#modalCloseButton').hide();
		return this.show(title, content, false, config);
	},
	showHtml : function(title, htmlHref, config){
		$('#modalOKButton').hide();
		$('#modalCancelButton').hide();
		$('#modalCloseButton').show();
		return this.show(title, htmlHref, true, config);
	},
	showHtmlConfirm : function(title, htmlHref, config){
		$('#modalOKButton').show();
		$('#modalCancelButton').show();
		$('#modalCloseButton').hide();
		return this.show(title, htmlHref, true, config);
	},
	show :function(title, content, isHtmlHref ,config){
		this.clearResult();
		var t = title || '訊息';
		var c = config || {
			keyboard : false,
			backdrop : 'static',
			focus : true,
			show : true
		};
		
		
		
		//TITLE
		$('#messageModalTitle').text(t);
		//清除內文
		$('#messageModalHtml').text("");
		$('#messageModalContent').text("");
		
		
		//內文
		if(isHtmlHref){
			
			//add @ 2018/06/26 , because modal win must set max-width(400px) for pc mode, but HtmlHref no need.  
			$("#messageModalContent").parents(".modal-content").removeClass("max-width-400-for-desktop");
		
			if(content.endsWith(".html") || content.endsWith(".htm")){
				$('#messageModalHtml').load(content);
			}else{
				$('#messageModalHtml').html(content);
			}
			
		}else{
			
			//add @ 2018/06/26 , because modal win must set max-width(400px) for pc mode.
			$("#messageModalContent").parents(".modal-content").addClass("max-width-400-for-desktop");
		
			if (!content) {
				content = "無訊息";
			}
			
			console.log("msgLen: " + content.length + ", msg: " + content);

			//訊息最後若有。則去掉
			if (content.endsWith("。")){
				content = content.replace(/.$/,"");
				console.log("去掉。後, msgLen: " + content.length + ", msg: " + content);
			}			
			
			if (content.substring(0, 5)=="HTML:"){
				
				content = content.substring(5);
				$('#messageModalContent').html(content);
			}else{
				$('#messageModalContent').text(content);
			}
			
		}
		
		
		return this.modalObj().modal(c);
	},
	modalObj : function() {
		return $('#messageModal');
	},
	bindShow : function(event) {
		return this.bindEvent(event,'show.bs.modal');
	},
	bindShown : function(event) {
		return this.bindEvent(event,'shown.bs.modal');
	},
	bindHide : function(event) {
		return this.bindEvent(event,'hide.bs.modal');
	},
	bindHidden : function(event) {
		return this.bindEvent(event,'hidden.bs.modal');
	},
	bindEvent : function(event,eventName){
		if(event){
			this.modalObj().off(eventName).on(eventName, event);
		}
		else{
			this.modalObj().off(eventName);
		}
		return this;
	},
	close : function(result){
		$('#modalResult').val(result);
		this.setButtonText();
		this.modalObj().modal('hide');
		return this;
	},
	clearResult : function(){
		$('#modalResult').val("");
		return this;
	},
	getResult : function(){
		return $('#modalResult').val();
	},
	setButtonText : function(ok,cancel){
		var okText = ok || "確認";
		var cancelText = cancel || "取消";
		$('#modalOKButton').html(okText);
		$('#modalCancelButton').html(cancelText);
		return this;
	}
}

// 使用範例
// <script>
// $(document).ready(function () {
// var errMsg = '<%= request.getAttribute("errmsg") %>'
// if (errMsg) Modal.showModal('title', errMsg);
// });
// </script>
