<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
  response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
  response.setHeader("Pragma","no-cache"); //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<html lang="en">

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link href="/resources/css/toastr.css" rel="stylesheet"/>
    <title>Inscrivez vous</title>
  </head>

  <style>
    body {
      display: -ms-flexbox;
      display: -webkit-box;
      display: flex;
      -ms-flex-align: center;
      -ms-flex-pack: center;
      -webkit-box-align: center;
      align-items: center;
      -webkit-box-pack: center;
      justify-content: center;
      padding-top: 40px;
      padding-bottom: 40px;
      background-color: #f5f5f5;
    }
  </style>

  <body class="bg-light">

      <div class="container">
        <div class="py-5 text-center">
          <img class="d-block mx-auto mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
          <h2>Formulaire d'inscription</h2>
          <p class="lead">Ci-dessous le formulaire d'inscription afin d'avoir un compte et de bénéficier de nos services.</p>
        </div>

        <div class="row justify-content-center">
          <div class="col-md-6 order-md-1">
            <form:form modelAttribute="customer" class="needs-validation" method="post" action="/registerSuccess">
              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="firstName">Nom:</label>
                  <form:input path="name" type="text" class="form-control" id="firstName" placeholder="" value="" required="required"/>
                  <div class="invalid-feedback">
                    Veuillez entrer un nom valable.
                  </div>
                </div>
                <div class="col-md-6 mb-3">
                  <label for="lastName">Prénom</label>
                  <form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="" value="" required="required"/>
                  <div class="invalid-feedback">
                    Veuillez entrer un prénom valable.
                  </div>
                </div>
              </div>

              <div class="mb-3">
                <label for="username">Nom d'utilisateur</label>
                <div class="input-group">
                  <div class="input-group-prepend">
                    <span class="input-group-text">@</span>
                  </div>
                  <form:input path="username" type="text" class="form-control" id="username" placeholder="Nom d'utilisateur" required="required"/>
                  <div class="invalid-feedback" style="width: 100%;">
                    Votre nom d'utilisateur est obligatoire.
                  </div>
                </div>
              </div>

              <div class="mb-3">
                <label for="email">Email</label>
                <form:input path="email" type="email" class="form-control" id="email" placeholder="you@example.com" required="required"/>
                <div class="invalid-feedback">
                  Veuillez entrer une adresse mail valide.
                </div>
              </div>

              <div class="mb-3">
                <label for="phone">N° de Téléphone</label>
                <form:input path="phone" type="tel" class="form-control" id="phone" placeholder="0654785236" required="required"/>
                <div class="invalid-feedback">
                  Veuillez entrer un numero de téléphone valide.
                </div>
              </div>

              <div class="mb-3">
                <label for="address">Adresse</label>
                <form:input path ="address" type="text" class="form-control" id="address" placeholder="1234 Main St" required="required"/>
                <div class="invalid-feedback">
                  Veuillez entrer votre adresse.
                </div>
              </div>

              <div class="mb-3">
                <label for="bday">Date de naissance</label>
                <form:input path="birthday" type="date" class="form-control" id="bday" required="required"/>
              </div>

              <div class="d-block my-3">
                <div class="custom-control custom-radio">
                  <form:radiobutton path="sex" id="male" value="male" class="custom-control-input" required="required"/>
                  <label class="custom-control-label" for="male">Masculin</label>
                </div>
                <div class="custom-control custom-radio">
                  <form:radiobutton path="sex" id="female" value="female" class="custom-control-input" required="required"/>
                  <label class="custom-control-label" for="male">Féminin</label>
                </div>
              </div>

              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="password">Mot de passe</label>
                  <form:input path="password" type="password" class="form-control" id="password" required="required" />
                  <div class="invalid-feedback">
                    Veuillez entrer votre mot de passe.
                  </div>
                </div>
                <div class="col-md-6 mb-3">
                  <label for="idCards">N° de carte</label>
                  <form:input path="idNumber" type="number" class="form-control" id="idCards" required="required"/>
                  <div class="invalid-feedback">
                    Veuillez entrer un numéro de carte valide.
                  </div>
                </div>
              </div>

              <hr class="mb-4">
              <button class="btn btn-primary btn-lg btn-block"  type="submit">S'inscrire</button>
            </form:form>
          </div>
        </div>
        <footer class="my-5 pt-5 text-muted text-center text-small">
          <p class="mb-1">&copy; 2018-2019 Société Immobilière</p>
        </footer>
      </div>


      <!-- jQuery LDN -->
      <script src="/resources/js/jquery.js"></script>
      <!-- toastr Js -->
      <script src="/resources/js/toastr.js"></script>
      <!-- Bootstrap Js LDN -->
      <script src="/resources/js/bootstrap.min.js"></script>

      <script>
        window.addEventListener('load', function errMessage() {
            var user = "${user}";
            var email = "${email}";
            var id = "${id}";
            if (user) {
                toastr.error(user);
            }
            if (email){
                toastr.error(email);
            }
            if (id){
                toastr.error(id);
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