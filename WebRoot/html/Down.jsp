<%@page contentType="text/html;charset=utf-8"%>

<%@page import="java.text.SimpleDateFormat" import="java.util.*"%>
<%@page session="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="lzr.ico"  type="image/x-icon"  />
<link rel="bookmark" href="lzr.ico"  type="image/x-icon"/>

<title>消息下载</title>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/wushang.js"></script>


<link rel="stylesheet" type="text/css" href="../css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../css/themes/icon.css">
<% 
String result=(String)session.getAttribute("ischeck");
if(result==null || result.equals("")){
	response.sendRedirect("login.html"); 
}else{
	
	
}
%>

</head>

<body>
<div style="margin:20px 0;"></div>
	<table>
		<tr>
			<td>开始时间:</td>
			<td>
				<input id="start" class="easyui-datetimebox"  style="width:200px">
			</td>
			<td>结束时间:</td>
			<td>
				<input id="end" class="easyui-datetimebox"  style="width:200px">
			</td>
		</tr>
	</table>
	
 <input type="button" id="create" onclick="down()" value="生成此时的文件"/><br></br>
 <input type="button" id="getit" onclick="downloads()" value="下载"/><br></br>
</body>
</html>

