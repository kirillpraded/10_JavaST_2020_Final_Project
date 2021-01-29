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

    <tags:styles/>
</head>
<body>
<jsp:include page="parts/navbar.jsp"/>

<div class="container">

    <h2><fmt:message key="category.edit.form"
                     bundle="${ rb }"/></h2>
    <div class="col-md-6 col-md-offset-3">


        <form action="<c:url value="/admin/edit-category" />" id="categoryEditForm" method="post" accept-charset="UTF-8">
            <div class="form-group">
                <fmt:message key="admin.category.name"
                             bundle="${ rb }"/><input type="text"
                       class="form-control" id="categoryName"
                       name="category_name" value="${category.name}" >
                <div class="invalid-feedback">
                    <fmt:message key="category.validation.error" bundle="${ rb }"/>
                </div>
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
<script src="<c:url value="/js/validators.js" /> "></script>
<script src="<c:url value="/js/categoryEditValidation.js" /> "></script>
</body>
</html>
