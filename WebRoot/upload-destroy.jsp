<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>文件上传</title>
    <script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/wushang.js"></script>
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath}/servlet/destroyCard" enctype="multipart/form-data" method="post">
        
        上传文件1：<input type="file" name="file1"><br/>
     
        <input type="submit" value="提交">
    </form>
    
     <input type="button" onclick="addData()" value="导入">
    
  </body>
</html>