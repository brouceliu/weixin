/**
 * Created by DELL on 2015/5/26.
 */




$(document).ready(function(){
   
    var jsti = $("#a2").val();
    var timestep = $("#a4").val();
    var SUIJI = $("#a3").val();
    
    var cardtype = $("#a6").val();
    var cardtime = $("#a7").val();
    var cardsuiji = $("#a5").val();
    var cardid = $("#a8").val();
    var cardticket = $("#a9").val();
    var cnum = $("#a0").val();
   

    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: 'wx6f1863a4b2802eea', // 必填，公众号的唯一标识
        timestamp: timestep, // 必填，生成签名的时间戳
        nonceStr: SUIJI, // 必填，生成签名的随机串
        signature: jsti,// 必填，签名，见附录1  ticket生产的签名
        jsApiList: ["addCard","chooseCard","openCard"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    wx.ready(function(){

        $("#getcard").tap(function(){


            wx.chooseCard({
                shopId: '',
                cardType: cardtype, // 卡券类型
                cardId: cardid, // 卡券Id
                timestamp: cardtime, // 卡券签名时间戳
                nonceStr: cardsuiji, // 卡券签名随机串
                signType: 'SHA1', // 签名方式，默认'SHA1'
                cardSign: cardticket, // 卡券签名
                success: function (res) {
                    var cardList= res.cardList; // 用户选中的卡券列表信息
                    
                }
            });
        });

        /* var ext={'code' : cnum,
             'openid' : '',
             'timestamp' : cardtime,
             'nonce_str' : cardsuiji,
             'signature' : cardticket};*/

        var ext = new Object();
        ext.code =cnum;
        ext.nonce_str =cardsuiji;
        ext.signature=cardticket;
        ext.openid='';
        ext.timestamp=cardtime;

        var ext1 = JSON.stringify(ext);

if(!cnum){
	
	alert('已经发完了');
}else{
	
	 $("#addcard").tap(function(){

         wx.addCard({
             cardList: [{
                 cardId: cardid,
                 cardExt:  ext1
             }],
             success: function (res) {
                 var cardList = res.cardList;
                 changestatus(cnum);
             }

         });

     });
}
       
        
        
    });
    
    
    wx.error(function(res){
    	
    	//var a=JSON.stringify(res); 
    	// alert(a);
    	// alert(res.errMsg);
     
    });

    $("#testinfo").tap(function(){
    wx.checkJsApi({
        jsApiList: ["addCard","chooseCard","openCard"], // 需要检测的JS接口列表，所有JS接口列表见附录2,
        success: function(res) {
            alert("check success");
            alert(res.checkResult.addCard);
            alert(res.checkResult.chooseCard);
            // 以键值对的形式返回，可用的api值true，不可用为false
            // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
        }
    });
    });


    });


