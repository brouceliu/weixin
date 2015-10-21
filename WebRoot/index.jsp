<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>后台登陆</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="Thiss is my page">
	<link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	
	<script type="text/javascript" src="jslib/jquery.easyui.min.js"></script>
	
	<script type="text/javascript" src="js/test.js"></script>
  </head>
  
  <body>
  
  <h2><% 
String datetime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); 
out.print("现在时间是 "+datetime);
%></h2>
	
	<div align="center">
	<div class="easyui-panel" title="微信后台登录" style="width:400px" >
		<div style="padding:10px 60px 20px 60px">
	    <form id="ff" method="post" action="">
	    	<table cellpadding="5" >
	    		<tr>
	    			<td>用户名:</td>
	    			<td><input class="easyui-textbox" type="text" name="code" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>密码:</td>
	    			<td><input class="easyui-textbox" type="password" name="passwd" data-options="required:true"></input></td>
	    		</tr>
	    		
	    		
	    		
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    
	    	<a  class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a  class="easyui-linkbutton" onclick="clearForm()">清除</a>
	    </div>
	    </div>
	</div>
    </div>
  </body>
</html>
