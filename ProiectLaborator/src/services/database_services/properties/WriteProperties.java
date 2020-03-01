package services.database_services.properties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class WriteProperties {
    public static void main(String[] args){

        Properties properties = new Properties();

        try {
            properties.load(new FileReader(new File("src/properties/db_info.properties")));

            properties.setProperty("DB_URL", "jdbc:mysql://localhost:3306/company?serverTimezone=UTC");
            properties.setProperty("DB_USER", "root");
            properties.setProperty("DB_PASS", "root");

            properties.store(new PrintStream(new File("src/properties/db_info.properties")), "properties");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
