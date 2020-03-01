package services.read_write_services.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Vector;

public class WriteService {
    private static WriteService ourInstance = new WriteService();

    public static WriteService getInstance() {
        return ourInstance;
    }

    private WriteService() {
    }

    public void writeDataFile(String filePath, Vector<String[]> data){
        try(RandomAccessFile file = new RandomAccessFile(filePath, "rw")){
            String header = file.readLine();
            file.setLength(0);
            for(int i=0; i<header.length(); ++i){
                file.write(header.charAt(i));
            }
            for(int i=0; i<data.size(); ++i){
                file.write('\n');
                String dataItem = String.join(",", data.elementAt(i));
                for(int j=0; j<dataItem.length(); ++j){
                    file.write(dataItem.charAt(j));
                }
            }
        }
        catch(IOException exception){
            exception.printStackTrace();
        }
    }
}
