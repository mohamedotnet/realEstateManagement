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
                <li><a href="${pageContext.request.contextPath}/Operator/addLocality">Ajouter Une Localit�</a></li>
                <li><a href="${pageContext.request.contextPath}/Operator/addCustomer">Ajouter Un Client</a></li>
                <li><a href="${pageContext.request.contextPath}/Operator/customerList?customer=default">Fixer Un Rendez-Vous</a></li>
                <li><a href="${pageContext.request.contextPath}/Operator/cancelAppointment?customer=default">Annuler Un Rendez-Vous</a></li>
                <li><a href="${pageContext.request.contextPath}/Operator/changeProfilePicture">Changer Photo De Profile</a></li>
            </ul>
            <form action="/logout" method="post">
                <button type="submit" class="btn btn-light ml-4" style="width:200px;">Se D�connecter</button>
            </form>
        </nav>

        <div class="container">
            <div class="py-5 text-center">
                <img class="d-block mx-auto mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
                <h2>Ajouter Un Appartement</h2>
            </div>

            <div class="row justify-content-center">
                <div class="col-md-6 order-md-1">
                    <form class="needs-validation" method="post" action="/addApartmentSuccess" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="firstName">Reference</label>
                                <input name="reference" type="text" class="form-control" id="firstName" placeholder="R�ference"  required="required"/>
                                <div class="invalid-feedback">
                                    Veuillez entrer une r�ference valide.
                                </div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="nbetage">Nombre d'�tage</label>
                            <input name="floor" type="text" class="form-control" id="nbetage" placeholder="Nombre d'�tage" required="required"/>
                            <div class="invalid-feedback" style="width: 100%;">
                                Veuillez entre un num�ro d'�tage valide.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="nbroom">Nombre de pi�ces</label>
                            <input name="nbrRoom" type="text" class="form-control" id="nbroom" placeholder="Nombre de pi�ces" required="required"/>
                            <div class="invalid-feedback" style="width: 100%;">
                                Veuillez entre un nombre de pi�ces valide.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="nbrbalcon">Nombre de balcons</label>
                            <input name="nbrBalcony" type="number" class="form-control" id="nbrbalcon" placeholder="Nombre de Balcon" required="required"/>
                            <div class="invalid-feedback" style="width: 100%;">
                                Veuillez entre un nombre de pi�ces valide.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="prix">Prix</label>
                            <input name="price" type="number" class="form-control" id="prix" placeholder="Prix" required="required"/>
                            <div class="invalid-feedback" style="width: 100%;">
                                Veuillez entre un prix valide.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="type">Type</label>
                            <input name="type" type="text" class="form-control" id="type" placeholder="Type" required="required"/>
                            <div class="invalid-feedback" style="width: 100%;">
                                Veuillez entre un type valide.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="surface">Surface</label>
                            <input name="surface" type="number" class="form-control" id="surface" placeholder="Surface" required="required"/>
                            <div class="invalid-feedback" style="width: 100%;">
                                Veuillez entre une surface valide.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="surface">Batiment</label>
                            <select name="building" class="custom-select custom-select-lg mb-3">
                                <c:forEach items="${array}" var="building">
                                    <option value= "${building.id}">${building.id}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <div class="custom-file">
                                <input name="picture" type="file" class="custom-file-input" id="validatedCustomFile" multiple="multiple"/>
                                <label class="custom-file-label" for="validatedCustomFile">Ajouter 5 Photos...</label>
                                <div class="invalid-feedback">Example invalid custom file feedback</div>
                            </div>
                        </div>
                        <hr class="mb-4">
                        <button class="btn btn-primary btn-lg btn-block"  type="submit">Ajouter Appartment</button>
                    </form>
                </div>
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
