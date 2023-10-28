<%-- 
    Document   : potvrdiPorudzbinu
    Created on : Sep 6, 2022, 6:26:34 PM
    Author     : Nemanja
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="models.*"%>
<%@page import="database.*"%>

<%
   
    
     KorisnikDB korisnikDB = new KorisnikDB();
    Meni meni = (Meni) request.getAttribute("meni");
    
    
    int cena = meni.getCenaPoOsobi();
    int ljudi = (Integer) request.getAttribute("ljudi");
    
    int ukupnaCena = cena * ljudi;
    

%>


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
               korisnikDB = (KorisnikDB) session.getAttribute("korisnikDB");
          %>
          <ul class="dropdown-menu"
              aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/KorisnikProfil">Porudzbine</a></li>
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Logout">Odjavi se</a></li>
              <li><font class="dropdown-item disabled">Poeni: <input class="form-control" style="width: 60px; display:inline;"
                                                                     type="text" value="<%= korisnikDB.getBrojPoena(session.getAttribute("user").toString())%>" readonly="readonly" id="poeni"></font></li>
                                       
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
      

    


            <section class="py-5">
                <div class="container px-5 my-5">
                    <%
                        if (session.getAttribute("user") == null) {
                    %>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-10">
                            <p>
                                <b>Registracijom imate mogucnost da ostvarite popuste na osnovu broja zakazivanja</b>
                            </p>
                            <p>
                                <a href="Login">Registruj se</a>
                            </p>
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <% } %>
                    <div class="row">
                        <div class="col-md-1"></div>
<!--                        <div class="col-md-10">-->
                            <h4>Meni koji ste zakazali</h4>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Ime i prezime</th>
                                        <th scope="col">Kontakt telefon</th>
                                        <th scope="col">Naziv menija</th>
                                        <th scope="col">Predjelo</th>
                                        <th scope="col">Glavno jelo</th>
                                        <th scope="col">Dezert</th>
                                        <th scope="col">Cena po osobi</th>
                                        <th scope="col">Broj ljudi</th>
                                        <th scope="col">Racun</th>
                                    </tr>
                                </thead>
                                <%
                                    if (session.getAttribute("user") == null) {
                                %>
                                <tbody>
                                    <tr>
                                        <td><%=request.getParameter("imePrezime")%></td>
                                        <td><%=request.getParameter("brTel")%></td>
                                        <td><%= meni.getNazivMenija()%></td>
                                        <td><%= meni.getPredjelo()%></td>
                                        <td><%= meni.getGlavnoJelo()%></td>
                                        <td><%= meni.getDezert()%></td>
                                        <td><%= meni.getCenaPoOsobi()%></td>
                                        <td><%=request.getParameter("ljudi")%></td>
                                        <td id="ukupnaCena"><%= ukupnaCena%> RSD</td>
                                    </tr>
                                </tbody>
                                <% } else {  Korisnik korisnik = (Korisnik) request.getAttribute("korisnik");%>
                                <tbody>
                                    <tr>
                                        <td><%= korisnik.getImePrezime()%></td>
                                        <td><%= korisnik.getBrTel()%></td>
                                        <td><%= meni.getNazivMenija()%></td>
                                        <td><%= meni.getPredjelo()%></td>
                                        <td><%= meni.getGlavnoJelo()%></td>
                                        <td><%= meni.getDezert()%></td>
                                        <td><%= meni.getCenaPoOsobi()%></td>
                                        <td><%=request.getParameter("ljudi")%></td>
                                        <td id="ukupnaCena"><%= ukupnaCena%> RSD</td>
                                    </tr>
                                </tbody>
                                <% } %>
                            </table>
