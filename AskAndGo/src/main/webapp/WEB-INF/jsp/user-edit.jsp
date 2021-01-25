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

    <h2><fmt:message key="edit.user.title"
                     bundle="${ rb }"/></h2>
    <div class="col-md-6 col-md-offset-3">


        <form action="<c:url value="/admin/edit-user" />" method="post" accept-charset="UTF-8">

            <div class="form-group">
                ID: <input type="text"
                           class="form-control" id="uid"
                           name="id" value="${user.id}" readonly>
            </div>

            <div class="form-group">
                <fmt:message key="admin.users.firstname"
                             bundle="${ rb }"/> <input type="text"
                                   class="form-control" id="first_name"
                                   name="firstName" value="${user.firstName}" readonly>
            </div>

            <div class="form-group">
                <fmt:message key="admin.users.lastname"
                             bundle="${ rb }"/> <input type="text"
                                  class="form-control" id="last_name"
                                  name="lastName" value="${user.lastName}" readonly>
            </div>
            <div class="form-group">
                <fmt:message key="admin.users.email"
                             bundle="${ rb }"/> <input type="text"
                              class="form-control" id="email"
                              name="email" value="${user.email}" readonly>
            </div>

            <div class="form-group">
                <fmt:message key="admin.users.username"
                             bundle="${ rb }"/> <input type="text"
                                  class="form-control" id="username"
                                  name="username" value="${user.username}" readonly>
            </div>

            <div class="form-group">
                <label for="role"></label>

                <select name="role" id="role" class="form-select form-select-lg mb-3"
                        aria-label=".form-select-lg example">
                    <option value="0" <c:if test="${user.role.name() eq 'ADMIN'}">selected</c:if>><fmt:message key="role.admin"
                                                                                                               bundle="${ rb }"/></option>
                    <option value="1" <c:if test="${user.role.name() eq 'MODERATOR'}">selected</c:if>><fmt:message key="role.moderator"
                                                                                                                   bundle="${ rb }"/></option>
                    <option value="2" <c:if test="${user.role.name() eq 'WRITER'}">selected</c:if>><fmt:message key="role.writer"
                                                                                                                bundle="${ rb }"/></option>
                    <option value="3" <c:if test="${user.role.name() eq 'READER'}">selected</c:if>><fmt:message key="role.reader"
                                                                                                                bundle="${ rb }"/></option>
                </select>

            </div>

            <button type="submit" class="btn btn-primary"><fmt:message key="submit"
                                                                       bundle="${ rb }"/></button>

        </form>
    </div>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>
