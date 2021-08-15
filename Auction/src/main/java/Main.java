import DAO.AuctionDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("TEST");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop_final_project?characterEncoding=UTF8&user=root&password=password");

        AuctionDAO dao = new AuctionDAO(conn);
    }
}
