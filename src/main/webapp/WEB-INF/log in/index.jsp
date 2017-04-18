<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Welcome to Around the World!</title>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.button.name.ru" var="ru_name" />
    <fmt:message bundle="${loc}" key="local.button.name.en" var="en_name" />
    <fmt:message bundle="${loc}" key="sign.out" var="sign_out" />
    <fmt:message bundle="${loc}" key="log.in" var="log_in" />
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/entrance" method="post">
    <input type="submit" value="${log_in}" /><br />
</form>

<form action="<c:url value="/registration"/>" method="post">
    <input type="submit" value="${sign_out}" /><br />
</form>

${language} <a href = "/local?local=ru"> ${ru_name}</a> &nbsp <a href = "/local?local=en"> ${en_name} </a>

</body>
</html>
