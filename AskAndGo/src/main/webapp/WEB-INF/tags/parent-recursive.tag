<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<%@ attribute name="category" required="true" rtexprvalue="true" type="by.praded.ask_and_go.entity.Category" %>


<c:choose>
    <c:when test="${not empty category.parent}">
        <tags:parent-recursive category="${category.parent}"/>
        ->${category.name}
    </c:when>
    <c:otherwise>
        ${category.name}
    </c:otherwise>
</c:choose>