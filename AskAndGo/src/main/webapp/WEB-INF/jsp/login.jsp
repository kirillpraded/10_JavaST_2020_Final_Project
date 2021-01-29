<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.text" var="rb"/>
<html>

<head>
    <meta charset="utf-8">

    <title><fmt:message key="login.title"
                        bundle="${ rb }"/></title>

    <tags:styles />
</head>
<body>
<jsp:include page="parts/navbar.jsp"/>
<div class="container col-md-5 col-md-offset-3" style="overflow: auto">
        <h1><fmt:message key="login.form"
                         bundle="${ rb }"/></h1>
        <c:if test="${not empty requestScope.message}">
            <div class="alert alert-danger" role="alert"><fmt:message key="${message}"
                                                                      bundle="${ rb }"/></div>
        </c:if>
        <span class="form-group" style='font-size:100px;'>&#9749;</span>

        <form action="<c:url value="/login" />" method="post" accept-charset="UTF-8">

            <div class="form-group">
                <fmt:message key="admin.users.username"
                             bundle="${ rb }"/> <input
                    type="text"
                    class="form-control <c:if test="${not empty requestScope.message}">is-invalid</c:if>"
                    id="username"
                    placeholder="<fmt:message key="admin.users.username" bundle="${ rb }"/>"
                    name="username"
                    value="<c:if test="${not empty requestScope.username}">${username}</c:if>"
                    required>


            </div>

            <div class="form-group">
                <fmt:message key="password"
                             bundle="${ rb }"/> <input
                    type="password"
                    class="form-control" id="password"
                    placeholder="<fmt:message key="password" bundle="${ rb }"/>"
                    name="password"
                    required>
            </div>

            <div>
                <button type="submit" class="btn btn-primary"><fmt:message key="navbar.login"
                                                                           bundle="${ rb }"/></button>
                <a href="<c:url value="/registration"/>"><fmt:message key="login.create"
                                                                      bundle="${ rb }"/></a>
            </div>
        </form>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>