<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="../error/dataError.jsp" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="registration" var="registration"/>
    <fmt:message bundle="${loc}" key="sign.out" var="sign_out"/>
    <fmt:message bundle="${loc}" key="password" var="password"/>
    <fmt:message bundle="${loc}" key="firstname" var="first_name"/>
    <fmt:message bundle="${loc}" key="lastname" var="last_name"/>
    <fmt:message bundle="${loc}" key="day" var="day"/>
    <fmt:message bundle="${loc}" key="month" var="month"/>
    <fmt:message bundle="${loc}" key="year" var="year"/>
    <fmt:message bundle="${loc}" key="dob" var="dob"/>
    <fmt:message bundle="${loc}" key="gender" var="gender"/>
    <fmt:message bundle="${loc}" key="male" var="male"/>
    <fmt:message bundle="${loc}" key="female" var="female"/>

    <title>${registration}</title>

</head>
<body>

<form action="/registration/email" method="post">

    ${first_name} <br/> <input name = "first_name" placeholder="${first_name}"/><br/>
    ${last_name} <br/> <input name = "last_name" placeholder="${last_name}"/><br/>
    ${dob}<br/>
    <input name = "day" placeholder="${day}"/> <input name="month" placeholder="${month}"/> <input name="year" placeholder="${year}"/><br/>
    ${gender}<input type="radio" name = "gender" value="0"/> ${male} <input type="radio" name = "gender" value = "1"/> ${female}<br/>

    <br/><input type = "submit" value = "${sign_out}"/>

</form>

</body>
</html>


