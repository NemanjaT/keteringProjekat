/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mysql.cj.util.StringUtils;
import database.KorisnikDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Korisnik;

/**
 *
 * @author Nemanja
 */
public class IzmeniKorisnika extends HttpServlet {
          
    
     KorisnikDB korisnikDB = new KorisnikDB();
     Korisnik korisnik = null;
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
               
                korisnik = korisnikDB.uzmiKorisnika(request.getParameter("username"));
               
                    
                    request.setAttribute("korisnik", korisnik);
               
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("izmeniKorisnika.jsp");
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
            out.println("<title>Servlet IzmeniKorisnika</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IzmeniKorisnika at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession sesija = request.getSession();
         
         if(sesija.getAttribute("rola") != null){
            if(sesija.getAttribute("rola").toString().matches("Admin")){
                 
                 
                 
                 
                    
                     
                     if(!StringUtils.isNullOrEmpty(request.getParameter("imePrezime")) && 
                           !StringUtils.isNullOrEmpty(request.getParameter("brTel")) && 
                             !StringUtils.isNullOrEmpty(request.getParameter("poeni")) && !StringUtils.isNullOrEmpty(request.getParameter("adresa")))
                     {
                        
                        korisnik.setImePrezime(request.getParameter("imePrezime"));
                        korisnik.setAdresa(request.getParameter("adresa"));
                        korisnik.setBrTel(request.getParameter("brTel"));
                        korisnik.setPoeni(Integer.parseInt(request.getParameter("poeni")));
                        

                        try{
                        korisnikDB.azurirajKorisnika(korisnik);
                        response.sendRedirect("AdminProfil");
                        }catch(Exception e){
                            e.printStackTrace();
                        }   
                             }else{
                                      request.setAttribute("message", "Sva polja moraju biti popunjena!");
                                      request.setAttribute("korisnik", korisnik);
                                      RequestDispatcher dispatcher = request.getRequestDispatcher("izmeniKorisnika.jsp");
                                      dispatcher.forward(request,response);

                             }
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
            out.println("<title>Servlet IzmeniKorisnika</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IzmeniKorisnika at " + request.getContextPath() + request.getParameter("korisnickoIme")  + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
             }
    }

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
