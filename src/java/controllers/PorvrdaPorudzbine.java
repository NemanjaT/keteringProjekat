/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import database.KorisnikDB;
import database.PorudzbinaDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Porudzbina;

/**
 *
 * @author Nemanja
 */
public class PorvrdaPorudzbine extends HttpServlet {
    
    PorudzbinaDB porudzbinaDB = new PorudzbinaDB();
    Porudzbina porudzbina = null;

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
        
        HttpSession sesija =  request.getSession();
        String korisnik = "";
        
        if(sesija.getAttribute("user") != null){
            korisnik = sesija.getAttribute("user").toString();
        }
        
        String username = request.getParameter("imePrezime").toString();
        String brTel = request.getParameter("brTel").toString();
        String nazivMenija = request.getParameter("nazivMenija").toString();
        int ketSluzbaID = Integer.parseInt(request.getParameter("ketSluzbaID").toString());
        String vreme = request.getParameter("vreme").toString();
        int cena = Integer.parseInt(request.getParameter("cena").toString());
        
        porudzbina = new Porudzbina(0,
        nazivMenija,
        username,
        ketSluzbaID,
        vreme,
        brTel,
        username,
        cena);
        
        if(request.getParameter("poeniUnos") != null){
          KorisnikDB korisnikDB = new KorisnikDB();  
          try{
          korisnikDB.azurirajPoene(korisnik,Integer.parseInt(request.getParameter("poeniUnos")));
          }catch(Exception e){
              System.out.println("GRESKA!" + e);
          }
        }
        
        try{
            porudzbinaDB.dodajPorudzbinu(porudzbina);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("poruceno.jsp");
        dispatcher.forward(request,response);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PorvrdaPorudzbine</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PorvrdaPorudzbine at "+ korisnik + Integer.parseInt(request.getParameter("poeniUnos")) +"</h1>");
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
