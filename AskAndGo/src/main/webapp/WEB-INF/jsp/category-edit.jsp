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

    <title><fmt:message key="category.edit.title"
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

    <h2><fmt:message key="category.edit.form"
                     bundle="${ rb }"/></h2>
    <div class="col-md-6 col-md-offset-3">


        <form action="${pageContext.request.contextPath}/admin/edit-category" method="post" accept-charset="UTF-8">
            <div class="form-group">
                <fmt:message key="admin.category.name"
                             bundle="${ rb }"/><input type="text"
                       class="form-control" id="category_name"
                       name="category_name" value="${category.name}" >
            </div>
            <input type="hidden" id="category_id" name="category_id" value="${category.id}">
            <div class="form-group">
                <fmt:message key="category.edit.parent"
                             bundle="${ rb }"/><label for="parent_id"></label>
                <select name="parent_id" size="1" id="parent_id">
                    <option></option>
                    <c:forEach var="cat" items="${categories}">

                        <tags:select-tree category="${cat}" space="" current_category="${category}"/>

                    </c:forEach>
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
