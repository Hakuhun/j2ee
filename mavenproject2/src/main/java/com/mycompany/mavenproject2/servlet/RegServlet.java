package com.mycompany.mavenproject2.servlet;

import com.mycompany.mavenproject2.service.UserService;
import com.mycompany.mavenproject2.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

@WebServlet(name = "RegServlet", urlPatterns = {"/reg"})
public class RegServlet extends HttpServlet {
        @EJB
        UserService uService;

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


        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        
       MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(RegServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
       messageDigest.update(password.getBytes());
       byte[] digiest = messageDigest.digest();
       String hashedPassword = DatatypeConverter.printHexBinary(digiest);

        User register  = new User(name,hashedPassword,role);
        
        if (uService.login(register)) {
            out.print("User name exists!");
        } else {
            uService.add(register);
            request.getSession().setAttribute("user", register);
        }

        response.setContentType("text/html;charset=UTF-8");
        getServletContext().getRequestDispatcher("/login.jsp").include(request, response);

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
