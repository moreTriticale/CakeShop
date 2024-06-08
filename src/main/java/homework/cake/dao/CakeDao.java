package homework.cake.dao;

import homework.entity.Cake;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;


public class CakeDao {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //新增蛋糕功能
    public void saveCake(Cake cake) {
        String name = cake.getName();
        int price = cake.getPrice();
        int state = 1;
        cake.setState(state);
        try {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cake", "root", "yaojinlun");
        String sql = "insert into cake_db (name,price,state) values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, name);
        ps.setInt(2, price);
        ps.setInt(3, state);
        // 执行sql语句
        ps.executeUpdate();
        ps.close();


        System.out.println("to DB success");

        con.close();
        } catch (Exception e) {
            System.out.println("to DB fail");
            throw new RuntimeException(e);
        }

    }

    //修改蛋糕功能
    public void updateState(int id, int state){

    }

    //查询所有在售蛋糕功能
    public List<Cake> findByNormalState(){

        return null;
    }

}
