package com.mycompany.mavenproject2.servlet;

import com.mycompany.mavenproject2.repository.UserRepository;
import com.mycompany.mavenproject2.repository.SpeciesRepository;
import com.mycompany.mavenproject2.model.User;
import com.mycompany.mavenproject2.repository.BuildingRepository;
import com.mycompany.mavenproject2.repository.MineralResourceRepository;
import com.mycompany.mavenproject2.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    
    @Inject
    UserService uService; 
    UserRepository uRepository = new UserRepository();
    SpeciesRepository sRepository = new SpeciesRepository();
    BuildingRepository bRepository = new BuildingRepository();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        
        PrintWriter out = response.getWriter();
        if(this.uService.login(request.getParameter("name"), request.getParameter("password"))) {
            User login = uRepository.getByNameAndPassword(request.getParameter("name"), request.getParameter("password"));
            request.setAttribute("species",sRepository.getAll());
            request.setAttribute("buildings", bRepository.getAll());
            request.setAttribute("allResources", new MineralResourceRepository().getAll());
            request.getSession().setAttribute("user", login);
            getServletContext().getRequestDispatcher("/newhero.jsp").include(request, response);
        }
        else     out.print("Regisztráció szükséges");
        
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
