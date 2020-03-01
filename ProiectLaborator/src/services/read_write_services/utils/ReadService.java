package services.read_write_services.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Vector;

public class ReadService {
    private static ReadService ourInstance = new ReadService();

    public static ReadService getInstance() {
        return ourInstance;
    }

    private ReadService() {
    }

    public Vector<String[]> readDataFile(String filePath){
        Vector<String[]> data = new Vector<>();
        try(RandomAccessFile file = new RandomAccessFile(filePath, "r")){
            String temp;
            while((temp = file.readLine()) != null){
                data.add(temp.split(","));
            }
        }
        catch(IOException exception){
            System.out.println(Arrays.toString(exception.getStackTrace()));
        }
        return data;
    }

}
