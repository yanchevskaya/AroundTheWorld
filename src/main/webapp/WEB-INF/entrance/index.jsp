<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="log.in" var="log_in" />
    <fmt:message bundle="${loc}" key="password" var="password" />
    <title>${log_in}</title>

</head>
<body>

<form action="/entrance/authorization" method="post">
    <input type="hidden" name="command" value="naming" /><c:out value="e-mail:"/>
    <input name= "j_username" placeholder="e-mail"/><br/><c:out value="${password}"/>
    <input type = password name = "j_password" placeholder="password"/><br/>

    <input type = "submit" value="${log_in}"/>
</form>

</body>
</html>
