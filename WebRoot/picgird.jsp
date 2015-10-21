<%@page contentType="text/html;charset=utf-8"%>

<%@page import="java.text.SimpleDateFormat" import="java.util.*"  import="javax.servlet.http.*"%>
<%@page session="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<%
 /*  String name;
  name=(String)session.getAttribute("userwushang");
if(name==null){
	response.sendRedirect("index.jsp");
} else if(name.equals("lzr")==false){
	response.sendRedirect("index.jsp");
} */
%>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/main1.js"></script>
<link rel="shortcut icon" href="ico/lzr.ico"  type="image/x-icon"  />
<link rel="bookmark" href="ico/lzr.ico"  type="image/x-icon"/>
<link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/themes/color.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">

<body>

<style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
    
    <h2>微信图文消息编辑</h2>
   <a href="index.jsp" class="b">登录</a>
    <table id="dg" title="微信图文消息" class="easyui-datagrid" style="width:330;height:550px"
            url="servlet/PicMeg" method="get"
            toolbar="#toolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="id" width="50">ID(唯一的id)</th>
                <th field="mid" width="50">MID(多图文消息mid一样)</th>
                 <th field="title" width="50">标题</th>
                <th field="desc" width="50">描述</th>
                <th field="picurl" width="50">图片url</th>
                <th field="url" width="50">url</th>
                
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateUser()">编辑</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addPicMeg()">添加</a> 
    </div>
    
    
  <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
       
        <form id="fm" method="post" novalidate>
        <div class="fitem">
                <label>id:</label>
                <input name="id" id="id" type="hidden" />
            </div>
         <div class="fitem">
                <label>mid:</label>
                <input name="mid" id="mid" />
            </div>
            <div class="fitem">
                <label>标题:</label>
                <input name="title" id="title"/>
            </div>
            <div class="fitem">
                <label>描述:</label>
                <input name="desc" id="description"/>
            </div>        
            <div class="fitem"><label >图片url</label>
                <input name="picurl" id="picurl" />
            </div>
           <div class="fitem"><label >链接url</label>
                <input name="url" id="linkurl"/>
            </div>
          
        </form>
  
  <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="editeDate()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close');">取消</a>
   </div>
      </div>
    
    
   
    <div id="Add" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px" closed="true" buttons="#dlgAdd-buttons">
       
        <form id="fm1" method="post" novalidate>
        
            <div class="fitem">
                <label>mid:</label>
                <input name="mid" id="mids"  class="easyui-validatebox" required="true" />
            </div>
            <div class="fitem">
                <label>标题:</label>
                <input name="title" id="titles" class="easyui-validatebox" required="true" />
            </div>
            <div class="fitem">
                <label>描述:</label>
                <input name="description" id="descriptions" class="easyui-validatebox" required="true" />
            </div>        
            <div class="fitem"><label for="">图片url</label>
                <input name="picurl" id="picurls" class="easyui-validatebox" required="true" />
            </div>
           <div class="fitem"><label for="">链接url</label>
                <input name="linkurl" id="linkurls" class="easyui-validatebox" required="true" />
            </div>
        
        </form>
   
   
   <div id="dlgAdd-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="addDate()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close');">取消</a>
   </div>
     </div>
   

</body>

</html>

