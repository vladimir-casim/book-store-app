<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="list-group">
  <c:forEach items="${genres}" var="genre">
    <a href="<spring:url value="/books/${genre.id}/genre"/>" class="list-group-item ${genre.id == currentGenre ? 'active':''}">
        ${genre.name}<span class="badge">${genre.booksCount}</span>
    </a>
  </c:forEach>
</div>