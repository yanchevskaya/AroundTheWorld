<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="${error}" var="message" />
    <fmt:message bundle="${loc}" key="error" var="title" />

    <title>${title}</title>
</head>
<body>

${message}

<br/><br/>

<jsp:include page="../registration/index.jsp"/>

</body>
</html>
