<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.text" var="rb"/>
<html>
<head>
    <meta charset="UTF-8">

    <title><fmt:message key="edit.user.title"
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

<div class="container">
    <div class="container col-md-5 col-md-offset-3" style="overflow: auto">
        <div>
            <h2><fmt:message key="edit.user.title"
                             bundle="${ rb }"/></h2>
            <c:if test="${not empty error_message}">
                <div class="alert alert-danger" role="alert"><fmt:message key="${error_message}"
                                                                          bundle="${ rb }"/></div>
            </c:if>

            <form action="<c:url value="/user-edit" />" method="post" accept-charset="UTF-8">

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
                                                           id="first_name"
                                                           name="first_name" value="${user.firstName}">
                    <c:if test="${not empty requestScope.first_name_msg}">
                        <div class="invalid-feedback"><fmt:message key="${first_name_msg}"
                                                                   bundle="${ rb }"/></div>
                    </c:if>
                </div>

                <div class="form-group">
                    <fmt:message key="admin.users.lastname"
                                 bundle="${ rb }"/> <input type="text"
                                                           class="form-control <c:if test="${not empty requestScope.last_name_msg}">is-invalid</c:if>"
                                                           id="last_name"
                                                           name="last_name" value="${user.lastName}">
                    <c:if test="${not empty requestScope.last_name_msg}">
                        <div class="invalid-feedback"><fmt:message key="${last_name_msg}"
                                                                   bundle="${ rb }"/></div>
                    </c:if>
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
            <form method="post" action="<c:url value="/change-password" />" accept-charset="UTF-8">
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
                                                           id="password" placeholder="Password"
                                                           name="new_password" required>
                    <c:if test="${not empty requestScope.error_match}">
                        <div class="invalid-feedback"><fmt:message key="${error_match}"
                                                                   bundle="${ rb }"/></div>
                    </c:if>
                </div>

                <div class="form-group">
                    <fmt:message key="password.confirmation"
                                 bundle="${ rb }"/> <input type="password"
                                                           class="form-control"
                                                           id="password_confirm" placeholder="Password Confirmation"
                                                           name="password_confirm" required>
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
</body>
</html>
