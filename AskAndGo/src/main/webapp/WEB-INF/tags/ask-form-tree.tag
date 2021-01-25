<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<%@ attribute name="category" required="true" rtexprvalue="true" type="by.praded.ask_and_go.entity.Category" %>
<%@ attribute name="current_category" required="false" rtexprvalue="true" type="by.praded.ask_and_go.entity.Category" %>

<%@ attribute name="space" required="true" rtexprvalue="true" type="java.lang.String" %>

<option value="${category.id}"
        <c:if test="${current_category != null and current_category.id eq category.id}">
            selected
        </c:if>
        <c:if test="${category.subcategories.size() != 0}">
            disabled
        </c:if>>
    <jsp:text>${space}</jsp:text>${category.name}

</option>


<c:forEach var="cat" items="${category.subcategories}">

    <c:if test="${current_category!=null}">
        <tags:ask-form-tree category="${cat}" space="${space}&nbsp;&nbsp;&nbsp;&nbsp;" current_category="${current_category}" />
    </c:if>

    <c:if test="${current_category==null}">
        <tags:ask-form-tree category="${cat}" space="${space}&nbsp;&nbsp;&nbsp;&nbsp;" />
    </c:if>
</c:forEach>
