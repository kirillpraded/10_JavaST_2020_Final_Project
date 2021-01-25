<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.text" var="rb"/>
<html>
<head>
    <meta charset="UTF-8">

    <title><fmt:message key="admin.title"
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

<div class="container m-2 mt-1">

    <div>
        <div>
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a href="#users" class="nav-link active" data-toggle="tab"><fmt:message key="admin.users"
                                                                                            bundle="${ rb }"/></a>
                </li>
                <li class="nav-item">
                    <a href="#categories" class="nav-link" data-toggle="tab"><fmt:message key="categories"
                                                                                          bundle="${ rb }"/></a>
                </li>
            </ul>
        </div>
    </div>
    <div class="tab-content">


        <div class="tab-pane fade show active container" id="users">

            <h4 class="mt-2"><fmt:message key="admin.users.list" bundle="${ rb }"/></h4>


            <c:if test="${not empty requestScope.msg_user_success}">
                <div class="alert alert-success" role="alert">
                    <fmt:message key="${msg_user_success}" bundle="${ rb }"/>
                </div>
            </c:if>
            <c:if test="${not empty requestScope.msg_user_error}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="${msg_user_error}" bundle="${ rb }"/>
                </div>
            </c:if>
            <div class="container">
                <hr>
                <br>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th><fmt:message key="admin.users.username" bundle="${ rb }"/></th>
                        <th><fmt:message key="admin.users.firstname" bundle="${ rb }"/></th>
                        <th><fmt:message key="admin.users.lastname" bundle="${ rb }"/></th>
                        <th><fmt:message key="admin.users.email" bundle="${ rb }"/></th>
                        <th><fmt:message key="admin.users.role" bundle="${ rb }"/></th>
                        <th><fmt:message key="admin.users.reg" bundle="${ rb }"/></th>
                        <th><fmt:message key="admin.users.act" bundle="${ rb }"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${users}">

                        <tr>
                            <td>
                                <c:out value="${user.id}"/>
                            </td>
                            <td>
                                <c:out value="${user.username}"/>
                            </td>
                            <td>
                                <c:out value="${user.firstName}"/>
                            </td>
                            <td>
                                <c:out value="${user.lastName}"/>
                            </td>
                            <td>
                                <c:out value="${user.email}"/>
                            </td>
                            <td>
                                <c:out value="${user.role}"/>
                            </td>
                            <td>
                                <c:out value="${user.regDate}"/>
                            </td>

                            <td>
                                <form action="<c:url value="/admin/edit-user" />" method="get">
                                    <input type="hidden"
                                           id="to-edit-id"
                                           name="id" value="${user.id}">
                                    <button type="submit" class="btn btn-primary mt-1">
                                        <fmt:message key="edit" bundle="${ rb }"/>
                                    </button>
                                </form>
                                <form action="<c:url value="/admin/delete-user" />" method="post">
                                    <input type="hidden"
                                           id="to-delete-id"
                                           name="id" value="${user.id}">
                                    <button type="submit" class="btn btn-primary mt-1">
                                        <fmt:message key="delete" bundle="${ rb }"/>
                                    </button>

                                </form>
                        </tr>
                    </c:forEach>

                    </tbody>

                </table>

            </div>
        </div>


        <div class="tab-pane fade" id="categories">
            <h4 class="mt-2"><fmt:message key="admin.categories.list" bundle="${ rb }"/></h4>
            <c:if test="${not empty requestScope.category_success}">
                <div class="alert alert-success" role="alert"><fmt:message key="${category_success}"
                                                                           bundle="${ rb }"/></div>
            </c:if>
            <c:if test="${not empty requestScope.category_error}">
                <div class="alert alert-danger" role="alert"><fmt:message key="${category_error}"
                                                                          bundle="${ rb }"/></div>
            </c:if>

            <div class="form-group">
                <c:forEach var="category" items="${categories}">

                    <tags:tree category="${category}" space=""/>

                </c:forEach>
            </div>

            <form action="<c:url value="/admin/add-category" />" method="POST" accept-charset="UTF-8">

                <div class="form-group">
                    <label for="parent_id"></label>
                    <fmt:message key="admin.category.msg" bundle="${ rb }"/>
                    <select name="parent_id" size="1" id="parent_id">
                        <option></option>
                        <c:forEach var="category" items="${categories}">

                            <tags:select-tree category="${category}" space=""/>

                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <fmt:message key="admin.category.name" bundle="${ rb }"/>
                    <input type="text"
                           class="form-control" id="name"
                           placeholder="<fmt:message key="admin.category.name" bundle="${ rb }"/>"
                           name="name"
                           value="${category_name}"
                           required>
                </div>

                <button type="submit" class="btn btn-primary"><fmt:message key="add" bundle="${ rb }"/></button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>