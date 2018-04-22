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
    <title>Espace de ${operator.username}</title>
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
                <li class="active"><a href="${pageContext.request.contextPath}/Operator/operatorSpace">Acceuil</a></li>
                <li><a href="${pageContext.request.contextPath}/Operator/addApartment">Ajouter Un Appartement</a></li>
                <li><a href="${pageContext.request.contextPath}/Operator/addBuilding">Ajouter Un Batiment</a></li>
                <li><a href="${pageContext.request.contextPath}/Operator/addLocality">Ajouter Une Localité</a></li>
                <li><a href="${pageContext.request.contextPath}/Operator/addCustomer">Ajouter Un Client</a></li>
                <li><a href="${pageContext.request.contextPath}/Operator/customerList?customer=default">Fixer Un Rendez-Vous</a></li>
                <li><a href="${pageContext.request.contextPath}/Operator/cancelAppointment?customer=default">Annuler Un Rendez-Vous</a></li>
                <li><a href="${pageContext.request.contextPath}/Operator/changeProfilePicture">Changer Photo De Profile</a></li>
            </ul>
            <form action="/logout" method="post">
                <button type="submit" class="btn btn-light ml-4" style="width:200px;">Se Déconnecter</button>
            </form>
        </nav>

        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-8 mt-4">
                    <div class="card">
                        <div class="card-body" style="padding: 0;">
                            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators">
                                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                                </ol>
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img class="d-block w-100" style="width: 850px; height: 450px;" src="/${appr.picture1}" alt="First slide">
                                    </div>
                                    <div class="carousel-item">
                                        <img class="d-block w-100" style="width: 850px; height: 450px;" src="/${appr.picture2}" alt="Second slide">
                                    </div>
                                    <div class="carousel-item">
                                        <img class="d-block w-100" style="width: 850px; height: 450px;" src="/${appr.picture3}" alt="Third slide">
                                    </div>
                                </div>
                                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card" style="height: 320px;">
                        <div class="card-header">
                            <h4 style="font-size: 1.32rem; margin-bottom: 0;" class="card-title">Rendez-Vous</h4>
                        </div>

                        <div class="card-body" style="padding: 0;">
                            <div class="card-block" style="padding: 2rem;">
                                <form class="form" method="post" action="/fixAppointmentSuccess">
                                    <div class="form-body">
                                        <div class="row">
                                            <div class="form-group">
                                                <label for="date">Date: </label>
                                                <input type="date" id="date" name="date" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label for="time">Heure: </label>
                                                <div class="position-relative has-icon-left">
                                                    <input type="time" id="time" name="time" class="form-control">
                                                </div>
                                            </div>
                                            <input type="hidden" name="app_ref" value="${appr.reference}" class="form-control">
                                            <input type="hidden" name="customer" value="${customer}" class="form-control">
                                        </div>
                                        <button type="submit" class="btn btn-dark" style="width: 180px;">Prendre</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <div class="jumbotron">
                <h1 class="display-4">Appartement: ${appr.reference}</h1>
                <p class="lead">Un appartement de <bold>${appr.nbrRoom}</bold> pièces et ${appr.nbrBalcony} balcons. Situé au ${appr.floor} dans le batiment N°: ${appr.building}. Surface de ${appr.surface} mètres carrés et son prix est de: ${appr.price} DA.</p>
                <hr class="my-4">
            </div>
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
