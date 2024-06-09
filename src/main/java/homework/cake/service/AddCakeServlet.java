package homework.cake.service;

import homework.cake.dao.CakeDao;
import homework.entity.Cake;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AddCake")
public class AddCakeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求
        String name = req.getParameter("name");
        String price = req.getParameter("price");

        //完成读取表单数据的业务
        Cake cake = new Cake();
        cake.setName(name);
        cake.setPrice(Integer.parseInt(price));//若输入的数据本来为非整数则为异常

        CakeDao cakeDao = new CakeDao();
        try {
            cakeDao.saveCake(cake);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }


        //跳转到展示界面
        resp.sendRedirect("list");


    }
}
