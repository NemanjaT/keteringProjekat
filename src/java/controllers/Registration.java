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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nemanja
 */
public class Registration extends HttpServlet {

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
        
        KorisnikDB korisnikData = new KorisnikDB();
        
        try{
            korisnikData.connect();
            
            if(!StringUtils.isNullOrEmpty(request.getParameter("username")) ||  !StringUtils.isNullOrEmpty(request.getParameter("pass"))  || !StringUtils.isNullOrEmpty(request.getParameter("imePrezime")) ||  !StringUtils.isNullOrEmpty(request.getParameter("brTel")) ||  !StringUtils.isNullOrEmpty(request.getParameter("adresa"))) 
            {
                String passParametar = request.getParameter("pass");
                String passPotvrdaParametar = request.getParameter("passConf");
                if(!korisnikData.proveraUsername(request.getParameter("username")))
                        {
                            if(passParametar.matches(passPotvrdaParametar))
                            {
                                Korisnik korisnik = new Korisnik(request.getParameter("username"), request.getParameter("pass"),
                                        request.getParameter("imePrezime"), request.getParameter("brTel"), request.getParameter("adresa"), 0);
                            
                            if(korisnikData.dodajKorisnika(korisnik)){
                                request.setAttribute("message", "Uspesno ste se registrovali, mozete se ulogovati.");
                                RequestDispatcher dispatcher = request.getRequestDispatcher("registracija.jsp");
                                dispatcher.forward(request, response);
                            }else{
                                request.setAttribute("message", "Nije moguca registracija pokusajte kasnije!");
                                RequestDispatcher dispatcher = request.getRequestDispatcher("registracija.jsp");
                                dispatcher.forward(request, response);
                            }
                            
                            }else{
                                request.setAttribute("message", "Lozinke se ne poklapaju!");
                                RequestDispatcher dispatcher = request.getRequestDispatcher("registracija.jsp");
                                dispatcher.forward(request, response);
                            }
                        }else{
                    request.setAttribute("message", "Vec postoji uneto korisnicko ime, pokusajte drugo.");
                                RequestDispatcher dispatcher = request.getRequestDispatcher("registracija.jsp");
                                dispatcher.forward(request, response);
                }
            }else{
                request.setAttribute("message", "Morate popuniti sva polja!");
                                RequestDispatcher dispatcher = request.getRequestDispatcher("registracija.jsp");
                                dispatcher.forward(request, response);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registration</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registration at " + request.getContextPath() + "</h1>");
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
