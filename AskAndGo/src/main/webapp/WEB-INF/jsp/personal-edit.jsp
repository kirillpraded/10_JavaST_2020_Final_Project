<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.text" var="rb"/>
<html>
<head>
    <meta charset="UTF-8">

    <title><fmt:message key="edit.user.title"
                        bundle="${ rb }"/></title>

    <tags:styles />
</head>
<body>
<jsp:include page="parts/navbar.jsp"/>

<div class="container">
    <div class="container col-md-5 col-md-offset-3" style="overflow: auto">
        <div>
            <h2><fmt:message key="edit.user.title"
                             bundle="${ rb }"/></h2>
            <c:if test="${not empty error_message}">
                <div class="alert alert-danger" role="alert"><fmt:message key="${error_message}"
                                                                          bundle="${ rb }"/></div>
            </c:if>

            <form action="<c:url value="/user-edit" />" id="personalInfoChangeForm" method="post" accept-charset="UTF-8">

                <div class="form-group">
                    <fmt:message key="admin.users.username"
                                 bundle="${ rb }"/> <input type="text"
                                                           class="form-control" id="username"
                                                           name="username" value="${user.username}" readonly>
                </div>

                <div class="form-group">
                    <fmt:message key="admin.users.firstname"
                                 bundle="${ rb }"/> <input type="text"
                                                           class="form-control <c:if test="${not empty requestScope.first_name_msg}">is-invalid</c:if>"
                                                           id="firstName"
                                                           name="first_name" value="${user.firstName}">
                    <c:choose>
                    <c:when test="${not empty requestScope.first_name_msg}">
                        <div class="invalid-feedback"><fmt:message key="${first_name_msg}"
                                                                   bundle="${ rb }"/></div>
                    </c:when>
                        <c:otherwise>
                            <div class="invalid-feedback"><fmt:message key="validation.firstname"
                                                                       bundle="${ rb }"/></div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="form-group">
                    <fmt:message key="admin.users.lastname"
                                 bundle="${ rb }"/> <input type="text"
                                                           class="form-control <c:if test="${not empty requestScope.last_name_msg}">is-invalid</c:if>"
                                                           id="lastName"
                                                           name="last_name" value="${user.lastName}">
                    <c:choose>
                    <c:when test="${not empty requestScope.last_name_msg}">
                        <div class="invalid-feedback"><fmt:message key="${last_name_msg}"
                                                                   bundle="${ rb }"/></div>
                    </c:when>
                        <c:otherwise>
                            <div class="invalid-feedback"><fmt:message key="validation.lastname"
                                                                       bundle="${ rb }"/></div>

                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="form-group">
                    <fmt:message key="admin.users.email"
                                 bundle="${ rb }"/> <input type="text"
                                                           class="form-control <c:if test="${not empty requestScope.email_msg}">is-invalid</c:if>"
                                                           id="email"
                                                           name="email" value="${user.email}">
                    <c:if test="${not empty requestScope.email_msg}">
                        <div class="invalid-feedback"><fmt:message key="${email_msg}"
                                                                   bundle="${ rb }"/></div>
                    </c:if>
                </div>
                <input type="hidden"
                       class="form-control"
                       name="user_id" value="${user.id}">


                <button type="submit" class="btn btn-primary"><fmt:message key="submit"
                                                                           bundle="${ rb }"/></button>

            </form>
        </div>
        <hr>
        <div>
            <h2><fmt:message key="edit.user.password"
                             bundle="${ rb }"/></h2>
            <c:if test="${not empty error_message}">
                <div class="alert alert-danger" role="alert"><fmt:message key="${error_message}"
                                                                          bundle="${ rb }"/></div>
            </c:if>

            <form method="post" action="<c:url value="/change-password" />" id="changePasswordForm" accept-charset="UTF-8">
                <div class="form-group">
                    <fmt:message key="password.old"
                                 bundle="${ rb }"/> <input type="password"
                                                           class="form-control <c:if test="${not empty requestScope.password_error}">is-invalid</c:if>"
                                                           id="password" placeholder="Password"
                                                           name="old_password" required>
                    <c:if test="${not empty requestScope.password_error}">
                        <div class="invalid-feedback"><fmt:message key="${password_error}"
                                                                   bundle="${ rb }"/></div>
                    </c:if>
                </div>

                <div class="form-group">
                    <fmt:message key="password"
                                 bundle="${ rb }"/> <input type="password"
                                                           class="form-control
                                                            <c:if test="${not empty requestScope.error_match}">
                                                                is-invalid
                                                            </c:if>"
                                                           id="newPassword" placeholder="Password"
                                                           name="new_password" required>
                    <c:choose>
                    <c:when test="${not empty requestScope.error_match}">
                        <div class="invalid-feedback"><fmt:message key="${error_match}"
                                                                   bundle="${ rb }"/></div>
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
                                                           class="form-control"
                                                           id="passwordConfirmation" placeholder="Password Confirmation"
                                                           name="password_confirm" required>
                    <div class="invalid-feedback">
                        <fmt:message key="password.match.error" bundle="${ rb }"/>
                    </div>
                </div>

                <input type="hidden"
                       class="form-control"
                       name="user_id"
                       value="${user.id}">


                <button type="submit" class="btn btn-primary"><fmt:message key="submit"
                                                                           bundle="${ rb }"/></button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="parts/footer.jsp"/>

<script src="<c:url value="/js/validators.js" /> "></script>
<script src="<c:url value="/js/passwordChangeValidation.js" /> "></script>
</body>
</html>
