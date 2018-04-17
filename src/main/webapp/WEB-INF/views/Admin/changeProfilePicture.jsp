<%--
  Created by IntelliJ IDEA.
  User: B.IHAB
  Date: 3/27/2018
  Time: 1:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0);
%>
<head>
    <title>change profile picture</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link href="/resources/css/toastr.css" rel="stylesheet"/>
    <!-- Our Custom CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/customer.css" />">
</head>
<body>
<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<div>
    <form:form action="/changePictureSuccessAd" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <br>
        <button type="submit">Save</button>
        <p>(Max size 10 mb)</p>
    </form:form>
</div>
</body>
</html>

