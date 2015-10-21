$(document).ready(function(){
	
	var u = navigator.userAgent, app = navigator.appVersion; 
	
	
	var wechatInfo = navigator.userAgent.match(/MicroMessenger\/([\d\.]+)/i) ;
	if( !wechatInfo ) {
	  //  alert("本活动仅支持微信") ;
	} else if ( wechatInfo[1] < "6.0" ) {
	//    alert("本活动仅支持微信5.0以上版本") ;
	}
	
	
	
	
});

function clearForm(){
	
	$('#ff').form('clear');
}

function submitForm(){
	
	//表单提交
	$('#ff').form('submit',{
	    url:'http://wx.wushang.com/Txwx/servlet/loginsite',
	    onSubmit:function(){
	        return $(this).form('validate');
	    },//检查表单
	    success:function(ret){
	    	var obj = JSON.parse(ret);
	      
	        if(obj.meg=="ok"){
	        	 alert("登录成功");
	        window.location="http://wx.wushang.com/Txwx/MyJsp.jsp";
	        }else{
	        	 alert("账号密码错误");
	        }
	        
	    }
	});	
	
}
