<%-- 
    Document   : poruciPodaci
    Created on : Sep 21, 2022, 3:44:47 PM
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
      

     <%
           Meni meni = (Meni) request.getAttribute("meni");
           %>

    
        
<div class="container px-5">
    <% if(request.getAttribute("message") != null){ %>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-6">
            <font color="red" size="6pt"><%=request.getAttribute("message")%></font>
        </div>
        <div class="col-md-2"></div>
    </div>
        <% } %>
        
            <form id="registracija" method="Post" action="Porucivanje">
            <div class="text-center">
                <h3 class="page-section-heading text-center text-uppercase text-secondary mb-0">Unesite podatke</h3>
            </div>
            <% if (session.getAttribute("user") == null){ %>
            <div class="form-group row py-1">
                <div class="col-sm-2"></div>
                <label for="colFormLabel" class="col-sm-2 col-form-label">Ime i prezime</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="colFormLabel" name="imePrezime" >
                </div>
                <div class="col-sm-2"></div>
            </div>
            
             <div class="form-group row py-1">
                <div class="col-sm-2"></div>
                <label for="colFormLabel" class="col-sm-2 col-form-label">Kontakt telefon</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="colFormLabel" name="brTel" >
                </div>
                <div class="col-sm-2"></div>
            </div>
            
            <% } %>
            
           
             <div class="form-group row py-1">
                <div class="col-sm-2"></div>
                <label for="colFormLabel" class="col-sm-2 col-form-label">Unesite broj ljudi za koje zelite uslugu: </label>
                <div class="col-sm-6">
                    <input type="number" value="1" class="form-control" id="colFormLabel" name="ljudi" >
                </div>
                <div class="col-sm-2"></div>
            </div>
            
             <div class="form-group row py-1">
                <div class="col-sm-2"></div>
                <label for="example-datatime-local-input" class="col-sm-2 col-form-label">Datum izvrsenja usluge</label>
                <div class="col-sm-3">
                    <input type="date" class="form-control" id="example-datetime-local-input" name="datum" value="10/9/2022" >
                </div>
                <div class="col-sm-1">
                <label class="col-sm-2 col-form-label" for="example-time-input">Vreme</label>
                </div>
                <div class="col-sm-2">
                    <input class="form-control" type="time" value="00:00:00" id="example-time-input" name="vreme">
                </div>
            </div>
            
             <div class="form-group row py-1">
                <div class="col-sm-2"></div>
                
                <div class="col-sm-6">
                    <input type="hidden" value="<%=meni.getMeniID()%>" name="meniID">
                    <input type="hidden" value="<%=meni.getKetSluzbaID()%>" name="sluzbaID">
        					
        <input type="submit" class="btn btn-success" value="Zakazi uslugu" style="margin-left: 55%; margin-top: 15px;">
                </div>
                <div class="col-sm-2"></div>
            </div>
            
            
            <hr class="my-4" />
            
             <h3 class="page-section-heading text-center text-uppercase text-secondary mb-0">Meni koji ste izabrali</h3>
      
      <div class="divider-custom">
          <div class="divider-custom-line"></div>
          <div class="divider-custom-line"><i class="fas fa-user"></i></div>
          <div class="divider-custom-line"></div>
      </div>
          <div class="row gx-5">
              <div class="col-lg-4 mb-5"></div>
           
         
            <div class="col-lg-4 mb-5 ">
    <div class="card h-100 shadow border-1">
        
      
       
        
      
      <div class="card-body p-4">
        <h4 class="card-title font-weight-bold text-center"><a><%= meni.getNazivMenija()%></a></h4>
           <ul class="list-unstyled list-inline mb-0">
         
         
        </ul>
        <p class="mb-2">Cena menija po osobi • <%= meni.getCenaPoOsobi() %> RSD</p>
        
        <hr class="my-4" />
      </div>
        
        <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
            <p class="card-text">
         Ponuda:
        </p>
<ul class="list-group list-group-flush">
    <li class="list-group-item">PREDJELO: <%= meni.getPredjelo() %></li>
    <li class="list-group-item">GLAVNO JELO: <%= meni.getGlavnoJelo() %></li>
    <li class="list-group-item">DEZERT: <%= meni.getDezert() %></li>
    
  </ul>
       
	
       				
      </div>
     </div> 
    </div>
    
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