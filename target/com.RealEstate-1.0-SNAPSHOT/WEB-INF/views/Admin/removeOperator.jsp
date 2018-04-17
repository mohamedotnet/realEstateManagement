<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
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
    <title>Inscrivez vous</title>
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
                    <button type="submit" class="btn btn-primary mb-2">Déconnecter</button>
                </form></li>
            </ul>
        </nav>

        <div class="container">
            <div class="py-5 text-center">
                <img class="d-block mx-auto mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
                <h2>Ajouter un Client</h2>
            </div>

            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Prénom</th>
                    <th scope="col">Nom d'utilisateur</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${array}" var="operator">
                    <tr>
                        <th scope="row">${operator.id}</th>
                        <td>${operator.name}</td>
                        <td>${operator.lastName}</td>
                        <td>${operator.username}</td>
                        <td><form:form modelAttribute="operator" action="/removeOperatorSuccess" method="post">
                            <form:hidden path="id" value="${operator.id}"/>
                            <form:hidden path="name" value="${operator.name}"/>
                            <form:hidden path="lastName" value="${operator.lastName}"/>
                            <form:hidden path="email" value="${operator.email}"/>
                            <form:hidden path="username" value="${operator.username}"/>
                            <form:hidden path="sex" value="${operator.sex}"/>
                            <form:hidden path="phone" value="${operator.phone}"/>
                            <form:hidden path="password" value="${operator.password}"/>
                            <form:hidden path="address" value="${operator.address}"/>
                            <form:hidden path="birthday" value="${operator.birthday}"/>
                            <form:hidden path="idNumber" value="${operator.idNumber}"/>
                            <button type="submit" class="btn btn-primary mb-2">Supprimer</button>
                        </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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
    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function() {
            'use strict';

            window.addEventListener('load', function() {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');

                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>

</body>

</html>