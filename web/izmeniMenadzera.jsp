<%-- 
    Document   : izmeniMenadzera
    Created on : Sep 5, 2022, 12:29:27 PM
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
			<%Menadzer menadzer = (Menadzer) request.getAttribute("menadzer"); %>
		<form id="registracija" method="post" action="IzmeniMenadzera">
                    <h3 class="page-header page-section-heading text-center text-uppercase text-secondary mb-0">Izmeni menadzera</h3>
			<div class="form-group row py-1">
				<div class="col-sm-2"></div>
				<label for="colFormLabel" class="col-sm-2 col-form-label">Username</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="colFormLabel"
						name="korisnickoIme" value="<%=menadzer.getUsername()%>" disabled>
				</div>
				<div class="col-sm-2"></div>
			</div>
			<div class="form-group row py-1">
				<div class="col-sm-2"></div>
				<label for="colFormLabel" class="col-sm-2 col-form-label">Ime
					i Prezime</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="colFormLabel"
						name="imePrezime"  value="<%=menadzer.getImePrezime()%>">
				</div>
				<div class="col-sm-2"></div>
			</div>
			<div class="form-group row py-1">
				<div class="col-sm-2"></div>
				<label for="colFormLabel" class="col-sm-2 col-form-label">Kontakt
					telefon</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="colFormLabel"
						name="brTelefona"  value="<%=menadzer.getBrTelefona()%>">
				</div>
				<div class="col-sm-2"></div>
			</div>
			<%List<Keteringsluzba> sluzbe = (List<Keteringsluzba>) request.getAttribute("sluzbe"); %>
			<div class="form-group row py-1">
				<div class="col-sm-2"></div>
				<label for="colFormLabel" class="col-sm-2 col-form-label">Agencija</label>
				<div class="col-sm-6">
					<select class="form-control" id="colFormLabel" name="ketSluzbaID">
						<option selected value="none">Odaberite agenciju</option>
						<%if(sluzbe != null){ %>
						<%for(Keteringsluzba sluzba : sluzbe){ %>
						<option value="<%=sluzba.getKetSluzbaID()%>"><%=sluzba.getKetSluzbaID()%> - <%=sluzba.getNaziv()%></option>
						<%} %>
						<%} %>
					</select>
				</div>
				<div class="col-sm-2"></div>
			</div>
			<%
			if (request.getAttribute("message") != null) {
			%>
			<div class="row">
				<div class="col-sm-2"></div>
				<div class="col-sm-8" align="center">
					<p style="color: red"><%=request.getAttribute("message")%></p>
				</div>
				<div class="col-sm-2"></div>
			</div>
			<%
			}
			%>
			<div class="form-group row py-1">
				<div class="col-sm-2"></div>
				<div class="col-sm-8">
					<input type="submit" class="form-control btn btn-primary"
						id="colFormLabel" value="Izmeni">
				</div>
				<div class="col-sm-2"></div>
			</div>
		</form>
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
