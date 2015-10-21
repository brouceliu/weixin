<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>导航</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="导航,几步,查询">
	<meta http-equiv="description" content="导航">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">body,html,#allmap {width:100%;height: 100% ;overflow : hidden;margin:0;} </style>
 
 <script src="http://api.map.baidu.com/api?v=1.5&ak=F9af0fcc4b18993fc7a6559683068b31">
 </script>

</head>
  <%
String p1=request.getParameter("p1");
String p2=request.getParameter("p2");
 %>
  <body>
    <div id="allmap"></div>
  </body>
  <script type="text/javascript">
  //创建起点。终点经纬度坐标
  
  var p1=new BMap.Point(<%=p1%>);
  var p2=new BMap.Point(<%=p2%>);
  //创建地图，设置中心坐标
  var map=new BMap.Map("allmap");
  map.centerAndZoom(new BMap.Point((p1.lng+p2.lng)/2),(p1.lat+p2.lat/2),17);
  //右下角添加缩放按钮
  map.addControl(new BMap.NavigationControl({anchor : BMAP_ANCHOR_BOTTOM_RIGHT,type: BMAP_NAVIGATION_CONTROL_ZOOM}));
  //步行检索导航
  var walking = new BMap.WalkingRoute(map,{renderOptions:{map:map,autoViewport:true}});
  
  walking.search(p1,p2);
  </script>
</html>