<!--                        </div>-->
                    </div>
                        <% if (session.getAttribute("user") != null && session.getAttribute("rola").toString().matches("Korisnik")) { %>
                        <div class="col-md-1"></div> 
                        <div class="col-md-4">
                            <h4 class="page-header">Popust</h4>
                            <p>Klikom mozete da iskoristite popust koji imate na osnovu vasih porudzbina,svaka porudzbina vredi 5 poena 
                                onoliko poena koliko iskoristite toliki popust ostvarujete(maksimalno 50)</p>
                            <div class="form-group row">
                                <div class="col-sm-6">
                                    <input type="number" value="0" min="0" max="50" id="popust" class="form-control" placeholder="Unesite broj poena">
                                </div>
                                <div class="col-sm-6">
                                    <input type="submit" value="Uracunaj popust" class="btn btn-primary" onclick="popust()" id="btnPopust">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-1"></div>
                        <% } %>
                    </div>


                    <form method="post" action="PorvrdaPorudzbine">
                        <div class="form-group row">
                            <div class="col-sm-4"></div>
                            <div class="col-sm-4">

                                <% if (session.getAttribute("user") != null && session.getAttribute("rola").toString().matches("Korisnik")) {%>
                                <input type="hidden" name="poeniUnos" id="poeniUnos" value="<%= korisnikDB.getBrojPoena(session.getAttribute("user").toString()) + 5%>">
                                <% } %>
                                <% if (session.getAttribute("user") == null) {%>
                                <input type="hidden" name="imePrezime"  value="<%=request.getParameter("imePrezime")%>">
                                <input type="hidden" name="brTel"  value="<%=request.getParameter("brTel")%>">
                                <input type="hidden" name="nazivMenija"  value="<%= meni.getNazivMenija()%>">
                                <input type="hidden" name="ketSluzbaID"  value="<%= meni.getKetSluzbaID()%>">
                                <input type="hidden" name="vreme"  value="<%= request.getParameter("datum") + " " + request.getParameter("vreme")%>">
                                <input type="hidden" name="cena"   value="<%= ukupnaCena%>">
                                <% } else {   Korisnik korisnik = (Korisnik) request.getAttribute("korisnik");%>
                                <input type="hidden" name="imePrezime"  value="<%= korisnik.getUsername()%>">
                                <input type="hidden" name="brTel"  value="<%= korisnik.getBrTel()%>">
                                <input type="hidden" name="nazivMenija"  value="<%= meni.getNazivMenija()%>">
                                <input type="hidden" name="ketSluzbaID"  value="<%= meni.getKetSluzbaID()%>">
                                <input type="hidden" name="vreme"  value="<%= request.getParameter("datum") + " " + request.getParameter("vreme")%>">
                                <input type="hidden" name="cena" id="ukupnaCenaa"  value="<%= ukupnaCena%>">
                                <% } %>

                                <input type="submit" class="form-control btn btn-success " value="Potvrdi porudzbinu">
                            </div>
                            <div class="col-sm-4"></div>
                     
                                </div>
                    </form>
                </div>
            </section>
        </div>









          <div class="container px-5" id="fot">
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

    <% if (session.getAttribute("user") != null && session.getAttribute("rola").toString().matches("Korisnik")) {%>
    <script type="text/javascript">
                                        function popust() {
                                            var ukupnaCena = parseInt(document.getElementById("ukupnaCena").innerHTML.toString());
                                            var poeni = parseInt(document.getElementById("poeni").value.toString());
                                            var popust = parseInt(document.getElementById("popust").value.toString())

                                            if (popust <= <%= korisnikDB.getBrojPoena(session.getAttribute("user").toString())%> && popust > 0) {
                                                if ((ukupnaCena - ((popust / 100) * ukupnaCena)) > 0) {
                                                    document.getElementById("ukupnaCena").innerHTML = ukupnaCena - ((popust / 100) * ukupnaCena);
                                                    document.getElementById("ukupnaCenaa").value = ukupnaCena - ((popust / 100) * ukupnaCena);
                                                }
                                                document.getElementById("btnPopust").disabled = true;
                                                document.getElementById("popust").disabled = true;
                                                document.getElementById("poeni").value = poeni - popust ;
                                                document.getElementById("poeniUnos").value = parseInt(<%= korisnikDB.getBrojPoena(session.getAttribute("user").toString())%> - popust + 5);
                                            } else
                                            {
//                                                document.getElementById("popust"). = "Mozete iskoristiti samo  poena";
                                                alert('Mozete iskoristiti samo = ' + <%= korisnikDB.getBrojPoena(session.getAttribute("user").toString())%> + " poena");
                                            }
                                        }
    </script>
<% } %>
</body>
</html>

