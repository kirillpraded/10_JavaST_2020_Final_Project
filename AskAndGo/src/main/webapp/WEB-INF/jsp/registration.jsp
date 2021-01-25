<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.text" var="rb"/>

<html>
<head>
    <meta charset="UTF-8">

    <title><fmt:message key="registration.title"
                        bundle="${ rb }"/></title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


    <!-- Custom styles for navbar -->
    <link href="<c:url value="/css/navbar-top-fixed.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="parts/navbar.jsp"/>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
    <h1><fmt:message key="registration.title"
                     bundle="${ rb }"/></h1>
    <form action="<c:url value="/registration"/>" method="post" accept-charset="UTF-8">

        <div class="form-group">
            <fmt:message key="admin.users.username"
                         bundle="${ rb }"/> <input type="text"
                            class="form-control <c:if test="${not empty requestScope.username_error}">is-invalid</c:if>" id="username" placeholder="Username"
                            name="username"
                            value="${username}"
                            required>
            <c:if test="${not empty requestScope.username_error}">
                <div class="invalid-feedback">
                    <fmt:message key="${username_error}" bundle="${ rb }"/>
                </div>
            </c:if>
        </div>

        <div class="form-group">
            <fmt:message key="admin.users.firstname"
                         bundle="${ rb }"/> <input type="text"
                              class="form-control <c:if test="${not empty requestScope.first_name_error}">is-invalid</c:if>" id="first_name" placeholder="First name"
                              name="first_name"
                              value="${first_name}" required>
            <c:if test="${not empty requestScope.first_name_error}">
                <div class="invalid-feedback">
                    <fmt:message key="${first_name_error}" bundle="${ rb }"/>
                </div>
            </c:if>
        </div>

        <div class="form-group">
            <fmt:message key="admin.users.lastname"
                         bundle="${ rb }"/> <input type="text"
                             class="form-control <c:if test="${not empty requestScope.last_name_error}">is-invalid</c:if>" id="last_name" placeholder="Last name"
                             name="last_name"
                             value="${last_name}" required>
            <c:if test="${not empty requestScope.last_name_error}">
                <div class="invalid-feedback">
                        <fmt:message key="${last_name_error}" bundle="${ rb }"/>
                </div>
            </c:if>
        </div>

        <div class="form-group">
            <fmt:message key="admin.users.email"
                         bundle="${ rb }"/> <input type="email"
                             class="form-control <c:if test="${not empty requestScope.email_error}">is-invalid</c:if>" id="email" placeholder="Email"
                             name="email" value="${email}" required>
            <c:if test="${not empty requestScope.email_error}">
                <div class="invalid-feedback">
                    <fmt:message key="${email_error}" bundle="${ rb }"/>
                </div>
            </c:if>
        </div>

        <div class="form-group">
            <fmt:message key="password"
                         bundle="${ rb }"/> <input type="password"
                             class="form-control <c:if test="${not empty requestScope.password_error}">is-invalid</c:if>" id="password" placeholder="Password"
                             name="password" required>
            <c:if test="${not empty requestScope.password_error}">
                <div class="invalid-feedback">
                       <fmt:message key="${password_error}" bundle="${ rb }"/>
                </div>
            </c:if>
        </div>

        <div class="form-group">
            <fmt:message key="password.confirmation"
                         bundle="${ rb }"/> <input type="password"
                                          class="form-control <c:if test="${not empty requestScope.password_confirmation_error}">is-invalid</c:if>"
                                          id="password_confirm" placeholder="Password Confirmation"
                                          name="password_confirm" required>
            <c:if test="${not empty requestScope.password_confirmation_error}">
                <div class="invalid-feedback">
                    <fmt:message key="${password_confirmation_error}" bundle="${ rb }"/>
                </div>
            </c:if>
        </div>

        <button type="submit" class="btn btn-primary"><fmt:message key="submit" bundle="${ rb }"/></button>
    </form>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>