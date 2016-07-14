<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-default">
  <div class="container-fluid">

    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<spring:url value="/books/"/>">Bookstore</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

      <ul class="nav navbar-nav">
        <li><a href="<spring:url value="/books/admin"/>">Admin</a></li>
        <li><a href="<spring:url value="/books/admin/add"/>">Add</a></li>
      </ul>

      <form class="navbar-form navbar-left" role="search" action="<spring:url value="/books/search"/>" >
        <div class="form-group">
          <input type="text" name="searchInput" class="form-control" placeholder="Search">
        </div>
        <div class="form-group">
          <select name="searchBy" class="form-control" id="select">
            <option value="title">Title</option>
            <option value="author">Author</option>
          </select>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>

    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<!-- ======================================================== -->