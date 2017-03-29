<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="registration" var="registration"/>
    <fmt:message bundle="${loc}" key="sign.out" var="sign_out" />
    <fmt:message bundle="${loc}" key="password" var="password" />
    <fmt:message bundle="${loc}" key="email" var="email" />
    <fmt:message bundle="${loc}" key="enterpassword" var="enter_password" />
    <fmt:message bundle="${loc}" key="password.confirmation" var="confirm_password" />
    <fmt:message bundle="${loc}" key="mainpage" var="main" />

    <title>${registration}</title>

</head>
<body>

<form action="/registration/safeInfo" method="post">

    <input type="hidden" name="command" value="naming" />
    e-mail: <br/> <input type="email" name = "j_username" placeholder="${email}"/><br/>
    ${password} <br/> <input type = password name = "j_password" placeholder="${enter_password}"/><br/>
    <input type="password" name="j_password2" placeholder="${confirm_password}"/><br/>

    <br/><input type = "submit" value = "${sign_out}"/>
</form>
<br/>
<a href = "/"> ${main}</a>

</body>
</html>


