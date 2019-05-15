package com.mycompany.mavenproject2.servlet;

import com.mycompany.mavenproject2.model.Building;
import com.mycompany.mavenproject2.model.Empire;
import com.mycompany.mavenproject2.model.User;
import com.mycompany.mavenproject2.service.BuildingService;
import com.mycompany.mavenproject2.service.EmpireService;
import com.mycompany.mavenproject2.service.MineralResourceService;
import com.mycompany.mavenproject2.service.SpeciesService;
import com.mycompany.mavenproject2.service.UserService;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession;

@WebServlet(name = "NewBuildingServlet", urlPatterns = {"/building"})
public class NewBuildingServlet extends HttpServlet{

    @EJB
    EmpireService eService;
    @EJB
    UserService uService;
    @EJB
    BuildingService bService;
    @EJB
    SpeciesService sService;
    @EJB
    MineralResourceService mrService;
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int empid = Integer.parseInt(request.getParameter("empire"));
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        
        User sessionUser = ((User) session.getAttribute("user"));
        
        User managedUser = uService.getById(sessionUser.getId());
        
        Empire emp = null;
        try {
            emp = uService.getEmpireOfUserByID(managedUser, empid);
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/newhero.jsp").include(request, response);
        }
        Building build = bService.getById(Integer.parseInt(request.getParameter("building")));
        
//        PrintWriter out = response.getWriter();
        eService.addBuilding(emp, build);
        
        int index = managedUser.getEmpires().indexOf(emp);
        managedUser.getEmpires().set(index, emp);
        uService.modify(managedUser);

        session.setAttribute("user", managedUser);
        session.setAttribute("species", sService.getAll());
        session.setAttribute("empires", eService.getAll());
        session.setAttribute("buildings", bService.getAll());
        session.setAttribute("allResources", mrService.getAll());
        
        getServletContext().getRequestDispatcher("/newhero.jsp").include(request, response);
    }

}