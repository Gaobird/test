
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>



<input type="button" value="denglu" id="btn"/>
<input type="button" value="tianjia" id="btn2"/>

<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">

    $("#btn").on("click",function(){
        $.ajax({
            url: '/user/login',
            type: 'POST',
            data: JSON.stringify(
                {"username":"test1",
                    "password":"123456"
                }
            ),
            contentType: "application/json;charset=utf-8",
            dataType: 'json',
            success: show,
        });
        function show(data){

            if(data.code<0){
                alert(data.msg);
            }

        }
    })


    $("#btn2").on("click",function(){
        $.ajax({
            url: '/user/addUser',
            type: 'POST',
            data: JSON.stringify(
                {"username":"test664",
                    "password":"123456"
                }
            ),
            contentType: "application/json;charset=utf-8",
            dataType: 'json',
            success: show,
        });
        function show(data){

            if(data.code<0){
                alert(data.msg);
            }

        }
    })



</script>
</body>
</html>
