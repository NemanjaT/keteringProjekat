<%-- 
    Document   : izmeniMeni
    Created on : Sep 5, 2022, 8:07:43 PM
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
        
        <% Meni meni = (Meni) request.getAttribute("meni"); %>
        
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
<form id="registracija" method="post" action="IzmeniMeni">
    <h3 class="page-header page-section-heading text-center text-uppercase text-secondary mb-0">Izmeni meni</h3>
        <div class="form-group row py-1">
                <div class="col-sm-2"></div>
                <label for="colFormLabel" class="col-sm-2 col-form-label">ID Menija</label>
                <div class="col-sm-6">
                        <input type="text" class="form-control" id="colFormLabel"
                                name="meniID"
                                value="<%= meni.getMeniID()%>" readonly>
                                
                </div>
                <div class="col-sm-2"></div>
        </div>
        <div class="form-group row py-1">
                <div class="col-sm-2"></div>
                <label for="colFormLabel" class="col-sm-2 col-form-label">Naziv Menija</label>
                <div class="col-sm-6">
                        <input type="text" class="form-control" id="colFormLabel"
                                name="nazivMenija" value="<%= meni.getNazivMenija()%>">
                </div>
                <div class="col-sm-2"></div>
        </div>
        <div class="form-group row py-1">
                <div class="col-sm-2"></div>
                <label for="colFormLabel" class="col-sm-2 col-form-label">Predjelo</label>
                <div class="col-sm-6">
                        <input type="text" class="form-control" id="colFormLabel"
                                name="predjelo" value="<%= meni.getPredjelo()%>">
                </div>
                <div class="col-sm-2"></div>
        </div>
        <div class="form-group row py-1">
                <div class="col-sm-2"></div>
                <label for="colFormLabel" class="col-sm-2 col-form-label">Glavno jelo</label>
                <div class="col-sm-6">
                        <input type="text" class="form-control" id="colFormLabel"
                                name="glavnoJelo" value="<%= meni.getGlavnoJelo()%>">
                </div>
                <div class="col-sm-2"></div>
        </div>
        <div class="form-group row py-1">
                <div class="col-sm-2"></div>
                <label for="colFormLabel" class="col-sm-2 col-form-label">Dezert</label>
                <div class="col-sm-6">
                        <input type="text" class="form-control" id="colFormLabel"
                                name="dezert" value="<%= meni.getDezert()%>">
                </div>
                <div class="col-sm-2"></div>
        </div>
                                
          <div class="form-group row py-1">
                <div class="col-sm-2"></div>
                <label for="colFormLabel" class="col-sm-2 col-form-label">Cena po osobi</label>
                <div class="col-sm-6">
                        <input type="text" class="form-control" id="colFormLabel"
                                name="cenaPoOsobi" value="<%= meni.getCenaPoOsobi()%>">
                </div>
                <div class="col-sm-2"></div>
        </div>
                                
                                  <div class="form-group row py-1">
                <div class="col-sm-2"></div>
                <label for="colFormLabel" class="col-sm-2 col-form-label">Ketering sluzba ID</label>
                <div class="col-sm-6">
                        <input type="text" class="form-control" id="colFormLabel"
                               name="ketSluzbaID" value="<%=meni.getKetSluzbaID()%>" readonly>
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

        

        <div class="form-group row">
           <div class="col-sm-2"> </div>
                <div class="col-sm-8">
                        <input type="submit" class="form-control btn btn-success" id="colFormLabel" value="UNESI">
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
