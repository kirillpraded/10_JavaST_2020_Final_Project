<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "custom" uri = "/WEB-INF/tld/custom.tld"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.text" var="rb"/>
<html>
<head>
    <meta charset="UTF-8">

    <title><fmt:message key="error"
                        bundle="${ rb }"/></title>

    <tags:styles />
</head>
<body>
<jsp:include page="parts/navbar.jsp"/>

<div class="container">
    <custom:error code="${pageContext.errorData.statusCode}"/>
</div>

<jsp:include page="parts/footer.jsp"/>
</body>
</html>
