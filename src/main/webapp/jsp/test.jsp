<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/9
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<input type="text" id = "test" />
<input type="button" value="提交5" id="btn"/>

<form action="/user/role" method="post" style="margin: 0 auto">
    <button type="submit">查看权限</button>
</form>



<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    $("#btn").on("click",function(){
        alert("wochulaile")
        $.ajax({
            url: '/user/deleteUser',
            type: 'POST',
            data: JSON.stringify(3),
            contentType: "application/json;charset=utf-8",
            dataType: 'json',
            success: show,
        });
        function show(data){
            console.log(data.name);
        }
    })
</script>
</body>
</html>
