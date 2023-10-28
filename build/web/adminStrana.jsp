<%-- 
    Document   : adminStrana
    Created on : Sep 4, 2022, 2:11:43 PM
    Author     : Nemanja
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="models.*"%>
<%@page import="database.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width , initial-scale=1, shrink-to-fit=no">
        <meta name="description" content=""/>
        <meta name="author" content="" />
        <title>Ketering Sluzbe</title>
        
<!--        BOOTSTRAP-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="assets/css/stil.css" rel="stylesheet"/>

    </head>
    <body class="d-flex flex-column h-100">
        <div class="container">
            
<!--header-->
<div class="page-header">
	<h4><a href="assets/img/logo.png"><img id="logo" src="assets/img/logo3.png" alt="Ketering Logo"></a><small id="tekst">  Online ketering usluga</small></h4>


</div>
        
<!--        navbar-->
<nav class="navbar navbar-expand-lg navbar-light">
  <div class="container px-5">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/Pocetna">Pocetna strana</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">

      
      
      
        <% if(session.getAttribute("user") == null){ %>
      <form class="d-flex">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link"  href="${pageContext.request.contextPath}/login.jsp">Login</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/registracija.jsp">Registracija</a>
        </li>
          </ul>
     
      
      </form>
         <% } %>
         
        <% if(session.getAttribute("user") != null){ %>
            
          <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" id="navbarDropdown"
                  href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><%=session.getAttribute("user").toString() %></a>
                                               
          <% if(session.getAttribute("rola").toString().matches("Korisnik")) {%>
          <%
              KorisnikDB korisnikDB = (KorisnikDB) session.getAttribute("korisnikDB");
          %>
          <ul class="dropdown-menu"
              aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/KorisnikProfil">Porudzbine</a></li>
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Logout">Odjavi se</a></li>
              <li><font class="dropdown-item disabled">Poeni: <input class="form-control" style="width: 60px; display:inline;"
                                                                     type="text" value="<%= korisnikDB.getBrojPoena(session.getAttribute("user").toString())%>" readonly="readonly"></font></li>
                                       
          </ul>
              
        
        <%} %>
        
        <% if(session.getAttribute("rola").toString().matches("Menadzer")) { %>
        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownPortfolio">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/MenadzerProfil">Opcije</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Logout">Odjavi se</a></li>
        </ul>
         <% } %>
         
          <% if(session.getAttribute("rola").toString().matches("Admin")) { %>
        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownPortfolio">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/AdminProfil">Opcije</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Logout">Odjavi se</a></li>
        </ul>
         <% } %>
<!--          </li>-->
          <% } %>
  </ul>
  </div>
</nav>
<!--navbarend-->
      

    
        
