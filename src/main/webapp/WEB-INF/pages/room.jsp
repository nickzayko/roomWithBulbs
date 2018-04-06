<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Room</title>

    <style>
        .lamp-on {
            background: yellow;
            border: 1px solid #2D292E;
            border-radius: 40px;
            box-shadow: 0px 0px 10px 0 yellow;
            height: 20px;
            margin: 20px;
            width: 20px;
        }

        .lamp-off {
            background: ghostwhite;
            border: 1px solid #2D292E;
            border-radius: 40px;

            height: 20px;
            margin: 20px;
            width: 20px;
        }
    </style>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
    <script>

        $(document).ready(function () {
            setInterval(updateBulbStatus, 1000);
        });

        function updateBulbStatus() {
            $.ajax({
                url: "/updateBulbStatus",
                data: "roomNumber=${roomNumber}",
                dataType: "json",
                success: function (json) {
                    $("#bulb").removeClass().addClass(json);
                }
            })
        }

        //                    if (json = "lamp-off") {
        //                        $("#bulb").replaceWith("<div class='lamp-off'  id='bulb'></div> ");
        //                    } else {
        //                        $("#bulb").replaceWith("<div class='lamp-on'  id='bulb'></div> ");
        //                    }
        <%--function updateBulbStatus() {--%>
            <%--var param = $.ajax({--%>
                <%--url: "/updateBulbStatus",--%>
                <%--data: "roomNumber=${roomNumber}",--%>
                <%--dataType: "json",--%>
                <%--success: function (json) {--%>
                    <%--return (json);--%>
                <%--}--%>
            <%--}).responseText;--%>
            <%--if (param === "lamp-off") {--%>
                <%--$("#bulb").replaceWith("<div class='lamp-off'  id='bulb'></div> ");--%>
            <%--} else {--%>
                <%--$("#bulb").replaceWith("<div class='lamp-on'  id='bulb'></div> ");--%>
            <%--}--%>
        <%--}--%>


//                    $("#bulb").removeClass();
//                    $("#bulb").addClass(param);



    </script>

</head>
<body>
<div align="center">
    <h1>Welcome to room number ${roomNumber}</h1>
</div>


<div align="center">
    <div class="${className}" id="bulb"></div>
    <form action="bulbSwitch" method="post">
        <input type="hidden" name="room" value="${roomNumber}">
        <input type="submit" value="On" name="switch">
        <input type="submit" value="Off" name="switch">
    </form>
</div>

<div align="center">
    <a href="/">
        <button>Exit from room</button>
    </a>
</div>

</body>
</html>
