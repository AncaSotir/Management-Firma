package loggers;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Timestamp;
import java.util.Calendar;

public class CompanyLogger {
    private static CompanyLogger ourInstance = new CompanyLogger();

    public static CompanyLogger getInstance() {
        return ourInstance;
    }

    private CompanyLogger() {
    }

    public void logAction(String actionDesc){

        String loggerFilePath = "src/loggers/logger.csv";

        try(RandomAccessFile file = new RandomAccessFile(loggerFilePath, "rw")){

            file.seek(file.length());

            java.sql.Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String timestamp = ts.toString();

            for(int i=0; i<timestamp.length(); ++i){
                file.write(timestamp.charAt(i));
            }

            file.write(',');

            for(int i=0; i<actionDesc.length(); ++i){
                file.write(actionDesc.charAt(i));
            }

            file.write('\n');
        }
        catch(IOException exception){
            exception.printStackTrace();
        }
    }

    public void wipeLogger(){

        String loggerFilePath = "src/loggers/logger.csv";

        try(RandomAccessFile file = new RandomAccessFile(loggerFilePath, "rw")){
            file.setLength(0);
        }
        catch(IOException exception){
            exception.printStackTrace();
        }
    }
}
