<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
  String name;
  name=(String)session.getAttribute("login");
if(name==null){
	response.sendRedirect("index.jsp");
} else if(name.equals("success")==false){
	response.sendRedirect("index.jsp");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>创建折扣卷</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="创建折扣卷">
	<meta http-equiv="description" content="创建折扣卷">
	
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/wushang.js"></script>

<style type="text/css">
	body{
	
 background-color:#97FFFF;
 }
	.dataintable .out{
	align:center;
	
	}
	
	
	
	</style>

  </head>
  
  <body>
  <div class="out">
   <p>填写logo的url:<input type="text" name="logo" id="logo"/></p>
   <p>填写卡卷颜色:<input type="text"  name="color" id="color"/></p>
   <p>填写卡卷名字:<input type="text"  name="name" id="name"/></p>
   <p>填写卡卷使用提醒:<input type="text"  name="notice" id="notice"/></p>
   <p>填写卡卷使用说明:<input type="text"  name="description" id="description"/></p>
   <p>填写卡卷库存数量:<input type="text"  name="sku" id="sku"/></p>
   <p>填写卡卷开始使用时间:<input type="text"  name="datastart" id="datastart"/>(2015-05-25 12:00:00)</p>
   <p>填写卡卷截至时间:<input type="text"  name="dataend" id="dataend"/></p>
   <p>填写打几折:<input type="text"  name="zhe" id="zhe"/>（比如30就是三折）</p>
   <input type="button"  value="创建卡卷" onclick="create()"/>
   </div>
   
<table class="dataintable">
  <tr>
<th style="width:50%; text-align:left;">Color</th>
<th style="width:50%; text-align:left;">Color HEX</th>

  </tr>
  <tr>
    <td style="background-color:#63b359">&nbsp;</td>
    <td>填写值:Color010</td>
  </tr>
  <tr>
    <td style="background-color:#2c9f67">&nbsp;</td>
    <td>填写值:Color020</td>
  </tr>
   <tr>
    <td style="background-color:#509fc9">&nbsp;</td>
    <td>填写值:Color030</td>
  </tr>
  <tr>
    <td style="background-color:#5885cf">&nbsp;</td>
    <td>填写值:Color040</td>
  </tr>
  <tr>
    <td style="background-color:#9062c0">&nbsp;</td>
    <td>填写值:Color050</td>
  </tr>
  <tr>
    <td style="background-color:#d09a45">&nbsp;</td>
    <td>填写值:Color060</td>
  </tr>
  <tr>
    <td style="background-color:#e4b138">&nbsp;</td>
    <td>填写值:Color070</td>
  </tr>
  <tr>
    <td style="background-color:#ee903c">&nbsp;</td>
    <td>填写值:Color080</td>
  </tr>
  
  <tr>
    <td style="background-color:#f08500">&nbsp;</td>
    <td>填写值:Color081</td>
  </tr>
  <tr>
    <td style="background-color:#dd6549">&nbsp;</td>
    <td>填写值:Color090</td>
  </tr>
  <tr>
    <td style="background-color:#cc463d">&nbsp;</td>
    <td>填写值:Color100</td>
  </tr>
  <tr>
    <td style="background-color:#cf3e36">&nbsp;</td>
    <td>填写值:Color101</td>
  </tr>
</table>



  </body>
</html>
