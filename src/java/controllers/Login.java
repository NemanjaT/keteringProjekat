/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mysql.cj.util.StringUtils;
import database.*;
import models.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nemanja
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       String rola = request.getParameter("uloga");
 if(!StringUtils.isNullOrEmpty(request.getParameter("username")) ||  !StringUtils.isNullOrEmpty(request.getParameter("pass"))){
       if(rola.matches("Korisnik")){
           KorisnikDB korisnikDB = new KorisnikDB();
           Korisnik korisnik = null;
           try{
               korisnik = korisnikDB.validacijaKorisnika(request.getParameter("username"), request.getParameter("password"));
           }catch(SQLException e){
               System.out.println("Greska" + e);
           }
           if (korisnik != null){
               HttpSession sesija = request.getSession();
               
               sesija.setAttribute("user", korisnik.getUsername());
               sesija.setAttribute("rola", rola);
               sesija.setAttribute("pass", korisnik.getPassword());
               
               sesija.setMaxInactiveInterval(2*60*60);
               
               sesija.setAttribute("korisnikDB", korisnikDB);
               response.sendRedirect(request.getContextPath() + "/Pocetna");
           }else{
               request.setAttribute("message", "Pogresan username ili lozinka!");
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
               dispatcher.forward(request,response);
           }
       }
       
       if(rola.matches("Menadzer")){
           MenadzerDB menadzerDB = new MenadzerDB();
           Menadzer menadzer = null;
           try{
               menadzer = menadzerDB.validacijaMenadzera(request.getParameter("username"), request.getParameter("password"));
           }catch(SQLException e){
               e.printStackTrace();
           }
           if (menadzer != null){
               HttpSession sesija = request.getSession();
               
               sesija.setAttribute("user", menadzer.getUsername());
               sesija.setAttribute("rola", rola);
               sesija.setAttribute("pass", menadzer.getPassword());
               
               sesija.setMaxInactiveInterval(2*60*60);
               
               sesija.setAttribute("korisnikDB", menadzerDB);
               response.sendRedirect(request.getContextPath() + "/MenadzerProfill");
           }else{
               request.setAttribute("message", "Pogresan username ili lozinka!");
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
               dispatcher.forward(request,response);
           }
       }
       
       
       if(rola.matches("Admin")){
           AdminDB adminDB = new AdminDB();
           Admin admin = null;
           try{
               admin = adminDB.validacijaAdmina(request.getParameter("username"), request.getParameter("password"));
           }catch(SQLException e){
               e.printStackTrace();
           }
           if (admin != null){
               HttpSession sesija = request.getSession();
               
               sesija.setAttribute("user", admin.getUsername());
               sesija.setAttribute("rola", rola);
               sesija.setAttribute("pass", admin.getPassword());
               
               sesija.setMaxInactiveInterval(2*60*60);
               
               sesija.setAttribute("korisnikDB", adminDB);
               response.sendRedirect(request.getContextPath() + "/AdminProfill");
           }else{
               request.setAttribute("message", "Pogresan username ili lozinka!");
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
               dispatcher.forward(request,response);
           }
       }
       
 }else{
     request.setAttribute("message", "Morate uneti oba polja!");
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
               dispatcher.forward(request,response);
 }
       
       
        
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + rola + "</h1>");
            if (rola.matches("Korisnik")){
                out.println("Podudaranje");
                
            }else{
                out.println("NEPODUDARANJE");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
