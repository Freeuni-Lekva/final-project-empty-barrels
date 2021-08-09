package Listeners;

import org.apache.commons.dbcp.BasicDataSource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ResourceBundle;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ResourceBundle reader = null;
        try{
            // Reads resources from dbconfig.properties file located
            // under src/main/resources/
            reader = ResourceBundle.getBundle("dbconfig");

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl(reader.getString("db.url"));
            dataSource.setUsername(reader.getString("db.username"));
            dataSource.setPassword(reader.getString("db.password"));

//            Uncomment for testing
//            System.out.println(reader.getString("db.url"));
//            System.out.println(reader.getString("db.username"));
//            System.out.println(reader.getString("db.password"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
