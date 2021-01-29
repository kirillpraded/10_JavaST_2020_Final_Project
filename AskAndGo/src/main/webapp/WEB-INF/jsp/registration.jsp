<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.text" var="rb"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>


<html>
<head>
    <meta charset="UTF-8">

    <title><fmt:message key="registration.title"
                        bundle="${ rb }"/></title>
    <tags:styles />
</head>
<body>
<jsp:include page="parts/navbar.jsp"/>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
    <h1><fmt:message key="registration.title"
                     bundle="${ rb }"/></h1>
    <form action="<c:url value="/registration"/>" id="registrationForm" method="post" accept-charset="UTF-8">

        <div class="form-group">
            <fmt:message key="admin.users.username"
                         bundle="${ rb }"/> <input type="text"
                                                   class="form-control <c:if test="${not empty requestScope.username_error}">is-invalid</c:if>"
                                                   placeholder="Username"
                                                   name="username" id="usernameValidation"
                                                   value="${username}"
                                                   required>
            <c:choose>
                <c:when test="${not empty requestScope.username_error}">
                    <div class="invalid-feedback">
                        <fmt:message key="${username_error}" bundle="${ rb }"/>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="invalid-feedback">
                        <fmt:message key="username.validation.error" bundle="${ rb }"/>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>

        <div class="form-group">
            <fmt:message key="admin.users.firstname"
                         bundle="${ rb }"/> <input type="text"
                                                   class="form-control <c:if test="${not empty requestScope.first_name_error}">is-invalid</c:if>"
                                                   id="firstName" placeholder="First name"
                                                   name="first_name"
                                                   value="${first_name}" required>
            <c:choose>
                <c:when test="${not empty requestScope.first_name_error}">
                    <div class="invalid-feedback">
                        <fmt:message key="${first_name_error}" bundle="${ rb }"/>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="invalid-feedback">
                        <fmt:message key="validation.firstname" bundle="${ rb }"/>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="form-group">
            <fmt:message key="admin.users.lastname"
                         bundle="${ rb }"/> <input type="text"
                                                   class="form-control <c:if test="${not empty requestScope.last_name_error}">is-invalid</c:if>"
                                                   id="lastName" placeholder="Last name"
                                                   name="last_name"

                                                   value="${last_name}" required>
            <c:choose>
            <c:when test="${not empty requestScope.last_name_error}">
                <div class="invalid-feedback">
                    <fmt:message key="${last_name_error}" bundle="${ rb }"/>
                </div>
            </c:when>
                <c:otherwise>
                    <div class="invalid-feedback">
                        <fmt:message key="validation.lastname" bundle="${ rb }"/>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="form-group">
            <fmt:message key="admin.users.email"
                         bundle="${ rb }"/> <input type="email"
                                                   class="form-control <c:if test="${not empty requestScope.email_error}">is-invalid</c:if>"
                                                   id="email" placeholder="Email"
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
                                                   class="form-control <c:if test="${not empty requestScope.password_error}">is-invalid</c:if>"
                                                   id="password" placeholder="Password"
                                                   name="password" required>
            <c:choose>
            <c:when test="${not empty requestScope.password_error}">
                <div class="invalid-feedback">
                    <fmt:message key="${password_error}" bundle="${ rb }"/>
                </div>
            </c:when>
                <c:otherwise>
                    <div class="invalid-feedback">
                        <fmt:message key="password.validation.error" bundle="${ rb }"/>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="form-group">
            <fmt:message key="password.confirmation"
                         bundle="${ rb }"/> <input type="password"
                                                   class="form-control <c:if test="${not empty requestScope.password_confirmation_error}">is-invalid</c:if>"
                                                   id="passwordConfirmation" placeholder="Password Confirmation"
                                                   name="password_confirm" required>
            <c:choose>
            <c:when test="${not empty requestScope.password_confirmation_error}">
                <div class="invalid-feedback">
                    <fmt:message key="${password_confirmation_error}" bundle="${ rb }"/>
                </div>
            </c:when>
                <c:otherwise>
                    <div class="invalid-feedback">
                        <fmt:message key="password.match.error" bundle="${ rb }"/>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <button type="submit" class="btn btn-primary"><fmt:message key="submit" bundle="${ rb }"/></button>
    </form>
</div>
<jsp:include page="parts/footer.jsp"/>

<script src="<c:url value="/js/validators.js" /> "></script>
<script src="<c:url value="/js/regValidation.js" /> "></script>

</body>
</html>