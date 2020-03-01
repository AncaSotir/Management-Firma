package services.database_services.properties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

public class ReadProperties {
    public static Vector<String> readProperties(){

        Vector<String> dbProperties = new Vector<>(3);

        Properties properties = new Properties();

        try {
            properties.load(new FileReader(new File("src/services/database_services/properties/db_info.properties")));

            dbProperties.add(properties.getProperty("DB_URL"));
            dbProperties.add(properties.getProperty("DB_USER"));
            dbProperties.add(properties.getProperty("DB_PASS"));

//            properties.store(new PrintStream(new File("src/properties/db_info.properties")), "properties");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dbProperties;
    }
}
