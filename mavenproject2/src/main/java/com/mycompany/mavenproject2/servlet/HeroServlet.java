package com.mycompany.mavenproject2.servlet;

import com.mycompany.mavenproject2.service.HeroService;
import com.mycompany.mavenproject2.service.UserService;
import com.mycompany.mavenproject2.model.Hibryd;
import com.mycompany.mavenproject2.model.User;
import com.mycompany.mavenproject2.model.Hero;
import com.mycompany.mavenproject2.service.BuildingService;
import com.mycompany.mavenproject2.service.EmpireService;
import com.mycompany.mavenproject2.service.MineralResourceService;
import com.mycompany.mavenproject2.service.SpeciesService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "HeroServlet", urlPatterns = {"/hero"})
public class HeroServlet extends HttpServlet {

    @EJB
    UserService uService;
    
    HeroService hService;
    @EJB
    EmpireService eService;
    @EJB
    SpeciesService sService;
    @EJB
    BuildingService bService;
    @EJB
    MineralResourceService mrService;
    

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();

        Hero hero = new Hero();
        hero.setName(request.getParameter("name"));
        hero.setDescription(request.getParameter("description"));

        Enumeration<String> params = request.getParameterNames();

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        while (params.hasMoreElements()) {
            String nextElement = params.nextElement();
            if (nextElement.startsWith("pspec_") && Integer.parseInt(request.getParameter(nextElement)) > 0) {
                int speciesID = Integer.parseInt(nextElement.substring(6));
                Hibryd hibryd = new Hibryd(sService.getById(speciesID), Byte.parseByte(request.getParameter(nextElement)));
                hero.getHibryds().add(hibryd);
                //hibryd.setHero(hero);
            }
        }
        
        if (hService.isValid(hero)) {
            User sessionUser  = (User) session.getAttribute("user");
            User managedUser = uService.getById(sessionUser.getId());
            hero.setUser(managedUser);
            managedUser.getHeroes().add(hero);      
            uService.modify(managedUser);
            
            session.setAttribute("user", managedUser);
            session.setAttribute("species", sService.getAll());
            session.setAttribute("empires", eService.getAll());
            session.setAttribute("buildings", bService.getAll());
            session.setAttribute("allResources", mrService.getAll());
            getServletContext().getRequestDispatcher("/newhero.jsp").include(request, response);
        } else {
            out.print("nem valid a config");
        }

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
