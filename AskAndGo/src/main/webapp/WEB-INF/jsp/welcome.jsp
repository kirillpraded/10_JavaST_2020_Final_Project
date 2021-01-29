<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="custom" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.text" var="rb"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<html>
<head>
    <title>Ask&Go</title>
    <meta charset="UTF-8">


    <tags:styles />

</head>
<body>
<jsp:include page="parts/navbar.jsp"/>
<main>
    <div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light">
        <div class="col-md-5 p-lg-5 mx-auto my-5">
            <h1 class="display-4 fw-normal">Ask&Go</h1>
            <p class="lead fw-normal"><fmt:message key="footer.info"
                                                   bundle="${ rb }"/></p>
            <a class="btn btn-outline-secondary" href="<c:url value="/login" />"><fmt:message key="navbar.login" bundle="${ rb }"/></a>
        </div>
        <div class="product-device shadow-sm d-none d-md-block"></div>
        <div class="product-device product-device-2 shadow-sm d-none d-md-block"></div>
    </div>
</main>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>