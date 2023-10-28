/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mysql.cj.util.StringUtils;
import database.KeteringsluzbaDB;
import database.MenadzerDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Menadzer;

/**
 *
 * @author Nemanja
 */
public class DodajMenadzera extends HttpServlet {

    KeteringsluzbaDB sluzbaDB = new KeteringsluzbaDB();
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession sesija = request.getSession();
        
        if(sesija.getAttribute("rola") != null){
            if(sesija.getAttribute("rola").toString().matches("Admin")){
                try{
                    sluzbaDB.connect();
                    request.setAttribute("sluzbe", sluzbaDB.uzmiSve());
                }catch(Exception e){
                    e.printStackTrace();
                }
                
              
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("dodajMenadzera.jsp");
                dispatcher.forward(request,response);
                        
            }else{
                 RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request,response);
            }
        }else{
             RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request,response);
        }
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DodajMenadzera</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DodajMenadzera at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);

         HttpSession sesija = request.getSession();
         
         if(sesija.getAttribute("rola") != null){
             if(sesija.getAttribute("rola").toString().matches("Admin")){
                 
                 MenadzerDB menadzerDB = new MenadzerDB();
                 
                 try{
                     menadzerDB.connect();
                     
                     if(!StringUtils.isNullOrEmpty(request.getParameter("username")) && 
                           !StringUtils.isNullOrEmpty(request.getParameter("pass")) && 
                             !StringUtils.isNullOrEmpty(request.getParameter("imePrezime")) &&
                             !StringUtils.isNullOrEmpty(request.getParameter("telefon")) &&
                                     !StringUtils.isNullOrEmpty(request.getParameter("ketSluzbaID")))
                     {
                         String lozinka = request.getParameter("pass");
                         String potvrdaLozinke = request.getParameter("passConf");
                         if(!menadzerDB.proveraUsername(request.getParameter("username"))){
                             if(lozinka.matches(potvrdaLozinke)){
                                 
                                 Menadzer menadzer = new Menadzer(request.getParameter("username"), request.getParameter("pass")
                                    ,Integer.parseInt(request.getParameter("ketSluzbaID"))      
                                ,request.getParameter("imePrezime") , request.getParameter("telefon") );
                                 
                                if(menadzerDB.dodajMenadzera(menadzer)){
                                     response.sendRedirect("AdminProfil");
                                 }else{
                                      request.setAttribute("message", "Greska prilikom unosa!");
                                      RequestDispatcher dispatcher = request.getRequestDispatcher("dodajMenadzera.jsp");
                                      dispatcher.forward(request,response);
                                 }
                             }else{
                                      request.setAttribute("message", "Lozinke se ne poklapaju!");
                                      RequestDispatcher dispatcher = request.getRequestDispatcher("dodajMenadzera.jsp");
                                      dispatcher.forward(request,response);

                             }
                         }else{
                             request.setAttribute("message", "Korisnicko ime vec postoji pokusajte drugo ime!");
                                      RequestDispatcher dispatcher = request.getRequestDispatcher("dodajMenadzera.jsp");
                                      dispatcher.forward(request,response);

                         }
                     }else{
                         request.setAttribute("message", "Morate popuniti sva polja!");
                                      RequestDispatcher dispatcher = request.getRequestDispatcher("dodajMenadzera.jsp");
                                      dispatcher.forward(request,response);

                     }
                 }catch(Exception e){
                     e.printStackTrace();
                     System.out.println("GRESKA" + e);
                 }
             }else{
                 
                                      RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                                      dispatcher.forward(request,response);

             }
             
         }else{
             
                                      RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                                      dispatcher.forward(request,response);

         }

       


    }
}

//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
