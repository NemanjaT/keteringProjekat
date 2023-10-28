/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mysql.cj.util.StringUtils;
import database.KeteringsluzbaDB;
import database.MeniDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Keteringsluzba;
import models.Meni;

/**
 *
 * @author Nemanja
 */
public class DodajMeni extends HttpServlet {
KeteringsluzbaDB sluzbaDB = new KeteringsluzbaDB();
    MeniDB meniDB = new MeniDB();
    Meni meni = null;
    Keteringsluzba sluzba = null;
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
            if(sesija.getAttribute("rola").toString().matches("Menadzer")){
                
                        sluzba = sluzbaDB.uzmiSluzbu(request.getParameter("sluzbaID"));
                        request.setAttribute("sluzba",sluzba);
                        request.setAttribute("novMeniID", meniDB.novMeni());
                
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("dodajMeni.jsp");
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
            out.println("<title>Servlet DodajMeni</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DodajMeni at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
@Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
          HttpSession sesija = request.getSession();
         
         if(sesija.getAttribute("rola") != null){
             if(sesija.getAttribute("rola").toString().matches("Menadzer")){
                 if(!StringUtils.isNullOrEmpty(request.getParameter("nazivMenija")) &&
                         !StringUtils.isNullOrEmpty(request.getParameter("predjelo")) &&
                         !StringUtils.isNullOrEmpty(request.getParameter("glavnoJelo")) &&
                         !StringUtils.isNullOrEmpty(request.getParameter("dezert")) && !StringUtils.isNullOrEmpty(request.getParameter("cenaPoOsobi")))
                 {
                     try{
                  Meni  meni = new Meni(
                     Integer.parseInt(request.getParameter("meniID")),
                     request.getParameter("nazivMenija"),
                     request.getParameter("predjelo"),
                     request.getParameter("glavnoJelo"),
                     request.getParameter("dezert"),
                     Integer.parseInt(request.getParameter("cenaPoOsobi")),
                     Integer.parseInt(request.getParameter("ketSluzbaID")));
                     
                     meniDB.dodajMeni(meni);
                     response.sendRedirect("MenadzerProfil");
                     }catch(Exception e){
                         e.printStackTrace();
                     }
                 }
                 else{
                     request.setAttribute("message", "Sva polja moraju biti popunjena");
                     
                     doGet(request,response);
                 }
             }
             else{
                 RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request,response);
             }
         }
         else{
             RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request,response);
         }
            response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DodajSluzbu</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DodajSluzbu at</h1> " );
            out.println("</body>");
            out.println("</html>");
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
