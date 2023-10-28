/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mysql.cj.util.StringUtils;
import database.KorisnikDB;
import database.MeniDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;

/**
 *
 * @author Nemanja
 */
public class Porucivanje extends HttpServlet {
    
    MeniDB meniDB = new MeniDB();
    KorisnikDB korisnikDB = new KorisnikDB();
    Meni meni = null;
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
        
        meni = meniDB.uzmiMeni(request.getParameter("meniID"));
        request.setAttribute("meni",meni);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("poruciPodaci.jsp");
              dispatcher.forward(request,response);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Porucivanje</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Porucivanje at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
          HttpSession sesija = request.getSession();
          
          if(sesija.getAttribute("user") == null){
              if(!StringUtils.isNullOrEmpty(request.getParameter("imePrezime")) && !StringUtils.isNullOrEmpty(request.getParameter("brTel")) && !StringUtils.isNullOrEmpty(request.getParameter("brTel"))) 
              {
                  
                   int ljudi = Integer.parseInt(request.getParameter("ljudi"));
                   meni = meniDB.uzmiMeni(request.getParameter("meniID"));
                
                   request.setAttribute("meni", meni);
                
                  request.setAttribute("ljudi",ljudi);
                  
                  RequestDispatcher dispatcher = request.getRequestDispatcher("potvrdiPorudzbinu.jsp");
                  dispatcher.forward(request,response);
              }else{
                request.setAttribute("message", "Morate popuniti sva polja!");
                
                meni = meniDB.uzmiMeni(request.getParameter("meniID"));
                
                request.setAttribute("meni", meni);
                
                
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("poruciPodaci.jsp");
              dispatcher.forward(request,response);
              }
          }else if (sesija.getAttribute("rola").toString().matches("Korisnik")){
             
              if(!StringUtils.isNullOrEmpty(request.getParameter("ljudi"))){
              
                  int ljudi = Integer.parseInt(request.getParameter("ljudi"));
              korisnik = korisnikDB.uzmiKorisnika(sesija.getAttribute("user").toString());
              meni = meniDB.uzmiMeni(request.getParameter("meniID"));
              
            request.setAttribute("meni", meni);
              request.setAttribute("korisnik", korisnik);
              request.setAttribute("ljudi",ljudi);
              
              RequestDispatcher dispatcher = request.getRequestDispatcher("potvrdiPorudzbinu.jsp");
              dispatcher.forward(request,response);
             }else{
                  request.setAttribute("message", "Morate uneti broj ljudi!");
                
                meni = meniDB.uzmiMeni(request.getParameter("meniID"));
                
                request.setAttribute("meni", meni);
                
                
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("poruciPodaci.jsp");
              dispatcher.forward(request,response);
              }
          }else if(sesija.getAttribute("rola").toString().matches("Menadzer") || sesija.getAttribute("rola").toString().matches("Admin")){
            request.setAttribute("message", "Admini i menadzeri ne mogu vrsiti porudzbine, izlogujte se!");
            
                meni = meniDB.uzmiMeni(request.getParameter("meniID"));
                
                request.setAttribute("meni", meni);
                
                
                 RequestDispatcher dispatcher = request.getRequestDispatcher("poruciPodaci.jsp");
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
