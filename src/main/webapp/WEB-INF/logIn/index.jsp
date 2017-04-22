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
    <fmt:message bundle="${loc}" key="language" var="language" />
</head>
<body>

<form action="<c:url value="/entrance"/>" method="get">
    <input type="submit" value="${log_in}"/><br />
</form>

<form action="<c:url value="/registration"/>" method="get">
    <input type="submit" value="${sign_out}"/><br/>
</form>

${language} <a href = "<c:url value="/local?local=ru"/>"> ${ru_name}</a> &nbsp <a href = "<c:url value="/local?local=en"/>"> ${en_name} </a>

</body>
</html>
