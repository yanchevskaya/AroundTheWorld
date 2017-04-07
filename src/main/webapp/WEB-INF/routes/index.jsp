<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="usertag" uri="com.epam.lizhen" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="logout" var="logout" />
    <fmt:message bundle="${loc}" key="routes" var="entitle" />
    <fmt:message bundle="${loc}" key="profile" var="profile" />
    <fmt:message bundle="${loc}" key="mainpage" var="main" />
    <fmt:message bundle="${loc}" key="description" var="description" />
    <fmt:message bundle="${loc}" key="author" var="author" />

    <title>${entitle}</title>
</head>

<body>

<a href = "/profile"> ${profile}</a> &nbsp&nbsp <a href = "/"> ${main}</a>  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href = "/logout"> ${logout}</a><br/><br/>

<form action="/routes" method="post">
    <input name = "traveller" value=""/>
    <input type="submit" value="Find"/><br/>
</form>

<table width="75%">
    <tr><th>${entitle}</th><th>${description}</th><th>${author}</th></tr><br/>
    <usertag:routes routeList="${routes}"/>

</body>
</html>


