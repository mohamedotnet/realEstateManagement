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
    <title>Espace de ${agent.username}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link href="/resources/css/toastr.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/customer.css" />">
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
                <li class="active"><a href="${pageContext.request.contextPath}/Agent/agentSpace">Acceuil</a></li>
                <li><a href="${pageContext.request.contextPath}/Agent/changeProfilePicture">Changer Photo de Profile</a></li>
                <li><a href="${pageContext.request.contextPath}/Agent/appointments">Liste des Rendez-Vous</a></li>
                <li><a href="${pageContext.request.contextPath}/Agent/programVisits">Programme de Visite</a></li>
                <li><a href="${pageContext.request.contextPath}/Agent/addReport">Ajouter un Rapport</a></li>
                <li><a href="${pageContext.request.contextPath}/Agent/editReport">Modifier un Rapport</a></li>
                <li><a href="${pageContext.request.contextPath}/Agent/removeReport">Supprimer un Rapport</a></li>
            </ul>
            <form action="/logout" method="post">
                <button type="submit" class="btn btn-light ml-4" style="width:200px;">Se Déconnecter</button>
            </form>
        </nav>

        <div id="content">
            <h1 class="display-1"> Welcome ${agent.name}</h1>
        </div>

    </div>

    <script src="/resources/js/jquery.js"></script>
    <script src="/resources/js/popper-min.js" ></script>
    <!-- toastr Js -->
    <script src="/resources/js/toastr.js"></script>
    <!-- Bootstrap Js LDN -->
    <script src="/resources/js/bootstrap.min.js"></script>

    <script>
        window.addEventListener('load', function errMessage() {
            var err = '${err}';
            if (err) {
                toastr.error(err);
            }
        });
    </script>
</body>
</html>
