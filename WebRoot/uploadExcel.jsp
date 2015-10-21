<%@ page language="java" pageEncoding="UTF-8"%>
<%
  String name;
  name=(String)session.getAttribute("login");
if(name==null){
	response.sendRedirect("index.jsp");
} else if(name.equals("success")==false){
	response.sendRedirect("index.jsp");
}
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>导入客服消息</title>
    <script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/wushang.js"></script>
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath}/servlet/UploadCustom" enctype="multipart/form-data" method="post">
        
        上传文件1：<input type="file" name="file1"><br/>
     
        <input type="submit" value="提交并且提交">
    </form>
    
    
    
  </body>
</html>