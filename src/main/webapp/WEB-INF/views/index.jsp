<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>BookStore</title>
    <!-- Bootstrap -->
    <link href="<spring:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<spring:url value="/resources/css/style.css"/>" rel="stylesheet">
</head>
<body>

<jsp:include page="../views/fragments/header.jsp" ></jsp:include>

<div id="wrapper" class="container">

    <div id="category" class="row">
        <div id="featured" class="col-md-9">
            <div class="row">

                <c:forEach items="${books}" var="book">
                    <div class="product col-sm-6 col-md-4 col-lg-3">
                        <div class="thumbnail well">
                            <div class="image-wrapper">
                                <img src="data:image/jpg;base64,<c:out value='${book.imageFile}'/>" title="${book.title}" >
                            </div>
                            <div class="caption">
                                <div class="book_title"><h4>${book.title}</h4></div>
                                <a href="<spring:url value="/books/${book.id}/moreinfo"/>" class="btn btn-primary" role="button">More</a>
                                <a href="<spring:url value="/resources/files/${book.bookFilePath}"/>" class="btn btn-success" role="button">Load</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="col-md-12 pagesBlock">
                    <ul class="pagination">
                        <c:forEach begin="${1}" end="${pagesCount}" var="p">
                            <li class="${p == currentPage ? 'active' : 'none'}">
                                <a href="<c:url value="/books"><c:param name="page"  value="${p}" /></c:url>">${p}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>

        <div id="sidebar" class="col-md-3">
            <jsp:include page="../views/fragments/genres_navigate.jsp" ></jsp:include>
        </div>

    </div><!-- end row -->

    <div id="footer">
        <div class="footer-bottom row">
            <div class="copyrights col-sm-6 col-md-6"><div class="well">6<br>.copyrights</div></div>
            <div class="social-icons col-sm-6 col-md-6"><div class="well">6<br>.social-icons</div></div>
        </div>
    </div>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
</body>
</html>