<div class="container px-5">
    <h2 id="menadzer">Ketering sluzbe</h2>
    <form method="get" action="DodajSluzbu">
        <input type="submit" value="Dodaj novu ketering sluzbu" class="btn btn-success">
    </form>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Naziv</th>
                <th scope="col">Adresa</th>
                <th scope="col">Broj telefona</th>
                <th scope="col">Slika</th>
                <th scope="col">Akcija</th>
            </tr>
        </thead>
        <%
            List<Keteringsluzba> sluzbe = (List<Keteringsluzba>)request.getAttribute("sluzbe");
        %>
        <tbody>
            <%
                if (sluzbe != null){
            %>
            <%
                for(Keteringsluzba sluzba : sluzbe) {
            %>
            <tr>
                <th scope="row"><%= sluzba.getKetSluzbaID() %></th>
                <td><%=sluzba.getNaziv()%></td>
                <td><%=sluzba.getAdresa()%></td>
                <td><%=sluzba.getTelefon()%></td>
                <td><%=sluzba.getSlikaUrl()%></td>
                <td>
                    <div class="row">
                    <div class="col-md-4">
                    <form method="post" action="ObrisiSluzbu" class="py-1">
                        <input type="hidden" value="<%= sluzba.getKetSluzbaID() %>" name="ketSluzbaID">
                        <input type="submit" value="Obrisi" class="btn btn-danger">
                    </form>
                        </div>
                        <div class="col-md-4">
                        <form method="get" action="IzmeniSluzbu">
                        <input type="hidden" value="<%= sluzba.getKetSluzbaID() %>" name="ketSluzbaID">
                        <input type="submit" value="Izmeni" class="btn btn-primary">
                    </form>
                        </div>
                        </div>
                </td>
            </tr>
            <% } %>
            <% } %>
        </tbody>
    </table>
        
        <div class="py-5"></div>
        
        <div class="container" id="korisnici">
            
            <h2 id="menadzeri">Menadzeri</h2>
            <form method="get" action="DodajMenadzera" class="py-1">
        <input type="submit" value="Dodaj novog menadzera" class="btn btn-success">
    </form>
            <table class="table table-bordered">
        <thead>
            <tr>
                <th scope="col">Username</th>
                <th scope="col">Ime i prezime</th>
                <th scope="col">Ketering sluzba</th>
                <th scope="col">Broj telefona</th>
                <th scope="col">Akcija</th>
            </tr>
        </thead>
        <%
            List<Menadzer> menadzeri = (List<Menadzer>)request.getAttribute("menadzeri");
        %>
        <tbody>
            <%
                if (menadzeri != null){
            %>
            <%
                for(Menadzer menadzer : menadzeri) {
            %>
            <tr>
                <th scope="row"><%= menadzer.getUsername()%></th>
                <td><%= menadzer.getImePrezime()%></td>
                <td><%= menadzer.getKetSluzbaID()%></td>
                <td><%= menadzer.getBrTelefona()%></td>
                
                <td>
                    <div class="row">
                        <div class="col-md-4">
                    <form method="post" action="ObrisiMenadzera" class="py-1">
                        
                        <input type="hidden" value="<%= menadzer.getUsername()%>" name="username">
                        <input type="submit" value="Obrisi" class="btn btn-danger">
                        
                    </form>
                        </div>
                        <div class="col-md-4">
                        <form method="get" action="IzmeniMenadzera">
                        <input type="hidden" value="<%= menadzer.getUsername()%>" name="username">
                        <input type="submit" value="Izmeni" class="btn btn-primary">
                        
                    </form>
                        </div>
                        </div>
                </td>
            </tr>
            <% } %>
            <% } %>
        </tbody>
    </table>
        
        <div class="py-5"></div>
        <h2>Korisnici</h2>
        <table class="table table-bordered">
        <thead>
            <tr>
                <th scope="col">Username</th>
                <th scope="col">Ime i prezime</th>
                <th scope="col">Poeni</th>
                <th scope="col">Broj telefona</th>
                <th scope="col">Adresa</th>
                
                <th scope="col">Akcija</th>
            </tr>
        </thead>
        <%
            List<Korisnik> korisnici = (List<Korisnik>)request.getAttribute("korisnici");
        %>
        <tbody>
            <%
                if (korisnici != null){
            %>
            <%
                for(Korisnik korisnik : korisnici) {
            %>
            <tr>
                <th scope="row"><%= korisnik.getUsername()%></th>
                <td><%= korisnik.getImePrezime()%></td>
                <td><%= korisnik.getPoeni()%></td>
                <td><%= korisnik.getBrTel()%></td>
                <td><%= korisnik.getAdresa()%></td>
                
                <td>
                    <div class="row">
                        <div class="col-md-4">
                    <form method="post" action="ObrisiKorisnika" class="py-1">
                        <input type="hidden" value="<%= korisnik.getUsername()%>" name="usernameKor">
                        <input type="submit" value="Obrisi" class="btn btn-danger">
                    </form>
                        </div>
                        <div class="col-md-4">
                        <form method="get" action="IzmeniKorisnika">
                        <input type="hidden" value="<%= korisnik.getUsername() %>" name="username">
                        <input type="submit" value="Izmeni" class="btn btn-primary">
                    </form>
                        </div>
                        </div>
                </td>
            </tr>
            <% } %>
            
            <% } %>
        </tbody>
    </table>
        
        
        
        </div>
</div>












          <div class="container px-5" id="foot">
<footer class="bg-white py-4 mt-auto">
    
        <div class="row align-items-center justify-content-between flex-column flex-sm-row">
            <div class="col-auto">
                <div class="small m-0">Copyright &copy; Nemanja Tutunovic 2022</div>
            </div>
        </div>
    
</footer>
        
    </div> 
        
    </div> 
<!--    endContainer-->
        
<!--        BOOTSTRAP JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>

    </body>
</html>