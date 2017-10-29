<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j" %>
<html>
<head>
    <title align="center">Журнал взаимодействий</title>
</head>
<body>
<table>
    <j:forEach items="${historyList}" var="history">
        <tr>
            <td><j:out value="${history.getId()}"/></td>
            <td><j:out value="${history.getDateTime()}"/></td>
            <td><j:out value="${history.getPatchFrom()}"/></td>
            <td><j:out value="${history.getPatch()}"/></td>
            <td><j:out value="${history.getStatus()}"/></td>
        </tr>
    </j:forEach>
</table>
</body>
</html>
