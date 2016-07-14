<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

      <spring:url value="/books/admin/update" var="formUrl"/>
      <form class="form-horizontal" action="${formUrl}" method="POST" enctype="multipart/form-data">
        <fieldset>
          <legend>Updating the book</legend>

          <form:hidden path="book.id" />
          <form:hidden path="book.imageFile" />
          <form:hidden path="book.bookFilePath" />
          <form:hidden path="book.genre.id" />

          <spring:bind path="book.title">
            <div class="form-group ${status.error ? 'has-error' : ''}">
              <label for="inputTitle" class="col-md-2 control-label">Title</label>
              <div class="col-md-6">
                <form:input  path="book.title" cssClass="form-control" id="inputTitle" />
                <form:errors path="book.title" cssClass="text-danger" />
              </div>
            </div>
          </spring:bind>

          <spring:bind path="book.pageCount">
            <div class="form-group ${status.error ? 'has-error' : ''}">
              <label for="inputPageCount" class="col-md-2 control-label">Page count</label>
              <div class="col-md-6">
                <form:input path="book.pageCount" cssClass="form-control" id="inputPageCount" />
                <form:errors path="book.pageCount" cssClass="text-danger" />
              </div>
            </div>
          </spring:bind>

          <spring:bind path="book.publisher.name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
              <label for="inputPublisher" class="col-md-2 control-label">Publisher</label>
              <div class="col-md-6">
                <input name="${status.expression}" value="${status.value}" class="form-control" id="inputPublisher" />
                <form:errors path="book.publisher.name" cssClass="text-danger" />
                <!-- <input name="publisher" class="form-control" id="publisher" /> -->
              </div>
            </div>
          </spring:bind>

          <spring:bind path="book.publishYear">
            <div class="form-group ${status.error ? 'has-error' : ''}">
              <label for="publishingDate" class="col-md-2 control-label">Publishing date</label>
              <div class="col-md-6">
                <form:input path="book.publishYear" cssClass="form-control" id="publishingDate"  />
                <form:errors path="book.publishYear" cssClass="text-danger" />
                <!-- <input name="publish_date" class="form-control" id="publishDate"  placeholder="YYYY-MM-DD" />-->
              </div>
            </div>
          </spring:bind>

          <spring:bind path="book.language">
            <div class="form-group ${status.error ? 'has-error' : ''}">
              <label for="inputLanguage" class="col-md-2 control-label">Edition Language</label>
              <div class="col-md-6">
                <form:input path="book.language" cssClass="form-control" id="inputLanguage" />
                <form:errors path="book.language" cssClass="text-danger" />
              </div>
            </div>
          </spring:bind>

          <spring:bind path="book.author.name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
              <label for="inputAuthor" class="col-md-2 control-label">Author</label>
              <div class="col-md-6">
                <form:input path="book.author.name" cssClass="form-control"  id="inputAuthor" />
                <form:errors path="book.author.name" cssClass="text-danger" />
              </div>
            </div>
          </spring:bind>

          <spring:bind path="genreFormBinding.name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
              <label for="genreInput" class="col-md-2 control-label">Genre</label>
              <div class="col-md-6">
                <form:select path="genreFormBinding.name" cssClass="form-control" id="genreInput">
                  <option value=""></option>
                  <c:forEach items="${genres}" var="genreOption">
                    <option value="${genreOption.id}" ${genreFormBinding.name == genreOption.id ? 'selected="selected"':''}>${genreOption.name}</option>
                  </c:forEach>
                </form:select>
                <form:errors path="genreFormBinding.name" cssClass="text-danger" />
              </div>
            </div>
          </spring:bind>

          <spring:bind path="book.description">
            <div class="form-group ${status.error ? 'has-error' : ''}">
              <label for="inputDescription" class="col-md-2 control-label">Description</label>
              <div class="col-md-6">
                <form:textarea path="book.description" cssClass="form-control" rows="3" id="inputDescription" />
                <form:errors path="book.description" cssClass="text-danger" />
              </div>
            </div>
          </spring:bind>

          <div class="form-group">
            <label class="col-md-2 control-label">Image</label>
            <div class="col-md-6">
              <input type="file" class="form-control" name="imageFileForUpdating" title="Search for an image to add">
            </div>
          </div>

          <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-md-2 control-label">File</label>
            <div class="col-md-6">
              <input type="file" class="form-control" name="bookFileForUpdating" title="Search for a book to add">
              <form:errors path="book.bookFilePath" cssClass="text-danger" />
            </div>
          </div>

          <div class="form-group">
            <div class="col-md-offset-2 col-md-8">
              <button type="submit" class="btn btn-primary">Update</button>
            </div>
          </div>

        </fieldset>
      </form>

    </div>
    <div id="sidebar" class="col-md-3">
      <jsp:include page="../views/fragments/genres_navigate.jsp" ></jsp:include>
    </div>

    <div id="footer">
      <div class="footer-bottom row">
        <div class="copyrights col-sm-6 col-md-6"><div class="well">6<br>.copyrights</div></div>
        <div class="social-icons col-sm-6 col-md-6"><div class="well">6<br>.social-icons</div></div>
      </div>
    </div>

  </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
  $(function() {
    $( "#publishingDate" ).datepicker({
      dateFormat: 'yy-mm-dd',
      changeMonth: true,
      changeYear: true
    });
  });
</script>
</body>
</html>