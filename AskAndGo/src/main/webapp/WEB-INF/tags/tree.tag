<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.text" var="rb"/>

<%@ attribute name="category" required="true" rtexprvalue="true" type="by.praded.ask_and_go.entity.Category" %>

<%@ attribute name="space" required="true" rtexprvalue="true" type="java.lang.String" %>

<div class="m-2" >
    <jsp:text>${space}</jsp:text>
    <details>
        <summary>
            ${category.name}






                    <div style="display: flex;">
            <form action="<c:url value="/admin/edit-category"/>" method="get">
                <input type="hidden"
                       id="to-edit-id"
                       name="category_id" value="${category.id}">
                <button type="submit" class="btn btn-primary"><fmt:message key="edit"
                                                                           bundle="${ rb }"/></button>
            </form>

            <form action="<c:url value="/admin/delete-category"/>" method="post">
                <input type="hidden"
                       id="to-delete-id"
                       name="category_id" value="${category.id}">
                <button type="submit" class="btn btn-primary ml-1"><fmt:message key="delete"
                                                                           bundle="${ rb }"/></button>
            </form>
            </div>
        </summary>

        <c:forEach var="cat" items="${category.subcategories}">

            <tags:tree category="${cat}" space="${space}&nbsp;&nbsp;&nbsp;&nbsp;"/>

        </c:forEach>
    </details>
</div>
