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
	<meta http-equiv="keywords" content="创建代金卷">
	<meta http-equiv="description" content="创建代金卷">
	<style type="text/css">
.c1{
background-color:#63b359;
}
.c2{
background-color:#2c9f67;
}
.c3{
background-color:#509fc9;
}
.c4{
background-color:#5885cf;
}
.c5{
background-color:#9062c0;
}
.c6{
background-color:#d09a45;
}
.c7{
background-color:#e4b138;
}
.c8{
background-color:#ee903c;
}
.c9{
background-color:#dd6549;
}
.c0{
background-color:#cc463d;
}
.c11{
background-color:#cf3e36;
}

.c12{
background-color:#f08500;
}

.out{
margin-top: 30px;
float: left;
width:30%;
height: 100%;
margin-left: 33%;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/wushang.js"></script>

</head>
  
  <body>
  
   
   <div class="out">
 <table>
 <tr>
 <td><p>填写logo的url:</p></td>
 <td><input type="text" name="logo" id="logo"/></td>
 </tr>
 <tr>
 <td><p>填写卡卷颜色:</p></td>
 <td><select id="color">
     <option class="c1" value="aa">Color010</option>
     <option class="c2" value="bb">Color020</option>
     <option class="c3" value="cc">Color030</option>
     <option class="c4" value="dd">Color040</option>
     <option class="c5" value="ee">Color050</option>
     <option class="c6" value="ff">Color060</option>
     <option class="c7" value="gg">Color070</option>
     <option class="c8" value="hh">Color080</option>
     <option class="c12" value="ll">Color081</option>
     <option class="c9" value="ii">Color090</option>
     <option class="c0" value="jj">Color100</option>
     <option class="c11" value="kk">Color101</option>
</select></td>
 </tr>
 
 <tr>
 <td> <p>填写卡卷名字:</p></td>
 <td><input type="text"  name="name" id="name"/></td>
 </tr>
 <tr>
 
 <tr>
 <td><p>填写卡卷副标题:</p></td>
 <td><input type="text"  name="namefu" id="namefu"/></td>
 </tr>
 <tr>
 
 <tr>
 <td><p>填写卡卷使用提醒:</p></td>
 <td><input type="text"  name="notice" id="notice"/></td>
 </tr>
 <tr>
 
 <tr>
 <td><p>填写卡卷使用说明:</p></td>
 <td><input type="text"  name="description" id="description"/></td>
 </tr>
 <tr>
 
 <tr>
 <td><p>填写卡卷库存数量:</p></td>
 <td><input type="text"  name="sku" id="sku"/></td>
 </tr>
 <tr>
 
 <tr>
 <td><p>填写卡卷开始使用时间:</p></td>
 <td><input type="datetime-local"  name="datastart" id="datastart" value="2015-09-01T10:55:33"/></td>
 </tr>
 <tr>
 
 <tr>
 <td><p>填写卡卷截至时间:</p></td>
 <td><input type="datetime-local"  name="dataend" id="dataend" value="2015-12-01T10:55:55"/></td>
 </tr>
 <tr>
 
 <tr>
 <td> <p>填写卡卷第一行自定义链接说明:</p></td>
 <td><input type="text"  name="cusname" id="cusname"/></td>
 </tr>
 <tr>
 
 <tr>
 <td><p>填写卡卷自定义链接地址(第一行):</td>
 <td><input type="text"  name="cusurl" id="cusurl"/></td>
 </tr>
 <tr>
 
 <tr>
 <td>    <p>填写卡卷自定义链接说明(第二行):</p></td>
 <td><input type="text"  name="cusname2" id="cusname2"/>
 </td>
 </tr>
 <tr>
 
 <tr>
 <td><p>填写卡卷自定义链接地址(第二行):</p></td>
 <td><input type="text"  name="cusurl2" id="cusurl2"/></td>
 </tr>
 <tr>
 
 <tr>
 <td><p>填写卡卷启用金额(必填 单位是分):</p></td>
 <td><input type="text"  name="qiyong" id="qiyong"/></td>
 </tr>
 <tr>
 
 <tr>
 <td><p>填写卡卷减免金额(必填 单位是分):</p></td>
 <td><input type="text"  name="jianmian" id="jianmian"/></td>
 </tr>
 <tr>
 
 <tr>
 <td><input type="button"  value="创建卡卷" onclick="createcash()"/></td>
 
 </tr>
 <tr>
 </table>
  <div>
   
   
   
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
