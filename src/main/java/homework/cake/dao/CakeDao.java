package homework.cake.dao;

import homework.entity.Cake;

import java.sql.*;
import java.util.ArrayList;
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
        Connection con = null;
        List<Cake> list = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cake", "root", "yaojinlun");
            list = new ArrayList<>();
            String sql = "select * from cake_db";
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int state = rs.getInt("state");
                Cake cake = new Cake(id,name,price,state);
                list.add(cake);
            }

        } catch (Exception e) {
           e.printStackTrace();
        }finally {
            if (con != null){
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

}
