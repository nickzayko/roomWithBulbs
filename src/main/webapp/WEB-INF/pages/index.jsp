<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Start page</title>
</head>
<body>
<div align="center">
    <h1>Choose one of exist rooms</h1>
</div>

<div align="center">
    <table>
        <tr>
            <c:forEach begin="1" end="4" varStatus="roomNumber">
                <td>
                    <form action="/interToRoom" method="post">
                        <input type="submit" value=${roomNumber.count} name="roomNumber">
                    </form>
                </td>
            </c:forEach>
        </tr>
    </table>
</div>

</body>
</html>
