<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="usertag" uri="com.epam.lizhen" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="logout" var="logout" />
    <fmt:message bundle="${loc}" key="profile" var="profile" />
    <fmt:message bundle="${loc}" key="mainpage" var="main" />

    <title>${country}</title>
</head>

<body>

<a href = "profile"> ${profile}</a> &nbsp&nbsp <a href = "/"> ${main}</a>  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href = "logout"> ${logout}</a><br/><br/>

<form action="/cities" method="post">
    <input type="hidden" name="command" value="naming" />
    <input name = "traveller" value=""/>
    <input type="submit" value="Find"/><br/>
</form>

<table width="25%">
    <tr><th>${country}</th></tr><br/>
    <usertag:cities cityList="${cities}"/>


</body>
</html>


