package homework.cake.service;

import homework.cake.dao.CakeDao;
import homework.entity.Cake;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/list")
public class ListCakeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CakeDao cakeDao = new CakeDao();
        List<Cake> cakeList = cakeDao.findByNormalState();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<style>" +
                "  table {" +
                "    margin-left: auto;" +
                "    margin-right: auto;" +
                "  }" +
                "</style>");
        out.println("<table border='1'>");
        out.println("<tr><th>名称</th><th>价格</th><th>操作</th></tr>");

        cakeList.forEach(cake -> {
            if(cake.getState() != 2){
                int id = cake.getId();
                out.println("<tr><td>" + cake.getName() + "</td><td>" + cake.getPrice() + "</td>"+"<td><a href=\"delete?id="+id+"\">下架</a></td>" + "</tr>");
            }
        });
        out.println("<a href=\"add.html\">返回添加页</a>");
    }
}