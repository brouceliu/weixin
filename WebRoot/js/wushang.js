
function down(){
	var starttime=$("#start").datebox('getValue');
	
	var endtime=$("#end").datebox('getValue');
	

var url1="http://wx.wushang.com/Txwx/doExcelServlet";

var postData={start :spi(starttime),end:spi(endtime)};
 $.post(url1,postData,function(ret){ 
 /*$.post("http://vote.wushang.com:8089//Volunteer/sur/jsp/Download.jsp",postData,function(ret){  */
if(ret.meg=="nodata"){
alert("没有数据");
}else{
alert("成功");
}
 
},"JSON");
} 

function downloads(){
var url="http://wx.wushang.com/Txwx/message/";
var myDate = new Date();
var month=myDate.getMonth()+1;
var times=myDate.getFullYear()+"-"+month+"-"+myDate.getDate();
  window.location.href = url+times+"message.xls"; 
/* window.location = "http://vote.wushang.com:8089/Volunteer/download/"+times+"volunteer.xls";*/
}

function spi(time){
	var data=time.split(" ");
	var a= data[0].split("/");
	var b=a[2]+"-"+a[0]+"-"+a[1];
	return b+" "+data[1];
}

	
		function subm(){
		var loginurl="http://wx.wushang.com/Txwx/LoginWinxin";
		var uname=$("#login").val();
		var password=$("#password").val();
		var codes=$("#code").val();
		var postData={name:uname,pass:password,code:codes};
		$.post(loginurl,postData,function(ret){ 
			 
			if(ret.meg=="error"){
			alert("您输入的错误");
			}else{
				window.location.href = "http://wx.wushang.com/Txwx/html/Down.jsp";
			}
			 
			},"JSON");
	
		}

		function create(){
			var loginurl="http://wx.wushang.com/Txwx/CreateCard";
			var name1=$("#name").val();
			var logo1=$("#logo").val();
			var colco1r=$("#color").val();
			var notice1=$("#notice").val();
			var description1=$("#description").val();
			var sku1=$("#sku").val();
			var datastart1=$("#datastart").val();
			var dataend1=$("#dataend").val();
			var zhe1=$("#zhe").val();
			var namefu1=$("#namefu").val();
			var postData={url:logo1,color:colco1r,zhe:zhe1,name:name1,notice:notice1,
					description:description1,sku:sku1,datastart:datastart1,dataend:dataend1,cardtype:'DISCOUNT',namefu:namefu1};
			
			$.post(loginurl,postData,function(ret){ 
				 
				alert(ret.mes);
				 
				},"JSON");
		
			}
		
		
		function createcash(){
			var loginurl="http://wx.wushang.com/Txwx/CreateCard";
			var name1=$("#name").val();
			var logo1=$("#logo").val();
			var colco1r=$("#color").find("option:selected").text();
			var notice1=$("#notice").val();
			var description1=$("#description").val();
			var sku1=$("#sku").val();
			var datastart1=$("#datastart").val();
			var dataend1=$("#dataend").val();
			var qiyong1=$("#qiyong").val();
			var jianmian1=$("#jianmian").val();
			var namefu1=$("#namefu").val();
			var cusname=$("#cusname").val();
			var cusurl=$("#cusurl").val();
			var cusname2=$("#cusname2").val();
			var cusurl2=$("#cusurl2").val();
			datastart1=datastart1.replace("T"," ");
			dataend1=dataend1.replace("T"," ");
			alert(datastart1);
			var postData={url:logo1,color:colco1r,qiyong:qiyong1,jianmian:jianmian1,name:name1,notice:notice1,
					description:description1,sku:sku1,datastart:datastart1,dataend:dataend1,cardtype:'CASH',cusname01:cusname
					,cusurl01:cusurl,cusname02:cusname2,cusurl02:cusurl2,namefu:namefu1};
			
			$.post(loginurl,postData,function(ret){ 
				 alert("成功");
				alert(ret.mes);
				 
				},"JSON");
		
			}


		function destroy(){
			var loginurl="http://wx.wushang.com/Txwx/destroyCard";
			var name1=$("#code").val();
			var name2=$("#passwd").val();
			var postData={"code":name1,"passwd":name2};
			
			$.get(loginurl,postData,function(ret){ 
				alert(ret.meg);
				},"JSON");
			}

		function updatecard(){
			var loginurl="http://wx.wushang.com/Txwx/updateCard";
			var type=$("#type").val();
			var manual=$("#manual").val();
			var desc=$("#desc").val();
			var postData={"type":type,"notice":manual,"desc":desc};
			
			$.post(loginurl,postData,function(ret){ 
				alert(ret.msg);
				},"JSON");
			}
		
		
		
		function changestatus(num){
			var loginurl="http://wx.wushang.com/Txwx/change";
			
			var postData={"code":num};
			
			$.post(loginurl,postData,function(ret){ 
			//	alert(ret.message);
				},"JSON");
			}