<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<%@ attribute name="category" required="true" rtexprvalue="true" type="by.praded.ask_and_go.entity.Category" %>
<%@ attribute name="current_category" required="false" rtexprvalue="true" type="by.praded.ask_and_go.entity.Category" %>

<%@ attribute name="space" required="true" rtexprvalue="true" type="java.lang.String" %>

<option value="${category.id}" <c:if test="${requestScope.parentId eq category.id}">selected</c:if>
                                <c:if test="${current_category != null and current_category.parent.id eq category.id}">selected</c:if>>
    <jsp:text>${space}</jsp:text>${category.name}
</option>


<c:forEach var="cat" items="${category.subcategories}">

    <tags:select-tree category="${cat}" space="${space}&nbsp;&nbsp;&nbsp;&nbsp;" current_category="${current_category}"/>

</c:forEach>
