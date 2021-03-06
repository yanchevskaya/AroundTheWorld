<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="usertag" uri="com.epam.lizhen" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="logout" var="logout" />
    <fmt:message bundle="${loc}" key="change" var="entitle" />
    <fmt:message bundle="${loc}" key="profile" var="profile" />
    <fmt:message bundle="${loc}" key="mainpage" var="main" />
    <fmt:message bundle="${loc}" key="change" var="change" />
    <fmt:message bundle="${loc}" key="description" var="description" />
    <fmt:message bundle="${loc}" key="cancel" var="cancel" />
    <fmt:message bundle="${loc}" key="call" var="call" />

    <title>${entitle}</title>
</head>

<body>

<a href = "profile"> ${profile}</a> &nbsp&nbsp <a href = "/"> ${main}</a>  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href = "logout"> ${logout}</a><br/><br/>

<form action="/myroutes/manage" method="post">
    <input type="hidden" name = "change" value="${route.id}">
    ${call} <input name = "name" value="${route.name}"/>
    ${description}<input name = "route.description" value="${route.description}"/>
    <input type="submit" value="${change}"/><br/>
</form>

<form action="/myroutes" method="post">
    <input type="submit" value="${cancel}"/><br/>
</form>

</body>
</html>


