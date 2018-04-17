<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0);
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link href="/resources/css/toastr.css" rel="stylesheet"/>
    <!-- Our Custom CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/customer.css" />">
    <title>Espace de </title>

</head>

<style>
    body {
        font-family: 'Poppins', sans-serif;
        background: #fafafa;
    }

    p {
        font-family: 'Poppins', sans-serif;
        font-size: 1.1em;
        font-weight: 300;
        line-height: 1.7em;
        color: #999;
    }

    a, a:hover, a:focus {
        color: inherit;
        text-decoration: none;
        transition: all 0.3s;
    }

    #sidebar {
        /* don't forget to add all the previously mentioned styles here too */
        background: #007bff;
        color: #fff;
        transition: all 0.3s;
    }

    #sidebar .sidebar-header {
        padding: 20px;
        background: #007bff;
    }

    #sidebar ul.components {
        padding: 20px 0;
        border-bottom: 1px solid #415ccc;
    }

    #sidebar ul p {
        color: #fff;
        padding: 10px;
    }

    #sidebar ul li a {
        padding: 10px;
        font-size: 1.1em;
        display: block;
    }
    #sidebar ul li a:hover {
        color: #7386D5;
        background: #fff;
    }

    #sidebar ul li.active > a, a[aria-expanded="true"] {
        color: #fff;
        background: #415ccc;
    }
    ul ul a {
        font-size: 0.9em !important;
        padding-left: 30px !important;
        background: #415ccc;
    }
    .wrapper {
        display: flex;
        align-items: stretch;
    }
    #sidebar {
        min-width: 250px;
        max-width: 250px;
        min-height: 100vh;
    }

    #sidebar.active {
        margin-left: -250px;
    }
    @media (max-width: 768px) {
        #sidebar {
            margin-left: -250px;
        }
        #sidebar.active {
            margin-left: 0;
        }
    }
</style>
<body>
<div class="wrapper">
    <nav id="sidebar">
        <!-- Sidebar Header -->
        <div class="sidebar-header">
            <picture>
                <source srcset="/resources/images/profile_female.png" type="image/svg+xml">
                <img src="/resources/images/profile_female.png" class="img-fluid img-thumbnail" alt="profile_pic">
            </picture>
        </div>

        <!-- Sidebar Links -->
        <ul class="list-unstyled components">
            <li class="active"><a href="${pageContext.request.contextPath}/Admin/adminSpace">Acceuil</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/changeProfilePicture">Changer Photo de Profile</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/addAgent">Ajouter un Agent</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/addCustomer">Ajouter un Client</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/addManager">Ajouter un Responsable</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/addOperator">Ajouter un Opérateur</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/registerAdmin">Ajouter un Admin</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/editAdmins">Modifier un Admin</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/editAgents">Modifier un Agent</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/editCustomers">Modifier un Client</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/editManagers">Modifier un Responsable</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/editOperators">Modifier un Opérateur</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/removeAdmin">Supprimer un Admin</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/removeAgent">Supprimer un Agent</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/removeCustomer">Supprimer un Client</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/removeManager">Supprimer un Responsable</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/removeOperator">Supprimer un Opérateur</a></li>
            <li><a href="${pageContext.request.contextPath}/Admin/validateAccount">Valider un compte Client</a></li>
            <li><form action="/logout" method="post">
                <button type="submit" class="btn btn-primary mb-2">Se Déconnecter</button>
            </form></li>
        </ul>
    </nav>

    <div id="content">
        <h1 class="display-1"> Welcome</h1>
    </div>

</div>

<!-- jQuery LDN -->
<script src="/resources/js/jquery.js"></script>
<!-- Bootstrap Js LDN -->
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
