/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2.servlet;

import com.mycompany.mavenproject2.model.Building;
import com.mycompany.mavenproject2.model.Empire;
import com.mycompany.mavenproject2.model.MineralResource;
import com.mycompany.mavenproject2.model.User;
import com.mycompany.mavenproject2.repository.BuildingRepository;
import com.mycompany.mavenproject2.repository.EmpireRepository;
import com.mycompany.mavenproject2.repository.MineralResourceRepository;
import com.mycompany.mavenproject2.repository.SpeciesRepository;
import com.mycompany.mavenproject2.repository.UserRepository;
import com.mycompany.mavenproject2.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author javaee
 */

@WebServlet(name = "SearchByDescriptionServlet", urlPatterns = {"/searchbydesc"})
public class SearchByDescriptionServlet extends HttpServlet {

    @Inject
    UserService uService; 
    @Inject
    EmpireRepository eRepository;
    @Inject
    UserRepository uRepository;
    @Inject
    SpeciesRepository sRepository;
    @Inject
    BuildingRepository bRepository;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        
        User user = ((User) request.getSession().getAttribute("user"));
        String empireName = request.getParameter("searchname");
        String empireDescription = request.getParameter("searchdescription");
        String resID = request.getParameter("searchresources");
        MineralResource resource = new MineralResourceRepository().getById(Integer.parseInt(resID));
        
        Building building = new BuildingRepository().getById(Integer.parseInt(request.getParameter("searchbuilding")));
        
        session.setAttribute("user", user);
        session.setAttribute("species", sRepository.getAll());
        session.setAttribute("empires", eRepository.getAll());
        session.setAttribute("buildings", bRepository.getAll());
        session.setAttribute("allResources", new MineralResourceRepository().getAll());

        List<Empire> empires = eRepository.findEmpire(user, empireName, empireDescription, resource, 0, building);
        
        PrintWriter out = response.getWriter();
        if (empires.isEmpty()) {
            out.println("<p style='color: red;'> Nincs a keresésnek megfelelő Birodalom</p>");
        }else{
            out.println("A keresett birodalmaknak megfelel: ");
            for (Empire empire : empires) {
                out.println(empire.getName() + "<br/>");
            }
        }
        
        getServletContext().getRequestDispatcher("/newhero.jsp").include(request, response);
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
