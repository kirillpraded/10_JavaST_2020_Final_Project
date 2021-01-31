<!DOCTYPE html>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.text" var="rb"/>
<html>
<head>
    <meta charset="utf-8">

    <title><fmt:message key="login.title"
                        bundle="${ rb }"/></title>

    <tags:styles />

    <link href="<c:url value="/css/profile.css" />" rel="stylesheet">

</head>
<body>
<jsp:include page="parts/navbar.jsp"/>
<section id="content" class="container">
    <div class="page-heading">
        <div class="media clearfix">
            <div class="media-left pr30">

                <img class="media-object mw150" src="<c:url value="/img/user/${user.profileImage}"/>"
                     style="max-height: 155px; max-width: 155px;" alt="...">
                <c:if test="${user.id eq sessionScope.auth_user.id}">

                    <form action="<c:url value="/update-image"/>" enctype="multipart/form-data" method="post">
                        <input type="file" name="image"  multiple="false"
                               accept="image/jpeg,image/png,image/gif,image/tiff,image/jpg, image/bmp" required/>
                        <button type="submit" class="btn btn-secondary"><fmt:message key="profile.image"
                                                                                     bundle="${ rb }"/></button>
                    </form>
                </c:if>
            </div>
            <div class="media-body va-m">
                <h2 class="media-heading">${user.username}</h2>
                <p class="lead">${user.firstName} ${user.lastName}</p>
                <div>
                    <p class="lead"><fmt:message key="profile.contact"
                                                 bundle="${ rb }"/>: ${user.email}</p>
                </div>
                <div>
                    <p class="lead"><fmt:message key="admin.users.reg"
                                                 bundle="${ rb }"/>: ${user.regDate}</p>
                </div>
                <c:if test="${user.id eq sessionScope.auth_user.id}">
                    <a href="<c:url value="/user-edit?user_id=${user.id}" />">

                        <button type="button" class="btn btn-primary"><fmt:message key="profile.update"
                                                                                   bundle="${ rb }"/></button>
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</section>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>