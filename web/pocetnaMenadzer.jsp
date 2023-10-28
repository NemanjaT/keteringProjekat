<%-- 
    Document   : pocetnaMenadzer
    Created on : Sep 5, 2022, 4:50:16 PM
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
			<%
		Keteringsluzba sluzba = (Keteringsluzba) request.getAttribute("sluzba");
		List<Porudzbina> porudzbine = (List<Porudzbina>) request.getAttribute("porudzbine");
                List<Meni> meniji = (List<Meni>) request.getAttribute("meniji");
		%>
		<div class="row">
                    <div class="col-md-1"></div>
			<div class="col-md-10" id="menadzer">
                            <h2 class="text-center">Menadzer panel ketering sluzbe - <%=sluzba.getNaziv()%></h2>
                
                        </div>
                </div>
                <div class="row">
 
			<div class="col-md-1"></div>
			<div class="col-md-10" id="menadzer">
				<h3>Porudzbine</h3>
				
			</div>
		</div>
 
		<%
		if (porudzbine != null) {
		%>
		<div class="row">
 
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<table class="table">
					<thead class="">
						<tr>
							<th scope="col">ID Porudzbine</th>
							<th scope="col">Porucene Stavke</th>
							<th scope="col">Korisnik ID</th>
							<th scope="col">Sluzba ID</th>
							<th scope="col">Vreme isporuke </th>
							<th scope="col">Broj telefona </th>
							<th scope="col">Korisnik </th>
							<th scope="col">Akcija</th>
						</tr>
					</thead>
					<tbody>
 
						<%
						for (Porudzbina porudzbina : porudzbine) {
						%>
 
						<tr>
							<th scope="row"><%=porudzbina.getPorudzbinaID()%></th>
							<td><%=porudzbina.getPoruceneStavke()%></td>
							<td><%=porudzbina.getKorisnikID()%></td>
							<td><%=porudzbina.getKetSluzbaID()%></td>
							<td><%=porudzbina.getVremeIsporuke()%></td>
							<td><%=porudzbina.getBrTel()%></td>
                                                        <td><%=porudzbina.getKorisnik()%></td>
 
							
							
							
 
							
 
							<td>
								<div class="row">
								<div class="col-md-5">
									<form method="post" action="ObrisiPorudzbinu">
										<input type="hidden"
											value="<%=porudzbina.getPorudzbinaID()%>"
											name="porudzbinaID"> <input type="submit"
											class="btn btn-danger" value="Otkazi">
									</form>
									</div>
 
								</div>
							</td>
						</tr>
 
						<%
						}
						%>
					</tbody>
				</table>
			</div>
			<div class="col-md-1"></div>
		</div>
		<%
		} else {
		%>
 
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-6">
				<h4>Trenutno nemate porudzbina</h4>
			</div>
 
		</div>
 
		<%
		}
		%>
                
                
                
                
                <div class="row">
 
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<h3>Lista menija</h3>
                                <form method="get" action="DodajMeni">
                                    <input type="hidden" name="sluzbaID" value="<%=sluzba.getKetSluzbaID()%>">
                                    <input type="submit" value="Dodaj novi meni" class="btn btn-success">
                                </form>
			</div>
		</div>
 
                
                
                
                
                
		<%
		if (meniji != null) {
		%>
		<div class="row">
 
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<table class="table">
					<thead class="thead-light">
						<tr>
							<th scope="col">ID Menija</th>
							<th scope="col">Naziv menija</th>
							<th scope="col">Predjelo</th>
							<th scope="col">Glavno jelo</th>
							<th scope="col">Dezert </th>
							<th scope="col">Cena po osobi </th>
                                                        <th scope="col">Ketering sluzba ID </th>
							<th scope="col">Akcija</th>
						</tr>
					</thead>
					<tbody>
 
						<%
						for (Meni meni : meniji) {
						%>
 
						<tr>
							<th scope="row"><%=meni.getMeniID()%></th>
							<td><%=meni.getNazivMenija()%></td>
							<td><%=meni.getPredjelo()%></td>
							<td><%=meni.getGlavnoJelo()%></td>
							<td><%=meni.getDezert()%></td>
							<td><%=meni.getCenaPoOsobi()%></td>
                                                        <td><%=meni.getKetSluzbaID()%></td>
 
							
							
							
 
							
 
							<td>
								<div class="row">
								<div class="col-md-5">
									<form method="post" action="ObrisiMeni">
										<input type="hidden"
											value="<%=meni.getMeniID()%>"
											name="meniID"> <input type="submit"
											class="btn btn-danger" value="Ukloni">
									</form>
									</div>
                                                                                        
                                                                                        <div class="col-md-5">
									<form method="get" action="IzmeniMeni">
										<input type="hidden"
											value="<%=meni.getMeniID()%>"
											name="meniID"> <input type="submit"
											class="btn btn-primary" value="Izmeni">
									</form>
									</div>
 
								</div>
							</td>
						</tr>
 
						<%
						}
						%>
					</tbody>
				</table>
			</div>
			<div class="col-md-1"></div>
		</div>
		<%
		} else {
		%>
 
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-6">
				<h2>Trenutno nemate menija</h2>
			</div>
 
		</div>
 
		<%
		}
		%>
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

