<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: B.IHAB
  Date: 3/22/2018
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Apartments List</title>
    <spring:url value="/resources/css/materialize.min.css" var="materializeCss"/>
    <spring:url value="/resources/css/nouislider.min.css" var="nouisliderCss"/>
    <spring:url value="/resources/js/materialize.min.js" var="materializeJs"/>
    <spring:url value="/resources/js/nouislider.min.js" var="nouisliderJs"/>
    <spring:url value="/resources/js/jquery-3.3.1.min.js" var="jqueryJs"/>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link href="${materializeCss}" media="screen,projection" rel="stylesheet" type="text/css"/>
    <link href="${nouisliderCss}" media="screen,projection" rel="stylesheet" type="text/css"/>

    <style type="text/css">
        .container {
            display: inline-block;
            margin-left: 300px;
        }
        .filter {
            position: fixed;
            width: 250px;
            padding: 0px 20px;
            display: inline-block;
            z-index: 900;
            overflow-y: scroll;
            height: 100%;
        }
    </style>

</head>
<body>
<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${nouisliderJs}"></script>
<script type="text/javascript" src="${materializeJs}"></script>

<div class="filter">
    <form:form method="post" action="/search" >
        <input type="text" autocomplete="off" name="reference" id="autocomplete-input" class="autocomplete" placeholder="search">
        <button type="submit">Go</button>
    </form:form>

    <form:form method="post" action="/filter">
        <label>Floor</label>
        <input type="number" name="floor">
        <br>
        <label>Number of balcony</label>
        <input type="number" name="nbrBalcony">
        <br>
        <div class="input-field col s12">
            <select name="type">
                <option value="all" selected>all</option>
                <option value="F2">F2</option>
                <option value="F3">F3</option>
                <option value="F4">F4</option>
                <option value="F5">F5</option>
                <option value="F6">F6</option>
            </select>
            <label>Apartment Type</label>
        </div>
        <br>
        <div class="input-field col s12">
            <select name="locality">
                <option value="all" selected>all</option>
                <c:forEach var="i" begin="0" end="${size1 - 1}" step="1">
                    <option value="${list1[i].name}">${list1[i].name}</option>
                </c:forEach>
            </select>
            <label>Locality</label>
        </div>
        <br>
        <label for="minPrice">Min Price :</label>
        <input type="text" id="minPrice" readonly style="border:0; color:#4db6ac ; font-weight:bold;" name="minPrice">
        <label for="maxPrice">Max Price :</label>
        <input type="text" id="maxPrice" readonly style="border:0; color:#4db6ac ; font-weight:bold;" name="maxPrice">
        <br>
        <div id="price-slider"></div>
        <br>
        <label for="minSurface">Min Surface :</label>
        <input type="text" id="minSurface" readonly style="border:0; color:#4db6ac ; font-weight:bold;" name="minSurface">
        <label for="maxSurface">Max Surface :</label>
        <input type="text" id="maxSurface" readonly style="border:0; color:#4db6ac ; font-weight:bold;" name="maxSurface">
        <br>
        <div id="surface-slider"></div>
        <br>
        <button type="submit">
            Filter
        </button>
        <br>
    </form:form>
</div>

<div class="container">
    <c:if test="${size > 0}">
    <c:forEach var="i" begin="0" end="${size - 1}" step="1">
    <c:if test="${i % 3 == 0}">
    <div class="row">
        </c:if>
        <div class="col s12 m4">
            <div class="card sticky-action">
                <div class="card-image waves-effect waves-block waves-light">
                    <img class="activator" src="${list[i].picture1}">
                </div>
                <div class="card-content">
                            <span class="card-title activator grey-text text-darken-4">Apartment ${list[i].reference}
                                <i class="material-icons right">more_vert</i>
                            </span>
                </div>
                <div class="card-reveal">
                            <span class="card-title grey-text text-darken-4">Information
                                <i class="material-icons right">close</i>
                            </span>
                    <p>Id : ${list[i].id}</p>
                    <p>Building :${list[i].building}</p>
                    <p>Floor : ${list[i].floor}</p>
                    <p>Price : ${list[i].price}</p>
                    <p>Surface : ${list[i].surface}</p>
                    <p>Number of rooms : ${list[i].nbrRoom}</p>
                </div>
                <div class="card-action">
                    <a href="#">RENT</a>
                    <a href="Customer/sellApartment">BUY</a>
                </div>
            </div>
        </div>
        <c:if test="${i % 3 == 0}">
            <div/>
        </c:if>
        </c:forEach>
        </c:if>
    </div>



    <script type="text/javascript">
        $(document).ready(function () {
            $('select').material_select();
        });
        var surface = document.getElementById('surface-slider');
        var price = document.getElementById('price-slider');
        var prices = [$("#minPrice"), $("#maxPrice")];
        var surfaces = [$("#minSurface"), $("#maxSurface")];
        noUiSlider.create(surface, {
            start: [0, 300],
            connect: true,
            step: 5,
            orientation: 'horizontal',
            range: {
                'min': 0,
                'max': 300
            }
        });
        noUiSlider.create(price, {
            start: [0, 15000000],
            connect: true,
            step: 100000,
            orientation: 'horizontal',
            range: {
                'min': 0,
                'max': 15000000
            }
        });
        price.noUiSlider.on('update', function (values, handle) {
            prices[handle].val("DZD " + values[handle]);
        });
        surface.noUiSlider.on('update', function (values, handle) {
            surfaces[handle].val(values[handle] + " M²");
        });
        $(document).ready(function(){
            $('input.autocomplete').autocomplete({
                data: {
                    <c:forEach var="i" begin="0" end="${size2 - 1}" step="1">
                    "${all[i].reference}" : null,
                    </c:forEach>
                    "${all[size2 - 1].reference}" : null
                },
            });
        });
    </script>
</body>
</html>