package com.mycompany.mavenproject2.servlet;

import com.mycompany.mavenproject2.service.EmpireService;
import com.mycompany.mavenproject2.model.MineralResource;
import com.mycompany.mavenproject2.repository.MineralResourceRepository;
import com.mycompany.mavenproject2.model.User;
import com.mycompany.mavenproject2.model.Empire;
import com.mycompany.mavenproject2.model.Resources;
import com.mycompany.mavenproject2.service.BuildingService;
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

@WebServlet(name = "EmpireServlet", urlPatterns = {"/empire"})
public class EmpireServlet extends HttpServlet{

    @EJB
    EmpireService eService;
    
    @EJB
    SpeciesService sService;
    
    @EJB
    MineralResourceService mrService;
    
    @EJB
    BuildingService bService;
    
    @EJB
    UserService uService;
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();

        User sessionUser = ((User) session.getAttribute("user"));
        User managedUser = uService.getById(sessionUser.getId());
        
        Empire empire = new Empire();
        empire.setName(request.getParameter("name"));
        empire.setDescription(request.getParameter("description"));
        empire.setUser(managedUser);
        
        for (MineralResource mineral : mrService.getAll()) {
            int count = eService.randomCount(); //itt add randomot hozzá
            Resources resource = new Resources(mineral, count); //ez lesz a végleges ásvány ami az empirehez hozzá lesz adva
            mrService.add(mineral);
            empire.getResources().add(resource);
        }

        //itt megkapja a user az empire-t, ez nem tuti h jó:
         if (!eService.isExists(managedUser,empire)) {
            managedUser.getEmpires().add(empire);
            uService.modify(managedUser);
         }

            session.setAttribute("user", managedUser);
            session.setAttribute("species", sService.getAll());
            session.setAttribute("empires", eService.getAll());
            session.setAttribute("buildings", bService.getAll());
            session.setAttribute("allResources", new MineralResourceRepository().getAll());
        
        getServletContext().getRequestDispatcher("/newhero.jsp").include(request, response);
            

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}