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
    <fmt:message bundle="${loc}" key="create" var="create" />
    <fmt:message bundle="${loc}" key="mainpage" var="main" />
    <fmt:message bundle="${loc}" key="description" var="description" />
    <fmt:message bundle="${loc}" key="choose" var="choose" />
    <fmt:message bundle="${loc}" key="delete" var="delete" />

    <title>${entitle}</title>
</head>

<body>

<a href = "/profile"> ${profile}</a> &nbsp&nbsp <a href = "/"> ${main}</a>  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href = "/logout"> ${logout}</a><br/><br/>

<form action="/myroutes/create" method="post">
    <input type="submit" value="${create}"/><br/>
</form>

<form action ="/myroutes/delete" method = "post">
    <input type="submit" value="${delete}">

<table width="50%">
    <tr><th>${entitle}</th><th>${description}</th><th>${choose}</th></tr><br/>
    <usertag:myRoutes routeList="${routes}"/>
</table>
</form>

</body>
</html>



