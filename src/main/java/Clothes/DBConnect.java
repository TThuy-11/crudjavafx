package Clothes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBConnect {
    private Connection connection;

    public DBConnect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/clothes", "root", "");
            System.out.println("Connect successfully!!!");

        } catch (SQLException e) {
            connection = null;
            System.out.println(e);
        }
    }

    public List<clothes> getClothes(){
        ArrayList <clothes> clothes = new ArrayList<>();
        try {
            ResultSet result=connection.prepareStatement("SELECT * from clothes").executeQuery();
            while (result.next()){
                int id = result.getInt( "id");
                String name =result.getString("name");
                String img = result.getString("img");
                String size = result.getString("size");
                Float price = result.getFloat("price");
                System.out.println("========");
                System.out.println(id);
                System.out.println(name);
                System.out.println(img);
                System.out.println(size);
                System.out.println(price);

                clothes.add(new clothes(id,name,img,size,price));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return clothes;
    }

    public void insertClothes(clothes clothes){

        String sql = "INSERT INTO clothes (name, img, size, price) VALUES ('"+clothes.name+"','"+clothes.img+"','"+clothes.size+"',"+clothes.price+")";
        System.out.println(sql);

        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateClothes (clothes clothes){
        String sql= "UPDATE clothes SET name='"+clothes.name+"', img='"+clothes.img+"', size='"+clothes.size+"', price="+clothes.price+" WHERE id="+clothes.id;
        System.out.println(sql);
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteClothes (int id){
        String sql= "DELETE from clothes WHERE id="+id;
        System.out.println(sql);
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

