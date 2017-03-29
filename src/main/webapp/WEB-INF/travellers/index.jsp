<%@ taglib prefix="usertag" uri="com.epam.lizhen" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="travellers" var="entitle"/>
    <fmt:message bundle="${loc}" key="currentplace" var="currentplace" />
    <fmt:message bundle="${loc}" key="mainpage" var="main" />
    <fmt:message bundle="${loc}" key="logout" var="logout" />
    <fmt:message bundle="${loc}" key="profile" var="profile" />


    <title>${entitle}</title>
</head>
<body>
<a href = "profile"> ${profile}</a> &nbsp&nbsp <a href = "/"> ${main}</a>  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href = "logout"> ${logout}</a><br/><br/>

<form action="/travellers/find" method="post">
    <input name = "traveller" value=""/>
    <input type="submit" value="Find"/><br/>
</form>

<table border="1" cellpadding="3" width="50%">
<tr><th>${entitle}</th><th>${currentplace}</th></tr><br/>
<usertag:travellers travellersList="${travellers}"/>

</body>
</html>
