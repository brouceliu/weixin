<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%> 

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
    
    
    <title>主页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="Thiss is my page">
	<link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/test.js"></script>
	<script type="text/javascript" src="jslib/jquery.easyui.min.js"></script>
	<style type="text/css">
	body{
	

 }
	.out{
float: left;
width:30%;
height: 100%;
margin-left: 20%;
background-color:#97FFFF;	
	}
	.a1 .a2 .a3 .a4{
		width:60px;
	}
	</style>
  </head>
  
  <body >
  

<div class="out">
<div>
  <h5 class="a1"><a href="http://wx.wushang.com/Txwx/uplogo.jsp" >创建折扣卷</a></h5>
  <h5 class="a2"><a href="http://wx.wushang.com/Txwx/UpdateCard.html" >修改折扣卷</a></h5>
  <h5 class="a3"><a href="http://wx.wushang.com/Txwx/uploadCardCode.jsp" >导入卡卷号码</a></h5>
  <h5 class="a4"><a href="http://wx.wushang.com/Txwx/uploadExcel.jsp" >导入客服消息</a></h5>
  </div>
</div>		
	


  
	
  </body>
</html>
