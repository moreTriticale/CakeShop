package homework.cake.service;

import homework.cake.dao.CakeDao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

@WebServlet("/delete")
public class UpdateCakeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        CakeDao cakeDao = new CakeDao();
        cakeDao.updateState(Integer.parseInt(id),2);
        response.sendRedirect("list");
    }
}